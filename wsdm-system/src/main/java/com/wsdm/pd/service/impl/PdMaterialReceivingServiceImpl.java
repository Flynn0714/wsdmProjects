package com.wsdm.pd.service.impl;

import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.pd.constant.PdMaterialReceivingStatus;
import com.wsdm.pd.constant.Prefix;
import com.wsdm.pd.domain.PdMaterialReceiving;
import com.wsdm.pd.domain.PdMaterialReceivingDetail;
import com.wsdm.pd.domain.PdMaterialReceivingVO;
import com.wsdm.pd.domain.PdSemiProduceManage;
import com.wsdm.pd.mapper.PdMaterialReceivingDetailMapper;
import com.wsdm.pd.mapper.PdMaterialReceivingMapper;
import com.wsdm.pd.mapper.PdSemiProduceManageMapper;
import com.wsdm.pd.service.IPdMaterialReceivingService;
import com.wsdm.stock.domain.SkStock;
import com.wsdm.stock.mapper.SkStockMapper;
import com.wsdm.stock.service.ISkStockService;
import com.wsdm.utils.CodeUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * 物料领取单Service业务层处理
 *
 * @author wsdm
 * @date 2021-05-11
 */
@Service
public class PdMaterialReceivingServiceImpl implements IPdMaterialReceivingService
{
    private static final Logger log = LoggerFactory.getLogger(PdMaterialReceivingServiceImpl.class);

    @Autowired
    private SkStockMapper                   skStockMapper;
    @Autowired
    private PdMaterialReceivingMapper       pdMaterialReceivingMapper;
    @Autowired
    private PdMaterialReceivingDetailMapper pdMaterialReceivingDetailMapper;

    @Autowired
    private ISkStockService skStockService;

    @Autowired
    private PdSemiProduceManageMapper pdSemiProduceManageMapper;

    /**
     * 查询物料领取单
     *
     * @param id 物料领取单ID
     * @return 物料领取单
     */
    @Override
    public PdMaterialReceivingVO selectPdMaterialReceivingById(Long id)
    {
        PdMaterialReceiving pdMaterialReceiving = pdMaterialReceivingMapper.selectPdMaterialReceivingById(id);
        List<PdMaterialReceivingDetail> details = listDetail(id);
        if (CollectionUtils.isNotEmpty(details))
        {
            List<Future> futures = details.stream().map(detail -> CompletableFuture.runAsync(() -> {
                setAvailableQuantity(pdMaterialReceiving.getWarehouseCode(), detail);
                setType(detail);
            })).collect(Collectors.toList());
            for (Future future : futures)
            {
                try
                {
                    future.get();
                }
                catch (Exception e)
                {
                    log.error(e.toString(), e);
                }
            }
        }

        PdMaterialReceivingVO vo = new PdMaterialReceivingVO();
        vo.setPdMaterialReceiving(pdMaterialReceiving);
        vo.setPdMaterialReceivingDetailList(details);
        return vo;
    }

    /**
     * 查询物料领取单列表
     *
     * @param pdMaterialReceiving 物料领取单
     * @return 物料领取单
     */
    @Override
    public List<PdMaterialReceiving> selectPdMaterialReceivingList(PdMaterialReceiving pdMaterialReceiving)
    {
        return pdMaterialReceivingMapper.selectPdMaterialReceivingList(pdMaterialReceiving);
    }

