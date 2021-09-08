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

public class SalesOrderExchange {
    /*
     * 编号
     * */
    private Long id;

    /*
    * sid
    * */
    private Long sid;

    /*
    * 换货单号
    * */
    private String exchangeNumber;

    /*
     * 销售单号
     * */
    private String salesNumber;

    /*
     * 换货时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date exchangeTime;

    /*
     * 换货数量
     * */
    @NotBlank(message = "数量不能为空")
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal exchangeQuantity = new BigDecimal(0.0);

    /*
     * 换货补价
     * */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal orderAmount = new BigDecimal(0.00);

    /*
     * 客户编号
     * */
    private String customer;

    /*
    * 状态
    * */
    private String status;

    /*
     * 备注
     * */
    private String remark;

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
     * 审核人
     * */
    private String approver;

    /*
     * 审核时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approveTime;

    /*
    * 提审人
    * */
    private String auditor;

    /*
    * 提审时间
    * */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditTime;


    /*物料详情*/
    private List<SalesOrderExchangeDetails> exchangeDetailsList;

    public List<SalesOrderExchangeDetails> getExchangeDetailsList() {
        return exchangeDetailsList;
    }

    public void setExchangeDetailsList(List<SalesOrderExchangeDetails> exchangeDetailsList) {
        this.exchangeDetailsList = exchangeDetailsList;
    }

    public SalesOrderExchange() {
    }

    public SalesOrderExchange(Long id) {
        this.id = id;
    }

    public SalesOrderExchange(String exchangeNumber, String salesNumber, @NotBlank(message = "数量不能为空") BigDecimal exchangeQuantity, BigDecimal orderAmount, String customer, String status, String approver, Date approveTime) {
        this.exchangeNumber = exchangeNumber;
        this.salesNumber = salesNumber;
        this.exchangeQuantity = exchangeQuantity;
        this.orderAmount = orderAmount;
        this.customer = customer;
        this.status = status;
        this.approver = approver;
        this.approveTime = approveTime;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
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

    public Date getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(Date exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public BigDecimal getExchangeQuantity() {
        return exchangeQuantity;
    }

    public void setExchangeQuantity(BigDecimal exchangeQuantity) {
        this.exchangeQuantity = exchangeQuantity;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Override
    public String toString() {
        return "SalesOrderExchange{" +
                "id=" + id +
                ", sid=" + sid +
                ", exchangeNumber='" + exchangeNumber + '\'' +
                ", salesNumber='" + salesNumber + '\'' +
                ", exchangeTime=" + exchangeTime +
                ", exchangeQuantity=" + exchangeQuantity +
                ", orderAmount=" + orderAmount +
                ", customer='" + customer + '\'' +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", approver='" + approver + '\'' +
                ", approveTime=" + approveTime +
                ", auditor='" + auditor + '\'' +
                ", auditTime=" + auditTime +
                ", exchangeDetailsList=" + exchangeDetailsList +
                '}';
    }
}
