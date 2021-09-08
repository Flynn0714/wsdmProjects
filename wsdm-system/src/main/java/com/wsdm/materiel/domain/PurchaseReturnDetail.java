package com.wsdm.materiel.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wsdm.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * @Auther: wjj
 * @Date: 2021/7/15 15:22
 * @Description:  采购退货详情表  wl_return_order_detail
 */
public class PurchaseReturnDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;


    /**
     * 序号
     */
    private Integer itemNo;
    /**
     * 退货单号
     */
    private String returnNumber;

    /**
     * 合同编号
     */
    private String contractNumber;
    /**
     * 物料编码
     */
    private String materialNumber;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 规格/参数
     */
    private String modelParam;


    /**
     * 已收货数量
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal receivedQuantity;

    /**
     * 采退数量
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal returnQuantity;

    /**
     * 已退货数量
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal returnedQuantity;
    /**
     * 计量单位
     */
    private String unit;

    /**
     * 单价
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal price;
    /**
     * 小计
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal amount;

    /**
     * 半成品类型
     */
    private String wlType;

    /**
     * 材质
     */
    private String materialQuality;
    /**
     * 采购重量
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal weight;

    /**
     * 计价方式  1-按数量计算 2-按重量计算
     */
    private String valuationWay;

    /**
     * 其他金额
     */
    private BigDecimal otherAmount;

    /**
     * 备注
     */
    private String remark;

    public BigDecimal getOtherAmount() {
        return otherAmount;
    }

    public void setOtherAmount(BigDecimal otherAmount) {
        this.otherAmount = otherAmount;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMaterialQuality() {
        return materialQuality;
    }

    public void setMaterialQuality(String materialQuality) {
        this.materialQuality = materialQuality;
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

    public String getWlType() {
        return wlType;
    }

    public void setWlType(String wlType) {
        this.wlType = wlType;
    }


    public String getReturnNumber() {
        return returnNumber;
    }

    public void setReturnNumber(String returnNumber) {
        this.returnNumber = returnNumber;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getModelParam() {
        return modelParam;
    }

    public void setModelParam(String modelParam) {
        this.modelParam = modelParam;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigDecimal getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(BigDecimal receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public BigDecimal getReturnQuantity() {
        return returnQuantity;
    }

    public void setReturnQuantity(BigDecimal returnQuantity) {
        this.returnQuantity = returnQuantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public BigDecimal getReturnedQuantity() {
        return returnedQuantity;
    }

    public void setReturnedQuantity(BigDecimal returnedQuantity) {
        this.returnedQuantity = returnedQuantity;
    }

    @Override
    public String toString() {
        return "PurchaseReturnDetail{" +
                "id=" + id +
                ", itemNo=" + itemNo +
                ", returnNumber='" + returnNumber + '\'' +
                ", contractNumber='" + contractNumber + '\'' +
                ", materialNumber='" + materialNumber + '\'' +
                ", materialName='" + materialName + '\'' +
                ", modelParam='" + modelParam + '\'' +
                ", receivedQuantity=" + receivedQuantity +
                ", returnQuantity=" + returnQuantity +
                ", returnedQuantity=" + returnedQuantity +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", wlType='" + wlType + '\'' +
                ", materialQuality='" + materialQuality + '\'' +
                ", weight=" + weight +
                ", valuationWay='" + valuationWay + '\'' +
                ", otherAmount=" + otherAmount +
                ", remark='" + remark + '\'' +
                '}';
    }
}
