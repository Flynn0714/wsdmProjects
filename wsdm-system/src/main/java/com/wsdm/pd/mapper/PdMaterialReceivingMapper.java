package com.wsdm.pd.mapper;

import com.wsdm.pd.domain.PdMaterialReceiving;

import java.util.List;

/**
 * 物料领取单Mapper接口
 *
 * @author wsdm
 * @date 2021-05-11
 */
public interface PdMaterialReceivingMapper
{
    /**
     * 查询物料领取单
     *
     * @param id 物料领取单ID
     * @return 物料领取单
     */
    public PdMaterialReceiving selectPdMaterialReceivingById(Long id);

    /**
     * 查询物料领取单列表
     *
     * @param pdMaterialReceiving 物料领取单
     * @return 物料领取单集合
     */
    public List<PdMaterialReceiving> selectPdMaterialReceivingList(PdMaterialReceiving pdMaterialReceiving);

    /**
     * 新增物料领取单
     *
     * @param pdMaterialReceiving 物料领取单
     * @return 结果
     */
    public int insertPdMaterialReceiving(PdMaterialReceiving pdMaterialReceiving);

    /**
     * 修改物料领取单
     *
     * @param pdMaterialReceiving 物料领取单
     * @return 结果
     */
    public int updatePdMaterialReceiving(PdMaterialReceiving pdMaterialReceiving);

    /**
     * 删除物料领取单
     *
     * @param id 物料领取单ID
     * @return 结果
     */
    public int deletePdMaterialReceivingById(Long id);

    /**
     * 批量删除物料领取单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePdMaterialReceivingByIds(Long[] ids);

    String getLatestCode();
}
