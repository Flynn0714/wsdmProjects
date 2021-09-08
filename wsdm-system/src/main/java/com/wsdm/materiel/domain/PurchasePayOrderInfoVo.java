package com.wsdm.materiel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @author wanglei
 * @date 2021-06-26
 */
public class PurchasePayOrderInfoVo extends PurchasePayOrderInfo {

    private static final long serialVersionUID = 1L;

    List<PurchasePayOrderDetail> purchasePayOrderDetails;

    List<PurchaseDealDetail> purchaseDealDetails;

    /**
     * 付款方式  字典 dict_pay_way
     */
    private String payWay;

    /**
     * 付款日期查询开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date payStartDate;

    /**
     * 付款日期查询结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date payEndDate;

    /**
     * 交易日期查询开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dealStartDate;

    /**
     * 交易日期查询结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dealEndDate;

    private String dealType;

    private String purchaseType;

    private String dealNumber;

    /**
     * 物料编码
     */
    private String materielNumber;

    /**
     * 物料名称
     */
    private String materielName;

    public List<PurchasePayOrderDetail> getPurchasePayOrderDetails() {
        return purchasePayOrderDetails;
    }

    public void setPurchasePayOrderDetails(List<PurchasePayOrderDetail> purchasePayOrderDetails) {
        this.purchasePayOrderDetails = purchasePayOrderDetails;
    }

    public List<PurchaseDealDetail> getPurchaseDealDetails() {
        return purchaseDealDetails;
    }

    public void setPurchaseDealDetails(List<PurchaseDealDetail> purchaseDealDetails) {
        this.purchaseDealDetails = purchaseDealDetails;
    }

    public Date getPayStartDate() {
        return payStartDate;
    }

    public void setPayStartDate(Date payStartDate) {
        this.payStartDate = payStartDate;
    }

    public Date getPayEndDate() {
        return payEndDate;
    }

    public void setPayEndDate(Date payEndDate) {
        this.payEndDate = payEndDate;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public Date getDealStartDate() {
        return dealStartDate;
    }

    public void setDealStartDate(Date dealStartDate) {
        this.dealStartDate = dealStartDate;
    }

    public Date getDealEndDate() {
        return dealEndDate;
    }

    public void setDealEndDate(Date dealEndDate) {
        this.dealEndDate = dealEndDate;
    }

    public String getDealNumber() {
        return dealNumber;
    }

    public void setDealNumber(String dealNumber) {
        this.dealNumber = dealNumber;
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

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }
}
