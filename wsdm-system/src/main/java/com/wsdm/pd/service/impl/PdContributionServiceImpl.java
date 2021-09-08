package com.wsdm.pd.service.impl;

import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.pd.constant.PdContributionStatus;
import com.wsdm.pd.constant.Prefix;
import com.wsdm.pd.domain.*;
import com.wsdm.pd.mapper.PdContributionDetailMapper;
import com.wsdm.pd.mapper.PdContributionMapper;
import com.wsdm.pd.mapper.PdContributionTestDetailMapper;
import com.wsdm.pd.mapper.PdContributionTestMapper;
import com.wsdm.pd.service.IPdContributionService;
import com.wsdm.pd.service.IPdContributionTestService;
import com.wsdm.pd.service.ProcessPayOrderInfoService;
import com.wsdm.stock.domain.SkStock;
import com.wsdm.stock.domain.SkStockBatch;
import com.wsdm.stock.service.ISkStockService;
import com.wsdm.utils.CodeUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 工绩单Service业务层处理
 *
 * @author wsdm
 * @date 2021-05-11
 */
@Service
public class PdContributionServiceImpl implements IPdContributionService {
    @Autowired
    private PdContributionMapper pdContributionMapper;
    @Autowired
    private PdContributionDetailMapper pdContributionDetailMapper;
    @Autowired
    private PdContributionTestMapper pdContributionTestMapper;
    @Autowired
    private PdContributionTestDetailMapper pdContributionTestDetailMapper;

    @Autowired
    private ISkStockService skStockService;

    @Autowired
    private IPdContributionTestService pdContributionTestService;

    @Autowired
    private ProcessPayOrderInfoService payOrderInfoService;

    /**
     * 查询工绩单
     *
     * @param id 工绩单ID
     * @return 工绩单
     */
    @Override
    public PdContributionVO selectPdContributionById(Long id) {
        PdContribution pdContribution = pdContributionMapper.selectPdContributionById(id);
        PdContributionVO vo = new PdContributionVO();
        vo.setPdContribution(pdContribution);
        vo.setPdContributionDetailList(listDetail(id));
        return vo;
    }

    /**
     * 查询工绩单列表
     *
     * @param pdContribution 工绩单
     * @return 工绩单
     */
    @Override
    public List<PdContribution> selectPdContributionList(PdContribution pdContribution) {
        return pdContributionMapper.selectPdContributionList(pdContribution);
    }

