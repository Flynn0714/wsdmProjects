package com.wsdm.sale.mapper;

import com.wsdm.sale.domain.SalesOrderCustomer;

import java.util.List;

/**
 * 客户管理Mapper接口
 *
 * @author wsdm
 * @date 2020-11-06
 */
public interface SalesOrderCustomerMapper {
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
     * @param customerNumber 客户编码
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
    public int insertSalesOrderCustomer(SalesOrderCustomer salesOrderCustomer);

    /**
     * 修改客户管理
     *
     * @param salesOrderCustomer 客户管理
     * @return 结果
     */
    public int updateSalesOrderCustomer(SalesOrderCustomer salesOrderCustomer);

    /**
     * 删除客户管理
     *
     * @param id 客户管理ID
     * @return 结果
     */
    public int deleteSalesOrderCustomerById(Long id);

    /**
     * 批量删除客户管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSalesOrderCustomerByIds(Long[] ids);


    public String getMaxCustomerCode(String code);
}
