package com.wsdm.materiel.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.materiel.domain.SupplierContractChange;
import com.wsdm.materiel.domain.SupplierContractChangeVo;


import java.util.List;

/**
 * 供应商合同变更Service接口
 *
 * @author wanglei
 * @date 2021-04-29
 */
public interface SupplierContractChangeManageService {
    /**
     * 查询供应商合同信息
     *
     * @param id
     * @return SupplierContractChange
     */
    public SupplierContractChangeVo selectSupplierContractChangeById(Long id);

    /**
     * 查询供应商合同信息列表
     *
     * @param supplierContractChange
     * @return List<SupplierContractChange>
     */
    public List<SupplierContractChange> selectSupplierContractChangeList(SupplierContractChange supplierContractChange);

    /**
     * 新增供应商合同信息
     *
     * @param supplierContractChangeVo
     * @return 结果
     */
    public AjaxResult insertSupplierContractChange(SupplierContractChangeVo supplierContractChangeVo);

    /**
     * 修改供应商合同信息
     *
     * @param supplierContractChangeVo
     * @return 结果
     */
    public AjaxResult updateSupplierContractChange(SupplierContractChangeVo supplierContractChangeVo);


    /**
     * 审核供应商合同变更
     *
     * @param id
     * @return 结果
     */
    public AjaxResult updateApproveStatus(Long id, String action);

    /**
     * 批量删除供应商合同信息
     *
     * @param ids
     * @return 结果
     */
    public AjaxResult deleteSupplierContractChangeByIds(Long[] ids);

    /**
     * 删除供应商合同信息
     *
     * @param id
     * @return 结果
     */
    public AjaxResult deleteSupplierContractChangeById(Long id);
}
