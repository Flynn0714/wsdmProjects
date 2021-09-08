package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 销售单收款对象 sales_order_gathering
 *
 * @author wsdm
 * @date 2021-04-27
 */
public class SalesOrderGatheringQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 收款单号
     */
    private String gatheringNumber;

    /**
     * 发货单号
     */
    private String deliveryNumber;

    /**
     * 销售单号
     */
    private String salesNumber;

    /**
     * 客户
     */
    private String customer;


    /**
     * 发货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date gatheringDateStart;

    /**
     * 发货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date gatheringDateEnd;

    /**
     * 发货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDateStart;

    /**
     * 发货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDateEnd;

    /**
     * 状态
     */
    private String gatheringStatus;

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

    public void setGatheringStatus(String gatheringStatus) {
        this.gatheringStatus = gatheringStatus;
    }

    public String getGatheringStatus() {
        return gatheringStatus;
    }

    public Date getGatheringDateStart() {
        return gatheringDateStart;
    }

    public void setGatheringDateStart(Date gatheringDateStart) {
        this.gatheringDateStart = gatheringDateStart;
    }

    public Date getGatheringDateEnd() {
        return gatheringDateEnd;
    }

    public void setGatheringDateEnd(Date gatheringDateEnd) {
        this.gatheringDateEnd = gatheringDateEnd;
    }

    public Date getDeliveryDateStart() {
        return deliveryDateStart;
    }

    public void setDeliveryDateStart(Date deliveryDateStart) {
        this.deliveryDateStart = deliveryDateStart;
    }

    public Date getDeliveryDateEnd() {
        return deliveryDateEnd;
    }

    public void setDeliveryDateEnd(Date deliveryDateEnd) {
        this.deliveryDateEnd = deliveryDateEnd;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("gatheringNumber", getGatheringNumber())
                .append("deliveryNumber", getDeliveryNumber())
                .append("salesNumber", getSalesNumber())
                .append("customer", getCustomer())
                .append("deliveryDateStart", getGatheringDateStart())
                .append("gatheringStatus", getGatheringStatus())
                .toString();
    }
}
