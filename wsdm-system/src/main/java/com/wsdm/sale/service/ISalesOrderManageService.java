package com.wsdm.sale.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.sale.domain.SalesOrderAutomaticPurchase;
import com.wsdm.sale.domain.SalesOrderManage;
import com.wsdm.sale.domain.SalesOrderManageInfo;
import com.wsdm.sale.domain.SalesOrderManageRequest;

import java.util.List;
import java.util.Map;

/**
 * 销售订单Service接口
 *
 * @author wsdm
 * @date 2020-11-06
 */
public interface ISalesOrderManageService {
    /**
     * 查询销售订单详情信息
     *
     * @param id 销售订单ID
     * @return 销售订单详情
     */
    SalesOrderManageInfo selectSalesOrderManageById(Long id);

    /**
     * 查询销售订单列表
     *
     * @param salesOrderManage 销售订单
     * @return 销售订单集合
     */
    List<SalesOrderManageInfo> selectSalesOrderManageList(SalesOrderManageRequest salesOrderManage);

    List<Map> getSalesOrderByDelivery(SalesOrderManageRequest salesOrderManage);

    /**
     * 自动采购--销售单查询展示
     */
    List<SalesOrderAutomaticPurchase> salesOrderAutomaticPurchase();

    /**
     * 新增销售订单
     *
     * @param salesOrderManage 销售订单
     * @return 结果
     */
    AjaxResult insertSalesOrderManage(SalesOrderManage salesOrderManage);

    /**
     * 修改销售订单
     *
     * @param salesOrderManage 销售订单
     * @return 结果
     */
    AjaxResult updateSalesOrderManage(SalesOrderManage salesOrderManage);

    /**
     * 删除销售订单信息
     *
     * @param id 销售订单ID
     * @return 结果
     */
    AjaxResult deleteSalesOrderManageById(Long id);

    AjaxResult approve(Long id, String action);
}
