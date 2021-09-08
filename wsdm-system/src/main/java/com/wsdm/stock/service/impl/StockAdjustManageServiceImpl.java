package com.wsdm.stock.service.impl;

import com.google.common.collect.Lists;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.ResultEnums;
import com.wsdm.common.enums.StockAdjustStatus;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.StringUtils;
import com.wsdm.stock.domain.SkStockRequest;
import com.wsdm.stock.domain.StockAdjustDetail;
import com.wsdm.stock.domain.StockAdjustInfo;
import com.wsdm.stock.domain.StockAdjustInfoVo;
import com.wsdm.stock.mapper.StockAdjustDetailMapper;
import com.wsdm.stock.mapper.StockAdjustInfoMapper;
import com.wsdm.stock.service.IPdProduceHardwareSupport;
import com.wsdm.stock.service.ISkStockService;
import com.wsdm.stock.service.IStockAdjustManageService;
import com.wsdm.utils.CodeUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 库存调整信息Service业务层处理
 *
 * @author wsdm
 * @date 2021-01-20
 */
@Service
public class StockAdjustManageServiceImpl implements IStockAdjustManageService
{
    @Autowired
    private StockAdjustInfoMapper   stockAdjustInfoMapper;
    @Autowired
    private StockAdjustDetailMapper stockAdjustDetailMapper;
    @Autowired
    private ISkStockService         skStockService;
    @Autowired
    private IPdProduceHardwareSupport pdProduceHardwareSupport;

    /**
     * 查询库存调整信息
     *
     * @param id 库存调整信息ID
     * @return 库存调整信息
     */
    @Override
    public StockAdjustInfoVo selectStockAdjustInfoById(Long id)
    {
        StockAdjustInfoVo stockAdjustInfoVo = stockAdjustInfoMapper.selectStockAdjustInfoById(id);
        if (stockAdjustInfoVo == null || StringUtils.isEmpty(stockAdjustInfoVo.getAdjustNumber()))
        {
            return stockAdjustInfoVo;
        }
        // 根据调整单号查询库存调整详情
        List<StockAdjustDetail> stockAdjustDetails = stockAdjustDetailMapper.selectStockAdjustDetailByAdjustNumber(
                stockAdjustInfoVo.getAdjustNumber());
        if(CollectionUtils.isNotEmpty(stockAdjustDetails)){
            for (StockAdjustDetail detail : stockAdjustDetails) {
                detail.setModel(pdProduceHardwareSupport.getModel(detail.getMaterialId()));
            }
        }
        stockAdjustInfoVo.setStockAdjustDetails(stockAdjustDetails);
        return stockAdjustInfoVo;
    }

    /**
     * 查询库存调整信息列表
     *
     * @param stockAdjustInfo 库存调整信息
     * @return 库存调整信息
     */
    @Override
    public List<StockAdjustInfo> selectStockAdjustInfoList(StockAdjustInfo stockAdjustInfo)
    {
        return stockAdjustInfoMapper.selectStockAdjustInfoList(stockAdjustInfo);
    }

