package com.wsdm.stock.service.impl;

import com.alibaba.fastjson.JSON;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.ResultEnums;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.DictUtils;
import com.wsdm.common.utils.StringUtils;
import com.wsdm.stock.domain.SkStock;
import com.wsdm.stock.domain.SkStockBatch;
import com.wsdm.stock.domain.SkStockChange;
import com.wsdm.stock.domain.SkStockRequest;
import com.wsdm.stock.mapper.SkStockBatchLockMapper;
import com.wsdm.stock.mapper.SkStockBatchMapper;
import com.wsdm.stock.mapper.SkStockChangeMapper;
import com.wsdm.stock.mapper.SkStockMapper;
import com.wsdm.stock.service.IPdProduceHardwareSupport;
import com.wsdm.stock.service.ISkStockBatchService;
import com.wsdm.stock.service.ISkStockChangeService;
import com.wsdm.stock.service.ISkStockService;
import com.wsdm.stock.util.CodeUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * 库存主Service业务层处理
 *
 * @author wsdm
 * @date 2020-12-07
 */
@Service
public class SkStockServiceImpl implements ISkStockService
{
    private static final Logger log = LoggerFactory.getLogger(SkStockServiceImpl.class);

    @Autowired
    private SkStockMapper             skStockMapper;
    @Autowired
    private ISkStockBatchService      skStockBatchService;
    @Autowired
    private ISkStockChangeService     skStockChangeService;
    @Autowired
    private SkStockBatchMapper        skStockBatchMapper;
    @Autowired
    private SkStockBatchLockMapper    skStockBatchLockMapper;
    @Autowired
    private SkStockChangeMapper       skStockChangeMapper;
    @Autowired
    private IPdProduceHardwareSupport pdProduceHardwareSupport;

    /**
     * 查询库存主
     *
     * @param id 库存主ID
     * @return 库存主
     */
    @Override
    public SkStock selectSkStockById(Long id)
    {
        SkStock skStock = skStockMapper.selectSkStockById(id);
        skStock.setModel(pdProduceHardwareSupport.getModel(skStock.getProduce()));
        String name = DictUtils.getDictLabel("dict_stock_subarea", skStock.getCode());
        if (StringUtils.isNotEmpty(name))
        {
            skStock.setName(name);
        }
        return skStock;
    }

    /**
     * 查询库存主列表
     *
     * @param skStock 库存主
     * @return 库存主
     */
    @Override
    public List<SkStock> selectSkStockList(SkStock skStock)
    {
        List<SkStock> stocks = skStockMapper.selectSkStockList(skStock);
        if (CollectionUtils.isEmpty(stocks))
        {
            return stocks;
        }
        List<Future> futures = new ArrayList<>();
        for (SkStock stock : stocks)
        {
            futures.add(CompletableFuture.runAsync(() -> {
                SkStockBatch first = skStockBatchMapper.selectFirst(stock.getCode(), stock.getProduce());
                if (first != null)
                {
                    stock.setOpeningStockNum(first.getOpeningStockNum());
                }
                SkStockBatch summary = skStockBatchMapper.summary(stock.getCode(), stock.getProduce());
                if (summary != null)
                {
                    stock.setHistoryStockNum(summary.getOpeningStockNum());
                }

                stock.setModel(pdProduceHardwareSupport.getModel(stock.getProduce()));

                String name = DictUtils.getDictLabel("dict_stock_subarea", stock.getCode());
                if (StringUtils.isNotEmpty(name))
                {
                    stock.setName(name);
                }
            }));
        }
        for (Future future : futures)
        {
            try
            {
                future.get();
            }
            catch (Exception e)
            {
                log.error(e.toString(), e);
            }
        }
        return stocks;
    }

    /**
     * 新增库存主
     *
     * @param skStock 库存主
     * @return 结果
     */
    @Override
    public int insertSkStock(SkStock skStock)
    {
        skStock.setCreateTime(DateUtils.getNowDate());
        return skStockMapper.insertSkStock(skStock);
    }

