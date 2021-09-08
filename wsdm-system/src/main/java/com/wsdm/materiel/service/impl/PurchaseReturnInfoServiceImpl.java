package com.wsdm.materiel.service.impl;

import com.google.common.collect.Lists;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.*;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.StringUtils;
import com.wsdm.materiel.domain.*;
import com.wsdm.materiel.mapper.PurchaseReturnDetailMapper;
import com.wsdm.materiel.mapper.PurchaseReturnInfoMapper;
import com.wsdm.materiel.service.PurchasePayOrderManageService;
import com.wsdm.materiel.service.PurchaseReturnInfoManageService;
import com.wsdm.stock.domain.SkStockRequest;
import com.wsdm.stock.service.ISkStockService;
import com.wsdm.utils.CodeUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Auther: wjj
 * @Date: 2021/7/14 15:28
 * @Description: 采购退货信息列表
 */
@Service
public class PurchaseReturnInfoServiceImpl implements PurchaseReturnInfoManageService {
    @Resource
    private PurchaseReturnInfoMapper purchaseReturnInfoMapper;
    @Resource
    private PurchaseReturnDetailMapper purchaseReturnDetailMapper;

    @Resource
    private ISkStockService skStockService;
    @Autowired
    private PurchasePayOrderManageService payOrderManageService;


    /**
     * @Description: 查询采购退货信息列表
     * @Param:
     * @return: list结果集
     */
    public List<PurchaseReturnInfo> selectPurchaseReturnInfoList(PurchaseReturnInfoVo purchaseReturnInfoVo) {
        return purchaseReturnInfoMapper.selectPurchaseReturnInfoList(purchaseReturnInfoVo);
    }



    /**
     * @param purchaseReturnInfo
     * @Description: 选择采购收获单
     * @Param:
     * @return:
     */
    @Override
    public List<PurchaseReturnInfo> selectByReturnForReceive(PurchaseReturnInfo purchaseReturnInfo) {

        List<PurchaseReturnInfo> purchaseReturnInfos = purchaseReturnInfoMapper.selectPurchaseReturnForReceiveList(purchaseReturnInfo);

        List<PurchaseReturnInfo> removeObject = new ArrayList<>();

        if (!Objects.isNull(purchaseReturnInfos)) {
            for (PurchaseReturnInfo vo : purchaseReturnInfos) {
                Integer integer = purchaseReturnDetailMapper.countReturnQuantity(vo.getReceiveNumber());
                if(Objects.isNull(integer)) integer=0;
                vo.setReturnQuantity(new BigDecimal(integer));
                BigDecimal receiveQuantity = vo.getReceiveQuantity();
                //已退货数量
                BigDecimal returnQuantity = vo.getReturnQuantity();
                if(!Objects.isNull(receiveQuantity) && !Objects.isNull(returnQuantity)){
                    if (receiveQuantity.longValue() > returnQuantity.longValue()) {
                        removeObject.add(vo);
                    }
                }
            }
        }
        return removeObject;
    }



    /**
     * @param purchaseReturnInfo
     * @Description: 新增采购退货单信息
     * @Param:
     * @return:
     */
    @Transactional
    @Override
    public AjaxResult insertPurchaseReturnInfo(PurchaseReturnInfo purchaseReturnInfo) {
        //判断采购退货详情表集合是否为空
        List<PurchaseReturnDetail> purchaseReturnDetails = purchaseReturnInfo.getPurchaseReturnDetails();
        if (CollectionUtils.isEmpty(purchaseReturnDetails)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        Date now = DateUtils.getNowDate();
        String optUserName = SecurityUtils.getUsername();
        // 生成退货单号
        String returnPrefix = "CT" + DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date());
        String maxCode = purchaseReturnInfoMapper.getMaxReturnCode(returnPrefix);
        //赋值
        purchaseReturnInfo.setCreateTime(now);
        purchaseReturnInfo.setReturnDate(now);
        purchaseReturnInfo.setCreateBy(optUserName);
        // optType 操作类型 0 保存（编辑） 1 保存（编辑）提交
        String optChannel = purchaseReturnInfo.getOptType();
        purchaseReturnInfo.setStatus(STRING_0.equals(optChannel) ? ReturnOrderStatus.NOT_SUBMIT.getCode() : ReturnOrderStatus.WAIT_APPROVE.getCode());
        purchaseReturnInfo.setReturnNumber(CodeUtils.getOneCode(returnPrefix, maxCode, 3));

        int result = purchaseReturnInfoMapper.insertPurchaseReturnOrderInfo(purchaseReturnInfo);
        if (result <= 0) {
            throw new BaseException("退货订单新增失败！");
        }
        List<String> returnList = Lists.newArrayList();
        int count = 0;
        for (int i = 0; i < purchaseReturnDetails.size(); i++) {
            PurchaseReturnDetail detail = purchaseReturnInfo.getPurchaseReturnDetails().get(i);
            detail.setItemNo(i + 1);
            if(returnList.contains(detail.getMaterialNumber())){
                return AjaxResult.error("退货物料重复");
            }else {
                returnList.add(detail.getMaterialNumber());
            }
            detail.setReturnNumber(purchaseReturnInfo.getReturnNumber());
            detail.setCreateBy(optUserName);
            detail.setCreateTime(now);
            BigDecimal returnQuantity = detail.getReturnQuantity();
            returnQuantity = null == returnQuantity ? BigDecimal.ZERO : returnQuantity;
            detail.setReturnQuantity(returnQuantity);
            count += returnQuantity.intValue();
            if (detail.getReceivedQuantity().compareTo(returnQuantity) == -1) {
                throw new BaseException("退货数量不能大于收货数量！");
            }
        }

        if (STRING_1.equals(optChannel) && count == 0) {
            throw new BaseException("退货数量总额为0，不能提交审核！");
        }
        if(STRING_0.equals(optChannel) && count == 0){
            throw new BaseException("退货数量总额为0，不能创建！");
        }
        result = purchaseReturnDetailMapper.insertReturnDetailBatch(purchaseReturnDetails);
        if (result <= 0) {
            throw new BaseException("退货订单详情新增失败！");
        }
        return AjaxResult.success();
    }



