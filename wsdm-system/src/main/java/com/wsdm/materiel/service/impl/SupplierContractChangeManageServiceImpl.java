package com.wsdm.materiel.service.impl;

import com.google.common.collect.Lists;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.ContractAuditStatus;
import com.wsdm.common.enums.ResultEnums;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.StringUtils;
import com.wsdm.materiel.domain.*;
import com.wsdm.materiel.mapper.SupplierContractChangeDetailMapper;
import com.wsdm.materiel.mapper.SupplierContractChangeMapper;
import com.wsdm.materiel.mapper.SupplierContractDetailMapper;
import com.wsdm.materiel.mapper.SupplierContractMapper;
import com.wsdm.materiel.service.SupplierContractChangeManageService;
import com.wsdm.materiel.service.SupplierContractManageService;
import com.wsdm.utils.CodeUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 供应商合同变更Service业务层处理
 *
 * @author wanglei
 * @date 2021-04-29
 */
@Service
public class SupplierContractChangeManageServiceImpl implements SupplierContractChangeManageService {

    public static final String STRING_0 = "0";
    public static final String STRING_1 = "1";
    public static final String STRING_2 = "2";
    public static final String STRING_3 = "3";

    @Autowired
    private SupplierContractChangeMapper supplierContractChangeMapper;
    @Autowired
    private SupplierContractChangeDetailMapper supplierContractChangeDetailMapper;
    @Autowired
    private SupplierContractManageService supplierContractManageService;
    @Autowired
    private SupplierContractMapper supplierContractMapper;
    @Autowired
    private SupplierContractDetailMapper supplierContractDetailMapper;

    @Override
    public SupplierContractChangeVo selectSupplierContractChangeById(Long id) {
        SupplierContractChangeVo supplierContractChangeVo = supplierContractChangeMapper.selectSupplierContractChangeById(id);
        if (supplierContractChangeVo == null || StringUtils.isEmpty(supplierContractChangeVo.getChangeNumber())) {
            return supplierContractChangeVo;
        }
        // 根据合同变更编号查询合同变更明细
        supplierContractChangeVo.setSupplierContractChangeDetails(supplierContractChangeDetailMapper.selectSupplierContractChangeDetailList(supplierContractChangeVo.getChangeNumber()));
        return supplierContractChangeVo;
    }

