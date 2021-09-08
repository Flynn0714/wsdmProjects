package com.wsdm.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 * 收款记录对象 sales_order_account_received
 *
 * @author wsdm
 * @date 2021-07-17
 */
public class SalesOrderAccountReceivedQuery {

    /**
     * 客户
     */
    private String customer;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 收款方式
     */
    private String paymentMethod;

    /**
     * 收款日期 起始
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date gatheringDateStart;

    /**
     * 收款日期 结束
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date gatheringDateEnd;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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
}
