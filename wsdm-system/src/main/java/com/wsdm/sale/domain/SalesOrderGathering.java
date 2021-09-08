package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 销售单收款对象 sales_order_gathering
 *
 * @author wsdm
 * @date 2021-04-27
 */
public class SalesOrderGathering extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 收款单号
     */
    private String gatheringNumber;

    /**
     * 发货单号
     */
    @NotBlank(message = "发货单号不能为空")
    private String deliveryNumber;

    /**
     * 销售单号
     */
    @NotBlank(message = "销售单号不能为空")
    private String salesNumber;

    /**
     * 客户
     */
    @NotBlank(message = "客户不能为空")
    private String customer;

    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal gatheringAmount;

    /**
     * 收款日期
     */
    @NotNull(message = "收款日期不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date gatheringDate;

    /**
     * 状态
     */
    private String gatheringStatus;

    /**
     * 审核人
     */
    private String approve;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date approveTime;

    private List<SalesOrderGatheringDetails> gatheringDetailsList;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setGatheringNumber(String gatheringNumber) {
        this.gatheringNumber = gatheringNumber;
    }

    public String getGatheringNumber() {
        return gatheringNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }

    public BigDecimal getGatheringAmount() {
        return gatheringAmount;
    }

    public void setGatheringAmount(BigDecimal gatheringAmount) {
        this.gatheringAmount = gatheringAmount;
    }

    public Date getGatheringDate() {
        return gatheringDate;
    }

    public void setGatheringDate(Date gatheringDate) {
        this.gatheringDate = gatheringDate;
    }

    public void setGatheringStatus(String gatheringStatus) {
        this.gatheringStatus = gatheringStatus;
    }

    public String getGatheringStatus() {
        return gatheringStatus;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public String getApprove() {
        return approve;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public List<SalesOrderGatheringDetails> getGatheringDetailsList() {
        return gatheringDetailsList;
    }

    public void setGatheringDetailsList(List<SalesOrderGatheringDetails> gatheringDetailsList) {
        this.gatheringDetailsList = gatheringDetailsList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("gatheringNumber", getGatheringNumber())
                .append("deliveryNumber", getDeliveryNumber())
                .append("salesNumber", getSalesNumber())
                .append("customer", getCustomer())
                .append("gatheringAmount", getGatheringAmount())
                .append("deliveryDate", getGatheringDate())
                .append("remark", getRemark())
                .append("gatheringStatus", getGatheringStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("approve", getApprove())
                .append("approveTime", getApproveTime())
                .toString();
    }
}
