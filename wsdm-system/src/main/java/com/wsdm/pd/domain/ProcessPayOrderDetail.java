package com.wsdm.pd.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wsdm.common.annotation.Excel;
import com.wsdm.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: wjj
 * @Date: 2021/8/24 11:16
 * @Description: 加工付款记录详情信息
 */
public class ProcessPayOrderDetail extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;
    /**
     * 加工部门
     */
    private String processDept;
    private String deptName;

    /**
     * 员工Id
     */
    private Long pid;
    /**
     * 加工员工
     */
    @Excel(name ="加工员工",width = 20)
    private String employee;
    /**
     * 付款方式(字典)
     */
    private String payWay;

    /**
     * 付款方式名称
     */
    @Excel(name ="付款方式",width = 20)
    private String wayName;
    /**
     * 付款银行(字典)
     */
    private String payBank;

    /**
     * 付款银行名称
     */
    @Excel(name ="付款银行",width = 20)
    private String bankName;

    /**
     * 付款账户
     */
    @Excel(name ="付款账户",width = 20)
    private String payAccount;

    /**
     * 付款金额
     */
    @Excel(name ="付款金额",width = 20)
    private BigDecimal payAmount;
    /**
     * 付款人
     */
    @Excel(name ="付款人",width = 20)
    private String payName;

    /**
     * 付款日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name ="付款人",width = 20,dateFormat = "yyyy-MM-dd")
    private Date payDate;

    /**
     * 备注
     */
    @Excel(name ="备注",width = 50)
    private String remark;

    @JsonIgnore
    private String startTime;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getProcessDept() {
        return processDept;
    }

    public void setProcessDept(String processDept) {
        this.processDept = processDept;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getWayName() {
        return wayName;
    }

    public void setWayName(String wayName) {
        this.wayName = wayName;
    }

    public String getPayBank() {
        return payBank;
    }

    public void setPayBank(String payBank) {
        this.payBank = payBank;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ProcessPayOrderDetail{" +
                "id=" + id +
                ", processDept='" + processDept + '\'' +
                ", deptName='" + deptName + '\'' +
                ", pid=" + pid +
                ", employee='" + employee + '\'' +
                ", payWay='" + payWay + '\'' +
                ", wayName='" + wayName + '\'' +
                ", payBank='" + payBank + '\'' +
                ", bankName='" + bankName + '\'' +
                ", payAccount='" + payAccount + '\'' +
                ", payAmount=" + payAmount +
                ", payName='" + payName + '\'' +
                ", payDate=" + payDate +
                ", remark='" + remark + '\'' +
                ", startTime='" + startTime + '\'' +
                '}';
    }
}