    /**
     * 原料采购-在途
     *
     * @param skStocks 库存主
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult insertStockTransit(List<SkStockRequest> skStocks)
    {
        // 库存主
        for (SkStockRequest stockRequest : skStocks)
        {
            // 查询物料是否已初始化
            if (StringUtils.isEmpty(stockRequest.getCode()) || StringUtils.isEmpty(stockRequest.getProduce()))
            {
                return AjaxResult.error(ResultEnums.NULL_PARAM);
            }
            SkStock stock = new SkStock();
            stock.setCode(stockRequest.getCode());
            stock.setProduce(stockRequest.getProduce());
            List<SkStock> list = selectSkStockList(stock);
            if (CollectionUtils.isEmpty(list))
            {
                // 新增记录
                insertSkStock(stockRequest);
            }
            else
            {
                // 更新在途库存数量记录
                SkStock data = list.get(0);
                BigDecimal total = data.getStockTotal();
                BigDecimal stockTransitNum = data.getStockTransitNum();
                stockRequest.setId(data.getId());
                stockRequest.setStockTotal(stockRequest.getStockTotal().add(total));
                stockRequest.setStockTransitNum(stockRequest.getStockTransitNum().add(stockTransitNum));
                updateSkStock(stockRequest);
            }
        }
        return AjaxResult.success();
    }

    /**
     * 原料采购-入库
     *
     * @param stockRequests 库存主
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult insertPurchaseStock(List<SkStockRequest> stockRequests)
    {
        for (SkStockRequest stockRequest : stockRequests)
        {
            String stockCode = stockRequest.getCode();
            String stockName = stockRequest.getName();
            String produceName = stockRequest.getProduceName();
            String produce = stockRequest.getProduce();
            String supplierCode = stockRequest.getSupplierCode();
            // 变动数量
            BigDecimal changeNum = stockRequest.getChangeNum();
            // 操作类型
            String operation = stockRequest.getOperation();
            // 操作描述
            String operationName = stockRequest.getOperationName();
            if (StringUtils.isEmpty(stockCode) || StringUtils.isEmpty(produce))
            {
                return AjaxResult.error(ResultEnums.NULL_PARAM);
            }
            // 变动数量为0不操作数据库
            if (changeNum.compareTo(BigDecimal.ZERO) == 0)
            {
                continue;
            }
            // 查询库存表信息
            SkStock stock = new SkStock();
            stock.setCode(stockCode);
            stock.setProduce(produce);
            List<SkStock> list = selectSkStockList(stock);
            SkStock data = list.get(0);
            stock.setId(data.getId());
            stock.setName(stockName);
            stock.setProduceName(produceName);
            // 更新库存表
            BigDecimal stockTotal = null == data.getStockTotal() ? BigDecimal.ZERO : data.getStockTotal();
            // 在途数量减少
            BigDecimal stockTransitNum =
                    null == data.getStockTransitNum() ? BigDecimal.ZERO : data.getStockTransitNum();
            stockTransitNum = stockTransitNum.subtract(changeNum);
            stockTransitNum = stockTransitNum.compareTo(BigDecimal.ZERO) >= 0 ? stockTransitNum : BigDecimal.ZERO;
            // 可用数量增加
            BigDecimal totalStockAvailableNum =
                    null == data.getStockAvailableNum() ? BigDecimal.ZERO : data.getStockAvailableNum();
            BigDecimal stockAvailableNum = stockRequest.getStockAvailableNum();
            totalStockAvailableNum = totalStockAvailableNum.add(stockAvailableNum);
            // 报损数量增加
            BigDecimal totalFaultyNum = null == data.getStockFaultyNum() ? BigDecimal.ZERO : data.getStockFaultyNum();
            BigDecimal faultyNum = stockRequest.getStockFaultyNum();
            totalFaultyNum = totalFaultyNum.add(faultyNum);
            // 总库存不变
            stock.setStockTotal(stockTotal.add(changeNum));
            stock.setStockTransitNum(stockTransitNum);
            stock.setStockAvailableNum(totalStockAvailableNum);
            stock.setStockFaultyNum(totalFaultyNum);
            updateSkStock(stock);

            // 记录批次表
            SkStockBatch batch = new SkStockBatch();
            batch.setStockCode(stockCode);
            batch.setStockName(stockName);
            batch.setProduceName(produceName);
            batch.setProduce(produce);
            // 生成批次编号
            String batchPrefix = DateUtils.parseDateToStr(DateUtils.YYYYMM, new Date());
            String maxCode = skStockBatchService.getMaxBatchCode(batchPrefix);
            String batchCode = CodeUtils.getOneCode(batchPrefix, maxCode, 1);
            batch.setBatchCode(batchCode);
            // 批次总数量
            batch.setBatchTotal(changeNum);
            // 期初数量
            batch.setOpeningStockNum(changeNum);
            // 批次可用数量
            batch.setStockAvailableNum(stockAvailableNum);
            // 批次报损
            batch.setStockFaultyNum(faultyNum);
            batch.setSupplierCode(supplierCode);
            skStockBatchService.insertSkStockBatch(batch);

            // 记录变动表
            SkStockChange stockChange = new SkStockChange();
            stockChange.setChangeNum(changeNum);
            stockChange.setBatchCode(batchCode);
            stockChange.setStockCode(stockCode);
            stockChange.setStockName(stockName);
            stockChange.setProduce(produce);
            stockChange.setProduceName(produceName);
            stockChange.setOperation(operation);
            stockChange.setOperationName(operationName);
            skStockChangeService.insertSkStockChange(stockChange);
        }
        return AjaxResult.success();
    }

    /**
     * 原料采购-退货
     *
     * @param stockRequests 库存主
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult returnPurchaseStock(List<SkStockRequest> stockRequests)
    {
        for (SkStockRequest stockRequest : stockRequests)
        {
            String stockCode = stockRequest.getCode();
            String stockName = stockRequest.getName();
            String produceName = stockRequest.getProduceName();
            String produce = stockRequest.getProduce();
            // 变动数量
            BigDecimal changeNum = stockRequest.getChangeNum();
            // 操作类型
            String operation = stockRequest.getOperation();
            // 操作描述
            String operationName = stockRequest.getOperationName();
            if (StringUtils.isEmpty(stockCode) || StringUtils.isEmpty(produce))
            {
                return AjaxResult.error(ResultEnums.NULL_PARAM);
            }
            // 变动数量为0不操作数据库
            if (changeNum.compareTo(BigDecimal.ZERO) == 0)
            {
                continue;
            }
            // 查询库存表信息
            SkStock stock = new SkStock();
            stock.setCode(stockCode);
            stock.setProduce(produce);
            List<SkStock> list = selectSkStockList(stock);
            SkStock data = list.get(0);
            stock.setId(data.getId());
            stock.setName(stockName);
            stock.setProduceName(produceName);
            // 更新库存表-总数减少
            BigDecimal stockTotal = data.getStockTotal();
            // 可用数量增加
            BigDecimal stockAvailableNum = data.getStockAvailableNum();
            // 可用不够扣，只扣除可用数量
            if (stockAvailableNum.compareTo(changeNum) < 0)
            {
                stockTotal = stockTotal.subtract(stockAvailableNum);
                stockAvailableNum = BigDecimal.ZERO;
            }
            else
            {
                stockTotal = stockTotal.subtract(changeNum);
                stockAvailableNum = stockAvailableNum.subtract(changeNum);
            }
            stock.setStockTotal(stockTotal);
            stock.setStockAvailableNum(stockAvailableNum);
            updateSkStock(stock);

            // 更新批次表，减少
            SkStockBatch stockBatch = new SkStockBatch();
            stockBatch.setStockCode(stockCode);
            stockBatch.setProduce(produce);
            List<SkStockBatch> batches = skStockBatchService.selectSkStockBatchList(stockBatch);
            stockBatch.setSupplierCode(stockRequest.getSupplierCode());
            if (CollectionUtils.isEmpty(batches))
            {
                continue;
            }
            // 记录变动表
            SkStockChange stockChange = new SkStockChange();
            stockChange.setStockCode(stockCode);
            stockChange.setStockName(stockName);
            stockChange.setProduce(produce);
            stockChange.setProduceName(produceName);
            stockChange.setOperation(operation);
            stockChange.setOperationName(operationName);
            for (SkStockBatch batch : batches)
            {
                BigDecimal ableNum = batch.getStockAvailableNum();
                BigDecimal total = batch.getBatchTotal();
                stockChange.setBatchCode(batch.getBatchCode());
                if (changeNum.compareTo(ableNum) <= 0)
                {
                    // 可用够扣减
                    batch.setBatchTotal(total.subtract(changeNum));
                    batch.setStockAvailableNum(ableNum.subtract(changeNum));
                    skStockBatchService.updateSkStockBatch(batch);
                    stockChange.setChangeNum(changeNum);
                    skStockChangeService.insertSkStockChange(stockChange);
                    break;
                }
                else
                {
                    batch.setBatchTotal(total.subtract(ableNum));
                    batch.setStockAvailableNum(BigDecimal.ZERO);
                    changeNum = changeNum.subtract(ableNum);
                    skStockBatchService.updateSkStockBatch(batch);
                    stockChange.setChangeNum(ableNum);
                    skStockChangeService.insertSkStockChange(stockChange);
                }
            }
        }
        return AjaxResult.success();
    }

    /**
     * 库存调整-手工增加
     *
     * @param stockRequests 库存主
     * @return 结果
     */
    @Override
    public AjaxResult addManual(List<SkStockRequest> stockRequests)
    {
        for (SkStockRequest stockRequest : stockRequests)
        {
            // 批次编码
            String batchCode = stockRequest.getBatchCode();
            // 物料编码
            String produceCode = stockRequest.getProduce();
            // 供应商编码
            String supplierCode = stockRequest.getSupplierCode();
            // 变动数量
            BigDecimal changeNum = stockRequest.getChangeNum();
            // 操作类型
            String operation = stockRequest.getOperation();
            // 操作描述
            String operationNme = stockRequest.getOperationName();
            if (StringUtils.isEmpty(batchCode) || StringUtils.isEmpty(produceCode))
            {
                return AjaxResult.error(ResultEnums.NULL_PARAM);
            }
            // 变动数量为0不操作数据库
            if (changeNum.compareTo(BigDecimal.ZERO) == 0)
            {
                continue;
            }
            // 查询库存表信息
            SkStock stock = new SkStock();
            stock.setCode(stockRequest.getCode());
            stock.setProduce(produceCode);
            List<SkStock> list = selectSkStockList(stock);
            SkStock data = list.get(0);
            // 仓库编码
            String stockCode = data.getCode();
            // 仓库名称
            String stockName = data.getName();
            String produceName = data.getProduceName();
            // 库存总量增加
            BigDecimal stockTotal = data.getStockTotal().add(changeNum);
            // 可用数量增加
            BigDecimal stockAvailableNum = data.getStockAvailableNum().add(changeNum);
            stock.setId(data.getId());
            stock.setProduce(produceCode);
            stock.setStockTotal(stockTotal);
            stock.setStockAvailableNum(stockAvailableNum);
            // 更新库存表
            updateSkStock(stock);

            // 更新批次表
            SkStockBatch batch = new SkStockBatch();
            batch.setStockCode(stockCode);
            batch.setProduce(produceCode);
            List<SkStockBatch> batchList = skStockBatchService.selectSkStockBatchList(batch);
            batch.setStockName(stockName);
            batch.setProduceName(produceName);
            batch.setSupplierCode(supplierCode);
            batch.setBatchCode(batchCode);
            SkStockBatch batchData = batchList.get(0);
            // 批次总数量增加
            batch.setBatchTotal(batchData.getBatchTotal().add(changeNum));
            batch.setId(batchData.getId());
            // 批次可用数量增加
            batch.setStockAvailableNum(batchData.getStockAvailableNum().add(changeNum));
            skStockBatchService.updateSkStockBatch(batch);

            // 记录变动表
            SkStockChange stockChange = new SkStockChange();
            stockChange.setChangeNum(changeNum);
            stockChange.setBatchCode(batchCode);
            stockChange.setStockCode(stockCode);
            stockChange.setStockName(stockName);
            stockChange.setProduce(produceCode);
            stockChange.setProduceName(produceName);
            stockChange.setOperation(operation);
            stockChange.setOperationName(operationNme);
            skStockChangeService.insertSkStockChange(stockChange);
        }
        return AjaxResult.success();
    }

