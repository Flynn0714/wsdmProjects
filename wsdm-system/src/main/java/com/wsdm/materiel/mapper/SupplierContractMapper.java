package com.wsdm.materiel.mapper;

import com.wsdm.materiel.domain.SupplierContract;
import com.wsdm.materiel.domain.SupplierContractVo;

import java.util.List;
import java.util.Map;

/**
 * 供应商合同Mapper接口
 *
 * @author wanglei
 * @date 2021-04-25
 */
public interface SupplierContractMapper {
    /**
     * 查询供应商合同
     *
     * @param id
     * @return SupplierContractVo
     */
    public SupplierContractVo selectSupplierContractById(Long id);

    /**
     * 查询供应商合同信息列表
     *
     * @param supplierContract 物料
     * @return List<SupplierContract>
     */
    public List<SupplierContract> selectSupplierContractList(SupplierContract supplierContract);

    public List<SupplierContractVo> selectSupplierContractVoList(SupplierContractVo supplierContract);

    /**
     * 查询过期供应商合同信息列表
     *
     * @param supplierContract 物料
     * @return List<SupplierContract>
     */
    public List<SupplierContract> selectOverdueSupplierContractList(SupplierContract supplierContract);

    /**
     * 新增供应商合同
     *
     * @param supplierContract
     * @return 结果
     */
    public int insertSupplierContract(SupplierContract supplierContract);

    /**
     * 修改供应商合同
     *
     * @param supplierContract
     * @return 结果
     */
    public int updateSupplierContract(SupplierContract supplierContract);

    /**
     * 审核供应商合同
     *
     * @param supplierContract
     * @return 结果
     */
    public int updateAuditStatus(SupplierContract supplierContract);

    /**
     * 更新供应商合同变更
     *
     * @param supplierContract
     * @return 结果
     */
    public int updateSupplierContractChange(SupplierContract supplierContract);

    /**
     * 删除供应商
     *
     * @param id
     * @return 结果
     */
    public int deleteSupplierContractById(Long id);

    /**
     * 批量删除供应商
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSupplierContractByIds(Long[] ids);

    /**
     * 获取当前最大除供应商编码
     *
     * @param prefix
     * @return 结果
     */
    public String getMaxSupplierContractNumber(String prefix);

    public int updateContractStatusBatch(List<SupplierContract> supplierContracts);

    List<Map<String, Object>> queryPurchaseMaterielList(Map<String, Object> sqlMap);

    List<Map<String, Object>> queryListForAutoPurchase(Map<String, Object> sqlMap);
}
