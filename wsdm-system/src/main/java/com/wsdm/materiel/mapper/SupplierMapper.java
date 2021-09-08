package com.wsdm.materiel.mapper;

import com.wsdm.materiel.domain.Supplier;

import java.util.List;

/**
 * 物料Mapper接口
 *
 * @author wsdm
 * @date 2020-11-06
 */
public interface SupplierMapper {
    /**
     * 查询物料
     *
     * @param id 物料ID
     * @return 物料
     */
    public Supplier selectSupplierById(Long id);

    /**
     * 查询物料列表
     *
     * @param supplier 物料
     * @return 物料集合
     */
    public List<Supplier> selectSupplierList(Supplier supplier);

    /**
     * 新增物料
     *
     * @param supplier 物料
     * @return 结果
     */
    public int insertSupplier(Supplier supplier);

    /**
     * 修改物料
     *
     * @param supplier 物料
     * @return 结果
     */
    public int updateSupplier(Supplier supplier);

    /**
     * 删除物料
     *
     * @param id 物料ID
     * @return 结果
     */
    public int deleteSupplierById(Long id);

    /**
     * 批量删除物料
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSupplierByIds(Long[] ids);

    public String getMaxSupplierCode(String code);

    public int recovery(Long id);
}
