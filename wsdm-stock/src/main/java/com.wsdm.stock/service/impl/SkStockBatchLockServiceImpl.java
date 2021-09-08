package com.wsdm.stock.service.impl;

import com.wsdm.common.utils.DateUtils;
import com.wsdm.stock.domain.SkStockBatchLock;
import com.wsdm.stock.mapper.SkStockBatchLockMapper;
import com.wsdm.stock.service.ISkStockBatchLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 库存批次锁定记录Service业务层处理
 *
 * @author wsdm
 * @date 2020-12-07
 */
@Service
public class SkStockBatchLockServiceImpl implements ISkStockBatchLockService {
    @Autowired
    private SkStockBatchLockMapper skStockBatchLockMapper;

    /**
     * 查询库存批次锁定记录
     *
     * @param id 库存批次锁定记录ID
     * @return 库存批次锁定记录
     */
    @Override
    public SkStockBatchLock selectSkStockBatchLockById(Long id) {
        return skStockBatchLockMapper.selectSkStockBatchLockById(id);
    }

    /**
     * 查询库存批次锁定记录列表
     *
     * @param skStockBatchLock 库存批次锁定记录
     * @return 库存批次锁定记录
     */
    @Override
    public List<SkStockBatchLock> selectSkStockBatchLockList(SkStockBatchLock skStockBatchLock) {
        return skStockBatchLockMapper.selectSkStockBatchLockList(skStockBatchLock);
    }

    /**
     * 新增库存批次锁定记录
     *
     * @param skStockBatchLock 库存批次锁定记录
     * @return 结果
     */
    @Override
    public int insertSkStockBatchLock(SkStockBatchLock skStockBatchLock) {
        skStockBatchLock.setCreateTime(DateUtils.getNowDate());
        return skStockBatchLockMapper.insertSkStockBatchLock(skStockBatchLock);
    }

    /**
     * 修改库存批次锁定记录
     *
     * @param skStockBatchLock 库存批次锁定记录
     * @return 结果
     */
    @Override
    public int updateSkStockBatchLock(SkStockBatchLock skStockBatchLock) {
        skStockBatchLock.setUpdateTime(DateUtils.getNowDate());
        return skStockBatchLockMapper.updateSkStockBatchLock(skStockBatchLock);
    }

    /**
     * 批量删除库存批次锁定记录
     *
     * @param ids 需要删除的库存批次锁定记录ID
     * @return 结果
     */
    @Override
    public int deleteSkStockBatchLockByIds(Long[] ids) {
        return skStockBatchLockMapper.deleteSkStockBatchLockByIds(ids);
    }

    /**
     * 删除库存批次锁定记录信息
     *
     * @param id 库存批次锁定记录ID
     * @return 结果
     */
    @Override
    public int deleteSkStockBatchLockById(Long id) {
        return skStockBatchLockMapper.deleteSkStockBatchLockById(id);
    }
}
