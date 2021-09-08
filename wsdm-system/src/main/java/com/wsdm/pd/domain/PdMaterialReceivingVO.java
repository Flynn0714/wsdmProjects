package com.wsdm.pd.domain;

import java.util.List;

/**
 * [功能描述]
 *
 * @author Forany
 * @date 2021/5/13 22:09
 * @see [相关类/方法](可选)
 * @since [产品/模块版本](可选)
 */
public class PdMaterialReceivingVO
{
    private PdMaterialReceiving             pdMaterialReceiving;
    private List<PdMaterialReceivingDetail> pdMaterialReceivingDetailList;

    /**
     * 审批类型：0 - 无需审批，1 - 提交审核
     */
    private String auditType;

    public PdMaterialReceiving getPdMaterialReceiving()
    {
        return pdMaterialReceiving;
    }

    public void setPdMaterialReceiving(PdMaterialReceiving pdMaterialReceiving)
    {
        this.pdMaterialReceiving = pdMaterialReceiving;
    }

    public List<PdMaterialReceivingDetail> getPdMaterialReceivingDetailList()
    {
        return pdMaterialReceivingDetailList;
    }

    public void setPdMaterialReceivingDetailList(List<PdMaterialReceivingDetail> pdMaterialReceivingDetailList)
    {
        this.pdMaterialReceivingDetailList = pdMaterialReceivingDetailList;
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
