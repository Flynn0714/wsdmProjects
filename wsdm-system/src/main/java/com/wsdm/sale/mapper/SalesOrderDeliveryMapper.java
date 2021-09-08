package com.wsdm.sale.mapper;

import com.wsdm.sale.domain.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 销售单发货Mapper接口
 *
 * @author wsdm
 * @date 2021-04-27
 */
public interface SalesOrderDeliveryMapper {
    /**
     * 查询销售单发货列表
     *
     * @param salesOrderDeliveryQuery 销售单发货
     * @return 销售单发货集合
     */
    List<SalesOrderDeliveryInfo> selectSalesOrderDeliveryList(SalesOrderDeliveryQuery salesOrderDeliveryQuery);

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
    SalesOrderDeliveryInfo selectSalesOrderDeliveryByNumber(String deliveryNumber);



    Map getAddDeliverInfoBySalesNumber(String salesNumber);

    SalesOrderDelivery selectSalesOrderDeliveryByCode(String deliveryNumber);

    List<Map> getSalesDeliveryOrder(@Param("deliveryNumber") String salesNumber, @Param("customer") String customer);

    List<SalesOrderDeliveryDetails> getDeliverDetailsBySalesNumber(String salesNumber);

    List<SalesOrderDeliveryDetails> getDeliverDetailsByDeliveryNumber(String deliveryNumber);

    List<Map> getDeliverDetails(@Param("deliveryNumber") String salesNumber, @Param("customer") String customer);

    Map getDeliverQuantity(@Param("orderDetailsId") String orderDetailsId, @Param("id") String id);

    BigDecimal getDeliverTotal(String salesNumber);

    String getMaxDeliveryCode(String code);

    /**
     * 新增销售单发货
     *
     * @param salesOrderDelivery 销售单发货
     * @return 结果
     */
    int insertSalesOrderDelivery(SalesOrderDelivery salesOrderDelivery);

    /**
     * 修改销售单发货
     *
     * @param salesOrderDelivery 销售单发货
     * @return 结果
     */
    int updateSalesOrderDelivery(SalesOrderDelivery salesOrderDelivery);

    /**
     * 批量新增销售发货单
     *
     * @param salesOrderDelivery 销售单发货
     * @return 结果
     */
    int insertDeliveryDetailsBatch(List<SalesOrderDeliveryDetails> list);

    /**
     * 批量修改销售单发货
     *
     * @param salesOrderDelivery 销售单发货
     * @return 结果
     */
    int updateDeliveryDetailsBatch(List<SalesOrderDeliveryDetails> list);

    int updateDeliveredQuantityBatch(List<SalesOrderDeliveryDetails> list);

    /**
     * 删除销售单发货
     *
     * @param id 销售单发货ID
     * @return 结果
     */
    int deleteSalesOrderDeliveryById(Long id);

}
