package com.wsdm.materiel.mapper;

import com.wsdm.materiel.domain.DefaultSupplierConfig;

import java.util.List;

/**
 * 默认供应商配置Mapper接口
 *
 * @author wsdm
 * @date 2020-11-29
 */
public interface DefaultSupplierConfigMapper {
    /**
     * 查询默认供应商配置
     *
     * @param id 默认供应商配置ID
     * @return 默认供应商配置
     */
    public DefaultSupplierConfig selectDefaultSupplierConfigById(Long id);

    /**
     * 查询默认供应商配置列表
     *
     * @param defaultSupplierConfig 默认供应商配置
     * @return 默认供应商配置集合
     */
    public List<DefaultSupplierConfig> selectDefaultSupplierConfigList(DefaultSupplierConfig defaultSupplierConfig);

    /**
     * 查询默认供应商配置信息
     *
     * @param purchaseCategoryName 默认供应商配置
     * @return 默认供应商配置集合
     */
    public DefaultSupplierConfig selectDefaultSupplierConfig(String supplierCode);


    /**
     * 查询默认供应商配置列表
     *
     * @param
     * @return 默认供应商配置集合
     */
    public List<DefaultSupplierConfig> selectDefaultSupplierConfigList2();

    /**
     * 新增默认供应商配置
     *
     * @param defaultSupplierConfig 默认供应商配置
     * @return 结果
     */
    public int insertDefaultSupplierConfig(DefaultSupplierConfig defaultSupplierConfig);

    /**
     * 修改默认供应商配置
     *
     * @param defaultSupplierConfig 默认供应商配置
     * @return 结果
     */
    public int updateDefaultSupplierConfig(DefaultSupplierConfig defaultSupplierConfig);

    /**
     * 删除默认供应商配置
     *
     * @param id 默认供应商配置ID
     * @return 结果
     */
    public int deleteDefaultSupplierConfigById(Long id);

    /**
     * 批量删除默认供应商配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDefaultSupplierConfigByIds(Long[] ids);
}
