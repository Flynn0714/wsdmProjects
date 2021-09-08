package com.wsdm.sale.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * 销售单发货详情列对象 sales_order_delivery_details
 *
 * @author wsdm
 * @date 2021-04-27
 */
public class SalesOrderDeliveryDetails extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 发货单号
     */
    @NotBlank(message = "发货单号不能为空")
    private String deliveryNumber;

    /**
     * 销售单详情id
     */
    @NotBlank(message = "销售订单详id不能为空")
    private String orderDetailsId;

    /**
     * 销售单详情id
     */
    @NotBlank(message = "销售单号不能为空")
    private String salesNumber;

    /**
     * 已发货数量
     *
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal deliveredQuantity;

    /**
     * 发货数量
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal deliverQuantity;

    /**
     * 发货金额
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal deliveryAmount;

    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal totalWeight;

    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal quantity;

    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal price;

    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal subtotal;

    /**
     * 物料编号
     */
    private String materialNumber;

    /**
     * 物料名称
     */
    private String materialNumberName;

    /**
     * 类型
     */
    private String detailsType;

    /**
     * 类型
     */
    private String detailsTypeName;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public String getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(String orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public void setDeliveredQuantity(BigDecimal deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    public BigDecimal getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliverQuantity(BigDecimal deliverQuantity) {
        this.deliverQuantity = deliverQuantity;
    }

    public BigDecimal getDeliverQuantity() {
        return deliverQuantity;
    }

    public void setDeliveryAmount(BigDecimal deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public BigDecimal getDeliveryAmount() {
        return deliveryAmount;
    }


    public String getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
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

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getMaterialNumberName() {
        return materialNumberName;
    }

    public void setMaterialNumberName(String materialNumberName) {
        this.materialNumberName = materialNumberName;
    }

    public String getDetailsType() {
        return detailsType;
    }

    public void setDetailsType(String detailsType) {
        this.detailsType = detailsType;
    }

    public String getDetailsTypeName() {
        return detailsTypeName;
    }

    public void setDetailsTypeName(String detailsTypeName) {
        this.detailsTypeName = detailsTypeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("deliveryNumber", getDeliveryNumber())
                .append("orderDetailsId", getOrderDetailsId())
                .append("salesNumber", getSalesNumber())
                .append("deliveredQuantity", getDeliveredQuantity())
                .append("deliverQuantity", getDeliverQuantity())
                .append("deliveryAmount", getDeliveryAmount())
                .append("totalWeight", getTotalWeight())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
