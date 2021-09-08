package com.wsdm.pd.domain;

import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 原材料对象 pd_raw_material
 *
 * @author wsdm
 * @date 2021-06-21
 */
public class PdRawMaterial extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 半成品名称
     */
    @Excel(name = "半成品名称")
    private String semiProduceName;

    /**
     * 半成品编号
     */
    @Excel(name = "半成品编号")
    private String semiProduceNumber;

    /**
     * 半成品类型
     */
    @Excel(name = "半成品类型")
    private String semiProduceModel;

    /**
     * 材质
     */
    @Excel(name = "材质")
    private String materialQuality;

    /**
     * 模数齿数
     */
    @Excel(name = "模数齿数")
    private String modulusToothNumber;

    /**
     * 规格
     */
    @Excel(name = "规格")
    private String specifications;

    /**
     * 状态  0正常 1已删除
     */
    @Excel(name = "状态  0正常 1已删除")
    private String status;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setSemiProduceName(String semiProduceName) {
        this.semiProduceName = semiProduceName;
    }

    public String getSemiProduceName() {
        return semiProduceName;
    }

    public void setSemiProduceNumber(String semiProduceNumber) {
        this.semiProduceNumber = semiProduceNumber;
    }

    public String getSemiProduceNumber() {
        return semiProduceNumber;
    }

    public void setSemiProduceModel(String semiProduceModel) {
        this.semiProduceModel = semiProduceModel;
    }

    public String getSemiProduceModel() {
        return semiProduceModel;
    }

    public void setMaterialQuality(String materialQuality) {
        this.materialQuality = materialQuality;
    }

    public String getMaterialQuality() {
        return materialQuality;
    }

    public void setModulusToothNumber(String modulusToothNumber) {
        this.modulusToothNumber = modulusToothNumber;
    }

    public String getModulusToothNumber() {
        return modulusToothNumber;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("semiProduceName", getSemiProduceName())
                .append("semiProduceNumber", getSemiProduceNumber())
                .append("semiProduceModel", getSemiProduceModel())
                .append("materialQuality", getMaterialQuality())
                .append("modulusToothNumber", getModulusToothNumber())
                .append("specifications", getSpecifications())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
