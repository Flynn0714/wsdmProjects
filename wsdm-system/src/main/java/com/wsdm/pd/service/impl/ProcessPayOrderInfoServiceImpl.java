package com.wsdm.pd.service.impl;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.ResultEnums;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.StringUtils;
import com.wsdm.pd.domain.*;
import com.wsdm.pd.mapper.PdContributionDetailMapper;
import com.wsdm.pd.mapper.PdContributionMapper;
import com.wsdm.pd.mapper.ProcessPayOrderDetailMappper;
import com.wsdm.pd.mapper.ProcessPayOrderInfoMapper;
import com.wsdm.pd.service.ProcessPayOrderInfoService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: wjj
 * @Date: 2021/8/24 16:57
 * @Description: 加工应付款service接口
 */
@Service
public class ProcessPayOrderInfoServiceImpl implements ProcessPayOrderInfoService {

    @Autowired
    private ProcessPayOrderInfoMapper payOrderInfoMapper;
    @Autowired
    private PdContributionMapper pdContributionMapper;
    @Autowired
    private PdContributionDetailMapper detailMapper;
    @Autowired
    private ProcessPayOrderDetailMappper payOrderDetailMappper;

    /**
     * 查询加工应付款单列表
     * @param payOrderInfo
     * @return
     */
    @Override
    public List<ProcessPayOrderInfo> selectByProcessList(ProcessPayOrderInfo payOrderInfo) {
        return payOrderInfoMapper.selectListByOrder(payOrderInfo);
    }
    /**
     * 新增加工应付款单信息
     * @param id
     * @return
     */
    @Override
    public int add(Long id) {
        PdContribution pdContribution = pdContributionMapper.selectPdContributionById(id);
        ProcessPayOrderInfo processPayOrderInfo = new ProcessPayOrderInfo();
        String username = SecurityUtils.getUsername();
        BigDecimal totalAmount = detailMapper.selectTotalAmount(pdContribution.getEmployeeName());
        int result;
        ProcessPayOrderInfo payOrderInfo = payOrderInfoMapper.selectByName(pdContribution.getEmployeeName());
        if(!ObjectUtils.isEmpty(payOrderInfo)){
            BigDecimal totalTransAmount = payOrderInfo.getTotalTransAmount();
            BigDecimal payableAmount = payOrderInfo.getPayableAmount();
            //累计加工金额
            payOrderInfo.setTotalTransAmount(totalAmount);
            //应付款金额
            payOrderInfo.setPayableAmount(totalAmount);
            payOrderInfo.setLastTransTime(DateUtils.getNowDate());
            result = payOrderInfoMapper.updateByPayInfo(payOrderInfo);
        }else {
            processPayOrderInfo.setId(pdContribution.getId());
            //加工部门
            processPayOrderInfo.setProcessDept(pdContribution.getDeptCode());
            //加工员工
            processPayOrderInfo.setProcessEmployee(pdContribution.getEmployeeName());

            //累计加工金额
            processPayOrderInfo.setTotalTransAmount(totalAmount);
            //应付金额
            processPayOrderInfo.setPayableAmount(totalAmount);
            //累计付款金额
            processPayOrderInfo.setTotalPayAmount(BigDecimal.ZERO);
            //最后加工时间
            processPayOrderInfo.setLastTransTime(pdContribution.getProcessTime());
            processPayOrderInfo.setCreateTime(DateUtils.getNowDate());
            pdContribution.setCreateBy(username);
            result = payOrderInfoMapper.add(processPayOrderInfo);
        }
        if(result <= 0){
            throw new BaseException("加工应付款信息汇总更新失败");
        }
        return 0;
    }

    /**
     * 修改加工付款单信息
     *
     * @param payOrderInfo
     * @return
     */
    @Override
    public int updateByPayInfo(ProcessPayOrderInfo payOrderInfo) {
        String username = SecurityUtils.getUsername();
        payOrderInfo.setUpdateBy(username);
        payOrderInfo.setUpdateTime(DateUtils.getNowDate());
        return payOrderInfoMapper.updateByPayInfo(payOrderInfo);
    }

    /**
     * 根据加工员工查看付款单详情
     * @param id
     * @return
     */
    @Override
    public ProcessPayOrderInfo selectById(Long id) {
        return payOrderInfoMapper.selectById(id);
    }

    /**
     * 查询加工列表信息
     *
     * @return
     */
    @Override
    public List<PdContribution> selectPdList(PdContribution pdContribution) {
       return pdContributionMapper.selectPdContributionList(pdContribution);
    }


    /**
    * @Description:  付款单信息
    * @Param:
    * @return:
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult insertProcessPayRecord(ProcessPayOrderInfo payOrderInfo) {
        String processEmployee = payOrderInfo.getProcessEmployee();
        List<ProcessPayOrderDetail> payOrderDetails = payOrderInfo.getPayOrderDetails();
        if(StringUtils.isEmpty(processEmployee) || CollectionUtils.isEmpty(payOrderDetails)){
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        List<ProcessPayOrderInfo> payOrderInfos = payOrderInfoMapper.selectListByOrder(payOrderInfo);
        ProcessPayOrderInfo orderInfo = payOrderInfos.get(0);
        BigDecimal payableAmount = orderInfo.getPayableAmount();
        BigDecimal totalPayAmount = orderInfo.getTotalPayAmount();
        String username = SecurityUtils.getUsername();
        Date date = DateUtils.getNowDate();
        payOrderInfo.setLastPayTime(date);
        payOrderInfo.setLastPayBy(username);
        BigDecimal total = BigDecimal.ZERO;
        for (ProcessPayOrderDetail detail:payOrderDetails) {
            detail.setPid(payOrderInfo.getId());
            detail.setCreateTime(date);
            detail.setCreateBy(username);
            detail.setEmployee(processEmployee);
            detail.setPayDate(date);
            total = total.add(detail.getPayAmount());
        }
        if(total.compareTo(payableAmount) > 0){
            throw new BaseException("付款金额不能大于应付金额！");
        }
        //应付款金额
        payableAmount = payableAmount.subtract(total);
        //累计付款金额
        totalPayAmount = totalPayAmount.add(total);
        payOrderInfo.setPayableAmount(payableAmount);
        payOrderInfo.setTotalPayAmount(totalPayAmount);
        int result = payOrderInfoMapper.updateByPayInfo(payOrderInfo);
        if(result <= 0){
            throw new BaseException("加工付款单信息更新失败!");
        }
        result = payOrderDetailMappper.insertDetails(payOrderDetails);
        if(result <= 0){
            throw new BaseException("加工付款记录新增失败");
        }
        return AjaxResult.success();
    }

}
