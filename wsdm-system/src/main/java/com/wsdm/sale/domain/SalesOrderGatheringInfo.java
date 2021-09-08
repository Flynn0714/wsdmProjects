package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author wangr
 * @version 1.0
 * @date 2021/5/13 14:13
 */
public class SalesOrderGatheringInfo extends SalesOrderGathering {

    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal deliveryAmount;

    /**
     * 状态
     */
    private String gatheringStatusName;

    /**
     * 客户
     */
    private String customerName;


    private String approveName;

    /**
     * 发货日期
     */
    @NotBlank(message = "发货日期不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDate;

    List<SalesOrderDeliveryDetails> deliveryDetailsList;

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public List<SalesOrderDeliveryDetails> getDeliveryDetailsList() {
        return deliveryDetailsList;
    }

    public void setDeliveryDetailsList(List<SalesOrderDeliveryDetails> deliveryDetailsList) {
        this.deliveryDetailsList = deliveryDetailsList;
    }

    public BigDecimal getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(BigDecimal deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public String getGatheringStatusName() {
        return gatheringStatusName;
    }

    public void setGatheringStatusName(String gatheringStatusName) {
        this.gatheringStatusName = gatheringStatusName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
