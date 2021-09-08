package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/*
* 销售换货 sales_order_exchange
*
* _author_: 符方龙
* _date_: 2021年7月13日20:43:17
* */

public class SalesOrderExchangeQuery {
    /**
     * 换货单号
     */
    private String exchangeNumber;

    /**
     * 销售单号
     */
    private String salesNumber;

    /**
     * 客户
     */
    private String customer;

    /**
     * 退货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date DateStart;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date DateEnd;

    /**
     * 状态
     */
    private String status;

    public SalesOrderExchangeQuery(String exchangeNumber) {
        this.exchangeNumber = exchangeNumber;
    }

    public SalesOrderExchangeQuery() {
    }

    public SalesOrderExchangeQuery(String exchangeNumber, String salesNumber, String customer, Date dateStart, Date dateEnd, String status) {
        this.exchangeNumber = exchangeNumber;
        this.salesNumber = salesNumber;
        this.customer = customer;
        DateStart = dateStart;
        DateEnd = dateEnd;
        this.status = status;
    }

    @Override
    public String toString() {
        return "SalesOrderExchangeQuery{" +
                "exchangeNumber='" + exchangeNumber + '\'' +
                ", salesNumber='" + salesNumber + '\'' +
                ", customer='" + customer + '\'' +
                ", DateStart=" + DateStart +
                ", DateEnd=" + DateEnd +
                ", status='" + status + '\'' +
                '}';
    }

    public String getExchangeNumber() {
        return exchangeNumber;
    }

    public void setExchangeNumber(String exchangeNumber) {
        this.exchangeNumber = exchangeNumber;
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

    public Date getDateStart() {
        return DateStart;
    }

    public void setDateStart(Date dateStart) {
        DateStart = dateStart;
    }

    public Date getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        DateEnd = dateEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
