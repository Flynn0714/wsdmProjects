package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 收款记录对象 sales_order_account_received
 *
 * @author wsdm
 * @date 2021-07-17
 */
public class SalesOrderAccountReceived extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 收款日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gatheringDate;

    /**
     * 收款类型
     */
    @Excel(name = "收款类型")
    private String gatheringType;

    /**
     * 收款人
     */
    @Excel(name = "收款人")
    private String gatheringUser;

    /**
     * 收款方式
     */
    @Excel(name = "收款方式")
    private String paymentMethod;

    /**
     * 收款银行
     */
    @Excel(name = "收款银行")
    private String receivingBank;

    /**
     * 收款账户
     */
    @Excel(name = "收款账户")
    private String accountNumber;

    /**
     * 收款金额
     */
    @Excel(name = "收款金额", scale = 2)
    private BigDecimal payeeAmount = new BigDecimal(0.00);

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

    /**
     * 客户编码
     */
    @Excel(name = "客户编码")
    private String customer;

    /**
     * 客户名称
     */
    @Excel(name = "客户名称")
    private String customerName;

    /**
     * 收款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "收款时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date gatheringTime;

    public SalesOrderAccountReceived(String customer, String gatheringType, String gatheringUser, String paymentMethod,
                                     String receivingBank, BigDecimal payeeAmount, String accountNumber, Date gatheringDate) {
        this.customer = customer;
        this.gatheringType = gatheringType;
        this.gatheringUser = gatheringUser;
        this.paymentMethod = paymentMethod;
        this.receivingBank = receivingBank;
        this.payeeAmount = payeeAmount;
        this.accountNumber = accountNumber;
        this.gatheringDate = gatheringDate;
    }

    public SalesOrderAccountReceived() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setGatheringType(String gatheringType) {
        this.gatheringType = gatheringType;
    }

    public String getGatheringType() {
        return gatheringType;
    }

    public void setGatheringUser(String gatheringUser) {
        this.gatheringUser = gatheringUser;
    }

    public String getGatheringUser() {
        return gatheringUser;
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

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setGatheringDate(Date gatheringDate) {
        this.gatheringDate = gatheringDate;
    }

    public Date getGatheringDate() {
        return gatheringDate;
    }

    public Date getGatheringTime() {
        return gatheringTime;
    }

    public void setGatheringTime(Date gatheringTime) {
        this.gatheringTime = gatheringTime;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("customer", getCustomer())
                .append("gatheringType", getGatheringType())
                .append("gatheringUser", getGatheringUser())
                .append("paymentMethod", getPaymentMethod())
                .append("receivingBank", getReceivingBank())
                .append("payeeAmount", getPayeeAmount())
                .append("accountNumber", getAccountNumber())
                .append("gatheringDate", getGatheringDate())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
