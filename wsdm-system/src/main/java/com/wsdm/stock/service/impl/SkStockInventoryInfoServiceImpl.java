package com.wsdm.stock.service.impl;

import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.pd.constant.Prefix;
import com.wsdm.stock.domain.*;
import com.wsdm.stock.mapper.SkStockInventoryDetailMapper;
import com.wsdm.stock.mapper.SkStockInventoryInfoMapper;
import com.wsdm.stock.mapper.SkStockMapper;
import com.wsdm.stock.service.IPdProduceHardwareSupport;
import com.wsdm.stock.service.ISkStockInventoryInfoService;
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
 * 库存盘点信息Service业务层处理
 *
 * @author wsdm
 * @date 2021-05-25
 */
@Service
public class SkStockInventoryInfoServiceImpl implements ISkStockInventoryInfoService
{
    @Autowired
    private SkStockInventoryInfoMapper   skStockInventoryInfoMapper;
    @Autowired
    private SkStockInventoryDetailMapper skStockInventoryDetailMapper;
    @Autowired
    private SkStockMapper                skStockMapper;

    @Autowired
    private ISkStockService skStockService;
    @Autowired
    private IPdProduceHardwareSupport pdProduceHardwareSupport;

    /**
     * 查询库存盘点信息
     *
     * @param id 库存盘点信息ID
     * @return 库存盘点信息
     */
    @Override
    public SkStockInventoryInfoVO selectSkStockInventoryInfoById(Long id)
    {
        SkStockInventoryInfo skStockInventoryInfo = skStockInventoryInfoMapper.selectSkStockInventoryInfoById(id);
        if (skStockInventoryInfo == null)
        {
            return new SkStockInventoryInfoVO();
        }
        SkStockInventoryInfoVO vo = new SkStockInventoryInfoVO();
        vo.setSkStockInventoryInfo(skStockInventoryInfo);
        List<SkStockInventoryDetail> details = listDetail(skStockInventoryInfo.getInventoryNumber());
        if(CollectionUtils.isNotEmpty(details)){
            for (SkStockInventoryDetail detail : details) {
                detail.setModel(pdProduceHardwareSupport.getModel(detail.getMaterialId()));
            }
        }
        vo.setSkStockInventoryDetailList(details);
        return vo;
    }

    /**
     * 查询库存盘点信息列表
     *
     * @param skStockInventoryInfo 库存盘点信息
     * @return 库存盘点信息
     */
    @Override
    public List<SkStockInventoryInfo> selectSkStockInventoryInfoList(SkStockInventoryInfo skStockInventoryInfo)
    {
        return skStockInventoryInfoMapper.selectSkStockInventoryInfoList(skStockInventoryInfo);
    }

