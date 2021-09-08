package com.wsdm.pd.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 工绩单对象 pd_contribution
 *
 * @author wsdm
 * @date 2021-06-16
 */
public class PdContribution extends BaseEntity
{
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
     * 员工姓名
     */
    @Excel(name = "员工姓名")
    private String employeeName;

    /**
     * 加工时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "加工时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date processTime;

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
     * 总加工数量
     */
    @Excel(name = "总加工数量")
    private BigDecimal totalQuantity;

    /**
     * 是否需要质检：0 - 不需要，1 - 需要
     */
    @Excel(name = "是否需要质检：0 - 不需要，1 - 需要")
    private String test;

    /**
     * 质检单号
     */
    @Excel(name = "质检单号")
    private String testCode;

    /**
     * 质检单ID
     */
    @Excel(name = "质检单ID")
    private Long testId;

    /**
     * 状态：0 - 删除，1 - 待审核，2 - 已审核，3 - 待提交，4 - 待质检
     */
    @Excel(name = "状态：0 - 删除，1 - 待审核，2 - 已审核，3 - 待提交，4 - 待质检")
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

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }

    public void setEmployeeName(String employeeName)
    {
        this.employeeName = employeeName;
    }

    public String getEmployeeName()
    {
        return employeeName;
    }

    public void setProcessTime(Date processTime)
    {
        this.processTime = processTime;
    }

    public Date getProcessTime()
    {
        return processTime;
    }

    public void setDeptCode(String deptCode)
    {
        this.deptCode = deptCode;
    }

    public String getDeptCode()
    {
        return deptCode;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public String getDeptName()
    {
        return deptName;
    }

    public void setMaterialType(String materialType)
    {
        this.materialType = materialType;
    }

    public String getMaterialType()
    {
        return materialType;
    }

    public void setMaterialTypeName(String materialTypeName)
    {
        this.materialTypeName = materialTypeName;
    }

    public String getMaterialTypeName()
    {
        return materialTypeName;
    }

    public void setTotalQuantity(BigDecimal totalQuantity)
    {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getTotalQuantity()
    {
        return totalQuantity;
    }

    public void setTest(String test)
    {
        this.test = test;
    }

    public String getTest()
    {
        return test;
    }

    public void setTestCode(String testCode)
    {
        this.testCode = testCode;
    }

    public String getTestCode()
    {
        return testCode;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setAuditor(String auditor)
    {
        this.auditor = auditor;
    }

    public String getAuditor()
    {
        return auditor;
    }

    public void setAuditTime(Date auditTime)
    {
        this.auditTime = auditTime;
    }

    public Date getAuditTime()
    {
        return auditTime;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", getId())
                                                                        .append("code", getCode())
                                                                        .append("employeeName", getEmployeeName())
                                                                        .append("processTime", getProcessTime())
                                                                        .append("deptCode", getDeptCode())
                                                                        .append("deptName", getDeptName())
                                                                        .append("materialType", getMaterialType())
                                                                        .append("materialTypeName",
                                                                                getMaterialTypeName())
                                                                        .append("totalQuantity", getTotalQuantity())
                                                                        .append("test", getTest())
                                                                        .append("testCode", getTestCode())
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
