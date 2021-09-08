package com.wsdm.pd.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 产品关联实体类
 */
public class PdAssociation {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 产品编码
     */
    private String materialNumber;

    /**
     * 产品名称
     */
    private String materialName;

    /**
     * 产品类型编号
     */
    private String detailsType;

    /**
     * 产品类型编号名称
     */
    private String detailsTypeName;

    /**
     * 规格
     */
    private String specs;

    /**
     * 关联产品id
     */
    private String pdId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态  0未关联 1已关联 2已删除
     */
    private Character status;

    /**
     * 附件名称
     */
    private String annexName;

    /**
     * 附件路径
     */
    private String annexUrl;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:SS")
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:SS")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getDetailsType() {
        return detailsType;
    }

    public void setDetailsType(String detailsType) {
        this.detailsType = detailsType;
    }

    public String getDetailsTypeName() {
        return detailsTypeName;
    }

    public void setDetailsTypeName(String detailsTypeName) {
        this.detailsTypeName = detailsTypeName;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getPdId() {
        return pdId;
    }

    public void setPdId(String pdId) {
        this.pdId = pdId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public String getAnnexName() {
        return annexName;
    }

    public void setAnnexName(String annexName) {
        this.annexName = annexName;
    }

    public String getAnnexUrl() {
        return annexUrl;
    }

    public void setAnnexUrl(String annexUrl) {
        this.annexUrl = annexUrl;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    @Override
    public String toString() {
        return "PdAssociation{" +
                "id=" + id +
                ", materialNumber='" + materialNumber + '\'' +
                ", materialName='" + materialName + '\'' +
                ", detailsType='" + detailsType + '\'' +
                ", detailsTypeName='" + detailsTypeName + '\'' +
                ", specs='" + specs + '\'' +
                ", pdId='" + pdId + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", annexName='" + annexName + '\'' +
                ", annexUrl='" + annexUrl + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
