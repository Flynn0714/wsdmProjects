package com.wsdm.produce.domain;

import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 半成品管理对象 pd_semi_produce_manage
 *
 * @author wsdm
 * @date 2020-11-06
 */
public class SemiProduceManage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 半成品名称
     */
    private String semiProduceName;

    /**
     * 半成品编号
     */
    private String semiProduceNumber;

    /**
     * 半成品类型
     */
    private String semiProduceModel;

    /**
     * 材质
     */
    private String materialQuality;

    /**
     * 模数齿数
     */
    private String modulusToothNumber;


    /**
     * 规格
     */
    private String specifications;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 状态
     */
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

    public String getModulusToothNumber() {
        return modulusToothNumber;
    }

    public void setModulusToothNumber(String modulusToothNumber) {
        this.modulusToothNumber = modulusToothNumber;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
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
                .append("num", getNum())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
