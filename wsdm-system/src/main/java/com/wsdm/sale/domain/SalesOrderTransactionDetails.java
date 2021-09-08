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
public class SalesOrderTransactionDetails extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 交易日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transactionDate;

    /**
     * 交易类型
     */
    @Excel(name = "交易类型")
    private String transactionType;

    /**
     * 交易单号
     */
    @Excel(name = "交易单号")
    private String transactionNumber;

    /**
     * 客户
     */
    private String customer;

    /**
     * 客户名称
     */
    @Excel(name = "客户")
    private String customerName;

    /**
     * 物料编码
     */
    @Excel(name = "物料编码")
    private String materialNumber;

    /**
     * 物料名称
     */
    @Excel(name = "物料名称")
    private String materialName;

    /**
     * 交易数量
     */
    @Excel(name = "交易数量", scale = 1, isStatistics = true)
    private BigDecimal quantity = new BigDecimal(0.0);

    /**
     * 交易单价
     */
    @Excel(name = "交易单价", scale = 2)
    private BigDecimal price = new BigDecimal(0.00);

    /**
     * 交易金额
     */
    @Excel(name = "交易金额", scale = 2, isStatistics = true)
    private BigDecimal amount = new BigDecimal(0.00);

    /**
     * 交易时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date transactionTime;
    /**
     * 规格
     */
    private String specs;

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public SalesOrderTransactionDetails(String customer, String transactionNumber, String materialNumber,
                                        BigDecimal quantity, BigDecimal price, BigDecimal amount) {
        this.customer = customer;
        this.transactionNumber = transactionNumber;
        this.materialNumber = materialNumber;
        this.quantity = quantity;
        this.price = price;
        this.amount = amount;
    }

    public SalesOrderTransactionDetails() {}

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

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("customer", getCustomer())
                .append("transactionType", getTransactionType())
                .append("transactionNumber", getTransactionNumber())
                .append("materialNumber", getMaterialNumber())
                .append("quantity", getQuantity())
                .append("price", getPrice())
                .append("amount", getAmount())
                .append("transactionDate", getTransactionDate())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
