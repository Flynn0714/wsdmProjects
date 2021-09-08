package com.wsdm.stock.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 库存批次锁定记录对象 sk_stock_batch_lock
 *
 * @author wsdm
 * @date 2020-12-07
 */
public class SkStockBatchLock extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 库存编码
     */
    private String stockCode;

    private String stockName;

    /**
     * 批次编码
     */
    private String batchCode;

    /**
     * 库存产品 其实是关联关系字段
     */
    private String produce;

    /**
     * 产品名称 其实是关联关说明
     */
    private String produceName;

    /**
     * 供应商
     */
    private String supplierCode;

    /**
     * 库存锁定数量
     */
    private BigDecimal stockLockNum;

    /**
     * 锁定单号
     */
    private String lockNumber;

    /**
     * 锁定单号类型
     */
    private String lockType;

    /**
     * 批次时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date unlockTime;

    /**
     * 备注
     */
    private String lockRemark;

    /**
     * 备注
     */
    private String unlockRemark;

    /**
     * 是否锁定 1锁定 0未锁定 默认为1
     */
    private String isLock;

    /**
     * 乐观锁标志
     */
    private int version;

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

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public String getProduce() {
        return produce;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getProduceName() {
        return produceName;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setStockLockNum(BigDecimal stockLockNum) {
        this.stockLockNum = stockLockNum;
    }

    public BigDecimal getStockLockNum() {
        return stockLockNum;
    }

    public void setLockNumber(String lockNumber) {
        this.lockNumber = lockNumber;
    }

    public String getLockNumber() {
        return lockNumber;
    }

    public void setLockType(String lockType) {
        this.lockType = lockType;
    }

    public String getLockType() {
        return lockType;
    }

    public void setUnlockTime(Date unlockTime) {
        this.unlockTime = unlockTime;
    }

    public Date getUnlockTime() {
        return unlockTime;
    }

    public void setLockRemark(String lockRemark) {
        this.lockRemark = lockRemark;
    }

    public String getLockRemark() {
        return lockRemark;
    }

    public void setUnlockRemark(String unlockRemark) {
        this.unlockRemark = unlockRemark;
    }

    public String getUnlockRemark() {
        return unlockRemark;
    }

    public void setIsLock(String isLock) {
        this.isLock = isLock;
    }

    public String getIsLock() {
        return isLock;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("stockCode", getStockCode())
                .append("batchCode", getBatchCode())
                .append("produce", getProduce())
                .append("stockName", getStockName())
                .append("produceName", getProduceName())
                .append("supplierCode", getSupplierCode())
                .append("stockLockNum", getStockLockNum())
                .append("lockNumber", getLockNumber())
                .append("lockType", getLockType())
                .append("unlockTime", getUnlockTime())
                .append("lockRemark", getLockRemark())
                .append("unlockRemark", getUnlockRemark())
                .append("isLock", getIsLock())
                .append("version", getVersion())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