    /**
     * 新增工绩单
     *
     * @param vo 工绩单
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertPdContribution(PdContributionVO vo) {
        PdContribution pdContribution = vo.getPdContribution();

        validate(vo.getPdContributionDetailList());

        String prefix = Prefix.CONTRIBUTION + DateUtils.parseDateToStr("yyyyMMdd", new Date());
        String latestCode = pdContributionMapper.getLatestCode();
        latestCode = StringUtils.isEmpty(latestCode) ? "" : latestCode;
        latestCode = latestCode.startsWith(prefix) ? latestCode.replaceFirst(prefix, "") : "0";
        pdContribution.setCode(CodeUtils.getOneCode(prefix, latestCode, 3));
        pdContribution.setCreateTime(DateUtils.getNowDate());
        pdContribution.setStatus(PdContributionStatus.WAIT);
        pdContribution.setTest("1");
        pdContribution.setAuditor("");
        pdContribution.setAuditTime(null);
        List<PdContributionDetail> details = vo.getPdContributionDetailList();
        BigDecimal total = BigDecimal.ZERO;
        for (PdContributionDetail detail : details) {
            total = total.add(detail.getQuantity());
        }
        pdContribution.setTotalQuantity(total);
        pdContributionMapper.insertPdContribution(pdContribution);

        for (PdContributionDetail detail : details) {
            detail.setContributionId(pdContribution.getId());
            detail.setCostTimeTotal(detail.getQuantity().multiply(detail.getCostTime()));
            detail.setStatus("1");
            detail.setCreateBy(pdContribution.getCreateBy());
            detail.setCreateTime(new Date());
            pdContributionDetailMapper.insertPdContributionDetail(detail);
        }

        if ("1".equals(vo.getAuditType())) {
            submit(pdContribution.getId(), pdContribution.getCreateBy());
        }
        return 1;
    }

    /**
     * 修改工绩单
     *
     * @param vo 工绩单
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updatePdContribution(PdContributionVO vo) {
        PdContribution pdContribution = vo.getPdContribution();
        //判断是否存在、待审批
        PdContribution old = pdContributionMapper.selectPdContributionById(pdContribution.getId());
        if (old == null) {
            return 0;
        }
        if (!PdContributionStatus.WAIT.equals(old.getStatus())) {
            return 0;
        }

        validate(vo.getPdContributionDetailList());

        pdContribution.setUpdateTime(DateUtils.getNowDate());
        pdContribution.setStatus(PdContributionStatus.WAIT);
        pdContribution.setTest("1");
        pdContribution.setAuditor("");
        pdContribution.setAuditTime(null);
        List<PdContributionDetail> details = vo.getPdContributionDetailList();
        BigDecimal total = BigDecimal.ZERO;
        for (PdContributionDetail detail : details) {
            total = total.add(detail.getQuantity());
        }
        pdContribution.setTotalQuantity(total);
        pdContributionMapper.updatePdContribution(pdContribution);


        List<PdContributionDetail> oldDetails = listDetail(pdContribution.getId());
        List<Long> deleting = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(oldDetails)) {
            deleting.addAll(oldDetails.stream().map(PdContributionDetail::getId).collect(Collectors.toList()));
        }
        for (PdContributionDetail detail : details) {
            detail.setCostTimeTotal(detail.getQuantity().multiply(detail.getCostTime()));
            detail.setUpdateBy(pdContribution.getUpdateBy());
            detail.setUpdateTime(new Date());
            if (pdContributionDetailMapper.updatePdContributionDetail(detail) <= 0) {
                detail.setContributionId(pdContribution.getId());
                detail.setStatus("1");
                detail.setCreateBy(pdContribution.getUpdateBy());
                detail.setCreateTime(new Date());
                pdContributionDetailMapper.insertPdContributionDetail(detail);
                continue;
            }
            deleting.remove(detail.getId());
        }

        for (Long detailId : deleting) {
            PdContributionDetail param = new PdContributionDetail();
            param.setId(detailId);
            param.setStatus("0");
            param.setUpdateBy(pdContribution.getUpdateBy());
            param.setUpdateTime(new Date());
            pdContributionDetailMapper.updatePdContributionDetail(param);
        }

        if ("1".equals(vo.getAuditType())) {
            submit(pdContribution.getId(), pdContribution.getCreateBy());
        }
        return 1;
    }

    @Override
    public int deleteSafe(Long id, String userId) {
        PdContribution pdContribution = pdContributionMapper.selectPdContributionById(id);
        if (!PdContributionStatus.SUBMIT.equals(pdContribution.getStatus())) {
            return 0;
        }
        pdContribution = new PdContribution();
        pdContribution.setId(id);
        pdContribution.setStatus(PdContributionStatus.DELETE);
        pdContribution.setUpdateBy(userId);
        pdContribution.setUpdateTime(new Date());
        return pdContributionMapper.updatePdContribution(pdContribution);
    }

    @Override
    public int recover(Long id, String userId) {
        PdContribution pdContribution = pdContributionMapper.selectPdContributionById(id);
        if (!PdContributionStatus.DELETE.equals(pdContribution.getStatus())) {
            return 0;
        }
        pdContribution = new PdContribution();
        pdContribution.setId(id);
        pdContribution.setStatus(PdContributionStatus.SUBMIT);
        pdContribution.setUpdateBy(userId);
        pdContribution.setUpdateTime(new Date());
        return pdContributionMapper.updatePdContribution(pdContribution);
    }

    @Override
    public Long syncToContributionQuantities(Long testId, String username) {
        PdContributionTest test = pdContributionTestMapper.selectPdContributionTestById(testId);
        if (test == null) {
            return null;
        }
        PdContribution param0 = new PdContribution();
        param0.setCode(test.getCode());
        param0.setTestCode(test.getTestCode());
        List<PdContribution> contributionList = pdContributionMapper.selectPdContributionList(param0);
        if (CollectionUtils.isEmpty(contributionList)) {
            return null;
        }

        Long id = contributionList.get(0).getId();
        List<PdContributionDetail> details = listDetail(id);
        if (CollectionUtils.isEmpty(details)) {
            return id;
        }

        PdContributionTestDetail param = new PdContributionTestDetail();
        param.setTestId(testId);
        param.setStatus("1");
        List<PdContributionTestDetail> testDetails = pdContributionTestDetailMapper.selectPdContributionTestDetailList(param);
        if (CollectionUtils.isEmpty(testDetails)) {
            return id;
        }
        List<PdContributionDetail> updating = new ArrayList<>();
        for (PdContributionTestDetail testDetail : testDetails) {
            PdContributionDetail detail = CollectionUtils.find(details, detail0 -> detail0.getMaterialCode().equals(testDetail.getMaterialCode()));
            PdContributionDetail o = new PdContributionDetail();
            o.setId(detail.getId());
            o.setQuantityTest(testDetail.getQuantityTest());
            o.setQuantityPass(testDetail.getQuantityPass());
            o.setQuantityFault(testDetail.getQuantityFault());
            o.setQuantityRework(testDetail.getQuantityRework());
            o.setUpdateBy(username);
            o.setUpdateTime(new Date());
            updating.add(o);
        }
        updating.forEach(pdContributionDetailMapper::updatePdContributionDetail);
        return id;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int submit(Long id, String username) {
        PdContribution pdContribution = pdContributionMapper.selectPdContributionById(id);
        if (!PdContributionStatus.WAIT.equals(pdContribution.getStatus())) {
            return 0;
        }
        PdContribution param = new PdContribution();
        param.setId(id);
        if ("1".equals(pdContribution.getTest())) {
            param.setStatus(PdContributionStatus.WAIT_TEST);

            // 生成质检单
            pdContributionTestService.createPdContributionTest(pdContribution, listDetail(id));
        } else {
            param.setStatus(PdContributionStatus.SUBMIT);
        }
        param.setUpdateBy(username);
        param.setUpdateTime(new Date());
        pdContributionMapper.updatePdContribution(param);

        // 增加在途
        addStockTransitNum(pdContribution, username);

        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int pass(Long id, String username) {
        PdContribution pdContribution = pdContributionMapper.selectPdContributionById(id);
        if (!PdContributionStatus.SUBMIT.equals(pdContribution.getStatus())) {
            return 0;
        }

        PdContribution param = new PdContribution();
        param.setId(id);
        param.setStatus(PdContributionStatus.PASS);
        param.setUpdateBy(username);
        param.setUpdateTime(new Date());
        param.setAuditor(username);
        param.setAuditTime(new Date());
        pdContributionMapper.updatePdContribution(param);

        // 调整库存
        adjustStock(pdContribution, username);
        payOrderInfoService.add(id);

        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int reject(Long id, String username) {
        PdContribution pdContribution = pdContributionMapper.selectPdContributionById(id);
        if (!PdContributionStatus.SUBMIT.equals(pdContribution.getStatus())) {
            return 0;
        }
        // 扣除在途
        removeStockTransitNum(pdContribution, username);

        PdContribution param = new PdContribution();
        param.setId(id);
        param.setStatus(PdContributionStatus.WAIT);
        param.setUpdateBy(username);
        param.setUpdateTime(new Date());
        param.setAuditor(username);
        param.setAuditTime(new Date());

        pdContributionMapper.updatePdContribution(param);

        List<PdContributionDetail> details = listDetail(id);
        if (CollectionUtils.isNotEmpty(details)) {
            List<PdContributionDetail> updating = new ArrayList<>();
            for (PdContributionDetail detail : details) {
                PdContributionDetail param0 = new PdContributionDetail();
                param0.setId(detail.getId());
                param0.setQuantityTest(BigDecimal.ZERO);
                param0.setQuantityPass(BigDecimal.ZERO);
                param0.setQuantityFault(BigDecimal.ZERO);
                param0.setQuantityRework(BigDecimal.ZERO);
                param0.setUpdateBy(username);
                param0.setUpdateTime(new Date());
                updating.add(param0);
            }
            updating.forEach(pdContributionDetailMapper::updatePdContributionDetail);
        }
        return 1;
    }

    @Override
    public void setToSubmit(Long id, String username) {
        PdContribution param = new PdContribution();
        param.setId(id);
        param.setStatus(PdContributionStatus.SUBMIT);
        param.setUpdateBy(username);
        param.setUpdateTime(new Date());
        pdContributionMapper.updatePdContribution(param);
    }

    private List<PdContributionDetail> listDetail(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("need id");
        }
        PdContributionDetail param = new PdContributionDetail();
        param.setContributionId(id);
        param.setStatus("1");
        return pdContributionDetailMapper.selectPdContributionDetailList(param);
    }

    private void validate(List<PdContributionDetail> details) {
        if (CollectionUtils.isEmpty(details)) {
            throw new BaseException("请至少提供一个工绩单明细。");
        }

        Set<String> codeSet = new HashSet<>();
        for (PdContributionDetail detail : details) {
            //校验客户产品是否重复
            String key = detail.getMaterialCode();
            if (codeSet.contains(key)) {
                throw new BaseException("物料编号：" + detail.getMaterialCode() + "不得重复。");
            }
            codeSet.add(key);
        }
    }

    private void addStockTransitNum(PdContribution contribution, String operator) {
        List<PdContributionDetail> detailList = listDetail(contribution.getId());
        if (CollectionUtils.isEmpty(detailList)) {
            return;
        }

        for (PdContributionDetail detail : detailList) {
            SkStock param = new SkStock();
            param.setCode(contribution.getMaterialType());
            param.setName(contribution.getMaterialTypeName());
            param.setProduce(detail.getMaterialCode());
            param.setProduceName(detail.getMaterialName());
            param.setStockTransitNum(detail.getQuantity());
            param.setUpdateBy(operator);
            skStockService.addStockTransitNum(param);
        }
    }

    private void removeStockTransitNum(PdContribution contribution, String operator) {
        List<PdContributionDetail> detailList = listDetail(contribution.getId());
        if (CollectionUtils.isEmpty(detailList)) {
            return;
        }

        for (PdContributionDetail detail : detailList) {
            skStockService.removeStockTransitNum(contribution.getMaterialType(), detail.getMaterialCode(), detail.getQuantity(), operator);
        }
    }

    private void adjustStock(PdContribution contribution, String operator) {
        List<PdContributionDetail> detailList = listDetail(contribution.getId());
        if (CollectionUtils.isEmpty(detailList)) {
            return;
        }

        for (PdContributionDetail detail : detailList) {
            SkStockBatch stockBatch = new SkStockBatch();
            stockBatch.setStockCode(contribution.getMaterialType());
            stockBatch.setStockName(contribution.getMaterialTypeName());
            stockBatch.setProduceName(detail.getMaterialName());
            stockBatch.setProduce(detail.getMaterialCode());
            if ("1".equals(contribution.getTest())) {
                stockBatch.setBatchTotal(detail.getQuantityPass().add(detail.getQuantityFault()));
                stockBatch.setStockFaultyNum(detail.getQuantityFault());
                stockBatch.setStockAvailableNum(detail.getQuantityPass());
            } else {
                stockBatch.setStockAvailableNum(detail.getQuantity());
            }
            stockBatch.setCreateBy(operator);
            skStockService.addStock(stockBatch);

            skStockService.removeStockTransitNum(contribution.getMaterialType(), detail.getMaterialCode(), detail.getQuantity(), operator);
        }
    }
}
