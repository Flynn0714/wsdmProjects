package com.wsdm.stock.service.impl;

import com.wsdm.common.constant.Constants;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.SalesStatus;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.stock.domain.SalesDelivery;
import com.wsdm.stock.domain.SalesDeliveryDetail;
import com.wsdm.stock.domain.SalesDeliveryRequest;
import com.wsdm.stock.mapper.SalesDeliveryMapper;
import com.wsdm.stock.service.ISalesDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 发货订单信息Service业务层处理
 *
 * @author wsdm
 * @date 2021-01-12
 */
@Service
public class SalesDeliveryServiceImpl implements ISalesDeliveryService {
    @Autowired
    private SalesDeliveryMapper salesDeliveryMapper;

    /**
     * 查询发货订单信息
     *
     * @param id 发货订单信息ID
     * @return 发货订单信息
     */
    @Override
    public SalesDelivery selectSalesDeliveryById(Long id) {
        SalesDelivery salesDelivery = salesDeliveryMapper.selectSalesDeliveryById(id);
        List<SalesDeliveryDetail> salesDeliveryDetails = salesDeliveryMapper.selectSalesDeliveryDetailList(new SalesDeliveryDetail(salesDelivery.getSalesNumber()));
        salesDelivery.setSalesDeliveryDetails(salesDeliveryDetails);
        return salesDelivery;
    }

    /**
     * 查询发货订单信息列表
     *
     * @param salesDelivery 发货订单信息
     * @return 发货订单信息
     */
    @Override
    public List<SalesDelivery> selectSalesDeliveryList(SalesDeliveryRequest salesDelivery) {
        return salesDeliveryMapper.selectSalesDeliveryList(salesDelivery);
    }

    /**
     * 新增发货订单信息
     *
     * @param salesDelivery 发货订单信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSalesDelivery(SalesDelivery salesDelivery) {
        String maxCode = salesDeliveryMapper.getMaxSalesDeliveryCode(Constants.SALES_DELIVERY_FH);
        List<SalesDeliveryDetail> salesDeliveryDetails = salesDelivery.getSalesDeliveryDetails();
        salesDeliveryDetails.stream().forEach(salesDeliveryDetail -> salesDeliveryDetail.setSalesNumber(maxCode));

        salesDelivery.setSalesNumber(maxCode);
        salesDelivery.setCreateBy(SecurityUtils.getUsername());

        salesDeliveryMapper.insertSalesDeliveryDetailBatch(salesDeliveryDetails);

        return salesDeliveryMapper.insertSalesDelivery(salesDelivery);
    }

    /**
     * 修改发货订单信息
     *
     * @param salesDelivery 发货订单信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateSalesDelivery(SalesDelivery salesDelivery) {
        SalesDelivery newSalesDelivery = salesDeliveryMapper.selectSalesDeliveryById(salesDelivery.getId());

        if (!SalesStatus.TO_BE_DELIVERED.getCode().equals(newSalesDelivery.getStatus())) {
            return 0;
        }

        if (!SalesStatus.TO_BE_DELIVERED.getCode().equals(salesDelivery.getStatus())) {
            salesDelivery.setApprovePerson(SecurityUtils.getUsername());
            salesDelivery.setApproveTime(DateUtils.getNowDate());
        }

        List<SalesDeliveryDetail> salesDeliveryDetails = salesDelivery.getSalesDeliveryDetails();
        salesDeliveryDetails.stream().forEach(materialReceiveDetail -> materialReceiveDetail.setSalesNumber(newSalesDelivery.getSalesNumber()));

        salesDelivery.setSalesNumber(newSalesDelivery.getSalesNumber());
        salesDelivery.setUpdateBy(SecurityUtils.getUsername());

        salesDeliveryMapper.deleteSalesDeliveryDetailBySalesNumber(newSalesDelivery.getSalesNumber());
        salesDeliveryMapper.insertSalesDeliveryDetailBatch(salesDeliveryDetails);
        return salesDeliveryMapper.updateSalesDelivery(salesDelivery);
    }

    @Override
    public AjaxResult approved(SalesDelivery salesDelivery) {
        return null;
    }

    /**
     * 批量删除发货订单信息
     *
     * @param ids 需要删除的发货订单信息ID
     * @return 结果
     */
    @Override
    public int deleteSalesDeliveryByIds(Long[] ids) {
        return salesDeliveryMapper.deleteSalesDeliveryByIds(ids);
    }

    /**
     * 删除发货订单信息信息
     *
     * @param id 发货订单信息ID
     * @return 结果
     */
    @Override
    public int deleteSalesDeliveryById(Long id) {
        return salesDeliveryMapper.deleteSalesDeliveryById(id);
    }

    /**
     * 查询发货订单详情
     *
     * @param id 发货订单详情ID
     * @return 发货订单详情
     */
    @Override
    public SalesDeliveryDetail selectSalesDeliveryDetailById(Long id) {
        return salesDeliveryMapper.selectSalesDeliveryDetailById(id);
    }

    /**
     * 查询发货订单详情列表
     *
     * @param salesDeliveryDetail 发货订单详情
     * @return 发货订单详情
     */
    @Override
    public List<SalesDeliveryDetail> selectSalesDeliveryDetailList(SalesDeliveryDetail salesDeliveryDetail) {
        return salesDeliveryMapper.selectSalesDeliveryDetailList(salesDeliveryDetail);
    }

    /**
     * 新增发货订单详情
     *
     * @param salesDeliveryDetail 发货订单详情
     * @return 结果
     */
    @Override
    public int insertSalesDeliveryDetail(SalesDeliveryDetail salesDeliveryDetail) {
        salesDeliveryDetail.setCreateTime(DateUtils.getNowDate());
        return salesDeliveryMapper.insertSalesDeliveryDetail(salesDeliveryDetail);
    }

    /**
     * 修改发货订单详情
     *
     * @param salesDeliveryDetail 发货订单详情
     * @return 结果
     */
    @Override
    public int updateSalesDeliveryDetail(SalesDeliveryDetail salesDeliveryDetail) {
        salesDeliveryDetail.setUpdateTime(DateUtils.getNowDate());
        return salesDeliveryMapper.updateSalesDeliveryDetail(salesDeliveryDetail);
    }

    /**
     * 批量删除发货订单详情
     *
     * @param ids 需要删除的发货订单详情ID
     * @return 结果
     */
    @Override
    public int deleteSalesDeliveryDetailByIds(Long[] ids) {
        return salesDeliveryMapper.deleteSalesDeliveryDetailByIds(ids);
    }

    /**
     * 删除发货订单详情信息
     *
     * @param id 发货订单详情ID
     * @return 结果
     */
    @Override
    public int deleteSalesDeliveryDetailById(Long id) {
        return salesDeliveryMapper.deleteSalesDeliveryDetailById(id);
    }
}
