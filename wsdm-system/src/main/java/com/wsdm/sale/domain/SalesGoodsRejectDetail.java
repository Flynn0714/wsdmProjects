package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class SalesGoodsRejectDetail implements Serializable {

    /**
     * $column.columnComment
     */
    private Long id;
    /**
     * 类型
     */
    private String detailsType;

    /**
     * 退货单号
     */
    private String returnNumber;

    private Long saleId;

    /** 物料编号 */
    private String materialNumber;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 已发货数量
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal deliveredQuantity = new BigDecimal(0.0);

    /**
     * 已退货数量
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal quantityReturned = new BigDecimal(0.0);

    /**
     * 退货数量
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal returnQuantity = new BigDecimal(0.0);

    /**
     * 单价
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal price = new BigDecimal(0.00);

    /**
     * 手工输入单价
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal priceBy = new BigDecimal(0.00);

    /**
     * 备注
     */
    private String detailsRemark;
    /**
     * 规格
     */
    private String specs;

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public BigDecimal getPriceBy() {
        return priceBy;
    }

    public void setPriceBy(BigDecimal priceBy) {
        this.priceBy = priceBy;
    }

    public String getDetailsRemark() {
        return detailsRemark;
    }

    public void setDetailsRemark(String detailsRemark) {
        this.detailsRemark = detailsRemark;
    }

    /**
     * 退货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;

    /**
     * 小计
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal subtotal = new BigDecimal(0.00);

    private String returnStatusName;

    public String getReturnStatusName() {
        return returnStatusName;
    }

    public void setReturnStatusName(String returnStatusName) {
        this.returnStatusName = returnStatusName;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnNumber() {
        return returnNumber;
    }

    public void setReturnNumber(String returnNumber) {
        this.returnNumber = returnNumber;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetailsType() {
        return detailsType;
    }

    public void setDetailsType(String detailsType) {
        this.detailsType = detailsType;
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

    public BigDecimal getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(BigDecimal deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    public BigDecimal getQuantityReturned() {
        return quantityReturned;
    }

    public void setQuantityReturned(BigDecimal quantityReturned) {
        this.quantityReturned = quantityReturned;
    }

    public BigDecimal getReturnQuantity() {
        return returnQuantity;
    }

    public void setReturnQuantity(BigDecimal returnQuantity) {
        this.returnQuantity = returnQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "SalesGoodsRejectDetail{" +
                "id=" + id +
                ", detailsType='" + detailsType + '\'' +
                ", returnNumber='" + returnNumber + '\'' +
                ", saleId=" + saleId +
                ", materialNumber='" + materialNumber + '\'' +
                ", materialName='" + materialName + '\'' +
                ", deliveredQuantity=" + deliveredQuantity +
                ", quantityReturned=" + quantityReturned +
                ", returnQuantity=" + returnQuantity +
                ", price=" + price +
                ", priceBy=" + priceBy +
                ", detailsRemark='" + detailsRemark + '\'' +
                ", specs='" + specs + '\'' +
                ", returnDate=" + returnDate +
                ", subtotal=" + subtotal +
                ", returnStatusName='" + returnStatusName + '\'' +
                '}';
    }
}
