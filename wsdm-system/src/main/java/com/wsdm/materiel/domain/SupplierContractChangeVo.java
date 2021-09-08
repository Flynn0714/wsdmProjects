package com.wsdm.materiel.domain;

import java.util.List;

/**
 * @author wanglei
 * @date 2021-04-29
 */
public class SupplierContractChangeVo extends SupplierContractChange {
    private static final long serialVersionUID = 1L;

    /**
     * 供应商合同变更明细列表
     */
    private List<SupplierContractChangeDetail> supplierContractChangeDetails;

    /**
     * 操作类型 0 保存（编辑） 1 保存（编辑）提交
     */
    private String optType;

    public List<SupplierContractChangeDetail> getSupplierContractChangeDetails() {
        return supplierContractChangeDetails;
    }

    public void setSupplierContractChangeDetails(List<SupplierContractChangeDetail> supplierContractChangeDetails) {
        this.supplierContractChangeDetails = supplierContractChangeDetails;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }
}
