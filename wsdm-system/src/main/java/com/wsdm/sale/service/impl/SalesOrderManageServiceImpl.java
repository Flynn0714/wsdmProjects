package com.wsdm.sale.service.impl;

import com.google.common.base.Strings;
import com.wsdm.common.CodeUtils;
import com.wsdm.common.constant.Constants;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.AccountReceivedType;
import com.wsdm.common.enums.ResultEnums;
import com.wsdm.common.enums.SalesOrderStatus;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.StringUtils;
import com.wsdm.sale.domain.*;
import com.wsdm.sale.mapper.SalesOrderAccountReceivedMapper;
import com.wsdm.sale.mapper.SalesOrderManageMapper;
import com.wsdm.sale.service.ISalesOrderAccountReceivedService;
import com.wsdm.sale.service.ISalesOrderCollectionService;
import com.wsdm.sale.service.ISalesOrderManageService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 销售订单Service业务层处理
 *
 * @author wsdm
 * @date 2020-11-06
 */
@Service
public class SalesOrderManageServiceImpl implements ISalesOrderManageService {

    @Autowired
    private SalesOrderManageMapper salesOrderManageMapper;

    @Autowired
    private ISalesOrderAccountReceivedService accountReceivedService;

    @Autowired
    private ISalesOrderCollectionService collectionService;

    @Autowired
    private SalesOrderAccountReceivedMapper accountReceivedMapper;

    /**
     * 查询销售订单详情信息
     *
     * @param id 销售订单ID
     * @return 销售订单详情
     */
    @Override
    public SalesOrderManageInfo selectSalesOrderManageById(Long id) {
        SalesOrderManageInfo salesOrderManage = salesOrderManageMapper.selectSalesOrderManageById(id);
        if (salesOrderManage == null || StringUtils.isEmpty(salesOrderManage.getSalesNumber())) {
            return salesOrderManage;
        }
        // 根据销售单号查询销售订单合同详情
        List<SalesOrderManageDetails> salesOrderManageDetails = salesOrderManageMapper.selectSalesOrderManageDetailsList(salesOrderManage.getSalesNumber());
        List<SalesOrderManageGathering> salesOrderManageGatherings = salesOrderManageMapper.getOrderGatheringByNumber(salesOrderManage.getSalesNumber());
        salesOrderManage.setSalesOrderManageDetailsList(salesOrderManageDetails);
        salesOrderManage.setSalesOrderManageGatherings(salesOrderManageGatherings);
        return salesOrderManage;
    }

    /**
     * 查询销售订单列表
     *
     * @param salesOrderManage 销售订单
     * @return 销售订单
     */
    @Override
    public List<SalesOrderManageInfo> selectSalesOrderManageList(SalesOrderManageRequest salesOrderManage) {
        return salesOrderManageMapper.selectSalesOrderManageList(salesOrderManage);
    }

    @Override
    public List<Map> getSalesOrderByDelivery(SalesOrderManageRequest salesOrderManage) {
        List<Map> list = salesOrderManageMapper.getSalesOrderByDelivery(salesOrderManage);
        list.stream().forEach(map -> {

            map.put("total", StringUtils.format2(map.get("total") != null ? map.get("total") : 0));
            map.put("deliveredQuantity", StringUtils.format2(map.get("deliveredQuantity") != null ? map.get("deliveredQuantity") : 0));

        });
        return list;
    }

    /**
     * 自动采购--销售单查询展示
     */
    @Override
    public List<SalesOrderAutomaticPurchase> salesOrderAutomaticPurchase() {
        return salesOrderManageMapper.salesOrderAutomaticPurchase();
    }

