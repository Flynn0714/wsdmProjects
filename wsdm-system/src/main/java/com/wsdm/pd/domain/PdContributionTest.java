package com.wsdm.pd.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 加工质检单对象 pd_contribution_test
 *
 * @author wsdm
 * @date 2021-06-18
 */
public class PdContributionTest extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 单号
     */
    @Excel(name = "单号")
    private String code;

    /**
     * 质检单号
     */
    @Excel(name = "质检单号")
    private String testCode;

    /**
     * 员工姓名
     */
    @Excel(name = "员工姓名")
    private String employeeName;

    /**
     * 部门编号
     */
    @Excel(name = "部门编号")
    private String deptCode;

    /**
     * 部门名称
     */
    @Excel(name = "部门名称")
    private String deptName;

    /**
     * 物料类型编码
     */
    @Excel(name = "物料类型编码")
    private String materialType;

    /**
     * 物料类型名称
     */
    @Excel(name = "物料类型名称")
    private String materialTypeName;

    /**
     * 质检人
     */
    @Excel(name = "质检人")
    private String tester;

    /**
     * 质检时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "质检时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date testTime;

    /**
     * 状态：0 - 删除，1 - 待审核，2 - 已审核，3 - 待提交，4 - 待质检
     */
    @Excel(name = "状态：0 - 删除，1 - 待审核，2 - 已审核，3 - 待提交，4 - 待质检，5 - 已驳回")
    private String status;

    /**
     * 审核人
     */
    @Excel(name = "审核人")
    private String auditor;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditTime;

    /**
     * 质检日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "质检日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date testDate;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public String getTester() {
        return tester;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public Date getTestDate()
    {
        return testDate;
    }

    public void setTestDate(Date testDate)
    {
        this.testDate = testDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("code", getCode())
                .append("testCode", getTestCode())
                .append("employeeName", getEmployeeName())
                .append("deptCode", getDeptCode())
                .append("deptName", getDeptName())
                .append("materialType", getMaterialType())
                .append("materialTypeName", getMaterialTypeName())
                .append("tester", getTester())
                .append("testTime", getTestTime())
                .append("remark", getRemark())
                .append("status", getStatus())
                .append("auditor", getAuditor())
                .append("auditTime", getAuditTime())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
