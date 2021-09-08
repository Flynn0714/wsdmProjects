package com.wsdm.produce.mapper;

import com.wsdm.produce.domain.RawMaterial;

import java.util.List;

/**
 * 原材料Mapper接口
 *
 * @author wsdm
 * @date 2020-11-06
 */
public interface RawMaterialMapper {
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

    public int insertRawMaterialBatch(List<RawMaterial> list);

    /**
     * 修改原材料
     *
     * @param rawMaterial 原材料
     * @return 结果
     */
    public int updateRawMaterial(RawMaterial rawMaterial);

    /**
     * 删除原材料
     *
     * @param id 原材料ID
     * @return 结果
     */
    public int deleteRawMaterialById(Long id);

    /**
     * 批量删除原材料
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRawMaterialByIds(Long[] ids);
}
