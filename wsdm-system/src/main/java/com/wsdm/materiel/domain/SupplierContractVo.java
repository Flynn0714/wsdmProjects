package com.wsdm.materiel.domain;

import java.util.Date;
import java.util.List;

/**
 * @author wanglei
 * @date 2021-04-26
 */
public class SupplierContractVo extends SupplierContract {
    private static final long serialVersionUID = 1L;

    /**
     * 供应商合同采购物料明细列表
     */
    private List<SupplierContractDetail> supplierContractDetails;

    /**
     * 合同有效日期
     */
    private Date contractEffectiveDate;

    /**
     * 操作类型 0 保存（编辑） 1 保存（编辑）提交
     */
    private String optType;

    public List<SupplierContractDetail> getSupplierContractDetails() {
        return supplierContractDetails;
    }

    public void setSupplierContractDetails(List<SupplierContractDetail> supplierContractDetails) {
        this.supplierContractDetails = supplierContractDetails;
    }

    public Date getContractEffectiveDate() {
        return contractEffectiveDate;
    }

    public void setContractEffectiveDate(Date contractEffectiveDate) {
        this.contractEffectiveDate = contractEffectiveDate;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }
}
