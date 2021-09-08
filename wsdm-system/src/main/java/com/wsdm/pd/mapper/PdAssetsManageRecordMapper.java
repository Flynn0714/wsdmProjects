package com.wsdm.pd.mapper;

import com.wsdm.pd.domain.PdAssetsManageRecord;

import java.util.List;

/**
 * 资产管理更新记录Mapper接口
 *
 * @author wsdm
 * @date 2021-06-28
 */
public interface PdAssetsManageRecordMapper {
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
     * @return 集合
     */
    public List<PdAssetsManageRecord> selectPdAssetsManageRecordList(PdAssetsManageRecord pdAssetsManageRecord);

    /**
     * 新增资产管理更新记录
     * @param pdAssetsManageRecord 资产管理更新记录
     * @return 结果
     */
    public int insertPdAssetsManageRecord(PdAssetsManageRecord pdAssetsManageRecord);

    /**
     * 修改资产管理更新记录
     * @param pdAssetsManageRecord 资产管理更新记录
     * @return
     */
    public int updatePdAssetsManageRecord(PdAssetsManageRecord pdAssetsManageRecord);

    /**
     * 删除资产管理更新记录
     *
     * @param id 资产管理更新记录ID
     * @return 结果
     */
    public int deletePdAssetsManageRecordById(Long id);

    /**
     * 批量删除资产管理更新记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePdAssetsManageRecordByIds(Long[] ids);

}