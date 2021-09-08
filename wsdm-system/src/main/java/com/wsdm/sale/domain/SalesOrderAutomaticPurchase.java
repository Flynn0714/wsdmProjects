package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 自动采购销售单查询展示
 *
 * @author wangr
 * @version 1.0
 * @date 2021/6/26 16:02
 */
public class SalesOrderAutomaticPurchase {

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
    private BigDecimal orderQuantity;

    /**
     * 订单金额
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal orderAmount;

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

    private String orderTypeName;

    private String customerName;

    private String levelName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getOrderQuantity() {
        if (orderQuantity == null) {
            orderQuantity = new BigDecimal(0.0);
        }
        return orderQuantity.setScale(1);
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public BigDecimal getOrderAmount() {
        if (orderAmount == null) {
            orderAmount= new BigDecimal(0.00);
        }
        return orderAmount.setScale(2);
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
