package com.wsdm.stock.service.impl;

import com.google.common.collect.Lists;
import com.wsdm.common.CodeUtils;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.ResultEnums;
import com.wsdm.common.enums.StockInventoryStatus;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.StringUtils;
import com.wsdm.stock.domain.SkStockRequest;
import com.wsdm.stock.domain.StockInventoryDetail;
import com.wsdm.stock.domain.StockInventoryInfo;
import com.wsdm.stock.domain.StockInventoryInfoVo;
import com.wsdm.stock.mapper.StockInventoryDetailMapper;
import com.wsdm.stock.mapper.StockInventoryInfoMapper;
import com.wsdm.stock.service.ISkStockService;
import com.wsdm.stock.service.IStockInventoryManageService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * 库存盘点信息Service业务层处理
 *
 * @author wsdm
 * @date 2021-01-22
 */
@Service
public class StockInventoryManageServiceImpl implements IStockInventoryManageService {
    @Autowired
    private StockInventoryInfoMapper stockInventoryInfoMapper;
    @Autowired
    private StockInventoryDetailMapper stockInventoryDetailMapper;
    @Autowired
    private ISkStockService skStockService;

    /**
     * 查询库存盘点信息
     *
     * @param id 库存盘点信息ID
     * @return 库存盘点信息
     */
    @Override
    public StockInventoryInfoVo selectStockInventoryInfoById(Long id) {
        // 查询库存盘点信息
        StockInventoryInfoVo stockInventoryInfoVo = stockInventoryInfoMapper.selectStockInventoryInfoById(id);
        if (stockInventoryInfoVo == null || StringUtils.isEmpty(stockInventoryInfoVo.getInventoryNumber())) {
            return stockInventoryInfoVo;
        }
        // 根据库存盘点单号查询库存盘点详情
        List<StockInventoryDetail> stockInventoryDetails = stockInventoryDetailMapper.selectStockInventoryDetailByInventoryNumber(stockInventoryInfoVo.getInventoryNumber());
        stockInventoryInfoVo.setStockInventoryDetails(stockInventoryDetails);
        return stockInventoryInfoVo;
    }

    /**
     * 查询库存盘点信息列表
     *
     * @param stockInventoryInfo 库存盘点信息
     * @return 库存盘点信息
     */
    @Override
    public List<StockInventoryInfo> selectStockInventoryInfoList(StockInventoryInfoVo stockInventoryInfo) {
        return stockInventoryInfoMapper.selectStockInventoryInfoList(stockInventoryInfo);
    }

