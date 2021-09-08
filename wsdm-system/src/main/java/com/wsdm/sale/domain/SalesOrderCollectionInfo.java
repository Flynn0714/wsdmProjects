package com.wsdm.sale.domain;

import java.util.List;

/**
 * 收款汇总对象 sales_order_collection
 *
 * @author wsdm
 * @date 2021-07-17
 */
public class SalesOrderCollectionInfo {

    private SalesOrderCollection salesOrderCollection;

    private List<SalesOrderAccountReceived> accountReceivedList;

    private List<SalesOrderTransactionDetails>  transactionDetailsList;

    public SalesOrderCollection getSalesOrderCollection() {
        return salesOrderCollection;
    }

    public void setSalesOrderCollection(SalesOrderCollection salesOrderCollection) {
        this.salesOrderCollection = salesOrderCollection;
    }

    public List<SalesOrderAccountReceived> getAccountReceivedList() {
        return accountReceivedList;
    }

    public void setAccountReceivedList(List<SalesOrderAccountReceived> accountReceivedList) {
        this.accountReceivedList = accountReceivedList;
    }

    public List<SalesOrderTransactionDetails> getTransactionDetailsList() {
        return transactionDetailsList;
    }

    public void setTransactionDetailsList(List<SalesOrderTransactionDetails> transactionDetailsList) {
        this.transactionDetailsList = transactionDetailsList;
    }
}