    /**
     * 库存调整-手工减少
     *
     * @param stockRequests
     * @return 结果
     */
    @Override
    public AjaxResult subtractManual(List<SkStockRequest> stockRequests)
    {
        for (SkStockRequest stockRequest : stockRequests)
        {
            // 批次编码
            String batchCode = stockRequest.getBatchCode();
            // 物料编码
            String produceCode = stockRequest.getProduce();
            // 供应商编码
            String supplierCode = stockRequest.getSupplierCode();
            // 变动数量
            BigDecimal changeNum = stockRequest.getChangeNum();
            // 操作类型
            String operation = stockRequest.getOperation();
            // 操作描述
            String operationNme = stockRequest.getOperationName();
            if (StringUtils.isEmpty(batchCode) || StringUtils.isEmpty(produceCode))
            {
                return AjaxResult.error(ResultEnums.NULL_PARAM);
            }
            // 变动数量为0不操作数据库
            if (changeNum.compareTo(BigDecimal.ZERO) == 0)
            {
                continue;
            }
            // 查询库存表信息
            SkStock stock = new SkStock();
            stock.setCode(stockRequest.getCode());
            stock.setProduce(produceCode);
            List<SkStock> list = selectSkStockList(stock);
            SkStock data = list.get(0);
            // 仓库编码
            String stockCode = data.getCode();
            // 仓库名称
            String stockName = data.getName();
            String produceName = data.getProduceName();
            // 库存总量減少
            BigDecimal stockTotal = data.getStockTotal().subtract(changeNum);
            stockTotal = stockTotal.compareTo(BigDecimal.ZERO) >= 0 ? stockTotal : BigDecimal.ZERO;
            // 可用数量減少
            BigDecimal stockAvailableNum = data.getStockAvailableNum().subtract(changeNum);
            stockAvailableNum = stockAvailableNum.compareTo(BigDecimal.ZERO) >= 0 ? stockAvailableNum : BigDecimal.ZERO;
            stock.setId(data.getId());
            stock.setProduce(produceCode);
            stock.setStockTotal(stockTotal);
            stock.setStockAvailableNum(stockAvailableNum);
            // 更新库存表
            updateSkStock(stock);

            // 更新批次表
            SkStockBatch batch = new SkStockBatch();
            batch.setStockCode(stockCode);
            batch.setProduce(produceCode);
            List<SkStockBatch> batchList = skStockBatchService.selectSkStockBatchList(batch);
            batch.setStockName(stockName);
            batch.setProduceName(produceName);
            batch.setSupplierCode(supplierCode);
            batch.setBatchCode(batchCode);
            SkStockBatch batchData = batchList.get(0);
            // 批次总数量減少
            batchData.setBatchTotal(BigDecimal.ZERO);
            if (batchData.getBatchTotal().compareTo(changeNum) > 0)
            {
                batch.setBatchTotal(batchData.getBatchTotal().subtract(changeNum));
            }
            batch.setId(batchData.getId());
            // 批次可用数量減少
            batch.setStockAvailableNum(BigDecimal.ZERO);
            if (batchData.getStockAvailableNum().compareTo(changeNum) > 0)
            {
                batch.setStockAvailableNum(batchData.getStockAvailableNum().subtract(changeNum));
            }
            skStockBatchService.updateSkStockBatch(batch);

            // 记录变动表
            SkStockChange stockChange = new SkStockChange();
            stockChange.setChangeNum(changeNum);
            stockChange.setBatchCode(batchCode);
            stockChange.setStockCode(stockCode);
            stockChange.setStockName(stockName);
            stockChange.setProduce(produceCode);
            stockChange.setProduceName(produceName);
            stockChange.setOperation(operation);
            stockChange.setOperationName(operationNme);
            skStockChangeService.insertSkStockChange(stockChange);
        }
        return AjaxResult.success();
    }

