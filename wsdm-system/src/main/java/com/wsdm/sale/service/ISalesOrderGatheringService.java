package com.wsdm.sale.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.sale.domain.SalesOrderGathering;
import com.wsdm.sale.domain.SalesOrderGatheringInfo;
import com.wsdm.sale.domain.SalesOrderGatheringQuery;

import java.util.List;
import java.util.Map;

/**
 * 销售单收款Service接口
 *
 * @author wsdm
 * @date 2021-04-27
 */
public interface ISalesOrderGatheringService {
    /**
     * 查询销售单收款
     *
     * @param id 销售单收款ID
     * @return 销售单收款
     */
    SalesOrderGatheringInfo selectSalesOrderGatheringById(Long id);

    /**
     * 查询销售单收款列表
     *
     * @param salesOrderGathering 销售单收款
     * @return 销售单收款集合
     */
    List<SalesOrderGathering> selectSalesOrderGatheringList(SalesOrderGatheringQuery salesOrderGathering);

    List<Map> getSalesGatheringOrder(String salesNumber, String customer);

    /**
     * 新增销售单收款
     *
     * @param salesOrderGathering 销售单收款
     * @return 结果
     */
    AjaxResult insertSalesOrderGathering(SalesOrderGathering salesOrderGathering);

    /**
     * 修改销售单收款
     *
     * @param salesOrderGathering 销售单收款
     * @return 结果
     */
    AjaxResult updateSalesOrderGathering(SalesOrderGathering salesOrderGathering);

    /**
     * 删除销售单收款信息
     *
     * @param id 销售单收款ID
     * @return 结果
     */
    int deleteSalesOrderGatheringById(Long id);

    AjaxResult approve(Long id, String action);
}
