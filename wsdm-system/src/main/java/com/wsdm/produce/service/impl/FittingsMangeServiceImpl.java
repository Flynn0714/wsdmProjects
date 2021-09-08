package com.wsdm.produce.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.ResultEnums;
import com.wsdm.common.enums.WisdomBusinessStatus;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.produce.domain.FittingsMange;
import com.wsdm.produce.mapper.FittingsMangeMapper;
import com.wsdm.produce.service.IFittingsMangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 配件管理Service业务层处理
 *
 * @author wsdm
 * @date 2020-11-06
 */
@Service
public class FittingsMangeServiceImpl implements IFittingsMangeService {
    @Autowired
    private FittingsMangeMapper fittingsMangeMapper;

    /**
     * 查询配件管理
     *
     * @param id 配件管理ID
     * @return 配件管理
     */
    @Override
    public FittingsMange selectFittingsMangeById(Long id) {
        return fittingsMangeMapper.selectFittingsMangeById(id);
    }

    /**
     * 查询配件管理列表
     *
     * @param fittingsMange 配件管理
     * @return 配件管理
     */
    @Override
    public List<FittingsMange> selectFittingsMangeList(FittingsMange fittingsMange) {
        return fittingsMangeMapper.selectFittingsMangeList(fittingsMange);
    }

    /**
     * 新增配件管理
     *
     * @param fittingsMange 配件管理
     * @return 结果
     */
    @Override
    public AjaxResult insertFittingsMange(FittingsMange fittingsMange) {
        if (Strings.isNullOrEmpty(fittingsMange.getFittingName()) || Strings.isNullOrEmpty(fittingsMange.getFittingModel())) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }

        fittingsMange.setFittingNumber(fittingsMange.getFittingName() + "-" + fittingsMange.getFittingModel());

        FittingsMange fitting = fittingsMangeMapper.getFittingInfoByFittingNumber(fittingsMange.getFittingName());
        if (fitting != null && fitting.getId() != null) {
            return AjaxResult.error(ResultEnums.FITTINGS_EXIST);
        }

        fittingsMange.setCreateTime(DateUtils.getNowDate());
        fittingsMange.setCreateBy(SecurityUtils.getUsername());
        fittingsMange.setStatus(WisdomBusinessStatus.STATUS_NORMAL.getCode());
        int result = fittingsMangeMapper.insertFittingsMange(fittingsMange);

        return result > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 修改配件管理
     *
     * @param fittingsMange 配件管理
     * @return 结果
     */
    @Override
    public AjaxResult updateFittingsMange(FittingsMange fittingsMange) {

        fittingsMange.setFittingNumber(fittingsMange.getFittingName() + "-" + fittingsMange.getFittingModel());

        FittingsMange fitting = fittingsMangeMapper.getFittingInfoByFittingNumber(fittingsMange.getFittingNumber());
        if (fitting != null && fitting.getId() != null && !fitting.getFittingNumber().equals(fittingsMange.getFittingNumber())) {
            return AjaxResult.error(ResultEnums.FITTINGS_EXIST);
        }

        fittingsMange.setUpdateTime(DateUtils.getNowDate());
        fittingsMange.setUpdateBy(SecurityUtils.getUsername());
        int result = fittingsMangeMapper.updateFittingsMange(fittingsMange);

        return result > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 批量删除配件管理
     *
     * @param ids 需要删除的配件管理ID
     * @return 结果
     */
    @Override
    public int deleteFittingsMangeByIds(Long[] ids) {
        return fittingsMangeMapper.deleteFittingsMangeByIds(ids);
    }

    /**
     * 删除配件管理信息
     *
     * @param id 配件管理ID
     * @return 结果
     */
    @Override
    public int deleteFittingsMangeById(Long id) {
        return fittingsMangeMapper.deleteFittingsMangeById(id);
    }

}
