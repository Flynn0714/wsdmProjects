package com.wsdm.pd.service.impl;

import com.wsdm.pd.domain.PdAssetsManageRecord;
import com.wsdm.pd.mapper.PdAssetsManageRecordMapper;
import com.wsdm.pd.service.IPdAssetsManageRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资产管理Service业务层处理
 *
 * @author wsdm
 * @date 2021-06-28
 */
@Service
public class PdAssetsManageRecordServiceImpl implements IPdAssetsManageRecordService{

    @Autowired
    private PdAssetsManageRecordMapper pdAssetsManageRecordMapper;

    /**
     * 查询资产管理更新记录
     *
     * @param id 资产管理更新记录ID
     * @return 资产管理更新记录
     */
    @Override
    public PdAssetsManageRecord selectPdAssetsManageRecordById(Long id) {
        return pdAssetsManageRecordMapper.selectPdAssetsManageRecordById(id);
    }

    /**
     * 查询资产管理更新记录列表
     *
     * @param pdAssetsManageRecord 资产管理更新记录
     * @return 资产管理更新记录集合
     */
    @Override
    public List<PdAssetsManageRecord> selectPdAssetsManageRecordList(PdAssetsManageRecord pdAssetsManageRecord) {
        return pdAssetsManageRecordMapper.selectPdAssetsManageRecordList(pdAssetsManageRecord);
    }

    /**
     * 新增资产管理更新记录
     *
     * @param pdAssetsManageRecord 资产管理更新记录
     * @return 结果
     */
    @Override
    public int insertPdAssetsManageRecord(PdAssetsManageRecord pdAssetsManageRecord) {
        return pdAssetsManageRecordMapper.insertPdAssetsManageRecord(pdAssetsManageRecord);
    }

    /**
     * 修改资产管理更新记录
     *
     * @param pdAssetsManageRecord 资产管理更新记录
     * @return 结果
     */
    @Override
    public int updatePdAssetsManageRecord(PdAssetsManageRecord pdAssetsManageRecord) {
        return pdAssetsManageRecordMapper.updatePdAssetsManageRecord(pdAssetsManageRecord);
    }

    /**
     * 批量删除资产管理
     *
     * @param ids 需要删除的资产管理ID
     * @return 结果
     */
    @Override
    public int deletePdAssetsManageRecordByIds(Long[] ids) {
        return pdAssetsManageRecordMapper.deletePdAssetsManageRecordByIds(ids);
    }

    /**
     * 删除资产管理信息更新记录
     *
     * @param id 资产管理更新记录ID
     * @return 结果
     */
    @Override
    public int deletePdAssetsManageRecordById(Long id) {
        return pdAssetsManageRecordMapper.deletePdAssetsManageRecordById(id);
    }
}