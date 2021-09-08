package com.wsdm.pd.mapper;

import com.wsdm.pd.domain.PdContribution;

import java.util.List;

/**
 * 工绩单Mapper接口
 *
 * @author wsdm
 * @date 2021-06-16
 */
public interface PdContributionMapper
{
    /**
     * 查询工绩单
     *
     * @param id 工绩单ID
     * @return 工绩单
     */
    public PdContribution selectPdContributionById(Long id);

    /**
     * 查询工绩单列表
     *
     * @param pdContribution 工绩单
     * @return 工绩单集合
     */
    public List<PdContribution> selectPdContributionList(PdContribution pdContribution);

    /**
     * 新增工绩单
     *
     * @param pdContribution 工绩单
     * @return 结果
     */
    public int insertPdContribution(PdContribution pdContribution);

    /**
     * 修改工绩单
     *
     * @param pdContribution 工绩单
     * @return 结果
     */
    public int updatePdContribution(PdContribution pdContribution);

    /**
     * 删除工绩单
     *
     * @param id 工绩单ID
     * @return 结果
     */
    public int deletePdContributionById(Long id);

    /**
     * 批量删除工绩单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePdContributionByIds(Long[] ids);

    String getLatestCode();
}
