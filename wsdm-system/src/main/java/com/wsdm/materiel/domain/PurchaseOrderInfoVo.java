package com.wsdm.materiel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class PurchaseOrderInfoVo extends PurchaseOrderInfo {

    private List<PurchaseOrderDetail> purchaseOrderDetails;
    /**
     * 操作渠道：1-仅保存 2-保存提审
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
     * 预计到货查询开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expectArrivalStartDate;
    /**
     * 预计到货查询结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expectArrivalEndDate;

    public List<PurchaseOrderDetail> getPurchaseOrderDetails() {
        return purchaseOrderDetails;
    }

    public void setPurchaseOrderDetails(List<PurchaseOrderDetail> purchaseOrderDetails) {
        this.purchaseOrderDetails = purchaseOrderDetails;
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

    public Date getExpectArrivalStartDate() {
        return expectArrivalStartDate;
    }

    public void setExpectArrivalStartDate(Date expectArrivalStartDate) {
        this.expectArrivalStartDate = expectArrivalStartDate;
    }

    public Date getExpectArrivalEndDate() {
        return expectArrivalEndDate;
    }

    public void setExpectArrivalEndDate(Date expectArrivalEndDate) {
        this.expectArrivalEndDate = expectArrivalEndDate;
    }

    public PurchaseOrderInfoVo(){}

    public PurchaseOrderInfoVo(String batchNumber){
        super.setStatus(batchNumber);
    }
}
