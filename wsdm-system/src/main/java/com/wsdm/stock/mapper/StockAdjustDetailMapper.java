package com.wsdm.stock.mapper;

import com.wsdm.stock.domain.StockAdjustDetail;

import java.util.List;

/**
 * 库存调整详情Mapper接口
 *
 * @author wsdm
 * @date 2021-01-20
 */
public interface StockAdjustDetailMapper {
    /**
     * 查询库存调整详情
     *
     * @param id 库存调整详情ID
     * @return 库存调整详情
     */
    public StockAdjustDetail selectStockAdjustDetailById(Long id);

    /**
     * 查询库存调整详情列表
     *
     * @param stockAdjustDetail 库存调整详情
     * @return 库存调整详情集合
     */
    public List<StockAdjustDetail> selectStockAdjustDetailList(StockAdjustDetail stockAdjustDetail);

    /**
     * 新增库存调整详情
     *
     * @param stockAdjustDetail 库存调整详情
     * @return 结果
     */
    public int insertStockAdjustDetail(StockAdjustDetail stockAdjustDetail);

    /**
     * 修改库存调整详情
     *
     * @param stockAdjustDetail 库存调整详情
     * @return 结果
     */
    public int updateStockAdjustDetail(StockAdjustDetail stockAdjustDetail);

    /**
     * 删除库存调整详情
     *
     * @param id 库存调整详情ID
     * @return 结果
     */
    public int deleteStockAdjustDetailById(Long id);

    /**
     * 批量删除库存调整详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStockAdjustDetailByIds(Long[] ids);


    /**
     * 新增库存调整详情
     *
     * @param list 库存调整订单详情
     * @return 结果
     */
    public int insertStockAdjustDetailBatch(List<StockAdjustDetail> list);

    /**
     * 删除库存调整详情
     *
     * @param adjustNumber 库存调整单号
     * @return 结果
     */
    public int deleteStockAdjustDetailByAdjustNumber(String adjustNumber);

    /**
     * 查询库存调整详情列表
     *
     * @param adjustNumber 库存调整单号
     * @return 结果
     */
    List<StockAdjustDetail> selectStockAdjustDetailByAdjustNumber(String adjustNumber);
}
