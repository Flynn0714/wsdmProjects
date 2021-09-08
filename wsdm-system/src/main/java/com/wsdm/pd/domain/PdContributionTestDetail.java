package com.wsdm.pd.domain;

import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 工绩单质检明细对象 pd_contribution_test_detail
 *
 * @author wsdm
 * @date 2021-06-18
 */
public class PdContributionTestDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 工绩单ID
     */
    @Excel(name = "工绩单ID")
    private Long testId;

    /**
     * 物料编码
     */
    @Excel(name = "物料编码")
    private String materialCode;

    /**
     * 物料名称
     */
    @Excel(name = "物料名称")
    private String materialName;

    /**
     * 加工数量
     */
    @Excel(name = "加工数量")
    private BigDecimal quantity;

    /**
     * 质检数量
     */
    @Excel(name = "质检数量")
    private BigDecimal quantityTest;

    /**
     * 合格数量
     */
    @Excel(name = "合格数量")
    private BigDecimal quantityPass;

    /**
     * 不合格数量
     */
    @Excel(name = "不合格数量")
    private BigDecimal quantityReject;

    /**
     * 报废数量
     */
    @Excel(name = "报废数量")
    private BigDecimal quantityFault;

    /**
     * 重加工数量
     */
    @Excel(name = "重加工数量")
    private BigDecimal quantityRework;

    /**
     * 状态：0 - 删除，1 - 正常
     */
    @Excel(name = "状态：0 - 删除，1 - 正常")
    private String status;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public Long getTestId() {
        return testId;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantityTest(BigDecimal quantityTest) {
        this.quantityTest = quantityTest;
    }

    public BigDecimal getQuantityTest() {
        return quantityTest;
    }

    public void setQuantityPass(BigDecimal quantityPass) {
        this.quantityPass = quantityPass;
    }

    public BigDecimal getQuantityPass() {
        return quantityPass;
    }

    public void setQuantityReject(BigDecimal quantityReject) {
        this.quantityReject = quantityReject;
    }

    public BigDecimal getQuantityReject() {
        return quantityReject;
    }

    public void setQuantityFault(BigDecimal quantityFault) {
        this.quantityFault = quantityFault;
    }

    public BigDecimal getQuantityFault() {
        return quantityFault;
    }

    public void setQuantityRework(BigDecimal quantityRework) {
        this.quantityRework = quantityRework;
    }

    public BigDecimal getQuantityRework() {
        return quantityRework;
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
                .append("testId", getTestId())
                .append("materialCode", getMaterialCode())
                .append("materialName", getMaterialName())
                .append("quantity", getQuantity())
                .append("quantityTest", getQuantityTest())
                .append("quantityPass", getQuantityPass())
                .append("quantityReject", getQuantityReject())
                .append("quantityFault", getQuantityFault())
                .append("quantityRework", getQuantityRework())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
