package com.wsdm.sale.service.impl;

import com.wsdm.common.enums.TransactionType;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.sale.domain.SalesOrderTransactionDetails;
import com.wsdm.sale.domain.SalesOrderTransactionQuery;
import com.wsdm.sale.mapper.SalesOrderTransactionDetailsMapper;
import com.wsdm.sale.service.ISalesOrderTransactionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


/**
 * 销售交易记录Service业务层处理
 *
 * @author wsdm
 * @date 2021-07-17
 */
@Service
public class SalesOrderTransactionDetailsServiceImpl implements ISalesOrderTransactionDetailsService {
    @Autowired
    private SalesOrderTransactionDetailsMapper salesOrderTransactionDetailsMapper;

    /**
     * 查询销售交易记录
     *
     * @param id 销售交易记录ID
     * @return 销售交易记录
     */
    @Override
    public SalesOrderTransactionDetails selectSalesOrderTransactionDetailsById(Long id) {
        return salesOrderTransactionDetailsMapper.selectSalesOrderTransactionDetailsById(id);
    }

    /**
     * 查询销售交易记录列表
     *
     * @param salesOrderTransactionDetails 销售交易记录
     * @return 销售交易记录
     */
    @Override
    public List<SalesOrderTransactionDetails> selectSalesOrderTransactionDetailsList(SalesOrderTransactionQuery salesOrderTransactionDetails) {
        List<SalesOrderTransactionDetails> list = salesOrderTransactionDetailsMapper.selectSalesOrderTransactionDetailsList(salesOrderTransactionDetails);
        list.stream().forEach(t -> t.setTransactionType(TransactionType.codeToValue(t.getTransactionType())));
        return list;
    }

    /**
     * 查询销售交易记录列表
     *
     * @param salesOrderTransactionDetails 销售交易记录
     * @return 销售交易记录
     */
    @Override
    public List<SalesOrderTransactionDetails> selectSalesOrderTransactionDetailsListTwo(SalesOrderTransactionQuery salesOrderTransactionDetails) {
        List<SalesOrderTransactionDetails> list = salesOrderTransactionDetailsMapper.selectSalesOrderTransactionDetailsListTwo(salesOrderTransactionDetails);
        list.stream().forEach(t -> t.setTransactionType(TransactionType.codeToValue(t.getTransactionType())));
        return list;
    }

    @Override
    public BigDecimal getTransactionQuantity(SalesOrderTransactionQuery salesOrderTransactionDetails) {
        return salesOrderTransactionDetailsMapper.getTransactionQuantity(salesOrderTransactionDetails);
    }

    @Override
    public BigDecimal getTransactionAmount(SalesOrderTransactionQuery salesOrderTransactionDetails) {

        return salesOrderTransactionDetailsMapper.getTransactionAmount(salesOrderTransactionDetails);
    }

    /**
     * 新增销售交易记录
     *
     * @param list 销售交易记录
     * @return 结果
     */
    @Override
    public int insertSalesOrderTransactionDetails(TransactionType transactionType, List<SalesOrderTransactionDetails> list) {

        list.stream().forEach(salesOrderTransactionDetails -> {
            salesOrderTransactionDetails.setTransactionDate(DateUtils.getNowDate());
            salesOrderTransactionDetails.setTransactionType(transactionType.getCode());

            salesOrderTransactionDetails.setCreateBy(SecurityUtils.getUsername());
            salesOrderTransactionDetails.setCreateTime(DateUtils.getNowDate());
        });
        return salesOrderTransactionDetailsMapper.insertSalesOrderTransactionDetailsBatch(list);
    }

    /**
     * 修改销售交易记录
     *
     * @param salesOrderTransactionDetails 销售交易记录
     * @return 结果
     */
    @Override
    public int updateSalesOrderTransactionDetails(SalesOrderTransactionDetails salesOrderTransactionDetails) {
        salesOrderTransactionDetails.setUpdateTime(DateUtils.getNowDate());
        return salesOrderTransactionDetailsMapper.updateSalesOrderTransactionDetails(salesOrderTransactionDetails);
    }

    /**
     * 批量删除销售交易记录
     *
     * @param ids 需要删除的销售交易记录ID
     * @return 结果
     */
    @Override
    public int deleteSalesOrderTransactionDetailsByIds(Long[] ids) {
        return salesOrderTransactionDetailsMapper.deleteSalesOrderTransactionDetailsByIds(ids);
    }

    /**
     * 删除销售交易记录信息
     *
     * @param id 销售交易记录ID
     * @return 结果
     */
    @Override
    public int deleteSalesOrderTransactionDetailsById(Long id) {
        return salesOrderTransactionDetailsMapper.deleteSalesOrderTransactionDetailsById(id);
    }
}
