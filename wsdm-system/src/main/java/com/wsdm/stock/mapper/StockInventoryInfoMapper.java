package com.wsdm.stock.mapper;


import com.wsdm.stock.domain.StockInventoryInfo;
import com.wsdm.stock.domain.StockInventoryInfoVo;

import java.util.List;

/**
 * 库存盘点信息Mapper接口
 *
 * @author wsdm
 * @date 2021-01-22
 */
public interface StockInventoryInfoMapper {
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
    public int insertStockInventoryInfo(StockInventoryInfo stockInventoryInfo);

    /**
     * 修改库存盘点信息
     *
     * @param stockInventoryInfo 库存盘点信息
     * @return 结果
     */
    public int updateStockInventoryInfo(StockInventoryInfo stockInventoryInfo);

    /**
     * 删除库存盘点信息
     *
     * @param id 库存盘点信息ID
     * @return 结果
     */
    public int deleteStockInventoryInfoById(Long id);

    /**
     * 批量删除库存盘点信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStockInventoryInfoByIds(Long[] ids);

    /**
     * 查询最大库存盘点单号
     *
     * @param code 库调单号前缀
     * @return 结果
     */
    public String getMaxInventoryCode(String code);

    /**
     * 批量查询库存盘点信息
     *
     * @param ids 需要查询的数据ID
     * @return 结果
     */
    public List<StockInventoryInfoVo> selectStockInventoryInfoByIds(Long[] ids);
}
