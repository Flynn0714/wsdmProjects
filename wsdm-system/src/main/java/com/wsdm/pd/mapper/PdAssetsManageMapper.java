package com.wsdm.pd.mapper;

import com.wsdm.pd.domain.PdAssetsManage;

import java.util.List;

/**
 * 资产管理Mapper接口
 *
 * @author wsdm
 * @date 2021-06-28
 */
public interface PdAssetsManageMapper {
    /**
     * 查询资产管理
     *
     * @param id 资产管理ID
     * @return 资产管理
     */
    public PdAssetsManage selectPdAssetsManageById(Long id);

    /**
     * 查询资产管理列表
     *
     * @param pdAssetsManage 资产管理
     * @return 资产管理集合
     */
    public List<PdAssetsManage> selectPdAssetsManageList(PdAssetsManage pdAssetsManage);

    /**
     * 新增资产管理
     *
     * @param pdAssetsManage 资产管理
     * @return 结果
     */
    public int insertPdAssetsManage(PdAssetsManage pdAssetsManage);

    /**
     * 修改资产管理
     *
     * @param pdAssetsManage 资产管理
     * @return 结果
     */
    public int updatePdAssetsManage(PdAssetsManage pdAssetsManage);

    /**
     * 删除资产管理
     *
     * @param id 资产管理ID
     * @return 结果
     */
    public int deletePdAssetsManageById(Long id);

    /**
     * 批量删除资产管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePdAssetsManageByIds(Long[] ids);

    public String getMaxCustomerCode(String code);

}