package com.wsdm.sale.service;

import com.wsdm.common.enums.TransactionType;
import com.wsdm.sale.domain.SalesOrderTransactionDetails;
import com.wsdm.sale.domain.SalesOrderTransactionQuery;

import java.math.BigDecimal;
import java.util.List;


/**
 * 销售交易记录Service接口
 *
 * @author wsdm
 * @date 2021-07-17
 */
public interface ISalesOrderTransactionDetailsService {
    /**
     * 查询销售交易记录
     *
     * @param id 销售交易记录ID
     * @return 销售交易记录
     */
    public SalesOrderTransactionDetails selectSalesOrderTransactionDetailsById(Long id);

    /**
     * 查询销售交易记录列表
     *
     * @param salesOrderTransactionDetails 销售交易记录
     * @return 销售交易记录集合
     */
    public List<SalesOrderTransactionDetails> selectSalesOrderTransactionDetailsList(SalesOrderTransactionQuery salesOrderTransactionDetails);
    /**
     * 查询销售交易记录列表
     *
     * @param salesOrderTransactionDetails 销售交易记录
     * @return 销售交易记录集合
     */
    public List<SalesOrderTransactionDetails> selectSalesOrderTransactionDetailsListTwo(SalesOrderTransactionQuery salesOrderTransactionDetails);

    BigDecimal getTransactionQuantity(SalesOrderTransactionQuery salesOrderTransactionDetails);

    BigDecimal getTransactionAmount(SalesOrderTransactionQuery salesOrderTransactionDetails);
    /**
     * 新增销售交易记录
     *
     * @param salesOrderTransactionDetails 销售交易记录
     * @return 结果
     */
    public int insertSalesOrderTransactionDetails(TransactionType transactionType, List<SalesOrderTransactionDetails> list);

    /**
     * 修改销售交易记录
     *
     * @param salesOrderTransactionDetails 销售交易记录
     * @return 结果
     */
    public int updateSalesOrderTransactionDetails(SalesOrderTransactionDetails salesOrderTransactionDetails);

    /**
     * 批量删除销售交易记录
     *
     * @param ids 需要删除的销售交易记录ID
     * @return 结果
     */
    public int deleteSalesOrderTransactionDetailsByIds(Long[] ids);

    /**
     * 删除销售交易记录信息
     *
     * @param id 销售交易记录ID
     * @return 结果
     */
    public int deleteSalesOrderTransactionDetailsById(Long id);
}
