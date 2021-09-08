package com.wsdm.materiel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class ReceiveOrderInfoVo extends ReceiveOrderInfo {

    private List<ReceiveOrderDetail> receiveOrderDetails;

    /**
     * 操作渠道：1-保存 2-保存审核
     */
    private String optChannel;

    /**
     * 采购日期查询开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchaseStartDate;

    /**
     * 采购日期查询结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchaseEndDate;

    /**
     * 收货日期查询开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date receiveStartDate;

    /**
     * 收货日期查询结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date receiveEndDate;

    public List<ReceiveOrderDetail> getReceiveOrderDetails() {
        return receiveOrderDetails;
    }

    public void setReceiveOrderDetails(List<ReceiveOrderDetail> receiveOrderDetails) {
        this.receiveOrderDetails = receiveOrderDetails;
    }

    public String getOptChannel() {
        return optChannel;
    }

    public void setOptChannel(String optChannel) {
        this.optChannel = optChannel;
    }

    public Date getPurchaseStartDate() {
        return purchaseStartDate;
    }

    public void setPurchaseStartDate(Date purchaseStartDate) {
        this.purchaseStartDate = purchaseStartDate;
    }

    public Date getPurchaseEndDate() {
        return purchaseEndDate;
    }

    public void setPurchaseEndDate(Date purchaseEndDate) {
        this.purchaseEndDate = purchaseEndDate;
    }

    public Date getReceiveStartDate() {
        return receiveStartDate;
    }

    public void setReceiveStartDate(Date receiveStartDate) {
        this.receiveStartDate = receiveStartDate;
    }

    public Date getReceiveEndDate() {
        return receiveEndDate;
    }

    public void setReceiveEndDate(Date receiveEndDate) {
        this.receiveEndDate = receiveEndDate;
    }

    @Override
    public String toString() {
        return "ReceiveOrderInfoVo{" +
                "receiveOrderDetails=" + receiveOrderDetails +
                ", optChannel='" + optChannel + '\'' +
                ", purchaseStartDate=" + purchaseStartDate +
                ", purchaseEndDate=" + purchaseEndDate +
                ", receiveStartDate=" + receiveStartDate +
                ", receiveEndDate=" + receiveEndDate +
                '}';
    }
}
