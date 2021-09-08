package com.wsdm.materiel.service.impl;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.ResultEnums;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.StringUtils;
import com.wsdm.materiel.domain.*;
import com.wsdm.materiel.mapper.PurchasePayOrderDetailMapper;
import com.wsdm.materiel.mapper.PurchasePayOrderInfoMapper;
import com.wsdm.materiel.service.PurchasePayOrderManageService;
import com.wsdm.materiel.service.PurchaseReturnInfoManageService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 物料采购付款单信息Service业务层处理
 *
 * @author wanglei
 * @date 2021-06-26
 */
@Service
public class PurchasePayOrderManageServiceImpl implements PurchasePayOrderManageService {
    public static final String STRING_0 = "0";
    public static final String STRING_1 = "1";
    public static final String STRING_2 = "2";
    public static final String STRING_3 = "3";
    @Autowired
    private PurchasePayOrderInfoMapper purchasePayOrderInfoMapper;
    @Autowired
    private PurchasePayOrderDetailMapper purchasePayOrderDetailMapper;
    @Autowired
    private ReceiveOrderManageServiceImpl receiveOrderManageService;
    @Autowired
    private PurchaseReturnInfoManageService purchaseReturnInfoService;

    /**
     * 查询物料采购付款单信息
     *
     * @param id 物料采购付款单信息ID
     * @return 物料采购付款单信息
     */
    @Override
    public PurchasePayOrderInfoVo selectPurchasePayOrderInfoById(Long id) {
        PurchasePayOrderInfoVo infoVo = purchasePayOrderInfoMapper.selectPurchasePayOrderInfoById(id);
        if (null == infoVo) {
            return null;
        }
        return infoVo;
    }

    /**
     * 查询物料采购付款单信息列表
     *
     * @param payOrderInfoVo 物料采购付款单信息
     * @return 物料采购付款单信息
     */
    @Override
    public List<PurchasePayOrderInfo> selectPurchasePayOrderInfoList(PurchasePayOrderInfo payOrderInfoVo) {
        return purchasePayOrderInfoMapper.selectPurchasePayOrderInfoList(payOrderInfoVo);
    }

    /**
     * 新增物料采购付款单信息
     *
     * @param infoVo 物料采购付款单信息
     * @return 结果
     */
    @Override
    public int insertPurchasePayOrderInfo(PurchasePayOrderInfo infoVo) {
        return purchasePayOrderInfoMapper.insertPurchasePayOrderInfo(infoVo);
    }

    private BigDecimal getTotalPaidAmountByReceiveNumber(String receiveNumber) {
        return purchasePayOrderInfoMapper.getTotalPaidAmountByReceiveNumber(receiveNumber);
    }

    /**
     * 修改物料采购付款单信息
     *
     * @param infoVo 物料采购付款单信息
     * @return 结果
     */
    @Override
    public int updatePurchasePayOrderInfo(PurchasePayOrderInfo infoVo) {
        return purchasePayOrderInfoMapper.updatePurchasePayOrderInfo(infoVo);
    }

    /**
     * 批量删除物料采购付款单信息
     *
     * @param ids 需要删除的物料采购付款单信息ID
     * @return 结果
     */
    @Override
    public int deletePurchasePayOrderInfoByIds(Long[] ids) {
        return purchasePayOrderInfoMapper.deletePurchasePayOrderInfoByIds(ids);
    }

    /**
     * 删除物料采购付款单信息信息
     *
     * @param id 物料采购付款单信息ID
     * @return 结果
     */
    @Override
    public int deletePurchasePayOrderInfoById(Long id) {
        return purchasePayOrderInfoMapper.deletePurchasePayOrderInfoById(id);
    }

    @Override
    public List<PurchasePayOrderDetail> selectPurchasePayRecordList(PurchasePayOrderInfoVo infoVo) {
        return purchasePayOrderDetailMapper.selectPurchasePayOrderDetailList(infoVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult insertPurchasePayRecord(PurchasePayOrderInfoVo infoVo) {
        String supplierCode = infoVo.getSupplierCode();
        List<PurchasePayOrderDetail> details = infoVo.getPurchasePayOrderDetails();
        if (StringUtils.isEmpty(supplierCode) || CollectionUtils.isEmpty(details)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        List<PurchasePayOrderInfo> payInfos = selectPurchasePayOrderInfoList(infoVo);
        PurchasePayOrderInfo payInfo = payInfos.get(0);
        BigDecimal payableAmount = payInfo.getPayableAmount();
        BigDecimal totalPayAmount = payInfo.getTotalPayAmount();
        String createBy = SecurityUtils.getUsername();
        Date createTime = DateUtils.getNowDate();
        infoVo.setLastPayer(createBy);
        infoVo.setLastPayTime(createTime);
        BigDecimal total = BigDecimal.ZERO;
        for (PurchasePayOrderDetail detail : details) {
            detail.setCreateBy(createBy);
            detail.setSupplierCode(supplierCode);
            detail.setCreateTime(createTime);
            total = total.add(detail.getPayAmount());
        }
        if (total.compareTo(payableAmount) > 0) {
            throw new BaseException("付款金额不能大于应付金额！");
        }
        payableAmount = payableAmount.subtract(total);
        totalPayAmount = totalPayAmount.add(total);
        infoVo.setPayableAmount(payableAmount);
        infoVo.setTotalPayAmount(totalPayAmount);
        int result = updatePurchasePayOrderInfo(infoVo);
        if (result <= 0) {
            throw new BaseException("采购应付货款信息更新失败！");
        }
        result = purchasePayOrderDetailMapper.insertPurchasePayOrderDetailBatch(details);
        if (result <= 0) {
            throw new BaseException("采购付款记录新增失败！");
        }
        return AjaxResult.success();
    }

    @Override
    public List<PurchaseDealDetail> selectPurchaseDealDetailList(PurchasePayOrderInfoVo infoVo) {
        return purchasePayOrderInfoMapper.selectPurchaseDealDetailList(infoVo);
    }

    @Override
    public AjaxResult selectPurchasePayDetailInfo(String dealType, String dealNumber) {
        if (STRING_1.equals(dealType)) {
            ReceiveOrderInfoVo infoVo = receiveOrderManageService.selectReceiveOrderInfoByReceiveNumber(dealNumber);
            return AjaxResult.success(infoVo);
        } else if (STRING_2.equals(dealType)) {
            PurchaseReturnInfo infoVo = purchaseReturnInfoService.selectReturnOrderInfoByReturnNumber(dealNumber);
            return AjaxResult.success(infoVo);
        }
        return AjaxResult.success();
    }

    @Override
    public PurchaseDealTableDataInfo getTotalDealNumAndAmount(PurchasePayOrderInfoVo purchasePayOrderInfo) {
        return purchasePayOrderInfoMapper.getTotalDealNumAndAmount(purchasePayOrderInfo);
    }

    @Override
    public List<PurchaseDealDetail> selectPurchaseDealRecordList(PurchasePayOrderInfoVo infoVo) {
        return purchasePayOrderInfoMapper.selectPurchaseDealRecordList(infoVo);
    }

}
