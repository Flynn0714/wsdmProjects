package com.wsdm.materiel.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;


/**
 * 物料收货质检单详情对象 wl_receive_quality_inspection_detail
 *
 * @author wanglei
 * @date 2021-06-20
 */
public class QualityInspectionDetail extends BaseEntity {
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
     * 物料编号
     */
    @Excel(name = "物料编号")
    private String materielNumber;

    /**
     * 物料名称
     */
    @Excel(name = "物料名称")
    private String materielName;

    /**
     * 规格参数
     */
    @Excel(name = "规格参数")
    private String modelParam;

    /**
     * 收货数量
     */
    @Excel(name = "收货数量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal receiveNum;

    /**
     * 质检类型 1-全检 2-抽检 3-全检
     */
    @Excel(name = "质检类型 1-全检 2-抽检 3-全检")
    private String checkType;

    private String checkTypeName;

    /**
     * 抽检数量
     */
    @Excel(name = "抽检数量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal checkNum;

    /**
     * 合格数量
     */
    @Excel(name = "合格数量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal qualifiedNum;

    /**
     * 报损数量
     */
    @Excel(name = "报损数量")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal faultyNum;

    /**
     * 半成品类型
     */
    private String wlType;

    /**
     * 材质
     */
    private String materialQuality;

    public String getWlType() {
        return wlType;
    }

    public void setWlType(String wlType) {
        this.wlType = wlType;
    }


    public String getCheckTypeName() {
        return checkTypeName;
    }

    public void setCheckTypeName(String checkTypeName) {
        this.checkTypeName = checkTypeName;
    }

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

    public void setMaterielNumber(String materielNumber) {
        this.materielNumber = materielNumber;
    }

    public String getMaterielNumber() {
        return materielNumber;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }

    public String getMaterielName() {
        return materielName;
    }

    public void setModelParam(String modelParam) {
        this.modelParam = modelParam;
    }

    public String getModelParam() {
        return modelParam;
    }

    public void setReceiveNum(BigDecimal receiveNum) {
        this.receiveNum = receiveNum;
    }

    public BigDecimal getReceiveNum() {
        return receiveNum;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckNum(BigDecimal checkNum) {
        this.checkNum = checkNum;
    }

    public BigDecimal getCheckNum() {
        return checkNum;
    }

    public void setQualifiedNum(BigDecimal qualifiedNum) {
        this.qualifiedNum = qualifiedNum;
    }

    public BigDecimal getQualifiedNum() {
        return qualifiedNum;
    }

    public void setFaultyNum(BigDecimal faultyNum) {
        this.faultyNum = faultyNum;
    }

    public BigDecimal getFaultyNum() {
        return faultyNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("checkNumber", getCheckNumber())
                .append("materielNumber", getMaterielNumber())
                .append("materielName", getMaterielName())
                .append("modelParam", getModelParam())
                .append("receiveNum", getReceiveNum())
                .append("checkType", getCheckType())
                .append("checkNum", getCheckNum())
                .append("qualifiedNum", getQualifiedNum())
                .append("faultyNum", getFaultyNum())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
