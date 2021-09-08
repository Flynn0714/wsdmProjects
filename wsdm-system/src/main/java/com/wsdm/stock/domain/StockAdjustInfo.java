package com.wsdm.stock.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 库存调整信息对象 sk_stock_adjust_info
 *
 * @author wsdm
 * @date 2021-01-20
 */
public class StockAdjustInfo extends BaseEntity {
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
     * 库存编码
     */
    @Excel(name = "库存编码")
    private String stockCode;

    /**
     * 仓库名称
     */
    @Excel(name = "仓库名称")
    private String stockName;

    /**
     * 库调类型
     */
    @Excel(name = "库调类型")
    private String adjustType;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private String status;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date auditTime;

    @Excel(name = "审核人")
    private String auditBy;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAdjustNumber(String adjustNumber) {
        this.adjustNumber = adjustNumber;
    }

    public String getAdjustNumber() {
        return adjustNumber;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setAdjustType(String adjustType) {
        this.adjustType = adjustType;
    }

    public String getAdjustType() {
        return adjustType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public String getAuditBy() {
        return auditBy;
    }

    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("adjustNumber", getAdjustNumber())
                .append("stockCode", getStockCode())
                .append("stockName", getStockName())
                .append("adjustType", getAdjustType())
                .append("remark", getRemark())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("auditTime", getAuditTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
