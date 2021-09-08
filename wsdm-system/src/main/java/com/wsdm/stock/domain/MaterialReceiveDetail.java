package com.wsdm.stock.domain;

import com.wsdm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 物料采购订单详情对象 sk_material_receive_detail
 *
 * @author wsdm
 * @date 2021-01-12
 */
public class MaterialReceiveDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 物料领取单号
     */
    private String materialNumber;

    /**
     * 合同号
     */
    private String contractNumber;

    /**
     * 产品
     */
    private String produce;

    /**
     * 物料
     */
    private String material;

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
     * 可用数量
     */
    private Integer availableNum;

    /**
     * 待领取数量
     */
    private Integer unclaimedNum;

    /**
     * 数量
     */
    private Integer receiveNum;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public String getProduce() {
        return produce;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterial() {
        return material;
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

    public void setAvailableNum(Integer availableNum) {
        this.availableNum = availableNum;
    }

    public Integer getAvailableNum() {
        return availableNum;
    }

    public void setUnclaimedNum(Integer unclaimedNum) {
        this.unclaimedNum = unclaimedNum;
    }

    public Integer getUnclaimedNum() {
        return unclaimedNum;
    }

    public void setReceiveNum(Integer receiveNum) {
        this.receiveNum = receiveNum;
    }

    public Integer getReceiveNum() {
        return receiveNum;
    }

    public MaterialReceiveDetail(){}

    public MaterialReceiveDetail(String materialNumber){
        this.materialNumber = materialNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("materialNumber", getMaterialNumber())
                .append("contractNumber", getContractNumber())
                .append("produce", getProduce())
                .append("material", getMaterial())
                .append("type", getType())
                .append("quality", getQuality())
                .append("modulusToothNumber", getModulusToothNumber())
                .append("specifications", getSpecifications())
                .append("model", getModel())
                .append("availableNum", getAvailableNum())
                .append("unclaimedNum", getUnclaimedNum())
                .append("receiveNum", getReceiveNum())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
