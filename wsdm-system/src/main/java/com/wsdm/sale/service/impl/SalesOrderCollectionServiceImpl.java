package com.wsdm.sale.service.impl;

import com.wsdm.common.enums.AccountReceivedType;
import com.wsdm.common.enums.TransactionType;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.sale.domain.*;
import com.wsdm.sale.mapper.SalesOrderAccountReceivedMapper;
import com.wsdm.sale.mapper.SalesOrderCollectionMapper;
import com.wsdm.sale.mapper.SalesOrderCustomerMapper;
import com.wsdm.sale.mapper.SalesOrderTransactionDetailsMapper;
import com.wsdm.sale.service.ISalesOrderCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 收款汇总Service业务层处理
 *
 * @author wsdm
 * @date 2021-07-17
 */
@Service
public class SalesOrderCollectionServiceImpl implements ISalesOrderCollectionService {
    @Autowired
    private SalesOrderCollectionMapper salesOrderCollectionMapper;

    @Autowired
    private SalesOrderCustomerMapper customerMapper;

    @Autowired
    private SalesOrderAccountReceivedMapper accountReceivedMapper;

    @Autowired
    private SalesOrderTransactionDetailsMapper transactionDetailsMapper;

    /**
     * 查询收款汇总
     *
     * @param id 收款汇总ID
     * @return 收款汇总
     */
    @Override
    public SalesOrderCollectionInfo selectSalesOrderCollectionById(Long id) {
        SalesOrderCollectionInfo info = new SalesOrderCollectionInfo();

        SalesOrderCollection salesOrderCollection = salesOrderCollectionMapper.selectSalesOrderCollectionById(id);

        if (salesOrderCollection == null) {
            throw new BaseException("没有该收款汇总信息");
        }

        salesOrderCollection.setGatheringAmount(salesOrderCollection.getTransactionTotal().subtract(salesOrderCollection.getGatheringTotal()).setScale(2));

        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.MONTH, -3);  //设置为前3月
        dBefore = calendar.getTime();   //得到前3月的时间

        SalesOrderAccountReceivedQuery receivedQuery = new SalesOrderAccountReceivedQuery();
        receivedQuery.setCustomer(salesOrderCollection.getCustomer());
        receivedQuery.setGatheringDateStart(dBefore);
        receivedQuery.setGatheringDateEnd(dNow);
        List<SalesOrderAccountReceived> receivedList = accountReceivedMapper.selectSalesOrderAccountReceivedList(receivedQuery);
        receivedList.stream().forEach(a -> a.setGatheringType(AccountReceivedType.codeToValue(a.getGatheringType())));

        SalesOrderTransactionQuery transactionQuery = new SalesOrderTransactionQuery();
        transactionQuery.setCustomer(salesOrderCollection.getCustomer());
        transactionQuery.setTransactionDateStart(dBefore);
        transactionQuery.setTransactionDateEnd(dNow);
        List<SalesOrderTransactionDetails> transactionList = transactionDetailsMapper.selectSalesOrderTransactionDetailsList(transactionQuery);
        transactionList.stream().forEach(t -> t.setTransactionType(TransactionType.codeToValue(t.getTransactionType())));

        info.setAccountReceivedList(receivedList);
        info.setTransactionDetailsList(transactionList);
        info.setSalesOrderCollection(salesOrderCollection);
        return info;
    }

    /**
     * 查询收款汇总列表
     *
     * @param salesOrderCollection 收款汇总
     * @return 收款汇总
     */
    @Override
    public List<SalesOrderCollection> selectSalesOrderCollectionList(SalesOrderCollection salesOrderCollection) {
        List<SalesOrderCollection> list = salesOrderCollectionMapper.selectSalesOrderCollectionList(salesOrderCollection);
        list.stream().forEach(c -> c.setGatheringAmount(c.getTransactionTotal().subtract(c.getGatheringTotal())));
        return list;
    }

    /**
     * 新增收款汇总
     *
     * @param customer 客户编码
     * @param transactionTotal 发生的销售金额   没有发生传 0
     * @param gatheringTotal 发生的 退换货和预收款金额  退换传改金额的负值 比如 -200， 如果有金额查同样处理 没有传 0
     * @param transactionDate 交易日期 （退换货） 没有传null
     *      * @param gatheringDate 收款日期 没有传null
     *
     * @return 结果
     */
    @Override
    public int insertSalesOrderCollection(String customer, BigDecimal transactionTotal,
                                          BigDecimal gatheringTotal, Date transactionDate, Date gatheringDate) {
        // 获取客户
        SalesOrderCustomer orderCustomer = customerMapper.selectSalesOrderCustomerByCustomerNumber(customer);
        if (orderCustomer == null) {
            throw new BaseException("客户不存在");
        }

        // 获取客户的收款汇总
        SalesOrderCollection collection = salesOrderCollectionMapper.getOrderCollectionByCustomer(customer);
        Date now = DateUtils.getNowDate();
        String userName = SecurityUtils.getUsername();

        if (collection != null && collection.getId() != null) {
            SalesOrderCollection update = new SalesOrderCollection();
            update.setId(collection.getId());
            update.setGatheringTotal(collection.getGatheringTotal().add(gatheringTotal));
            update.setTransactionTotal(collection.getTransactionTotal().add(transactionTotal));

            update.setUpdateTime(now);
            update.setUpdateBy(userName);

            return salesOrderCollectionMapper.updateSalesOrderCollection(update);

        } else {
            SalesOrderCollection insert = new SalesOrderCollection(
                    customer,
                    orderCustomer.getLevel(),
                    orderCustomer.getLinkman(),
                    orderCustomer.getPhone(),
                    orderCustomer.getSalesperson(),
                    transactionDate,
                    gatheringDate
            );
            insert.setGatheringTotal(gatheringTotal);
            insert.setTransactionTotal(transactionTotal);

            insert.setCreateTime(now);
            insert.setCreateBy(userName);

            return salesOrderCollectionMapper.insertSalesOrderCollection(insert);
        }
    }

    /**
     * 修改收款汇总
     *
     * @param salesOrderCollection 收款汇总
     * @return 结果
     */
    @Override
    public int updateSalesOrderCollection(SalesOrderCollection salesOrderCollection) {
        salesOrderCollection.setUpdateTime(DateUtils.getNowDate());
        return salesOrderCollectionMapper.updateSalesOrderCollection(salesOrderCollection);
    }

    /**
     * 批量删除收款汇总
     *
     * @param ids 需要删除的收款汇总ID
     * @return 结果
     */
    @Override
    public int deleteSalesOrderCollectionByIds(Long[] ids) {
        return salesOrderCollectionMapper.deleteSalesOrderCollectionByIds(ids);
    }

    /**
     * 删除收款汇总信息
     *
     * @param id 收款汇总ID
     * @return 结果
     */
    @Override
    public int deleteSalesOrderCollectionById(Long id) {
        return salesOrderCollectionMapper.deleteSalesOrderCollectionById(id);
    }
}
