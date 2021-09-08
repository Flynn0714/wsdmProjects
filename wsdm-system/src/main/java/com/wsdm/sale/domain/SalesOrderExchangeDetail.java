package com.wsdm.sale.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/*
* 单纯的换货物料详情
* @author 符方龙
* @Date 2021年7月16日17:35:40
* */
public class SalesOrderExchangeDetail {
//    /*
//     * 编号
//     * */
//    private Long id;

    /*
     * 产品类型  -- 存放的是产品类型编号
     * */
    private String productType;

    /*
     * 产品编号
     * */
    private String productNumber;

    /*
     * 产品名称
     * */
    private String productName;

    /*
     * 换货数量
     * */
   // @NotBlank(message = "换货数量不能为空")
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal exchangeQuantity = new BigDecimal(0.0);

    /*
     * 换补单价--差价
     * */
 //   @NotBlank(message = "换补单价不能为空")
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal priceDifference = new BigDecimal(0.00);

    /*
     * 小计
     * */
    @JsonSerialize(using= ToStringSerializer.class)
    private BigDecimal total = new BigDecimal(0.00);

    public SalesOrderExchangeDetail() {
    }

    public SalesOrderExchangeDetail(String productType, String productNumber, String productName, @NotBlank(message = "换货数量不能为空") BigDecimal exchangeQuantity, @NotBlank(message = "换补单价不能为空") BigDecimal priceDifference, BigDecimal total) {
        this.productType = productType;
        this.productNumber = productNumber;
        this.productName = productName;
        this.exchangeQuantity = exchangeQuantity;
        this.priceDifference = priceDifference;
        this.total = total;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getExchangeAmount() {
        return exchangeQuantity;
    }


    public void setExchangeAmount(BigDecimal exchangeQuantity) {
        this.exchangeQuantity = exchangeQuantity;
    }

    public BigDecimal getPriceDifference() {
        return priceDifference;
    }

    public void setPriceDifference(BigDecimal priceDifference) {
        this.priceDifference = priceDifference;
    }

    public BigDecimal getSubtotal() {
        return total;
    }

    public void setSubtotal(BigDecimal total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "SalesOrderExchangeDetail{" +
                "productType='" + productType + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", productName='" + productName + '\'' +
                ", exchangeQuantity=" + exchangeQuantity +
                ", priceDifference=" + priceDifference +
                ", total=" + total +
                '}';
    }
}
