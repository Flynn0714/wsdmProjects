package com.wsdm.produce.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.produce.domain.FittingsMange;

import java.util.List;

/**
 * 配件管理Service接口
 *
 * @author wsdm
 * @date 2020-11-06
 */
public interface IFittingsMangeService {
    /**
     * 查询配件管理
     *
     * @param id 配件管理ID
     * @return 配件管理
     */
    public FittingsMange selectFittingsMangeById(Long id);

    /**
     * 查询配件管理列表
     *
     * @param fittingsMange 配件管理
     * @return 配件管理集合
     */
    public List<FittingsMange> selectFittingsMangeList(FittingsMange fittingsMange);

    /**
     * 新增配件管理
     *
     * @param fittingsMange 配件管理
     * @return 结果
     */
    public AjaxResult insertFittingsMange(FittingsMange fittingsMange);

    /**
     * 修改配件管理
     *
     * @param fittingsMange 配件管理
     * @return 结果
     */
    public AjaxResult updateFittingsMange(FittingsMange fittingsMange);

    /**
     * 批量删除配件管理
     *
     * @param ids 需要删除的配件管理ID
     * @return 结果
     */
    public int deleteFittingsMangeByIds(Long[] ids);

    /**
     * 删除配件管理信息
     *
     * @param id 配件管理ID
     * @return 结果
     */
    public int deleteFittingsMangeById(Long id);
}
