package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 收款汇总对象 sales_order_collection
 *
 * @author wsdm
 * @date 2021-07-17
 */
public class SalesOrderCollection extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 客户
     */
    @Excel(name = "客户")
    private String customer;

    /**
     * 客户名称
     */
    @Excel(name = "客户名称")
    private String customerName;

    /**
     * 客户等级
     */
    @Excel(name = "客户等级")
    private String customerLevel;

    /**
     * 联系人
     */
    @Excel(name = "联系人")
    private String linkman;

    /**
     * 电话
     */
    @Excel(name = "电话")
    private String phone;

    /**
     * 销售员
     */
    @Excel(name = "销售员")
    private String salesperson;

    /**
     * 累计交易金额
     */
    @Excel(name = "累计交易金额", scale = 2)
    private BigDecimal transactionTotal = new BigDecimal(0.00);

    /**
     * 累计应收款金额
     */
    @Excel(name = "累计应收款金额", scale = 2)
    private BigDecimal gatheringTotal= new BigDecimal(0.00);

    /**
     * 应收款金额
     */
    @Excel(name = "应收款金额", scale = 2)
    private BigDecimal gatheringAmount = new BigDecimal(0.00);

    /**
     * 最后交易时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "最后交易时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date transactionDate;

    /**
     * 最后收款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "最后收款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gatheringDate;

    public SalesOrderCollection(){}

    public SalesOrderCollection(String customer, String customerLevel, String linkman, String phone, String salesperson,
                                Date transactionDate, Date gatheringDate) {
        this.customer = customer;
        this.customerLevel = customerLevel;
        this.linkman = linkman;
        this.phone = phone;
        this.salesperson = salesperson;
        this.transactionDate = transactionDate;
        this.gatheringDate = gatheringDate;
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

    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel;
    }

    public String getCustomerLevel() {
        return customerLevel;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setSalesperson(String salesperson) {
        this.salesperson = salesperson;
    }

    public String getSalesperson() {
        return salesperson;
    }

    public void setTransactionTotal(BigDecimal transactionTotal) {
        this.transactionTotal = transactionTotal;
    }

    public BigDecimal getTransactionTotal() {
        return transactionTotal;
    }

    public void setGatheringTotal(BigDecimal gatheringTotal) {
        this.gatheringTotal = gatheringTotal;
    }

    public BigDecimal getGatheringTotal() {
        return gatheringTotal;
    }

    public void setGatheringAmount(BigDecimal gatheringAmount) {
        this.gatheringAmount = gatheringAmount;
    }

    public BigDecimal getGatheringAmount() {
        return gatheringAmount;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setGatheringDate(Date gatheringDate) {
        this.gatheringDate = gatheringDate;
    }

    public Date getGatheringDate() {
        return gatheringDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("customer", getCustomer())
                .append("customerLevel", getCustomerLevel())
                .append("linkman", getLinkman())
                .append("phone", getPhone())
                .append("salesperson", getSalesperson())
                .append("transactionTotal", getTransactionTotal())
                .append("gatheringTotal", getGatheringTotal())
                .append("gatheringAmount", getGatheringAmount())
                .append("transactionDate", getTransactionDate())
                .append("gatheringDate", getGatheringDate())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
