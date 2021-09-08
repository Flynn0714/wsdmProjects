package com.wsdm.pd.service.impl;

import com.wsdm.common.utils.DateUtils;
import com.wsdm.pd.domain.PdContributionDetail;
import com.wsdm.pd.mapper.PdContributionDetailMapper;
import com.wsdm.pd.service.IPdContributionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工绩单明细Service业务层处理
 *
 * @author wsdm
 * @date 2021-05-11
 */
@Service
public class PdContributionDetailServiceImpl implements IPdContributionDetailService
{
    @Autowired
    private PdContributionDetailMapper pdContributionDetailMapper;

    /**
     * 查询工绩单明细
     *
     * @param id 工绩单明细ID
     * @return 工绩单明细
     */
    @Override
    public PdContributionDetail selectPdContributionDetailById(Long id)
    {
        return pdContributionDetailMapper.selectPdContributionDetailById(id);
    }

    /**
     * 查询工绩单明细列表
     *
     * @param pdContributionDetail 工绩单明细
     * @return 工绩单明细
     */
    @Override
    public List<PdContributionDetail> selectPdContributionDetailList(PdContributionDetail pdContributionDetail)
    {
        return pdContributionDetailMapper.selectPdContributionDetailList(pdContributionDetail);
    }

    /**
     * 新增工绩单明细
     *
     * @param pdContributionDetail 工绩单明细
     * @return 结果
     */
    @Override
    public int insertPdContributionDetail(PdContributionDetail pdContributionDetail)
    {
        pdContributionDetail.setCreateTime(DateUtils.getNowDate());
        return pdContributionDetailMapper.insertPdContributionDetail(pdContributionDetail);
    }

    /**
     * 修改工绩单明细
     *
     * @param pdContributionDetail 工绩单明细
     * @return 结果
     */
    @Override
    public int updatePdContributionDetail(PdContributionDetail pdContributionDetail)
    {
        pdContributionDetail.setUpdateTime(DateUtils.getNowDate());
        return pdContributionDetailMapper.updatePdContributionDetail(pdContributionDetail);
    }

    /**
     * 批量删除工绩单明细
     *
     * @param ids 需要删除的工绩单明细ID
     * @return 结果
     */
    @Override
    public int deletePdContributionDetailByIds(Long[] ids)
    {
        return pdContributionDetailMapper.deletePdContributionDetailByIds(ids);
    }

    /**
     * 删除工绩单明细信息
     *
     * @param id 工绩单明细ID
     * @return 结果
     */
    @Override
    public int deletePdContributionDetailById(Long id)
    {
        return pdContributionDetailMapper.deletePdContributionDetailById(id);
    }
}
