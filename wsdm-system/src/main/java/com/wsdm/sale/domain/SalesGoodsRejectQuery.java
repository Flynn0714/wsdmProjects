package com.wsdm.sale.domain;


import java.io.Serializable;


/**
 * 新增销售单  查询字段
 */
public class SalesGoodsRejectQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    private String salesNumber;
    /**
     * 客户
     */
    private String customer;

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
}
