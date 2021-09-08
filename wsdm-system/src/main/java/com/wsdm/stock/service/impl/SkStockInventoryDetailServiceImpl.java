package com.wsdm.stock.service.impl;

import com.wsdm.common.utils.DateUtils;
import com.wsdm.stock.domain.SkStockInventoryDetail;
import com.wsdm.stock.mapper.SkStockInventoryDetailMapper;
import com.wsdm.stock.service.ISkStockInventoryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 库存盘点详情Service业务层处理
 *
 * @author wsdm
 * @date 2021-05-25
 */
@Service
public class SkStockInventoryDetailServiceImpl implements ISkStockInventoryDetailService
{
    @Autowired
    private SkStockInventoryDetailMapper skStockInventoryDetailMapper;

    /**
     * 查询库存盘点详情
     *
     * @param id 库存盘点详情ID
     * @return 库存盘点详情
     */
    @Override
    public SkStockInventoryDetail selectSkStockInventoryDetailById(Long id)
    {
        return skStockInventoryDetailMapper.selectSkStockInventoryDetailById(id);
    }

    /**
     * 查询库存盘点详情列表
     *
     * @param skStockInventoryDetail 库存盘点详情
     * @return 库存盘点详情
     */
    @Override
    public List<SkStockInventoryDetail> selectSkStockInventoryDetailList(SkStockInventoryDetail skStockInventoryDetail)
    {
        return skStockInventoryDetailMapper.selectSkStockInventoryDetailList(skStockInventoryDetail);
    }

    /**
     * 新增库存盘点详情
     *
     * @param skStockInventoryDetail 库存盘点详情
     * @return 结果
     */
    @Override
    public int insertSkStockInventoryDetail(SkStockInventoryDetail skStockInventoryDetail)
    {
        skStockInventoryDetail.setCreateTime(DateUtils.getNowDate());
        return skStockInventoryDetailMapper.insertSkStockInventoryDetail(skStockInventoryDetail);
    }

    /**
     * 修改库存盘点详情
     *
     * @param skStockInventoryDetail 库存盘点详情
     * @return 结果
     */
    @Override
    public int updateSkStockInventoryDetail(SkStockInventoryDetail skStockInventoryDetail)
    {
        skStockInventoryDetail.setUpdateTime(DateUtils.getNowDate());
        return skStockInventoryDetailMapper.updateSkStockInventoryDetail(skStockInventoryDetail);
    }

    /**
     * 批量删除库存盘点详情
     *
     * @param ids 需要删除的库存盘点详情ID
     * @return 结果
     */
    @Override
    public int deleteSkStockInventoryDetailByIds(Long[] ids)
    {
        return skStockInventoryDetailMapper.deleteSkStockInventoryDetailByIds(ids);
    }

    /**
     * 删除库存盘点详情信息
     *
     * @param id 库存盘点详情ID
     * @return 结果
     */
    @Override
    public int deleteSkStockInventoryDetailById(Long id)
    {
        return skStockInventoryDetailMapper.deleteSkStockInventoryDetailById(id);
    }
}
