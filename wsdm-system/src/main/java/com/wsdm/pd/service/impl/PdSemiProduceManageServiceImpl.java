package com.wsdm.pd.service.impl;

import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.pd.domain.PdSemiProduceManage;
import com.wsdm.pd.mapper.PdSemiProduceManageMapper;
import com.wsdm.pd.service.IPdSemiProduceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 半成品管理Service业务层处理
 *
 * @author wsdm
 * @date 2021-06-21
 */
@Service
public class PdSemiProduceManageServiceImpl implements IPdSemiProduceManageService {
    @Autowired
    private PdSemiProduceManageMapper pdSemiProduceManageMapper;

    /**
     * 查询半成品管理
     *
     * @param id 半成品管理ID
     * @return 半成品管理
     */
    @Override
    public PdSemiProduceManage selectPdSemiProduceManageById(Long id) {
        return pdSemiProduceManageMapper.selectPdSemiProduceManageById(id);
    }

    /**
     * 查询半成品管理列表
     *
     * @param pdSemiProduceManage 半成品管理
     * @return 半成品管理
     */
    @Override
    public List<PdSemiProduceManage> selectPdSemiProduceManageList(PdSemiProduceManage pdSemiProduceManage) {
        return pdSemiProduceManageMapper.selectPdSemiProduceManageList(pdSemiProduceManage);
    }

    /**
     * 新增半成品管理
     *
     * @param pdSemiProduceManage 半成品管理
     * @return 结果
     */
    @Override
    public int insertPdSemiProduceManage(PdSemiProduceManage pdSemiProduceManage) {
        pdSemiProduceManage.setCreateTime(DateUtils.getNowDate());
        String pdSemiProduceNumber = pdSemiProduceManage.getSemiProduceNumber();
        PdSemiProduceManage pdSemiProduceManageBySelect = pdSemiProduceManageMapper.selectPdSemiByNumber(pdSemiProduceNumber);
        if (Objects.isNull(pdSemiProduceManageBySelect)) {
            pdSemiProduceManage.setCreateBy(SecurityUtils.getUsername());
            return pdSemiProduceManageMapper.insertPdSemiProduceManage(pdSemiProduceManage);
        } else{
            return 0;
        }
    }
    /**
     * 修改半成品管理
     *
     * @param pdSemiProduceManage 半成品管理
     * @return 结果
     */
    @Override
    public int updatePdSemiProduceManage(PdSemiProduceManage pdSemiProduceManage) {
        pdSemiProduceManage.setUpdateTime(DateUtils.getNowDate());
        return pdSemiProduceManageMapper.updatePdSemiProduceManage(pdSemiProduceManage);
    }

    /**
     * 批量删除半成品管理
     *
     * @param ids 需要删除的半成品管理ID
     * @return 结果
     */
    @Override
    public int deletePdSemiProduceManageByIds(Long[] ids) {
        return pdSemiProduceManageMapper.deletePdSemiProduceManageByIds(ids);
    }

    /**
     * 删除半成品管理信息
     *
     * @param id 半成品管理ID
     * @return 结果
     */
    @Override
    public int deletePdSemiProduceManageById(Long id) {
        return pdSemiProduceManageMapper.deletePdSemiProduceManageById(id);
    }
}
