package com.wsdm.pd.service;

import com.wsdm.pd.domain.ProcessPayOrderDetail;

import java.util.List;

/**
 * @author wjj
 * @Description
 * @date 2021/8/25 13:24
 */
public interface ProcessPayOrderDetailService {
    /**
     * 查询加工录入付款单详情列表
     * @param processPayOrderDetail
     * @return
     */
    List<ProcessPayOrderDetail> selectByPayDetails(ProcessPayOrderDetail processPayOrderDetail);



    /**
    * 删除付款单详情信息
    * @Param:
    * @return:
    */
    int deleteByDetails(Long id);

//    /**
//     * 添加付款单
//     * @param payOrderDetails
//     * @return
//     */
//
//    int addDetails(List<ProcessPayOrderDetail> payOrderDetails);
}
