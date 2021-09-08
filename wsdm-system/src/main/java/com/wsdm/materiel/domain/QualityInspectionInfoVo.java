package com.wsdm.materiel.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/*
 * 物料收货质检单信息对象 wl_receive_quality_inspection_info
 *
 * @author wanglei
 * @date 2021-06-20
 */
public class QualityInspectionInfoVo extends QualityInspectionInfo {
    private static final long serialVersionUID = 1L;

    List<QualityInspectionDetail> qualityInspectionDetails;

    /**
     * 操作类型 1-保存 2-保存提审
     */
    private String optType;

    /**
     * 质检日期查询开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkStartDate;

    /**
     * 质检日期查询结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkEndDate;

    public List<QualityInspectionDetail> getQualityInspectionDetails() {
        return qualityInspectionDetails;
    }

    public void setQualityInspectionDetails(List<QualityInspectionDetail> qualityInspectionDetails) {
        this.qualityInspectionDetails = qualityInspectionDetails;
    }

    public Date getCheckStartDate() {
        return checkStartDate;
    }

    public void setCheckStartDate(Date checkStartDate) {
        this.checkStartDate = checkStartDate;
    }

    public Date getCheckEndDate() {
        return checkEndDate;
    }

    public void setCheckEndDate(Date checkEndDate) {
        this.checkEndDate = checkEndDate;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }
}


