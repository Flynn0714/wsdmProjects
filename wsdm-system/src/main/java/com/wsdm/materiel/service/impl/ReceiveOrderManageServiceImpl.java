package com.wsdm.materiel.service.impl;

import com.google.common.collect.Lists;
import com.wsdm.common.constant.Constants;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.*;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.StringUtils;
import com.wsdm.materiel.domain.*;
import com.wsdm.materiel.mapper.ReceiveOrderDetailMapper;
import com.wsdm.materiel.mapper.ReceiveOrderInfoMapper;
import com.wsdm.materiel.service.IPurchaseOrderManageService;
import com.wsdm.materiel.service.IReceiveOrderManageService;
import com.wsdm.materiel.service.PurchasePayOrderManageService;
import com.wsdm.materiel.service.QualityInspectionManageService;
import com.wsdm.stock.domain.SkStockRequest;
import com.wsdm.stock.service.ISkStockService;
import com.wsdm.utils.CodeUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 物料收货订单信息Service业务层处理
 *
 * @author wsdm
 * @date 2021-01-09
 */
@Service
public class ReceiveOrderManageServiceImpl implements IReceiveOrderManageService {

    public static final String STRING_0 = "0";
    public static final String STRING_1 = "1";
    public static final String STRING_2 = "2";
    public static final String STRING_3 = "3";

    @Autowired
    private ReceiveOrderInfoMapper receiveOrderInfoMapper;
    @Autowired
    private ReceiveOrderDetailMapper receiveOrderDetailMapper;
    @Autowired
    private IPurchaseOrderManageService purchaseOrderManageService;
    @Autowired
    private ISkStockService skStockService;
    @Autowired
    private QualityInspectionManageService qualityInspectionManageService;
    @Autowired
    private PurchasePayOrderManageService payOrderManageService;

    /**
     * 查询物料收货订单信息
     *
     * @param id 物料收货订单信息ID
     * @return 物料收货订单信息
     */
    @Override
    public ReceiveOrderInfoVo selectReceiveOrderInfoById(Long id) {
        ReceiveOrderInfoVo receiveOrderInfoVo = receiveOrderInfoMapper.selectReceiveOrderInfoById(id);
        if (receiveOrderInfoVo == null || StringUtils.isEmpty(receiveOrderInfoVo.getReceiveNumber())) {
            return receiveOrderInfoVo;
        }
        // 根据收货单号查询收货订单详情
        List<ReceiveOrderDetail> receiveOrderDetails = receiveOrderDetailMapper.selectReceiveOrderDetailByReceiveNumber(receiveOrderInfoVo.getReceiveNumber());
        receiveOrderInfoVo.setReceiveOrderDetails(receiveOrderDetails);
        return receiveOrderInfoVo;
    }

    /**
     * 查询物料收货订单信息
     *
     * @param receiveNumber 物料收货订单信息ID
     * @return 物料收货订单信息
     */
    @Override
    public ReceiveOrderInfoVo selectReceiveOrderInfoByReceiveNumber(String receiveNumber) {
        ReceiveOrderInfoVo receiveOrderInfoVo = receiveOrderInfoMapper.selectReceiveOrderInfoByReceiveNumber(receiveNumber);
        if (receiveOrderInfoVo == null || StringUtils.isEmpty(receiveOrderInfoVo.getReceiveNumber())) {
            return receiveOrderInfoVo;
        }
        // 根据收货单号查询收货订单详情
        List<ReceiveOrderDetail> receiveOrderDetails = receiveOrderDetailMapper.selectReceiveOrderDetailByReceiveNumber(receiveOrderInfoVo.getReceiveNumber());
        receiveOrderInfoVo.setReceiveOrderDetails(receiveOrderDetails);
        return receiveOrderInfoVo;
    }