    /**
     * 新增库存盘点信息
     *
     * @param skStockInventoryInfoVO 库存盘点信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertSkStockInventoryInfo(SkStockInventoryInfoVO skStockInventoryInfoVO)
    {
        SkStockInventoryInfo skStockInventoryInfo = skStockInventoryInfoVO.getSkStockInventoryInfo();
        List<SkStockInventoryDetail> skStockInventoryDetails = skStockInventoryInfoVO.getSkStockInventoryDetailList();
        checkMaterialId(skStockInventoryDetails);

        String prefix = Prefix.INVENTORY + DateUtils.parseDateToStr("yyyyMMdd", new Date());
        String latestCode = skStockInventoryInfoMapper.getLatestCode();
        latestCode = StringUtils.isEmpty(latestCode) ? "" : latestCode;
        latestCode = latestCode.startsWith(prefix) ? latestCode.replaceFirst(prefix, "") : "0";
        skStockInventoryInfo.setInventoryNumber(CodeUtils.getOneCode(prefix, latestCode, 3));
        skStockInventoryInfo.setStatus(WAIT_START);
        skStockInventoryInfo.setCreateTime(DateUtils.getNowDate());
        skStockInventoryInfoMapper.insertSkStockInventoryInfo(skStockInventoryInfo);

        for (SkStockInventoryDetail detail : skStockInventoryDetails)
        {
            detail.setInventoryNumber(skStockInventoryInfo.getInventoryNumber());
            detail.setCreateBy(skStockInventoryInfo.getCreateBy());
            detail.setCreateTime(skStockInventoryInfo.getCreateTime());
            skStockInventoryDetailMapper.insertSkStockInventoryDetail(detail);
        }
        return 1;
    }

    /**
     * 修改库存盘点信息
     *
     * @param skStockInventoryInfoVO 库存盘点信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateSkStockInventoryInfo(SkStockInventoryInfoVO skStockInventoryInfoVO)
    {
        SkStockInventoryInfo skStockInventoryInfo = skStockInventoryInfoVO.getSkStockInventoryInfo();
        List<SkStockInventoryDetail> details = skStockInventoryInfoVO.getSkStockInventoryDetailList();
        checkMaterialId(details);

        //判断是否存在、待审批
        SkStockInventoryInfo old = skStockInventoryInfoMapper.selectSkStockInventoryInfoById(
                skStockInventoryInfo.getId());
        if (old == null)
        {
            return 0;
        }

        skStockInventoryInfo.setUpdateTime(DateUtils.getNowDate());
        List<SkStockInventoryDetail> oldDetailList = listDetail(old.getInventoryNumber());
        List<Long> deleting = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(oldDetailList))
        {
            deleting.addAll(oldDetailList.stream().map(SkStockInventoryDetail::getId).collect(Collectors.toList()));
        }
        List<SkStockInventoryDetail> adding = new ArrayList<>();
        List<SkStockInventoryDetail> updating = new ArrayList<>();
        for (SkStockInventoryDetail detail : details)
        {
            SkStockInventoryDetail oldDetail = CollectionUtils.find(oldDetailList,
                    skStockInventoryDetail -> skStockInventoryDetail.getId().equals(detail.getId()));
            if (oldDetail == null)
            {
                detail.setInventoryNumber(old.getInventoryNumber());
                detail.setCreateBy(skStockInventoryInfo.getUpdateBy());
                detail.setCreateTime(skStockInventoryInfo.getUpdateTime());
                adding.add(detail);
                continue;
            }
            detail.setUpdateBy(skStockInventoryInfo.getUpdateBy());
            detail.setUpdateTime(skStockInventoryInfo.getUpdateTime());
            updating.add(detail);
            deleting.remove(oldDetail.getId());
        }

        if ("1".equals(skStockInventoryInfoVO.getAction()))
        {
            //开始盘点，锁库
            lock(old.getStockCode(), details, skStockInventoryInfo.getUpdateBy());

            skStockInventoryInfo.setStatus(WAIT_RECORD);
            skStockInventoryInfo.setStartOperator(skStockInventoryInfo.getUpdateBy());
            skStockInventoryInfo.setStartTime(skStockInventoryInfo.getUpdateTime());
        }
        else if ("2".equals(skStockInventoryInfoVO.getAction()))
        {
            //存在新增部分，锁定库存
            lock(old.getStockCode(), adding, skStockInventoryInfo.getUpdateBy());
            //录入完成，设置差异
            for (SkStockInventoryDetail detail : details)
            {
                detail.setDifferencesNum(detail.getInventoryNum().subtract(detail.getStockNum()));
            }

            skStockInventoryInfo.setStatus(WAIT_ACCOUNT);
            skStockInventoryInfo.setEntryOperator(skStockInventoryInfo.getUpdateBy());
            skStockInventoryInfo.setEntryTime(skStockInventoryInfo.getUpdateTime());
        }
        else if ("3".equals(skStockInventoryInfoVO.getAction()))
        {
            //盘点登账，调库
            for (SkStockInventoryDetail detail : details)
            {
                detail.setStockAdjustNum(detail.getDifferencesNum());
                //待解锁库存数量，默认已被锁定库存量
                BigDecimal unlockingNum = detail.getStockNum();
                if (detail.getStockAdjustNum().compareTo(BigDecimal.ZERO) < 0)
                {
                    //现有库存偏多，确认扣减已被锁定库存
                    BigDecimal num = BigDecimal.ZERO.subtract(detail.getStockAdjustNum());
                    skStockService.confirmLockedStock(old.getStockCode(), detail.getMaterialId(), num,
                            skStockInventoryInfo.getUpdateBy());

                    //去除已被扣减部分的库存量
                    unlockingNum = unlockingNum.subtract(num);
                }
                else if (detail.getStockAdjustNum().compareTo(BigDecimal.ZERO) > 0)
                {
                    //现有库存偏低，增加库存
                    SkStockBatch stockBatch = new SkStockBatch();
                    stockBatch.setStockCode(old.getStockCode());
                    stockBatch.setStockName(old.getStockName());
                    stockBatch.setProduce(detail.getMaterialId());
                    stockBatch.setProduceName(detail.getMaterialName());
                    stockBatch.setBatchTotal(detail.getStockAdjustNum());
                    stockBatch.setCreateBy(skStockInventoryInfo.getUpdateBy());
                    skStockService.addStock(stockBatch);
                }
                //解锁库存
                skStockService.unlockStock(old.getStockCode(), detail.getMaterialId(), unlockingNum,
                        skStockInventoryInfo.getUpdateBy());
            }

            skStockInventoryInfo.setStatus(COMPLETE);
            skStockInventoryInfo.setBookingOperator(skStockInventoryInfo.getUpdateBy());
            skStockInventoryInfo.setBookingTime(skStockInventoryInfo.getUpdateTime());
        }
        else
        {
            //仅保存操作
            //待录入完成，新增部分锁定
            if (WAIT_RECORD.equals(skStockInventoryInfo.getStatus()))
            {
                lock(old.getStockCode(), adding, skStockInventoryInfo.getUpdateBy());
            }
        }

        //更新主记录
        skStockInventoryInfoMapper.updateSkStockInventoryInfo(skStockInventoryInfo);

        //新增的
        adding.forEach(skStockInventoryDetailMapper::insertSkStockInventoryDetail);

        //更新的
        updating.forEach(skStockInventoryDetailMapper::updateSkStockInventoryDetail);

        //删除多余的
        deleting.forEach(skStockInventoryDetailMapper::deleteSkStockInventoryDetailById);
        return 1;
    }

    /**
     * 批量删除库存盘点信息
     *
     * @param ids 需要删除的库存盘点信息ID
     * @return 结果
     */
    @Override
    public int deleteSkStockInventoryInfoByIds(Long[] ids)
    {
        return skStockInventoryInfoMapper.deleteSkStockInventoryInfoByIds(ids);
    }

