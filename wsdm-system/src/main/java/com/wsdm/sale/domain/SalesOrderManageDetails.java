package com.wsdm.sale.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;


/**
 * 销售订单对象 sales_order_manage_details
 *
 * @author wsdm
 * @date 2020-11-06
 */
public class SalesOrderManageDetails extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 销售单号
     */
    private String salesNumber;

    /** 物料编号 */
    @NotBlank(message = "物料编号不能为空")
    private String materialNumber;

    /**
     * 规格
     */
    private String specs;

    /**
     * 类型
     */
    @NotBlank(message = "物料类型不能为空")
    private String detailsType;

    /**
     * 数量
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal quantity = new BigDecimal(0.0);

    /**
     * 单价
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal price = new BigDecimal(0.00);

    /**
     * 小计
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal subtotal = new BigDecimal(0.00);

    private String materialName;

    private String detailsTypeName;


    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getDetailsType() {
        return detailsType;
    }

    public void setDetailsType(String detailsType) {
        this.detailsType = detailsType;
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

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
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
                .append("salesNumber", getSalesNumber())
                .append("materialNumber", getMaterialNumber())
                .append("detailsType", getDetailsType())
                .append("quantity", getQuantity())
                .append("price", getPrice())
                .append("subtotal", getSubtotal())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
