package com.wsdm.pd.domain;

/**
 * Created by zhangruzheng on 2021/6/22.
 */
public class PdSemiProduceManageVO {
    private PdSemiProduceManage pdSemiProduceManage;
    private PdProduceDetails pdProduceDetails;

	public PdSemiProduceManageVO() {
    }
	
    public PdSemiProduceManageVO(PdSemiProduceManage pdSemiProduceManage, PdProduceDetails pdProduceDetails) {
        this.pdSemiProduceManage = pdSemiProduceManage;
        this.pdProduceDetails = pdProduceDetails;
    }

    public PdSemiProduceManage getPdSemiProduceManage() {
        return pdSemiProduceManage;
    }

    public void setPdSemiProduceManage(PdSemiProduceManage pdSemiProduceManage) {
        this.pdSemiProduceManage = pdSemiProduceManage;
    }

    public PdProduceDetails getPdProduceDetails() {
        return pdProduceDetails;
    }

    public void setPdProduceDetails(PdProduceDetails pdProduceDetails) {
        this.pdProduceDetails = pdProduceDetails;
    }
}
