package com.wsdm.pd.service;

import com.wsdm.pd.domain.PdAssetsManageRecord;

import java.util.List;

/**
 * 资产管理更新记录Service接口
 *
 * @author wsdm
 * @date 2021-06-28
 */
public interface IPdAssetsManageRecordService {


    /**
     * 查询资产管理更新记录
     *
     * @param id 资产管理更新记录ID
     * @return 资产管理更新记录
     */
    public PdAssetsManageRecord selectPdAssetsManageRecordById(Long id);

    /**
     * 查询资产管理更新记录列表
     * @param pdAssetsManageRecord 资产管理更新记录
     * @return 资产管理更新记录集合
     */

    public List<PdAssetsManageRecord> selectPdAssetsManageRecordList(PdAssetsManageRecord pdAssetsManageRecord);

    /**
     * 新增资产管理更新记录
     *
     * @param pdAssetsManageRecord 资产管理更新记录
     * @return 结果
     */
    public int insertPdAssetsManageRecord(PdAssetsManageRecord pdAssetsManageRecord);

    /**
     * 修改资产管理更新记录
     *
     * @param pdAssetsManageRecord 资产管理更新记录
     * @return 结果
     */
    public int updatePdAssetsManageRecord(PdAssetsManageRecord pdAssetsManageRecord);

    /**
     * 批量删除资产管理
     *
     * @param ids 需要删除的资产管理ID
     * @return 结果
     */
    public int deletePdAssetsManageRecordByIds(Long[] ids);

    /**
     * 删除资产管理信息更新记录
     *
     * @param id 资产管理更新记录ID
     * @return 结果
     */
    public int deletePdAssetsManageRecordById(Long id);
}