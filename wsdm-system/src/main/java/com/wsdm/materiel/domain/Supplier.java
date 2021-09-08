package com.wsdm.materiel.domain;

import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 物料对象 wl_supplier
 *
 * @author wsdm
 * @date 2020-11-06
 */
public class Supplier extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 供应商类型 业务字典 dict_purchase_type
     */
    private String supplierType;

    /**
     * 供应商类型名称 业务字典
     */
    private String supplierTypeName;

    /**
     * 供应商等级 业务字典 dict_supplier_level
     */
    private String supplierLevel;

    /**
     * 供应商等级 业务字典 dict_supplier_level
     */
    private String supplierLevelName;

    /**
     * 供应商等级
     */
    private String supplierCredit;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 手机号
     */
    private String contactNumber;

    /**
     * 邮件
     */
    private String supplierEmail;

    /**
     * 传真
     */
    private String supplierFax;

    private String address;

    /**
     * 状态 0 正常 1 已删除
     */
    private String status;

    private String statusName;

    /**
     * 备注
     */
    private String supplierRemark;

    public String getSupplierTypeName() {
        return supplierTypeName;
    }

    public void setSupplierTypeName(String supplierTypeName) {
        this.supplierTypeName = supplierTypeName;
    }

    public String getSupplierLevelName() {
        return supplierLevelName;
    }

    public void setSupplierLevelName(String supplierLevelName) {
        this.supplierLevelName = supplierLevelName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierLevel(String supplierLevel) {
        this.supplierLevel = supplierLevel;
    }

    public String getSupplierLevel() {
        return supplierLevel;
    }

    public void setSupplierCredit(String supplierCredit) {
        this.supplierCredit = supplierCredit;
    }

    public String getSupplierCredit() {
        return supplierCredit;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierFax(String supplierFax) {
        this.supplierFax = supplierFax;
    }

    public String getSupplierFax() {
        return supplierFax;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setSupplierRemark(String supplierRemark) {
        this.supplierRemark = supplierRemark;
    }

    public String getSupplierRemark() {
        return supplierRemark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("supplierCode", getSupplierCode())
                .append("supplierName", getSupplierName())
                .append("supplierType", getSupplierType())
                .append("supplierTypeName", getSupplierTypeName())
                .append("supplierLevel", getSupplierLevel())
                .append("supplierLevelName", getSupplierLevelName())
                .append("supplierCredit", getSupplierCredit())
                .append("contacts", getContacts())
                .append("contactNumber", getContactNumber())
                .append("supplierEmail", getSupplierEmail())
                .append("supplierFax", getSupplierFax())
                .append("status", getStatus())
                .append("statusName", getStatusName())
                .append("supplierRemark", getSupplierRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
