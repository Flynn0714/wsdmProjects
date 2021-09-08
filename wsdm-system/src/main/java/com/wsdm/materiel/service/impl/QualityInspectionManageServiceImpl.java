package com.wsdm.materiel.service.impl;

import com.google.common.collect.Lists;
import com.wsdm.common.constant.Constants;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.ReceiveOrderStatus;
import com.wsdm.common.enums.ResultEnums;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.StringUtils;
import com.wsdm.materiel.domain.*;
import com.wsdm.materiel.mapper.QualityInspectionDetailMapper;
import com.wsdm.materiel.mapper.QualityInspectionInfoMapper;
import com.wsdm.materiel.mapper.ReceiveOrderDetailMapper;
import com.wsdm.materiel.mapper.ReceiveOrderInfoMapper;
import com.wsdm.materiel.service.QualityInspectionManageService;
import com.wsdm.utils.CodeUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 物料收货质检单信息Service业务层处理
 *
 * @author wanglei
 * @date 2021-06-20
 */
@Service
public class QualityInspectionManageServiceImpl implements QualityInspectionManageService {

    @Autowired
    private QualityInspectionInfoMapper qualityInspectionInfoMapper;
    @Autowired
    private QualityInspectionDetailMapper qualityInspectionDetailMapper;
    @Autowired
    private ReceiveOrderInfoMapper receiveOrderInfoMapper;
    @Autowired
    private ReceiveOrderDetailMapper receiveOrderDetailMapper;

    /**
     * 查询物料收货质检单信息
     *
     * @param id 物料收货质检单信息ID
     * @return 物料收货质检单信息
     */
    @Override
    public QualityInspectionInfoVo selectQualityInspectionInfoById(Long id) {
        QualityInspectionInfoVo infoVo = qualityInspectionInfoMapper.selectQualityInspectionInfoById(id);
        if (infoVo == null) {
            return null;
        }
        infoVo.setQualityInspectionDetails(qualityInspectionDetailMapper.selectQualityInspectionDetailByCheckNumber(infoVo.getCheckNumber()));
        return infoVo;
    }

    /**
     * 查询物料收货质检单信息列表
     *
     * @param qualityInspectionInfo 物料收货质检单信息
     * @return 物料收货质检单信息
     */
    @Override
    public List<QualityInspectionInfo> selectQualityInspectionInfoList(QualityInspectionInfoVo qualityInspectionInfo) {
        return qualityInspectionInfoMapper.selectQualityInspectionInfoList(qualityInspectionInfo);
    }

    /**
     * 新增物料收货质检单信息
     *
     * @param qualityInspectionInfo 物料收货质检单信息
     * @return 结果
     */
    @Override
    public AjaxResult insertQualityInspectionInfo(QualityInspectionInfoVo qualityInspectionInfo) {
        qualityInspectionInfo.setCreateTime(DateUtils.getNowDate());
        int result = qualityInspectionInfoMapper.insertQualityInspectionInfo(qualityInspectionInfo);
        if (result <= 0) {
            throw new BaseException("新增采购收货质检单失败！");
        }
        result = qualityInspectionDetailMapper.insertQualityInspectionDetailBatch(qualityInspectionInfo.getQualityInspectionDetails());
        if (result <= 0) {
            throw new BaseException("新增采购收货质检单明细失败！");
        }
        return AjaxResult.success();
    }

