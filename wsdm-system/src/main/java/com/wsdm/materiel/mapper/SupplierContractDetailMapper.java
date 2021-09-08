package com.wsdm.materiel.mapper;


import com.wsdm.materiel.domain.SupplierContractDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商合同明细Mapper接口
 *
 * @author wanglei
 * @date 2021-04-25
 */
public interface SupplierContractDetailMapper {
    /**
     * 查询供应商合同明细
     *
     * @param id
     * @return SupplierContractDetail
     */
    public SupplierContractDetail selectSupplierContractDetailById(Long id);

    /**
     * 根据合同编号查询供应商合同明细列表
     *
     * @param contractNumber 合同编号
     * @return List<SupplierContractDetail>
     */
    public List<SupplierContractDetail> selectSupplierContractDetailList(String contractNumber);

    /**
     * 新增供应商合同明细
     *
     * @param supplierContractDetail
     * @return 结果
     */
    public int insertSupplierContractDetail(SupplierContractDetail supplierContractDetail);

    /**
     * 修改供应商合同明细
     *
     * @param supplierContractDetail
     * @return 结果
     */
    public int updateSupplierContractDetail(SupplierContractDetail supplierContractDetail);

    /**
     * 删除供应商合同明细
     *
     * @param id
     * @return 结果
     */
    public int deleteSupplierContractDetailById(Long id);

    /**
     * 删除供应商合同明细
     *
     * @param contractNumber
     * @return 结果
     */
    public int deleteSupplierContractDetailByContractNumber(String contractNumber);

    /**
     * 批量删除供应商合同明细
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSupplierContractDetailByIds(Long[] ids);

    /**
     * 查詢合同重複（供应商+物料）数量
     *
     * @param materialNumber
     * @param supplierCode
     * @param contractNumber
     * @return 结果
     */
    public int selectRepeatDetailCount(@Param("materialNumber") String materialNumber, @Param("supplierCode") String supplierCode, @Param("contractNumber") String contractNumber);

    /**
     * 批量新增供应商合同明细
     *
     * @param list
     * @return 结果
     */
    int insertSupplierContractDetailBatch(List<SupplierContractDetail> list);
}
