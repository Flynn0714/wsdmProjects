package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 销售订单对象 sales_order_manage
 *
 * @author wangr
 * @date 2020-11-06
 */
public class SalesOrderManage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 销售单号
     */
    private String salesNumber;

    /**
     * 客户
     */
    @NotBlank(message = "客户不能为空")
    private String customer;

    /**
     * 紧急程度
     */
    @NotBlank(message = "紧急程度不能为空")
    private String level;

    /**
     * 订单类型
     */
    @NotBlank(message = "订单类型不能为空")
    private String orderType;

    /**
     * 数量
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal orderQuantity = new BigDecimal(0.0);

    /**
     * 订单金额
     */
    private BigDecimal orderAmount = new BigDecimal(0.00);

    /**
     * 预收款金额
     */
    private BigDecimal orderGathering = new BigDecimal(0.00);

    /**
     * 订单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    /**
     * 月交付日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expectedDeliveryDate;

    /**
     * 状态
     */
    private String status;

    /** 审核人 */
    private String approve;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approveTime;

    @Valid
    @NotEmpty(message = "订单类型不能为空")
    private List<SalesOrderManageDetails> salesOrderManageDetailsList;

    /** 预收款详情 */
    List<SalesOrderManageGathering> salesOrderManageGatherings;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderGathering() {
        return orderGathering;
    }

    public void setOrderGathering(BigDecimal orderGathering) {
        this.orderGathering = orderGathering;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
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

    public List<SalesOrderManageDetails> getSalesOrderManageDetailsList() {
        return salesOrderManageDetailsList;
    }

    public void setSalesOrderManageDetailsList(List<SalesOrderManageDetails> salesOrderManageDetailsList) {
        this.salesOrderManageDetailsList = salesOrderManageDetailsList;
    }

    public List<SalesOrderManageGathering> getSalesOrderManageGatherings() {
        return salesOrderManageGatherings;
    }

    public void setSalesOrderManageGatherings(List<SalesOrderManageGathering> salesOrderManageGatherings) {
        this.salesOrderManageGatherings = salesOrderManageGatherings;
    }

    public SalesOrderManage(){}

    public SalesOrderManage(Long id){
        this.id = id;
    }

    public SalesOrderManage(Long id, String status, BigDecimal orderAmount, BigDecimal orderQuantity, String approve, Date approveTime){
        this.id = id;
        this.status = status;
        this.approve = approve;
        this.approveTime = approveTime;
        this.orderAmount = orderAmount;
        this.orderQuantity = orderQuantity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("salesNumber", getSalesNumber())
                .append("customer", getCustomer())
                .append("level", getLevel())
                .append("orderQuantity", getOrderQuantity())
                .append("orderAmount", getOrderAmount())
                .append("orderDate", getOrderDate())
                .append("expectedDeliveryDate", getExpectedDeliveryDate())
                .append("remark", getRemark())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("approve", getApprove())
                .append("approveTime", getApproveTime())
                .toString();
    }
}
