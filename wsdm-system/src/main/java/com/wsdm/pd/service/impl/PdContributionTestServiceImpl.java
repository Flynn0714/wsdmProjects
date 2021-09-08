package com.wsdm.pd.service.impl;

import com.alibaba.fastjson.JSON;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.pd.constant.PdContributionStatus;
import com.wsdm.pd.constant.Prefix;
import com.wsdm.pd.domain.*;
import com.wsdm.pd.mapper.PdContributionMapper;
import com.wsdm.pd.mapper.PdContributionTestDetailMapper;
import com.wsdm.pd.mapper.PdContributionTestMapper;
import com.wsdm.pd.service.IPdContributionService;
import com.wsdm.pd.service.IPdContributionTestService;
import com.wsdm.utils.CodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 加工质检单Service业务层处理
 *
 * @author wsdm
 * @date 2021-06-18
 */
@Service
public class PdContributionTestServiceImpl implements IPdContributionTestService
{
    @Autowired
    private PdContributionTestMapper       pdContributionTestMapper;
    @Autowired
    private PdContributionTestDetailMapper pdContributionTestDetailMapper;
    @Autowired
    private PdContributionMapper           pdContributionMapper;

    @Autowired
    private IPdContributionService pdContributionService;

    /**
     * 查询加工质检单
     *
     * @param id 加工质检单ID
     * @return 加工质检单
     */
    @Override
    public PdContributionTestVO selectPdContributionTestById(Long id)
    {
        PdContributionTestVO vo = new PdContributionTestVO();
        PdContributionTest pdContributionTest = pdContributionTestMapper.selectPdContributionTestById(id);
        if (pdContributionTest == null)
        {
            return vo;
        }
        vo.setPdContributionTest(pdContributionTest);

        PdContributionTestDetail param = new PdContributionTestDetail();
        param.setTestId(pdContributionTest.getId());
        param.setStatus("1");
        vo.setPdContributionTestDetailList(pdContributionTestDetailMapper.selectPdContributionTestDetailList(param));
        return vo;
    }

    /**
     * 查询加工质检单列表
     *
     * @param pdContributionTest 加工质检单
     * @return 加工质检单
     */
    @Override
    public List<PdContributionTest> selectPdContributionTestList(PdContributionTest pdContributionTest)
    {
        return pdContributionTestMapper.selectPdContributionTestList(pdContributionTest);
    }

