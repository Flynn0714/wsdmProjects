package com.wsdm.materiel.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.materiel.domain.SupplierContractVo;

import java.util.List;
import java.util.Map;

/**
 * 供应商合同Service接口
 *
 * @author wanglei
 * @date 2021-04-25
 */
public interface SupplierContractManageService {
    /**
     * 查询供应商合同信息
     *
     * @param id
     * @return SupplierContract
     */
    public SupplierContractVo selectSupplierContractById(Long id);

    /**
     * 审核供应商合同
     *
     * @param id
     * @return 结果
     */
    public AjaxResult updateApproveStatus(Long id, String action);

    /**
     * 查询供应商合同信息列表
     *
     * @param supplierContract
     * @return List<SupplierContract>
     */
    public List<SupplierContractVo> selectSupplierContractList(SupplierContractVo supplierContract);

    /**
     * 新增供应商合同信息
     *
     * @param supplierContractVo
     * @return 结果
     */
    public AjaxResult insertSupplierContract(SupplierContractVo supplierContractVo);

    /**
     * 修改供应商合同信息
     *
     * @param supplierContractVo
     * @return 结果
     */
    public AjaxResult updateSupplierContract(SupplierContractVo supplierContractVo);

    /**
     * 批量删除供应商合同信息
     *
     * @param ids
     * @return 结果
     */
    public AjaxResult deleteSupplierContractByIds(Long[] ids);

    /**
     * 删除供应商合同信息
     *
     * @param id
     * @return 结果
     */
    AjaxResult deleteSupplierContractById(Long id);

    AjaxResult result(int result, String errMsg);

    /**
     * 更新供应商合同有效状态
     *
     * @param
     * @return
     */
    void updateSupplierContractStatus();

    List<Map<String,Object>> queryPurchaseMaterielList(Map<String,Object> sqlMap);

    List<Map<String,Object>> queryListForAutoPurchase(Map<String, Object> sqlMap);
}