    /**
     * 新增库存调整信息
     *
     * @param stockAdjustInfo 库存调整信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult insertStockAdjustInfo(StockAdjustInfoVo stockAdjustInfo)
    {
        List<StockAdjustDetail> stockAdjustDetails = stockAdjustInfo.getStockAdjustDetails();
        if (CollectionUtils.isEmpty(stockAdjustDetails))
        {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        String adjustPrefix = "T" + DateUtils.parseDateToStr(DateUtils.YYYYMM, new Date());
        String maxCode = stockAdjustInfoMapper.getMaxAdjustCode(adjustPrefix);
        String optChannel = stockAdjustInfo.getOptChannel();
        stockAdjustInfo.setCreateTime(DateUtils.getNowDate());
        stockAdjustInfo.setCreateBy(SecurityUtils.getUsername());
        // 操作渠道 optChannel 1-新增保存 2-新增保存审核 3-修改保存 4-修改保存审核
        stockAdjustInfo.setStatus(
                "2".equals(optChannel) ? StockAdjustStatus.AUDITED.getCode() : StockAdjustStatus.TO_SUBMIT.getCode());
        //补充审批时间
        if (StockAdjustStatus.AUDITED.getCode().equals(stockAdjustInfo.getStatus()))
        {
            stockAdjustInfo.setAuditBy(SecurityUtils.getUsername());
            stockAdjustInfo.setAuditTime(new Date());
        }
        // 编码
        stockAdjustInfo.setAdjustNumber(CodeUtils.getOneCode(adjustPrefix, maxCode, 3));
        int result = stockAdjustInfoMapper.insertStockAdjustInfo(stockAdjustInfo);
        if (result <= 0)
        {
            throw new BaseException("库存调整新增错误！");
        }
        for (StockAdjustDetail stockAdjustDetail : stockAdjustDetails)
        {
            stockAdjustDetail.setAdjustNumber(stockAdjustInfo.getAdjustNumber());
            stockAdjustDetail.setCreateBy(SecurityUtils.getUsername());
            stockAdjustDetail.setCreateTime(DateUtils.getNowDate());
        }
        result = stockAdjustDetailMapper.insertStockAdjustDetailBatch(stockAdjustDetails);
        if (result <= 0)
        {
            throw new BaseException("库存调整详情新增错误！");
        }
        if ("2".equals(optChannel))
        {
            // 更新库存
            // 库调类型
            String adjustType = stockAdjustInfo.getAdjustType();
            List<SkStockRequest> stockRequests = Lists.newArrayList();
            String operation = "";
            String operationName = "";
            if (ADJUST_TYPE_ADD.equals(adjustType))
            {
                operation = "手工增加";
                operationName = "手工增加";
            }
            else if (ADJUST_TYPE_REMOVE.equals(adjustType))
            {
                operation = "减少";
                operationName = "手工减少";
            }
            else
            {
                operation = "库存报损";
                operationName = "库存报损";
            }
            for (StockAdjustDetail stockAdjustDetail : stockAdjustDetails)
            {
                SkStockRequest request = new SkStockRequest();
                request.setCode(stockAdjustInfo.getStockCode());
                request.setBatchCode(stockAdjustDetail.getBatchCode());
                request.setProduce(stockAdjustDetail.getMaterialId());
                request.setSupplierCode(stockAdjustDetail.getSupplierCode());
                request.setChangeNum(stockAdjustDetail.getStockAdjustNum());
                request.setOperation(operation);
                request.setOperationName(operationName);
                stockRequests.add(request);
            }
            if (ADJUST_TYPE_ADD.equals(adjustType))
            {
                skStockService.addManual(stockRequests);
            }
            else if (ADJUST_TYPE_REMOVE.equals(adjustType))
            {
                skStockService.subtractManual(stockRequests);
            }
            else
            {
                skStockService.lossReportStock(stockRequests);
            }
        }
        return AjaxResult.success();
    }

    /**
     * 修改库存调整信息
     *
     * @param stockAdjustInfo 库存调整信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateStockAdjustInfo(StockAdjustInfoVo stockAdjustInfo)
    {
        stockAdjustInfo.setUpdateTime(DateUtils.getNowDate());
        stockAdjustInfo.setUpdateBy(SecurityUtils.getUsername());
        String optChannel = stockAdjustInfo.getOptChannel();
        if (StringUtils.isEmpty(stockAdjustInfo.getAdjustNumber()) || null == stockAdjustInfo.getId())
        {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        int result;
        // 操作渠道 optChannel 1-新增保存 2-新增保存审核 3-修改保存 4-修改保存审核
        List<StockAdjustDetail> stockAdjustDetails = stockAdjustInfo.getStockAdjustDetails();
        if (CollectionUtils.isEmpty(stockAdjustDetails))
        {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        // 查询库存调整状态
        StockAdjustInfo data = stockAdjustInfoMapper.selectStockAdjustInfoById(stockAdjustInfo.getId());
        if (!StringUtils.equals(StockAdjustStatus.TO_SUBMIT.getCode(), data.getStatus()))
        {
            return AjaxResult.error("库存调整状态非待提交，不能修改");
        }
        if ("4".equals(optChannel))
        {
            // 修改保存审核-待审核
            stockAdjustInfo.setStatus(StockAdjustStatus.AUDITED.getCode());
            stockAdjustInfo.setAuditTime(new Date());
            stockAdjustInfo.setAuditBy(SecurityUtils.getUsername());
        }
        else
        {
            // 修改保存-待提交
            stockAdjustInfo.setStatus(StockAdjustStatus.TO_SUBMIT.getCode());
        }
        for (StockAdjustDetail stockAdjustDetail : stockAdjustDetails)
        {
            stockAdjustDetail.setAdjustNumber(stockAdjustInfo.getAdjustNumber());
            stockAdjustDetail.setUpdateBy(SecurityUtils.getUsername());
            stockAdjustDetail.setUpdateTime(DateUtils.getNowDate());
        }
        result = stockAdjustInfoMapper.updateStockAdjustInfo(stockAdjustInfo);
        if (result <= 0)
        {
            return AjaxResult.error();
        }
        // 删除库存调整详情
        result = stockAdjustDetailMapper.deleteStockAdjustDetailByAdjustNumber(stockAdjustInfo.getAdjustNumber());
        if (result <= 0)
        {
            throw new BaseException("库存调整详情修改错误！");
        }
        stockAdjustDetailMapper.insertStockAdjustDetailBatch(stockAdjustDetails);
        if (result <= 0)
        {
            throw new BaseException("库存调整详情修改错误！");
        }
        if ("4".equals(optChannel))
        {
            // 更新库存
            // 库调类型
            String adjustType = stockAdjustInfo.getAdjustType();
            List<SkStockRequest> stockRequests = Lists.newArrayList();
            String operation = "";
            String operationName = "";
            if (ADJUST_TYPE_ADD.equals(adjustType))
            {
                operation = "手工增加";
                operationName = "手工增加";
            }
            else if (ADJUST_TYPE_REMOVE.equals(adjustType))
            {
                operation = "减少";
                operationName = "手工减少";
            }
            else
            {
                operation = "库存报损";
                operationName = "库存报损";
            }
            for (StockAdjustDetail stockAdjustDetail : stockAdjustDetails)
            {
                SkStockRequest request = new SkStockRequest();
                request.setBatchCode(stockAdjustDetail.getBatchCode());
                request.setCode(data.getStockCode());
                request.setProduce(stockAdjustDetail.getMaterialId());
                request.setSupplierCode(stockAdjustDetail.getSupplierCode());
                request.setChangeNum(stockAdjustDetail.getStockAdjustNum());
                request.setOperation(operation);
                request.setOperationName(operationName);
                stockRequests.add(request);
            }
            if (ADJUST_TYPE_ADD.equals(adjustType))
            {
                skStockService.addManual(stockRequests);
            }
            else if (ADJUST_TYPE_REMOVE.equals(adjustType))
            {
                skStockService.subtractManual(stockRequests);
            }
            else
            {
                skStockService.lossReportStock(stockRequests);
            }
        }
        return AjaxResult.success();
    }

    /**
     * 批量删除库存调整信息
     *
     * @param ids 需要删除的库存调整信息ID
     * @return 结果
     */
    @Override
    public AjaxResult deleteStockAdjustInfoByIds(Long[] ids)
    {
        if (ArrayUtils.isEmpty(ids))
        {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        for (Long id : ids)
        {
            if (id == null)
            {
                return AjaxResult.error(ResultEnums.NULL_PARAM);
            }
        }
        // 查询库存调整状态
        List<StockAdjustInfoVo> data = stockAdjustInfoMapper.selectStockAdjustInfoByIds(ids);
        for (StockAdjustInfo stockAdjustInfo : data)
        {
            if (!StringUtils.equals(StockAdjustStatus.TO_SUBMIT.getCode(), stockAdjustInfo.getStatus()))
            {
                return AjaxResult.error("库调状态非未提交，不能删除");
            }
        }
        int result = stockAdjustInfoMapper.deleteStockAdjustInfoByIds(ids);
        if (result <= 0)
        {
            throw new BaseException("库存调整删除错误！");
        }
        return AjaxResult.success();
    }

    /**
     * 删除库存调整信息信息
     *
     * @param id 库存调整信息ID
     * @return 结果
     */
    @Override
    public int deleteStockAdjustInfoById(Long id)
    {
        return stockAdjustInfoMapper.deleteStockAdjustInfoById(id);
    }
}
