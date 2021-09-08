package com.wsdm.sale.service.impl;

import com.wsdm.common.CodeUtils;
import com.wsdm.common.constant.Constants;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.TransactionType;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.sale.domain.*;
import com.wsdm.sale.mapper.*;
import com.wsdm.sale.service.ISalesOrderCollectionService;
import com.wsdm.sale.service.ISalesOrderExchangeService;
import com.wsdm.sale.service.ISalesOrderTransactionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 换货单Service业务层处理
 *
 * @author 符方龙
 * @date 2021年7月13日23:34:39
 */
@Service
public class SalesOrderExchangeServiceImpl implements ISalesOrderExchangeService {

    @Autowired
    private SalesOrderExchangeMapper salesOrderExchangeMapper;

    @Autowired
    private SalesOrderDeliveryMapper salesOrderDeliveryMapper;

    @Autowired
    private SalesOrderTransactionDetailsMapper salesOrderTransactionDetailsMapper; //交易记录对象

    @Autowired
    private SalesOrderCollectionMapper salesOrderCollectionMapper; //汇总对象

    @Autowired
    private SalesOrderCustomerMapper salesOrderCustomerMapper;

    @Autowired
    private ISalesOrderCollectionService salesOrderCollectionService;

    @Autowired
    private ISalesOrderTransactionDetailsService salesOrderTransactionDetailsService;


    @Override
    public List<SalesOrderExchange> queryExchangeList(SalesOrderExchangeQuery salesOrderExchangeQuery) {
        return salesOrderExchangeMapper.queryExchangeList(salesOrderExchangeQuery);
    }

    @Override
    public List<SalesOrderExchangeSelect> queryDeliverySalesOrders(SalesOrderExchangeQuery salesOrderExchangeQuery) {
        List<SalesOrderExchangeSelect> salesOrderExchangeSelects = salesOrderExchangeMapper.queryDeliverySalesOrders(salesOrderExchangeQuery);
        List<SalesOrderExchangeSelect> selects = new ArrayList<>();
        // 过滤全部已经换货的销售单，不能再换货

        if (!Objects.isNull(salesOrderExchangeSelects)) {
            for (SalesOrderExchangeSelect salesOrderExchangeSelect : salesOrderExchangeSelects) {
                if (salesOrderExchangeSelect.getExchangeQuantity() != null) {
                    if (salesOrderExchangeSelect.getExchangeQuantity().longValue() < salesOrderExchangeSelect.getDeliveredQuantity().longValue()) {
                        selects.add(salesOrderExchangeSelect);
                    }
                }else {
                    selects.add(salesOrderExchangeSelect);
                }

            }
        }
        return selects;
    }


    /*新增时获取新增对话框详情*/
    @Override
    public Map getAddInfo(String salesNumber) {
        Map info = salesOrderDeliveryMapper.getAddDeliverInfoBySalesNumber(salesNumber);
        List<SalesOrderExchangeDetails> detailsList = salesOrderExchangeMapper.getDetailsList(salesNumber);
        if (!Objects.isNull(detailsList)) {
            detailsList.forEach(
                    item -> {
                        item.setSubtotal(new BigDecimal(0.00));
                    }
            );
        }

        info.put("deliveryDetailsList_body", detailsList);
        return info;
    }

    /*
     * 新增换货单
     * */
    @Transactional  //事务
    @Override
    public int insertSalesOrderExchange(SalesOrderExchange salesOrderExchangeDetail) {

//        System.out.println("1>>>>>>>>>>>>>>>"+salesOrderExchangeDetail.getExchangeDetailsList());
        /*计算total--bug修复*/
        for (SalesOrderExchangeDetails salesOrderExchangeDetails : salesOrderExchangeDetail.getExchangeDetailsList()) {
            Long subtotal = salesOrderExchangeDetails.getPriceDifference().longValue() * salesOrderExchangeDetails.getExchangeAmount().longValue();
            salesOrderExchangeDetails.setTotal(new BigDecimal(subtotal));
        }
//        System.out.println("2>>>>>>>>>>>>>>>"+salesOrderExchangeDetail.getExchangeDetailsList());

        String userName = SecurityUtils.getUsername();
        /*换货单号生成*/
        String code = Constants.SALES_EXCHANGE_HH + DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date());
        String maxCode = salesOrderExchangeMapper.getMaxExchangeCode(code);
        String exchangecode = CodeUtils.getOneCode(code, maxCode, 3);
        /*将换货单号插入对象*/
        salesOrderExchangeDetail.setExchangeNumber(exchangecode);

        /*创建人*/
        salesOrderExchangeDetail.setCreateBy(userName);

        /*创建时间*/
        salesOrderExchangeDetail.setCreateTime(DateUtils.getNowDate());

        /*保存并审核*/
        if (salesOrderExchangeDetail.getStatus().trim().equals("1")) {
            salesOrderExchangeDetail.setAuditor(userName);
            salesOrderExchangeDetail.setAuditTime(DateUtils.getNowDate());
        }

        /*执行插入SQL*/
        salesOrderExchangeMapper.insertSalesOrderExchange(salesOrderExchangeDetail);

