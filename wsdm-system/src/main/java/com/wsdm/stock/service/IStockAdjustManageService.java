package com.wsdm.stock.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.stock.domain.StockAdjustInfo;
import com.wsdm.stock.domain.StockAdjustInfoVo;

import java.util.List;

/**
 * 库存调整信息Service接口
 *
 * @author wsdm
 * @date 2021-01-20
 */
public interface IStockAdjustManageService
{
    String ADJUST_TYPE_ADD    = "1";
    String ADJUST_TYPE_REMOVE = "2";

    /**
     * 查询库存调整信息
     *
     * @param id 库存调整信息ID
     * @return 库存调整信息
     */
    public StockAdjustInfoVo selectStockAdjustInfoById(Long id);

    /**
     * 查询库存调整信息列表
     *
     * @param stockAdjustInfo 库存调整信息
     * @return 库存调整信息集合
     */
    public List<StockAdjustInfo> selectStockAdjustInfoList(StockAdjustInfo stockAdjustInfo);

    /**
     * 新增库存调整信息
     *
     * @param stockAdjustInfo 库存调整信息
     * @return 结果
     */
    public AjaxResult insertStockAdjustInfo(StockAdjustInfoVo stockAdjustInfo);

    /**
     * 修改库存调整信息
     *
     * @param stockAdjustInfo 库存调整信息
     * @return 结果
     */
    public AjaxResult updateStockAdjustInfo(StockAdjustInfoVo stockAdjustInfo);

    /**
     * 批量删除库存调整信息
     *
     * @param ids 需要删除的库存调整信息ID
     * @return 结果
     */
    public AjaxResult deleteStockAdjustInfoByIds(Long[] ids);

    /**
     * 删除库存调整信息信息
     *
     * @param id 库存调整信息ID
     * @return 结果
     */
    public int deleteStockAdjustInfoById(Long id);
}
