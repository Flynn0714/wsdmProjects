package com.wsdm.stock.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 发货订单信息对象 sk_sales_delivery
 *
 * @author wsdm
 * @date 2021-01-12
 */
public class SalesDelivery extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 领取单号
     */
    private String salesNumber;

    /**
     * 客户单号
     */
    private String contractNumber;

    /**
     * 领取仓库
     */
    private String salesStock;

    /**
     * 领取仓库名称
     */
    private String salesStockName;

    /**
     * 生产计划
     */
    private String productionPlan;

    /**
     * 客户
     */
    private String customerNumber;

    /**
     * 联系人
     */
    private String linkman;

    /**
     * 联系人手机
     */
    private String linkphone;

    /**
     * 客户地址
     */
    private String customerAddress;

    /**
     * 领取数量
     */
    private Long deliveryNum;

    /**
     * 领取时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deliveryTime;

    /**
     * 状态
     */
    private String status;

    /**
     * 审核人
     */
    private String approvePerson;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approveTime;

    private List<SalesDeliveryDetail> salesDeliveryDetails;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setSalesStock(String salesStock) {
        this.salesStock = salesStock;
    }

    public String getSalesStock() {
        return salesStock;
    }

    public void setSalesStockName(String salesStockName) {
        this.salesStockName = salesStockName;
    }

    public String getSalesStockName() {
        return salesStockName;
    }

    public void setProductionPlan(String productionPlan) {
        this.productionPlan = productionPlan;
    }

    public String getProductionPlan() {
        return productionPlan;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone;
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setDeliveryNum(Long deliveryNum) {
        this.deliveryNum = deliveryNum;
    }

    public Long getDeliveryNum() {
        return deliveryNum;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setApprovePerson(String approvePerson) {
        this.approvePerson = approvePerson;
    }

    public String getApprovePerson() {
        return approvePerson;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public List<SalesDeliveryDetail> getSalesDeliveryDetails() {
        return salesDeliveryDetails;
    }

    public void setSalesDeliveryDetails(List<SalesDeliveryDetail> salesDeliveryDetails) {
        this.salesDeliveryDetails = salesDeliveryDetails;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("salesNumber", getSalesNumber())
                .append("contractNumber", getContractNumber())
                .append("salesStock", getSalesStock())
                .append("salesStockName", getSalesStockName())
                .append("productionPlan", getProductionPlan())
                .append("customerNumber", getCustomerNumber())
                .append("linkman", getLinkman())
                .append("linkphone", getLinkphone())
                .append("customerAddress", getCustomerAddress())
                .append("deliveryNum", getDeliveryNum())
                .append("deliveryTime", getDeliveryTime())
                .append("remark", getRemark())
                .append("status", getStatus())
                .append("approvePerson", getApprovePerson())
                .append("approveTime", getApproveTime())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
