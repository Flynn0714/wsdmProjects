package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Date;

/*
* 换货单查看
* @author 符方龙
* @Date 2021年7月16日10:25:26
* */
public class SalesOrderExchangeCheck {
    /*换货单id*/
    private Long id;

    /*换货详情单id*/
    private Long sid;

    /* 换货单号 */
    private String exchangeNumber;

    /* 销售单号 */
    private String salesNumber;

    /* 换货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date exchangeTime;

    /* 状态 */
    private String status;

    /* 客户 */
    private String customer;

    /* 备注 */
    private String remark;

    /* 产品类型 */
    private String productType;

    /* 产品编号 */
    private String productNumber;

    /* 产品名称 */
    private String productName;

    /* 已发货数量 */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal deliveredQuantity = new BigDecimal(0.0);

    /* 换补单元*/
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal priceDifference = new BigDecimal(0.00);

    /* 小计 */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal total  = new BigDecimal(0.00);

    /*
     * 审核人
     * */
    private String approver;

    /*
     * 审核时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approveTime;

    /*
     * 创建人
     * */
    private String createBy;

    /*
     * 创建时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public SalesOrderExchangeCheck() {
    }

    public SalesOrderExchangeCheck(String exchangeNumber, String salesNumber, Date exchangeTime, String status, String customer, String remark, String productType, String productNumber, String productName, BigDecimal deliveredQuantity, BigDecimal priceDifference, BigDecimal total, String approver, Date approveTime, String createBy, Date createTime) {
        this.exchangeNumber = exchangeNumber;
        this.salesNumber = salesNumber;
        this.exchangeTime = exchangeTime;
        this.status = status;
        this.customer = customer;
        this.remark = remark;
        this.productType = productType;
        this.productNumber = productNumber;
        this.productName = productName;
        this.deliveredQuantity = deliveredQuantity;
        this.priceDifference = priceDifference;
        this.total = total;
        this.approver = approver;
        this.approveTime = approveTime;
        this.createBy = createBy;
        this.createTime = createTime;
    }

    public SalesOrderExchangeCheck(Long id, Long sid, String exchangeNumber, String salesNumber, Date exchangeTime, String status, String customer, String remark, String productType, String productNumber, String productName, BigDecimal deliveredQuantity, BigDecimal priceDifference, BigDecimal total, String approver, Date approveTime, String createBy, Date createTime) {
        this.id = id;
        this.sid = sid;
        this.exchangeNumber = exchangeNumber;
        this.salesNumber = salesNumber;
        this.exchangeTime = exchangeTime;
        this.status = status;
        this.customer = customer;
        this.remark = remark;
        this.productType = productType;
        this.productNumber = productNumber;
        this.productName = productName;
        this.deliveredQuantity = deliveredQuantity;
        this.priceDifference = priceDifference;
        this.total = total;
        this.approver = approver;
        this.approveTime = approveTime;
        this.createBy = createBy;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
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

    public Date getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(Date exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public BigDecimal getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(BigDecimal deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    public BigDecimal getPriceDifference() {
        return priceDifference;
    }

    public void setPriceDifference(BigDecimal priceDifference) {
        this.priceDifference = priceDifference;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
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

    @Override
    public String toString() {
        return "SalesOrderExchangeCheck{" +
                "id=" + id +
                ", sid=" + sid +
                ", exchangeNumber='" + exchangeNumber + '\'' +
                ", salesNumber='" + salesNumber + '\'' +
                ", exchangeTime=" + exchangeTime +
                ", status='" + status + '\'' +
                ", customer='" + customer + '\'' +
                ", remark='" + remark + '\'' +
                ", productType='" + productType + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", productName='" + productName + '\'' +
                ", deliveredQuantity=" + deliveredQuantity +
                ", priceDifference=" + priceDifference +
                ", total=" + total +
                ", approver='" + approver + '\'' +
                ", approveTime=" + approveTime +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
