package com.wsdm.pd.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Auther: wjj
 * @Date: 2021/8/26 10:00
 * @Description:
 */
public class ProcessPayOrderVo extends ProcessPayOrderInfo{
    private static final long serialVersionUID = 1L;


    /**
     * 加工员工
     */
    private String processEmployee;
    /**
     * 加工部门(字典)
     */
    private String processDept;
    /**
     * 付款日期查询开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    /**
     * 付款查询结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 付款方式
     */
    private String payWay;

    @Override
    public String getProcessEmployee() {
        return processEmployee;
    }

    @Override
    public void setProcessEmployee(String processEmployee) {
        this.processEmployee = processEmployee;
    }

    @Override
    public String getProcessDept() {
        return processDept;
    }

    @Override
    public void setProcessDept(String processDept) {
        this.processDept = processDept;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

}
