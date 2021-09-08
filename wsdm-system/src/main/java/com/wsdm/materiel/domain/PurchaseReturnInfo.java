package com.wsdm.materiel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wsdm.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: wjj
 * @Date: 2021/7/14 09:22
 * @Description: 采购退货列表信息对象 wl_return_order_info
 */
public class PurchaseReturnInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 操作类型 0 保存（编辑） 1 保存（编辑）提交 2 详情页提交审核
     */
    private String optType;


    private List<PurchaseReturnDetail> purchaseReturnDetails;

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
     * 供应商名称
     */
    private String supplierName;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String contactsNumber;
    /**
     * 采购类型
     */
    private String purchaseType;

    /**
     * 采购类型名称  dict_purchase_type
     */
    private String purchaseTypeName;


    /**
     * 采退日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;

    /**
     * 收货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date arrivalDate;


    /**
     * 收货数量
     */
    private BigDecimal receiveQuantity;

    /**
     * 采退数量
     */
    private BigDecimal returnQuantity;

    /**
     * 已采退数量
     */
    private BigDecimal returnedQuantity;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态
     */
    private String status;

    /**
     * 状态名称
     */
    private String statusName;
    /**
     * 审核人
     */
    private String approveBy;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approveTime;


    public List<PurchaseReturnDetail> getPurchaseReturnDetails() {
        return purchaseReturnDetails;
    }

    public void setPurchaseReturnDetails(List<PurchaseReturnDetail> purchaseReturnDetails) {
        this.purchaseReturnDetails = purchaseReturnDetails;
    }

    public BigDecimal getReturnedQuantity() {
        return returnedQuantity;
    }

    public void setReturnedQuantity(BigDecimal returnedQuantity) {
        this.returnedQuantity = returnedQuantity;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public BigDecimal getReceiveQuantity() {
        return receiveQuantity;
    }

    public void setReceiveQuantity(BigDecimal receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
    }

    public BigDecimal getReturnQuantity() {
        return returnQuantity;
    }

    public void setReturnQuantity(BigDecimal returnQuantity) {
        this.returnQuantity = returnQuantity;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getPurchaseTypeName() {
        return purchaseTypeName;
    }

    public void setPurchaseTypeName(String purchaseTypeName) {
        this.purchaseTypeName = purchaseTypeName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public String getReturnNumber() {
        return returnNumber;
    }

    public String getReceiveNumber() {
        return receiveNumber;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public String getContacts() {
        return contacts;
    }

    public String getAddress() {
        return address;
    }

    public String getContactsNumber() {
        return contactsNumber;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    public String getStatus() {
        return status;
    }

    public String getApproveBy() {
        return approveBy;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReturnNumber(String returnNumber) {
        this.returnNumber = returnNumber;
    }

    public void setReceiveNumber(String receiveNumber) {
        this.receiveNumber = receiveNumber;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactsNumber(String contactsNumber) {
        this.contactsNumber = contactsNumber;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setApproveBy(String approveBy) {
        this.approveBy = approveBy;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    @Override
    public String toString() {
        return "PurchaseReturnInfo{" +
                "id=" + id +
                ", optType='" + optType + '\'' +
                ", purchaseReturnDetails=" + purchaseReturnDetails +
                ", returnNumber='" + returnNumber + '\'' +
                ", receiveNumber='" + receiveNumber + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", contacts='" + contacts + '\'' +
                ", address='" + address + '\'' +
                ", contactsNumber='" + contactsNumber + '\'' +
                ", purchaseType='" + purchaseType + '\'' +
                ", purchaseTypeName='" + purchaseTypeName + '\'' +
                ", returnDate=" + returnDate +
                ", arrivalDate=" + arrivalDate +
                ", receiveQuantity=" + receiveQuantity +
                ", returnQuantity=" + returnQuantity +
                ", returnedQuantity=" + returnedQuantity +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", statusName='" + statusName + '\'' +
                ", approveBy='" + approveBy + '\'' +
                ", approveTime=" + approveTime +
                '}';
    }
}
