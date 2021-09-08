package com.wsdm.pd.mapper;

import com.wsdm.pd.domain.PdMaterialReceivingDetail;

import java.util.List;

/**
 * 物料领取单明细Mapper接口
 *
 * @author wsdm
 * @date 2021-05-11
 */
public interface PdMaterialReceivingDetailMapper
{
    /**
     * 查询物料领取单明细
     *
     * @param id 物料领取单明细ID
     * @return 物料领取单明细
     */
    public PdMaterialReceivingDetail selectPdMaterialReceivingDetailById(Long id);

    /**
     * 查询物料领取单明细列表
     *
     * @param pdMaterialReceivingDetail 物料领取单明细
     * @return 物料领取单明细集合
     */
    public List<PdMaterialReceivingDetail> selectPdMaterialReceivingDetailList(
            PdMaterialReceivingDetail pdMaterialReceivingDetail);

    /**
     * 新增物料领取单明细
     *
     * @param pdMaterialReceivingDetail 物料领取单明细
     * @return 结果
     */
    public int insertPdMaterialReceivingDetail(PdMaterialReceivingDetail pdMaterialReceivingDetail);

    /**
     * 修改物料领取单明细
     *
     * @param pdMaterialReceivingDetail 物料领取单明细
     * @return 结果
     */
    public int updatePdMaterialReceivingDetail(PdMaterialReceivingDetail pdMaterialReceivingDetail);

    /**
     * 删除物料领取单明细
     *
     * @param id 物料领取单明细ID
     * @return 结果
     */
    public int deletePdMaterialReceivingDetailById(Long id);

    /**
     * 批量删除物料领取单明细
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePdMaterialReceivingDetailByIds(Long[] ids);
}