    /**
     * 修改物料收货质检单信息
     *
     * @param info 物料收货质检单信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateQualityInspectionInfo(QualityInspectionInfoVo info) {
        // 操作类型 1-保存 2-保存提审
        if (info.getId() == null || StringUtils.isEmpty(info.getCheckNumber())) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        List<QualityInspectionDetail> details = info.getQualityInspectionDetails();
        if (CollectionUtils.isEmpty(details)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        String operator = SecurityUtils.getUsername();
        Date now = DateUtils.getNowDate();

        // 合格数量
        BigDecimal totalQualifiedNum = BigDecimal.ZERO;
        // 抽检总数量
        BigDecimal totalCheckNum = BigDecimal.ZERO;
        for (QualityInspectionDetail detail : details) {
            detail.setCheckNumber(info.getCheckNumber());
            if (detail.getCheckNum().compareTo(detail.getQualifiedNum()) < 0) {
                return AjaxResult.error("合格数量不能大于抽检数量");
            }
            totalQualifiedNum = totalQualifiedNum.add(detail.getQualifiedNum());
            totalCheckNum = totalCheckNum.add(detail.getCheckNum());
            detail.setCreateBy(operator);
            detail.setCreateTime(now);
        }
        String percentPass = new BigDecimal("100").multiply(totalQualifiedNum).divide(totalCheckNum, 2, BigDecimal.ROUND_HALF_UP).toString().concat("%");
        info.setPercentPass(percentPass);
        String optType = info.getOptType();
        if (Constants.STRING_2.equals(optType)) {
            info.setUpdateBy(operator);
            info.setUpdateTime(now);
            info.setStatus(ReceiveOrderStatus.WAIT_AUDIT.getCode());
        } else {
            info.setStatus(ReceiveOrderStatus.WAIT_CHECK.getCode());
        }
        int result = qualityInspectionInfoMapper.updateQualityInspectionInfo(info);
        if (result <= 0) {
            throw new BaseException("修改采购收货质检单失败！");
        }
        result = qualityInspectionDetailMapper.deleteQualityInspectionDetailByCheckNumber(info.getCheckNumber());
        if (result <= 0) {
            throw new BaseException("修改采购收货质检单明细失败！");
        }
        result = qualityInspectionDetailMapper.insertQualityInspectionDetailBatch(details);
        if (result <= 0) {
            throw new BaseException("修改采购收货质检单明细失败！");
        }
        return AjaxResult.success();
    }

    /**
     * 批量删除物料收货质检单信息
     *
     * @param ids 需要删除的物料收货质检单信息ID
     * @return 结果
     */
    @Override
    public int deleteQualityInspectionInfoByIds(Long[] ids) {
        return qualityInspectionInfoMapper.deleteQualityInspectionInfoByIds(ids);
    }

    /**
     * 删除物料收货质检单信息信息
     *
     * @param id 物料收货质检单信息ID
     * @return 结果
     */
    @Override
    public int deleteQualityInspectionInfoById(Long id) {
        return qualityInspectionInfoMapper.deleteQualityInspectionInfoById(id);
    }

