package com.wsdm.stock.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.stock.domain.StockInventoryInfo;
import com.wsdm.stock.domain.StockInventoryInfoVo;

import java.util.List;

/**
 * 库存盘点信息Service接口
 *
 * @author wsdm
 * @date 2021-01-22
 */
public interface IStockInventoryManageService {
    /**
     * 查询库存盘点信息
     *
     * @param id 库存盘点信息ID
     * @return 库存盘点信息
     */
    public StockInventoryInfoVo selectStockInventoryInfoById(Long id);

    /**
     * 查询库存盘点信息列表
     *
     * @param stockInventoryInfo 库存盘点信息
     * @return 库存盘点信息集合
     */
    public List<StockInventoryInfo> selectStockInventoryInfoList(StockInventoryInfoVo stockInventoryInfo);

    /**
     * 新增库存盘点信息
     *
     * @param stockInventoryInfo 库存盘点信息
     * @return 结果
     */
    public AjaxResult insertStockInventoryInfo(StockInventoryInfoVo stockInventoryInfo);

    /**
     * 修改库存盘点信息
     *
     * @param stockInventoryInfo 库存盘点信息
     * @return 结果
     */
    public AjaxResult updateStockInventoryInfo(StockInventoryInfoVo stockInventoryInfo);

    /**
     * 批量删除库存盘点信息
     *
     * @param ids 需要删除的库存盘点信息ID
     * @return 结果
     */
    public AjaxResult deleteStockInventoryInfoByIds(Long[] ids);

    /**
     * 删除库存盘点信息信息
     *
     * @param id 库存盘点信息ID
     * @return 结果
     */
    public int deleteStockInventoryInfoById(Long id);
}
