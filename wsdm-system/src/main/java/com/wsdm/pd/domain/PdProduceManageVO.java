package com.wsdm.pd.domain;

import java.util.List;

/**
 * Created by zhangruzheng on 2021/6/22.
 */
public class PdProduceManageVO {
    private PdProduceManage pdProduceManage;
    private List<PdProduceAnnex> pdProduceAnnexList;
    private List<PdSemiProduceManageVO> pdSemiProduceManageList;
    private List<PdProduceHardwareVO> pdProduceHardwareList;

    public PdProduceManage getPdProduceManage() {
        return pdProduceManage;
    }

    public void setPdProduceManage(PdProduceManage pdProduceManage) {
        this.pdProduceManage = pdProduceManage;
    }

    public List<PdProduceAnnex> getPdProduceAnnexList() {
        return pdProduceAnnexList;
    }

    public void setPdProduceAnnexList(List<PdProduceAnnex> pdProduceAnnexList) {
        this.pdProduceAnnexList = pdProduceAnnexList;
    }

    public List<PdSemiProduceManageVO> getPdSemiProduceManageList() {
        return pdSemiProduceManageList;
    }

    public void setPdSemiProduceManageList(List<PdSemiProduceManageVO> pdSemiProduceManageList) {
        this.pdSemiProduceManageList = pdSemiProduceManageList;
    }

    public List<PdProduceHardwareVO> getPdProduceHardwareList() {
        return pdProduceHardwareList;
    }

    public void setPdProduceHardwareList(List<PdProduceHardwareVO> pdProduceHardwareList) {
        this.pdProduceHardwareList = pdProduceHardwareList;
    }
}
