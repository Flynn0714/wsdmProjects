package com.wsdm.pd.domain;

import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 pd_produce_details
 *
 * @author wsdm
 * @date 2021-06-21
 */
public class PdProduceDetails extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 类型 1半成品 2配件
     */
    @Excel(name = "类型 1半成品 2配件")
    private String type;

    /**
     * 产品
     */
    @Excel(name = "产品")
    private String produce;

    /**
     * 关联
     */
    @Excel(name = "关联")
    private String ref;

    /**
     * 数量
     */
    @Excel(name = "数量")
    private Integer num;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public String getProduce() {
        return produce;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRef() {
        return ref;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("type", getType())
                .append("produce", getProduce())
                .append("ref", getRef())
                .append("num", getNum())
                .toString();
    }
}
