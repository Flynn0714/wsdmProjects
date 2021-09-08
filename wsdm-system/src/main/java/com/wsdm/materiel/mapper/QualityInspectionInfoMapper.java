package com.wsdm.materiel.mapper;

import com.wsdm.materiel.domain.QualityInspectionInfo;
import com.wsdm.materiel.domain.QualityInspectionInfoVo;

import java.util.List;


/**
 * 物料收货质检单信息Mapper接口
 *
 * @author wanglei
 * @date 2021-06-20
 */
public interface QualityInspectionInfoMapper {
    /**
     * 查询物料收货质检单信息
     *
     * @param id 物料收货质检单信息ID
     * @return 物料收货质检单信息
     */
    public QualityInspectionInfoVo selectQualityInspectionInfoById(Long id);

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
    public int insertQualityInspectionInfo(QualityInspectionInfoVo qualityInspectionInfo);

    /**
     * 修改物料收货质检单信息
     *
     * @param qualityInspectionInfo 物料收货质检单信息
     * @return 结果
     */
    public int updateQualityInspectionInfo(QualityInspectionInfoVo qualityInspectionInfo);

    /**
     * 删除物料收货质检单信息
     *
     * @param id 物料收货质检单信息ID
     * @return 结果
     */
    public int deleteQualityInspectionInfoById(Long id);

    /**
     * 批量删除物料收货质检单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQualityInspectionInfoByIds(Long[] ids);

    /**
     * 查询最大质检单号
     *
     * @param code 物料收货单号前缀
     * @return 结果
     */
    public String getMaxCheckCode(String code);

    int updateStatusById(QualityInspectionInfo info);

    int updateApproveStatus(QualityInspectionInfo info);

    QualityInspectionInfoVo selectQualityInspectionInfoByCheckNumber(String checkNumber);
}
