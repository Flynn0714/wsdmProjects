package com.wsdm.produce.mapper;

import com.wsdm.produce.domain.SemiProduceManage;

import java.util.List;
import java.util.Map;

/**
 * 半成品管理Mapper接口
 *
 * @author wsdm
 * @date 2020-11-06
 */
public interface SemiProduceManageMapper {
    /**
     * 查询半成品管理
     *
     * @param id 半成品管理ID
     * @return 半成品管理
     */
    public SemiProduceManage selectSemiProduceManageById(Long id);

    /**
     * 查询半成品管理列表
     *
     * @param semiProduceManage 半成品管理
     * @return 半成品管理集合
     */
    public List<SemiProduceManage> selectSemiProduceManageList(SemiProduceManage semiProduceManage);

    /**
     * 查询半成品管理
     *
     * @param produceNumber 产品编号
     * @return 半成品管理
     */
    public List<SemiProduceManage> getSemiProduceByProduceNumber(String produceNumber);

    /**
     * 新增半成品管理
     *
     * @param semiProduceManage 半成品管理
     * @return 结果
     */
    public int insertSemiProduceManage(SemiProduceManage semiProduceManage);

    /**
     * 批量新增半成品管理
     *
     * @param list 半成品管理
     * @return 结果
     */
    public int insertSemiProduceManageBatch(List<SemiProduceManage> list);

    /**
     * 修改半成品管理
     *
     * @param semiProduceManage 半成品管理
     * @return 结果
     */
    public int updateSemiProduceManage(SemiProduceManage semiProduceManage);

    /**
     * 删除半成品管理
     *
     * @param id 半成品管理ID
     * @return 结果
     */
    public int deleteSemiProduceManageById(Long id);

    /**
     * 批量删除半成品管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSemiProduceManageByIds(Long[] ids);

    SemiProduceManage getSemiInfoBySemiNumber(String semiProduceNumber);

    public List<SemiProduceManage> semiInfoByListBySemiNumber(List<String> list);
}
