package com.wsdm.stock.mapper;

import com.wsdm.stock.domain.SkStockChange;

import java.util.List;

/**
 * 库存变动记录Mapper接口
 *
 * @author wsdm
 * @date 2020-12-07
 */
public interface SkStockChangeMapper {
    /**
     * 查询库存变动记录
     *
     * @param id 库存变动记录ID
     * @return 库存变动记录
     */
    public SkStockChange selectSkStockChangeById(Long id);

    /**
     * 查询库存变动记录列表
     *
     * @param skStockChange 库存变动记录
     * @return 库存变动记录集合
     */
    public List<SkStockChange> selectSkStockChangeList(SkStockChange skStockChange);

    /**
     * 新增库存变动记录
     *
     * @param skStockChange 库存变动记录
     * @return 结果
     */
    public int insertSkStockChange(SkStockChange skStockChange);

    /**
     * 修改库存变动记录
     *
     * @param skStockChange 库存变动记录
     * @return 结果
     */
    public int updateSkStockChange(SkStockChange skStockChange);

    /**
     * 删除库存变动记录
     *
     * @param id 库存变动记录ID
     * @return 结果
     */
    public int deleteSkStockChangeById(Long id);

    /**
     * 批量删除库存变动记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSkStockChangeByIds(Long[] ids);
}
