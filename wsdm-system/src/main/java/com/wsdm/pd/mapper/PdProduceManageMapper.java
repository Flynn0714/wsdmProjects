package com.wsdm.pd.mapper;

import com.wsdm.pd.domain.PdProduceManage;

import java.util.List;

/**
 * 产品管理Mapper接口
 *
 * @author wsdm
 * @date 2021-06-21
 */
public interface PdProduceManageMapper {
    /**
     * 查询产品管理
     *
     * @param id 产品管理ID
     * @return 产品管理
     */
    public PdProduceManage selectPdProduceManageById(Long id);

    /**
     * 查询产品管理列表
     *
     * @param pdProduceManage 产品管理
     * @return 产品管理集合
     */
    public List<PdProduceManage> selectPdProduceManageList(PdProduceManage pdProduceManage);

    /**
     * 新增产品管理
     *
     * @param pdProduceManage 产品管理
     * @return 结果
     */
    public int insertPdProduceManage(PdProduceManage pdProduceManage);

    /**
     * 修改产品管理
     *
     * @param pdProduceManage 产品管理
     * @return 结果
     */
    public int updatePdProduceManage(PdProduceManage pdProduceManage);

    /**
     * 删除产品管理
     *
     * @param id 产品管理ID
     * @return 结果
     */
    public int deletePdProduceManageById(Long id);

    /**
     * 批量删除产品管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePdProduceManageByIds(Long[] ids);
}
