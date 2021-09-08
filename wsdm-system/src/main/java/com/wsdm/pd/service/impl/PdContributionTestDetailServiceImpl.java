package com.wsdm.pd.service.impl;

import com.wsdm.common.utils.DateUtils;
import com.wsdm.pd.domain.PdContributionTestDetail;
import com.wsdm.pd.mapper.PdContributionTestDetailMapper;
import com.wsdm.pd.service.IPdContributionTestDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工绩单质检明细Service业务层处理
 *
 * @author wsdm
 * @date 2021-06-18
 */
@Service
public class PdContributionTestDetailServiceImpl implements IPdContributionTestDetailService {
    @Autowired
    private PdContributionTestDetailMapper pdContributionTestDetailMapper;

    /**
     * 查询工绩单质检明细
     *
     * @param id 工绩单质检明细ID
     * @return 工绩单质检明细
     */
    @Override
    public PdContributionTestDetail selectPdContributionTestDetailById(Long id) {
        return pdContributionTestDetailMapper.selectPdContributionTestDetailById(id);
    }

    /**
     * 查询工绩单质检明细列表
     *
     * @param pdContributionTestDetail 工绩单质检明细
     * @return 工绩单质检明细
     */
    @Override
    public List<PdContributionTestDetail> selectPdContributionTestDetailList(PdContributionTestDetail pdContributionTestDetail) {
        return pdContributionTestDetailMapper.selectPdContributionTestDetailList(pdContributionTestDetail);
    }

    /**
     * 新增工绩单质检明细
     *
     * @param pdContributionTestDetail 工绩单质检明细
     * @return 结果
     */
    @Override
    public int insertPdContributionTestDetail(PdContributionTestDetail pdContributionTestDetail) {
        pdContributionTestDetail.setCreateTime(DateUtils.getNowDate());
        return pdContributionTestDetailMapper.insertPdContributionTestDetail(pdContributionTestDetail);
    }

    /**
     * 修改工绩单质检明细
     *
     * @param pdContributionTestDetail 工绩单质检明细
     * @return 结果
     */
    @Override
    public int updatePdContributionTestDetail(PdContributionTestDetail pdContributionTestDetail) {
        pdContributionTestDetail.setUpdateTime(DateUtils.getNowDate());
        return pdContributionTestDetailMapper.updatePdContributionTestDetail(pdContributionTestDetail);
    }

    /**
     * 批量删除工绩单质检明细
     *
     * @param ids 需要删除的工绩单质检明细ID
     * @return 结果
     */
    @Override
    public int deletePdContributionTestDetailByIds(Long[] ids) {
        return pdContributionTestDetailMapper.deletePdContributionTestDetailByIds(ids);
    }

    /**
     * 删除工绩单质检明细信息
     *
     * @param id 工绩单质检明细ID
     * @return 结果
     */
    @Override
    public int deletePdContributionTestDetailById(Long id) {
        return pdContributionTestDetailMapper.deletePdContributionTestDetailById(id);
    }
}
