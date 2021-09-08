package com.wsdm.stock.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 物料收货订单信息对象 sk_material_receive
 *
 * @author wsdm
 * @date 2021-01-12
 */
public class MaterialReceive extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 领取单号
     */
    private String materialNumber;

    /**
     * 领取仓库
     */
    private String materialStock;

    /**
     * 领取仓库名称
     */
    private String materialStockName;

    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 生产计划
     */
    private String productionPlan;

    /**
     * 领取人
     */
    private String receivePerson;

    /**
     * 领取部门
     */
    private String receiveDept;

    /**
     * 领取时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receiveTime;

    /**
     * 领取数量
     */
    private Integer receiveTotal;

    /**
     * 状态
     */
    private String status;

    /**
     * 审核人
     */
    private String approvePerson;

    private List<MaterialReceiveDetail> materialReceiveDetails;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approveTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialStock(String materialStock) {
        this.materialStock = materialStock;
    }

    public String getMaterialStock() {
        return materialStock;
    }

    public void setMaterialStockName(String materialStockName) {
        this.materialStockName = materialStockName;
    }

    public String getMaterialStockName() {
        return materialStockName;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setProductionPlan(String productionPlan) {
        this.productionPlan = productionPlan;
    }

    public String getProductionPlan() {
        return productionPlan;
    }

    public void setReceivePerson(String receivePerson) {
        this.receivePerson = receivePerson;
    }

    public String getReceivePerson() {
        return receivePerson;
    }

    public void setReceiveDept(String receiveDept) {
        this.receiveDept = receiveDept;
    }

    public String getReceiveDept() {
        return receiveDept;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTotal(Integer receiveTotal) {
        this.receiveTotal = receiveTotal;
    }

    public Integer getReceiveTotal() {
        return receiveTotal;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setApprovePerson(String approvePerson) {
        this.approvePerson = approvePerson;
    }

    public String getApprovePerson() {
        return approvePerson;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public List<MaterialReceiveDetail> getMaterialReceiveDetails() {
        return materialReceiveDetails;
    }

    public void setMaterialReceiveDetails(List<MaterialReceiveDetail> materialReceiveDetails) {
        this.materialReceiveDetails = materialReceiveDetails;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("materialNumber", getMaterialNumber())
                .append("materialStock", getMaterialStock())
                .append("materialStockName", getMaterialStockName())
                .append("supplierCode", getSupplierCode())
                .append("productionPlan", getProductionPlan())
                .append("receivePerson", getReceivePerson())
                .append("receiveDept", getReceiveDept())
                .append("receiveTime", getReceiveTime())
                .append("receiveTotal", getReceiveTotal())
                .append("remark", getRemark())
                .append("status", getStatus())
                .append("approvePerson", getApprovePerson())
                .append("approveTime", getApproveTime())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
