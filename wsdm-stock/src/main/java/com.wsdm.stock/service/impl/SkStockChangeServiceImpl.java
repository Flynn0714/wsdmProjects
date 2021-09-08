package com.wsdm.stock.service.impl;

import com.wsdm.common.utils.DateUtils;
import com.wsdm.stock.domain.SkStockChange;
import com.wsdm.stock.mapper.SkStockChangeMapper;
import com.wsdm.stock.service.ISkStockChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 库存变动记录Service业务层处理
 *
 * @author wsdm
 * @date 2020-12-07
 */
@Service
public class SkStockChangeServiceImpl implements ISkStockChangeService {
    @Autowired
    private SkStockChangeMapper skStockChangeMapper;

    /**
     * 查询库存变动记录
     *
     * @param id 库存变动记录ID
     * @return 库存变动记录
     */
    @Override
    public SkStockChange selectSkStockChangeById(Long id) {
        return skStockChangeMapper.selectSkStockChangeById(id);
    }

    /**
     * 查询库存变动记录列表
     *
     * @param skStockChange 库存变动记录
     * @return 库存变动记录
     */
    @Override
    public List<SkStockChange> selectSkStockChangeList(SkStockChange skStockChange) {
        return skStockChangeMapper.selectSkStockChangeList(skStockChange);
    }

    /**
     * 新增库存变动记录
     *
     * @param skStockChange 库存变动记录
     * @return 结果
     */
    @Override
    public int insertSkStockChange(SkStockChange skStockChange) {
        skStockChange.setCreateTime(DateUtils.getNowDate());
        skStockChange.setChangeTime(DateUtils.getNowDate());
        return skStockChangeMapper.insertSkStockChange(skStockChange);
    }

    /**
     * 修改库存变动记录
     *
     * @param skStockChange 库存变动记录
     * @return 结果
     */
    @Override
    public int updateSkStockChange(SkStockChange skStockChange) {
        skStockChange.setUpdateTime(DateUtils.getNowDate());
        return skStockChangeMapper.updateSkStockChange(skStockChange);
    }

    /**
     * 批量删除库存变动记录
     *
     * @param ids 需要删除的库存变动记录ID
     * @return 结果
     */
    @Override
    public int deleteSkStockChangeByIds(Long[] ids) {
        return skStockChangeMapper.deleteSkStockChangeByIds(ids);
    }

    /**
     * 删除库存变动记录信息
     *
     * @param id 库存变动记录ID
     * @return 结果
     */
    @Override
    public int deleteSkStockChangeById(Long id) {
        return skStockChangeMapper.deleteSkStockChangeById(id);
    }
}
