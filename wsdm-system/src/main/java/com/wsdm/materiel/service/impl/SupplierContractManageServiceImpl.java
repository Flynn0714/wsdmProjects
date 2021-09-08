package com.wsdm.materiel.service.impl;

import com.google.common.collect.Lists;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.ContractAuditStatus;
import com.wsdm.common.enums.ContractStatus;
import com.wsdm.common.enums.PurchaseTypeEnum;
import com.wsdm.common.enums.ResultEnums;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.StringUtils;
import com.wsdm.materiel.domain.SupplierContract;
import com.wsdm.materiel.domain.SupplierContractDetail;
import com.wsdm.materiel.domain.SupplierContractVo;
import com.wsdm.materiel.mapper.SupplierContractDetailMapper;
import com.wsdm.materiel.mapper.SupplierContractMapper;
import com.wsdm.materiel.service.SupplierContractManageService;
import com.wsdm.utils.CodeUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 供应商合同Service业务层处理
 *
 * @author wanglei
 * @date 2021-04-25
 */
@Service
public class SupplierContractManageServiceImpl implements SupplierContractManageService {

    public static final String STRING_0 = "0";
    public static final String STRING_1 = "1";
    public static final String STRING_2 = "2";
    public static final String STRING_3 = "3";

    @Autowired
    private SupplierContractMapper supplierContractMapper;
    @Autowired
    private SupplierContractDetailMapper supplierContractDetailMapper;

    @Override
    public SupplierContractVo selectSupplierContractById(Long id) {
        SupplierContractVo supplierContractVo = supplierContractMapper.selectSupplierContractById(id);
        if (supplierContractVo == null) {
            return null;
        }
        parseContractStatus(supplierContractVo);
        // 根据合同编号查询合同物料明细
        if (StringUtils.isNotEmpty(supplierContractVo.getContractNumber())) {
            supplierContractVo.setSupplierContractDetails(supplierContractDetailMapper.selectSupplierContractDetailList(supplierContractVo.getContractNumber()));
        }
        return supplierContractVo;
    }

    @Override
    public List<SupplierContractVo> selectSupplierContractList(SupplierContractVo supplierContract) {
        List<SupplierContractVo> supplierContractVos = supplierContractMapper.selectSupplierContractVoList(supplierContract);
        if (CollectionUtils.isEmpty(supplierContractVos)) {
            return supplierContractVos;
        }
        // 合同状态封装
        for (SupplierContractVo contractVo : supplierContractVos) {
            parseContractStatus(contractVo);
        }
        for (SupplierContractVo supplierContractVo : supplierContractVos) {
            String contractNumber = supplierContractVo.getContractNumber();
            if (StringUtils.isNotEmpty(contractNumber)) {
                supplierContractVo.setSupplierContractDetails(supplierContractDetailMapper.selectSupplierContractDetailList(contractNumber));
            }
        }
        return supplierContractVos;
    }

