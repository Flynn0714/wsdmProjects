package com.wsdm.materiel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 物料收货质检单信息对象 wl_receive_quality_inspection_info
 *
 * @author wanglei
 * @date 2021-06-20
 */
public class QualityInspectionInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 质检单号
     */
    @Excel(name = "质检单号")
    private String checkNumber;

    /**
     * 收货单号
     */
    @Excel(name = "收货单号")
    private String receiveNumber;

    /**
     * 质检日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "质检日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkDate;

    /**
     * 合格率
     */
    @Excel(name = "合格率")
    private String percentPass;

    /**
     * 质检结果 1 建议拒收 2 建议收货
     */
    @Excel(name = "质检结果")
    private String checkResult;

    /**
     * 采购类型 A-原料采购 B-外协采购 D-五金采购
     */
    @Excel(name = "采购类型 A-原料采购 B-外协采购 D-五金采购")
    private String purchaseType;

    private String purchaseTypeName;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private String status;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private String statusName;

    /**
     * 审核人
     */
    @Excel(name = "审核人")
    private String auditBy;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setReceiveNumber(String receiveNumber) {
        this.receiveNumber = receiveNumber;
    }

    public String getReceiveNumber() {
        return receiveNumber;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setPercentPass(String percentPass) {
        this.percentPass = percentPass;
    }

    public String getPercentPass() {
        return percentPass;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy;
    }

    public String getAuditBy() {
        return auditBy;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public String getPurchaseTypeName() {
        return purchaseTypeName;
    }

    public void setPurchaseTypeName(String purchaseTypeName) {
        this.purchaseTypeName = purchaseTypeName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("checkNumber", getCheckNumber())
                .append("receiveNumber", getReceiveNumber())
                .append("checkDate", getCheckDate())
                .append("percentPass", getPercentPass())
                .append("checkResult", getCheckResult())
                .append("purchaseType", getPurchaseType())
                .append("remark", getRemark())
                .append("status", getStatus())
                .append("auditBy", getAuditBy())
                .append("auditTime", getAuditTime())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
