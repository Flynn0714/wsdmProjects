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
public class PdContributionTestVO {
    private PdContributionTest pdContributionTest;
    private List<PdContributionTestDetail> pdContributionTestDetailList;

    /**
     * 审批类型：0 - 无需提交，1 - 提交审核
     */
    private String auditType;

    public PdContributionTest getPdContributionTest() {
        return pdContributionTest;
    }

    public void setPdContributionTest(PdContributionTest pdContributionTest) {
        this.pdContributionTest = pdContributionTest;
    }

    public List<PdContributionTestDetail> getPdContributionTestDetailList() {
        return pdContributionTestDetailList;
    }

    public void setPdContributionTestDetailList(List<PdContributionTestDetail> pdContributionTestDetailList) {
        this.pdContributionTestDetailList = pdContributionTestDetailList;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }
}
