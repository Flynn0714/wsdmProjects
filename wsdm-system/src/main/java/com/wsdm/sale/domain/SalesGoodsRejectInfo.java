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
 * 销售退货列表
 */
public class SalesGoodsRejectInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 退货单号
     */
    private String returnNumber;

    /**
     * 销售单号
     */
    private String salesNumber;

    /**
     * 客户
     */
    private String customerName;

    /**
     * 退货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;


    private List<SalesGoodsRejectDetail> salesGoodsRejectDetailList;

    /**
     * 退货数量
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal returnQuantity;

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
    private String createBy;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:SS")
    private Date createTime;

    /**
     * 审核人
     */
    private String approve;


    /**
     * 提审时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:SS")
    private Date audit_time;

    /**
     * 提审人
     */
    private String auditor;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:SS")
    private Date approveTime;

    private String returnStatusName;

    public String getReturnStatusName() {
        return returnStatusName;
    }

    public void setReturnStatusName(String returnStatusName) {
        this.returnStatusName = returnStatusName;
    }

    public List<SalesGoodsRejectDetail> getSalesGoodsRejectDetailList() {
        return salesGoodsRejectDetailList;
    }

    public void setSalesGoodsRejectDetailList(List<SalesGoodsRejectDetail> salesGoodsRejectDetailList) {
        this.salesGoodsRejectDetailList = salesGoodsRejectDetailList;
    }

    @Override
    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getReturnQuantity() {
        return returnQuantity;
    }

    public void setReturnQuantity(BigDecimal returnQuantity) {
        this.returnQuantity = returnQuantity;
    }

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
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

    public Date getAudit_time() {
        return audit_time;
    }

    public void setAudit_time(Date audit_time) {
        this.audit_time = audit_time;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    @Override
    public String toString() {
        return "SalesGoodsRejectInfo{" +
                "id=" + id +
                ", returnNumber='" + returnNumber + '\'' +
                ", salesNumber='" + salesNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", returnDate=" + returnDate +
                ", salesGoodsRejectDetailList=" + salesGoodsRejectDetailList +
                ", returnQuantity=" + returnQuantity +
                ", returnAmount=" + returnAmount +
                ", returnStatus='" + returnStatus + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", approve='" + approve + '\'' +
                ", approveTime=" + approveTime +
                '}';
    }
}
