package com.wsdm.pd.mapper;

import com.wsdm.pd.domain.PdRawMaterial;

import java.util.List;

/**
 * 原材料Mapper接口
 *
 * @author wsdm
 * @date 2021-06-21
 */
public interface PdRawMaterialMapper {
    /**
     * 查询原材料
     *
     * @param id 原材料ID
     * @return 原材料
     */
    public PdRawMaterial selectPdRawMaterialById(Long id);

    /**
     * 查询原材料列表
     *
     * @param pdRawMaterial 原材料
     * @return 原材料集合
     */
    public List<PdRawMaterial> selectPdRawMaterialList(PdRawMaterial pdRawMaterial);

    /**
     * 新增原材料
     *
     * @param pdRawMaterial 原材料
     * @return 结果
     */
    public int insertPdRawMaterial(PdRawMaterial pdRawMaterial);

    /**
     * 修改原材料
     *
     * @param pdRawMaterial 原材料
     * @return 结果
     */
    public int updatePdRawMaterial(PdRawMaterial pdRawMaterial);

    /**
     * 删除原材料
     *
     * @param id 原材料ID
     * @return 结果
     */
    public int deletePdRawMaterialById(Long id);

    /**
     * 批量删除原材料
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePdRawMaterialByIds(Long[] ids);
}
