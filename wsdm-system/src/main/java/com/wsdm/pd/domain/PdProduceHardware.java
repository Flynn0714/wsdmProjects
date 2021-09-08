package com.wsdm.pd.domain;

import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 产品五金配件对象 pd_produce_hardware
 *
 * @author wsdm
 * @date 2021-06-21
 */
public class PdProduceHardware extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    @Excel(name = "五金编码")
    private String code;

    /**
     * 五金类型
     */
    @Excel(name = "五金类型")
    private String typeCode;

    /**
     * 五金类型名称
     */
    @Excel(name = "五金类型名称")
    private String typeName;

    /**
     * 五金型号
     */
    @Excel(name = "五金型号")
    private String model;

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

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("typeCode", getTypeCode())
                .append("typeName", getTypeName())
                .append("model", getModel())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
