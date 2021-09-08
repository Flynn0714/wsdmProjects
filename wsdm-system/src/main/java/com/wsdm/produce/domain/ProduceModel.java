package com.wsdm.produce.domain;

import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 产品型号对象 pd_produce_model
 *
 * @author wsdm
 * @date 2020-11-06
 */
public class ProduceModel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 配件名称
     */
    private String modelName;

    /**
     * 编号
     */
    private String modelNumber;

    /**
     * 型号
     */
    private String model;

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

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public ProduceModel(){}

    public ProduceModel(String modelNumber, String model, String status, String createBy, Date createTime) {
        this.modelNumber = modelNumber;
        this.model = model;
        this.status = status;
        this.setCreateBy(createBy);
        this.setCreateTime(createTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("modelName", getModelName())
                .append("modelNumber", getModelNumber())
                .append("model", getModel())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