    /**
     * 删除库存盘点信息信息
     *
     * @param id 库存盘点信息ID
     * @return 结果
     */
    @Override
    public int deleteSkStockInventoryInfoById(Long id)
    {
        return skStockInventoryInfoMapper.deleteSkStockInventoryInfoById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteSafe(Long id, String username)
    {
        SkStockInventoryInfo skStockInventoryInfo = skStockInventoryInfoMapper.selectSkStockInventoryInfoById(id);
        if (!WAIT_START.equals(skStockInventoryInfo.getStatus()))
        {
            return 0;
        }

        //解锁所有库存
        List<SkStockInventoryDetail> details = listDetail(skStockInventoryInfo.getInventoryNumber());
        if (CollectionUtils.isNotEmpty(details))
        {
            for (SkStockInventoryDetail detail : details)
            {
                skStockService.unlockStock(skStockInventoryInfo.getStockCode(), detail.getMaterialId(),
                        detail.getStockNum(), username);
            }
        }

        skStockInventoryInfo = new SkStockInventoryInfo();
        skStockInventoryInfo.setId(id);
        skStockInventoryInfo.setStatus(DELETE);
        skStockInventoryInfo.setUpdateBy(username);
        skStockInventoryInfo.setUpdateTime(new Date());
        return skStockInventoryInfoMapper.updateSkStockInventoryInfo(skStockInventoryInfo);
    }

    private void checkMaterialId(List<SkStockInventoryDetail> details)
    {
        if (CollectionUtils.isEmpty(details))
        {
            throw new BaseException("请至少提供一个库存盘点明细。");
        }

        Set<String> codeSet = new HashSet<>();
        for (SkStockInventoryDetail detail : details)
        {
            if (codeSet.contains(detail.getMaterialId()))
            {
                throw new BaseException("物料编号：" + detail.getMaterialId() + "不得重复。");
            }
            codeSet.add(detail.getMaterialId());
        }
    }

    private List<SkStockInventoryDetail> listDetail(String inventoryNumber)
    {
        SkStockInventoryDetail param = new SkStockInventoryDetail();
        param.setInventoryNumber(inventoryNumber);
        return skStockInventoryDetailMapper.selectSkStockInventoryDetailList(param);
    }

    private void lock(String stockCode, List<SkStockInventoryDetail> details, String operator)
    {
        for (SkStockInventoryDetail detail : details)
        {
            SkStock stock = skStockMapper.summary(stockCode, detail.getMaterialId());
            BigDecimal total = BigDecimal.ZERO;
            if (stock != null)
            {
                if (stock.getStockLockNum().compareTo(BigDecimal.ZERO) > 0)
                {
                    throw new BaseException("仓库编号：" + stockCode + "，物料编号：" +
                                            detail.getMaterialId() + "，存在锁库，暂不允许盘点。");
                }
                total = stock.getStockTotal();
            }

            if (total.compareTo(BigDecimal.ZERO) > 0)
            {
                //锁定所有库存
                skStockService.lockStock(stockCode, detail.getMaterialId(), total, operator);
            }

            //设置锁定数量
            detail.setStockNum(total);
        }
    }
}
