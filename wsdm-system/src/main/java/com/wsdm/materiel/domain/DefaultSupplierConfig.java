package com.wsdm.materiel.domain;

import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 默认供应商配置对象 wl_default_supplier_config
 *
 * @author wsdm
 * @date 2020-11-29
 */
public class DefaultSupplierConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 默认供应商编码
     */
    @Excel(name = "默认供应商编码")
    private String supplierCode;

    /**
     * 默认供应商名称
     */
    @Excel(name = "默认供应商名称")
    private String supplierName;

    /**
     * 采购品类名称
     */
    @Excel(name = "采购品类名称")
    private String purchaseCategoryName;

    /**
     * 采购默认供应商名称类ID-关联字典数据表半成品配件
     */
    @Excel(name = "采购默认供应商名称类ID-关联字典数据表半成品配件")
    private String purchaseCategoryId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setPurchaseCategoryName(String purchaseCategoryName) {
        this.purchaseCategoryName = purchaseCategoryName;
    }

    public String getPurchaseCategoryName() {
        return purchaseCategoryName;
    }

    public void setPurchaseCategoryId(String purchaseCategoryId) {
        this.purchaseCategoryId = purchaseCategoryId;
    }

    public String getPurchaseCategoryId() {
        return purchaseCategoryId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("supplierCode", getSupplierCode())
                .append("supplierName", getSupplierName())
                .append("purchaseCategoryName", getPurchaseCategoryName())
                .append("purchaseCategoryId", getPurchaseCategoryId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
