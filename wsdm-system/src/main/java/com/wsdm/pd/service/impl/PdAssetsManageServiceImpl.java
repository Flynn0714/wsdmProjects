package com.wsdm.pd.service.impl;

import com.wsdm.common.CodeUtils;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.pd.domain.PdAssetsManage;
import com.wsdm.pd.domain.PdAssetsManageRecord;
import com.wsdm.pd.mapper.PdAssetsManageMapper;
import com.wsdm.pd.mapper.PdAssetsManageRecordMapper;
import com.wsdm.pd.service.IPdAssetsManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.wsdm.common.constant.Constants.ASSET_NUMBER_PREFIX;

/**
 * 资产管理Service业务层处理
 *
 * @author wsdm
 * @date 2021-06-28
 */
@Service
public class PdAssetsManageServiceImpl implements IPdAssetsManageService {

    @Autowired
    private PdAssetsManageMapper pdAssetsManageMapper;

    @Autowired
    private PdAssetsManageRecordMapper pdAssetsManageRecordMapper;



    /**
     * 查询资产管理
     *
     * @param id 资产管理ID
     * @return 资产管理
     */
    @Override
    public PdAssetsManage selectPdAssetsManageById(Long id) {
        return pdAssetsManageMapper.selectPdAssetsManageById(id);
    }

    /**
     * 查询资产管理列表
     *
     * @param pdAssetsManage 资产管理
     * @return 资产管理
     */
    @Override
    public List<PdAssetsManage> selectPdAssetsManageList(PdAssetsManage pdAssetsManage) {
        return pdAssetsManageMapper.selectPdAssetsManageList(pdAssetsManage);
    }

    /**
     * 新增资产管理
     *
     * @param pdAssetsManage 资产管理
     * @return 结果
     */
    @Override
    public int insertPdAssetsManage(PdAssetsManage pdAssetsManage) {
        if(pdAssetsManage.getAssetQuantity() == null){
            return 0;
        }
        if(pdAssetsManage.getScrapQuantity() == null){
            pdAssetsManage.setScrapQuantity(new BigDecimal("0"));
        }
        if(pdAssetsManage.getRepairQuantity() == null){
            pdAssetsManage.setRepairQuantity(new BigDecimal("0"));
        }
        BigDecimal scrap_repair = pdAssetsManage.getScrapQuantity().add(pdAssetsManage.getRepairQuantity());
        if(scrap_repair.compareTo(pdAssetsManage.getAssetQuantity()) == -1){
        }else{
            return 0;
        }
        BigDecimal assetQuantity= pdAssetsManage.getAssetQuantity();
        assetQuantity = assetQuantity.setScale(1,BigDecimal.ROUND_HALF_UP);
        scrap_repair=scrap_repair.setScale(1,BigDecimal.ROUND_HALF_UP);
        BigDecimal usable = assetQuantity.subtract(scrap_repair);//总数量-（报废+维修）=可用数量
        if(pdAssetsManage.getUsableQuantity() !=null) {
            if (pdAssetsManage.getUsableQuantity().compareTo(usable) == 0) {
            } else {
                pdAssetsManage.setUsableQuantity(usable);
            }
        }else{
            pdAssetsManage.setUsableQuantity(usable);
        }
        String maxCode = pdAssetsManageMapper.getMaxCustomerCode(ASSET_NUMBER_PREFIX);
        pdAssetsManage.setAssetNumber(CodeUtils.getOneCode(ASSET_NUMBER_PREFIX, maxCode, 4));
        pdAssetsManage.setCreateBy(SecurityUtils.getUsername());
        pdAssetsManage.setCreateTime(DateUtils.getNowDate());
        return pdAssetsManageMapper.insertPdAssetsManage(pdAssetsManage);
    }

