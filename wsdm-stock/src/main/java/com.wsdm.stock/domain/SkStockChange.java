package com.wsdm.stock.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 库存变动记录对象 sk_stock_change
 *
 * @author wsdm
 * @date 2020-12-07
 */
public class SkStockChange extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 库存编码
     */
    private String stockCode;

    /**
     * 库存名称
     */
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
     * 变动数量 整数
     */
    private BigDecimal changeNum;

    /**
     * 变更时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date changeTime;

    /**
     * 操作类型
     */
    private String operation;

    /**
     * 操作说明
     */
    private String operationName;

    /**
     * 变动单号
     */
    private String relationNumber;

    /**
     * 变动编号
     */
    private String relationCode;

    /**
     * 变动说明
     */
    private String relationName;

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

    public void setProduce(String produce)
    {
        this.produce = produce;
    }

    public String getProduce()
    {
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

    public void setChangeNum(BigDecimal changeNum) {
        this.changeNum = changeNum;
    }

    public BigDecimal getChangeNum() {
        return changeNum;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setRelationNumber(String relationNumber) {
        this.relationNumber = relationNumber;
    }

    public String getRelationNumber() {
        return relationNumber;
    }

    public void setRelationCode(String relationCode) {
        this.relationCode = relationCode;
    }

    public String getRelationCode() {
        return relationCode;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("stockCode", getStockCode())
                .append("stockName", getStockName())
                .append("batchCode", getBatchCode())
                .append("produce", getProduce())
                .append("produceName", getProduceName())
                .append("changeNum", getChangeNum())
                .append("changeTime", getChangeTime())
                .append("operation", getOperation())
                .append("operationName", getOperationName())
                .append("relationNumber", getRelationNumber())
                .append("relationCode", getRelationCode())
                .append("relationName", getRelationName())
                .append("version", getVersion())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
