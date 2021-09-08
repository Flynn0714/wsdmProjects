package com.wsdm.sale.service.impl;

import com.wsdm.common.CodeUtils;
import com.wsdm.common.constant.Constants;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.SalesOrderStatus;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.StringUtils;
import com.wsdm.sale.domain.*;
import com.wsdm.sale.mapper.SalesOrderDeliveryMapper;
import com.wsdm.sale.mapper.SalesOrderGatheringMapper;
import com.wsdm.sale.service.ISalesOrderGatheringService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 销售单收款Service业务层处理
 *
 * @author wsdm
 * @date 2021-04-27
 */
@Service
public class SalesOrderGatheringServiceImpl implements ISalesOrderGatheringService {
    @Autowired
    private SalesOrderGatheringMapper salesOrderGatheringMapper;

    @Autowired
    private SalesOrderDeliveryMapper salesOrderDeliveryMapper;
    /**
     * 查询销售单收款
     *
     * @param id 销售单收款ID
     * @return 销售单收款
     */
    @Override
    public SalesOrderGatheringInfo selectSalesOrderGatheringById(Long id) {
        SalesOrderGatheringInfo info = salesOrderGatheringMapper.selectSalesOrderGatheringById(id);
        if (info == null) {
            return null;
        }
        info.setGatheringDetailsList(salesOrderGatheringMapper.getGatheringDetailsByNumber(info.getGatheringNumber()));
        List<SalesOrderDeliveryDetails> list = salesOrderDeliveryMapper.getDeliverDetailsByDeliveryNumber(info.getDeliveryNumber());
        info.setDeliveryDetailsList(list);
        return info;
    }

    /**
     * 查询销售单收款列表
     *
     * @param salesOrderGathering 销售单收款
     * @return 销售单收款
     */
    @Override
    public List<SalesOrderGathering> selectSalesOrderGatheringList(SalesOrderGatheringQuery salesOrderGathering) {
        return salesOrderGatheringMapper.selectSalesOrderGatheringList(salesOrderGathering);
    }

    @Override
    public List<Map> getSalesGatheringOrder(String salesNumber, String customer) {
        List<Map> list = salesOrderGatheringMapper.getSalesGatheringOrder(salesNumber, customer);
        list.stream().forEach(map -> map.put("gatheringAmount", StringUtils.format4(map.get("gatheringAmount") != null ? map.get("gatheringAmount") : 0)));
        return list;
    }

    /**
     * 新增销售单收款
     *
     * @param salesOrderGathering 销售单收款
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult insertSalesOrderGathering(SalesOrderGathering salesOrderGathering) {

        List<SalesOrderGatheringDetails> detailsList = salesOrderGathering.getGatheringDetailsList();

        String code = Constants.SALES_GATHERING_SK + DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date());
        String maxCode = salesOrderGatheringMapper.getMaxGatheringCode(code);
        String gatheringCode = CodeUtils.getOneCode(code, maxCode, 3);

        salesOrderGathering.setGatheringAmount(new BigDecimal(0.00));
        salesOrderGathering.setGatheringNumber(gatheringCode);
        salesOrderGathering.setCreateTime(DateUtils.getNowDate());
        salesOrderGathering.setCreateBy(SecurityUtils.getUsername());

        detailsList.stream().forEach(details -> {
            details.setGatheringNumber(gatheringCode);
        });
        salesOrderGatheringMapper.insertGatheringDetailsBatch(detailsList);

        if (salesOrderGatheringMapper.insertSalesOrderGathering(salesOrderGathering) == 0) {
            throw new BaseException("新增失败");
        }
        return AjaxResult.success();
    }

    /**
     * 修改销售单收款
     *
     * @param salesOrderGathering 销售单收款
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateSalesOrderGathering(SalesOrderGathering salesOrderGathering) {

        SalesOrderGathering gathering = salesOrderGatheringMapper.selectSalesOrderGatheringById(salesOrderGathering.getId());
        if (gathering == null || Integer.parseInt(gathering.getGatheringStatus()) > 0) {
            AjaxResult.error("数据不存在或该状态不可修改");
        }

        List<SalesOrderGatheringDetails> newList = salesOrderGathering.getGatheringDetailsList();
        List<SalesOrderGatheringDetails> oldList = salesOrderGatheringMapper.getGatheringDetailsByNumber(salesOrderGathering.getGatheringNumber());
        newList.stream().forEach(n -> {
            n.setGatheringNumber(salesOrderGathering.getGatheringNumber());
        });

        if (CollectionUtils.isNotEmpty(oldList)) salesOrderGatheringMapper.deleteSalesOrderGatheringDetailsByIds(oldList);
        if (CollectionUtils.isNotEmpty(newList)) salesOrderGatheringMapper.insertGatheringDetailsBatch(newList);

        salesOrderGathering.setUpdateTime(DateUtils.getNowDate());
        salesOrderGathering.setUpdateBy(SecurityUtils.getUsername());
        salesOrderGathering.setGatheringAmount(new BigDecimal(0.00));
        if (salesOrderGatheringMapper.updateSalesOrderGathering(salesOrderGathering) <= 0) {
            throw new BaseException("修改失败");
        }
        return AjaxResult.success();
    }

    /**
     * 删除销售单收款信息
     *
     * @param id 销售单收款ID
     * @return 结果
     */
    @Override
    public int deleteSalesOrderGatheringById(Long id) {
        return salesOrderGatheringMapper.deleteSalesOrderGatheringById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult approve(Long id, String action) {
        SalesOrderGatheringInfo orderGatheringInfo = salesOrderGatheringMapper.selectSalesOrderGatheringById(id);
        BigDecimal gatheringAmount = new BigDecimal(0.00);

        if (!SalesOrderStatus.TO_BE_REVIEWED.getCode().equals(orderGatheringInfo.getGatheringStatus())) {
            return AjaxResult.error("-1", "当前状态不可审核！");
        }

        if (Constants.ADOPT.equals(action)) {
            List<SalesOrderGatheringDetails> detailsList = salesOrderGatheringMapper.getGatheringDetailsByNumber(orderGatheringInfo.getGatheringNumber());

            for (SalesOrderGatheringDetails details : detailsList) {
                gatheringAmount = gatheringAmount.add(details.getPayeeAmount()).setScale(2);
            }
            /*
            int a = salesOrderDelivery.getDeliveryAmount().compareTo(gatheringAmount);
            if (a == -1) {
                return AjaxResult.error("总收款金额不能大于发货金额");
            }*/

            orderGatheringInfo.setGatheringStatus(SalesOrderStatus.REVIEWED.getCode());
            orderGatheringInfo.setGatheringAmount(gatheringAmount);
        } else {
            orderGatheringInfo.setGatheringStatus(SalesOrderStatus.NOT_SUBMIT.getCode());
        }

        orderGatheringInfo.setApproveTime(new Date());
        orderGatheringInfo.setApprove(SecurityUtils.getUsername());
        salesOrderGatheringMapper.updateSalesOrderGathering(orderGatheringInfo);
        return AjaxResult.success();
    }
}
