package com.wsdm.stock.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 物料收货订单信息对象 sk_material_receive
 *
 * @author wsdm
 * @date 2021-01-12
 */
public class MaterialReceiveRequest implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 领取单号
     */
    private String materialNumber;

    /**
     * 领取仓库
     */
    private String materialStock;

    /**
     * 领取仓库名称
     */
    private String materialStockName;

    /**
     * 生产计划
     */
    private String productionPlan;

    /**
     * 领取人
     */
    private String receivePerson;

    /**
     * 领取部门
     */
    private String receiveDept;

    /**
     * 领取时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receiveTimeStart;

    /**
     * 领取时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receiveTimeEnd;

    /**
     * 状态
     */
    private String status;

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getMaterialStock() {
        return materialStock;
    }

    public void setMaterialStock(String materialStock) {
        this.materialStock = materialStock;
    }

    public String getMaterialStockName() {
        return materialStockName;
    }

    public void setMaterialStockName(String materialStockName) {
        this.materialStockName = materialStockName;
    }

    public String getProductionPlan() {
        return productionPlan;
    }

    public void setProductionPlan(String productionPlan) {
        this.productionPlan = productionPlan;
    }

    public String getReceivePerson() {
        return receivePerson;
    }

    public void setReceivePerson(String receivePerson) {
        this.receivePerson = receivePerson;
    }

    public String getReceiveDept() {
        return receiveDept;
    }

    public void setReceiveDept(String receiveDept) {
        this.receiveDept = receiveDept;
    }

    public Date getReceiveTimeStart() {
        return receiveTimeStart;
    }

    public void setReceiveTimeStart(Date receiveTimeStart) {
        this.receiveTimeStart = receiveTimeStart;
    }

    public Date getReceiveTimeEnd() {
        return receiveTimeEnd;
    }

    public void setReceiveTimeEnd(Date receiveTimeEnd) {
        this.receiveTimeEnd = receiveTimeEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
