package com.wsdm.materiel.mapper;


import com.wsdm.materiel.domain.SupplierContractChange;
import com.wsdm.materiel.domain.SupplierContractChangeVo;

import java.util.List;

/**
 * 供应商合同变更Mapper接口
 *
 * @author wanglei
 * @date 2021-04-29
 */
public interface SupplierContractChangeMapper {
    /**
     * 查询供应商合同变更详情
     *
     * @param id
     * @return SupplierContractChangeVo
     */
    public SupplierContractChangeVo selectSupplierContractChangeById(Long id);

    /**
     * 查询供应商合同变更信息列表
     *
     * @param supplierContractChange 物料
     * @return List<SupplierContractChange>
     */
    public List<SupplierContractChange> selectSupplierContractChangeList(SupplierContractChange supplierContractChange);

    /**
     * 新增供应商合同变更
     *
     * @param supplierContractChange
     * @return 结果
     */
    public int insertSupplierContractChange(SupplierContractChange supplierContractChange);

    /**
     * 修改供应商合同变更
     *
     * @param supplierContractChange
     * @return 结果
     */
    public int updateSupplierContractChange(SupplierContractChange supplierContractChange);

    /**
     * 审核供应商合同变更
     *
     * @param supplierContractChange
     * @return 结果
     */
    public int updateAuditStatus(SupplierContractChange supplierContractChange);

    /**
     * 删除供应商合同变更
     *
     * @param id
     * @return 结果
     */
    public int deleteSupplierContractChangeById(Long id);

    /**
     * 批量删除供应商合同变更
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSupplierContractChangeByIds(Long[] ids);

    /**
     * 获取当前最大供应商合同变更编号
     *
     * @param prefix
     * @return 结果
     */
    public String getMaxSupplierContractChangeNumber(String prefix);
}