    /**
     * 修改资产管理
     *
     * @param pdAssetsManage 资产管理
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePdAssetsManage(PdAssetsManage pdAssetsManage) {
        if(pdAssetsManage.getAssetQuantity() == null){
            return 0;
        }
        if(pdAssetsManage.getScrapQuantity() == null){
            pdAssetsManage.setScrapQuantity(new BigDecimal("0"));
        }
        if(pdAssetsManage.getRepairQuantity() == null){
            pdAssetsManage.setRepairQuantity(new BigDecimal("0"));
        }
        BigDecimal scrap_repair = pdAssetsManage.getScrapQuantity().add(pdAssetsManage.getRepairQuantity());
        if(scrap_repair.compareTo(pdAssetsManage.getAssetQuantity()) == -1){
        }else{
            return 0;
        }
        BigDecimal assetQuantity= pdAssetsManage.getAssetQuantity();
        assetQuantity = assetQuantity.setScale(1,BigDecimal.ROUND_HALF_UP);
        scrap_repair=scrap_repair.setScale(1,BigDecimal.ROUND_HALF_UP);
        BigDecimal usable = assetQuantity.subtract(scrap_repair);//总数量-（报废+维修）=可用数量
        if(pdAssetsManage.getUsableQuantity() !=null) {
            if (pdAssetsManage.getUsableQuantity().compareTo(usable) == 0) {
            } else {
                pdAssetsManage.setUsableQuantity(usable);
            }
        }else{
            pdAssetsManage.setUsableQuantity(usable);
        }
        pdAssetsManage.setUpdateBy(SecurityUtils.getUsername());
        pdAssetsManage.setUpdateTime(DateUtils.getNowDate());

        PdAssetsManage pdAssetmanageModel= pdAssetsManageMapper.selectPdAssetsManageById(pdAssetsManage.getId());
        PdAssetsManageRecord pdAssetsManageRecord = new PdAssetsManageRecord();
        pdAssetsManageRecord.setAssetNumber(pdAssetmanageModel.getAssetNumber());
        pdAssetsManageRecord.setAssetName(pdAssetmanageModel.getAssetName());
        pdAssetsManageRecord.setAssetModel(pdAssetmanageModel.getAssetModel());
        pdAssetsManageRecord.setAssetQuantity(pdAssetmanageModel.getAssetQuantity());
        pdAssetsManageRecord.setScrapQuantity(pdAssetmanageModel.getScrapQuantity());
        pdAssetsManageRecord.setRepairQuantity(pdAssetmanageModel.getRepairQuantity());
        pdAssetsManageRecord.setUsableQuantity(pdAssetmanageModel.getUsableQuantity());
        pdAssetsManageRecord.setRemark(pdAssetmanageModel.getRemark());
        pdAssetsManageRecord.setCreateBy(pdAssetmanageModel.getCreateBy());
        pdAssetsManageRecord.setCreateTime(pdAssetmanageModel.getCreateTime());

        pdAssetsManageRecord.setUpdateBy(SecurityUtils.getUsername());
        pdAssetsManageRecord.setUpdateTime(DateUtils.getNowDate());

        int result = pdAssetsManageMapper.updatePdAssetsManage(pdAssetsManage);
        if(result <= 0 ){
            throw  new BaseException("资产管理更新失败");
        }
        int resultRecord = pdAssetsManageRecordMapper.insertPdAssetsManageRecord(pdAssetsManageRecord);
        if(resultRecord <=0 ){
            throw  new BaseException("资产管理更新记录添加失败");
        }
        return result;
    }

    /**
     * 批量删除资产管理
     *
     * @param ids 需要删除的资产管理ID
     * @return 结果
     */
    @Override
    public int deletePdAssetsManageByIds(Long[] ids) {
        return pdAssetsManageMapper.deletePdAssetsManageByIds(ids);
    }

    /**
     * 删除资产管理信息
     *
     * @param id 资产管理ID
     * @return 结果
     */
    @Override
    public int deletePdAssetsManageById(Long id) {
        return pdAssetsManageMapper.deletePdAssetsManageById(id);
    }
}