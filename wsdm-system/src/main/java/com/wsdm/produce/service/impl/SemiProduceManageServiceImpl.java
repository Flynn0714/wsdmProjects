package com.wsdm.produce.service.impl;

import com.google.common.base.Strings;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.ResultEnums;
import com.wsdm.common.enums.WisdomBusinessStatus;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.produce.domain.SemiProduceManage;
import com.wsdm.produce.mapper.SemiProduceManageMapper;
import com.wsdm.produce.service.ISemiProduceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 半成品管理Service业务层处理
 *
 * @author wsdm
 * @date 2020-11-06
 */
@Service
public class SemiProduceManageServiceImpl implements ISemiProduceManageService {
    @Autowired
    private SemiProduceManageMapper semiProduceManageMapper;

    /**
     * 查询半成品管理
     *
     * @param id 半成品管理ID
     * @return 半成品管理
     */
    @Override
    public SemiProduceManage selectSemiProduceManageById(Long id) {
        return semiProduceManageMapper.selectSemiProduceManageById(id);
    }

    /**
     * 查询半成品管理列表
     *
     * @param semiProduceManage 半成品管理
     * @return 半成品管理
     */
    @Override
    public List<SemiProduceManage> selectSemiProduceManageList(SemiProduceManage semiProduceManage) {
        return semiProduceManageMapper.selectSemiProduceManageList(semiProduceManage);
    }

    /**
     * 新增半成品管理
     *
     * @param semiProduceManage 半成品管理
     * @return 结果
     */
    @Override
    public AjaxResult insertSemiProduceManage(SemiProduceManage semiProduceManage) {

        if (Strings.isNullOrEmpty(semiProduceManage.getSemiProduceModel())) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        if(semiProduceManage.getMaterialQuality() == null){
            semiProduceManage.setMaterialQuality("0");
        }
        if(semiProduceManage.getModulusToothNumber() == null){
            semiProduceManage.setModulusToothNumber("0");
        }
        if(semiProduceManage.getSpecifications() == null){
            semiProduceManage.setSpecifications("0");
        }
            semiProduceManage.setSemiProduceNumber(semiProduceManage.getSemiProduceModel()
                    + "_" + semiProduceManage.getModulusToothNumber()
                    + "_" + semiProduceManage.getSpecifications()
                    + "." + semiProduceManage.getMaterialQuality());

        SemiProduceManage semiProduceManage1 = semiProduceManageMapper.getSemiInfoBySemiNumber(semiProduceManage.getSemiProduceNumber());
        if (semiProduceManage1 != null && semiProduceManage1.getId() != null) {
            return AjaxResult.error(ResultEnums.SEMI_EXIST);
        }

        semiProduceManage.setCreateTime(DateUtils.getNowDate());
        semiProduceManage.setCreateBy(SecurityUtils.getUsername());
        semiProduceManage.setStatus(WisdomBusinessStatus.STATUS_NORMAL.getCode());
        int result = semiProduceManageMapper.insertSemiProduceManage(semiProduceManage);
        return result > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 修改半成品管理
     *
     * @param semiProduceManage 半成品管理
     * @return 结果
     */
    @Override
    public AjaxResult updateSemiProduceManage(SemiProduceManage semiProduceManage) {

        if (Strings.isNullOrEmpty(semiProduceManage.getSemiProduceModel())) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        if(semiProduceManage.getMaterialQuality() == null){
            semiProduceManage.setMaterialQuality("0");
        }
        if(semiProduceManage.getModulusToothNumber() == null){
            semiProduceManage.setModulusToothNumber("0");
        }
        if(semiProduceManage.getSpecifications() == null){
            semiProduceManage.setSpecifications("0");
        }
        semiProduceManage.setSemiProduceNumber(semiProduceManage.getSemiProduceModel()
                + "_" + semiProduceManage.getModulusToothNumber()
                + "_" + semiProduceManage.getSpecifications()
                + "." + semiProduceManage.getMaterialQuality());

        SemiProduceManage semi = semiProduceManageMapper.getSemiInfoBySemiNumber(semiProduceManage.getSemiProduceNumber());
        if (semi != null && semi.getId() != null && !semi.getSemiProduceNumber().equals(semiProduceManage.getSemiProduceNumber())) {
            return AjaxResult.error(ResultEnums.SEMI_EXIST);
        }

        semiProduceManage.setUpdateTime(DateUtils.getNowDate());
        semiProduceManage.setUpdateBy(SecurityUtils.getUsername());
        int result = semiProduceManageMapper.updateSemiProduceManage(semiProduceManage);
        return result > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 批量删除半成品管理
     *
     * @param ids 需要删除的半成品管理ID
     * @return 结果
     */
    @Override
    public int deleteSemiProduceManageByIds(Long[] ids) {
        return semiProduceManageMapper.deleteSemiProduceManageByIds(ids);
    }

    /**
     * 删除半成品管理信息
     *
     * @param id 半成品管理ID
     * @return 结果
     */
    @Override
    public int deleteSemiProduceManageById(Long id) {
        return semiProduceManageMapper.deleteSemiProduceManageById(id);
    }
}
