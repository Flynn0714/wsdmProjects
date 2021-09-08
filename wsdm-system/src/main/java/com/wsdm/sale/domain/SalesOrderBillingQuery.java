package com.wsdm.sale.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售单开票对象 sales_order_billing
 *
 * @author wsdm
 * @date 2021-04-27
 */
public class SalesOrderBillingQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 开票单号
     */
    private String billingNumber;

    /**
     * 收款单号
     */
    private String gatheringNumber;

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
     * 发票编号
     */
    private String number;

    /**
     * 状态
     */
    private String billingStatus;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date billingDateStart;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date billingDateEnd;

    public String getBillingNumber() {
        return billingNumber;
    }

    public void setBillingNumber(String billingNumber) {
        this.billingNumber = billingNumber;
    }

    public String getGatheringNumber() {
        return gatheringNumber;
    }

    public void setGatheringNumber(String gatheringNumber) {
        this.gatheringNumber = gatheringNumber;
    }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(String billingStatus) {
        this.billingStatus = billingStatus;
    }

    public Date getBillingDateStart() {
        return billingDateStart;
    }

    public void setBillingDateStart(Date billingDateStart) {
        this.billingDateStart = billingDateStart;
    }

    public Date getBillingDateEnd() {
        return billingDateEnd;
    }

    public void setBillingDateEnd(Date billingDateEnd) {
        this.billingDateEnd = billingDateEnd;
    }
}
