package com.wsdm.sale.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.sale.domain.SalesOrderDelivery;
import com.wsdm.sale.domain.SalesOrderDeliveryDetails;
import com.wsdm.sale.domain.SalesOrderDeliveryInfo;
import com.wsdm.sale.domain.SalesOrderDeliveryQuery;

import java.util.List;
import java.util.Map;

/**
 * 销售单发货Service接口
 *
 * @author wsdm
 * @date 2021-04-27
 */
public interface ISalesOrderDeliveryService {
    /**
     * 查询销售单发货
     *
     * @param id 销售单发货ID
     * @return 销售单发货
     */
    SalesOrderDeliveryInfo selectSalesOrderDeliveryById(Long id);
    /**
     * 查询销售单发货
     *
     * @param id 销售单发货ID
     * @return 销售单发货
     */
    SalesOrderDeliveryInfo selectSalesOrderDeliveryById(String deliveryNumber);

    Map getAddInfo(String salesNumber);
    /**
     * 查询销售单发货列表
     *
     * @param salesOrderDeliveryQuery 销售单发货
     * @return 销售单发货集合
     */
    List<SalesOrderDeliveryInfo> selectSalesOrderDeliveryList(SalesOrderDeliveryQuery salesOrderDeliveryQuery);

    List<Map> getSalesDeliveryOrder(String deliveryNumber, String customer);

    List<SalesOrderDeliveryDetails> getDeliverDetailsByDeliveryNumber(String deliveryNumber);

    /**
     * 新增销售单发货
     *
     * @param salesOrderDelivery 销售单发货
     * @return 结果
     */
    AjaxResult insertSalesOrderDelivery(SalesOrderDelivery salesOrderDelivery);

    /**
     * 修改销售单发货
     *
     * @param salesOrderDelivery 销售单发货
     * @return 结果
     */
    AjaxResult updateSalesOrderDelivery(SalesOrderDelivery salesOrderDelivery);

    /**
     * 删除销售单发货信息
     *
     * @param id 销售单发货ID
     * @return 结果
     */
    AjaxResult deleteSalesOrderDeliveryById(Long id);

    AjaxResult approve(Long id, String action);

}