    /**
     * 新增物料领取单
     *
     * @param vo 物料领取单
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertPdMaterialReceiving(PdMaterialReceivingVO vo)
    {
        BigDecimal totalReceiveQuantity = BigDecimal.ZERO;
        List<PdMaterialReceivingDetail> details = vo.getPdMaterialReceivingDetailList();

        checkMaterialCode(details);

        for (PdMaterialReceivingDetail detail : details)
        {
            if (detail.getReceiveQuantity() == null)
            {
                continue;
            }
            totalReceiveQuantity = totalReceiveQuantity.add(detail.getReceiveQuantity());
        }

        PdMaterialReceiving pdMaterialReceiving = vo.getPdMaterialReceiving();

        String prefix = Prefix.MATERIAL_REC + DateUtils.parseDateToStr("yyyyMMdd", new Date());
        String latestCode = pdMaterialReceivingMapper.getLatestCode();
        latestCode = StringUtils.isEmpty(latestCode) ? "" : latestCode;
        latestCode = latestCode.startsWith(prefix) ? latestCode.replaceFirst(prefix, "") : "0";
        pdMaterialReceiving.setReceivingCode(CodeUtils.getOneCode(prefix, latestCode, 3));

        pdMaterialReceiving.setTotalQuantity(totalReceiveQuantity);
        pdMaterialReceiving.setCreateTime(DateUtils.getNowDate());
        if ("0".equals(vo.getAuditType()))
        {
            //无需审批
            pdMaterialReceiving.setStatus(PdMaterialReceivingStatus.WAIT);
            pdMaterialReceiving.setAuditor("");
            pdMaterialReceiving.setAuditTime(null);
        }
        else if ("1".equals(vo.getAuditType()))
        {
            //提交审批
            pdMaterialReceiving.setStatus(PdMaterialReceivingStatus.SUBMIT);
        }
        pdMaterialReceivingMapper.insertPdMaterialReceiving(pdMaterialReceiving);

        for (PdMaterialReceivingDetail detail : details)
        {
            detail.setReceivingId(pdMaterialReceiving.getId());
            detail.setStatus("1");
            detail.setCreateBy(pdMaterialReceiving.getCreateBy());
            detail.setCreateTime(new Date());
            pdMaterialReceivingDetailMapper.insertPdMaterialReceivingDetail(detail);
        }

        if (PdMaterialReceivingStatus.SUBMIT.equals(pdMaterialReceiving.getStatus()))
        {
            lockStock(pdMaterialReceiving.getWarehouseCode(), details, pdMaterialReceiving.getCreateBy());
        }
        return 1;
    }

    /**
     * 修改物料领取单
     *
     * @param vo 物料领取单
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updatePdMaterialReceiving(PdMaterialReceivingVO vo)
    {
        PdMaterialReceiving pdMaterialReceiving = vo.getPdMaterialReceiving();
        //判断是否存在、待审批
        PdMaterialReceiving old = pdMaterialReceivingMapper.selectPdMaterialReceivingById(pdMaterialReceiving.getId());
        if (old == null)
        {
            return 0;
        }
        if (!PdMaterialReceivingStatus.WAIT.equals(old.getStatus()))
        {
            return 0;
        }
        List<PdMaterialReceivingDetail> details = vo.getPdMaterialReceivingDetailList();
        checkMaterialCode(details);

        BigDecimal totalReceiveQuantity = BigDecimal.ZERO;
        for (PdMaterialReceivingDetail detail : details)
        {
            if (detail.getReceiveQuantity() == null)
            {
                continue;
            }
            totalReceiveQuantity = totalReceiveQuantity.add(detail.getReceiveQuantity());
        }
        pdMaterialReceiving.setTotalQuantity(totalReceiveQuantity);

        pdMaterialReceiving.setUpdateTime(DateUtils.getNowDate());
        if ("0".equals(vo.getAuditType()))
        {
            //无需审批
            pdMaterialReceiving.setStatus(PdMaterialReceivingStatus.WAIT);
            pdMaterialReceiving.setAuditor("");
            pdMaterialReceiving.setAuditTime(null);
        }
        else if ("1".equals(vo.getAuditType()))
        {
            //提交审批
            pdMaterialReceiving.setStatus(PdMaterialReceivingStatus.SUBMIT);
        }
        pdMaterialReceivingMapper.updatePdMaterialReceiving(pdMaterialReceiving);

        List<PdMaterialReceivingDetail> oldDetails = listDetail(pdMaterialReceiving.getId());
        List<Long> deleting = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(oldDetails))
        {
            deleting.addAll(oldDetails.stream().map(PdMaterialReceivingDetail::getId).collect(Collectors.toList()));
        }
        for (PdMaterialReceivingDetail detail : details)
        {
            detail.setUpdateBy(pdMaterialReceiving.getUpdateBy());
            detail.setUpdateTime(new Date());
            if (pdMaterialReceivingDetailMapper.updatePdMaterialReceivingDetail(detail) <= 0)
            {
                detail.setReceivingId(pdMaterialReceiving.getId());
                detail.setStatus("1");
                detail.setCreateBy(pdMaterialReceiving.getUpdateBy());
                detail.setCreateTime(new Date());
                pdMaterialReceivingDetailMapper.insertPdMaterialReceivingDetail(detail);
                continue;
            }
            deleting.remove(detail.getId());
        }
        for (Long detailId : deleting)
        {
            PdMaterialReceivingDetail param = new PdMaterialReceivingDetail();
            param.setId(detailId);
            param.setStatus("0");
            param.setUpdateBy(pdMaterialReceiving.getUpdateBy());
            param.setUpdateTime(new Date());
            pdMaterialReceivingDetailMapper.updatePdMaterialReceivingDetail(param);
        }

        if (PdMaterialReceivingStatus.SUBMIT.equals(pdMaterialReceiving.getStatus()))
        {
            lockStock(old.getWarehouseCode(), listDetail(old.getId()), pdMaterialReceiving.getUpdateBy());
        }
        return 1;
    }

    /**
     * 批量删除物料领取单
     *
     * @param ids 需要删除的物料领取单ID
     * @return 结果
     */
    @Override
    public int deletePdMaterialReceivingByIds(Long[] ids)
    {
        return pdMaterialReceivingMapper.deletePdMaterialReceivingByIds(ids);
    }