    /**
     * 获取收货质检单自动生成单号
     *
     * @param
     * @return 结果
     */
    @Override
    public String getCheckNumber() {
        String prefix = Constants.RECEIVE_CHECK_NUMBER_PREFIX + DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date());
        String maxCode = qualityInspectionInfoMapper.getMaxCheckCode(prefix);
        return CodeUtils.getOneCode(prefix, maxCode, 3);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateApproveStatus(Long id, String action, String approvedType) {
        if (null == id || StringUtils.isBlank(action)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        // 查询采购订单状态
        QualityInspectionInfoVo data = selectQualityInspectionInfoById(id);
        if (!ReceiveOrderStatus.WAIT_AUDIT.getCode().equals(data.getStatus())) {
            return AjaxResult.error("收货质检单状态非待审核，不能审核");
        }
        // 审核（已收货）
        QualityInspectionInfo info = new QualityInspectionInfo();
        info.setId(id);
        info.setAuditTime(DateUtils.getNowDate());
        info.setAuditBy(SecurityUtils.getUsername());
        if ("adopt".equals(action)) {
            info.setStatus(ReceiveOrderStatus.AUDITED.getCode());
            // 质检结果 1 建议拒收 2 建议收货
            if (StringUtils.isEmpty(approvedType)) {
                return AjaxResult.error("收货质检单质检结果不能为空！");
            }
            info.setCheckResult(approvedType);
        } else if ("cancel".equals(action)) {
            info.setStatus(ReceiveOrderStatus.WAIT_CHECK.getCode());
        } else {
            return AjaxResult.error("参数格式错误");
        }
        int result = qualityInspectionInfoMapper.updateApproveStatus(info);
        if (result <= 0) {
            return AjaxResult.error("收货质检单审核失败！");
        }
        if ("adopt".equals(action)) {
            ReceiveOrderInfoVo receiveOrderInfoVo = receiveOrderInfoMapper.selectReceiveOrderInfoByCheckNumber(data.getCheckNumber());
            if (null == receiveOrderInfoVo) {
                // 未找到对应收货单
                throw new BaseException("未找到对应的收货单！");
            }
            // 更新收货单状态为待审核
            ReceiveOrderInfoVo receiveInfo = new ReceiveOrderInfoVo();
            receiveInfo.setCheckNumber(data.getCheckNumber());
            receiveInfo.setStatus(ReceiveOrderStatus.WAIT_AUDIT.getCode());
            List<QualityInspectionDetail> details = data.getQualityInspectionDetails();
            String receiveNumber = receiveOrderInfoVo.getReceiveNumber();
            List<ReceiveOrderDetail> receiveOrderDetails = Lists.newArrayList();
            BigDecimal totalCheckNum = BigDecimal.ZERO;
            BigDecimal totalQualifiedNum = BigDecimal.ZERO;
            for (QualityInspectionDetail detail : details) {
                ReceiveOrderDetail orderDetail = new ReceiveOrderDetail();
                orderDetail.setReceiveNumber(receiveNumber);
                orderDetail.setMaterielNumber(detail.getMaterielNumber());
                orderDetail.setFaultyNum(detail.getFaultyNum());
                BigDecimal checkNum = detail.getCheckNum();
                orderDetail.setCheckNum(checkNum);
                BigDecimal qualifiedNum = detail.getQualifiedNum();
                orderDetail.setQualifiedNum(qualifiedNum);
                receiveOrderDetails.add(orderDetail);
                totalCheckNum = totalCheckNum.add(checkNum == null ? BigDecimal.ZERO : checkNum);
                totalQualifiedNum = totalQualifiedNum.add(qualifiedNum == null ? BigDecimal.ZERO : qualifiedNum);
            }
            receiveInfo.setCheckNum(totalCheckNum);
            receiveInfo.setQualifiedNum(totalQualifiedNum);
            receiveInfo.setUpdateBy(SecurityUtils.getUsername());
            receiveInfo.setUpdateTime(DateUtils.getNowDate());
            result = receiveOrderInfoMapper.updateReceiveStatus(receiveInfo);
            if (result <= 0) {
                throw new BaseException("更新采购收货订单状态失败！");
            }
            // 更新收货明细质检数量
            result = receiveOrderDetailMapper.updateReceiveOrderDetailBatch(receiveOrderDetails);
            if (result <= 0) {
                throw new BaseException("更新采购收货订单明细质检数量失败！");
            }
        }
        return AjaxResult.success();
    }

    @Override
    public AjaxResult submitApprove(Long id) {
        if (null == id) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        QualityInspectionInfo info = new QualityInspectionInfo();
        info.setId(id);
        String operator = SecurityUtils.getUsername();
        Date now = DateUtils.getNowDate();
        info.setUpdateBy(operator);
        info.setUpdateTime(now);
        info.setStatus(ReceiveOrderStatus.WAIT_AUDIT.getCode());
        int result = qualityInspectionInfoMapper.updateStatusById(info);
        if (result <= 0) {
            throw new BaseException("收货质检单提交审核失败！");
        }
        return AjaxResult.success();
    }

    @Override
    public QualityInspectionInfo selectQualityInspectionInfoByCheckNumber(String checkNumber) {
        QualityInspectionInfoVo infoVo = qualityInspectionInfoMapper.selectQualityInspectionInfoByCheckNumber(checkNumber);
        return selectQualityInspectionInfoById(infoVo.getId());
    }
}