    /**
     * 库存调整-库存报损
     *
     * @param stockRequests
     * @return 结果
     */
    @Override
    public AjaxResult lossReportStock(List<SkStockRequest> stockRequests)
    {
        for (SkStockRequest stockRequest : stockRequests)
        {
            // 批次编码
            String batchCode = stockRequest.getBatchCode();
            // 物料编码
            String produceCode = stockRequest.getProduce();
            // 供应商编码
            String supplierCode = stockRequest.getSupplierCode();
            // 变动数量
            BigDecimal changeNum = stockRequest.getChangeNum();
            // 操作类型
            String operation = stockRequest.getOperation();
            // 操作描述
            String operationNme = stockRequest.getOperationName();
            if (StringUtils.isEmpty(batchCode) || StringUtils.isEmpty(produceCode))
            {
                return AjaxResult.error(ResultEnums.NULL_PARAM);
            }
            // 变动数量为0不操作数据库
            if (changeNum.compareTo(BigDecimal.ZERO) == 0)
            {
                continue;
            }
            // 查询库存表信息
            SkStock stock = new SkStock();
            stock.setCode(stockRequest.getCode());
            stock.setProduce(produceCode);
            List<SkStock> list = selectSkStockList(stock);
            SkStock data = list.get(0);
            // 仓库编码
            String stockCode = data.getCode();
            // 仓库名称
            String stockName = data.getName();
            String produceName = data.getProduceName();
            // 可用数量減少
            BigDecimal stockAvailableNum = data.getStockAvailableNum().subtract(changeNum);
            stockAvailableNum = stockAvailableNum.compareTo(BigDecimal.ZERO) >= 0 ? stockAvailableNum : BigDecimal.ZERO;
            // 库存报损数量增加
            BigDecimal stockFaultyNum = (
                    data.getStockFaultyNum() == null ? BigDecimal.ZERO : data.getStockFaultyNum()).add(changeNum);
            stock.setId(data.getId());
            stock.setProduce(produceCode);
            stock.setStockAvailableNum(stockAvailableNum);
            stock.setStockFaultyNum(stockFaultyNum);
            // 更新库存表
            updateSkStock(stock);

            // 更新批次表
            SkStockBatch batch = new SkStockBatch();
            batch.setStockCode(stockCode);
            batch.setProduce(produceCode);
            List<SkStockBatch> batchList = skStockBatchService.selectSkStockBatchList(batch);
            SkStockBatch batchData = batchList.get(0);
            batch.setStockName(stockName);
            batch.setProduceName(produceName);
            batch.setSupplierCode(supplierCode);
            batch.setBatchCode(batchCode);
            // 批次总数量不变
            // 批次库存报损数量增加
            batch.setStockFaultyNum(
                    (batchData.getStockFaultyNum() == null ? BigDecimal.ZERO : batchData.getStockFaultyNum()).add(
                            changeNum));
            batch.setId(batchData.getId());
            // 批次可用数量減少
            batch.setStockAvailableNum((batchData.getStockAvailableNum() ==
                                        null ? BigDecimal.ZERO : batchData.getStockAvailableNum()).subtract(changeNum));
            skStockBatchService.updateSkStockBatch(batch);

            // 记录变动表
            SkStockChange stockChange = new SkStockChange();
            stockChange.setChangeNum(changeNum);
            stockChange.setBatchCode(batchCode);
            stockChange.setStockCode(stockCode);
            stockChange.setStockName(stockName);
            stockChange.setProduce(produceCode);
            stockChange.setProduceName(produceName);
            stockChange.setOperation(operation);
            stockChange.setOperationName(operationNme);
            skStockChangeService.insertSkStockChange(stockChange);
        }
        return AjaxResult.success();
    }

    /**
     * 盘点调整-增加
     *
     * @param stockRequests
     * @return 结果
     */
    @Override
    public AjaxResult addInventory(List<SkStockRequest> stockRequests)
    {
        for (SkStockRequest stockRequest : stockRequests)
        {
            // 物料编码
            String produceCode = stockRequest.getProduce();
            // 变动数量
            BigDecimal changeNum = stockRequest.getChangeNum();
            // 操作类型
            String operation = stockRequest.getOperation();
            // 操作描述
            String operationNme = stockRequest.getOperationName();
            if (StringUtils.isEmpty(produceCode))
            {
                return AjaxResult.error(ResultEnums.NULL_PARAM);
            }
            // 变动数量为0不操作数据库
            if (changeNum.compareTo(BigDecimal.ZERO) == 0)
            {
                continue;
            }
            // 查询库存表信息
            SkStock stock = new SkStock();
            stock.setProduce(produceCode);
            List<SkStock> list = selectSkStockList(stock);
            SkStock data = list.get(0);
            // 仓库编码
            String stockCode = data.getCode();
            // 仓库名称
            String stockName = data.getName();
            String produceName = data.getProduceName();
            // 库存总量增加
            BigDecimal stockTotal = data.getStockTotal().add(changeNum);
            // 可用数量增加
            BigDecimal stockAvailableNum = data.getStockAvailableNum().add(changeNum);
            stock.setId(data.getId());
            stock.setProduce(produceCode);
            stock.setStockTotal(stockTotal);
            stock.setStockAvailableNum(stockAvailableNum);
            // 更新库存表
            updateSkStock(stock);

            // 更新批次表
            SkStockBatch batch = new SkStockBatch();
            batch.setStockCode(stockCode);
            batch.setProduce(produceCode);
            List<SkStockBatch> batchList = skStockBatchService.selectSkStockBatchList(batch);
            batch.setStockName(stockName);
            batch.setProduceName(produceName);
            // 盘点增加至该物料的最新一个批次
            SkStockBatch stockBatch = batchList.get(batchList.size() - 1);
            // 批次庫存總量增加
            BigDecimal batchTotal = stockBatch.getBatchTotal().add(changeNum);
            // 可用增加
            BigDecimal availableNum = stockBatch.getStockAvailableNum().add(changeNum);
            stockBatch.setStockAvailableNum(availableNum);
            stockBatch.setBatchTotal(batchTotal);
            // 更新批次表
            skStockBatchService.updateSkStockBatch(stockBatch);
            // 记录变动表
            SkStockChange stockChange = new SkStockChange();
            stockChange.setChangeNum(changeNum);
            stockChange.setBatchCode(stockBatch.getBatchCode());
            stockChange.setStockCode(stockCode);
            stockChange.setStockName(stockName);
            stockChange.setProduce(produceCode);
            stockChange.setProduceName(produceName);
            stockChange.setOperation(operation);
            stockChange.setOperationName(operationNme);
            skStockChangeService.insertSkStockChange(stockChange);
        }
        return AjaxResult.success();
    }

