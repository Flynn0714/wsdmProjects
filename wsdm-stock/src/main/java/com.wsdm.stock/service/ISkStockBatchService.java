package com.wsdm.stock.service;

import com.wsdm.stock.domain.SkStockBatch;

import java.util.List;

/**
 * 库存批次Service接口
 *
 * @author wsdm
 * @date 2020-12-07
 */
public interface ISkStockBatchService {
    /**
     * 查询库存批次
     *
     * @param id 库存批次ID
     * @return 库存批次
     */
    public SkStockBatch selectSkStockBatchById(Long id);

    /**
     * 查询库存批次列表
     *
     * @param skStockBatch 库存批次
     * @return 库存批次集合
     */
    public List<SkStockBatch> selectSkStockBatchList(SkStockBatch skStockBatch);

    /**
     * 新增库存批次
     *
     * @param skStockBatch 库存批次
     * @return 结果
     */
    public int insertSkStockBatch(SkStockBatch skStockBatch);

    /**
     * 修改库存批次
     *
     * @param skStockBatch 库存批次
     * @return 结果
     */
    public int updateSkStockBatch(SkStockBatch skStockBatch);

    /**
     * 批量删除库存批次
     *
     * @param ids 需要删除的库存批次ID
     * @return 结果
     */
    public int deleteSkStockBatchByIds(Long[] ids);

    /**
     * 删除库存批次信息
     *
     * @param id 库存批次ID
     * @return 结果
     */
    public int deleteSkStockBatchById(Long id);

    /**
     * 查询最大批次单号
     *
     * @param code 批次单号前缀
     * @return 结果
     */
    public String getMaxBatchCode(String code);
}
