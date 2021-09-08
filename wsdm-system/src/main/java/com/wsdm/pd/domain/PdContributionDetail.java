package com.wsdm.pd.domain;

import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 工绩单明细对象 pd_contribution_detail
 *
 * @author wsdm
 * @date 2021-06-16
 */
public class PdContributionDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 工绩单ID
     */
    @Excel(name = "工绩单ID")
    private Long contributionId;

    /**
     * 物料编码
     */
    @Excel(name = "物料编码")
    private String materialCode;

    /**
     * 物料名称
     */
    @Excel(name = "物料名称")
    private String materialName;

    /**
     * 加工数量
     */
    @Excel(name = "加工数量")
    private BigDecimal quantity;

    /**
     * 加工价格
     */
    @Excel(name = "加工价格")
    private BigDecimal costTime;

    /**
     * 加工总价格
     */
    @Excel(name = "加工总价格")
    private BigDecimal costTimeTotal;

    /**
     * 质检数量
     */
    @Excel(name = "质检数量")
    private BigDecimal quantityTest;

    /**
     * 合格数量
     */
    @Excel(name = "合格数量")
    private BigDecimal quantityPass;

    /**
     * 报废数量
     */
    @Excel(name = "报废数量")
    private BigDecimal quantityFault;

    /**
     * 重加工数量
     */
    @Excel(name = "重加工数量")
    private BigDecimal quantityRework;

    /**
     * 状态：0 - 删除，1 - 正常
     */
    @Excel(name = "状态：0 - 删除，1 - 正常")
    private String status;

    /**
     * 计价方式：1 - 按件，2 - 按重量
     */
    @Excel(name = "计价方式：1 - 按件，2 - 按重量")
    private Integer priceType;

    /**
     * 工序
     */
    @Excel(name = "工序")
    private String processName;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setContributionId(Long contributionId)
    {
        this.contributionId = contributionId;
    }

    public Long getContributionId()
    {
        return contributionId;
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

    public void setQuantity(BigDecimal quantity)
    {
        this.quantity = quantity;
    }

    public BigDecimal getQuantity()
    {
        return quantity;
    }

    public void setCostTime(BigDecimal costTime)
    {
        this.costTime = costTime;
    }

    public BigDecimal getCostTime()
    {
        return costTime;
    }

    public void setQuantityTest(BigDecimal quantityTest)
    {
        this.quantityTest = quantityTest;
    }

    public BigDecimal getQuantityTest()
    {
        return quantityTest;
    }

    public void setQuantityPass(BigDecimal quantityPass)
    {
        this.quantityPass = quantityPass;
    }

    public BigDecimal getQuantityPass()
    {
        return quantityPass;
    }

    public void setQuantityFault(BigDecimal quantityFault)
    {
        this.quantityFault = quantityFault;
    }

    public BigDecimal getQuantityFault()
    {
        return quantityFault;
    }

    public void setQuantityRework(BigDecimal quantityRework)
    {
        this.quantityRework = quantityRework;
    }

    public BigDecimal getQuantityRework()
    {
        return quantityRework;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public BigDecimal getCostTimeTotal()
    {
        return costTimeTotal;
    }

    public void setCostTimeTotal(BigDecimal costTimeTotal)
    {
        this.costTimeTotal = costTimeTotal;
    }

    public Integer getPriceType()
    {
        return priceType;
    }

    public void setPriceType(Integer priceType)
    {
        this.priceType = priceType;
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
                                                                        .append("contributionId", getContributionId())
                                                                        .append("materialCode", getMaterialCode())
                                                                        .append("materialName", getMaterialName())
                                                                        .append("quantity", getQuantity())
                                                                        .append("costTime", getCostTime())
                                                                        .append("quantityTest", getQuantityTest())
                                                                        .append("quantityPass", getQuantityPass())
                                                                        .append("quantityFault", getQuantityFault())
                                                                        .append("quantityRework", getQuantityRework())
                                                                        .append("status", getStatus())
                                                                        .append("createBy", getCreateBy())
                                                                        .append("createTime", getCreateTime())
                                                                        .append("updateBy", getUpdateBy())
                                                                        .append("updateTime", getUpdateTime())
                                                                        .toString();
    }
}
