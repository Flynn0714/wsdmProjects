package com.wsdm.pd.service;

import com.wsdm.pd.domain.PdProduceManage;
import com.wsdm.pd.domain.PdProduceManageVO;

import java.util.List;

/**
 * 产品管理Service接口
 *
 * @author wsdm
 * @date 2021-06-21
 */
public interface IPdProduceManageService {
    /**
     * 查询产品管理
     *
     * @param id 产品管理ID
     * @return 产品管理
     */
    public PdProduceManageVO selectPdProduceManageById(Long id);

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
     * @param pdProduceManageVO 产品管理
     * @return 结果
     */
    public PdProduceManageVO insertPdProduceManage(PdProduceManageVO pdProduceManageVO);

    /**
     * 修改产品管理
     *
     * @param pdProduceManageVO 产品管理
     * @return 结果
     */
    public int updatePdProduceManage(PdProduceManageVO pdProduceManageVO);

    /**
     * 批量删除产品管理
     *
     * @param ids 需要删除的产品管理ID
     * @return 结果
     */
    public int deletePdProduceManageByIds(Long[] ids);

    /**
     * 删除产品管理信息
     *
     * @param id 产品管理ID
     * @return 结果
     */
    public int deletePdProduceManageById(Long id);

    int deleteSafe(Long id, String userId);

    int recover(Long id, String userId);
}
