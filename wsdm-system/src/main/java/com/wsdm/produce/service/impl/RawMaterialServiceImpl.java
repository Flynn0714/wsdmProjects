package com.wsdm.produce.service.impl;

import com.wsdm.common.enums.WisdomBusinessStatus;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.produce.domain.RawMaterial;
import com.wsdm.produce.mapper.RawMaterialMapper;
import com.wsdm.produce.service.IRawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 原材料Service业务层处理
 *
 * @author wsdm
 * @date 2020-11-06
 */
@Service
public class RawMaterialServiceImpl implements IRawMaterialService {
    @Autowired
    private RawMaterialMapper rawMaterialMapper;

    /**
     * 查询原材料
     *
     * @param id 原材料ID
     * @return 原材料
     */
    @Override
    public RawMaterial selectRawMaterialById(Long id) {
        return rawMaterialMapper.selectRawMaterialById(id);
    }

    /**
     * 查询原材料列表
     *
     * @param rawMaterial 原材料
     * @return 原材料
     */
    @Override
    public List<RawMaterial> selectRawMaterialList(RawMaterial rawMaterial) {
        return rawMaterialMapper.selectRawMaterialList(rawMaterial);
    }

    /**
     * 新增原材料
     *
     * @param rawMaterial 原材料
     * @return 结果
     */
    @Override
    public int insertRawMaterial(RawMaterial rawMaterial) {
        rawMaterial.setCreateTime(DateUtils.getNowDate());
        rawMaterial.setStatus(WisdomBusinessStatus.STATUS_NORMAL.getCode());
        return rawMaterialMapper.insertRawMaterial(rawMaterial);
    }

    /**
     * 修改原材料
     *
     * @param rawMaterial 原材料
     * @return 结果
     */
    @Override
    public int updateRawMaterial(RawMaterial rawMaterial) {
        rawMaterial.setUpdateTime(DateUtils.getNowDate());
        return rawMaterialMapper.updateRawMaterial(rawMaterial);
    }

    /**
     * 批量删除原材料
     *
     * @param ids 需要删除的原材料ID
     * @return 结果
     */
    @Override
    public int deleteRawMaterialByIds(Long[] ids) {
        return rawMaterialMapper.deleteRawMaterialByIds(ids);
    }

    /**
     * 删除原材料信息
     *
     * @param id 原材料ID
     * @return 结果
     */
    @Override
    public int deleteRawMaterialById(Long id) {
        return rawMaterialMapper.deleteRawMaterialById(id);
    }
}
