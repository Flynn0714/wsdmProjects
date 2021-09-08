package com.wsdm.stock.service.impl;

import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.DictUtils;
import com.wsdm.common.utils.StringUtils;
import com.wsdm.stock.domain.SkStockBatch;
import com.wsdm.stock.mapper.SkStockBatchMapper;
import com.wsdm.stock.service.IPdProduceHardwareSupport;
import com.wsdm.stock.service.ISkStockBatchService;
import com.wsdm.stock.service.ISupplierNameQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 库存批次Service业务层处理
 *
 * @author wsdm
 * @date 2020-12-07
 */
@Service
public class SkStockBatchServiceImpl implements ISkStockBatchService {
    @Autowired
    private SkStockBatchMapper skStockBatchMapper;

    @Autowired
    private IPdProduceHardwareSupport pdProduceHardwareSupport;

    @Autowired
    private ISupplierNameQuery supplierNameQuery;

    /**
     * 查询库存批次
     *
     * @param id 库存批次ID
     * @return 库存批次
     */
    @Override
    public SkStockBatch selectSkStockBatchById(Long id) {
        SkStockBatch batch = skStockBatchMapper.selectSkStockBatchById(id);
        batch.setModel(pdProduceHardwareSupport.getModel(batch.getProduce()));
        batch.setSupplierName(supplierNameQuery.query(batch.getSupplierCode()));
        String name = DictUtils.getDictLabel("dict_stock_subarea", batch.getStockCode());
        if (StringUtils.isNotEmpty(name)) {
            batch.setStockName(name);
        }
        return batch;
    }

    /**
     * 查询库存批次列表
     *
     * @param skStockBatch 库存批次
     * @return 库存批次
     */
    @Override
    public List<SkStockBatch> selectSkStockBatchList(SkStockBatch skStockBatch) {
        List<SkStockBatch> batches = skStockBatchMapper.selectSkStockBatchList(skStockBatch);
        if (CollectionUtils.isNotEmpty(batches)) {
            for (SkStockBatch batch : batches) {
                batch.setModel(pdProduceHardwareSupport.getModel(batch.getProduce()));
                batch.setSupplierName(supplierNameQuery.query(batch.getSupplierCode()));
                String name = DictUtils.getDictLabel("dict_stock_subarea", batch.getStockCode());
                if (StringUtils.isNotEmpty(name)) {
                    batch.setStockName(name);
                }
            }
        }
        return batches;
    }

    /**
     * 新增库存批次
     *
     * @param skStockBatch 库存批次
     * @return 结果
     */
    @Override
    public int insertSkStockBatch(SkStockBatch skStockBatch) {
        skStockBatch.setCreateTime(DateUtils.getNowDate());
        skStockBatch.setBatchTime(DateUtils.getNowDate());
        return skStockBatchMapper.insertSkStockBatch(skStockBatch);
    }

    /**
     * 修改库存批次
     *
     * @param skStockBatch 库存批次
     * @return 结果
     */
    @Override
    public int updateSkStockBatch(SkStockBatch skStockBatch) {
        skStockBatch.setUpdateTime(DateUtils.getNowDate());
        return skStockBatchMapper.updateSkStockBatch(skStockBatch);
    }

    /**
     * 批量删除库存批次
     *
     * @param ids 需要删除的库存批次ID
     * @return 结果
     */
    @Override
    public int deleteSkStockBatchByIds(Long[] ids) {
        return skStockBatchMapper.deleteSkStockBatchByIds(ids);
    }

    /**
     * 删除库存批次信息
     *
     * @param id 库存批次ID
     * @return 结果
     */
    @Override
    public int deleteSkStockBatchById(Long id) {
        return skStockBatchMapper.deleteSkStockBatchById(id);
    }

    /**
     * 查询最大批次单号
     *
     * @param code 批次单号前缀
     * @return 结果
     */
    @Override
    public String getMaxBatchCode(String code) {
        return skStockBatchMapper.getMaxBatchCode(code);
    }
}
