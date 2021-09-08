package com.wsdm.stock.mapper;

import com.wsdm.stock.domain.StockInventoryDetail;

import java.util.List;

/**
 * 库存盘点详情Mapper接口
 *
 * @author wsdm
 * @date 2021-01-22
 */
public interface StockInventoryDetailMapper {
    /**
     * 查询库存盘点详情
     *
     * @param id 库存盘点详情ID
     * @return 库存盘点详情
     */
    public StockInventoryDetail selectStockInventoryDetailById(Long id);

    /**
     * 查询库存盘点详情列表
     *
     * @param stockInventoryDetail 库存盘点详情
     * @return 库存盘点详情集合
     */
    public List<StockInventoryDetail> selectStockInventoryDetailList(StockInventoryDetail stockInventoryDetail);

    /**
     * 新增库存盘点详情
     *
     * @param stockInventoryDetail 库存盘点详情
     * @return 结果
     */
    public int insertStockInventoryDetail(StockInventoryDetail stockInventoryDetail);

    /**
     * 修改库存盘点详情
     *
     * @param stockInventoryDetail 库存盘点详情
     * @return 结果
     */
    public int updateStockInventoryDetail(StockInventoryDetail stockInventoryDetail);

    /**
     * 删除库存盘点详情
     *
     * @param id 库存盘点详情ID
     * @return 结果
     */
    public int deleteStockInventoryDetailById(Long id);

    /**
     * 批量删除库存盘点详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStockInventoryDetailByIds(Long[] ids);

    /**
     * 查询库存盘点详情列表
     *
     * @param inventoryNumber 库存盘点单号
     * @return 结果
     */
    public List<StockInventoryDetail> selectStockInventoryDetailByAdjustNumber(String inventoryNumber);

    /**
     * 新增库存盘点详情
     *
     * @param list 库存盘点订单详情
     * @return 结果
     */
    public int insertStockInventoryDetailBatch(List<StockInventoryDetail> list);

    /**
     * 删除库存盘点详情
     *
     * @param inventoryNumber 库存盘点单号
     * @return 结果
     */
    public int deleteStockInventoryDetailByInventoryNumber(String inventoryNumber);

    /**
     * 查询库存盘点详情列表
     *
     * @param inventoryNumber 库存盘点
     * @return 结果
     */
    List<StockInventoryDetail> selectStockInventoryDetailByInventoryNumber(String inventoryNumber);
}
