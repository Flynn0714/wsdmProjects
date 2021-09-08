package com.wsdm.pd.domain;

import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 产品附件对象 pd_produce_annex
 *
 * @author wsdm
 * @date 2021-06-21
 */
public class PdProduceAnnex extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 产品ID
     */
    @Excel(name = "产品ID")
    private String produceId;

    /**
     * 附件名称
     */
    @Excel(name = "附件名称")
    private String annexName;

    /**
     * 附件url
     */
    @Excel(name = "附件url")
    private String annexUrl;

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

    public void setProduceId(String produceId) {
        this.produceId = produceId;
    }

    public String getProduceId() {
        return produceId;
    }

    public void setAnnexName(String annexName) {
        this.annexName = annexName;
    }

    public String getAnnexName() {
        return annexName;
    }

    public void setAnnexUrl(String annexUrl) {
        this.annexUrl = annexUrl;
    }

    public String getAnnexUrl() {
        return annexUrl;
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
                .append("produceId", getProduceId())
                .append("annexName", getAnnexName())
                .append("annexUrl", getAnnexUrl())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
