package com.wsdm.sale.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 销售单发货对象 sales_order_delivery
 *
 * @author wsdm
 * @date 2021-04-27
 */
public class SalesOrderDeliveryInfo extends SalesOrderDelivery {

    /**
     * 客户名称
     */
    private String customerName;

    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal deliverQuantity;

    /**
     * 审核人
     */
    private String approveName;

    /**
     * 状态名称
     */
    private String deliveryStatusName;

    /**
     * 销售数量
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal total;

    /**
     * 销售金额
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal amount;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getDeliverQuantity() {
        return deliverQuantity;
    }

    public void setDeliverQuantity(BigDecimal deliverQuantity) {
        this.deliverQuantity = deliverQuantity;
    }

    public String getDeliveryStatusName() {
        return deliveryStatusName;
    }

    public void setDeliveryStatusName(String deliveryStatusName) {
        this.deliveryStatusName = deliveryStatusName;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }
}