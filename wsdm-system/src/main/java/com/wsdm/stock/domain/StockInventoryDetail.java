package com.wsdm.stock.domain;

import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 库存盘点详情对象 sk_stock_inventory_detail
 *
 * @author wsdm
 * @date 2021-01-22
 */
public class StockInventoryDetail extends BaseEntity {
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
    private Integer stockNum;

    /**
     * 盘点数量
     */
    @Excel(name = "盘点数量")
    private Integer inventoryNum;

    /**
     * 差异数量
     */
    @Excel(name = "差异数量")
    private Integer differencesNum;

    /**
     * 调整数量
     */
    @Excel(name = "调整数量")
    private Integer stockAdjustNum;

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

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setInventoryNum(Integer inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public Integer getInventoryNum() {
        return inventoryNum;
    }

    public void setDifferencesNum(Integer differencesNum) {
        this.differencesNum = differencesNum;
    }

    public Integer getDifferencesNum() {
        return differencesNum;
    }

    public void setStockAdjustNum(Integer stockAdjustNum) {
        this.stockAdjustNum = stockAdjustNum;
    }

    public Integer getStockAdjustNum() {
        return stockAdjustNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("inventoryNumber", getInventoryNumber())
                .append("materialId", getMaterialId())
                .append("materialName", getMaterialName())
                .append("param", getParam())
                .append("stockNum", getStockNum())
                .append("inventoryNum", getInventoryNum())
                .append("differencesNum", getDifferencesNum())
                .append("stockAdjustNum", getStockAdjustNum())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
