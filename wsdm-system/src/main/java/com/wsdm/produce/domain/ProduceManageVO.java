package com.wsdm.produce.domain;

import java.util.List;

/**
 * @param
 * @author wangr
 * @date 2020/11/9 19:24
 */
public class ProduceManageVO extends ProduceManage {

    private List<SemiProduceManage> semiProduceManages;

    private List<FittingsMange> fittingsManges;

    public List<SemiProduceManage> getSemiProduceManages() {
        return semiProduceManages;
    }

    public void setSemiProduceManages(List<SemiProduceManage> semiProduceManages) {
        this.semiProduceManages = semiProduceManages;
    }

    public List<FittingsMange> getFittingsManges() {
        return fittingsManges;
    }

    public void setFittingsManges(List<FittingsMange> fittingsManges) {
        this.fittingsManges = fittingsManges;
    }
}
