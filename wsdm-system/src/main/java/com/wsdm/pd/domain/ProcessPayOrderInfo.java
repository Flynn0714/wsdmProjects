package com.wsdm.pd.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsdm.common.core.domain.BaseEntity;
import com.wsdm.common.utils.DateUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: wjj
 * @Date: 2021/8/24 10:55
 * @Description: 加工录入付款单信息表 pd_contribution_pay_order_info
 */
public class ProcessPayOrderInfo extends BaseEntity{
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 加工单号
     */
    private String processNo;

    /**
     * 加工部门(字典)
     */
    private String processDept;
    /**
     * 加工部门名称
     */
    private String deptName;

    /**
     * 加工员工
     */
    private String processEmployee;

    /**
     * 应付金额
     */
    private BigDecimal payableAmount;

    /**
     * 累计付款金额
     */
    private BigDecimal totalPayAmount;

    /**
     * 累计交易金额
     */
    private BigDecimal totalTransAmount;

    /**
     * 最后付款人
     */
    private String lastPayBy;

    /**
     * 最后交易时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastTransTime;

    /**
     * 最后付款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastPayTime;

    /**
     * 加工付款交易记录详情
     * @return
     */
    private List<ProcessPayOrderDetail> payOrderDetails;

    /**
     * 加工明细信息
     */
    private List<PdContribution> pdContributions;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessNo() {
        return processNo;
    }

    public void setProcessNo(String processNo) {
        this.processNo = processNo;
    }

    public String getProcessDept() {
        return processDept;
    }

    public void setProcessDept(String processDept) {
        this.processDept = processDept;
    }

    public String getProcessEmployee() {
        return processEmployee;
    }

    public void setProcessEmployee(String processEmployee) {
        this.processEmployee = processEmployee;
    }

    public BigDecimal getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(BigDecimal payableAmount) {
        this.payableAmount = payableAmount;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public BigDecimal getTotalTransAmount() {
        return totalTransAmount;
    }

    public void setTotalTransAmount(BigDecimal totalTransAmount) {
        this.totalTransAmount = totalTransAmount;
    }

    public String getLastPayBy() {
        return lastPayBy;
    }

    public void setLastPayBy(String lastPayBy) {
        this.lastPayBy = lastPayBy;
    }

    public Date getLastTransTime() {
        return lastTransTime;
    }

    public void setLastTransTime(Date lastTransTime) {
        this.lastTransTime = lastTransTime;
    }

    public Date getLastPayTime() {
        return lastPayTime;
    }

    public void setLastPayTime(Date lastPayTime) {
        this.lastPayTime = lastPayTime;

    }

    public List<ProcessPayOrderDetail> getPayOrderDetails() {
        return payOrderDetails;
    }

    public void setPayOrderDetails(List<ProcessPayOrderDetail> payOrderDetails) {
        this.payOrderDetails = payOrderDetails;
    }

    public List<PdContribution> getPdContributions() {
        return pdContributions;
    }

    public void setPdContributions(List<PdContribution> pdContributions) {
        this.pdContributions = pdContributions;
    }


    @Override
    public String toString() {
        return "ProcessPayOrderInfo{" +
                "id=" + id +
                ", processNo='" + processNo + '\'' +
                ", processDept='" + processDept + '\'' +
                ", deptName='" + deptName + '\'' +
                ", processEmployee='" + processEmployee + '\'' +
                ", payableAmount=" + payableAmount +
                ", totalPayAmount=" + totalPayAmount +
                ", totalTransAmount=" + totalTransAmount +
                ", lastPayBy='" + lastPayBy + '\'' +
                ", lastTransTime=" + lastTransTime +
                ", lastPayTime=" + lastPayTime +
                ", payOrderDetails=" + payOrderDetails +
                ", pdContributions=" + pdContributions +
                '}';
    }
}
