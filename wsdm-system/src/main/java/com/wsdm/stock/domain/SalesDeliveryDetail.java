package com.wsdm.stock.domain;

import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 发货订单详情对象 sk_sales_delivery_detail
 *
 * @author wsdm
 * @date 2021-01-12
 */
public class SalesDeliveryDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 发货单号
     */
    private String salesNumber;

    /**
     * 合同号
     */
    private String contractNumber;

    /**
     * 物料名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 材质
     */
    private String quality;

    /**
     * 模数齿数
     */
    private String modulusToothNumber;

    /**
     * 规格
     */
    private String specifications;

    /**
     * 型号
     */
    private String model;

    /**
     * 订单数量
     */
    private Long orderNum;

    /**
     * 已发货数量
     */
    private Long deliveredNum;

    /**
     * 发货数量
     */
    private Long deliveryNum;

    public SalesDeliveryDetail(){}

    public SalesDeliveryDetail(String salesNumber){
        this.salesNumber = salesNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getQuality() {
        return quality;
    }

    public void setModulusToothNumber(String modulusToothNumber) {
        this.modulusToothNumber = modulusToothNumber;
    }

    public String getModulusToothNumber() {
        return modulusToothNumber;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setDeliveredNum(Long deliveredNum) {
        this.deliveredNum = deliveredNum;
    }

    public Long getDeliveredNum() {
        return deliveredNum;
    }

    public void setDeliveryNum(Long deliveryNum) {
        this.deliveryNum = deliveryNum;
    }

    public Long getDeliveryNum() {
        return deliveryNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("salesNumber", getSalesNumber())
                .append("contractNumber", getContractNumber())
                .append("name", getName())
                .append("type", getType())
                .append("quality", getQuality())
                .append("modulusToothNumber", getModulusToothNumber())
                .append("specifications", getSpecifications())
                .append("model", getModel())
                .append("orderNum", getOrderNum())
                .append("deliveredNum", getDeliveredNum())
                .append("deliveryNum", getDeliveryNum())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
