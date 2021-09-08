package com.wsdm.materiel.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 采购供应商合同对象 purchase_supplier_contract_change_detail
 *
 * @author wanglei
 * @date 2021-04-29
 */
public class SupplierContractChangeDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;

    /**
     * 供应商合同变更编号
     */
    private String changeNumber;

    /**
     * 物料编号
     */
    private String materialNumber;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 型号/参数
     */
    private String modelParam;

    /**
     * 采购单位
     */
    private String unit;

    /**
     * 原合同单价
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal originalPrice;

    /**
     * 变更合同单价
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal changePrice;

    public String getModelParam() {
        return modelParam;
    }

    public void setModelParam(String modelParam) {
        this.modelParam = modelParam;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getChangeNumber() {
        return changeNumber;
    }

    public void setChangeNumber(String changeNumber) {
        this.changeNumber = changeNumber;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(BigDecimal changePrice) {
        this.changePrice = changePrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("changeNumber", getChangeNumber())
                .append("materialNumber", getMaterialNumber())
                .append("materialName", getMaterialName())
                .append("unit", getUnit())
                .append("originalPrice", getOriginalPrice())
                .append("changePrice", getChangePrice())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
