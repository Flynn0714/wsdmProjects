package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigDecimal;
import java.util.Date;

/*
* 销售换货--选择销售单
* @author 符方龙
* @Date 2021年7月14日16:51:53
* */
public class SalesOrderExchangeSelect {
    /*
    * 销售单号
    * */
    private String salesNumber;

    /*
    * 客户
    * */
    private String customer;


    /*
    * 最后发货日期
    * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date exceptedDeliveryDate;

    /*
    * 已发货数量
    * */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal deliveredQuantity = new BigDecimal(0.0);

    /*
    * 已换货数量
    * */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal exchangeQuantity = new BigDecimal(0.0);

    public BigDecimal getExchangeQuantity() {
        return exchangeQuantity;
    }

    public void setExchangeQuantity(BigDecimal exchangeQuantity) {
        this.exchangeQuantity = exchangeQuantity;
    }

    public SalesOrderExchangeSelect() {
    }

    public SalesOrderExchangeSelect(String salesNumber, String customer, Date exceptedDeliveryDate, BigDecimal deliveredQuantity) {
        this.salesNumber = salesNumber;
        this.customer = customer;
        this.exceptedDeliveryDate = exceptedDeliveryDate;
        this.deliveredQuantity = deliveredQuantity;
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

    public Date getExceptedDeliveryDate() {
        return exceptedDeliveryDate;
    }

    public void setExceptedDeliveryDate(Date exceptedDeliveryDate) {
        this.exceptedDeliveryDate = exceptedDeliveryDate;
    }

    public BigDecimal getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(BigDecimal deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    @Override
    public String toString() {
        return "SalesOrderExchangeSelect{" +
                "salesNumber='" + salesNumber + '\'' +
                ", customer='" + customer + '\'' +
                ", exceptedDeliveryDate=" + exceptedDeliveryDate +
                ", deliveredQuantity=" + deliveredQuantity +
                ", exchangeQuantity=" + exchangeQuantity +
                '}';
    }
}
