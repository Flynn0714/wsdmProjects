package com.wsdm.stock.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 库存盘点信息对象 sk_stock_inventory_info
 *
 * @author wsdm
 * @date 2021-01-22
 */
public class StockInventoryInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 盘点单号
     */
    @Excel(name = "盘点单号")
    private String inventoryNumber;

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
     * 物料类型
     */
    @Excel(name = "物料类型")
    private String materialType;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private String status;

    /**
     * 开始操作人
     */
    @Excel(name = "开始操作人")
    private String startOperator;

    /**
     * 录入操作人
     */
    @Excel(name = "录入操作人")
    private String entryOperator;

    /**
     * 登账操作人
     */
    @Excel(name = "登账操作人")
    private String bookingOperator;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 录入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "录入时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date entryTime;

    /**
     * 登账时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "登账时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date bookingTime;

    /**
     * 盘点日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "盘点日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inventoryDate;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
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

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStartOperator(String startOperator) {
        this.startOperator = startOperator;
    }

    public String getStartOperator() {
        return startOperator;
    }

    public void setEntryOperator(String entryOperator) {
        this.entryOperator = entryOperator;
    }

    public String getEntryOperator() {
        return entryOperator;
    }

    public void setBookingOperator(String bookingOperator) {
        this.bookingOperator = bookingOperator;
    }

    public String getBookingOperator() {
        return bookingOperator;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setInventoryDate(Date inventoryDate) {
        this.inventoryDate = inventoryDate;
    }

    public Date getInventoryDate() {
        return inventoryDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("inventoryNumber", getInventoryNumber())
                .append("stockCode", getStockCode())
                .append("stockName", getStockName())
                .append("materialType", getMaterialType())
                .append("remark", getRemark())
                .append("status", getStatus())
                .append("startOperator", getStartOperator())
                .append("entryOperator", getEntryOperator())
                .append("bookingOperator", getBookingOperator())
                .append("startTime", getStartTime())
                .append("entryTime", getEntryTime())
                .append("bookingTime", getBookingTime())
                .append("inventoryDate", getInventoryDate())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
