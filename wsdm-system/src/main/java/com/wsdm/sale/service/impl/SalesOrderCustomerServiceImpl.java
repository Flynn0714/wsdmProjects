package com.wsdm.sale.service.impl;

import com.wsdm.common.CodeUtils;
import com.wsdm.common.enums.WisdomBusinessStatus;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.sale.domain.SalesOrderCustomer;
import com.wsdm.sale.mapper.SalesOrderCustomerMapper;
import com.wsdm.sale.service.ISalesOrderCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.wsdm.common.constant.Constants.CUSTOMER_M;

/**
 * 客户管理Service业务层处理
 *
 * @author wsdm
 * @date 2020-11-06
 */
@Service
public class SalesOrderCustomerServiceImpl implements ISalesOrderCustomerService {
    @Autowired
    private SalesOrderCustomerMapper salesOrderCustomerMapper;

    /**
     * 查询客户管理
     *
     * @param id 客户管理ID
     * @return 客户管理
     */
    @Override
    public SalesOrderCustomer selectSalesOrderCustomerById(Long id) {
        return salesOrderCustomerMapper.selectSalesOrderCustomerById(id);
    }

    /**
     * 查询客户管理
     *
     * @param customerNumber 客户编码
     * @return 客户管理
     */
    @Override
    public SalesOrderCustomer selectSalesOrderCustomerByCustomerNumber(String customerNumber) {
        return salesOrderCustomerMapper.selectSalesOrderCustomerByCustomerNumber(customerNumber);
    }

    /**
     * 查询客户管理列表
     *
     * @param salesOrderCustomer 客户管理
     * @return 客户管理
     */
    @Override
    public List<SalesOrderCustomer> selectSalesOrderCustomerList(SalesOrderCustomer salesOrderCustomer) {
        return salesOrderCustomerMapper.selectSalesOrderCustomerList(salesOrderCustomer);
    }

    /**
     * 新增客户管理
     *
     * @param salesOrderCustomer 客户管理
     * @return 结果
     */
    @Override
    public int insertSalesOrderCustomer(SalesOrderCustomer salesOrderCustomer) {

        String maxCode = salesOrderCustomerMapper.getMaxCustomerCode(CUSTOMER_M);

        salesOrderCustomer.setCreateBy(SecurityUtils.getUsername());
        salesOrderCustomer.setCreateTime(DateUtils.getNowDate());
        salesOrderCustomer.setStatus(WisdomBusinessStatus.STATUS_NORMAL.getCode());
        salesOrderCustomer.setCustomerNumber(CodeUtils.getOneCode(CUSTOMER_M, maxCode, 3));
        return salesOrderCustomerMapper.insertSalesOrderCustomer(salesOrderCustomer);
    }

    /**
     * 修改客户管理
     *
     * @param salesOrderCustomer 客户管理
     * @return 结果
     */
    @Override
    public int updateSalesOrderCustomer(SalesOrderCustomer salesOrderCustomer) {
        salesOrderCustomer.setUpdateBy(SecurityUtils.getUsername());
        salesOrderCustomer.setUpdateTime(DateUtils.getNowDate());
        return salesOrderCustomerMapper.updateSalesOrderCustomer(salesOrderCustomer);
    }

    /**
     * 批量删除客户管理
     *
     * @param ids 需要删除的客户管理ID
     * @return 结果
     */
    @Override
    public int deleteSalesOrderCustomerByIds(Long[] ids) {
        return salesOrderCustomerMapper.deleteSalesOrderCustomerByIds(ids);
    }

    /**
     * 删除客户管理信息
     *
     * @param id 客户管理ID
     * @return 结果
     */
    @Override
    public int deleteSalesOrderCustomerById(Long id) {
        return salesOrderCustomerMapper.deleteSalesOrderCustomerById(id);
    }
}
