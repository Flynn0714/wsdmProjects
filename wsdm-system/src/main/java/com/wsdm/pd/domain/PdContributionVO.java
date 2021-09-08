package com.wsdm.pd.domain;

import java.util.List;

/**
 * [功能描述]
 *
 * @author Forany
 * @date 2021/5/13 23:42
 * @see [相关类/方法](可选)
 * @since [产品/模块版本](可选)
 */
public class PdContributionVO
{
    private PdContribution             pdContribution;
    private List<PdContributionDetail> pdContributionDetailList;

    /**
     * 审批类型：0 - 无需提交，1 - 提交质检
     */
    private String auditType;

    public PdContribution getPdContribution()
    {
        return pdContribution;
    }

    public void setPdContribution(PdContribution pdContribution)
    {
        this.pdContribution = pdContribution;
    }

    public List<PdContributionDetail> getPdContributionDetailList()
    {
        return pdContributionDetailList;
    }

    public void setPdContributionDetailList(List<PdContributionDetail> pdContributionDetailList)
    {
        this.pdContributionDetailList = pdContributionDetailList;
    }

    public String getAuditType()
    {
        return auditType;
    }

    public void setAuditType(String auditType)
    {
        this.auditType = auditType;
    }
}
