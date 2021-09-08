package com.wsdm.sale.service;

import com.wsdm.sale.domain.*;

import java.util.List;

/**
 * 销售退货Mapper接口
 *
 * @author Lu
 * @date 2021-7-16
 */
public interface ISalesGoodsRejectService {

    /**
     * @param salesGoodsRejectQuery
     * @return
     */
    List<SalesGoodsReject> selectSalesGoodsRejectAll(SalesGoodsRejectQuery salesGoodsRejectQuery);

    /**
     * 查询销售退货列表
     * @param salesGoodsRejectInfoQuery 查询参数实体类
     * @return
     */
    List<SalesGoodsRejectInfo> selectSalesGoodsRejectInfo(SalesGoodsRejectInfoQuery salesGoodsRejectInfoQuery);

    /**
     * 根据销售单号查询货物
     * @param saleName 销售单号
     * @param customer 客户
     * @return
     */
    SalesGoodsReject selectSalesGoodsRejectDetailAllBySaleName(String saleName,String customer);

    /**
     * 保存退货单
     * @param type   保存类型  0 保存  1保存并提交
     * @param salesGoodsReject  退货实体类
     * @return
     */
    int saveOrSubmitSalesReturn(int type,SalesGoodsReject salesGoodsReject);


    int updateSalesReturn(SalesGoodsReject salesGoodsReject);
    /**
     * 根据退货单号查询退货详情
     * @param returnNumber 退货单号
     * @return
     */
    SalesGoodsReject getSalesGoodsRejectDetailByReturnNumber(String returnNumber);

    /**
     * 查询客户列表
     * @return
     */
    List<String> selectCustomerList();

    int updateSalesGoodsReturn(String returnNumber,String action);

    int deleteSalesGoodsReturn(String[] returnNumbers);
}
