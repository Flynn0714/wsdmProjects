package com.wsdm.pd.mapper;

import com.wsdm.pd.domain.PdContribution;
import com.wsdm.pd.domain.PdContributionVO;
import com.wsdm.pd.domain.ProcessPayOrderInfo;
import com.wsdm.pd.domain.ProcessPayOrderVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: wjj
 * @Date: 2021/8/24 14:07
 * @Description: 加工应付款单信息mapper接口
 */
public interface ProcessPayOrderInfoMapper {
    /**
     * 查询加工单应付款信息列表
     * @param payOrderInfo
     * @return
     */
    List<ProcessPayOrderInfo> selectListByOrder(ProcessPayOrderInfo payOrderInfo);

    /**
     * 新增加工付款单信息
     * @param payOrderInfo
     * @return
     */
    int add(ProcessPayOrderInfo payOrderInfo);

    /**
     * 修改加工付款单信息
     * @param payOrderInfo
     * @return
     */
    int updateByPayInfo(ProcessPayOrderInfo payOrderInfo);

    /**
     * 根据加工员工查看加工单应付款详情信息
     * @param id
     *
     * @return
     */
    ProcessPayOrderInfo selectById(Long id);

    /**
     * 根据姓名查询
     * @param processEmployee
     * @return
     */
    ProcessPayOrderInfo selectByName(String processEmployee);


}
