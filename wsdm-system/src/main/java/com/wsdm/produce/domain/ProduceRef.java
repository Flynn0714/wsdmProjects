package com.wsdm.produce.domain;

import java.io.Serializable;

/**
 * 产品资料关联
 *
 * @author wangr
 * @version 1.0
 * @date 2020/11/9 19:32
 */
public class ProduceRef implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String type;

    private String produce;

    private String ref;

    private int num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ProduceRef(){}

    public ProduceRef(String type, String produce, String ref, int num) {
        this.type = type;
        this.produce = produce;
        this.ref = ref;
        this.num = num;
    }
}