        for (SalesOrderExchangeDetails salesOrderExchangeDetail1 : salesOrderExchangeDetail.getExchangeDetailsList()) {
            salesOrderExchangeDetail1.setSalesNumber(salesOrderExchangeDetail.getSalesNumber());
            salesOrderExchangeDetail1.setExchangeNumber(exchangecode);
            salesOrderExchangeMapper.insertSalesOrderExchangeDetails(salesOrderExchangeDetail1);
        }
        return 1;
    }

    /*更新编辑换货单*/
    @Transactional  //事务
    @Override
    public int updateSalesOrderExchange(SalesOrderExchange salesOrderExchangeDetail) {
        System.out.println("1>>>>>>>>>>>>>>>" + salesOrderExchangeDetail.getExchangeDetailsList());
        /*计算total--bug修复*/
        for (SalesOrderExchangeDetails salesOrderExchangeDetails : salesOrderExchangeDetail.getExchangeDetailsList()) {
            Long subtotal = salesOrderExchangeDetails.getPriceDifference().longValue() * salesOrderExchangeDetails.getExchangeAmount().longValue();
            salesOrderExchangeDetails.setTotal(new BigDecimal(subtotal));
        }
        System.out.println("2>>>>>>>>>>>>>>>" + salesOrderExchangeDetail.getExchangeDetailsList());

        String userName = SecurityUtils.getUsername();

        /*保存并审核*/
        if (salesOrderExchangeDetail.getStatus().trim().equals("1")) {
            salesOrderExchangeDetail.setAuditor(userName);
            salesOrderExchangeDetail.setAuditTime(DateUtils.getNowDate());
        }

        salesOrderExchangeMapper.updateSalesOrderExchange(salesOrderExchangeDetail);
        salesOrderExchangeMapper.updateExchangeDetailsBatch(salesOrderExchangeDetail.getExchangeDetailsList());
        return 1;
    }


    /*
     * 审核
     * */
    @Transactional
    @Override
    public int approve(String exchangeNumber, String action) {
        Date date = DateUtils.getNowDate();
        String userName = SecurityUtils.getUsername();
        int approve = salesOrderExchangeMapper.approve(exchangeNumber, userName, date, action);
//        System.out.println(action);
        /* 增加交易记录 */
        if (action.trim().equals("2")) {
            addTransactionDetailsOrder(exchangeNumber);
        }
        return approve;
    }

    /*查看*/
    @Override
    public List<SalesOrderExchangeDetails> getExchangeDetailsByExchangeNumber(String exchangeNumber) {
        List<SalesOrderExchangeDetails> list = salesOrderExchangeMapper.getExchangeDetailsByExchangeNumber(exchangeNumber);
//        System.out.println(list);
        return list;
    }


    /*
     * 新增换货交易纪录   salesOrderTransactionDetails交易详情对象
     *
     * */
    private int addTransactionDetailsOrder(String exchangeNumber) {
//        System.out.println(3);
        List<SalesOrderExchangeDetails> salesOrderExchangeDetails = salesOrderExchangeMapper.getExchangeDetailsByExchangeNumber(exchangeNumber);
//        System.out.println("=================>" + salesOrderExchangeDetails);

        SalesOrderExchange orderExchange = salesOrderExchangeMapper.getExchange(exchangeNumber);

        /* 交易记录对象*/
        List<SalesOrderTransactionDetails> salesOrderTransactionDetails = new ArrayList<>();

        for (SalesOrderExchangeDetails salesOrderExchangeDetail : salesOrderExchangeDetails){
            SalesOrderTransactionDetails salesOrderTransactionDetail = new SalesOrderTransactionDetails();

            /*客户*/
            salesOrderTransactionDetail.setCustomer(orderExchange.getCustomer());
            System.out.println(orderExchange.getCustomer());

            /*交易单号*/
            salesOrderTransactionDetail.setTransactionNumber(salesOrderExchangeDetail.getExchangeNumber());

            /*物料编码*/
            salesOrderTransactionDetail.setMaterialNumber(salesOrderExchangeDetail.getProductNumber());

            /*交易数量*/
            salesOrderTransactionDetail.setQuantity(salesOrderExchangeDetail.getExchangeAmount());

            /*换补差价*/
            salesOrderTransactionDetail.setPrice(salesOrderExchangeDetail.getPriceDifference());

            /*总数*/
            salesOrderTransactionDetail.setAmount(salesOrderExchangeDetail.getTotal());

            /*备注*/
            salesOrderTransactionDetail.setRemark(salesOrderExchangeDetail.getRemark());

            salesOrderTransactionDetails.add(salesOrderTransactionDetail);
        }

        //添加交易详情
        salesOrderTransactionDetailsService.insertSalesOrderTransactionDetails(TransactionType.EXCHANGE, salesOrderTransactionDetails);

        /*汇总对象*/
        salesOrderCollectionService.insertSalesOrderCollection(orderExchange.getCustomer(), new BigDecimal(0), orderExchange.getOrderAmount(), DateUtils.getNowDate(), null);

        return 1;
    }
}
