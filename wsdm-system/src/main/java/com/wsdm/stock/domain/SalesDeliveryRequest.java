package com.wsdm.stock.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 发货订单信息 查询入参
 *
 * @author wsdm
 * @date 2021-01-12
 */
public class SalesDeliveryRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 领取单号
     */
    private String salesNumber;

    /**
     * 合同单号
     */
    private String contractNumber;

    /**
     * 客户
     */
    private String customerNumber;


    /**
     * 发货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deliveryTimeStart;

    /**
     * 发货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deliveryTimeEnd;

    /**
     * 状态
     */
    private String status;


    public String getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Date getDeliveryTimeStart() {
        return deliveryTimeStart;
    }

    public void setDeliveryTimeStart(Date deliveryTimeStart) {
        this.deliveryTimeStart = deliveryTimeStart;
    }

    public Date getDeliveryTimeEnd() {
        return deliveryTimeEnd;
    }

    public void setDeliveryTimeEnd(Date deliveryTimeEnd) {
        this.deliveryTimeEnd = deliveryTimeEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