    /**
     * 查询物料收货订单信息列表
     *
     * @param receiveOrderInfo 物料收货订单信息
     * @return 物料收货订单信息
     */
    @Override
    public List<ReceiveOrderInfo> selectReceiveOrderInfoList(ReceiveOrderInfo receiveOrderInfo) {
        return receiveOrderInfoMapper.selectReceiveOrderInfoList(receiveOrderInfo);
    }


    /**
     * 新增物料收货订单信息
     *
     * @param receiveInfo 物料收货订单信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult insertReceiveOrderInfo(ReceiveOrderInfoVo receiveInfo) {
        List<ReceiveOrderDetail> details = receiveInfo.getReceiveOrderDetails();
        if (CollectionUtils.isEmpty(details)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        // 生成收货单号
        String receivePrefix = Constants.RECEIVE_NUMBER_PREFIX + DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date());
        String maxCode = receiveOrderInfoMapper.getMaxReceiveCode(receivePrefix);

        receiveInfo.setCreateTime(DateUtils.getNowDate());
        receiveInfo.setCreateBy(SecurityUtils.getUsername());
        receiveInfo.setArrivalDate(DateUtils.getNowDate());
        receiveInfo.setUpdateBy(null);
        receiveInfo.setUpdateTime(null);
        // optChannel 1-保存 2-保存提审
        String optChannel = receiveInfo.getOptChannel();
        if (STRING_1.equals(optChannel)) {
            // 未提交
            receiveInfo.setStatus(ReceiveOrderStatus.NOT_SUBMIT.getCode());
        }
        // 收货编码
        String receiveNumber = CodeUtils.getOneCode(receivePrefix, maxCode, 3);
        receiveInfo.setReceiveNumber(receiveNumber);
        List<String> wlList = Lists.newArrayList();
        List<ReceiveOrderDetail> detailList = Lists.newArrayList();
        for (ReceiveOrderDetail detail : details) {
            BigDecimal receiveNum = detail.getReceiveNum();
            if (STRING_2.equals(optChannel) && (receiveNum == null || receiveNum.compareTo(BigDecimal.ZERO) == 0)) {
                continue;
            }
            detailList.add(detail);
        }
        if (CollectionUtils.isEmpty(detailList)) {
            return AjaxResult.error("收货数量不能全部为0！");
        }
        // 是否需要质检 0-需要 1-不需要
        String isCheck = "1";
        for (int i = 0; i < detailList.size(); i++) {
            ReceiveOrderDetail detail = detailList.get(i);
            detail.setItemNo(i + 1);
            if (wlList.contains(detail.getMaterielNumber())) {
                return AjaxResult.error("收货物料重复！");
            } else {
                wlList.add(detail.getMaterielNumber());
            }
            detail.setReceiveNumber(receiveNumber);
            detail.setCreateBy(SecurityUtils.getUsername());
            detail.setCreateTime(DateUtils.getNowDate());
            BigDecimal receiveNum = detail.getReceiveNum();
            BigDecimal unitPrice = detail.getUnitPrice();
            BigDecimal subtotalAmount = detail.getSubtotalAmount();
            unitPrice = null == unitPrice ? BigDecimal.ZERO : unitPrice;
            subtotalAmount = null == subtotalAmount ? BigDecimal.ZERO : subtotalAmount;
            detail.setUnitPrice(unitPrice);
            detail.setSubtotalAmount(subtotalAmount);
            receiveNum = null == receiveNum ? BigDecimal.ZERO : receiveNum;
            detail.setReceiveNum(receiveNum);
            // checkType 质检类型 1-全检 2-抽检 3-免检'
            String checkType = detail.getCheckType();
            // 收货数量大于0且需要质检
            if (receiveNum.compareTo(BigDecimal.ZERO) > 0 && (STRING_1.equals(checkType) || STRING_2.equals(checkType))) {
                isCheck = "0";
            }
        }
        if (STRING_2.equals(optChannel)) {
            receiveInfo.setStatus("0".equals(isCheck) ? ReceiveOrderStatus.WAIT_CHECK.getCode() : ReceiveOrderStatus.WAIT_AUDIT.getCode());
            if ("0".equals(isCheck)) {
                // 自动生成质检单
                QualityInspectionInfoVo infoVo = new QualityInspectionInfoVo();
                String checkNumber = qualityInspectionManageService.getCheckNumber();
                receiveInfo.setCheckNumber(checkNumber);
                infoVo.setReceiveNumber(receiveNumber);
                infoVo.setCheckNumber(checkNumber);
                infoVo.setCheckDate(DateUtils.getNowDate());
                infoVo.setCreateBy(SecurityUtils.getUsername());
                infoVo.setCreateTime(DateUtils.getNowDate());
                infoVo.setPurchaseType(receiveInfo.getPurchaseType());
                infoVo.setStatus(CheckOrderStatus.WAIT_CHECK.getCode());
                List<QualityInspectionDetail> checkDetails = Lists.newArrayList();
                for (ReceiveOrderDetail detail : detailList) {
                    BigDecimal receiveNum = detail.getReceiveNum();
                    // checkType 质检类型 1-全检 2-抽检 3-免检'
                    String checkType = detail.getCheckType();
                    // 收货数量大于0且需要质检
                    if (receiveNum.compareTo(BigDecimal.ZERO) > 0 && (STRING_1.equals(checkType) || STRING_2.equals(checkType))) {
                        QualityInspectionDetail checkDetail = new QualityInspectionDetail();
                        checkDetail.setCheckNumber(checkNumber);
                        checkDetail.setMaterielNumber(detail.getMaterielNumber());
                        checkDetail.setMaterielName(detail.getMaterielName());
                        checkDetail.setModelParam(detail.getModelParam());
                        checkDetail.setReceiveNum(detail.getReceiveNum());
                        checkDetail.setCheckType(detail.getCheckType());
                        checkDetail.setCreateBy(SecurityUtils.getUsername());
                        checkDetail.setCreateTime(DateUtils.getNowDate());
                        checkDetails.add(checkDetail);
                    }
                }
                infoVo.setQualityInspectionDetails(checkDetails);
                AjaxResult ajaxResult = qualityInspectionManageService.insertQualityInspectionInfo(infoVo);
                if (200 != MapUtils.getInteger(ajaxResult, "code")) {
                    throw new BaseException("生成质检单失败！");
                }
            } else {
                receiveInfo.setUpdateBy(SecurityUtils.getUsername());
                receiveInfo.setUpdateTime(DateUtils.getNowDate());
            }
        }
        receiveInfo.setIsCheck(isCheck);
        int result = receiveOrderInfoMapper.insertReceiveOrderInfo(receiveInfo);
        if (result <= 0) {
            throw new BaseException("收货订单新增失败！");
        }
        result = receiveOrderDetailMapper.insertReceiveOrderDetailBatch(detailList);
        if (result <= 0) {
            throw new BaseException("收货订单详情新增失败！");
        }
        return AjaxResult.success();
    }

    /**
     * 修改物料收货订单信息
     *
     * @param receiveInfo 物料收货订单信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateReceiveOrderInfo(ReceiveOrderInfoVo receiveInfo) {
        String receiveNumber = receiveInfo.getReceiveNumber();
        if (StringUtils.isEmpty(receiveNumber) || null == receiveInfo.getId()) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        List<ReceiveOrderDetail> details = receiveInfo.getReceiveOrderDetails();
        if (CollectionUtils.isEmpty(details)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }

        // optChannel 1-保存 2-保存提审
        String optChannel = receiveInfo.getOptChannel();
        if (STRING_1.equals(optChannel)) {
            // 未提交
            receiveInfo.setStatus(ReceiveOrderStatus.NOT_SUBMIT.getCode());
        }
        List<String> wlList = Lists.newArrayList();
        List<ReceiveOrderDetail> detailList = Lists.newArrayList();
        for (ReceiveOrderDetail detail : details) {
            BigDecimal receiveNum = detail.getReceiveNum();
            if (STRING_2.equals(optChannel) && (receiveNum == null || receiveNum.compareTo(BigDecimal.ZERO) == 0)) {
                continue;
            }
            detailList.add(detail);
        }
        if (CollectionUtils.isEmpty(detailList)) {
            return AjaxResult.error("收货数量不能全部为0！");
        }
        // 是否需要质检 0-需要 1-不需要
        String isCheck = "1";
        for (int i = 0; i < detailList.size(); i++) {
            ReceiveOrderDetail detail = detailList.get(i);
            detail.setItemNo(i + 1);
            if (wlList.contains(detail.getMaterielNumber())) {
                return AjaxResult.error("收货物料重复！");
            } else {
                wlList.add(detail.getMaterielNumber());
            }
            detail.setReceiveNumber(receiveNumber);
            detail.setCreateBy(SecurityUtils.getUsername());
            detail.setCreateTime(DateUtils.getNowDate());
            BigDecimal receiveNum = detail.getReceiveNum();
            receiveNum = null == receiveNum ? BigDecimal.ZERO : receiveNum;
            detail.setReceiveNum(receiveNum);
            // checkType 质检类型 1-全检 2-抽检 3-免检'
            String checkType = detail.getCheckType();
            // 收货数量大于0且需要质检
            if (receiveNum.compareTo(BigDecimal.ZERO) > 0 && (STRING_1.equals(checkType) || STRING_2.equals(checkType))) {
                isCheck = "0";
            }
            BigDecimal unitPrice = detail.getUnitPrice();
            BigDecimal subtotalAmount = detail.getSubtotalAmount();
            unitPrice = null == unitPrice ? BigDecimal.ZERO : unitPrice;
            subtotalAmount = null == subtotalAmount ? BigDecimal.ZERO : subtotalAmount;
            detail.setUnitPrice(unitPrice);
            detail.setSubtotalAmount(subtotalAmount);
        }
        if (STRING_2.equals(optChannel)) {
            receiveInfo.setStatus("0".equals(isCheck) ? ReceiveOrderStatus.WAIT_CHECK.getCode() : ReceiveOrderStatus.WAIT_AUDIT.getCode());
            if ("0".equals(isCheck)) {
                // 自动生成质检单
                QualityInspectionInfoVo infoVo = new QualityInspectionInfoVo();
                String checkNumber = qualityInspectionManageService.getCheckNumber();
                receiveInfo.setCheckNumber(checkNumber);
                infoVo.setReceiveNumber(receiveNumber);
                infoVo.setCheckNumber(checkNumber);
                infoVo.setCheckDate(DateUtils.getNowDate());
                infoVo.setCreateBy(SecurityUtils.getUsername());
                infoVo.setCreateTime(DateUtils.getNowDate());
                infoVo.setPurchaseType(receiveInfo.getPurchaseType());
                infoVo.setStatus(CheckOrderStatus.WAIT_CHECK.getCode());
                List<QualityInspectionDetail> checkDetails = Lists.newArrayList();
                for (ReceiveOrderDetail detail : detailList) {
                    BigDecimal receiveNum = detail.getReceiveNum();
                    // checkType 质检类型 1-全检 2-抽检 3-免检'
                    String checkType = detail.getCheckType();
                    // 收货数量大于0且需要质检
                    if (receiveNum.compareTo(BigDecimal.ZERO) > 0 && (STRING_1.equals(checkType) || STRING_2.equals(checkType))) {
                        QualityInspectionDetail checkDetail = new QualityInspectionDetail();
                        checkDetail.setCheckNumber(checkNumber);
                        checkDetail.setMaterielNumber(detail.getMaterielNumber());
                        checkDetail.setMaterielName(detail.getMaterielName());
                        checkDetail.setModelParam(detail.getModelParam());
                        checkDetail.setReceiveNum(detail.getReceiveNum());
                        checkDetail.setCheckType(detail.getCheckType());
                        checkDetail.setCreateBy(SecurityUtils.getUsername());
                        checkDetail.setCreateTime(DateUtils.getNowDate());
                        checkDetails.add(checkDetail);
                    }
                }
                infoVo.setQualityInspectionDetails(checkDetails);
                AjaxResult ajaxResult = qualityInspectionManageService.insertQualityInspectionInfo(infoVo);
                if (200 != MapUtils.getInteger(ajaxResult, "code")) {
                    throw new BaseException("生成质检单失败！");
                }
            } else {
                receiveInfo.setUpdateBy(SecurityUtils.getUsername());
                receiveInfo.setUpdateTime(DateUtils.getNowDate());
            }
        }
        receiveInfo.setIsCheck(isCheck);
        int result = receiveOrderInfoMapper.updateReceiveOrderInfo(receiveInfo);
        if (result <= 0) {
            throw new BaseException("收货订单新增失败！");
        }
        result = receiveOrderDetailMapper.deleteReceiveOrderDetailByReceiveNumber(receiveNumber);
        if (result <= 0) {
            throw new BaseException("收货订单详情新增失败！");
        }
        result = receiveOrderDetailMapper.insertReceiveOrderDetailBatch(detailList);
        if (result <= 0) {
            throw new BaseException("收货订单详情新增失败！");
        }
        return AjaxResult.success();
    }

    /**
     * 批量删除物料收货订单信息
     *
     * @param ids 需要删除的物料收货订单信息ID
     * @return 结果
     */
    @Override
    public int deleteReceiveOrderInfoByIds(Long[] ids) {
        return receiveOrderInfoMapper.deleteReceiveOrderInfoByIds(ids);
    }

