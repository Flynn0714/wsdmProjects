package com.wsdm.pd.mapper;

import com.wsdm.pd.domain.PdSemiProduceManage;

import java.util.List;

/**
 * 半成品管理Mapper接口
 *
 * @author wsdm
 * @date 2021-06-21
 */
public interface PdSemiProduceManageMapper {
    /**
     * 查询半成品管理
     *
     * @param id 半成品管理ID
     * @return 半成品管理
     */
    public PdSemiProduceManage selectPdSemiProduceManageById(Long id);

    /**
     * 查询半成品管理列表
     *
     * @param pdSemiProduceManage 半成品管理
     * @return 半成品管理集合
     */
    public List<PdSemiProduceManage> selectPdSemiProduceManageList(PdSemiProduceManage pdSemiProduceManage);

    /**
     * 新增半成品管理
     *
     * @param pdSemiProduceManage 半成品管理
     * @return 结果
     */
    public int insertPdSemiProduceManage(PdSemiProduceManage pdSemiProduceManage);

    /**
     * 修改半成品管理
     *
     * @param pdSemiProduceManage 半成品管理
     * @return 结果
     */
    public int updatePdSemiProduceManage(PdSemiProduceManage pdSemiProduceManage);

    /**
     * 删除半成品管理
     *
     * @param id 半成品管理ID
     * @return 结果
     */
    public int deletePdSemiProduceManageById(Long id);

    /**
     * 批量删除半成品管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePdSemiProduceManageByIds(Long[] ids);

    /**
     * 通过半成品编码查询是否存在该半成品
     * @param pdSemiProduceNumber
     * @return
     */
    public PdSemiProduceManage selectPdSemiByNumber(String pdSemiProduceNumber);
}