    /**
     * 盘点调整-减少
     *
     * @param stockRequests
     * @return 结果
     */
    @Override
    public AjaxResult subtractInventory(List<SkStockRequest> stockRequests)
    {
        for (SkStockRequest stockRequest : stockRequests)
        {
            // 物料编码
            String produceCode = stockRequest.getProduce();
            // 变动数量
            BigDecimal changeNum = stockRequest.getChangeNum();
            // 操作类型
            String operation = stockRequest.getOperation();
            // 操作描述
            String operationNme = stockRequest.getOperationName();
            if (StringUtils.isEmpty(produceCode))
            {
                return AjaxResult.error(ResultEnums.NULL_PARAM);
            }
            // 变动数量为0不操作数据库
            if (changeNum.compareTo(BigDecimal.ZERO) == 0)
            {
                continue;
            }
            // 查询库存表信息
            SkStock stock = new SkStock();
            stock.setProduce(produceCode);
            List<SkStock> list = selectSkStockList(stock);
            SkStock data = list.get(0);
            // 仓库编码
            String stockCode = data.getCode();
            // 仓库名称
            String stockName = data.getName();
            String produceName = data.getProduceName();
            // 库存总量减少
            BigDecimal stockTotal = data.getStockTotal().subtract(changeNum);
            stockTotal = stockTotal.compareTo(BigDecimal.ZERO) >= 0 ? stockTotal : BigDecimal.ZERO;
            // 可用数量减少
            BigDecimal stockAvailableNum = data.getStockAvailableNum().subtract(changeNum);
            stockAvailableNum = stockAvailableNum.compareTo(BigDecimal.ZERO) >= 0 ? stockAvailableNum : BigDecimal.ZERO;
            stock.setId(data.getId());
            stock.setProduce(produceCode);
            stock.setStockTotal(stockTotal);
            stock.setStockAvailableNum(stockAvailableNum);
            // 更新库存表
            updateSkStock(stock);

            // 更新批次表
            SkStockBatch batch = new SkStockBatch();
            batch.setStockCode(stockCode);
            batch.setProduce(produceCode);
            List<SkStockBatch> batchList = skStockBatchService.selectSkStockBatchList(batch);
            batch.setStockName(stockName);
            batch.setProduceName(produceName);
            for (SkStockBatch stockBatch : batchList)
            {
                BigDecimal availableNum = stockBatch.getStockAvailableNum();
                if (availableNum.compareTo(BigDecimal.ZERO) <= 0)
                {
                    // 该批次无可用数量不操作，继续执行下一个批次
                }
                else if (availableNum.compareTo(changeNum) < 0)
                {
                    // 该批次可用数量不够扣减，继续执行下一个批次
                    changeNum = changeNum.subtract(availableNum);
                    changeNum = changeNum.compareTo(BigDecimal.ZERO) >= 0 ? changeNum : BigDecimal.ZERO;
                    // 可用数量为0
                    stockBatch.setStockAvailableNum(BigDecimal.ZERO);
                    // 库存总量剩余=库存总量-可用数量
                    stockBatch.setBatchTotal(stockBatch.getBatchTotal().subtract(availableNum));
                    // 更新批次表
                    skStockBatchService.updateSkStockBatch(stockBatch);
                    // 记录变动表
                    SkStockChange stockChange = new SkStockChange();
                    stockChange.setChangeNum(changeNum);
                    stockChange.setBatchCode(stockBatch.getBatchCode());
                    stockChange.setStockCode(stockCode);
                    stockChange.setStockName(stockName);
                    stockChange.setProduce(produceCode);
                    stockChange.setProduceName(produceName);
                    stockChange.setOperation(operation);
                    stockChange.setOperationName(operationNme);
                    skStockChangeService.insertSkStockChange(stockChange);
                }
                else
                {
                    // 该批次可用数量够扣减，不继续执行下一个批次
                    availableNum = availableNum.subtract(changeNum);
                    availableNum = availableNum.compareTo(BigDecimal.ZERO) >= 0 ? availableNum : BigDecimal.ZERO;
                    // 可用数量
                    stockBatch.setStockAvailableNum(availableNum);
                    // 库存总量
                    stockBatch.setBatchTotal(stockBatch.getBatchTotal().subtract(changeNum));
                    // 更新批次表
                    skStockBatchService.updateSkStockBatch(stockBatch);
                    // 记录变动表
                    SkStockChange stockChange = new SkStockChange();
                    stockChange.setChangeNum(changeNum);
                    stockChange.setBatchCode(stockBatch.getBatchCode());
                    stockChange.setStockCode(stockCode);
                    stockChange.setStockName(stockName);
                    stockChange.setProduce(produceCode);
                    stockChange.setProduceName(produceName);
                    stockChange.setOperation(operation);
                    stockChange.setOperationName(operationNme);
                    skStockChangeService.insertSkStockChange(stockChange);
                    break;
                }
            }
        }
        return AjaxResult.success();
    }

    /**
     * 修改库存主
     *
     * @param skStock 库存主
     * @return 结果
     */
    @Override
    public int updateSkStock(SkStock skStock)
    {
        skStock.setUpdateTime(DateUtils.getNowDate());
        return skStockMapper.updateSkStock(skStock);
    }

    /**
     * 批量删除库存主
     *
     * @param ids 需要删除的库存主ID
     * @return 结果
     */
    @Override
    public int deleteSkStockByIds(Long[] ids)
    {
        return skStockMapper.deleteSkStockByIds(ids);
    }

    /**
     * 删除库存主信息
     *
     * @param id 库存主ID
     * @return 结果
     */
    @Override
    public int deleteSkStockById(Long id)
    {
        return skStockMapper.deleteSkStockById(id);
    }

    public SkStock getSkStock(String materialCode)
    {
        if (org.apache.commons.lang3.StringUtils.isEmpty(materialCode))
        {
            throw new IllegalArgumentException("need produce code.");
        }
        SkStock params = new SkStock();
        params.setProduce(materialCode);
        List<SkStock> stocks = skStockMapper.selectSkStockList(params);
        return CollectionUtils.isEmpty(stocks) ? null : stocks.get(0);
    }

    @Override
    public List<SkStock> batchQueryByProduce(List<String> produceList)
    {
        if (CollectionUtils.isEmpty(produceList))
        {
            return null;
        }
        List<SkStock> stocks = new ArrayList<>();
        for (String produce : produceList)
        {
            SkStock stock = skStockMapper.summary(null, produce);
            if (stock != null && stock.getProduce() != null)
            {
                stocks.add(stock);
            }
        }
        return stocks;
    }

    @Override
    public void addStockTransitNum(SkStock param)
    {
        SkStock stock = getStock(param.getCode(), param.getProduce());
        if (stock == null)
        {
            param.setCreateBy(param.getUpdateBy());
            param.setCreateTime(new Date());
            skStockMapper.insertSkStock(param);
            return;
        }
        param.setId(stock.getId());
        param.setUpdateTime(new Date());
        skStockMapper.addStockTransitNum(param);
    }

//    @Override
//    public void confirmStockTransitNum(SkStockBatch stockBatch, BigDecimal stockTransitNum)
//    {
//        SkStock stock = loadStock(stockBatch.getStockCode(), stockBatch.getProduce());
//
//        //扣减在途
//        SkStock param = new SkStock();
//        param.setId(stock.getId());
//        param.setStockTransitNum(-stockTransitNum.intValue());
//        param.setUpdateBy(stockBatch.getCreateBy());
//        param.setUpdateTime(new Date());
//        skStockMapper.addStockTransitNum(param);
//
//        //新增库存及批次
//        stockBatch.setStockName(stock.getName());
//        stockBatch.setProduceName(stock.getProduceName());
//        stockBatch.setOpeningStockNum(stock.getStockTotal());
//        stockBatch.setBatchTotal(stockTransitNum.intValue());
//        stockBatch.setStockAvailableNum(stockTransitNum.intValue());
//        addStock(stockBatch);
//    }

