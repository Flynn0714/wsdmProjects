package com.wsdm.sale.service;
import com.wsdm.sale.domain.SalesOrderCollection;
import com.wsdm.sale.domain.SalesOrderCollectionInfo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 收款汇总Service接口
 *
 * @author wsdm
 * @date 2021-07-17
 */
public interface ISalesOrderCollectionService {
    /**
     * 查询收款汇总
     *
     * @param id 收款汇总ID
     * @return 收款汇总
     */
    public SalesOrderCollectionInfo selectSalesOrderCollectionById(Long id);

    /**
     * 查询收款汇总列表
     *
     * @param salesOrderCollection 收款汇总
     * @return 收款汇总集合
     */
    public List<SalesOrderCollection> selectSalesOrderCollectionList(SalesOrderCollection salesOrderCollection);

    /**
     * 新增收款汇总
     *
     * @param salesOrderCollection 收款汇总
     * @return 结果
     */
    int insertSalesOrderCollection(String customer, BigDecimal transactionTotal,
                                   BigDecimal gatheringTotal, Date transactionDate, Date gatheringDate);

    /**
     * 修改收款汇总
     *
     * @param salesOrderCollection 收款汇总
     * @return 结果
     */
    public int updateSalesOrderCollection(SalesOrderCollection salesOrderCollection);

    /**
     * 批量删除收款汇总
     *
     * @param ids 需要删除的收款汇总ID
     * @return 结果
     */
    public int deleteSalesOrderCollectionByIds(Long[] ids);

    /**
     * 删除收款汇总信息
     *
     * @param id 收款汇总ID
     * @return 结果
     */
    public int deleteSalesOrderCollectionById(Long id);
}