    /**
     * @param purchaseReturnInfo
     * @Description: 修改退货单信息
     * @Param: purchaseReturnInfo 退货单信息对象
     * @return:
     */
    @Override
    public AjaxResult updatePurchaseReturnInfo(PurchaseReturnInfo purchaseReturnInfo) {
        if (StringUtils.isEmpty(purchaseReturnInfo.getReturnNumber()) || null == purchaseReturnInfo.getId()) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        int result;
        // optType 操作类型 0 保存（编辑） 1 保存（编辑）提交 2 详情页提交审核
        String optChannel = purchaseReturnInfo.getOptType();
        Date now = DateUtils.getNowDate();
        String optUserName = SecurityUtils.getUsername();
        purchaseReturnInfo.setUpdateBy(optUserName);
        purchaseReturnInfo.setUpdateTime(now);
        if (STRING_0.equals(optChannel) || STRING_1.equals(optChannel)) {
            // 根据退货订单id查询退货订单信息
            PurchaseReturnInfo data = purchaseReturnInfoMapper.selectReturnOrderInfoById(purchaseReturnInfo.getId());
            //状态为0和1的才能修改
            if ( !ReturnOrderStatus.NOT_SUBMIT.getCode().equals(data.getStatus()) && !ReturnOrderStatus.CANCEL.getCode().equals(data.getStatus())) {
                return AjaxResult.error("退货订单状态非未提交或已作废，不能修改");
            }
            purchaseReturnInfo.setStatus(STRING_0.equals(optChannel) ? data.getStatus() : ReturnOrderStatus.WAIT_APPROVE.getCode());
            //得到退货单详情表

            List<PurchaseReturnDetail> purchaseReturnDetails = purchaseReturnInfo.getPurchaseReturnDetails();
            if (CollectionUtils.isEmpty(purchaseReturnDetails)) {
                return AjaxResult.error(ResultEnums.NULL_PARAM);
            }
            List<String> returnList = Lists.newArrayList();
            int count = 0;
            for (int i = 0; i < purchaseReturnDetails.size(); i++) {
                PurchaseReturnDetail detail = purchaseReturnDetails.get(i);
                detail.setItemNo(i + 1);
                if(returnList.contains(detail.getMaterialNumber())){
                    return AjaxResult.error("退货物料重复");
                }else {
                    returnList.add(detail.getMaterialNumber());
                }
                detail.setCreateBy(optUserName);
                detail.setCreateTime(now);
                detail.setReturnNumber(purchaseReturnInfo.getReturnNumber());
                BigDecimal returnQuantity = detail.getReturnQuantity();
                returnQuantity = null == returnQuantity ? BigDecimal.ZERO : returnQuantity;
                detail.setReturnQuantity(returnQuantity);
                count += returnQuantity.intValue();
                if (detail.getReceivedQuantity().compareTo(returnQuantity) == -1) {
                    return AjaxResult.error("退货数量不能大于收货数量！");
                }
            }
            if (STRING_1.equals(optChannel) && count == 0) {
                throw new BaseException("退货数量总额为0，不能提交审核！");
            }
            if(STRING_0.equals(optChannel) && count == 0){
                    throw new BaseException("退货数量总额为0，不能保存修改！");
            }
            //修改退货订单信息
            System.err.println(purchaseReturnInfo);
            result = purchaseReturnInfoMapper.updatePurchaseReturnInfo(purchaseReturnInfo);
            if (result <= 0) {
                throw new BaseException("退货订单修改失败！");
            }
            // 删除退货订单详情
            result = purchaseReturnDetailMapper.deleteReturnDetail(purchaseReturnInfo.getReturnNumber());
            if (result <= 0) {
                throw new BaseException("退货订单详情修改失败！");
            }

            result = purchaseReturnDetailMapper.insertReturnDetailBatch(purchaseReturnDetails);
            if (result <= 0) {
                throw new BaseException("退货订单详情修改失败！");
            }
        }
        return AjaxResult.success();
    }



