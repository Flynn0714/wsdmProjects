package com.wsdm.pd.mapper;

import com.wsdm.pd.domain.PdProduceAnnex;

import java.util.List;

/**
 * 产品附件Mapper接口
 *
 * @author wsdm
 * @date 2021-06-21
 */
public interface PdProduceAnnexMapper {
    /**
     * 查询产品附件
     *
     * @param id 产品附件ID
     * @return 产品附件
     */
    public PdProduceAnnex selectPdProduceAnnexById(Long id);

    /**
     * 查询产品附件列表
     *
     * @param pdProduceAnnex 产品附件
     * @return 产品附件集合
     */
    public List<PdProduceAnnex> selectPdProduceAnnexList(PdProduceAnnex pdProduceAnnex);

    /**
     * 新增产品附件
     *
     * @param pdProduceAnnex 产品附件
     * @return 结果
     */
    public int insertPdProduceAnnex(PdProduceAnnex pdProduceAnnex);

    /**
     * 修改产品附件
     *
     * @param pdProduceAnnex 产品附件
     * @return 结果
     */
    public int updatePdProduceAnnex(PdProduceAnnex pdProduceAnnex);

    /**
     * 删除产品附件
     *
     * @param id 产品附件ID
     * @return 结果
     */
    public int deletePdProduceAnnexById(Long id);

    /**
     * 批量删除产品附件
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePdProduceAnnexByIds(Long[] ids);
}
