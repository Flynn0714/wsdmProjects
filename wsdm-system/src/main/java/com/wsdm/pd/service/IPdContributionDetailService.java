package com.wsdm.pd.service;

import com.wsdm.pd.domain.PdContributionDetail;

import java.util.List;

/**
 * 工绩单明细Service接口
 *
 * @author wsdm
 * @date 2021-05-11
 */
public interface IPdContributionDetailService
{
    /**
     * 查询工绩单明细
     *
     * @param id 工绩单明细ID
     * @return 工绩单明细
     */
    public PdContributionDetail selectPdContributionDetailById(Long id);

    /**
     * 查询工绩单明细列表
     *
     * @param pdContributionDetail 工绩单明细
     * @return 工绩单明细集合
     */
    public List<PdContributionDetail> selectPdContributionDetailList(PdContributionDetail pdContributionDetail);

    /**
     * 新增工绩单明细
     *
     * @param pdContributionDetail 工绩单明细
     * @return 结果
     */
    public int insertPdContributionDetail(PdContributionDetail pdContributionDetail);

    /**
     * 修改工绩单明细
     *
     * @param pdContributionDetail 工绩单明细
     * @return 结果
     */
    public int updatePdContributionDetail(PdContributionDetail pdContributionDetail);

    /**
     * 批量删除工绩单明细
     *
     * @param ids 需要删除的工绩单明细ID
     * @return 结果
     */
    public int deletePdContributionDetailByIds(Long[] ids);

    /**
     * 删除工绩单明细信息
     *
     * @param id 工绩单明细ID
     * @return 结果
     */
    public int deletePdContributionDetailById(Long id);
}