    /**
     * 查询退货订单信息
     *
     * @param id 退货订单信息ID
     * @return 退货订单信息
     */
    @Override
    public PurchaseReturnInfo selectReturnOrderInfoById(Long id) {
        PurchaseReturnInfo purchaseReturnInfo = purchaseReturnInfoMapper.selectReturnOrderInfoById(id);
        if (null == purchaseReturnInfo || StringUtils.isEmpty(purchaseReturnInfo.getReturnNumber())) {
            return purchaseReturnInfo;
        }
        List<PurchaseReturnDetail> details = purchaseReturnDetailMapper.selectReturnOrderDetailByReturnNumber(purchaseReturnInfo.getReturnNumber());
        if (CollectionUtils.isNotEmpty(details)) {
            for (PurchaseReturnDetail detail : details) {
                // 合同编号
                String contractNumber = detail.getContractNumber();
                // 物料编号
                String materialNumber = detail.getMaterialNumber();
                int returnedQuantity = getReturnedNum(purchaseReturnInfo.getReceiveNumber(), contractNumber, materialNumber);
                // 已退货数量
                detail.setReturnedQuantity(new BigDecimal(returnedQuantity));
            }
        }
        purchaseReturnInfo.setPurchaseReturnDetails(details);
        return purchaseReturnInfo;
    }

    private int getReturnedNum(String receiveNumber, String contractNumber, String materialNumber) {
        PurchaseReturnDetail detail = new PurchaseReturnDetail();
        detail.setContractNumber(contractNumber);
        detail.setMaterialNumber(materialNumber);
        // 已退货数量
        Integer returnedNum = purchaseReturnDetailMapper.queryReturnedNum(receiveNumber, detail);
        return null == returnedNum ? 0 : returnedNum;
    }



    /**
    * @Description: 根据退货单号查询退货单信息
    * @Param:
    * @return:
    */
    @Override
    public PurchaseReturnInfoVo selectReturnOrderInfoByReturnNumber(String returnNumber) {
        //根据id查询退货单信息
        PurchaseReturnInfoVo purchaseReturnInfo = purchaseReturnInfoMapper.selectReturnOrderInfoByReturnNumber(returnNumber);
        if (null == purchaseReturnInfo || StringUtils.isEmpty(purchaseReturnInfo.getReturnNumber())) {
            return purchaseReturnInfo;
        }
        //根据退货单号查询退货单详情信息
        List<PurchaseReturnDetail> details = purchaseReturnDetailMapper.selectReturnOrderDetailByReturnNumber(purchaseReturnInfo.getReturnNumber());
        if (CollectionUtils.isNotEmpty(details)) {
            for (PurchaseReturnDetail detail : details) {
                // 合同编号
                String contractNumber = detail.getContractNumber();
                // 物料编号
                String materialNumber = detail.getMaterialNumber();
                int returnedQuantity = getReturnedNum(purchaseReturnInfo.getReceiveNumber(), contractNumber, materialNumber);
                // 已退货数量
                detail.setReturnQuantity(new BigDecimal(returnedQuantity));
            }
        }
        purchaseReturnInfo.setPurchaseReturnDetails(details);
        return purchaseReturnInfo;
    }


    /**
     * @Description: 根据退货单id删除退货单信息
     * @Param: id
     * @return:
     */
    @Override
    public int deleteReturnOrderInfoById(Long id) {
        return purchaseReturnInfoMapper.deleteReturnOrderInfoById(id);
    }



