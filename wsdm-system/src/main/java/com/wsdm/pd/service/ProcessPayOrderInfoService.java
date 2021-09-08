package com.wsdm.pd.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.materiel.domain.PurchasePayOrderInfoVo;
import com.wsdm.pd.domain.PdContribution;
import com.wsdm.pd.domain.ProcessPayOrderInfo;
import com.wsdm.pd.domain.ProcessPayOrderVo;

import java.util.List;

/**
 * @author wjj
 * @Description
 * @date 2021/8/24 16:56
 */
public interface ProcessPayOrderInfoService {

    /**
     * 新增付款单信息
     * @param payOrderInfo
     * @return
     */
    List<ProcessPayOrderInfo> selectByProcessList(ProcessPayOrderInfo payOrderInfo);

    /**
     * 新增加工付款单信息
     * @param id
     * @return
     */
    int add(Long id);
    /**
     * 修改加工付款单信息
     * @param payOrderInfo
     * @return
     */
    int updateByPayInfo(ProcessPayOrderInfo payOrderInfo);

    /**
     * 根据加工员工查看付款单详情
     * @param id
     * @return
     */
    ProcessPayOrderInfo selectById(Long id);

    /**
     * 查询加工列表信息
     * @return
     */
    List<PdContribution> selectPdList(PdContribution pdContribution);

    /**
     * 添加付款信息（去付款）
     * @param payOrderInfo
     * @return
     */
    AjaxResult insertProcessPayRecord(ProcessPayOrderInfo payOrderInfo);




}
