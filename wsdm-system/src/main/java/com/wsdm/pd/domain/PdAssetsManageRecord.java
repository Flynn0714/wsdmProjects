package com.wsdm.pd.domain;

import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 资产管理更新记录对象 pd_assets_manage_detail
 *
 * @author wsdm
 * @date 2021-06-28
 */
public class PdAssetsManageRecord extends BaseEntity
{
    private static final long serialVersionUID=1L;

    /** 主键 */
    private Long id;

    /** 资产编号 */
    @Excel(name = "资产编号")
    private String assetNumber;

    /** 资产名称 */
    @Excel(name = "资产名称")
    private String assetName;

    /** 型号 */
    @Excel(name = "型号")
    private String assetModel;

    /** 资产数量 */
    @Excel(name = "资产数量")
    private BigDecimal assetQuantity;

    /** 报废数量 */
    @Excel(name = "报废数量")
    private BigDecimal scrapQuantity;

    /** 维修数量 */
    @Excel(name = "维修数量")
    private BigDecimal repairQuantity;

    /** 可用数量 */
    @Excel(name = "可用数量")
    private BigDecimal usableQuantity;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAssetNumber(String assetNumber)
    {
        this.assetNumber = assetNumber;
    }

    public String getAssetNumber()
    {
        return assetNumber;
    }
    public void setAssetName(String assetName)
    {
        this.assetName = assetName;
    }

    public String getAssetName()
    {
        return assetName;
    }
    public void setAssetModel(String assetModel)
    {
        this.assetModel = assetModel;
    }

    public String getAssetModel()
    {
        return assetModel;
    }
    public void setAssetQuantity(BigDecimal assetQuantity)
    {
        this.assetQuantity = assetQuantity;
    }

    public BigDecimal getAssetQuantity()
    {
        return assetQuantity;
    }
    public void setScrapQuantity(BigDecimal scrapQuantity)
    {
        this.scrapQuantity = scrapQuantity;
    }

    public BigDecimal getScrapQuantity()
    {
        return scrapQuantity;
    }
    public void setRepairQuantity(BigDecimal repairQuantity)
    {
        this.repairQuantity = repairQuantity;
    }

    public BigDecimal getRepairQuantity()
    {
        return repairQuantity;
    }
    public void setUsableQuantity(BigDecimal usableQuantity)
    {
        this.usableQuantity = usableQuantity;
    }

    public BigDecimal getUsableQuantity()
    {
        return usableQuantity;
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id",getId())
                .append("assetNumber",getAssetNumber())
                .append("assetName",getAssetName())
                .append("assetModel",getAssetModel())
                .append("assetQuantity",getAssetQuantity())
                .append("scrapQuantity",getScrapQuantity())
                .append("repairQuantity",getRepairQuantity())
                .append("usableQuantity",getUsableQuantity())
                .append("remark",getRemark())
                .append("createBy",getCreateBy())
                .append("createTime",getCreateTime())
                .append("updateBy",getUpdateBy())
                .append("updateTime",getUpdateTime())
                .toString();
    }
}