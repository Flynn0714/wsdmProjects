package com.wsdm.pd.service.impl;

import com.wsdm.common.utils.DateUtils;
import com.wsdm.pd.domain.PdMaterialReceivingDetail;
import com.wsdm.pd.mapper.PdMaterialReceivingDetailMapper;
import com.wsdm.pd.service.IPdMaterialReceivingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物料领取单明细Service业务层处理
 *
 * @author wsdm
 * @date 2021-05-11
 */
@Service
public class PdMaterialReceivingDetailServiceImpl implements IPdMaterialReceivingDetailService
{
    @Autowired
    private PdMaterialReceivingDetailMapper pdMaterialReceivingDetailMapper;

    /**
     * 查询物料领取单明细
     *
     * @param id 物料领取单明细ID
     * @return 物料领取单明细
     */
    @Override
    public PdMaterialReceivingDetail selectPdMaterialReceivingDetailById(Long id)
    {
        return pdMaterialReceivingDetailMapper.selectPdMaterialReceivingDetailById(id);
    }

    /**
     * 查询物料领取单明细列表
     *
     * @param pdMaterialReceivingDetail 物料领取单明细
     * @return 物料领取单明细
     */
    @Override
    public List<PdMaterialReceivingDetail> selectPdMaterialReceivingDetailList(
            PdMaterialReceivingDetail pdMaterialReceivingDetail)
    {
        return pdMaterialReceivingDetailMapper.selectPdMaterialReceivingDetailList(pdMaterialReceivingDetail);
    }

    /**
     * 新增物料领取单明细
     *
     * @param pdMaterialReceivingDetail 物料领取单明细
     * @return 结果
     */
    @Override
    public int insertPdMaterialReceivingDetail(PdMaterialReceivingDetail pdMaterialReceivingDetail)
    {
        pdMaterialReceivingDetail.setCreateTime(DateUtils.getNowDate());
        return pdMaterialReceivingDetailMapper.insertPdMaterialReceivingDetail(pdMaterialReceivingDetail);
    }

    /**
     * 修改物料领取单明细
     *
     * @param pdMaterialReceivingDetail 物料领取单明细
     * @return 结果
     */
    @Override
    public int updatePdMaterialReceivingDetail(PdMaterialReceivingDetail pdMaterialReceivingDetail)
    {
        pdMaterialReceivingDetail.setUpdateTime(DateUtils.getNowDate());
        return pdMaterialReceivingDetailMapper.updatePdMaterialReceivingDetail(pdMaterialReceivingDetail);
    }

    /**
     * 批量删除物料领取单明细
     *
     * @param ids 需要删除的物料领取单明细ID
     * @return 结果
     */
    @Override
    public int deletePdMaterialReceivingDetailByIds(Long[] ids)
    {
        return pdMaterialReceivingDetailMapper.deletePdMaterialReceivingDetailByIds(ids);
    }

    /**
     * 删除物料领取单明细信息
     *
     * @param id 物料领取单明细ID
     * @return 结果
     */
    @Override
    public int deletePdMaterialReceivingDetailById(Long id)
    {
        return pdMaterialReceivingDetailMapper.deletePdMaterialReceivingDetailById(id);
    }
}
