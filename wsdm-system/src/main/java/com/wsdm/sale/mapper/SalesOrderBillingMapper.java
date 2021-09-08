package com.wsdm.sale.mapper;

import com.wsdm.sale.domain.SalesOrderBilling;
import com.wsdm.sale.domain.SalesOrderBillingQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 销售单开票Mapper接口
 *
 * @author wsdm
 * @date 2021-04-27
 */
public interface SalesOrderBillingMapper {
    /**
     * 查询销售单开票
     *
     * @param id 销售单开票ID
     * @return 销售单开票
     */
    SalesOrderBilling selectSalesOrderBillingById(Long id);

    /**
     * 查询销售单开票列表
     *
     * @param salesOrderBilling 销售单开票
     * @return 销售单开票集合
     */
    List<SalesOrderBilling> selectSalesOrderBillingList(SalesOrderBillingQuery salesOrderBilling);

    String getMaxBillingCode(@Param("code") String code);

    /**
     * 新增销售单开票
     *
     * @param salesOrderBilling 销售单开票
     * @return 结果
     */
    int insertSalesOrderBilling(SalesOrderBilling salesOrderBilling);

    /**
     * 修改销售单开票
     *
     * @param salesOrderBilling 销售单开票
     * @return 结果
     */
    int updateSalesOrderBilling(SalesOrderBilling salesOrderBilling);

    /**
     * 删除销售单开票
     *
     * @param id 销售单开票ID
     * @return 结果
     */
    int deleteSalesOrderBillingById(Long id);
}
