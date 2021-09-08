package com.wsdm.pd.domain;

import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 物料领取单明细对象 pd_material_receiving_detail
 *
 * @author wsdm
 * @date 2021-05-11
 */
public class PdMaterialReceivingDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 物料领取单ID
     */
    @Excel(name = "物料领取单ID")
    private Long receivingId;

    /**
     * 物料编号
     */
    @Excel(name = "物料编号")
    private String materialCode;

    /**
     * 物料名称
     */
    @Excel(name = "物料名称")
    private String materialName;

    /**
     * 可用数量
     */
    @Excel(name = "可用数量")
    private BigDecimal avaliableQuantity;

    /**
     * 领取数量
     */
    @Excel(name = "领取数量")
    private BigDecimal receiveQuantity;

    /**
     * 状态：0 - 删除，1 - 正常
     */
    @Excel(name = "状态：0 - 删除，1 - 正常")
    private String status;

    /**
     * 型号/参数
     */
    @Excel(name = "型号/参数")
    private String model;

    /**
     * 工序
     */
    @Excel(name = "工序")
    private String processName;

    private transient String typeCode;

    private transient String materialQuality;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setReceivingId(Long receivingId)
    {
        this.receivingId = receivingId;
    }

    public Long getReceivingId()
    {
        return receivingId;
    }

    public void setMaterialCode(String materialCode)
    {
        this.materialCode = materialCode;
    }

    public String getMaterialCode()
    {
        return materialCode;
    }

    public void setMaterialName(String materialName)
    {
        this.materialName = materialName;
    }

    public String getMaterialName()
    {
        return materialName;
    }

    public void setAvaliableQuantity(BigDecimal avaliableQuantity)
    {
        this.avaliableQuantity = avaliableQuantity;
    }

    public BigDecimal getAvaliableQuantity()
    {
        return avaliableQuantity;
    }

    public void setReceiveQuantity(BigDecimal receiveQuantity)
    {
        this.receiveQuantity = receiveQuantity;
    }

    public BigDecimal getReceiveQuantity()
    {
        return receiveQuantity;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getModel()
    {
        return this.model;
    }

    public String getTypeCode()
    {
        return typeCode;
    }

    public void setTypeCode(String typeCode)
    {
        this.typeCode = typeCode;
    }

    public String getMaterialQuality()
    {
        return materialQuality;
    }

    public void setMaterialQuality(String materialQuality)
    {
        this.materialQuality = materialQuality;
    }

    public String getProcessName()
    {
        return processName;
    }

    public void setProcessName(String processName)
    {
        this.processName = processName;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", getId())
                                                                        .append("receivingId", getReceivingId())
                                                                        .append("materialCode", getMaterialCode())
                                                                        .append("materialName", getMaterialName())
                                                                        .append("avaliableQuantity",
                                                                                getAvaliableQuantity())
                                                                        .append("receiveQuantity", getReceiveQuantity())
                                                                        .append("status", getStatus())
                                                                        .append("createBy", getCreateBy())
                                                                        .append("createTime", getCreateTime())
                                                                        .append("updateBy", getUpdateBy())
                                                                        .append("updateTime", getUpdateTime())
                                                                        .toString();
    }
}
