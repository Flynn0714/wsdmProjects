package com.wsdm.materiel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 自动采购信息
 *
 * @author GYJ
 * @version 1.0
 * @date 2021/8/27
 */
public class SalesOrderAutomaticallyGenerate implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    /**
     *销售单号
     */
    private String salesNumber;
    /**
     *物料编码
     */
    private String materialNumber;
    /**
     *物料类型 A-原料B-半成品C-成品
     */
    private String code;
    /**
     *系统生成数量
     */
    private String num;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     *使用此功能的用户
     */
    private String useUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUseUser() {
        return useUser;
    }

    public void setUseUser(String useUser) {
        this.useUser = useUser;
    }
}
