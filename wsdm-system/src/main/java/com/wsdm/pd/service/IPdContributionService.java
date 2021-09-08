package com.wsdm.pd.service;

import com.wsdm.pd.domain.PdContribution;
import com.wsdm.pd.domain.PdContributionVO;

import java.util.List;

/**
 * 工绩单Service接口
 *
 * @author wsdm
 * @date 2021-05-11
 */
public interface IPdContributionService {
    /**
     * 查询工绩单
     *
     * @param id 工绩单ID
     * @return 工绩单
     */
    public PdContributionVO selectPdContributionById(Long id);

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
     * @param vo 工绩单
     * @return 结果
     */
    public int insertPdContribution(PdContributionVO vo);

    /**
     * 修改工绩单
     *
     * @param vo 工绩单
     * @return 结果
     */
    public int updatePdContribution(PdContributionVO vo);

    int deleteSafe(Long id, String userId);

    int recover(Long id, String userId);

    Long syncToContributionQuantities(Long testId, String username);

    int submit(Long id, String username);

    int pass(Long id, String username);

    int reject(Long id, String username);

    void setToSubmit(Long id, String username);
}
