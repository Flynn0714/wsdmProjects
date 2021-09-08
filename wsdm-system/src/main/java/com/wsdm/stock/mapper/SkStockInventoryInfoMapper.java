package com.wsdm.stock.mapper;

import com.wsdm.stock.domain.SkStockInventoryInfo;

import java.util.List;

/**
 * 库存盘点信息Mapper接口
 *
 * @author wsdm
 * @date 2021-05-25
 */
public interface SkStockInventoryInfoMapper
{
    /**
     * 查询库存盘点信息
     *
     * @param id 库存盘点信息ID
     * @return 库存盘点信息
     */
    public SkStockInventoryInfo selectSkStockInventoryInfoById(Long id);

    /**
     * 查询库存盘点信息列表
     *
     * @param skStockInventoryInfo 库存盘点信息
     * @return 库存盘点信息集合
     */
    public List<SkStockInventoryInfo> selectSkStockInventoryInfoList(SkStockInventoryInfo skStockInventoryInfo);

    /**
     * 新增库存盘点信息
     *
     * @param skStockInventoryInfo 库存盘点信息
     * @return 结果
     */
    public int insertSkStockInventoryInfo(SkStockInventoryInfo skStockInventoryInfo);

    /**
     * 修改库存盘点信息
     *
     * @param skStockInventoryInfo 库存盘点信息
     * @return 结果
     */
    public int updateSkStockInventoryInfo(SkStockInventoryInfo skStockInventoryInfo);

    /**
     * 删除库存盘点信息
     *
     * @param id 库存盘点信息ID
     * @return 结果
     */
    public int deleteSkStockInventoryInfoById(Long id);

    /**
     * 批量删除库存盘点信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSkStockInventoryInfoByIds(Long[] ids);

    String getLatestCode();
}
