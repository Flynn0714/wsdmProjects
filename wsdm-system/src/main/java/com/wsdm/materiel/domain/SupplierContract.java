package com.wsdm.materiel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 采购供应商合同对象 purchase_supplier_contract_info
 *
 * @author wanglei
 * @date 2021-04-25
 */
public class SupplierContract extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;

    /**
     * 供应商合同编号
     */
    private String contractNumber;

    /**
     * 合同类型 业务字典 dict_purchase_type
     */
    private String contractType;

    /**
     * 合同类型名称 业务字典 dict_purchase_type
     */
    private String contractTypeName;

    /**
     * 合同生效日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date contractStartDate;

    /**
     * 合同结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date contractEndDate;

    /**
     * 合同状态 0 未生效 1 待生效 2 生效中 3 已过期 4 已作废 字典 dict_contract_status
     */
    private String contractStatus;

    /**
     * 合同状态名称 0 未生效 1 待生效 2 生效中 3 已过期 4 已作废 字典 dict_contract_status
     */
    private String contractStatusName;

    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 审核状态 0 未提交 1 待审核 2 已审核 3 已删除 字典 dict_contract_audit_status
     */
    private String auditStatus;

    /**
     * 审核状态名称 0 未提交 1 待审核 2 已审核 3 已删除 字典 dict_contract_audit_status
     */
    private String auditStatusName;

    /**
     * 审核人
     */
    private String auditBy;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Date getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getContractTypeName() {
        return contractTypeName;
    }

    public void setContractTypeName(String contractTypeName) {
        this.contractTypeName = contractTypeName;
    }

    public String getContractStatusName() {
        return contractStatusName;
    }

    public void setContractStatusName(String contractStatusName) {
        this.contractStatusName = contractStatusName;
    }

    public String getAuditStatusName() {
        return auditStatusName;
    }

    public void setAuditStatusName(String auditStatusName) {
        this.auditStatusName = auditStatusName;
    }

    public String getAuditBy() {
        return auditBy;
    }

    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("contractNumber", getContractNumber())
                .append("contractType", getContractType())
                .append("contractTypeName", getContractTypeName())
                .append("contractStartDate", getContractStartDate())
                .append("contractEndDate", getContractEndDate())
                .append("contractStatus", getContractStatus())
                .append("contractStatusName", getContractStatusName())
                .append("supplierCode", getSupplierCode())
                .append("supplierName", getSupplierName())
                .append("auditStatus", getAuditStatus())
                .append("auditBy", getAuditBy())
                .append("auditStatusName", getAuditStatusName())
                .append("auditTime", getAuditTime())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
