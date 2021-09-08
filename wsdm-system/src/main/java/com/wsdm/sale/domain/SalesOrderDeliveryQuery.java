package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 销售单发货对象 sales_order_delivery
 *
 * @author wsdm
 * @date 2021-04-27
 */
public class SalesOrderDeliveryQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 发货单号
     */
    private String deliveryNumber;

    /**
     * 销售单号
     */
    private String salesNumber;

    /**
     * 客户
     */
    private String customer;

    /**
     * 发货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDateStart;

    /**
     * 发货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDateEnd;

    /**
     * 状态
     */
    private String deliveryStatus;

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
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

    public Date getDeliveryDateStart() {
        return deliveryDateStart;
    }

    public void setDeliveryDateStart(Date deliveryDateStart) {
        this.deliveryDateStart = deliveryDateStart;
    }

    public Date getDeliveryDateEnd() {
        return deliveryDateEnd;
    }

    public void setDeliveryDateEnd(Date deliveryDateEnd) {
        this.deliveryDateEnd = deliveryDateEnd;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