    /**
     * 审核采购退货单信息
     *
     * @param id
     * @param action
     * @return
     */
    @Override
    @Transactional
    public AjaxResult updateApproveStatus(Long id, String action) {
        if (null == id || StringUtils.isBlank(action)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        // 查询退货订单状态
        PurchaseReturnInfo data = selectReturnOrderInfoById(id);
        if (!ReturnOrderStatus.WAIT_APPROVE.getCode().equals(data.getStatus())) {
            return AjaxResult.error("退货订单状态非待审核，不能审核");
        }
        // 查询详情信息
        List<PurchaseReturnDetail> returnDetails = data.getPurchaseReturnDetails();
        if (CollectionUtils.isEmpty(returnDetails)) {
            return AjaxResult.error("退货订单详情不能为空！");
        }
        String username = SecurityUtils.getUsername();
        // 审核
        PurchaseReturnInfo returnInfo = new PurchaseReturnInfo();
        returnInfo.setId(id);
        returnInfo.setApproveBy(username);
        returnInfo.setApproveTime(DateUtils.getNowDate());
        if ("adopt".equals(action)) {
            for (PurchaseReturnDetail detail : returnDetails) {
                int returnedQuantity = getReturnedNum(data.getReceiveNumber(), detail.getContractNumber(), detail.getMaterialNumber());
                BigDecimal returnQuantity = detail.getReturnQuantity();
                returnQuantity = null == returnQuantity ? BigDecimal.ZERO : returnQuantity;
                if (detail.getReceivedQuantity().intValue() < (returnedQuantity + returnQuantity.intValue())) {
                    return AjaxResult.error("退货数量不能大于收货数量！");
                }
            }
            returnInfo.setStatus(ReturnOrderStatus.APPROVED.getCode());
        } else if ("cancel".equals(action)) {
            returnInfo.setStatus(ReturnOrderStatus.NOT_SUBMIT.getCode());
        } else {
            return AjaxResult.error("参数格式错误");
        }
        int result = purchaseReturnInfoMapper.updateReturnStatus(returnInfo);
        if (result <= 0) {
            return AjaxResult.error("退款订单审核失败！");
        }
        if ("adopt".equals(action)) {
            // 采购退库-可用减少
            String purchaseType = data.getPurchaseType();
            List<SkStockRequest> stockRequests = Lists.newArrayList();
            String supplierCode = data.getSupplierCode();
            BigDecimal totalTransactionAmount = BigDecimal.ZERO;
            for (int i = 0; i < returnDetails.size(); i++) {
                SkStockRequest stock = new SkStockRequest();
                stock.setSupplierCode(supplierCode);
                stock.setCode(purchaseType);
                stock.setName(StockEnum.codeToName(purchaseType));
                stock.setProduce(returnDetails.get(i).getMaterialNumber());
                stock.setProduceName(returnDetails.get(i).getMaterialName());
                stock.setOperation(StockChangeEnum.PURCHASE_OUT.getType());
                stock.setOperationName(StockChangeEnum.PURCHASE_OUT.getDesc());
                BigDecimal returnQuantity = returnDetails.get(i).getReturnQuantity();
                returnQuantity = null == returnQuantity ? BigDecimal.ZERO : returnQuantity;
                stock.setChangeNum(returnQuantity);
                totalTransactionAmount = totalTransactionAmount.add(BigDecimal.ZERO);
                stockRequests.add(stock);
            }

            // 更新供应商采购付款汇总
            PurchasePayOrderInfo payInfo = new PurchasePayOrderInfo();
            payInfo.setSupplierCode(data.getSupplierCode());
            List<PurchasePayOrderInfo> payInfos = payOrderManageService.selectPurchasePayOrderInfoList(payInfo);
            PurchasePayOrderInfo req;
            if (CollectionUtils.isNotEmpty(payInfos)) {
                req = payInfos.get(0);
                // 累计交易金额
                BigDecimal totalTradingAmount = req.getTotalTransactionAmount();
                totalTradingAmount = totalTradingAmount.subtract(totalTransactionAmount);
                BigDecimal payableAmount = req.getPayableAmount();
                payableAmount = payableAmount.subtract(totalTransactionAmount);
                req.setLastTradingTime(DateUtils.getNowDate());
                req.setPayableAmount(payableAmount);
                req.setTotalTransactionAmount(totalTradingAmount);
                result = payOrderManageService.updatePurchasePayOrderInfo(req);
            }
            if (result <= 0) {
                return AjaxResult.error("采购付款汇总更新失败！");
            }
            skStockService.returnPurchaseStock(stockRequests);
        }
        return AjaxResult.success();
    }



    /**
     * 批量删除退货订单信息
     *
     * @param ids 需要删除的退货订单信息ID
     * @return 结果
     */
    @Override
    public AjaxResult deleteReturnOrderInfoByIds(Long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        for (Long id : ids) {
            if (id == null) {
                return AjaxResult.error(ResultEnums.NULL_PARAM);
            }
        }
        // 查询采购订单状态
        List<PurchaseReturnInfo> data = purchaseReturnInfoMapper.selectReturnOrderInfoByIds(ids);
        for (PurchaseReturnInfo returnOrderInfo : data) {
            if (!ReturnOrderStatus.NOT_SUBMIT.getCode().equals(returnOrderInfo.getStatus())) {
                return AjaxResult.error("退货订单状态非未提交，不能进行删除操作");
            }
        }
        int result = purchaseReturnInfoMapper.deleteReturnOrderInfoByIds(ids);
        if (result <= 0) {
            throw new BaseException("退货订单作废失败！");
        }
        return AjaxResult.success();
    }


}