    private void parseContractStatus(SupplierContractVo contractVo) {
        Date startDate = contractVo.getContractStartDate();
        Date endDate = contractVo.getContractEndDate();
        String start = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, startDate) + " 00:00:01";
        String end = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, endDate) + " 23:59:59";
        String auditStatus = contractVo.getAuditStatus();
        String currentTime = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, new Date());
        if (!ContractAuditStatus.APPROVED.getCode().equals(auditStatus)) {
            // 非已审核，为未生效
            contractVo.setContractStatus(ContractStatus.INEFFECTIVE.getCode());
            contractVo.setContractStatusName(ContractStatus.INEFFECTIVE.getDesc());
        } else {
            // 判断当前时间与生效时间
            if (currentTime.compareTo(start) < 0) {
                // 待生效
                contractVo.setContractStatus(ContractStatus.WAIT_EFFECTIVE.getCode());
                contractVo.setContractStatusName(ContractStatus.WAIT_EFFECTIVE.getDesc());
            } else if (currentTime.compareTo(end) > 0) {
                // 已過期
                contractVo.setContractStatus(ContractStatus.EXPIRED.getCode());
                contractVo.setContractStatusName(ContractStatus.EXPIRED.getDesc());
            } else {
                // 生效中
                contractVo.setContractStatus(ContractStatus.EFFECTIVE.getCode());
                contractVo.setContractStatusName(ContractStatus.EFFECTIVE.getDesc());
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult insertSupplierContract(SupplierContractVo supplierContractVo) {
        List<SupplierContractDetail> supplierContractDetails = supplierContractVo.getSupplierContractDetails();
        if (CollectionUtils.isEmpty(supplierContractDetails)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        String createBy = SecurityUtils.getUsername();
        Date createTime = DateUtils.getNowDate();
        supplierContractVo.setCreateBy(createBy);
        supplierContractVo.setCreateTime(createTime);
        // 操作类型 0 保存（编辑） 1 保存（编辑）提交
        String optType = supplierContractVo.getOptType();
        if (STRING_1.equals(optType)) {
            // 待审核
            supplierContractVo.setUpdateBy(createBy);
            supplierContractVo.setUpdateTime(createTime);
            supplierContractVo.setAuditStatus(ContractAuditStatus.NOT_APPROVE.getCode());
        } else {
            // 待提交
            supplierContractVo.setAuditStatus(ContractAuditStatus.NOT_SUBMIT.getCode());
        }
        // 生成合同编号
        String prefix = "C" + DateUtils.parseDateToStr(DateUtils.YYYYMM, new Date());
        String maxCode = supplierContractMapper.getMaxSupplierContractNumber(prefix);
        String contractNumber = CodeUtils.getOneCode(prefix, maxCode, 3);
        supplierContractVo.setContractNumber(contractNumber);
        int result = supplierContractMapper.insertSupplierContract(supplierContractVo);
        if (result <= 0) {
            throw new BaseException("供应商合同新增失败！");
        }
        List<String> wlList = Lists.newArrayList();
        for (SupplierContractDetail detail : supplierContractDetails) {
            if (wlList.contains(detail.getMaterialNumber())) {
                throw new BaseException("物料编码重复");
            } else {
                wlList.add(detail.getMaterialNumber());
            }
            int num = supplierContractDetailMapper.selectRepeatDetailCount(detail.getMaterialNumber(), supplierContractVo.getSupplierCode(), supplierContractVo.getContractNumber());
            if (num > 0) {
                throw new BaseException("供应商：" + supplierContractVo.getSupplierCode() + "+物料编码：" + detail.getMaterialNumber() + "不允许出现在两个已审核且未过期的合同中");
            }
            detail.setContractNumber(contractNumber);
            // 五金采购拥有规格参数
            detail.setModelParam(PurchaseTypeEnum.HARDWARE.getCode().equals(supplierContractVo.getContractType()) ? detail.getModelParam() : "-");
            detail.setCreateBy(createBy);
            detail.setCreateTime(createTime);
        }
        result = supplierContractDetailMapper.insertSupplierContractDetailBatch(supplierContractDetails);
        if (result <= 0) {
            throw new BaseException("供应商合同物料详情新增失败！");
        }
        return AjaxResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateSupplierContract(SupplierContractVo contractVo) {
        String contractNumber = contractVo.getContractNumber();
        Long id = contractVo.getId();
        if (StringUtils.isEmpty(contractNumber) || null == id) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        // 查询合同状态
        SupplierContractVo data = supplierContractMapper.selectSupplierContractById(id);
        String auditStatus = data.getAuditStatus();
        String updateBy = SecurityUtils.getUsername();
        Date updateTime = DateUtils.getNowDate();
        // 操作类型 0 保存（编辑） 1 保存（编辑）提交
        String optType = contractVo.getOptType();
        if (!ContractAuditStatus.NOT_SUBMIT.getCode().equals(auditStatus)) {
            return AjaxResult.error("合同审核状态非未提交，不能修改");
        }
        String status = "";
        if (STRING_1.equals(optType)) {
            status = ContractAuditStatus.NOT_APPROVE.getCode();
            contractVo.setUpdateBy(updateBy);
            contractVo.setUpdateTime(updateTime);
        } else {
            status = ContractAuditStatus.NOT_SUBMIT.getCode();
        }
        contractVo.setAuditStatus(status);
        List<SupplierContractDetail> details = contractVo.getSupplierContractDetails();
        if (CollectionUtils.isEmpty(details)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        List<String> wlList = Lists.newArrayList();
        for (SupplierContractDetail detail : details) {
            if (wlList.contains(detail.getMaterialNumber())) {
                return AjaxResult.error("物料编码重复");
            } else {
                wlList.add(detail.getMaterialNumber());
            }
            int num = supplierContractDetailMapper.selectRepeatDetailCount(detail.getMaterialNumber(), contractVo.getSupplierCode(), contractVo.getContractNumber());
            if (num > 0) {
                return AjaxResult.error("供应商：" + contractVo.getSupplierCode() + "+物料编码：" + detail.getMaterialNumber() + "不允许出现在两个已审核且未过期的合同中");
            }
            detail.setContractNumber(contractNumber);
            // 五金采购拥有规格参数
            detail.setModelParam(PurchaseTypeEnum.HARDWARE.getCode().equals(contractVo.getContractType()) ? detail.getModelParam() : "-");
            detail.setCreateBy(SecurityUtils.getUsername());
            detail.setCreateTime(DateUtils.getNowDate());
        }
        int result = supplierContractMapper.updateSupplierContract(contractVo);
        if (result <= 0) {
            throw new BaseException("供应商合同修改失败！");
        }
        result = supplierContractDetailMapper.deleteSupplierContractDetailByContractNumber(contractNumber);
        if (result <= 0) {
            throw new BaseException("供应商合同物料明细修改失败！");
        }
        result = supplierContractDetailMapper.insertSupplierContractDetailBatch(details);
        if (result <= 0) {
            throw new BaseException("供应商合同物料明细修改失败！");
        }

        return AjaxResult.success();
    }

    /**
     * 审核供应商合同
     *
     * @param id
     * @return 结果
     */
    @Override
    public AjaxResult updateApproveStatus(Long id, String action) {
        if (null == id || StringUtils.isBlank(action)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        // 查询合同状态
        SupplierContractVo data = selectSupplierContractById(id);
        String auditStatus = data.getAuditStatus();
        // 审核
        if (!ContractAuditStatus.NOT_APPROVE.getCode().equals(auditStatus)) {
            return AjaxResult.error("合同审核状态非待审核，不能进行审核操作");
        }
        SupplierContractVo supplierContractVo = new SupplierContractVo();
        supplierContractVo.setId(id);
        supplierContractVo.setAuditTime(DateUtils.getNowDate());
        supplierContractVo.setAuditBy(SecurityUtils.getUsername());
        if ("adopt".equals(action)) {
            // 已审核
            supplierContractVo.setAuditStatus(ContractAuditStatus.APPROVED.getCode());
            // 判断供应商+物料唯一性
            for (SupplierContractDetail detail : data.getSupplierContractDetails()) {
                int num = supplierContractDetailMapper.selectRepeatDetailCount(detail.getMaterialNumber(), data.getSupplierCode(), data.getContractNumber());
                if (num > 0) {
                    return AjaxResult.error("供应商：" + data.getSupplierCode() + "+物料编码：" + detail.getMaterialNumber() + "物料编码不允许出现在两个已审核且未过期的合同中");
                }
            }
        } else if ("cancel".equals(action)) {
            // 驳回未提交
            supplierContractVo.setAuditStatus(ContractAuditStatus.NOT_SUBMIT.getCode());
        } else {
            return AjaxResult.error("action参数格式错误");
        }
        int result = supplierContractMapper.updateAuditStatus(supplierContractVo);
        return result > 0 ? AjaxResult.success() : AjaxResult.error("合同审核失败");
    }

    @Override
    public AjaxResult deleteSupplierContractByIds(Long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        for (Long id : ids) {
            if (null == id) {
                return AjaxResult.error(ResultEnums.NULL_PARAM);
            }
            SupplierContract data = selectSupplierContractById(id);
            if (!ContractAuditStatus.NOT_SUBMIT.equals(data.getAuditStatus())) {
                return AjaxResult.error("合同审核状态非未提交，不能进行删除操作");
            }
        }
        int result = supplierContractMapper.deleteSupplierContractByIds(ids);
        return result > 0 ? AjaxResult.success() : AjaxResult.error("合同删除失败");
    }

    @Override
    public AjaxResult deleteSupplierContractById(Long id) {
        if (id == null) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        SupplierContract contract = selectSupplierContractById(id);
        if (!ContractAuditStatus.NOT_SUBMIT.getCode().equals(contract.getAuditStatus())) {
            return AjaxResult.error("状态非未提交不可删除！");
        }
        return result(supplierContractMapper.deleteSupplierContractById(id), "合同刪除失败");
    }

    @Override
    public AjaxResult result(int result, String errMsg) {
        return result > 0 ? AjaxResult.success() : AjaxResult.error(errMsg);
    }

    @Override
    public void updateSupplierContractStatus() {
        SupplierContractVo supplierContractVo = new SupplierContractVo();
        supplierContractVo.setAuditStatus(ContractAuditStatus.APPROVED.getCode());
        // 查询已审核状态、有效期状态为待生效且当前时间在合同有效期内的合同数据
        supplierContractVo.setContractStatus(ContractStatus.WAIT_EFFECTIVE.getCode());
        supplierContractVo.setContractEffectiveDate(DateUtils.getNowDate());
        List<SupplierContract> supplierContracts = supplierContractMapper.selectSupplierContractList(supplierContractVo);
        if (CollectionUtils.isNotEmpty(supplierContracts)) {
            for (SupplierContract supplierContract : supplierContracts) {
                // 更新为生效中
                supplierContract.setContractStatus(ContractStatus.EFFECTIVE.getCode());
            }
            supplierContractMapper.updateContractStatusBatch(supplierContracts);
        }
        // 查询已审核状态、有效期状态为待生效且当前时间大于合同有效期止的合同数据
        supplierContracts = supplierContractMapper.selectOverdueSupplierContractList(supplierContractVo);
        if (CollectionUtils.isNotEmpty(supplierContracts)) {
            for (SupplierContract supplierContract : supplierContracts) {
                // 更新为已过期
                supplierContract.setContractStatus(ContractStatus.EXPIRED.getCode());
            }
            supplierContractMapper.updateContractStatusBatch(supplierContracts);
        }
        // 查询已审核状态、有效期状态为生效中且当前时间大于合同有效期止的合同数据
        supplierContractVo.setContractStatus(ContractStatus.EFFECTIVE.getCode());
        supplierContracts = supplierContractMapper.selectOverdueSupplierContractList(supplierContractVo);
        if (CollectionUtils.isNotEmpty(supplierContracts)) {
            for (SupplierContract supplierContract : supplierContracts) {
                // 更新为已过期
                supplierContract.setContractStatus(ContractStatus.EXPIRED.getCode());
            }
            supplierContractMapper.updateContractStatusBatch(supplierContracts);
        }
        // 查询已审核状态、有效期状态为已过期且当前时间在合同有效期内的合同数据
        supplierContractVo.setContractStatus(ContractStatus.EXPIRED.getCode());
        supplierContractVo.setContractEffectiveDate(DateUtils.getNowDate());
        supplierContracts = supplierContractMapper.selectSupplierContractList(supplierContractVo);
        if (CollectionUtils.isNotEmpty(supplierContracts)) {
            for (SupplierContract supplierContract : supplierContracts) {
                // 更新为生效中
                supplierContract.setContractStatus(ContractStatus.EFFECTIVE.getCode());
            }
            supplierContractMapper.updateContractStatusBatch(supplierContracts);
        }
    }

    @Override
    public List<Map<String, Object>> queryPurchaseMaterielList(Map<String, Object> sqlMap) {
        return supplierContractMapper.queryPurchaseMaterielList(sqlMap);
    }

    @Override
    public List<Map<String,Object>> queryListForAutoPurchase(Map<String, Object> sqlMap) {
        return supplierContractMapper.queryListForAutoPurchase(sqlMap);
    }
}