    /**
     * 新增销售订单
     *
     * @param salesOrderManage 销售订单
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult insertSalesOrderManage(SalesOrderManage salesOrderManage) {
        List<SalesOrderManageDetails> salesOrderManageDetailsList = salesOrderManage.getSalesOrderManageDetailsList();
        List<SalesOrderManageGathering> salesOrderManageGatherings = salesOrderManage.getSalesOrderManageGatherings();
        String salesPrefix = Constants.SALSE_XS + DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date());
        String maxCode = salesOrderManageMapper.getMaxSalesCode(salesPrefix);
        String salesCode = CodeUtils.getOneCode(salesPrefix, maxCode, 3);

        Date date = DateUtils.getNowDate();
        String userName = SecurityUtils.getUsername();

        if (Strings.isNullOrEmpty(salesOrderManage.getCustomer())) {
            salesOrderManage.setCustomer("0");
        }

        salesOrderManage.setCreateTime(date);
        salesOrderManage.setCreateBy(userName);
        // 编码
        salesOrderManage.setSalesNumber(salesCode);
        BigDecimal orderAmount = new BigDecimal(0);
        BigDecimal orderQuantity = new BigDecimal(0);

        for (SalesOrderManageDetails details : salesOrderManageDetailsList) {
            details.setCreateTime(date);
            details.setCreateBy(userName);
            details.setSalesNumber(salesCode);

            orderAmount = orderAmount.add(details.getSubtotal()).setScale(2);
            orderQuantity = orderQuantity.add(details.getQuantity()).setScale(1);
        }

        BigDecimal orderGathering = new BigDecimal(0);
        for (SalesOrderManageGathering gathering : salesOrderManageGatherings) {

            gathering.setCreateTime(date);
            gathering.setCreateBy(userName);
            gathering.setSalesNumber(salesCode);

            orderGathering = orderGathering.add(gathering.getPayeeAmount());
        }

        salesOrderManage.setOrderAmount(orderAmount);
        salesOrderManage.setOrderQuantity(orderQuantity);
        salesOrderManage.setOrderGathering(orderGathering);
        int detailsResult = salesOrderManageMapper.insertSalesOrderManageDetailsBatch(salesOrderManageDetailsList);
        if (detailsResult <= 0) {
            throw new BaseException("销售订单详情新增错误！");
        }

        if (CollectionUtils.isNotEmpty(salesOrderManageGatherings)) {
            int gatheringResult = salesOrderManageMapper.insertOrderManageGatheringBatch(salesOrderManageGatherings);
            if (gatheringResult <= 0) {
                throw new BaseException("销售订单预收款详情新增错误！");
            }
        }

        int result = salesOrderManageMapper.insertSalesOrderManage(salesOrderManage);

        if (result <= 0) {
            throw new BaseException("销售订单新增错误！");
        }
        return AjaxResult.success();
    }

    /**
     * 修改销售订单
     *
     * @param salesOrderManage 销售订单
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateSalesOrderManage(SalesOrderManage salesOrderManage) {
        SalesOrderManageInfo salesOrderManageInfo = salesOrderManageMapper.selectSalesOrderManageById(salesOrderManage.getId());
        if (salesOrderManageInfo == null || !salesOrderManageInfo.getSalesNumber().equals(salesOrderManage.getSalesNumber())) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }

        Date date = DateUtils.getNowDate();
        String userName = SecurityUtils.getUsername();
        List<SalesOrderManageDetails> salesOrderManageDetailsList = salesOrderManage.getSalesOrderManageDetailsList();
        List<SalesOrderManageGathering> salesOrderManageGatherings = salesOrderManage.getSalesOrderManageGatherings();

        BigDecimal orderAmount = new BigDecimal(0);
        BigDecimal orderQuantity = new BigDecimal(0);
        for (SalesOrderManageDetails details : salesOrderManageDetailsList) {
            details.setUpdateTime(date);
            details.setUpdateBy(userName);
            details.setCreateBy(userName);
            details.setCreateTime(salesOrderManageInfo.getCreateTime());
            details.setSalesNumber(salesOrderManage.getSalesNumber());

            orderAmount = orderAmount.add(details.getSubtotal()).setScale(2);
            orderQuantity = orderQuantity.add(details.getQuantity()).setScale(1);
        }

        BigDecimal orderGathering = new BigDecimal(0);
        for (SalesOrderManageGathering gathering : salesOrderManageGatherings) {
            gathering.setUpdateTime(date);
            gathering.setUpdateBy(userName);
            gathering.setCreateBy(userName);
            gathering.setCreateTime(salesOrderManageInfo.getCreateTime());
            gathering.setSalesNumber(salesOrderManage.getSalesNumber());
            orderGathering = orderGathering.add(gathering.getPayeeAmount());
        }

        salesOrderManage.setOrderAmount(orderAmount);
        salesOrderManage.setOrderQuantity(orderQuantity);
        salesOrderManage.setOrderGathering(orderGathering);
        salesOrderManage.setUpdateTime(date);
        salesOrderManage.setUpdateBy(userName);
        salesOrderManageMapper.deleteOrderDetailsBySalesNumber(salesOrderManage.getSalesNumber());
        salesOrderManageMapper.deleteOrderGatheringBySalesNumber(salesOrderManage.getSalesNumber());
        salesOrderManageMapper.insertSalesOrderManageDetailsBatch(salesOrderManageDetailsList);
        if (CollectionUtils.isNotEmpty(salesOrderManageGatherings))
            salesOrderManageMapper.insertOrderManageGatheringBatch(salesOrderManageGatherings);
        salesOrderManageMapper.updateSalesOrderManage(salesOrderManage);
        return AjaxResult.success();
    }

    /**
     * 删除销售订单信息
     *
     * @param id 销售订单ID
     * @return 结果
     */
    @Override
    public AjaxResult deleteSalesOrderManageById(Long id) {
        SalesOrderManage salesOrderManage = salesOrderManageMapper.selectSalesOrderManageById(id);
        if (Integer.parseInt(salesOrderManage.getStatus()) > 0) {
            return AjaxResult.error("该状态不可删除！");
        }
        return result(salesOrderManageMapper.deleteSalesOrderManageById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult approve(Long id, String action) {

        SalesOrderManage salesOrderManage = salesOrderManageMapper.selectSalesOrderManageById(id);
        if (!SalesOrderStatus.TO_BE_REVIEWED.getCode().equals(salesOrderManage.getStatus())) {
            return AjaxResult.error("-1", "当前状态不可审核！");
        }

        SalesOrderManage approveOrder = new SalesOrderManage(salesOrderManage.getId());
        if (Constants.ADOPT.equals(action)) {
            approveOrder.setStatus(SalesOrderStatus.REVIEWED.getCode());

            List<SalesOrderManageGathering> gatheringsList = salesOrderManageMapper.getOrderGatheringByNumber(salesOrderManage.getSalesNumber());

            if (CollectionUtils.isNotEmpty(gatheringsList)) {

                int result = insertAccountReceived(salesOrderManage, gatheringsList);
                if (result <= 0) {
                    throw new BaseException("预收款记录插入失败");
                }

                BigDecimal gather = new BigDecimal(0);
                for (SalesOrderManageGathering g : gatheringsList) {
                    gather = gather.add(g.getPayeeAmount());
                }

                int cResult = collectionService.insertSalesOrderCollection(
                        salesOrderManage.getCustomer(),
                        salesOrderManage.getOrderAmount(),
                        gather,
                        null,
                        DateUtils.getNowDate());

                if (cResult <= 0) {
                    throw new BaseException("收款汇总插入失败");
                }
            }

        } else {
            approveOrder.setStatus(SalesOrderStatus.NOT_SUBMIT.getCode());
        }

        approveOrder.setApprove(SecurityUtils.getUsername());
        approveOrder.setApproveTime(DateUtils.getNowDate());

        // 修改状态
        salesOrderManageMapper.approveSalesOrderManage(approveOrder);

        return AjaxResult.success();
    }

    // 新增预收款
    public int insertAccountReceived(SalesOrderManage salesOrderManage, List<SalesOrderManageGathering> gatheringList) {
        Date date = DateUtils.getNowDate();
        List<SalesOrderAccountReceived> accountReceivedList = new ArrayList<>();
        gatheringList.stream().forEach(o -> {
            SalesOrderAccountReceived accountReceived = new SalesOrderAccountReceived(
                    salesOrderManage.getCustomer(),
                    AccountReceivedType.ADVANCE.getCode(),
                    salesOrderManage.getCreateBy(),
                    o.getPaymentMethod(),
                    o.getReceivingBank(),
                    o.getPayeeAmount(),
                    o.getAccountNumber(),
                    date
            );
            accountReceived.setRemark(o.getRemark());
            accountReceived.setCreateTime(date);
            accountReceived.setCreateBy(SecurityUtils.getUsername());

            accountReceivedList.add(accountReceived);
        });
        return accountReceivedMapper.insertSalesOrderAccountReceivedBatch(accountReceivedList);
    }

    public AjaxResult result(int result) {
        return result > 0 ? AjaxResult.success() : AjaxResult.error("操作失败");
    }

}
