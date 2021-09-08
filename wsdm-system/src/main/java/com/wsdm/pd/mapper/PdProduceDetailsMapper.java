package com.wsdm.pd.mapper;

import com.wsdm.pd.domain.PdProduceDetails;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author wsdm
 * @date 2021-06-21
 */
public interface PdProduceDetailsMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public PdProduceDetails selectPdProduceDetailsById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param pdProduceDetails 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<PdProduceDetails> selectPdProduceDetailsList(PdProduceDetails pdProduceDetails);

    /**
     * 新增【请填写功能名称】
     *
     * @param pdProduceDetails 【请填写功能名称】
     * @return 结果
     */
    public int insertPdProduceDetails(PdProduceDetails pdProduceDetails);

    /**
     * 修改【请填写功能名称】
     *
     * @param pdProduceDetails 【请填写功能名称】
     * @return 结果
     */
    public int updatePdProduceDetails(PdProduceDetails pdProduceDetails);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deletePdProduceDetailsById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePdProduceDetailsByIds(Long[] ids);
}