    @Override
    public void addStock(SkStockBatch stockBatch)
    {
        log.error("Add stock:{}", JSON.toJSONString(stockBatch));

        if (stockBatch.getBatchTotal().compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new BaseException("库存总数量必须大于零。");
        }
        stockBatch.setOpeningStockNum(stockBatch.getBatchTotal());
        stockBatch.setStockAvailableNum(stockBatch.getStockAvailableNum());

        SkStock param = new SkStock();
        param.setCode(stockBatch.getStockCode());
        param.setProduce(stockBatch.getProduce());
        param.setStockTotal(stockBatch.getBatchTotal());
        param.setStockAvailableNum(stockBatch.getStockAvailableNum());
        param.setStockFaultyNum(stockBatch.getStockFaultyNum());
        param.setUpdateTime(new Date());
        param.setUpdateBy(stockBatch.getCreateBy());
        if (skStockMapper.addStock(param) <= 0)
        {
            param.setName(stockBatch.getStockName());
            param.setProduceName(stockBatch.getProduceName());
            param.setCreateBy(stockBatch.getCreateBy());
            param.setCreateTime(new Date());
            skStockMapper.insertSkStock(param);
        }

        String batchPrefix = DateUtils.parseDateToStr(DateUtils.YYYYMM, new Date());
        String maxCode = skStockBatchService.getMaxBatchCode(batchPrefix);
        stockBatch.setBatchCode(CodeUtils.getOneCode(batchPrefix, maxCode, 1));
        stockBatch.setBatchTime(new Date());
        stockBatch.setCreateTime(new Date());
        skStockBatchMapper.insertSkStockBatch(stockBatch);

        //新建变更
        addStockChange(stockBatch, stockBatch.getStockAvailableNum(), "新增库存", stockBatch.getCreateBy());
    }

//    @Override
//    public void takeStock(String code, String produce, BigDecimal num, String operator)
//    {
//        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(produce))
//        {
//            throw new IllegalArgumentException("need stock code and produce");
//        }
//        List<SkStockBatch> skStockBatchList = skStockBatchMapper.selectAvailableBatch(code, produce);
//        if (CollectionUtils.isEmpty(skStockBatchList))
//        {
//            throw new BaseException("可用库存不足，扣减失败。");
//        }
//
//        Map<Long, Integer> takingNums = new HashMap<>();
//        Map<Long, SkStockBatch> batchMap = new HashMap<>();
//        int inventoryNum = num.intValue();
//        for (SkStockBatch batch : skStockBatchList)
//        {
//            batchMap.put(batch.getId(), batch);
//            if (inventoryNum <= 0)
//            {
//                break;
//            }
//            if (batch.getStockAvailableNum() >= inventoryNum)
//            {
//                takingNums.put(batch.getId(), inventoryNum);
//                inventoryNum = 0;
//                continue;
//            }
//            takingNums.put(batch.getId(), batch.getStockAvailableNum());
//            inventoryNum -= batch.getStockAvailableNum();
//        }
//
//        //未分配完
//        if (inventoryNum > 0)
//        {
//            throw new BaseException("可用库存不足，扣减失败。");
//        }
//
//        //扣除库存可用额度
//        SkStock param0 = new SkStock();
//        param0.setCode(code);
//        param0.setProduce(produce);
//        param0.setStockAvailableNum(num.intValue());
//        param0.setUpdateBy(operator);
//        param0.setUpdateTime(new Date());
//        skStockMapper.removeAvailableNum(param0);
//        //扣除库存批次可用额度
//        for (Map.Entry<Long, Integer> entry : takingNums.entrySet())
//        {
//            SkStockBatch param = new SkStockBatch();
//            param.setId(entry.getKey());
//            param.setStockAvailableNum(entry.getValue());
//            param.setUpdateBy(operator);
//            param.setUpdateTime(new Date());
//            skStockBatchMapper.removeAvailableNum(param);
//
//            //库存变更
//            addStockChange(batchMap.get(entry.getKey()), new BigDecimal(entry.getValue()), "扣减", operator);
//        }
//    }

//    @Override
//    public void lockStock(String produce, BigDecimal num, String operator)
//    {
//        if (StringUtils.isEmpty(produce))
//        {
//            throw new IllegalArgumentException("need produce");
//        }
//        List<SkStockBatch> batchList = skStockBatchMapper.selectAvailableBatch(null, produce);
//        if (CollectionUtils.isEmpty(batchList))
//        {
//            throw new BaseException("无库存批次记录。");
//        }
//
//        Map<Long, Integer> lockedNumMap = new HashMap<>();
//        Map<String, Integer> stockLockedNumMap = new HashMap<>();
//        int inventoryNum = num.intValue();
//        for (SkStockBatch batch : batchList)
//        {
//            stockLockedNumMap.put(batch.getStockCode(), 0);
//        }
//        for (SkStockBatch batch : batchList)
//        {
//            if (inventoryNum <= 0)
//            {
//                break;
//            }
//            int availableNum = batch.getStockAvailableNum() - batch.getStockLockNum();
//            if (availableNum >= inventoryNum)
//            {
//                lockedNumMap.put(batch.getId(), inventoryNum);
//                stockLockedNumMap.put(batch.getStockCode(), stockLockedNumMap.get(batch.getStockCode()) + inventoryNum);
//                inventoryNum = 0;
//                continue;
//            }
//            lockedNumMap.put(batch.getId(), availableNum);
//            stockLockedNumMap.put(batch.getStockCode(), stockLockedNumMap.get(batch.getStockCode()) + availableNum);
//            inventoryNum -= availableNum;
//        }
//
//        //未分配完
//        if (inventoryNum > 0)
//        {
//            throw new BaseException("可用库存不足，锁定库存失败。");
//        }
//
//        for (Map.Entry<Long, Integer> entry : lockedNumMap.entrySet())
//        {
//            SkStockBatch param = new SkStockBatch();
//            param.setId(entry.getKey());
//            param.setStockLockNum(entry.getValue());
//            param.setUpdateBy(operator);
//            param.setUpdateTime(new Date());
//            if (skStockBatchMapper.addStockLockNum(param) <= 0)
//            {
//                throw new BaseException("锁定库存失败。");
//            }
//        }
//        for (Map.Entry<String, Integer> entry : stockLockedNumMap.entrySet())
//        {
//            if (entry.getValue() <= 0)
//            {
//                continue;
//            }
//            SkStock param0 = new SkStock();
//            param0.setCode(entry.getKey());
//            param0.setProduce(produce);
//            param0.setStockLockNum(entry.getValue());
//            param0.setUpdateBy(operator);
//            param0.setUpdateTime(new Date());
//            skStockMapper.addStockLockNum(param0);
//        }
//    }

//    @Override
//    public void unlockStock(String produce, BigDecimal num, String operator)
//    {
//        if (StringUtils.isEmpty(produce))
//        {
//            throw new IllegalArgumentException("need produce");
//        }
//        List<SkStockBatch> batchList = skStockBatchMapper.selectAvailableBatch(null, produce);
//        if (CollectionUtils.isEmpty(batchList))
//        {
//            throw new BaseException("无库存批次记录。");
//        }
//        Collections.reverse(batchList);
//
//        Map<Long, Integer> unlockedNumMap = new HashMap<>();
//        Map<String, Integer> stockUnlockedNumMap = new HashMap<>();
//        int inventoryNum = num.intValue();
//        for (SkStockBatch batch : batchList)
//        {
//            stockUnlockedNumMap.put(batch.getStockCode(), 0);
//        }
//        for (SkStockBatch batch : batchList)
//        {
//            if (inventoryNum <= 0)
//            {
//                break;
//            }
//            if (batch.getStockLockNum() >= inventoryNum)
//            {
//                unlockedNumMap.put(batch.getId(), inventoryNum);
//                stockUnlockedNumMap.put(batch.getStockCode(),
//                        stockUnlockedNumMap.get(batch.getStockCode()) + inventoryNum);
//                inventoryNum = 0;
//                continue;
//            }
//            unlockedNumMap.put(batch.getId(), batch.getStockLockNum());
//            stockUnlockedNumMap.put(batch.getStockCode(),
//                    stockUnlockedNumMap.get(batch.getStockCode()) + batch.getStockLockNum());
//            inventoryNum -= batch.getStockLockNum();
//        }
//
//        for (Map.Entry<Long, Integer> entry : unlockedNumMap.entrySet())
//        {
//            SkStockBatch param = new SkStockBatch();
//            param.setId(entry.getKey());
//            param.setStockLockNum(entry.getValue());
//            param.setUpdateBy(operator);
//            param.setUpdateTime(new Date());
//            skStockBatchMapper.removeStockLockNum(param);
//        }
//        for (Map.Entry<String, Integer> entry : stockUnlockedNumMap.entrySet())
//        {
//            if (entry.getValue() <= 0)
//            {
//                continue;
//            }
//            SkStock param0 = new SkStock();
//            param0.setCode(entry.getKey());
//            param0.setProduce(produce);
//            param0.setStockLockNum(entry.getValue());
//            param0.setUpdateBy(operator);
//            param0.setUpdateTime(new Date());
//            skStockMapper.removeStockLockNum(param0);
//        }
//    }

//    @Override
//    public void confirmLockedStock(String produce, BigDecimal num, String operator)
//    {
//        if (StringUtils.isEmpty(produce))
//        {
//            throw new IllegalArgumentException("need produce");
//        }
//        List<SkStockBatch> batchList = skStockBatchMapper.selectAvailableBatch(null, produce);
//        if (CollectionUtils.isEmpty(batchList))
//        {
//            throw new BaseException("无库存批次记录。");
//        }
//
//        Map<Long, Integer> usedNumMap = new HashMap<>();
//        Map<Long, SkStockBatch> batchMap = new HashMap<>();
//        Map<String, Integer> stockUsedNumMap = new HashMap<>();
//        int inventoryNum = num.intValue();
//        for (SkStockBatch batch : batchList)
//        {
//            batchMap.put(batch.getId(), batch);
//            stockUsedNumMap.put(batch.getStockCode(), 0);
//        }
//        for (SkStockBatch batch : batchList)
//        {
//            if (inventoryNum <= 0)
//            {
//                break;
//            }
//            if (batch.getStockLockNum() >= inventoryNum)
//            {
//                usedNumMap.put(batch.getId(), inventoryNum);
//                stockUsedNumMap.put(batch.getStockCode(), stockUsedNumMap.get(batch.getStockCode()) + inventoryNum);
//                inventoryNum = 0;
//                continue;
//            }
//            usedNumMap.put(batch.getId(), batch.getStockLockNum());
//            stockUsedNumMap.put(batch.getStockCode(),
//                    stockUsedNumMap.get(batch.getStockCode()) + batch.getStockLockNum());
//            inventoryNum -= batch.getStockLockNum();
//        }
//
//        //未分配完
//        if (inventoryNum > 0)
//        {
//            throw new BaseException("可用库存不足，扣除库存失败。");
//        }
//
//        for (Map.Entry<Long, Integer> entry : usedNumMap.entrySet())
//        {
//            SkStockBatch param = new SkStockBatch();
//            param.setId(entry.getKey());
//            param.setStockLockNum(entry.getValue());
//            param.setStockAvailableNum(entry.getValue());
//            param.setUpdateBy(operator);
//            param.setUpdateTime(new Date());
//            if (skStockBatchMapper.removeAvailableNum(param) <= 0)
//            {
//                throw new BaseException("扣除库存失败。");
//            }
//
//            addStockChange(batchMap.get(entry.getKey()), new BigDecimal(entry.getValue()), "确认扣减锁定库存", operator);
//        }
//        for (Map.Entry<String, Integer> entry : stockUsedNumMap.entrySet())
//        {
//            if (entry.getValue() <= 0)
//            {
//                continue;
//            }
//            SkStock param0 = new SkStock();
//            param0.setCode(entry.getKey());
//            param0.setProduce(produce);
//            param0.setStockLockNum(entry.getValue());
//            param0.setStockAvailableNum(entry.getValue());
//            param0.setUpdateBy(operator);
//            param0.setUpdateTime(new Date());
//            skStockMapper.removeAvailableNum(param0);
//        }
//    }

