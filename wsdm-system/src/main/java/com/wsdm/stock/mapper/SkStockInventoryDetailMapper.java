package com.wsdm.stock.mapper;

import com.wsdm.stock.domain.SkStockInventoryDetail;

import java.util.List;

/**
 * 库存盘点详情Mapper接口
 *
 * @author wsdm
 * @date 2021-05-25
 */
public interface SkStockInventoryDetailMapper
{
    /**
     * 查询库存盘点详情
     *
     * @param id 库存盘点详情ID
     * @return 库存盘点详情
     */
    public SkStockInventoryDetail selectSkStockInventoryDetailById(Long id);

    /**
     * 查询库存盘点详情列表
     *
     * @param skStockInventoryDetail 库存盘点详情
     * @return 库存盘点详情集合
     */
    public List<SkStockInventoryDetail> selectSkStockInventoryDetailList(SkStockInventoryDetail skStockInventoryDetail);

    /**
     * 新增库存盘点详情
     *
     * @param skStockInventoryDetail 库存盘点详情
     * @return 结果
     */
    public int insertSkStockInventoryDetail(SkStockInventoryDetail skStockInventoryDetail);

    /**
     * 修改库存盘点详情
     *
     * @param skStockInventoryDetail 库存盘点详情
     * @return 结果
     */
    public int updateSkStockInventoryDetail(SkStockInventoryDetail skStockInventoryDetail);

    /**
     * 删除库存盘点详情
     *
     * @param id 库存盘点详情ID
     * @return 结果
     */
    public int deleteSkStockInventoryDetailById(Long id);

    /**
     * 批量删除库存盘点详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSkStockInventoryDetailByIds(Long[] ids);
}
