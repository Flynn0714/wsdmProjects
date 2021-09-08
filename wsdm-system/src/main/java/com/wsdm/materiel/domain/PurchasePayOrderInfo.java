package com.wsdm.materiel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 物料采购付款单信息对象 wl_purchase_supplier_pay_info
 *
 * @author wanglei
 * @date 2021-06-26
 */
public class PurchasePayOrderInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;
    /**
     * 供应商
     */
    @Excel(name = "供应商编号")
    private String supplierCode;

    @Excel(name = "供应商名称")
    private String supplierName;

    /**
     * 供应商类型名称 业务字典
     */
    @Excel(name = "供应商类型")
    private String supplierTypeName;

    /**
     * 供应商等级 业务字典 dict_supplier_level
     */
    @Excel(name = "供应商级别")
    private String supplierLevelName;

    /**
     * 联系人
     */
    @Excel(name = "联系人")
    private String contacts;

    /**
     * 手机号
     */
    @Excel(name = "联系电话")
    private String contactNumber;

    /**
     * 收款总金额
     */
    @Excel(name = "累计交易金额")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal totalTransactionAmount;

    /**
     * 付款金额
     */
    @Excel(name = "累计付款金额")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal totalPayAmount;

    /**
     * 应付货款
     */
    @Excel(name = "应付货款")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal payableAmount;

    /**
     * 最后付款人
     */
    @Excel(name = "最后付款人")
    private String lastPayer;

    /**
     * 最后付款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "最后付款时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date lastPayTime;

    /**
     * 最后交易时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "最后交易时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date lastTradingTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getTotalTransactionAmount() {
        return totalTransactionAmount;
    }

    public void setTotalTransactionAmount(BigDecimal totalTransactionAmount) {
        this.totalTransactionAmount = totalTransactionAmount;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public BigDecimal getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(BigDecimal payableAmount) {
        this.payableAmount = payableAmount;
    }

    public String getLastPayer() {
        return lastPayer;
    }

    public void setLastPayer(String lastPayer) {
        this.lastPayer = lastPayer;
    }

    public Date getLastPayTime() {
        return lastPayTime;
    }

    public void setLastPayTime(Date lastPayTime) {
        this.lastPayTime = lastPayTime;
    }

    public Date getLastTradingTime() {
        return lastTradingTime;
    }

    public void setLastTradingTime(Date lastTradingTime) {
        this.lastTradingTime = lastTradingTime;
    }
}