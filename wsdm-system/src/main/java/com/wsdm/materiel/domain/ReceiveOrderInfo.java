package com.wsdm.materiel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 物料收货订单信息对象 wl_receive_order_info
 *
 * @author wsdm
 * @date 2021-01-09
 */
public class ReceiveOrderInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 收货单号
     */
    @Excel(name = "收货单号")
    private String receiveNumber;

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
     * 收货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date arrivalDate;

    /**
     * 收货人
     */
    @Excel(name = "收货人")
    private String consignee;

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
     * 收货数量
     */
    @Excel(name = "收货数量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal totalReceiveNum;

    /**
     * 是否需要质检 0-需要 1-不需要
     */
    private String isCheck;

    /**
     * 是否需要质检 0-需要 1-不需要
     */
    private String isCheckDesc;

    /**
     * 质检单号
     */
    private String checkNumber;

    /**
     * 质检数量
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal checkNum;

    /**
     * 合格数量
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal qualifiedNum;

    /**
     * 状态 0-未提交 1-待质检 2-待审核 3-已审核 字典 dict_purchase_receive_status
     */
    @Excel(name = "状态")
    private String status;


    /**
     * 状态
     */
    @Excel(name = "状态名称")
    private String statusName;

    /**
     * 审核人
     */
    private String auditBy;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal totalPaidAmount;

    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal totalReceiveAmount;

    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal totalOutstandingAmount;

    public BigDecimal getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(BigDecimal totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public BigDecimal getTotalReceiveAmount() {
        return totalReceiveAmount;
    }

    public void setTotalReceiveAmount(BigDecimal totalReceiveAmount) {
        this.totalReceiveAmount = totalReceiveAmount;
    }

    public BigDecimal getTotalOutstandingAmount() {
        return totalOutstandingAmount;
    }

    public void setTotalOutstandingAmount(BigDecimal totalOutstandingAmount) {
        this.totalOutstandingAmount = totalOutstandingAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiveNumber() {
        return receiveNumber;
    }

    public void setReceiveNumber(String receiveNumber) {
        this.receiveNumber = receiveNumber;
    }

    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
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

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
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

    public BigDecimal getTotalReceiveNum() {
        return totalReceiveNum;
    }

    public void setTotalReceiveNum(BigDecimal totalReceiveNum) {
        this.totalReceiveNum = totalReceiveNum;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getIsCheckDesc() {
        return isCheckDesc;
    }

    public void setIsCheckDesc(String isCheckDesc) {
        this.isCheckDesc = isCheckDesc;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public BigDecimal getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(BigDecimal checkNum) {
        this.checkNum = checkNum;
    }

    public BigDecimal getQualifiedNum() {
        return qualifiedNum;
    }

    public void setQualifiedNum(BigDecimal qualifiedNum) {
        this.qualifiedNum = qualifiedNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getAuditBy() {
        return auditBy;
    }

    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
}
