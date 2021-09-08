package com.wsdm.stock.domain;


import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 库存调整详情对象 sk_stock_adjust_detail
 *
 * @author wsdm
 * @date 2021-01-20
 */
public class StockAdjustDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 库调单号
     */
    @Excel(name = "库调单号")
    private String adjustNumber;

    /**
     * 批次编码
     */
    @Excel(name = "批次编码")
    private String batchCode;

    /**
     * 供应商
     */
    @Excel(name = "供应商")
    private String supplierCode;

    /**
     * 物料ID
     */
    @Excel(name = "物料ID")
    private String materialId;

    /**
     * 物料名称
     */
    @Excel(name = "物料名称")
    private String materialName;

    /**
     * 参数
     */
    @Excel(name = "参数")
    private String param;

    /**
     * 库存数量
     */
    @Excel(name = "库存数量")
    private BigDecimal stockNum;

    /**
     * 锁定数量
     */
    @Excel(name = "锁定数量")
    private BigDecimal stockLockNum;

    /**
     * 损坏数量
     */
    @Excel(name = "损坏数量")
    private BigDecimal stockFaultyNum;

    /**
     * 可用数量
     */
    @Excel(name = "可用数量")
    private BigDecimal stockAvailableNum;

    /**
     * 调整数量
     */
    @Excel(name = "调整数量")
    private BigDecimal stockAdjustNum;

    private transient String model;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setAdjustNumber(String adjustNumber)
    {
        this.adjustNumber = adjustNumber;
    }

    public String getAdjustNumber()
    {
        return adjustNumber;
    }

    public void setBatchCode(String batchCode)
    {
        this.batchCode = batchCode;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    public void setStockNum(BigDecimal stockNum) {
        this.stockNum = stockNum;
    }

    public BigDecimal getStockNum() {
        return stockNum;
    }

    public void setStockLockNum(BigDecimal stockLockNum) {
        this.stockLockNum = stockLockNum;
    }

    public BigDecimal getStockLockNum() {
        return stockLockNum;
    }

    public void setStockFaultyNum(BigDecimal stockFaultyNum) {
        this.stockFaultyNum = stockFaultyNum;
    }

    public BigDecimal getStockFaultyNum() {
        return stockFaultyNum;
    }

    public void setStockAvailableNum(BigDecimal stockAvailableNum) {
        this.stockAvailableNum = stockAvailableNum;
    }

    public BigDecimal getStockAvailableNum() {
        return stockAvailableNum;
    }

    public void setStockAdjustNum(BigDecimal stockAdjustNum) {
        this.stockAdjustNum = stockAdjustNum;
    }

    public BigDecimal getStockAdjustNum() {
        return stockAdjustNum;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("adjustNumber", getAdjustNumber())
                .append("batchCode", getBatchCode())
                .append("supplierCode", getSupplierCode())
                .append("materialId", getMaterialId())
                .append("materialName", getMaterialName())
                .append("param", getParam())
                .append("stockNum", getStockNum())
                .append("stockLockNum", getStockLockNum())
                .append("stockFaultyNum", getStockFaultyNum())
                .append("stockAvailableNum", getStockAvailableNum())
                .append("stockAdjustNum", getStockAdjustNum())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
