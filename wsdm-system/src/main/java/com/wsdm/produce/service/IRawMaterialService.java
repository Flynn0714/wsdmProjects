package com.wsdm.produce.service;

import com.wsdm.produce.domain.RawMaterial;

import java.util.List;

/**
 * 原材料Service接口
 *
 * @author wsdm
 * @date 2020-11-06
 */
public interface IRawMaterialService {
    /**
     * 查询原材料
     *
     * @param id 原材料ID
     * @return 原材料
     */
    public RawMaterial selectRawMaterialById(Long id);

    /**
     * 查询原材料列表
     *
     * @param rawMaterial 原材料
     * @return 原材料集合
     */
    public List<RawMaterial> selectRawMaterialList(RawMaterial rawMaterial);

    /**
     * 新增原材料
     *
     * @param rawMaterial 原材料
     * @return 结果
     */
    public int insertRawMaterial(RawMaterial rawMaterial);

    /**
     * 修改原材料
     *
     * @param rawMaterial 原材料
     * @return 结果
     */
    public int updateRawMaterial(RawMaterial rawMaterial);

    /**
     * 批量删除原材料
     *
     * @param ids 需要删除的原材料ID
     * @return 结果
     */
    public int deleteRawMaterialByIds(Long[] ids);

    /**
     * 删除原材料信息
     *
     * @param id 原材料ID
     * @return 结果
     */
    public int deleteRawMaterialById(Long id);
}
