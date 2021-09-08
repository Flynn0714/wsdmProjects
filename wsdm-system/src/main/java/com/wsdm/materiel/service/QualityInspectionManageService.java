package com.wsdm.materiel.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.materiel.domain.QualityInspectionInfo;
import com.wsdm.materiel.domain.QualityInspectionInfoVo;

import java.util.List;

/**
 * 物料收货质检单信息Service接口
 *
 * @author wanglei
 * @date 2021-06-20
 */
public interface QualityInspectionManageService {
    /**
     * 查询物料收货质检单信息
     *
     * @param id 物料收货质检单信息ID
     * @return 物料收货质检单信息
     */
    public QualityInspectionInfo selectQualityInspectionInfoById(Long id);

    /**
     * 查询物料收货质检单信息列表
     *
     * @param qualityInspectionInfo 物料收货质检单信息
     * @return 物料收货质检单信息集合
     */
    public List<QualityInspectionInfo> selectQualityInspectionInfoList(QualityInspectionInfoVo qualityInspectionInfo);

    /**
     * 新增物料收货质检单信息
     *
     * @param qualityInspectionInfo 物料收货质检单信息
     * @return 结果
     */
    public AjaxResult insertQualityInspectionInfo(QualityInspectionInfoVo qualityInspectionInfo);

    /**
     * 修改物料收货质检单信息
     *
     * @param qualityInspectionInfo 物料收货质检单信息
     * @return 结果
     */
    public AjaxResult updateQualityInspectionInfo(QualityInspectionInfoVo qualityInspectionInfo);

    /**
     * 批量删除物料收货质检单信息
     *
     * @param ids 需要删除的物料收货质检单信息ID
     * @return 结果
     */
    public int deleteQualityInspectionInfoByIds(Long[] ids);

    /**
     * 删除物料收货质检单信息信息
     *
     * @param id 物料收货质检单信息ID
     * @return 结果
     */
    public int deleteQualityInspectionInfoById(Long id);

    public String getCheckNumber();

    AjaxResult updateApproveStatus(Long id, String action, String approvedType);

    AjaxResult submitApprove(Long id);

    QualityInspectionInfo selectQualityInspectionInfoByCheckNumber(String checkNumber);
}
