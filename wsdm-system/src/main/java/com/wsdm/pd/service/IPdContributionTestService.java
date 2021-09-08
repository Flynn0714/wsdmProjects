package com.wsdm.pd.service;

import com.wsdm.pd.domain.PdContribution;
import com.wsdm.pd.domain.PdContributionDetail;
import com.wsdm.pd.domain.PdContributionTest;
import com.wsdm.pd.domain.PdContributionTestVO;

import java.util.List;

/**
 * 加工质检单Service接口
 *
 * @author wsdm
 * @date 2021-06-18
 */
public interface IPdContributionTestService
{
    /**
     * 查询加工质检单
     *
     * @param id 加工质检单ID
     * @return 加工质检单
     */
    public PdContributionTestVO selectPdContributionTestById(Long id);

    /**
     * 查询加工质检单列表
     *
     * @param pdContributionTest 加工质检单
     * @return 加工质检单集合
     */
    public List<PdContributionTest> selectPdContributionTestList(PdContributionTest pdContributionTest);

    /**
     * 修改加工质检单
     *
     * @param vo 加工质检单
     * @return 结果
     */
    public int updatePdContributionTest(PdContributionTestVO vo);

    /**
     * 根据加工单创建质检单，同时更新录入单质检单Id和质检单编号
     */
    void createPdContributionTest(PdContribution pdContribution, List<PdContributionDetail> pdContributionDetailList);

    int submit(Long id, String username);

    int pass(Long id, String username);

    int reject(Long id, String username);
}
