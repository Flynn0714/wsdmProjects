package com.wsdm.sale.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 销售单开票对象 sales_order_billing
 *
 * @author wsdm
 * @date 2021-04-27
 */
public class SalesOrderBilling extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 开票单号
     */
    private String billingNumber;

    /**
     * 收款单号
     */
    @NotBlank(message = "收款单号不能为空")
    private String gatheringNumber;

    /**
     * 发货单号
     */
    @NotBlank(message = "发货单号不能为空")
    private String deliveryNumber;

    /**
     * 销售单号
     */
    @NotBlank(message = "销售单号不能为空")
    private String salesNumber;

    /**
     * 客户
     */
    @NotBlank(message = "客户不能为空")
    private String customer;

    /**
     * 金额
     */
    @NotNull(message = "金额不能为空")
    @Min(value = 0, message = "开票金额要大于0")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal billingTotal;

    /**
     * 发票编号
     */
    @NotBlank(message = "发票编号不能为空")
    private String number;

    /**
     * 状态
     */
    private String billingStatus;

    /**
     * 审核人
     */
    private String approve;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date approveTime;



    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDate;

    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal gatheringAmount;

    /**
     * 客户
     */
    private String customerName;

    /**
     * 状态
     */
    private String billingStatusName;

    /**
     * 审核人
     */
    private String approveName;

    private List<SalesOrderGatheringDetails> gatheringDetailsList;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setBillingNumber(String billingNumber) {
        this.billingNumber = billingNumber;
    }

    public String getBillingNumber() {
        return billingNumber;
    }

    public void setGatheringNumber(String gatheringNumber) {
        this.gatheringNumber = gatheringNumber;
    }

    public String getGatheringNumber() {
        return gatheringNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setBillingTotal(BigDecimal billingTotal) {
        this.billingTotal = billingTotal;
    }

    public BigDecimal getBillingTotal() {
        return billingTotal;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setBillingStatus(String billingStatus) {
        this.billingStatus = billingStatus;
    }

    public String getBillingStatus() {
        return billingStatus;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBillingStatusName() {
        return billingStatusName;
    }

    public void setBillingStatusName(String billingStatusName) {
        this.billingStatusName = billingStatusName;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public List<SalesOrderGatheringDetails> getGatheringDetailsList() {
        return gatheringDetailsList;
    }

    public void setGatheringDetailsList(List<SalesOrderGatheringDetails> gatheringDetailsList) {
        this.gatheringDetailsList = gatheringDetailsList;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public BigDecimal getGatheringAmount() {
        return gatheringAmount;
    }

    public void setGatheringAmount(BigDecimal gatheringAmount) {
        this.gatheringAmount = gatheringAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("billingNumber", getBillingNumber())
                .append("gatheringNumber", getGatheringNumber())
                .append("deliveryNumber", getDeliveryNumber())
                .append("salesNumber", getSalesNumber())
                .append("billingTotal", getBillingTotal())
                .append("number", getNumber())
                .append("remark", getRemark())
                .append("billingStatus", getBillingStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("approve", getApprove())
                .append("approveTime", getApproveTime())
                .toString();
    }
}
