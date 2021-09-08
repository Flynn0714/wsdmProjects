package com.wsdm.pd.mapper;

import com.wsdm.pd.domain.PdContributionTest;

import java.util.List;

/**
 * 加工质检单Mapper接口
 *
 * @author wsdm
 * @date 2021-06-18
 */
public interface PdContributionTestMapper {
    /**
     * 查询加工质检单
     *
     * @param id 加工质检单ID
     * @return 加工质检单
     */
    public PdContributionTest selectPdContributionTestById(Long id);

    /**
     * 查询加工质检单列表
     *
     * @param pdContributionTest 加工质检单
     * @return 加工质检单集合
     */
    public List<PdContributionTest> selectPdContributionTestList(PdContributionTest pdContributionTest);

    /**
     * 新增加工质检单
     *
     * @param pdContributionTest 加工质检单
     * @return 结果
     */
    public int insertPdContributionTest(PdContributionTest pdContributionTest);

    /**
     * 修改加工质检单
     *
     * @param pdContributionTest 加工质检单
     * @return 结果
     */
    public int updatePdContributionTest(PdContributionTest pdContributionTest);

    /**
     * 删除加工质检单
     *
     * @param id 加工质检单ID
     * @return 结果
     */
    public int deletePdContributionTestById(Long id);

    /**
     * 批量删除加工质检单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePdContributionTestByIds(Long[] ids);

    String getLatestCode();
}
