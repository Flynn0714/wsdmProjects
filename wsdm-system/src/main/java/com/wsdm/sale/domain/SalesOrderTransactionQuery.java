package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售交易记录对象 sales_order_transaction_details
 *
 * @author wsdm
 * @date 2021-07-17
 */
public class SalesOrderTransactionQuery {

    /**
     * 客户
     */
    private String customer;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 交易类型
     */
    private String transactionType;

    /**
     *
     */
    private String transactionNumber;

    /**
     * 物料
     */
    private String materialNumber;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 交易日期 起始
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date transactionDateStart;

    /**
     * 交易日期 结束
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date transactionDateEnd;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Date getTransactionDateStart() {
        return transactionDateStart;
    }

    public void setTransactionDateStart(Date transactionDateStart) {
        this.transactionDateStart = transactionDateStart;
    }

    public Date getTransactionDateEnd() {
        return transactionDateEnd;
    }

    public void setTransactionDateEnd(Date transactionDateEnd) {
        this.transactionDateEnd = transactionDateEnd;
    }
}
