package com.wsdm.materiel.mapper;

import com.wsdm.materiel.domain.QualityInspectionDetail;

import java.util.List;

;

/**
 * 物料收货质检单详情Mapper接口
 *
 * @author wanglei
 * @date 2021-06-20
 */
public interface QualityInspectionDetailMapper {
    /**
     * 查询物料收货质检单详情
     *
     * @param id 物料收货质检单详情ID
     * @return 物料收货质检单详情
     */
    public QualityInspectionDetail selectQualityInspectionDetailById(Long id);

    /**
     * 查询物料收货质检单详情列表
     *
     * @param qualityInspectionDetail 物料收货质检单详情
     * @return 物料收货质检单详情集合
     */
    public List<QualityInspectionDetail> selectQualityInspectionDetailList(QualityInspectionDetail qualityInspectionDetail);

    /**
     * 新增物料收货质检单详情
     *
     * @param qualityInspectionDetail 物料收货质检单详情
     * @return 结果
     */
    public int insertQualityInspectionDetail(QualityInspectionDetail qualityInspectionDetail);

    /**
     * 修改物料收货质检单详情
     *
     * @param qualityInspectionDetail 物料收货质检单详情
     * @return 结果
     */
    public int updateQualityInspectionDetail(QualityInspectionDetail qualityInspectionDetail);

    /**
     * 删除物料收货质检单详情
     *
     * @param id 物料收货质检单详情ID
     * @return 结果
     */
    public int deleteQualityInspectionDetailById(Long id);

    /**
     * 批量删除物料收货质检单详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQualityInspectionDetailByIds(Long[] ids);

    List<QualityInspectionDetail> selectQualityInspectionDetailByCheckNumber(String checkNumber);

    int deleteQualityInspectionDetailByCheckNumber(String checkNumber);

    int insertQualityInspectionDetailBatch(List<QualityInspectionDetail> details);
}
