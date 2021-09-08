package com.wsdm.materiel.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 物料采购订单详情对象 wl_purchase_order_detail
 *
 * @author wsdm
 * @date 2020-11-26
 */
public class PurchaseOrderDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 采购单号
     */
    @Excel(name = "采购单号")
    private String purchaseNumber;

    /**
     * 物料编码
     */
    @Excel(name = "物料编码")
    private String materielNumber;

    /**
     * 供应商合同编号
     */
    private String contractNumber;

    /**
     * 物料名称
     */
    @Excel(name = "物料名称")
    private String materielName;

    /**
     * 参数
     */
    @Excel(name = "参数")
    private String modelParam;

    /**
     * 型号
     */
    @Excel(name = "型号")
    private String specifications;

    /**
     * 单价
     */
    @Excel(name = "单价")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal unitPrice;

    /**
     * 采购数量
     */
    @Excel(name = "采购数量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal purchaseNum;

    /**
     * 小计金额
     */
    @Excel(name = "小计金额")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal subtotalAmount;

    /**
     * 已收货数量
     */
    @Excel(name = "已收货数量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal receivedNum;

    /**
     * 收货数量
     */
    @Excel(name = "收货数量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal receiveNum;

    /**
     * 半成品类型
     */
    private String wlType;

    /**
     * 材质
     */
    private String materialQuality;

    public String getWlType() {
        return wlType;
    }

    public void setWlType(String wlType) {
        this.wlType = wlType;
    }

    public String getMaterialQuality() {
        return materialQuality;
    }

    public void setMaterialQuality(String materialQuality) {
        this.materialQuality = materialQuality;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public BigDecimal getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(BigDecimal receiveNum) {
        this.receiveNum = receiveNum;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    public String getMaterielNumber() {
        return materielNumber;
    }

    public void setMaterielNumber(String materielNumber) {
        this.materielNumber = materielNumber;
    }

    public String getMaterielName() {
        return materielName;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }

    public String getModelParam() {
        return modelParam;
    }

    public void setModelParam(String modelParam) {
        this.modelParam = modelParam;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(BigDecimal purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public BigDecimal getSubtotalAmount() {
        return subtotalAmount;
    }

    public void setSubtotalAmount(BigDecimal subtotalAmount) {
        this.subtotalAmount = subtotalAmount;
    }

    public BigDecimal getReceivedNum() {
        return receivedNum;
    }

    public void setReceivedNum(BigDecimal receivedNum) {
        this.receivedNum = receivedNum;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("purchaseNumber", getPurchaseNumber())
                .append("materielNumber", getMaterielNumber())
                .append("materielName", getMaterielName())
                .append("modelParam", getModelParam())
                .append("unitPrice", getUnitPrice())
                .append("purchaseNum", getPurchaseNum())
                .append("subtotalAmount", getSubtotalAmount())
                .append("receivedNum", getReceivedNum())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