    @Override
    public void lockStock(String code, String produce, BigDecimal num, String operator)
    {
        log.error("Try lock stock,code:{},produce:{},num:{},operator:{}", code, produce, num, operator);
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(produce))
        {
            log.error("Try lock stock failure,code:{},produce:{},num:{},operator:{}", code, produce, num, operator);
            throw new IllegalArgumentException("need code and produce");
        }

        if (num.compareTo(BigDecimal.ZERO) <= 0)
        {
            return;
        }

        List<SkStockBatch> batchList = skStockBatchMapper.selectAvailableBatch(code, produce);
        if (CollectionUtils.isEmpty(batchList))
        {
            log.error("Try lock stock failure,code:{},produce:{},num:{},operator:{}", code, produce, num, operator);
            throw new BaseException("库存不足，锁定库存失败。");
        }

        Map<Long, BigDecimal> lockedNumMap = new HashMap<>();
        BigDecimal inventoryNum = num;
        for (SkStockBatch batch : batchList)
        {
            if (inventoryNum.compareTo(BigDecimal.ZERO) <= 0)
            {
                break;
            }
            if (batch.getStockAvailableNum().compareTo(inventoryNum) >= 0)
            {
                lockedNumMap.put(batch.getId(), inventoryNum);
                inventoryNum = BigDecimal.ZERO;
                continue;
            }
            lockedNumMap.put(batch.getId(), batch.getStockAvailableNum());
            inventoryNum = inventoryNum.subtract(batch.getStockAvailableNum());
        }

        //未分配完
        if (inventoryNum.compareTo(BigDecimal.ZERO) > 0)
        {
            log.error("Try lock stock failure,code:{},produce:{},num:{},operator:{}", code, produce, num, operator);
            throw new BaseException("可用库存不足，锁定库存失败。");
        }

