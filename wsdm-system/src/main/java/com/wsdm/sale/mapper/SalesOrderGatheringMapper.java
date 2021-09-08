package com.wsdm.sale.mapper;

import com.wsdm.sale.domain.SalesOrderGathering;
import com.wsdm.sale.domain.SalesOrderGatheringDetails;
import com.wsdm.sale.domain.SalesOrderGatheringInfo;
import com.wsdm.sale.domain.SalesOrderGatheringQuery;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 销售单收款Mapper接口
 *
 * @author wsdm
 * @date 2021-04-27
 */
public interface SalesOrderGatheringMapper {
    /**
     * 查询销售单收款
     *
     * @param id 销售单收款ID
     * @return 销售单收款
     */
    SalesOrderGatheringInfo selectSalesOrderGatheringById(Long id);

    SalesOrderGathering selectSalesOrderGatheringByCode(String gatheringNumber);
    /**
     * 查询销售单收款列表
     *
     * @param salesOrderGathering 销售单收款
     * @return 销售单收款集合
     */
    List<SalesOrderGathering> selectSalesOrderGatheringList(SalesOrderGatheringQuery salesOrderGathering);

    List<SalesOrderGatheringDetails> getGatheringDetailsByNumber(String number);

    BigDecimal getGatheringAmount(String deliveryNumber);

    List<Map> getSalesGatheringOrder(@Param("salesNumber") String salesNumber, @Param("customer") String customer);

    String getMaxGatheringCode(String code);
    /**
     * 新增销售单收款
     *
     * @param salesOrderGathering 销售单收款
     * @return 结果
     */
    int insertSalesOrderGathering(SalesOrderGathering salesOrderGathering);

    /**
     * 修改销售单收款
     *
     * @param salesOrderGathering 销售单收款
     * @return 结果
     */
    int updateSalesOrderGathering(SalesOrderGathering salesOrderGathering);

    int insertGatheringDetailsBatch(List<SalesOrderGatheringDetails> gatheringDetails);

    int updateGatheringDetailsBatch(List<SalesOrderGatheringDetails> gatheringDetails);

    /**
     * 删除销售单收款
     *
     * @param id 销售单收款ID
     * @return 结果
     */
    int deleteSalesOrderGatheringById(Long id);

    /**
     * 批量删除销售单收款
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSalesOrderGatheringByIds(Long[] ids);

    int deleteSalesOrderGatheringDetailsByIds(List<SalesOrderGatheringDetails> list);
}
