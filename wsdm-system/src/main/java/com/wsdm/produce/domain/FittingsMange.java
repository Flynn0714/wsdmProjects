package com.wsdm.produce.domain;

import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;


/**
 * 配件管理对象 pd_fittings_mange
 *
 * @author wsdm
 * @date 2020-11-06
 */
public class FittingsMange extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 配件名称
     */
    @NotBlank(message = "配件名称不为空")
    private String fittingName;

    private String fittingLabel;
    /**
     * 编号
     */
    private String fittingNumber;

    /**
     * 型号
     */
    @NotBlank(message = "配件型号不能为空")
    private String fittingModel;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 状态
     */
    private String status;

    public String getFittingLabel() {
        return fittingLabel;
    }

    public void setFittingLabel(String fittingLabel) {
        this.fittingLabel = fittingLabel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setFittingName(String fittingName) {
        this.fittingName = fittingName;
    }

    public String getFittingName() {
        return fittingName;
    }

    public void setFittingNumber(String fittingNumber) {
        this.fittingNumber = fittingNumber;
    }

    public String getFittingNumber() {
        return fittingNumber;
    }

    public void setFittingModel(String fittingModel) {
        this.fittingModel = fittingModel;
    }

    public String getFittingModel() {
        return fittingModel;
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
                .append("fittingName", getFittingName())
                .append("fittingLabel",getFittingLabel())
                .append("fittingNumber", getFittingNumber())
                .append("fittingModel", getFittingModel())
                .append("num", getNum())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
