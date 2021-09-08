package com.wsdm.pd.service.impl;

import com.wsdm.common.utils.DateUtils;
import com.wsdm.pd.domain.PdRawMaterial;
import com.wsdm.pd.mapper.PdRawMaterialMapper;
import com.wsdm.pd.service.IPdRawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 原材料Service业务层处理
 *
 * @author wsdm
 * @date 2021-06-21
 */
@Service
public class PdRawMaterialServiceImpl implements IPdRawMaterialService {
    @Autowired
    private PdRawMaterialMapper pdRawMaterialMapper;

    /**
     * 查询原材料
     *
     * @param id 原材料ID
     * @return 原材料
     */
    @Override
    public PdRawMaterial selectPdRawMaterialById(Long id) {
        return pdRawMaterialMapper.selectPdRawMaterialById(id);
    }

    /**
     * 查询原材料列表
     *
     * @param pdRawMaterial 原材料
     * @return 原材料
     */
    @Override
    public List<PdRawMaterial> selectPdRawMaterialList(PdRawMaterial pdRawMaterial) {
        return pdRawMaterialMapper.selectPdRawMaterialList(pdRawMaterial);
    }

    /**
     * 新增原材料
     *
     * @param pdRawMaterial 原材料
     * @return 结果
     */
    @Override
    public int insertPdRawMaterial(PdRawMaterial pdRawMaterial) {
        pdRawMaterial.setCreateTime(DateUtils.getNowDate());
        return pdRawMaterialMapper.insertPdRawMaterial(pdRawMaterial);
    }

    /**
     * 修改原材料
     *
     * @param pdRawMaterial 原材料
     * @return 结果
     */
    @Override
    public int updatePdRawMaterial(PdRawMaterial pdRawMaterial) {
        pdRawMaterial.setUpdateTime(DateUtils.getNowDate());
        return pdRawMaterialMapper.updatePdRawMaterial(pdRawMaterial);
    }

    /**
     * 批量删除原材料
     *
     * @param ids 需要删除的原材料ID
     * @return 结果
     */
    @Override
    public int deletePdRawMaterialByIds(Long[] ids) {
        return pdRawMaterialMapper.deletePdRawMaterialByIds(ids);
    }

    /**
     * 删除原材料信息
     *
     * @param id 原材料ID
     * @return 结果
     */
    @Override
    public int deletePdRawMaterialById(Long id) {
        return pdRawMaterialMapper.deletePdRawMaterialById(id);
    }
}
