package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 销售退货列表查询字段
 */
public class SalesGoodsRejectInfoQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 退货单号
     */
    private String returnNumber;

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
    private Date returnDateStart;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date returnDateEnd;

    /**
     * 状态
     */
    private String returnStatus;


    public String getReturnNumber() {
        return returnNumber;
    }

    public void setReturnNumber(String returnNumber) {
        this.returnNumber = returnNumber;
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

    public Date getReturnDateStart() {
        return returnDateStart;
    }

    public void setReturnDateStart(Date returnDateStart) {
        this.returnDateStart = returnDateStart;
    }

    public Date getReturnDateEnd() {
        return returnDateEnd;
    }

    public void setReturnDateEnd(Date returnDateEnd) {
        this.returnDateEnd = returnDateEnd;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }
}
