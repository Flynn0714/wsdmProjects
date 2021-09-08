package com.wsdm.materiel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @Auther: wjj
 * @Date: 2021/7/16 19:27
 * @Description:
 */
public class PurchaseReturnInfoVo extends PurchaseReturnInfo{
    private static final long serialVersionUID = 1L;


    /**
     * 操作类型 0 保存（编辑） 1 保存（编辑）提交 2 详情页提交审核
     */
    private String optType;

    /**
     * 采退单号
     */
    private String returnNumber;

    /**
     * 收货单号
     */

    private String receiveNumber;

    /**
     *供应商编码
     */
    private String supplierCode;


    /**
     * 采购类型
     */
    private String purchaseType;

    /**
     * 状态
     */
    private String status;
    /**
     * 采购类型名称  dict_purchase_type
     */
    private String purchaseTypeName;

    /**
     * 采退日期查询开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date returnStartDate;

    /**
     * 采退日期查询结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date returnEndDate;
    /**
     * 收货日期查询开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date receiveStartDate;

    /**
     * 收货日期查询结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date receiveEndDate;

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public Date getReturnStartDate() {
        return returnStartDate;
    }

    public void setReturnStartDate(Date returnStartDate) {
        this.returnStartDate = returnStartDate;
    }

    public Date getReturnEndDate() {
        return returnEndDate;
    }

    public void setReturnEndDate(Date returnEndDate) {
        this.returnEndDate = returnEndDate;
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

    public String getReturnNumber() {
        return returnNumber;
    }

    public void setReturnNumber(String returnNumber) {
        this.returnNumber = returnNumber;
    }

    public String getReceiveNumber() {
        return receiveNumber;
    }

    public void setReceiveNumber(String receiveNumber) {
        this.receiveNumber = receiveNumber;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPurchaseTypeName() {
        return purchaseTypeName;
    }

    public void setPurchaseTypeName(String purchaseTypeName) {
        this.purchaseTypeName = purchaseTypeName;
    }

    @Override
    public String toString() {
        return "PurchaseReturnInfoVo{" +
                "optType='" + optType + '\'' +
                ", returnNumber='" + returnNumber + '\'' +
                ", receiveNumber='" + receiveNumber + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", purchaseType='" + purchaseType + '\'' +
                ", status='" + status + '\'' +
                ", purchaseTypeName='" + purchaseTypeName + '\'' +
                ", returnStartDate=" + returnStartDate +
                ", returnEndDate=" + returnEndDate +
                ", receiveStartDate=" + receiveStartDate +
                ", receiveEndDate=" + receiveEndDate +
                '}';
    }
}