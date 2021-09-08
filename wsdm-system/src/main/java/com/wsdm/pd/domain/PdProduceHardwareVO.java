package com.wsdm.pd.domain;

/**
 * Created by zhangruzheng on 2021/6/22.
 */
public class PdProduceHardwareVO {
    private PdProduceHardware pdProduceHardware;
    private PdProduceDetails pdProduceDetails;

	public PdProduceHardwareVO() {
    }
	
    public PdProduceHardwareVO(PdProduceHardware pdProduceHardware,PdProduceDetails pdProduceDetails) {
        this.pdProduceHardware = pdProduceHardware;
        this.pdProduceDetails = pdProduceDetails;
    }

    public PdProduceHardware getPdProduceHardware() {
        return pdProduceHardware;
    }

    public void setPdProduceHardware(PdProduceHardware pdProduceHardware) {
        this.pdProduceHardware = pdProduceHardware;
    }

    public PdProduceDetails getPdProduceDetails() {
        return pdProduceDetails;
    }

    public void setPdProduceDetails(PdProduceDetails pdProduceDetails) {
        this.pdProduceDetails = pdProduceDetails;
    }
}
