package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wsdm.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 销售退货
 */
public class SalesGoodsReject extends BaseEntity implements Serializable {

    private Long id;

    /**
     * 销售单号
     */
    private String salesNumber;


    /**
     * 退货单号
     */
    private String returnNumber;

    /**
     * 客户
     */
    private String customer;

    /**
     * 发货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDate;

    /**
     * 发货数量
     */
    private BigDecimal deliveredQuantity;

    /**
     * 退货数量
     */
    private BigDecimal returnQuantity;

    /**
     * 退货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;

    /**
     * 退货金额
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal returnAmount;

    /**
     * 状态
     */
    private String returnStatus;

    /**
     * 审核人
     */
    private String approve;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date approveTime;

    /**
     * 提审人
     */
    private String auditor;

    /**
     * 提审时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:SS")
    private Date audit_time;

    private String returnStatusName;

    public String getReturnStatusName() {
        return returnStatusName;
    }

    public void setReturnStatusName(String returnStatusName) {
        this.returnStatusName = returnStatusName;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Date getAudit_time() {
        return audit_time;
    }

    public void setAudit_time(Date audit_time) {
        this.audit_time = audit_time;
    }

    private List<SalesGoodsRejectDetail> salesGoodsRejectDetailList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReturnNumber() {
        return returnNumber;
    }

    public void setReturnNumber(String returnNumber) {
        this.returnNumber = returnNumber;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public BigDecimal getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(BigDecimal deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    public BigDecimal getReturnQuantity() {
        return returnQuantity;
    }

    public void setReturnQuantity(BigDecimal returnQuantity) {
        this.returnQuantity = returnQuantity;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public List<SalesGoodsRejectDetail> getSalesGoodsRejectDetailList() {
        return salesGoodsRejectDetailList;
    }

    public void setSalesGoodsRejectDetailList(List<SalesGoodsRejectDetail> salesGoodsRejectDetailList) {
        this.salesGoodsRejectDetailList = salesGoodsRejectDetailList;
    }

    @Override
    public String toString() {
        return "SalesGoodsReject{" +
                "id=" + id +
                ", salesNumber='" + salesNumber + '\'' +
                ", returnNumber='" + returnNumber + '\'' +
                ", customer='" + customer + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", deliveredQuantity=" + deliveredQuantity +
                ", returnQuantity=" + returnQuantity +
                ", returnDate=" + returnDate +
                ", returnAmount=" + returnAmount +
                ", returnStatus='" + returnStatus + '\'' +
                ", approve='" + approve + '\'' +
                ", approveTime=" + approveTime +
                ", auditor='" + auditor + '\'' +
                ", audit_time=" + audit_time +
                ", salesGoodsRejectDetailList=" + salesGoodsRejectDetailList +
                '}';
    }
}
