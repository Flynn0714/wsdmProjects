package com.wsdm.materiel.service.impl;

import com.wsdm.common.CodeUtils;
import com.wsdm.common.enums.WisdomBusinessStatus;
import com.wsdm.common.exception.CustomException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.materiel.domain.Supplier;
import com.wsdm.materiel.mapper.SupplierMapper;
import com.wsdm.materiel.service.ISupplierService;
import com.wsdm.stock.service.ISupplierNameQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.wsdm.common.constant.Constants.GYS;


/**
 * 物料Service业务层处理
 *
 * @author wsdm
 * @date 2020-11-06
 */
@Service
public class SupplierServiceImpl implements ISupplierService, ISupplierNameQuery {
    @Autowired
    private SupplierMapper supplierMapper;

    /**
     * 查询物料
     *
     * @param id 物料ID
     * @return 物料
     */
    @Override
    public Supplier selectSupplierById(Long id) {
        return supplierMapper.selectSupplierById(id);
    }

    /**
     * 查询物料列表
     *
     * @param supplier 物料
     * @return 物料
     */
    @Override
    public List<Supplier> selectSupplierList(Supplier supplier) {
        return supplierMapper.selectSupplierList(supplier);
    }

    /**
     * 新增物料
     *
     * @param supplier 物料
     * @return 结果
     */
    @Override
    public int insertSupplier(Supplier supplier) {
        String maxCode = supplierMapper.getMaxSupplierCode(GYS);
        supplier.setCreateTime(DateUtils.getNowDate());
        supplier.setCreateBy(SecurityUtils.getUsername());
        supplier.setStatus(WisdomBusinessStatus.STATUS_NORMAL.getCode());
        supplier.setSupplierCode(CodeUtils.getOneCode(GYS, maxCode, 3));
        return supplierMapper.insertSupplier(supplier);
    }

    /**
     * 修改物料
     *
     * @param supplier 物料
     * @return 结果
     */
    @Override
    public int updateSupplier(Supplier supplier) {
        supplier.setUpdateTime(DateUtils.getNowDate());
        supplier.setUpdateBy(SecurityUtils.getUsername());
        return supplierMapper.updateSupplier(supplier);
    }

    /**
     * 批量删除物料
     *
     * @param ids 需要删除的物料ID
     * @return 结果
     */
    @Override
    public int deleteSupplierByIds(Long[] ids) {
        return supplierMapper.deleteSupplierByIds(ids);
    }

    /**
     * 删除物料信息
     *
     * @param id 物料ID
     * @return 结果
     */
    @Override
    public int deleteSupplierById(Long id) {
        return supplierMapper.deleteSupplierById(id);
    }

    @Override
    public int recovery(Long id) {
        return supplierMapper.recovery(id);
    }

    @Override
    public String query(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        Supplier param = new Supplier();
        param.setSupplierCode(code);
        List<Supplier> suppliers = supplierMapper.selectSupplierList(param);
        return CollectionUtils.isEmpty(suppliers) ? null : suppliers.get(0).getSupplierName();
    }
}