    @Override
    public List<SupplierContractChange> selectSupplierContractChangeList(SupplierContractChange supplierContractChange) {
        return supplierContractChangeMapper.selectSupplierContractChangeList(supplierContractChange);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult insertSupplierContractChange(SupplierContractChangeVo contractChangeVo) {
        List<SupplierContractChangeDetail> supplierContractChangeDetails = contractChangeVo.getSupplierContractChangeDetails();
        if (CollectionUtils.isEmpty(supplierContractChangeDetails)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        String createBy = SecurityUtils.getUsername();
        Date now = DateUtils.getNowDate();
        contractChangeVo.setCreateBy(createBy);
        contractChangeVo.setCreateTime(now);
        // 操作类型 0 保存（编辑） 1 保存（编辑）提交
        String optType = contractChangeVo.getOptType();
        if (STRING_1.equals(optType)) {
            // 待审核
            contractChangeVo.setUpdateBy(createBy);
            contractChangeVo.setUpdateTime(now);
            contractChangeVo.setChangeStatus(ContractAuditStatus.NOT_APPROVE.getCode());
        } else {
            // 待提交
            contractChangeVo.setChangeStatus(ContractAuditStatus.NOT_SUBMIT.getCode());
        }
        // 生成合同编号
        String prefix = "BG" + DateUtils.parseDateToStr(DateUtils.YYYYMM, new Date());
        String maxCode = supplierContractChangeMapper.getMaxSupplierContractChangeNumber(prefix);
        String changeNumber = CodeUtils.getOneCode(prefix, maxCode, 3);
        contractChangeVo.setChangeNumber(changeNumber);
        int result = supplierContractChangeMapper.insertSupplierContractChange(contractChangeVo);
        if (result <= 0) {
            throw new BaseException("供应商合同变更新增失败！");
        }
        List<String> wlList = Lists.newArrayList();
        for (SupplierContractChangeDetail detail : supplierContractChangeDetails) {
            if (wlList.contains(detail.getMaterialNumber())) {
                throw new BaseException("物料编码重复");
            } else {
                wlList.add(detail.getMaterialNumber());
            }
            // 判断供应商+物料唯一性
            int num = supplierContractDetailMapper.selectRepeatDetailCount(detail.getMaterialNumber(), contractChangeVo.getSupplierCode(), contractChangeVo.getContractNumber());
            if (num > 0) {
                throw new BaseException("供应商：" + contractChangeVo.getSupplierCode() + "+物料编码：" + detail.getMaterialNumber() + "物料编码不允许出现在两个已审核且未过期的合同中");
            }
            detail.setChangeNumber(changeNumber);
            detail.setCreateBy(createBy);
            detail.setCreateTime(now);
        }
        result = supplierContractChangeDetailMapper.insertSupplierContractChangeDetailBatch(supplierContractChangeDetails);
        if (result <= 0) {
            throw new BaseException("供应商合同变更详情新增失败！");
        }
        return AjaxResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateSupplierContractChange(SupplierContractChangeVo changeVo) {
        String changeNumber = changeVo.getChangeNumber();
        Long id = changeVo.getId();
        if (StringUtils.isEmpty(changeNumber) || null == id) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        // 查询合同状态
        SupplierContractChangeVo data = supplierContractChangeMapper.selectSupplierContractChangeById(id);
        String changeStatus = data.getChangeStatus();
        String updateBy = SecurityUtils.getUsername();
        Date now = DateUtils.getNowDate();
        // 操作类型 0 保存（编辑） 1 保存（编辑）提交
        String optType = changeVo.getOptType();

        if (!ContractAuditStatus.NOT_SUBMIT.getCode().equals(changeStatus)) {
            return AjaxResult.error("合同变更状态非未提交，不能修改");
        }
        if (STRING_1.equals(optType)) {
            // 待审核
            changeVo.setUpdateBy(updateBy);
            changeVo.setUpdateTime(now);
            changeVo.setChangeStatus(ContractAuditStatus.NOT_APPROVE.getCode());
        } else {
            // 待提交
            changeVo.setChangeStatus(ContractAuditStatus.NOT_SUBMIT.getCode());
        }
        List<SupplierContractChangeDetail> details = changeVo.getSupplierContractChangeDetails();
        if (CollectionUtils.isEmpty(details)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        List<String> wlList = Lists.newArrayList();
        for (SupplierContractChangeDetail detail : details) {
            if (wlList.contains(detail.getMaterialNumber())) {
                return AjaxResult.error("物料编码重复");
            } else {
                wlList.add(detail.getMaterialNumber());
            }
            // 判断供应商+物料唯一性
            int num = supplierContractDetailMapper.selectRepeatDetailCount(detail.getMaterialNumber(), changeVo.getSupplierCode(), changeVo.getContractNumber());
            if (num > 0) {
                return AjaxResult.error("供应商：" + changeVo.getSupplierCode() + "+物料编码：" + detail.getMaterialNumber() + "物料编码不允许出现在两个已审核且未过期的合同中");
            }
            detail.setChangeNumber(changeNumber);
            detail.setUpdateBy(updateBy);
            detail.setUpdateTime(now);
        }
        int result = supplierContractChangeMapper.updateSupplierContractChange(changeVo);
        if (result <= 0) {
            throw new BaseException("供应商合同变更修改失败！");
        }
        result = supplierContractChangeDetailMapper.deleteSupplierContractChangeDetailByChangeNumber(changeNumber);
        if (result <= 0) {
            throw new BaseException("供应商合同变更明细修改失败！");
        }
        result = supplierContractChangeDetailMapper.insertSupplierContractChangeDetailBatch(details);
        if (result <= 0) {
            throw new BaseException("供应商合同变更明细修改失败！");
        }
        return AjaxResult.success();
    }

    /**
     * 审核供应商合同变更
     *
     * @param id
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateApproveStatus(Long id, String action) {
        if (null == id || StringUtils.isBlank(action)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        // 查询合同变更状态
        SupplierContractChangeVo data = selectSupplierContractChangeById(id);
        // 审核
        if (!ContractAuditStatus.NOT_APPROVE.getCode().equals(data.getChangeStatus())) {
            return AjaxResult.error("合同变更状态非待审核，不能进行审核操作");
        }
        Date now = DateUtils.getNowDate();
        String operator = SecurityUtils.getUsername();
        SupplierContractChangeVo supplierContractChangeVo = new SupplierContractChangeVo();
        supplierContractChangeVo.setId(id);
        supplierContractChangeVo.setAuditTime(now);
        supplierContractChangeVo.setAuditBy(operator);
        if ("adopt".equals(action)) {
            supplierContractChangeVo.setChangeStatus(ContractAuditStatus.APPROVED.getCode());
        } else if ("cancel".equals(action)) {
            supplierContractChangeVo.setChangeStatus(ContractAuditStatus.NOT_SUBMIT.getCode());
        } else {
            return AjaxResult.error("参数格式错误");
        }
        int result = supplierContractChangeMapper.updateAuditStatus(supplierContractChangeVo);
        if (result <= 0) {
            return AjaxResult.error("合同变更审核失败");
        }
        if ("adopt".equals(action)) {
            // 审核通过，更新合同信息
            SupplierContract supplierContract = new SupplierContract();
            supplierContract.setContractNumber(data.getContractNumber());
            supplierContract.setContractEndDate(data.getContractEndDate());
            supplierContract.setSupplierCode(data.getSupplierCode());
            supplierContract.setContractType(data.getContractType());
            supplierContract.setUpdateBy(operator);
            supplierContract.setUpdateTime(now);
            result = supplierContractMapper.updateSupplierContractChange(supplierContract);
            if (result <= 0) {
                throw new BaseException("供应商合同变更失败！");
            }
            List<SupplierContractChangeDetail> details = data.getSupplierContractChangeDetails();
            List<SupplierContractDetail> supplierContractDetails = Lists.newArrayList();
            for (SupplierContractChangeDetail detail : details) {
                // 判断供应商+物料唯一性
                int num = supplierContractDetailMapper.selectRepeatDetailCount(detail.getMaterialNumber(), data.getSupplierCode(), data.getContractNumber());
                if (num > 0) {
                    throw new BaseException("供应商：" + data.getSupplierCode() + "+物料编码：" + detail.getMaterialNumber() + "物料编码不允许出现在两个已审核且未过期的合同中");
                }
                SupplierContractDetail contractDetail = new SupplierContractDetail();
                contractDetail.setContractNumber(data.getContractNumber());
                contractDetail.setMaterialNumber(detail.getMaterialNumber());
                contractDetail.setMaterialName(detail.getMaterialName());
                contractDetail.setModelParam(detail.getModelParam());
                contractDetail.setPrice(detail.getChangePrice());
                contractDetail.setCreateBy(operator);
                contractDetail.setCreateTime(now);
                supplierContractDetails.add(contractDetail);
            }
            supplierContractDetailMapper.deleteSupplierContractDetailByContractNumber(data.getContractNumber());
            if (result <= 0) {
                throw new BaseException("供应商合同变更失败！");
            }
            supplierContractDetailMapper.insertSupplierContractDetailBatch(supplierContractDetails);
            if (result <= 0) {
                throw new BaseException("供应商合同变更失败！");
            }
        }
        return AjaxResult.success();
    }

    @Override
    public AjaxResult deleteSupplierContractChangeByIds(Long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        for (Long id : ids) {
            if (null == id) {
                return AjaxResult.error(ResultEnums.NULL_PARAM);
            }
            SupplierContractChange data = selectSupplierContractChangeById(id);
            if (!ContractAuditStatus.NOT_SUBMIT.equals(data.getChangeStatus())) {
                return AjaxResult.error("合同变更状态非未提交，不能进行删除操作");
            }
        }
        int result = supplierContractChangeMapper.deleteSupplierContractChangeByIds(ids);
        return result > 0 ? AjaxResult.success() : AjaxResult.error("合同变更删除失败");
    }

    @Override
    public AjaxResult deleteSupplierContractChangeById(Long id) {
        if (id == null) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        SupplierContractChangeVo contractChangeVo = selectSupplierContractChangeById(id);
        if (!ContractAuditStatus.NOT_SUBMIT.getCode().equals(contractChangeVo.getChangeStatus())) {
            return AjaxResult.error("状态非未提交不可删除！");
        }
        return supplierContractManageService.result(supplierContractChangeMapper.deleteSupplierContractChangeById(id), "合同变更刪除失败");
    }
}