    /**
     * 删除物料收货订单信息信息
     *
     * @param id 物料收货订单信息ID
     * @return 结果
     */
    @Override
    public int deleteReceiveOrderInfoById(Long id) {
        return receiveOrderInfoMapper.deleteReceiveOrderInfoById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateApproveStatus(Long id, String action) {
        if (null == id || StringUtils.isBlank(action)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        // 查询采购订单状态
        ReceiveOrderInfoVo data = selectReceiveOrderInfoById(id);
        if (!ReceiveOrderStatus.WAIT_AUDIT.getCode().equals(data.getStatus())) {
            return AjaxResult.error("收货订单状态非待审核，不能审核");
        }
        // 审核（已收货）
        ReceiveOrderInfoVo receiveInfo = new ReceiveOrderInfoVo();
        receiveInfo.setId(id);
        receiveInfo.setAuditTime(DateUtils.getNowDate());
        receiveInfo.setAuditBy(SecurityUtils.getUsername());
        int result;
        if ("adopt".equals(action)) {
            receiveInfo.setStatus(ReceiveOrderStatus.AUDITED.getCode());
        } else if ("cancel".equals(action)) {
            receiveInfo.setStatus(ReceiveOrderStatus.NOT_SUBMIT.getCode());
            // 清空质检单号
            receiveInfo.setCheckNumber("");
            receiveInfo.setCheckNum(BigDecimal.ZERO);
            receiveInfo.setQualifiedNum(BigDecimal.ZERO);
            // 清除收货明细质检信息
            result = receiveOrderDetailMapper.updateCheckInfo(data.getReceiveNumber());
            if (result <= 0) {
                return AjaxResult.error("收货订单审核驳回失败！");
            }
        } else {
            return AjaxResult.error("参数格式错误");
        }
        result = receiveOrderInfoMapper.updateReceiveStatus(receiveInfo);

        if (result <= 0) {
            return AjaxResult.error("收货订单审核失败！");
        }
        if ("adopt".equals(action)) {
            // 采购入库-在途转可用
            String purchaseType = data.getPurchaseType();
            List<SkStockRequest> stockRequests = Lists.newArrayList();
            String supplierCode = data.getSupplierCode();
            List<ReceiveOrderDetail> receiveDetails = data.getReceiveOrderDetails();
            if (CollectionUtils.isEmpty(receiveDetails)) {
                return AjaxResult.error("收货订单详情不能为空！");
            }
            BigDecimal totalTransactionAmount = BigDecimal.ZERO;
            for (ReceiveOrderDetail detail : receiveDetails) {
                SkStockRequest stock = new SkStockRequest();
                stock.setSupplierCode(supplierCode);
                stock.setCode(purchaseType);
                stock.setName(StockEnum.codeToName(purchaseType));
                stock.setProduce(detail.getMaterielNumber());
                stock.setProduceName(detail.getMaterielName());
                stock.setOperation(StockChangeEnum.PURCHASE_INPUT.getType());
                stock.setOperationName(StockChangeEnum.PURCHASE_INPUT.getDesc());
                BigDecimal receiveNum = detail.getReceiveNum();
                stock.setChangeNum(receiveNum);
                BigDecimal checkNum = detail.getCheckNum();
                checkNum = checkNum == null ? BigDecimal.ZERO : checkNum;
                BigDecimal qualifiedNum = detail.getQualifiedNum();
                qualifiedNum = qualifiedNum == null ? BigDecimal.ZERO : qualifiedNum;
                stock.setStockAvailableNum(receiveNum.subtract(checkNum.subtract(qualifiedNum)));
                stock.setStockFaultyNum(checkNum.subtract(qualifiedNum));
                totalTransactionAmount = detail.getSubtotalAmount().add(totalTransactionAmount);
                stockRequests.add(stock);
            }
            // 新增或者更新供应商采购付款汇总
            PurchasePayOrderInfo payInfo = new PurchasePayOrderInfo();
            payInfo.setSupplierCode(data.getSupplierCode());
            List<PurchasePayOrderInfo> payInfos = payOrderManageService.selectPurchasePayOrderInfoList(payInfo);
            PurchasePayOrderInfo req;
            if (CollectionUtils.isNotEmpty(payInfos)) {
                req = payInfos.get(0);
                // 累计交易金额
                BigDecimal totalTradingAmount = req.getTotalTransactionAmount();
                totalTradingAmount = totalTradingAmount.add(totalTransactionAmount);
                BigDecimal payableAmount = req.getPayableAmount();
                payableAmount = payableAmount.add(totalTransactionAmount);
                req.setLastTradingTime(DateUtils.getNowDate());
                req.setPayableAmount(payableAmount);
                req.setTotalTransactionAmount(totalTradingAmount);
                result = payOrderManageService.updatePurchasePayOrderInfo(req);
            } else {
                req = new PurchasePayOrderInfo();
                req.setLastTradingTime(DateUtils.getNowDate());
                req.setPayableAmount(totalTransactionAmount);
                req.setTotalTransactionAmount(totalTransactionAmount);
                req.setTotalPayAmount(BigDecimal.ZERO);
                req.setSupplierCode(data.getSupplierCode());
                req.setCreateBy(SecurityUtils.getUsername());
                req.setCreateTime(DateUtils.getNowDate());
                result = payOrderManageService.insertPurchasePayOrderInfo(req);
            }
            if (result <= 0) {
                return AjaxResult.error("采购付款汇总更新失败！");
            }
            skStockService.insertPurchaseStock(stockRequests);
        }
        return AjaxResult.success();
    }

    @Override
    public List<ReceiveOrderInfo> selectListForPay(ReceiveOrderInfo receiveOrderInfo) {
        return receiveOrderInfoMapper.selectListForPay(receiveOrderInfo);
    }

    @Override
    public List<Map<String, Object>> selectDetailListForPay(ReceiveOrderInfo receiveOrderInfo) {
        return receiveOrderDetailMapper.selectDetailListForPay(receiveOrderInfo);
    }

}