        for (Map.Entry<Long, BigDecimal> entry : lockedNumMap.entrySet())
        {
            SkStockBatch param = new SkStockBatch();
            param.setId(entry.getKey());
            param.setStockLockNum(entry.getValue());
            param.setUpdateBy(operator);
            param.setUpdateTime(new Date());
            if (skStockBatchMapper.addStockLockNum(param) <= 0)
            {
                log.error("Try lock stock failure,code:{},produce:{},num:{},operator:{}", code, produce, num, operator);
                throw new BaseException("锁定库存失败。");
            }
        }
        SkStock param0 = new SkStock();
        param0.setCode(code);
        param0.setProduce(produce);
        param0.setStockLockNum(num);
        param0.setUpdateBy(operator);
        param0.setUpdateTime(new Date());
        skStockMapper.addStockLockNum(param0);
    }

    @Override
    public void unlockStock(String code, String produce, BigDecimal num, String operator)
    {
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(produce))
        {
            log.error("Try unlock stock failure,code:{},produce:{},num:{},operator:{}", code, produce, num, operator);
            throw new IllegalArgumentException("need code and produce");
        }

        if (num.compareTo(BigDecimal.ZERO) <= 0)
        {
            return;
        }

        List<SkStockBatch> batchList = skStockBatchMapper.selectLockedBatch(code, produce);
        if (CollectionUtils.isEmpty(batchList))
        {
            return;
        }
        Collections.reverse(batchList);

        Map<Long, BigDecimal> unlockedNumMap = new HashMap<>();
        BigDecimal inventoryNum = num;
        for (SkStockBatch batch : batchList)
        {
            if (inventoryNum.compareTo(BigDecimal.ZERO) <= 0)
            {
                break;
            }
            if (batch.getStockLockNum().compareTo(inventoryNum) >= 0)
            {
                unlockedNumMap.put(batch.getId(), inventoryNum);
                inventoryNum = BigDecimal.ZERO;
                continue;
            }
            unlockedNumMap.put(batch.getId(), batch.getStockLockNum());
            inventoryNum = inventoryNum.subtract(batch.getStockLockNum());
        }

        for (Map.Entry<Long, BigDecimal> entry : unlockedNumMap.entrySet())
        {
            SkStockBatch param = new SkStockBatch();
            param.setId(entry.getKey());
            param.setStockLockNum(entry.getValue());
            param.setUpdateBy(operator);
            param.setUpdateTime(new Date());
            skStockBatchMapper.removeStockLockNum(param);
        }
        SkStock param0 = new SkStock();
        param0.setCode(code);
        param0.setProduce(produce);
        param0.setStockLockNum(num);
        param0.setUpdateBy(operator);
        param0.setUpdateTime(new Date());
        skStockMapper.removeStockLockNum(param0);
    }

    @Override
    public void confirmLockedStock(String code, String produce, BigDecimal num, String operator)
    {
        log.error("Try confirm locked stock,code:{},produce:{},num:{},operator:{}", code, produce, num, operator);
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(produce))
        {
            log.error("Try confirm locked stock failure,code:{},produce:{},num:{},operator:{}", code, produce, num,
                    operator);
            throw new IllegalArgumentException("need code and produce");
        }

        if (num.compareTo(BigDecimal.ZERO) <= 0)
        {
            return;
        }

        List<SkStockBatch> batchList = skStockBatchMapper.selectLockedBatch(code, produce);
        if (CollectionUtils.isEmpty(batchList))
        {
            log.error("Try confirm locked stock failure,code:{},produce:{},num:{},operator:{}", code, produce, num,
                    operator);
            throw new BaseException("无库存批次记录。");
        }

        Map<Long, BigDecimal> usedNumMap = new HashMap<>();
        Map<Long, SkStockBatch> batchMap = new HashMap<>();
        BigDecimal inventoryNum = num;
        for (SkStockBatch batch : batchList)
        {
            batchMap.put(batch.getId(), batch);
        }
        for (SkStockBatch batch : batchList)
        {
            if (inventoryNum.compareTo(BigDecimal.ZERO) <= 0)
            {
                break;
            }
            if (batch.getStockLockNum().compareTo(inventoryNum) >= 0)
            {
                usedNumMap.put(batch.getId(), inventoryNum);
                inventoryNum = BigDecimal.ZERO;
                continue;
            }
            usedNumMap.put(batch.getId(), batch.getStockLockNum());
            inventoryNum = inventoryNum.subtract(batch.getStockLockNum());
        }

        //未分配完
        if (inventoryNum.compareTo(BigDecimal.ZERO) > 0)
        {
            log.error("Try confirm locked stock failure,code:{},produce:{},num:{},operator:{}", code, produce, num,
                    operator);
            throw new BaseException("可用库存不足，扣除库存失败。");
        }

        for (Map.Entry<Long, BigDecimal> entry : usedNumMap.entrySet())
        {
            SkStockBatch param = new SkStockBatch();
            param.setId(entry.getKey());
            param.setStockAvailableNum(entry.getValue());
            param.setUpdateBy(operator);
            param.setUpdateTime(new Date());
            if (skStockBatchMapper.removeAvailableNum(param) <= 0)
            {
                log.error("Try confirm locked stock failure,code:{},produce:{},num:{},operator:{}", code, produce, num,
                        operator);
                throw new BaseException("扣除库存失败。");
            }

            addStockChange(batchMap.get(entry.getKey()), entry.getValue(), "确认扣减锁定库存", operator);
        }
        SkStock param0 = new SkStock();
        param0.setCode(code);
        param0.setProduce(produce);
        param0.setStockAvailableNum(num);
        param0.setUpdateBy(operator);
        param0.setUpdateTime(new Date());
        skStockMapper.removeAvailableNum(param0);
    }

    @Override
    public void removeStockTransitNum(String code, String produce, BigDecimal num, String operator)
    {
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(produce))
        {
            throw new IllegalArgumentException("need code and produce");
        }
        if (num.compareTo(BigDecimal.ZERO) <= 0)
        {
            return;
        }
        SkStock param = new SkStock();
        param.setCode(code);
        param.setProduce(produce);
        param.setStockTransitNum(num);
        param.setUpdateBy(operator);
        param.setUpdateTime(new Date());
        skStockMapper.removeStockTransitNum(param);
    }

    private SkStock getStock(String code, String produce)
    {
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(produce))
        {
            throw new IllegalArgumentException("need stock code and produce");
        }
        SkStock param = new SkStock();
        param.setCode(code);
        param.setProduce(produce);
        List<SkStock> stockList = skStockMapper.selectSkStockList(param);
        return CollectionUtils.isEmpty(stockList) ? null : stockList.get(0);
    }

    private void addStockChange(SkStockBatch stockBatch, BigDecimal num, String operation, String operator)
    {
        SkStockChange skStockChange = new SkStockChange();
        skStockChange.setStockCode(stockBatch.getStockCode());
        skStockChange.setStockName(stockBatch.getStockName());
        skStockChange.setBatchCode(stockBatch.getBatchCode());
        skStockChange.setProduce(stockBatch.getProduce());
        skStockChange.setProduceName(stockBatch.getProduceName());
        skStockChange.setChangeNum(num);
        skStockChange.setChangeTime(new Date());
        skStockChange.setOperationName(operation);
        skStockChange.setCreateBy(operator);
        skStockChange.setCreateTime(new Date());
        skStockChangeMapper.insertSkStockChange(skStockChange);
    }
}
