package com.wsdm.produce.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.produce.domain.SemiProduceManage;

import java.util.List;

/**
 * 半成品管理Service接口
 *
 * @author wsdm
 * @date 2020-11-06
 */
public interface ISemiProduceManageService {
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
     * 新增半成品管理
     *
     * @param semiProduceManage 半成品管理
     * @return 结果
     */
    public AjaxResult insertSemiProduceManage(SemiProduceManage semiProduceManage);

    /**
     * 修改半成品管理
     *
     * @param semiProduceManage 半成品管理
     * @return 结果
     */
    public AjaxResult updateSemiProduceManage(SemiProduceManage semiProduceManage);

    /**
     * 批量删除半成品管理
     *
     * @param ids 需要删除的半成品管理ID
     * @return 结果
     */
    public int deleteSemiProduceManageByIds(Long[] ids);

    /**
     * 删除半成品管理信息
     *
     * @param id 半成品管理ID
     * @return 结果
     */
    public int deleteSemiProduceManageById(Long id);
}