    /**
     * 删除物料领取单信息
     *
     * @param id 物料领取单ID
     * @return 结果
     */
    @Override
    public int deletePdMaterialReceivingById(Long id)
    {
        return pdMaterialReceivingMapper.deletePdMaterialReceivingById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteSafe(Long id, String userId)
    {
        PdMaterialReceiving receiving = pdMaterialReceivingMapper.selectPdMaterialReceivingById(id);
        if (!PdMaterialReceivingStatus.SUBMIT.equals(receiving.getStatus()))
        {
            return 0;
        }
        unlockStock(receiving.getWarehouseCode(), listDetail(id), userId);

        receiving = new PdMaterialReceiving();
        receiving.setId(id);
        receiving.setStatus(PdMaterialReceivingStatus.DELETE);
        receiving.setUpdateBy(userId);
        receiving.setUpdateTime(new Date());
        return pdMaterialReceivingMapper.updatePdMaterialReceiving(receiving);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int recover(Long id, String userId)
    {
        PdMaterialReceiving receiving = pdMaterialReceivingMapper.selectPdMaterialReceivingById(id);
        if (!PdMaterialReceivingStatus.DELETE.equals(receiving.getStatus()))
        {
            return 0;
        }
        PdMaterialReceiving param = new PdMaterialReceiving();
        param.setId(id);
        param.setStatus(PdMaterialReceivingStatus.SUBMIT);
        param.setUpdateBy(userId);
        param.setUpdateTime(new Date());
        pdMaterialReceivingMapper.updatePdMaterialReceiving(param);

        lockStock(receiving.getWarehouseCode(), listDetail(id), userId);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int submit(Long id, String username)
    {
        PdMaterialReceiving receiving = pdMaterialReceivingMapper.selectPdMaterialReceivingById(id);
        if (!PdMaterialReceivingStatus.WAIT.equals(receiving.getStatus()))
        {
            return 0;
        }
        PdMaterialReceiving param = new PdMaterialReceiving();
        param.setId(id);
        param.setStatus(PdMaterialReceivingStatus.SUBMIT);
        param.setUpdateBy(username);
        param.setUpdateTime(new Date());
        pdMaterialReceivingMapper.updatePdMaterialReceiving(param);

        lockStock(receiving.getWarehouseCode(), listDetail(id), username);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int pass(Long id, String username)
    {
        PdMaterialReceiving receiving = pdMaterialReceivingMapper.selectPdMaterialReceivingById(id);
        if (!PdMaterialReceivingStatus.SUBMIT.equals(receiving.getStatus()))
        {
            return 0;
        }
        PdMaterialReceiving param = new PdMaterialReceiving();
        param.setId(id);
        param.setStatus(PdMaterialReceivingStatus.PASS);
        param.setAuditor(username);
        param.setAuditTime(new Date());
        param.setUpdateBy(username);
        param.setUpdateTime(new Date());
        pdMaterialReceivingMapper.updatePdMaterialReceiving(param);

        confirmStock(receiving.getWarehouseCode(), listDetail(id), username);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int reject(Long id, String username)
    {
        PdMaterialReceiving receiving = pdMaterialReceivingMapper.selectPdMaterialReceivingById(id);
        if (!PdMaterialReceivingStatus.SUBMIT.equals(receiving.getStatus()))
        {
            return 0;
        }
        PdMaterialReceiving param = new PdMaterialReceiving();
        param.setId(id);
        param.setStatus(PdMaterialReceivingStatus.WAIT);
        param.setAuditor(username);
        param.setAuditTime(new Date());
        param.setUpdateBy(username);
        param.setUpdateTime(new Date());
        pdMaterialReceivingMapper.updatePdMaterialReceiving(param);

        unlockStock(receiving.getWarehouseCode(), listDetail(id), username);
        return 1;
    }

    @Override
    public List<SkStock> selectAvailableMaterial(SkStock stock)
    {
        return skStockMapper.selectAvailableMaterial(stock);
    }

    private void lockStock(String warehouseCode, List<PdMaterialReceivingDetail> detailList, String operator)
    {
        if (CollectionUtils.isEmpty(detailList))
        {
            return;
        }
        for (PdMaterialReceivingDetail detail : detailList)
        {
            skStockService.lockStock(warehouseCode, detail.getMaterialCode(), detail.getReceiveQuantity(), operator);
        }
    }

    private void unlockStock(String warehouseCode, List<PdMaterialReceivingDetail> detailList, String operator)
    {
        if (CollectionUtils.isEmpty(detailList))
        {
            return;
        }
        for (PdMaterialReceivingDetail detail : detailList)
        {
            skStockService.unlockStock(warehouseCode, detail.getMaterialCode(), detail.getReceiveQuantity(), operator);
        }
    }

    private void confirmStock(String warehouseCode, List<PdMaterialReceivingDetail> detailList, String operator)
    {
        if (CollectionUtils.isEmpty(detailList))
        {
            return;
        }
        for (PdMaterialReceivingDetail detail : detailList)
        {
            skStockService.confirmLockedStock(warehouseCode, detail.getMaterialCode(), detail.getReceiveQuantity(),
                    operator);
        }
    }

    private List<PdMaterialReceivingDetail> listDetail(Long id)
    {
        if (id == null)
        {
            throw new IllegalArgumentException("need id");
        }
        PdMaterialReceivingDetail param = new PdMaterialReceivingDetail();
        param.setReceivingId(id);
        param.setStatus("1");
        return pdMaterialReceivingDetailMapper.selectPdMaterialReceivingDetailList(param);
    }

    private void checkMaterialCode(List<PdMaterialReceivingDetail> details)
    {
        if (CollectionUtils.isEmpty(details))
        {
            throw new BaseException("请至少提供一个物料领取明细。");
        }

        Set<String> codeSet = new HashSet<>();
        for (PdMaterialReceivingDetail detail : details)
        {
            String key = detail.getMaterialCode();
            if (codeSet.contains(key))
            {
                throw new BaseException("物料编号：" + detail.getMaterialCode() + "不得重复。");
            }
            codeSet.add(key);
        }
    }

    private void setAvailableQuantity(String warehouseCode, PdMaterialReceivingDetail detail)
    {
        detail.setAvaliableQuantity(BigDecimal.ZERO);
        if (StringUtils.isEmpty(detail.getMaterialCode()))
        {
            return;
        }
        SkStock stock = skStockMapper.summary(warehouseCode, detail.getMaterialCode());
        if (stock == null)
        {
            return;
        }
        detail.setAvaliableQuantity(stock.getStockAvailableNum());
    }

    private void setType(PdMaterialReceivingDetail detail)
    {
        if (StringUtils.isEmpty(detail.getMaterialCode()))
        {
            return;
        }
        PdSemiProduceManage param = new PdSemiProduceManage();
        param.setSemiProduceNumber(detail.getMaterialCode());
        List<PdSemiProduceManage> manages = pdSemiProduceManageMapper.selectPdSemiProduceManageList(param);
        if (CollectionUtils.isEmpty(manages))
        {
            return;
        }
        PdSemiProduceManage manage = manages.get(0);
        detail.setTypeCode(manage.getSemiProduceModel());
        detail.setMaterialQuality(manage.getMaterialQuality());
    }
}
