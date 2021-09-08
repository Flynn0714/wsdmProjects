package com.wsdm.stock.mapper;

import com.wsdm.stock.domain.SkStockBatchLock;

import java.util.List;

/**
 * 库存批次锁定记录Mapper接口
 *
 * @author wsdm
 * @date 2020-12-07
 */
public interface SkStockBatchLockMapper {
    /**
     * 查询库存批次锁定记录
     *
     * @param id 库存批次锁定记录ID
     * @return 库存批次锁定记录
     */
    public SkStockBatchLock selectSkStockBatchLockById(Long id);

    /**
     * 查询库存批次锁定记录列表
     *
     * @param skStockBatchLock 库存批次锁定记录
     * @return 库存批次锁定记录集合
     */
    public List<SkStockBatchLock> selectSkStockBatchLockList(SkStockBatchLock skStockBatchLock);

    /**
     * 新增库存批次锁定记录
     *
     * @param skStockBatchLock 库存批次锁定记录
     * @return 结果
     */
    public int insertSkStockBatchLock(SkStockBatchLock skStockBatchLock);

    /**
     * 修改库存批次锁定记录
     *
     * @param skStockBatchLock 库存批次锁定记录
     * @return 结果
     */
    public int updateSkStockBatchLock(SkStockBatchLock skStockBatchLock);

    /**
     * 删除库存批次锁定记录
     *
     * @param id 库存批次锁定记录ID
     * @return 结果
     */
    public int deleteSkStockBatchLockById(Long id);

    /**
     * 批量删除库存批次锁定记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSkStockBatchLockByIds(Long[] ids);
}
