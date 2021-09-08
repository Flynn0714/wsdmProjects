package com.wsdm.materiel.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 物料收货订单详情对象 wl_receive_order_detail
 *
 * @author wsdm
 * @date 2021-01-09
 */
public class ReceiveOrderDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 收货单号
     */
    @Excel(name = "收货单号")
    private String receiveNumber;

    /**
     * 项次编号
     */
    @Excel(name = "项次编号")
    private Integer itemNo;

    /**
     * 物料编码
     */
    @Excel(name = "物料编码")
    private String materielNumber;

    /**
     * 物料名称
     */
    @Excel(name = "物料名称")
    private String materielName;

    /**
     * 规格参数
     */
    @Excel(name = "规格参数")
    private String modelParam;

    /**
     * 采购数量
     */
    @Excel(name = "采购数量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal purchaseNum;

    /**
     * 收货数量
     */
    @Excel(name = "收货数量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal receiveNum;

    /**
     * 采购重量
     */
    @Excel(name = "重量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal weight;

    /**
     * 计价方式 1-按数量计算 2-按重量计算
     */
    private String valuationWay;

    /**
     * 计价方式 1-按数量计算 2-按重量计算
     */
    @Excel(name = "计价方式")
    private String valuationWayDesc;

    /**
     * 已退货数量
     */
    @Excel(name = "已退货数量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal returnedNum;

    /**
     * 收货单价
     */
    @Excel(name = "收货单价")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal unitPrice;

    /**
     * 小计金额
     */
    @Excel(name = "收货金额")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal subtotalAmount;

    /**
     * 已收货数量
     */
    @Excel(name = "已收货数量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal receivedNum;

    /**
     * 质检类型  字典
     */
    @Excel(name = "质检类型")
    private String checkType;

    /**
     * 质检类型
     */
    @Excel(name = "质检类型")
    private String checkTypeName;

    /**
     * 抽检数量
     */
    @Excel(name = "抽检数量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal checkNum;

    /**
     * 合格数量
     */
    @Excel(name = "合格数量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal qualifiedNum;

    /**
     * 报损数量
     */
    @Excel(name = "报损数量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal faultyNum;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getReturnedNum() {
        return returnedNum;
    }

    public void setReturnedNum(BigDecimal returnedNum) {
        this.returnedNum = returnedNum;
    }

    public String getReceiveNumber() {
        return receiveNumber;
    }

    public void setReceiveNumber(String receiveNumber) {
        this.receiveNumber = receiveNumber;
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

    public BigDecimal getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(BigDecimal purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public BigDecimal getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(BigDecimal receiveNum) {
        this.receiveNum = receiveNum;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCheckTypeName() {
        return checkTypeName;
    }

    public void setCheckTypeName(String checkTypeName) {
        this.checkTypeName = checkTypeName;
    }

    public BigDecimal getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(BigDecimal checkNum) {
        this.checkNum = checkNum;
    }

    public BigDecimal getQualifiedNum() {
        return qualifiedNum;
    }

    public void setQualifiedNum(BigDecimal qualifiedNum) {
        this.qualifiedNum = qualifiedNum;
    }

    public BigDecimal getFaultyNum() {
        return faultyNum;
    }

    public void setFaultyNum(BigDecimal faultyNum) {
        this.faultyNum = faultyNum;
    }

    public BigDecimal getReceivedNum() {
        return receivedNum;
    }

    public void setReceivedNum(BigDecimal receivedNum) {
        this.receivedNum = receivedNum;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSubtotalAmount() {
        return subtotalAmount;
    }

    public void setSubtotalAmount(BigDecimal subtotalAmount) {
        this.subtotalAmount = subtotalAmount;
    }

    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getValuationWay() {
        return valuationWay;
    }

    public void setValuationWay(String valuationWay) {
        this.valuationWay = valuationWay;
    }

    public String getValuationWayDesc() {
        return valuationWayDesc;
    }

    public void setValuationWayDesc(String valuationWayDesc) {
        this.valuationWayDesc = valuationWayDesc;
    }

    public String getMaterialQuality() {
        return materialQuality;
    }

    public void setMaterialQuality(String materialQuality) {
        this.materialQuality = materialQuality;
    }
}