    /**
     * 修改加工质检单
     *
     * @param vo 加工质检单
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updatePdContributionTest(PdContributionTestVO vo)
    {
        PdContributionTest pdContributionTest = vo.getPdContributionTest();
        PdContributionTest old = pdContributionTestMapper.selectPdContributionTestById(pdContributionTest.getId());
        if (old == null)
        {
            return 0;
        }
        if (!PdContributionStatus.WAIT_TEST.equals(old.getStatus()))
        {
            return 0;
        }

        List<PdContributionTestDetail> details = vo.getPdContributionTestDetailList();
        List<PdContributionTestDetail> updating = new ArrayList<>();
        for (PdContributionTestDetail detail : details)
        {
            PdContributionTestDetail oldDetail = pdContributionTestDetailMapper.selectPdContributionTestDetailById(
                    detail.getId());
            if (oldDetail == null)
            {
                continue;
            }

            PdContributionTestDetail param = new PdContributionTestDetail();
            param.setId(detail.getId());
            param.setQuantityPass(detail.getQuantityPass());
            param.setQuantityReject(oldDetail.getQuantityTest().subtract(detail.getQuantityPass()));
            param.setQuantityFault(detail.getQuantityFault());
            param.setQuantityRework(param.getQuantityReject().subtract(param.getQuantityFault()));
            param.setUpdateBy(pdContributionTest.getUpdateBy());
            param.setUpdateTime(pdContributionTest.getUpdateTime());
            if (param.getQuantityReject().compareTo(BigDecimal.ZERO) < 0)
            {
                throw new BaseException("合格数量不得超过抽检数量！");
            }
            if (param.getQuantityRework().compareTo(BigDecimal.ZERO) < 0)
            {
                throw new BaseException("废弃数量不得超过不合格数量！");
            }
            updating.add(param);
        }

        old.setRemark(pdContributionTest.getRemark());
        old.setUpdateBy(pdContributionTest.getUpdateBy());
        old.setUpdateTime(new Date());
        pdContributionTestMapper.updatePdContributionTest(old);

        updating.forEach(pdContributionTestDetailMapper::updatePdContributionTestDetail);

        if ("1".equals(vo.getAuditType()))
        {
            submit(old.getId(), old.getUpdateBy());
        }

        return 1;
    }

    @Override
    public void createPdContributionTest(PdContribution pdContribution,
            List<PdContributionDetail> pdContributionDetailList)
    {
        PdContributionTest pdContributionTest = JSON.parseObject(JSON.toJSONString(pdContribution),
                PdContributionTest.class);
        pdContributionTest.setId(null);
        String prefix = Prefix.CONTRIBUTION_TEST + DateUtils.parseDateToStr("yyyyMMdd", new Date());
        String latestCode = pdContributionTestMapper.getLatestCode();
        latestCode = StringUtils.isEmpty(latestCode) ? "" : latestCode;
        latestCode = latestCode.startsWith(prefix) ? latestCode.replaceFirst(prefix, "") : "0";
        pdContributionTest.setTestCode(CodeUtils.getOneCode(prefix, latestCode, 3));
        pdContributionTest.setCode(pdContribution.getCode());
        pdContributionTest.setCreateTime(DateUtils.getNowDate());
        pdContributionTest.setStatus(PdContributionStatus.WAIT_TEST);
        pdContributionTest.setAuditor("");
        pdContributionTest.setAuditTime(null);
        pdContributionTest.setTestDate(pdContribution.getCreateTime());
        pdContributionTestMapper.insertPdContributionTest(pdContributionTest);

        PdContribution param = new PdContribution();
        param.setId(pdContribution.getId());
        param.setTestId(pdContributionTest.getId());
        param.setTestCode(pdContributionTest.getTestCode());
        pdContributionMapper.updatePdContribution(param);

        for (PdContributionDetail pdContributionDetail : pdContributionDetailList)
        {
            PdContributionTestDetail detail = JSON.parseObject(JSON.toJSONString(pdContributionDetail),
                    PdContributionTestDetail.class);
            detail.setId(null);
            detail.setTestId(pdContributionTest.getId());
            detail.setQuantityTest(detail.getQuantity());
            detail.setStatus("1");
            detail.setCreateBy(pdContributionTest.getCreateBy());
            detail.setCreateTime(pdContributionTest.getCreateTime());
            pdContributionTestDetailMapper.insertPdContributionTestDetail(detail);
        }
    }

    @Override
    public int submit(Long id, String username)
    {
        PdContributionTest pdContributionTest = pdContributionTestMapper.selectPdContributionTestById(id);
        if (pdContributionTest == null)
        {
            return 0;
        }
        if (!PdContributionStatus.WAIT_TEST.equals(pdContributionTest.getStatus()))
        {
            return 0;
        }
        PdContributionTest param = new PdContributionTest();
        param.setId(id);
        param.setStatus(PdContributionStatus.SUBMIT);
        param.setTester(username);
        param.setTestTime(new Date());
        param.setUpdateBy(username);
        param.setUpdateTime(new Date());
        return pdContributionTestMapper.updatePdContributionTest(param);
    }

    @Override
    public int pass(Long id, String username)
    {
        PdContributionTest pdContributionTest = pdContributionTestMapper.selectPdContributionTestById(id);
        if (pdContributionTest == null)
        {
            return 0;
        }
        if (!PdContributionStatus.SUBMIT.equals(pdContributionTest.getStatus()))
        {
            return 0;
        }
        PdContributionTest param = new PdContributionTest();
        param.setId(id);
        param.setStatus(PdContributionStatus.PASS);
        param.setAuditor(username);
        param.setAuditTime(new Date());
        param.setUpdateBy(username);
        param.setUpdateTime(new Date());
        pdContributionTestMapper.updatePdContributionTest(param);

        Long pdContributionId = pdContributionService.syncToContributionQuantities(id, username);

        if (pdContributionId != null)
        {
            pdContributionService.setToSubmit(pdContributionId, username);
        }
        return 1;
    }

    @Override
    public int reject(Long id, String username)
    {
        PdContributionTest pdContributionTest = pdContributionTestMapper.selectPdContributionTestById(id);
        if (pdContributionTest == null)
        {
            return 0;
        }
        if (!PdContributionStatus.SUBMIT.equals(pdContributionTest.getStatus()))
        {
            return 0;
        }
        PdContributionTest param = new PdContributionTest();
        param.setId(id);
        param.setStatus(PdContributionStatus.WAIT_TEST);
        param.setAuditor(username);
        param.setAuditTime(new Date());
        param.setUpdateBy(username);
        param.setUpdateTime(new Date());
        return pdContributionTestMapper.updatePdContributionTest(param);
    }
}
