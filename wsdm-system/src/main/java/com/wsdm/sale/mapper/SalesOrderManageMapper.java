package com.wsdm.sale.mapper;

import com.wsdm.sale.domain.*;

import java.util.List;
import java.util.Map;

/**
 * 销售订单Mapper接口
 *
 * @author wsdm
 * @date 2020-11-06
 */
public interface SalesOrderManageMapper {
    /**
     * 查询销售订单
     *
     * @param id 销售订单ID
     * @return 销售订单
     */
    SalesOrderManageInfo selectSalesOrderManageById(Long id);

    SalesOrderManageInfo selectSalesOrderManageBySalesNumber(String salesNumber);

    /**
     * 查询销售订单列表
     *
     * @param salesOrderManage 销售订单
     * @return 销售订单集合
     */
    List<SalesOrderManageInfo> selectSalesOrderManageList(SalesOrderManageRequest salesOrderManage);

    List<Map> getSalesOrderByDelivery(SalesOrderManageRequest salesOrderManage);

    /**
     * 查询销售订单详情列表
     *
     * @param salesNumber 销售单号
     * @return 销售订单详情列表
     */
    List<SalesOrderManageDetails> selectSalesOrderManageDetailsList(String salesNumber);

    /**
     * 查询销售订单预收款详情列表
     *
     * @param salesNumber 销售单号
     * @return 销售订单预收款详情列表
     */
    List<SalesOrderManageGathering> getOrderGatheringByNumber(String salesNumber);

    List<SalesOrderAutomaticPurchase> salesOrderAutomaticPurchase();
    /**
     * 查询最大销售订单合同编号
     *
     * @param code 合同编号前缀
     * @return 结果
     */
    String getMaxSalesCode(String code);

    /**
     * 新增销售订单
     *
     * @param salesOrderManage 销售订单
     * @return 结果
     */
    int insertSalesOrderManage(SalesOrderManage salesOrderManage);

    /**
     * 修改销售订单
     *
     * @param salesOrderManage 销售订单
     * @return 结果
     */
    int updateSalesOrderManage(SalesOrderManage salesOrderManage);

    // 销售单审核
    int approveSalesOrderManage(SalesOrderManage salesOrderManage);

    /**
     * 新增销售订单详情
     *
     * @param list 销售订单详情
     * @return 结果
     */
    int insertSalesOrderManageDetailsBatch(List<SalesOrderManageDetails> list);


    /**
     * 新增销售单预收款详情
     *
     * @param list 销售订单详情
     * @return 结果
     */
    int insertOrderManageGatheringBatch(List<SalesOrderManageGathering> list);

    /**
     * 修改销售订单
     *
     * @param salesOrderManageDetails 销售订单
     * @return 结果
     */
    int updateSalesOrderManageDetailsBatch(List<SalesOrderManageDetails> salesOrderManageDetails);

    /**
     * 删除销售订单
     *
     * @param id 销售订单ID
     * @return 结果
     */
    int deleteSalesOrderManageById(Long id);

    /**
     * 删除销售订单详情列表
     *
     * @param salesNumber 合同号
     * @return 结果
     */
    int deleteOrderDetailsBySalesNumber(String salesNumber);

    /**
     * 删除销售订单预收款详情列表
     *
     * @param salesNumber 合同号
     * @return 结果
     */
    int deleteOrderGatheringBySalesNumber(String salesNumber);
}
