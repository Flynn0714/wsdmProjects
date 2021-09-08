package com.wsdm.sale.service;

import com.wsdm.sale.domain.SalesOrderAccountReceived;
import com.wsdm.sale.domain.SalesOrderAccountReceivedQuery;

import java.util.List;


/**
 * 收款记录Service接口
 *
 * @author wsdm
 * @date 2021-07-17
 */
public interface ISalesOrderAccountReceivedService {
    /**
     * 查询收款记录
     *
     * @param id 收款记录ID
     * @return 收款记录
     */
    public SalesOrderAccountReceived selectSalesOrderAccountReceivedById(Long id);

    /**
     * 查询收款记录列表
     *
     * @param salesOrderAccountReceived 收款记录
     * @return 收款记录集合
     */
    public List<SalesOrderAccountReceived> selectSalesOrderAccountReceivedList(SalesOrderAccountReceivedQuery salesOrderAccountReceived);

    /**
     * 新增收款记录
     *
     * @param salesOrderAccountReceived 收款记录
     * @return 结果
     */
    public int insertSalesOrderAccountReceived(SalesOrderAccountReceived salesOrderAccountReceived);

    /**
     * 新增预收款记录
     *
     * @param list 收款记录
     * @return 结果
     */
    public int insertSalesOrderAccountReceivedBatch(List<SalesOrderAccountReceived> list);

    /**
     * 修改收款记录
     *
     * @param salesOrderAccountReceived 收款记录
     * @return 结果
     */
    public int updateSalesOrderAccountReceived(SalesOrderAccountReceived salesOrderAccountReceived);

    /**
     * 批量删除收款记录
     *
     * @param ids 需要删除的收款记录ID
     * @return 结果
     */
    public int deleteSalesOrderAccountReceivedByIds(Long[] ids);

    /**
     * 删除收款记录信息
     *
     * @param id 收款记录ID
     * @return 结果
     */
    public int deleteSalesOrderAccountReceivedById(Long id);
}
