package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/*
* 换货详情
* @author 符方龙
* @Date 2021年7月14日10:04:25
* */
public class SalesOrderExchangeDetails extends SalesOrderExchangeCheck{
    /*
    * id
    * */
    private Long id;

    /*
    * 客户
    * */
    private String customer;

    /*
    * 换货单号
    * */
    private String exchangeNumber;


    /*
    * 销售单号
    * */
    private String salesNumber;

    /*
    * 产品类型  -- 存放的是产品类型编号
    * */
    private String productType;

    /*
    * 产品编号
    * */
    private String productNumber;

    /*
    * 产品名称
    * */
    private String productName;

    /*
     * 单价
     * */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal price = new BigDecimal(0.00);

    /*
     * 销售数量
     * */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal amount = new BigDecimal(0.0);

    /*
     * 已发货数量
     * */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal deliveredQuantity = new BigDecimal(0.0);

    /*
     * 换货数量
     * */
    @NotBlank(message = "换货数量不能为空")
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal exchangeAmount = new BigDecimal(0.0);

    /*
     * 换补单价--差价
     * */
    @NotBlank(message = "换补单价不能为空")
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal priceDifference = new BigDecimal(0.00);

    /*
     * 换补单价--差价 手工输入
     * */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal priceBy = new BigDecimal(0.00);

    /*
     * 小计
     * */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal total = new BigDecimal(0.00);

    /*
     * 备注
     * */
    private String detailsRemark;

    /*
     * 创建人
     * */
    private String createBy;

    /*
     * 创建时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /*
     * 更新人
     * */
    private String updateBy;

    /*
     * 更新时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /*
     * 提审人
     * */
    private String auditor;

    /*
     * 提审时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditTime;
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

    public BigDecimal getPriceBy() {
        return priceBy;
    }

    public String getDetailsRemark() {
        return detailsRemark;
    }

    public void setDetailsRemark(String detailsRemark) {
        this.detailsRemark = detailsRemark;
    }

    public void setPriceBy(BigDecimal priceBy) {
        this.priceBy = priceBy;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public SalesOrderExchangeDetails() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public SalesOrderExchangeDetails(Long id) {
        this.id = id;
    }

    public SalesOrderExchangeDetails(Long id, String exchangeNumber, String salesNumber, String productType, String productNumber, String productName, BigDecimal price, BigDecimal amount, BigDecimal deliveredQuantity, @NotBlank(message = "换货数量不能为空") BigDecimal exchangeAmount, @NotBlank(message = "换补单价不能为空") BigDecimal priceDifference, BigDecimal total, String detailsRemark, String createBy, Date createTime, String updateBy, Date updateTime) {
        this.id = id;
        this.exchangeNumber = exchangeNumber;
        this.salesNumber = salesNumber;
        this.productType = productType;
        this.productNumber = productNumber;
        this.productName = productName;
        this.price = price;
        this.amount = amount;
        this.deliveredQuantity = deliveredQuantity;
        this.exchangeAmount = exchangeAmount;
        this.priceDifference = priceDifference;
        this.total = total;
        this.detailsRemark = detailsRemark;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(BigDecimal deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    public BigDecimal getExchangeAmount() {
        return exchangeAmount;
    }

    public void setExchangeAmount(BigDecimal exchangeAmount) {
        this.exchangeAmount = exchangeAmount;
    }

    public BigDecimal getPriceDifference() {
        return priceDifference;
    }

    public void setPriceDifference(BigDecimal priceDifference) {
        this.priceDifference = priceDifference;
    }

    public BigDecimal getSubtotal() {
        return total;
    }

    public void setSubtotal(BigDecimal total) {
        this.total = total;
    }

    public String getRemark() {
        return detailsRemark;
    }

    public void setRemark(String detailsRemark) {
        this.detailsRemark = detailsRemark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SalesOrderExchangeDetails{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", exchangeNumber='" + exchangeNumber + '\'' +
                ", salesNumber='" + salesNumber + '\'' +
                ", productType='" + productType + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", deliveredQuantity=" + deliveredQuantity +
                ", exchangeAmount=" + exchangeAmount +
                ", priceDifference=" + priceDifference +
                ", total=" + total +
                ", detailsRemark='" + detailsRemark + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", auditor='" + auditor + '\'' +
                ", auditTime=" + auditTime +
                '}';
    }
}
