package com.wsdm.stock.mapper;

import com.wsdm.stock.domain.StockAdjustInfo;
import com.wsdm.stock.domain.StockAdjustInfoVo;

import java.util.List;

/**
 * 库存调整信息Mapper接口
 *
 * @author wsdm
 * @date 2021-01-20
 */
public interface StockAdjustInfoMapper {
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
    public int insertStockAdjustInfo(StockAdjustInfo stockAdjustInfo);

    /**
     * 修改库存调整信息
     *
     * @param stockAdjustInfo 库存调整信息
     * @return 结果
     */
    public int updateStockAdjustInfo(StockAdjustInfo stockAdjustInfo);

    /**
     * 删除库存调整信息
     *
     * @param id 库存调整信息ID
     * @return 结果
     */
    public int deleteStockAdjustInfoById(Long id);

    /**
     * 批量删除库存调整信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStockAdjustInfoByIds(Long[] ids);

    /**
     * 查询最大库调单号
     *
     * @param code 库调单号前缀
     * @return 结果
     */
    public String getMaxAdjustCode(String code);

    /**
     * 批量查询库存调整信息
     *
     * @param ids 需要查询的数据ID
     * @return 结果
     */
    public List<StockAdjustInfoVo> selectStockAdjustInfoByIds(Long[] ids);
}
