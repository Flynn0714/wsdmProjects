package com.wsdm.sale.service;

import com.wsdm.sale.domain.SalesOrderCustomer;

import java.util.List;

/**
 * 销售Service接口
 *
 * @author wsdm
 * @date 2020-11-06
 */
public interface ISalesOrderCustomerService {
    /**
     * 查询客户管理
     *
     * @param id 客户管理ID
     * @return 客户管理
     */
    public SalesOrderCustomer selectSalesOrderCustomerById(Long id);

    /**
     * 查询客户管理
     *
     * @param customerNumber 客户编号
     * @return 客户管理
     */
    public SalesOrderCustomer selectSalesOrderCustomerByCustomerNumber(String customerNumber);

    /**
     * 查询客户管理列表
     *
     * @param salesOrderCustomer 客户管理
     * @return 客户管理集合
     */
    public List<SalesOrderCustomer> selectSalesOrderCustomerList(SalesOrderCustomer salesOrderCustomer);

    /**
     * 新增客户管理
     *
     * @param salesOrderCustomer 客户管理
     * @return 结果
     */
    public int insertSalesOrderCustomer(SalesOrderCustomer salesOrderCustomer) throws Exception;

    /**
     * 修改客户管理
     *
     * @param salesOrderCustomer 客户管理
     * @return 结果
     */
    public int updateSalesOrderCustomer(SalesOrderCustomer salesOrderCustomer);

    /**
     * 批量删除客户管理
     *
     * @param ids 需要删除的客户管理ID
     * @return 结果
     */
    public int deleteSalesOrderCustomerByIds(Long[] ids);

    /**
     * 删除客户管理信息
     *
     * @param id 客户管理ID
     * @return 结果
     */
    public int deleteSalesOrderCustomerById(Long id);
}
