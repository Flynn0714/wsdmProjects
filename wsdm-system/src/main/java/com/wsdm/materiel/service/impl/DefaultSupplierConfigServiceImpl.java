package com.wsdm.materiel.service.impl;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.ResultEnums;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.StringUtils;
import com.wsdm.materiel.domain.DefaultSupplierConfig;
import com.wsdm.materiel.mapper.DefaultSupplierConfigMapper;
import com.wsdm.materiel.service.IDefaultSupplierConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 默认供应商配置Service业务层处理
 *
 * @author wsdm
 * @date 2020-11-29
 */
@Service
public class DefaultSupplierConfigServiceImpl implements IDefaultSupplierConfigService {
    @Autowired
    private DefaultSupplierConfigMapper defaultSupplierConfigMapper;

    /**
     * 查询默认供应商配置
     *
     * @param id 默认供应商配置ID
     * @return 默认供应商配置
     */
    @Override
    public DefaultSupplierConfig selectDefaultSupplierConfigById(Long id) {
        return defaultSupplierConfigMapper.selectDefaultSupplierConfigById(id);
    }

    /**
     * 查询默认供应商配置列表
     *
     * @param defaultSupplierConfig 默认供应商配置
     * @return 默认供应商配置
     */
    @Override
    public List<DefaultSupplierConfig> selectDefaultSupplierConfigList(DefaultSupplierConfig defaultSupplierConfig) {
        return defaultSupplierConfigMapper.selectDefaultSupplierConfigList2();
    }

    /**
     * 新增默认供应商配置
     *
     * @param defaultSupplierConfig 默认供应商配置
     * @return 结果
     */
    @Override
    public int insertDefaultSupplierConfig(DefaultSupplierConfig defaultSupplierConfig) {
        defaultSupplierConfig.setCreateTime(DateUtils.getNowDate());
        return defaultSupplierConfigMapper.insertDefaultSupplierConfig(defaultSupplierConfig);
    }

    /**
     * 修改默认供应商配置
     *
     * @param defaultSupplierConfig 默认供应商配置
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateDefaultSupplierConfig(DefaultSupplierConfig defaultSupplierConfig) {

        int result = 0;
        if (defaultSupplierConfig.getId() != null){
            // 修改
            defaultSupplierConfig.setUpdateBy(SecurityUtils.getUsername());
            defaultSupplierConfig.setUpdateTime(DateUtils.getNowDate());
            result = defaultSupplierConfigMapper.updateDefaultSupplierConfig(defaultSupplierConfig);
        }
        // 新增
        else {
            defaultSupplierConfig.setUpdateBy(SecurityUtils.getUsername());
            defaultSupplierConfig.setUpdateTime(DateUtils.getNowDate());
            defaultSupplierConfig.setCreateBy(SecurityUtils.getUsername());
            defaultSupplierConfig.setCreateTime(DateUtils.getNowDate());
            result = defaultSupplierConfigMapper.insertDefaultSupplierConfig(defaultSupplierConfig);
        }


        if (result <= 0) {
            throw new BaseException("默认供应商修改错误！");
        }
        return AjaxResult.success();
    }

    /**
     * 批量删除默认供应商配置
     *
     * @param ids 需要删除的默认供应商配置ID
     * @return 结果
     */
    @Override
    public int deleteDefaultSupplierConfigByIds(Long[] ids) {
        return defaultSupplierConfigMapper.deleteDefaultSupplierConfigByIds(ids);
    }

    /**
     * 删除默认供应商配置信息
     *
     * @param id 默认供应商配置ID
     * @return 结果
     */
    @Override
    public int deleteDefaultSupplierConfigById(Long id) {
        return defaultSupplierConfigMapper.deleteDefaultSupplierConfigById(id);
    }
}
