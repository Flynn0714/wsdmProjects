package com.wsdm.pd.mapper;

import com.wsdm.pd.domain.ProcessPayOrderDetail;

import java.util.List;

/**
 * @author wjj
 * @Description  加工付款记录Mapper接口
 * @date 2021/8/25 12:05
 */
public interface ProcessPayOrderDetailMappper {
    /**
     * 查询付款单详情信息
     * @param processPayOrderDetail
     * @return
     */
    List<ProcessPayOrderDetail> selectProcessDetail(ProcessPayOrderDetail processPayOrderDetail);

    /**
     * 添加付款单详情
     * @param processPayOrderDetail
     * @return
     */
    int insertDetails(List<ProcessPayOrderDetail> processPayOrderDetail);

    /**
     * 根据id删除付款单详情信息
     * @param id
     * @return
     */
    int deleteByDetails(Long id);

}
