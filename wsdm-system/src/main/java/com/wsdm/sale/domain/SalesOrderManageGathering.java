package com.wsdm.sale.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 销售单预收款详情对象 sales_order_manage_gathering
 *
 * @author wsdm
 * @date 2021-04-27
 */
public class SalesOrderManageGathering extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 收款单号
     */
    private String salesNumber;

    /**
     * 收款方式
     */
    private String paymentMethod;

    /**
     * 收款银行
     */
    private String receivingBank;

    /**
     * 收款金额
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal payeeAmount;

    /**
     * 账号
     */
    private String accountNumber;

    /**
     * 收款方式
     */
    private String paymentMethodName;

    /**
     * 收款银行
     */
    private String receivingBankName;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setReceivingBank(String receivingBank) {
        this.receivingBank = receivingBank;
    }

    public String getReceivingBank() {
        return receivingBank;
    }

    public void setPayeeAmount(BigDecimal payeeAmount) {
        this.payeeAmount = payeeAmount;
    }

    public BigDecimal getPayeeAmount() {
        return payeeAmount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public String getReceivingBankName() {
        return receivingBankName;
    }

    public void setReceivingBankName(String receivingBankName) {
        this.receivingBankName = receivingBankName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("salesNumber", getSalesNumber())
                .append("paymentMethod", getPaymentMethod())
                .append("receivingBank", getReceivingBank())
                .append("payeeAmount", getPayeeAmount())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