    /**
     * 新增库存盘点信息
     *
     * @param stockInventoryInfo 库存盘点信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult insertStockInventoryInfo(StockInventoryInfoVo stockInventoryInfo) {
        List<StockInventoryDetail> stockInventoryDetails = stockInventoryInfo.getStockInventoryDetails();
        if (CollectionUtils.isEmpty(stockInventoryDetails)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        String inventoryPrefix = "C" + DateUtils.parseDateToStr(DateUtils.YYYYMM, new Date());
        String maxCode = stockInventoryInfoMapper.getMaxInventoryCode(inventoryPrefix);
        stockInventoryInfo.setCreateBy(SecurityUtils.getUsername());
        stockInventoryInfo.setCreateTime(DateUtils.getNowDate());

        stockInventoryInfo.setStatus(StockInventoryStatus.NOT_STARTED.getCode());
        // 编码
        stockInventoryInfo.setInventoryNumber(CodeUtils.getOneCode(maxCode, maxCode, 3));
        int result = stockInventoryInfoMapper.insertStockInventoryInfo(stockInventoryInfo);
        if (result <= 0) {
            throw new BaseException("库存盘点新增错误！");
        }
        for (StockInventoryDetail stockInventoryDetail : stockInventoryDetails) {
            stockInventoryDetail.setInventoryNumber(stockInventoryInfo.getInventoryNumber());
            stockInventoryDetail.setCreateBy(SecurityUtils.getUsername());
            stockInventoryDetail.setCreateTime(DateUtils.getNowDate());
        }
        result = stockInventoryDetailMapper.insertStockInventoryDetailBatch(stockInventoryDetails);
        if (result <= 0) {
            throw new BaseException("库存盘点详情新增错误！");
        }
        return AjaxResult.success();
    }

    /**
     * 修改库存盘点信息
     *
     * @param stockInventoryInfo 库存盘点信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateStockInventoryInfo(StockInventoryInfoVo stockInventoryInfo) {
        stockInventoryInfo.setUpdateTime(DateUtils.getNowDate());
        stockInventoryInfo.setUpdateBy(SecurityUtils.getUsername());
        // 操作渠道 optChannel 1-待开始修改保存 2-盘点开始 3-待盘点修改保存 4-录入完成 5-盘点登账
        String optChannel = stockInventoryInfo.getOptChannel();
        if (StringUtils.isEmpty(stockInventoryInfo.getInventoryNumber()) || null == stockInventoryInfo.getId()) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        int result;
        if (optChannel.equals(2)) {
            // 盘点开始，仅修改状态为待盘点录入
            stockInventoryInfo.setStatus(StockInventoryStatus.WAIT_ENTRY.getCode());
            stockInventoryInfo.setStartTime(DateUtils.getNowDate());
            stockInventoryInfo.setStartOperator(SecurityUtils.getUsername());
            result = stockInventoryInfoMapper.updateStockInventoryInfo(stockInventoryInfo);
            if (result <= 0) {
                return AjaxResult.error();
            }
        } else {
            if ("1".equals(optChannel)) {
                stockInventoryInfo.setStatus(StockInventoryStatus.NOT_STARTED.getCode());
            } else if ("3".equals(optChannel)) {
                stockInventoryInfo.setStatus(StockInventoryStatus.WAIT_ENTRY.getCode());
            } else if ("4".equals(optChannel)) {
                stockInventoryInfo.setEntryTime(DateUtils.getNowDate());
                stockInventoryInfo.setEntryOperator(SecurityUtils.getUsername());
                stockInventoryInfo.setStatus(StockInventoryStatus.WAIT_BOOKED.getCode());
            } else if ("5".equals(optChannel)) {
                stockInventoryInfo.setBookingTime(DateUtils.getNowDate());
                stockInventoryInfo.setBookingOperator(SecurityUtils.getUsername());
                stockInventoryInfo.setStatus(StockInventoryStatus.COMPLETED.getCode());
            }
            result = stockInventoryInfoMapper.updateStockInventoryInfo(stockInventoryInfo);
            if (result <= 0) {
                return AjaxResult.error();
            }
            List<StockInventoryDetail> stockInventoryDetails = stockInventoryInfo.getStockInventoryDetails();
            if (CollectionUtils.isEmpty(stockInventoryDetails)) {
                return AjaxResult.error(ResultEnums.NULL_PARAM);
            }
            for (StockInventoryDetail stockInventoryDetail : stockInventoryDetails) {
                stockInventoryDetail.setInventoryNumber(stockInventoryInfo.getInventoryNumber());
                stockInventoryDetail.setUpdateBy(SecurityUtils.getUsername());
                stockInventoryDetail.setUpdateTime(DateUtils.getNowDate());
            }
            // 删除库存盘点详情
            result = stockInventoryDetailMapper.deleteStockInventoryDetailByInventoryNumber(stockInventoryInfo.getInventoryNumber());
            if (result <= 0) {
                throw new BaseException("库存盘点详情修改错误！");
            }
            stockInventoryDetailMapper.insertStockInventoryDetailBatch(stockInventoryDetails);
            if (result <= 0) {
                throw new BaseException("库存盘点详情修改错误！");
            }
        }
        if ("5".equals(optChannel)) {
            // 更新库存表
            for (StockInventoryDetail stockInventoryDetail : stockInventoryInfo.getStockInventoryDetails()) {
                List<SkStockRequest> stockRequests = Lists.newArrayList();
                SkStockRequest request = new SkStockRequest();
                request.setProduce(stockInventoryDetail.getMaterialId());
                int adjustNum = stockInventoryDetail.getStockAdjustNum();
                if (adjustNum < 0) {
                    request.setOperation("盘点调整-减少");
                    request.setOperationName("盘点调整-减少");
//                    request.setChangeNum(adjustNum * -1);
                    stockRequests.add(request);
                    skStockService.subtractInventory(stockRequests);
                } else {
                    request.setOperation("盘点调整-增加");
                    request.setOperationName("盘点调整-增加");
//                    request.setChangeNum(adjustNum);
                    stockRequests.add(request);
                    skStockService.addInventory(stockRequests);
                }
            }
        }
        return AjaxResult.success();
    }

    /**
     * 批量删除库存盘点信息
     *
     * @param ids 需要删除的库存盘点信息ID
     * @return 结果
     */
    @Override
    public AjaxResult deleteStockInventoryInfoByIds(Long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        for (Long id : ids) {
            if (id == null) {
                return AjaxResult.error(ResultEnums.NULL_PARAM);
            }
        }
        // 查询库存盘点状态
        List<StockInventoryInfoVo> data = stockInventoryInfoMapper.selectStockInventoryInfoByIds(ids);
        for (StockInventoryInfoVo stockInventoryInfo : data) {
            if (!StringUtils.equals(StockInventoryStatus.NOT_STARTED.getCode(), stockInventoryInfo.getStatus())) {
                return AjaxResult.error("库存盘点状态非待开始，不能删除");
            }
        }
        int result = stockInventoryInfoMapper.deleteStockInventoryInfoByIds(ids);
        if (result <= 0) {
            throw new BaseException("库存盘点删除错误！");
        }
        return AjaxResult.success();
    }

    /**
     * 删除库存盘点信息信息
     *
     * @param id 库存盘点信息ID
     * @return 结果
     */
    @Override
    public int deleteStockInventoryInfoById(Long id) {
        return stockInventoryInfoMapper.deleteStockInventoryInfoById(id);
    }
}
