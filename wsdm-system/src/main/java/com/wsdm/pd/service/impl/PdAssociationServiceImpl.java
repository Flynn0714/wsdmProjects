package com.wsdm.pd.service.impl;

import com.wsdm.common.CodeUtils;
import com.wsdm.common.constant.Constants;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.utils.DateUtils;

import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.pd.domain.PdAssociation;
import com.wsdm.pd.domain.PdProduceManage;
import com.wsdm.pd.mapper.PdAssociationMapper;
import com.wsdm.pd.service.IPdAssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PdAssociationServiceImpl implements IPdAssociationService {
    @Autowired
    PdAssociationMapper pdAssociationMapper;


    /**
     * 新增
     * @param pdAssociation
     * @return
     */
    @Transactional
    @Override
    public AjaxResult insertPdAssoci(PdAssociation pdAssociation) {
        /**
         * 判断是否已存在产品
         */
        if (Objects.isNull(pdAssociationMapper.selectPdAssociBySpecs(pdAssociation.getSpecs()))){
            /**
             * 生成编号、创建时间和创建人
             */
            String code = Constants.PD_ASSOCIATION_CODE + DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date());
            String maxCode = pdAssociationMapper.getMaxCode(code);
            String Code = CodeUtils.getOneCode(code, maxCode, 3);
            pdAssociation.setMaterialNumber(Code);

            String createBy = SecurityUtils.getUsername();
            pdAssociation.setCreateBy(createBy);
            Date createTime = DateUtils.getNowDate();
            pdAssociation.setCreateTime(createTime);

            pdAssociation.setStatus('0');

            /**
             * 新增
             */
            pdAssociationMapper.insertPdAssoci(pdAssociation);
            return AjaxResult.success("新增成功!");
        }else {
            return AjaxResult.error("该产品已存在，请核对后再添加!");
        }
    }

    /**
     * 批量添加
     * @param pdAssociations
     * @return
     */
    @Transactional
    @Override
    public AjaxResult insertPdAssociList(List<PdAssociation> pdAssociations) {
        for (PdAssociation pdAssociation : pdAssociations) {
            AjaxResult ajaxResult = insertPdAssoci(pdAssociation);
            if ("该产品已存在，请核对后再添加!".equals(ajaxResult.get("msg"))) {
                return AjaxResult.error("已存在部分或全部产品，请检查后重新添加!");
            }
        }
        return AjaxResult.success("添加成功!");
    }

    /**
     * 查询关联产品列表
     * @param pdAssociation
     * @return
     */
    @Override
    public List<PdAssociation> queryPdAssociList(PdAssociation pdAssociation) {
        return pdAssociationMapper.queryPdAssociList(pdAssociation);
    }

    /**
     * 关联产品
     * @param pdAssociation
     * @return
     */
    @Transactional
    @Override
    public AjaxResult toRelation(PdAssociation pdAssociation) {
        /**
         * 状态为0才可以关联
         */
        Character statsu = pdAssociationMapper.selectPdAssociByStatus(pdAssociation.getId()).getStatus();
        if ("0".equals(statsu.toString())){
            /**
             * 更新人、时间、状态
             */
            pdAssociation.setStatus('1');
            pdAssociation.setUpdateTime(DateUtils.getNowDate());
            String updateBy = SecurityUtils.getUsername();
            pdAssociation.setUpdateBy(updateBy);

            pdAssociationMapper.toRelation(pdAssociation);
            return AjaxResult.success("关联成功!");
        }else {
            return AjaxResult.error("关联失败，状态为" + statsu);
        }
    }

    /**
     * 查询内部产品
     * @param pdProduceManage
     * @return
     */
    @Override
    public List<PdProduceManage> selectPdProduct(PdProduceManage pdProduceManage) {
        return pdAssociationMapper.selectPdProduct(pdProduceManage);
    }

    @Override
    public AjaxResult queryDrawing(String annexName, String annexUrl) {
        return null;
    }

    /**
     * 编辑产品关联
     * @param pdAssociation
     * @return
     */
    @Transactional
    @Override
    public AjaxResult editPdAssoci(PdAssociation pdAssociation) {

        Character status = pdAssociationMapper.selectPdAssociByStatus(pdAssociation.getId()).getStatus();
        if ("0".equals((status.toString()))){
            /**
             * 更新时间、更新人
             */
            String updateBy = SecurityUtils.getUsername();
            pdAssociation.setUpdateBy(updateBy);
            pdAssociation.setUpdateTime(DateUtils.getNowDate());

            pdAssociationMapper.editPdAssoci(pdAssociation);
            return AjaxResult.success("编辑成功！");
        } else {
            return AjaxResult.error("编辑失败，状态为" + status);
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Transactional
    @Override
    public AjaxResult deletePdAssoci(Long id) {
        PdAssociation pdAssociation = pdAssociationMapper.selectPdAssicoById(id);
        if (!Objects.isNull(pdAssociation)){
            pdAssociationMapper.deletePdAssoci(id);
            return AjaxResult.success("删除成功!");
        }else {
            return AjaxResult.error("删除失败,产品不存在!");
        }
    }

    /**
     * 查看内部产品信息
     * @param pdId
     * @return
     */
    @Override
    public Object queryInnerPdInfo(String pdId)  {
        //判断内部产品类型
        String type = pdAssociationMapper.selectTypeByPdid(pdId);
        if ("B".equals(type)) {
            //半成品
            return pdAssociationMapper.selectSemiByPdid(pdId);
        } else if ("C".equals(type)) {
            //成品
            return pdAssociationMapper.selectNoByPdid(pdId);
        } else {
            return AjaxResult.error("未知产品类型！");
        }
    }
}
