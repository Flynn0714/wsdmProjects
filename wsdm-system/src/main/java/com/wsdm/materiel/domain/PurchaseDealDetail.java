package com.wsdm.materiel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wsdm.common.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 采购交易明细
 *
 * @author wsdm
 * @date 2021-01-09
 */
public class PurchaseDealDetail {
    private static final long serialVersionUID = 1L;

    /**
     * 采购日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dealDate;

    /**
     * 交易类型 1-采购收货 2-采购退货
     */
    private String dealType;

    /**
     * 交易类型
     */
    @Excel(name = "交易类型")
    private String dealTypeName;

    /**
     * 交易单号
     */
    @Excel(name = "交易单号")
    private String dealNumber;

    /**
     * 供应商
     */
    @Excel(name = "供应商")
    private String supplier;

    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 供应商名称
     */
    private String supplierName;

    private String purchaseType;

    /**
     * 采购类型 业务字典 dict_purchase_type
     */
    @Excel(name = "采购类型")
    private String purchaseTypeName;

    /**
     * 明细项次
     */
    @Excel(name = "明细项次")
    private String itemNo;

    /**
     * 物料编码
     */
    @Excel(name = "物料编码")
    private String materielNumber;

    /**
     * 物料名称
     */
    @Excel(name = "物料名称")
    private String materielName;

    /**
     * 型号/参数
     */
    @Excel(name = "型号/参数")
    private String modelParam;

    /**
     * 半成品类型
     */
    private String wlType;

    /**
     * 材质
     */
    private String materialQuality;

    public String getWlType() {
        return wlType;
    }

    public void setWlType(String wlType) {
        this.wlType = wlType;
    }


    /**
     * 交易数量
     */
    @Excel(name = "交易数量", isStatistics = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal dealNum;

    /**
     * 采购重量
     */
    @Excel(name = "重量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal weight;

    /**
     * 计价方式 1-按数量计算 2-按重量计算
     */
    private String valuationWay;

    /**
     * 计价方式 1-按数量计算 2-按重量计算
     */
    @Excel(name = "计价方式")
    private String valuationWayDesc;

    /**
     * 交易单价
     */
    @Excel(name = "交易单价")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal unitPrice;

    /**
     * 交易金额
     */
    @Excel(name = "交易金额", isStatistics = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal subtotalAmount;

    /**
     * 交易时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date dealTime;

    public String getDealTypeName() {
        return dealTypeName;
    }

    public void setDealTypeName(String dealTypeName) {
        this.dealTypeName = dealTypeName;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getDealNumber() {
        return dealNumber;
    }

    public void setDealNumber(String dealNumber) {
        this.dealNumber = dealNumber;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getMaterielNumber() {
        return materielNumber;
    }

    public void setMaterielNumber(String materielNumber) {
        this.materielNumber = materielNumber;
    }

    public String getMaterielName() {
        return materielName;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }

    public String getModelParam() {
        return modelParam;
    }

    public void setModelParam(String modelParam) {
        this.modelParam = modelParam;
    }

    public BigDecimal getDealNum() {
        return dealNum;
    }

    public void setDealNum(BigDecimal dealNum) {
        this.dealNum = dealNum;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSubtotalAmount() {
        return subtotalAmount;
    }

    public void setSubtotalAmount(BigDecimal subtotalAmount) {
        this.subtotalAmount = subtotalAmount;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
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

    public String getPurchaseTypeName() {
        return purchaseTypeName;
    }

    public void setPurchaseTypeName(String purchaseTypeName) {
        this.purchaseTypeName = purchaseTypeName;
    }

    public String getMaterialQuality() {
        return materialQuality;
    }

    public void setMaterialQuality(String materialQuality) {
        this.materialQuality = materialQuality;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getValuationWay() {
        return valuationWay;
    }

    public void setValuationWay(String valuationWay) {
        this.valuationWay = valuationWay;
    }

    public String getValuationWayDesc() {
        return valuationWayDesc;
    }

    public void setValuationWayDesc(String valuationWayDesc) {
        this.valuationWayDesc = valuationWayDesc;
    }
}