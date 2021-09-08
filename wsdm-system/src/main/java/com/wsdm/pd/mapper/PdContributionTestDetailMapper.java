package com.wsdm.pd.mapper;

import com.wsdm.pd.domain.PdContributionTestDetail;

import java.util.List;

/**
 * 工绩单质检明细Mapper接口
 *
 * @author wsdm
 * @date 2021-06-18
 */
public interface PdContributionTestDetailMapper {
    /**
     * 查询工绩单质检明细
     *
     * @param id 工绩单质检明细ID
     * @return 工绩单质检明细
     */
    public PdContributionTestDetail selectPdContributionTestDetailById(Long id);

    /**
     * 查询工绩单质检明细列表
     *
     * @param pdContributionTestDetail 工绩单质检明细
     * @return 工绩单质检明细集合
     */
    public List<PdContributionTestDetail> selectPdContributionTestDetailList(PdContributionTestDetail pdContributionTestDetail);

    /**
     * 新增工绩单质检明细
     *
     * @param pdContributionTestDetail 工绩单质检明细
     * @return 结果
     */
    public int insertPdContributionTestDetail(PdContributionTestDetail pdContributionTestDetail);

    /**
     * 修改工绩单质检明细
     *
     * @param pdContributionTestDetail 工绩单质检明细
     * @return 结果
     */
    public int updatePdContributionTestDetail(PdContributionTestDetail pdContributionTestDetail);

    /**
     * 删除工绩单质检明细
     *
     * @param id 工绩单质检明细ID
     * @return 结果
     */
    public int deletePdContributionTestDetailById(Long id);

    /**
     * 批量删除工绩单质检明细
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePdContributionTestDetailByIds(Long[] ids);
}
