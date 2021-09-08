package com.wsdm.pd.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 物料领取单对象 pd_material_receiving
 *
 * @author wsdm
 * @date 2021-05-11
 */
public class PdMaterialReceiving extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 领取单号
     */
    @Excel(name = "领取单号")
    private String receivingCode;

    /**
     * 领取仓库
     */
    @Excel(name = "领取仓库编号")
    private String warehouseCode;

    /**
     * 领取仓库
     */
    @Excel(name = "领取仓库")
    private String warehouseName;

    /**
     * 领取人
     */
    @Excel(name = "领取人")
    private String receiver;

    /**
     * 领取部门
     */
    @Excel(name = "领取部门")
    private String receivingDept;

    /**
     * 领取部门
     */
    @Excel(name = "领取部门名称")
    private String receivingDeptName;

    /**
     * 领取时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "领取时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiveTime;

    /**
     * 领取总数量
     */
    @Excel(name = "领取总数量")
    private BigDecimal totalQuantity;

    /**
     * 状态：0 - 删除，1 - 待审核，2 - 已审核
     */
    @Excel(name = "状态：0 - 删除，1 - 待审核，2 - 已审核，3 - 待提交")
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

    @Excel(name = "供应商编码")
    private String supplierCode;

    @Excel(name = "供应商名称")
    private String supplierName;

    private transient String salesNumber;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setReceivingCode(String receivingCode) {
        this.receivingCode = receivingCode;
    }

    public String getReceivingCode() {
        return receivingCode;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceivingDept(String receivingDept) {
        this.receivingDept = receivingDept;
    }

    public String getReceivingDept() {
        return receivingDept;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setTotalQuantity(BigDecimal totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getTotalQuantity() {
        return totalQuantity;
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

    public String getReceivingDeptName() {
        return receivingDeptName;
    }

    public void setReceivingDeptName(String receivingDeptName) {
        this.receivingDeptName = receivingDeptName;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", getId())
                .append("receivingCode", getReceivingCode())
                .append("warehouseName", getWarehouseName())
                .append("receiver", getReceiver())
                .append("receivingDept", getReceivingDept())
                .append("receiveTime", getReceiveTime())
                .append("totalQuantity", getTotalQuantity())
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
