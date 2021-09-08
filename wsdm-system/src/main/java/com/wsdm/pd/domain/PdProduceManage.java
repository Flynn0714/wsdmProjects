package com.wsdm.pd.domain;

import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 产品管理对象 pd_produce_manage
 *
 * @author wsdm
 * @date 2021-06-21
 */
public class PdProduceManage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String produceName;

    /**
     * 产品编号
     */
    @Excel(name = "产品编号")
    private String produceNumber;

    /**
     * 产品类型
     */
    @Excel(name = "产品类型")
    private String produceModel;

    /**
     * 速比
     */
    @Excel(name = "速比")
    private String speedRatio;

    /**
     * 产品类型 半成品 成品
     */
    private String type;

    /**
     * 装配形式
     */
    @Excel(name = "装配形式")
    private String assembleModality;

    /**
     * 状态  0正常 1已删除
     */
    @Excel(name = "状态  0正常 1已删除")
    private String status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName;
    }

    public String getProduceName() {
        return produceName;
    }

    public void setProduceNumber(String produceNumber) {
        this.produceNumber = produceNumber;
    }

    public String getProduceNumber() {
        return produceNumber;
    }

    public void setProduceModel(String produceModel) {
        this.produceModel = produceModel;
    }

    public String getProduceModel() {
        return produceModel;
    }

    public void setSpeedRatio(String speedRatio) {
        this.speedRatio = speedRatio;
    }

    public String getSpeedRatio() {
        return speedRatio;
    }

    public void setAssembleModality(String assembleModality) {
        this.assembleModality = assembleModality;
    }

    public String getAssembleModality() {
        return assembleModality;
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
                .append("produceName", getProduceName())
                .append("produceNumber", getProduceNumber())
                .append("produceModel", getProduceModel())
                .append("speedRatio", getSpeedRatio())
                .append("assembleModality", getAssembleModality())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
