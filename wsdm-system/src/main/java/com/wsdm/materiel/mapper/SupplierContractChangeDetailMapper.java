package com.wsdm.materiel.mapper;



import com.wsdm.materiel.domain.SupplierContractChangeDetail;

import java.util.List;

/**
 * 供应商合同变更明细Mapper接口
 *
 * @author wanglei
 * @date 2021-04-29
 */
public interface SupplierContractChangeDetailMapper {
    /**
     * 查询供应商合同变更明细
     *
     * @param id
     * @return SupplierContractChangeDetail
     */
    public SupplierContractChangeDetail selectSupplierContractChangeDetailById(Long id);

    /**
     * 根据变更编号查询供应商合同变更明细列表
     *
     * @param changeNumber 合同编号
     * @return List<SupplierContractChangeDetail>
     */
    public List<SupplierContractChangeDetail> selectSupplierContractChangeDetailList(String changeNumber);

    /**
     * 新增供应商合同变更明细
     *
     * @param supplierContractChangeDetail
     * @return 结果
     */
    public int insertSupplierContractChangeDetail(SupplierContractChangeDetail supplierContractChangeDetail);

    /**
     * 修改供应商合同变更明细
     *
     * @param supplierContractChangeDetail
     * @return 结果
     */
    public int updateSupplierContractChangeDetail(SupplierContractChangeDetail supplierContractChangeDetail);

    /**
     * 删除供应商合同变更明细
     *
     * @param id
     * @return 结果
     */
    public int deleteSupplierContractChangeDetailById(Long id);

    /**
     * 删除供应商合同变更明细
     *
     * @param changeNumber
     * @return 结果
     */
    public int deleteSupplierContractChangeDetailByChangeNumber(String changeNumber);

    /**
     * 批量删除供应商合同变更明细
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSupplierContractChangeDetailByIds(Long[] ids);

    /**
     * 批量新增供应商合同变更明细
     *
     * @param list
     * @return 结果
     */
    int insertSupplierContractChangeDetailBatch(List<SupplierContractChangeDetail> list);
}
