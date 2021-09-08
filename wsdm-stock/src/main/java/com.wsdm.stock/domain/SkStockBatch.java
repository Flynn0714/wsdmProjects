package com.wsdm.stock.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 库存批次对象 sk_stock_batch
 *
 * @author wsdm
 * @date 2020-12-07
 */
public class SkStockBatch extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 库存编码
     */
    @Excel(name = "库存编码")
    private String stockCode;

    /**
     * 库存名称
     */
    @Excel(name = "库存名称")
    private String stockName;

    /**
     * 批次编码
     */
    @Excel(name = "批次编码")
    private String batchCode;

    /**
     * 库存产品 其实是关联关系字段
     */
    @Excel(name = "库存产品")
    private String produce;

    /**
     * 产品名称 其实是关联关说明
     */
    @Excel(name = "产品名称")
    private String produceName;

    /**
     * 供应商
     */
    @Excel(name = "供应商")
    private String supplierCode;

    /**
     * 供应商名称
     */
    @Excel(name = "供应商名称")
    private String supplierName;

    /**
     * 期初库存数量
     */
    @Excel(name = "起始库存")
    private BigDecimal openingStockNum;

    /**
     * 库存总数量 整数
     */
    @Excel(name = "库存总数量")
    private BigDecimal batchTotal;

    /**
     * 库存锁定数量
     */
    @Excel(name = "库存锁定数量")
    private BigDecimal stockLockNum;

    /**
     * 库存报损数量
     */
    @Excel(name = "库存报损数量")
    private BigDecimal stockFaultyNum;

    /**
     * 库存可用数量
     */
    @Excel(name = "库存可用数量")
    private BigDecimal stockAvailableNum;

    /**
     * 批次时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "批次时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date batchTime;

    /**
     * 乐观锁标志
     */
    private int version;

    /**
     * 参数/型号
     */
    private transient String model;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setStockCode(String stockCode)
    {
        this.stockCode = stockCode;
    }

    public String getStockCode()
    {
        return stockCode;
    }

    public void setBatchCode(String batchCode)
    {
        this.batchCode = batchCode;
    }

    public String getBatchCode()
    {
        return batchCode;
    }

    public void setProduce(String produce)
    {
        this.produce = produce;
    }

    public String getProduce()
    {
        return produce;
    }

    public String getStockName()
    {
        return stockName;
    }

    public void setStockName(String stockName)
    {
        this.stockName = stockName;
    }

    public String getProduceName()
    {
        return produceName;
    }

    public void setProduceName(String produceName)
    {
        this.produceName = produceName;
    }

    public void setSupplierCode(String supplierCode)
    {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode()
    {
        return supplierCode;
    }

    public void setBatchTotal(BigDecimal batchTotal)
    {
        this.batchTotal = batchTotal;
    }

    public BigDecimal getBatchTotal()
    {
        return batchTotal;
    }

    public void setStockLockNum(BigDecimal stockLockNum)
    {
        this.stockLockNum = stockLockNum;
    }

    public BigDecimal getStockLockNum()
    {
        return stockLockNum;
    }

    public void setStockFaultyNum(BigDecimal stockFaultyNum)
    {
        this.stockFaultyNum = stockFaultyNum;
    }

    public BigDecimal getStockFaultyNum()
    {
        return stockFaultyNum;
    }

    public void setStockAvailableNum(BigDecimal stockAvailableNum)
    {
        this.stockAvailableNum = stockAvailableNum;
    }

    public BigDecimal getStockAvailableNum()
    {
        return stockAvailableNum;
    }

    public void setBatchTime(Date batchTime)
    {
        this.batchTime = batchTime;
    }

    public Date getBatchTime()
    {
        return batchTime;
    }

    public void setVersion(int version)
    {
        this.version = version;
    }

    public int getVersion()
    {
        return version;
    }

    public BigDecimal getOpeningStockNum()
    {
        return openingStockNum;
    }

    public void setOpeningStockNum(BigDecimal openingStockNum)
    {
        this.openingStockNum = openingStockNum;
    }

    public String getSupplierName()
    {
        return supplierName;
    }

    public void setSupplierName(String supplierName)
    {
        this.supplierName = supplierName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", getId())
                                                                        .append("stockCode", getStockCode())
                                                                        .append("batchCode", getBatchCode())
                                                                        .append("produce", getProduce())
                                                                        .append("stockName", getStockName())
                                                                        .append("produceName", getProduceName())
                                                                        .append("supplierCode", getSupplierCode())
                                                                        .append("supplierName", getSupplierName())
                                                                        .append("openingStockNum", getOpeningStockNum())
                                                                        .append("batchTotal", getBatchTotal())
                                                                        .append("stockLockNum", getStockLockNum())
                                                                        .append("stockFaultyNum", getStockFaultyNum())
                                                                        .append("stockAvailableNum",
                                                                                getStockAvailableNum())
                                                                        .append("batchTime", getBatchTime())
                                                                        .append("version", getVersion())
                                                                        .append("remark", getRemark())
                                                                        .append("createBy", getCreateBy())
                                                                        .append("createTime", getCreateTime())
                                                                        .append("updateBy", getUpdateBy())
                                                                        .append("updateTime", getUpdateTime())
                                                                        .toString();
    }
}
