package com.wsdm.stock.domain;


import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 库存主对象 sk_stock
 *
 * @author wsdm
 * @date 2020-12-07
 */
public class SkStock extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 库存编码
     */
    @Excel(name = "库存编码")
    private String code;

    /**
     * 库存名称
     */
    @Excel(name = "仓库名称")
    private String name;

    /**
     * 库存产品 其实是关联关系字段
     */
    @Excel(name = "产品编号")
    private String produce;

    /**
     * 产品名称 其实是关联关说明
     */
    @Excel(name = "产品名称")
    private String produceName;

    /**
     * 期初库存数量
     */
    @Excel(name = "起始库存")
    private BigDecimal openingStockNum;

    /**
     * 历史累计库存
     */
    @Excel(name = "累计库存")
    private BigDecimal historyStockNum;

    /**
     * 库存总数量 整数
     */
    @Excel(name = "库存总数量")
    private BigDecimal stockTotal;

    /**
     * 在途数量 整数
     */
    @Excel(name = "在途数量")
    private BigDecimal stockTransitNum;

    /**
     * 库存锁定数量
     */
    @Excel(name = "锁定数量")
    private BigDecimal stockLockNum;

    /**
     * 库存报损数量
     */
    @Excel(name = "报损数量")
    private BigDecimal stockFaultyNum;

    /**
     * 库存可用数量
     */
    @Excel(name = "可用数量")
    private BigDecimal stockAvailableNum;

    /**
     * 乐观锁标志
     */
    private int version;

    /**
     * 供应商编号
     */
    private String supplierCode;

    /**
     * 参数/型号
     */
    private transient String model;

    private transient Boolean onlyAvailable;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getProduce() {
        return produce;
    }

    public String getProduceName() {
        return produceName;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName;
    }

    public void setStockTotal(BigDecimal stockTotal) {
        this.stockTotal = stockTotal;
    }

    public BigDecimal getStockTotal() {
        return stockTotal;
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

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public BigDecimal getStockTransitNum() {
        return stockTransitNum;
    }

    public void setStockTransitNum(BigDecimal stockTransitNum) {
        this.stockTransitNum = stockTransitNum;
    }

    public BigDecimal getOpeningStockNum() {
        return openingStockNum;
    }

    public void setOpeningStockNum(BigDecimal openingStockNum) {
        this.openingStockNum = openingStockNum;
    }

    public BigDecimal getHistoryStockNum() {
        return historyStockNum;
    }

    public void setHistoryStockNum(BigDecimal historyStockNum) {
        this.historyStockNum = historyStockNum;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getOnlyAvailable() {
        return onlyAvailable;
    }

    public void setOnlyAvailable(Boolean onlyAvailable) {
        this.onlyAvailable = onlyAvailable;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", getId())
                .append("code", getCode())
                .append("name", getName())
                .append("produce", getProduce())
                .append("produceName", getProduceName())
                .append("stockTotal", getStockTotal())
                .append("stockTransitNum", getStockTransitNum())
                .append("stockLockNum", getStockLockNum())
                .append("stockFaultyNum", getStockFaultyNum())
                .append("stockAvailableNum",
                        getStockAvailableNum())
                .append("version", getVersion())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
