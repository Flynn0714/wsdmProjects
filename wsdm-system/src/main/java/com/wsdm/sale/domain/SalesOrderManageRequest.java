package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class SalesOrderManageRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 销售单号
     */
    private String salesNumber;

    /**
     * 客户
     */
    private String customer;

    /**
     * 紧急程度
     */
    private String level;

    /**
     * 订单类型
     */
    private String orderType;

    /**
     * 订货日期查询开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderStartDate;
    /**
     * 订货日期查询结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderEndDate;

    /**
     * 预交付查询开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expectedDeliveryStartDate;
    /**
     * 预交付查询结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expectedDeliveryEndDate;

    private String status;

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

    public Date getOrderStartDate() {
        return orderStartDate;
    }

    public void setOrderStartDate(Date orderStartDate) {
        this.orderStartDate = orderStartDate;
    }

    public Date getOrderEndDate() {
        return orderEndDate;
    }

    public void setOrderEndDate(Date orderEndDate) {
        this.orderEndDate = orderEndDate;
    }

    public Date getExpectedDeliveryStartDate() {
        return expectedDeliveryStartDate;
    }

    public void setExpectedDeliveryStartDate(Date expectedDeliveryStartDate) {
        this.expectedDeliveryStartDate = expectedDeliveryStartDate;
    }
    public Date getExpectedDeliveryEndDate() {
        return expectedDeliveryEndDate;
    }

    public void setExpectedDeliveryEndDate(Date expectedDeliveryEndDate) {
        this.expectedDeliveryEndDate = expectedDeliveryEndDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
