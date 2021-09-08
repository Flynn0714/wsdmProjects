package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 销售单发货对象 sales_order_delivery
 *
 * @author wsdm
 * @date 2021-04-27
 */
public class SalesOrderDelivery extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 发货单号
     */
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
     * 客户联系人
     */
    private String customerLink;

    /**
     * 客户联系电话
     */
    private String customerLinkTel;

    /**
     * 客户地址
     */
    private String customerAddress;

    /**
     * 发货日期
     */
    @NotNull(message = "发货日期不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDate;

    /**
     * 状态
     */
    private String deliveryStatus;

    /**
     * 发货数量
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal deliveredQuantity;

    /**
     * 发货金额
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal deliveryAmount;

    /**
     * 审核人
     */
    private String approve;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date approveTime;

    @NotEmpty(message = "发货详情不能为空")
    private List<SalesOrderDeliveryDetails> deliveryDetailsList;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomerLink(String customerLink) {
        this.customerLink = customerLink;
    }

    public String getCustomerLink() {
        return customerLink;
    }

    public void setCustomerLinkTel(String customerLinkTel) {
        this.customerLinkTel = customerLinkTel;
    }

    public String getCustomerLinkTel() {
        return customerLinkTel;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public String getApprove() {
        return approve;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public List<SalesOrderDeliveryDetails> getDeliveryDetailsList() {
        return deliveryDetailsList;
    }

    public void setDeliveryDetailsList(List<SalesOrderDeliveryDetails> deliveryDetailsList) {
        this.deliveryDetailsList = deliveryDetailsList;
    }

    public BigDecimal getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(BigDecimal deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    public BigDecimal getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(BigDecimal deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("deliveryNumber", getDeliveryNumber())
                .append("salesNumber", getSalesNumber())
                .append("customer", getCustomer())
                .append("customerLink", getCustomerLink())
                .append("customerLinkTel", getCustomerLinkTel())
                .append("customerAddress", getCustomerAddress())
                .append("deliveryDate", getDeliveryDate())
                .append("deliveredQuantity",getDeliveredQuantity())
                .append("deliveryAmount",getDeliveryAmount())
                .append("remark", getRemark())
                .append("deliveryStatus", getDeliveryStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("approve", getApprove())
                .append("approveTime", getApproveTime())
                .toString();
    }
}
