package com.wsdm.produce.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.produce.domain.ProduceManage;
import com.wsdm.produce.domain.ProduceManageVO;

import java.util.List;

/**
 * 产品管理Service接口
 *
 * @author wsdm
 * @date 2020-11-06
 */
public interface IProduceManageService {
    /**
     * 查询产品管理
     *
     * @param id 产品管理ID
     * @return 产品管理
     */
    public ProduceManage selectProduceManageById(Long id);

    /**
     * 查询产品管理
     *
     * @param id 产品管理ID
     * @return 产品管理
     */
    public ProduceManageVO selectProduceManageVoById(Long id);

    /**
     * 查询产品管理列表
     *
     * @param produceManage 产品管理
     * @return 产品管理集合
     */
    public List<ProduceManage> selectProduceManageList(ProduceManage produceManage);

    public AjaxResult introduceProduce(ProduceManage produceManage);

    /**
     * 新增产品管理
     *
     * @param produceManage 产品管理
     * @return 结果
     */
    public AjaxResult insertProduceManage(ProduceManageVO produceManage);

    /**
     * 修改产品管理
     *
     * @param produceManage 产品管理
     * @return 结果
     */
    public AjaxResult updateProduceManage(ProduceManageVO produceManage);

    /**
     * 批量删除产品管理
     *
     * @param ids 需要删除的产品管理ID
     * @return 结果
     */
    public int deleteProduceManageByIds(Long[] ids);

    /**
     * 删除产品管理信息
     *
     * @param id 产品管理ID
     * @return 结果
     */
    public int deleteProduceManageById(Long id);
}
