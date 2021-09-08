package com.wsdm.materiel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 物料采购订单信息对象 wl_purchase_order_info
 *
 * @author wsdm
 * @date 2020-11-26
 */
public class PurchaseOrderInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 采购单号
     */
    @Excel(name = "采购单号")
    private String purchaseNumber;

    /**
     * 供应商编码
     */
    @Excel(name = "供应商编码")
    private String supplierCode;

    /**
     * 供应商名称
     */
    @Excel(name = "供应商名称")
    private String supplierName;

    /**
     * 联系人
     */
    @Excel(name = "联系人")
    private String contacts;

    /**
     * 联系电话
     */
    @Excel(name = "联系电话")
    private String contactNumber;

    /**
     * 采购日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "采购日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purchaseDate;

    /**
     * 预计到货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计到货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expectArrivalDate;

    /**
     * 收货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date arrivalDate;

    /**
     * 采购类型 业务字典 dict_purchase_type
     */
    @Excel(name = "采购类型 原料采购、五金采购、外协采购")
    private String purchaseType;

    /**
     * 采购类型 业务字典 dict_purchase_type
     */
    @Excel(name = "采购类型 原料采购、五金采购、外协采购")
    private String purchaseTypeName;

    /**
     * 订货数量
     */
    @Excel(name = "订货数量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal totalPurchaseNum;

    /**
     * 已收貨数量
     */
    @Excel(name = "已收貨数量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal totalReceivedNum;

    /**
     * 采购总价
     */
    @Excel(name = "采购总价")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal totalPurchaseAmount;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private String status;

    /** 批号 */
    private String batchNumber;

    /**
     * 状态
     */
    @Excel(name = "状态名称")
    private String statusName;

    /**
     * 收貨状态
     */
    @Excel(name = "状态名称")
    private String receiveStatusName;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditTime;

    /**
     * 审核人
     */
    private String auditBy;

    public BigDecimal getTotalReceivedNum() {
        return totalReceivedNum;
    }

    public void setTotalReceivedNum(BigDecimal totalReceivedNum) {
        this.totalReceivedNum = totalReceivedNum;
    }

    public String getReceiveStatusName() {
        return receiveStatusName;
    }

    public void setReceiveStatusName(String receiveStatusName) {
        this.receiveStatusName = receiveStatusName;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditBy() {
        return auditBy;
    }

    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setExpectArrivalDate(Date expectArrivalDate) {
        this.expectArrivalDate = expectArrivalDate;
    }

    public Date getExpectArrivalDate() {
        return expectArrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
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

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getPurchaseTypeName() {
        return purchaseTypeName;
    }

    public void setPurchaseTypeName(String purchaseTypeName) {
        this.purchaseTypeName = purchaseTypeName;
    }

    public BigDecimal getTotalPurchaseNum() {
        return totalPurchaseNum;
    }

    public void setTotalPurchaseNum(BigDecimal totalPurchaseNum) {
        this.totalPurchaseNum = totalPurchaseNum;
    }

    public BigDecimal getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    public void setTotalPurchaseAmount(BigDecimal totalPurchaseAmount) {
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("purchaseNumber", getPurchaseNumber())
                .append("supplierCode", getSupplierCode())
                .append("supplierName", getSupplierName())
                .append("contacts", getContacts())
                .append("contactNumber", getContactNumber())
                .append("purchaseDate", getPurchaseDate())
                .append("expectArrivalDate", getExpectArrivalDate())
                .append("arrivalDate", getArrivalDate())
                .append("purchaseType", getPurchaseType())
                .append("totalPurchaseNum", getTotalPurchaseNum())
                .append("totalPurchaseAmount", getTotalPurchaseAmount())
                .append("remark", getRemark())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
