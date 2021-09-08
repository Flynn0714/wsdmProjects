package com.wsdm.materiel.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 自动采购信息
 *
 * @author wangr
 * @version 1.0
 * @date 2021/6/27 2:44
 */
public class AutomaticPurchaseInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String number;

    private String name;

    private String modelParam;

    private String type;

    private String typeName;

    /** 可用户数量 */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal num;

    /** 在途数量 */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal stockTransitNum;

    /** 所数量 */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal needNum;

    private String supplierCode;

    private String supplierName;

    private String contractNumber;

    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal price;

    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal purchaseNum;

    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal subtotalAmount;

    private String contacts;

    private String contactNumber;

    private String key;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelParam() {
        return modelParam;
    }

    public void setModelParam(String modelParam) {
        this.modelParam = modelParam;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public BigDecimal getNum() {
        return num.setScale(1);
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getStockTransitNum() {
        if (stockTransitNum == null) {
            stockTransitNum = new BigDecimal(0.0);
        }
        return stockTransitNum.setScale(1);
    }

    public void setStockTransitNum(BigDecimal stockTransitNum) {
        this.stockTransitNum = stockTransitNum;
    }

    public BigDecimal getNeedNum() {
        if (needNum == null) {
            needNum = new BigDecimal(0.0);
        }
        return needNum.setScale(1);
    }

    public void setNeedNum(BigDecimal needNum) {
        this.needNum = needNum;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public BigDecimal getPrice() {
        if (price == null) {
            price= new BigDecimal(0.00);
        }
        return price.setScale(2);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getContacts() {
        return contacts;
    }

    public BigDecimal getPurchaseNum() {
        if (purchaseNum == null) {
            purchaseNum = new BigDecimal(0);
        }
        return purchaseNum.setScale(1);
    }

    public void setPurchaseNum(BigDecimal purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public BigDecimal getSubtotalAmount() {
        if (subtotalAmount == null) {
            subtotalAmount = new BigDecimal(0);
        }
        return subtotalAmount.setScale(2);
    }

    public void setSubtotalAmount(BigDecimal subtotalAmount) {
        this.subtotalAmount = subtotalAmount;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
