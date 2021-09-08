package com.wsdm.pd.service.impl;

import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.pd.domain.ProcessPayOrderDetail;
import com.wsdm.pd.mapper.ProcessPayOrderDetailMappper;
import com.wsdm.pd.service.ProcessPayOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: wjj
 * @Date: 2021/8/25 13:26
 * @Description: 加工应付款service接口
 */
@Service
public class ProcessPayOrderIDetailServiceImpl implements ProcessPayOrderDetailService {
    @Autowired
    private ProcessPayOrderDetailMappper payOrderDetailMappper;

    /**
     * 查询付款记录列表信息
     * @param processPayOrderDetail
     * @return
     */
    @Override
    public List<ProcessPayOrderDetail> selectByPayDetails(ProcessPayOrderDetail processPayOrderDetail) {
        return payOrderDetailMappper.selectProcessDetail(processPayOrderDetail);
    }

    /**
     * 删除付款单详情信息
     * @param id
     * @return
     */
    @Override
    public int deleteByDetails(Long id) {
        return payOrderDetailMappper.deleteByDetails(id);
    }



    /**
     * 添加付款单
     * @param payOrderDetails
     * @return
     */
//    @Override
//    public int addDetails(List<ProcessPayOrderDetail> payOrderDetails) {
//        for (ProcessPayOrderDetail p:payOrderDetails) {
//            insertDetails(p);
//        }
//        return 0;
//    }
}
