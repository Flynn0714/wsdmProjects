package com.wsdm.stock.service.impl;

import com.wsdm.common.constant.Constants;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.MaterialStatus;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.stock.domain.MaterialReceive;
import com.wsdm.stock.domain.MaterialReceiveDetail;
import com.wsdm.stock.domain.MaterialReceiveRequest;
import com.wsdm.stock.mapper.MaterialReceiveMapper;
import com.wsdm.stock.service.IMaterialReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 物料收货订单信息Service业务层处理
 *
 * @author wsdm
 * @date 2021-01-12
 */
@Service
public class MaterialReceiveServiceImpl implements IMaterialReceiveService {
    @Autowired
    private MaterialReceiveMapper materialReceiveMapper;

    /**
     * 查询物料收货订单信息
     *
     * @param id 物料收货订单信息ID
     * @return 物料收货订单信息
     */
    @Override
    public MaterialReceive selectMaterialReceiveById(Long id) {
        MaterialReceive materialReceive = materialReceiveMapper.selectMaterialReceiveById(id);
        List<MaterialReceiveDetail> materialReceiveDetails = materialReceiveMapper.selectMaterialReceiveDetailList(new MaterialReceiveDetail(materialReceive.getMaterialNumber()));
        materialReceive.setMaterialReceiveDetails(materialReceiveDetails);
        return materialReceive;
    }

    /**
     * 查询物料收货订单信息列表
     *
     * @param materialReceive 物料收货订单信息
     * @return 物料收货订单信息
     */
    @Override
    public List<MaterialReceive> selectMaterialReceiveList(MaterialReceiveRequest materialReceive) {
        return materialReceiveMapper.selectMaterialReceiveList(materialReceive);
    }

    /**
     * 新增物料收货订单信息
     *
     * @param materialReceive 物料收货订单信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertMaterialReceive(MaterialReceive materialReceive) {
        String maxCode = materialReceiveMapper.getMaxMaterialReceiveCode(Constants.MATERIALRECEIVE_CODE);
        if (MaterialStatus.APPROVED.getCode().equals(materialReceive.getStatus())) {
            materialReceive.setApprovePerson(SecurityUtils.getUsername());
            materialReceive.setApproveTime(DateUtils.getNowDate());
        }
        List<MaterialReceiveDetail> materialReceiveDetails = materialReceive.getMaterialReceiveDetails();
        materialReceiveDetails.stream().forEach(materialReceiveDetail -> materialReceiveDetail.setMaterialNumber(maxCode));

        materialReceive.setMaterialNumber(maxCode);
        materialReceive.setCreateBy(SecurityUtils.getUsername());

        materialReceiveMapper.insertMaterialReceiveDetailBatch(materialReceiveDetails);
        return materialReceiveMapper.insertMaterialReceive(materialReceive);
    }

    /**
     * 修改物料收货订单信息
     *
     * @param materialReceive 物料收货订单信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateMaterialReceive(MaterialReceive materialReceive) {
        MaterialReceive newMaterialReceive = materialReceiveMapper.selectMaterialReceiveById(materialReceive.getId());

        if (MaterialStatus.APPROVED.getCode().equals(newMaterialReceive.getStatus())) {
            return 0;
        }

        if (MaterialStatus.APPROVED.getCode().equals(materialReceive.getStatus())) {
            materialReceive.setApprovePerson(SecurityUtils.getUsername());
            materialReceive.setApproveTime(DateUtils.getNowDate());
        }

        List<MaterialReceiveDetail> materialReceiveDetails = materialReceive.getMaterialReceiveDetails();
        materialReceiveDetails.stream().forEach(materialReceiveDetail -> materialReceiveDetail.setMaterialNumber(newMaterialReceive.getMaterialNumber()));

        materialReceive.setMaterialNumber(newMaterialReceive.getMaterialNumber());
        materialReceive.setUpdateBy(SecurityUtils.getUsername());

        materialReceiveMapper.deleteMaterialReceiveDetailByNumber(newMaterialReceive.getMaterialNumber());
        materialReceiveMapper.insertMaterialReceiveDetailBatch(materialReceiveDetails);
        return materialReceiveMapper.updateMaterialReceive(materialReceive);
    }

    @Override
    public AjaxResult approved(MaterialReceive materialReceive) {
        return null;
    }

    /**
     * 批量删除物料收货订单信息
     *
     * @param ids 需要删除的物料收货订单信息ID
     * @return 结果
     */
    @Override
    public int deleteMaterialReceiveByIds(Long[] ids) {
        return materialReceiveMapper.deleteMaterialReceiveByIds(ids);
    }

    /**
     * 删除物料收货订单信息信息
     *
     * @param id 物料收货订单信息ID
     * @return 结果
     */
    @Override
    public int deleteMaterialReceiveById(Long id) {
        return materialReceiveMapper.deleteMaterialReceiveById(id);
    }
}
