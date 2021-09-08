package com.wsdm.pd.service;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.pd.domain.PdAssociation;
import com.wsdm.pd.domain.PdProduceManage;

import java.util.List;

public interface IPdAssociationService {

    /**
     * 新增产品关联
     * @param pdAssociation
     * @return
     */
    public AjaxResult insertPdAssoci(PdAssociation pdAssociation);

    /**
     * 批量添加产品关联
     * @param pdAssociations
     * @return
     */
    public AjaxResult insertPdAssociList(List<PdAssociation> pdAssociations);

    /**
     * 产品关联查询
     * 产品编号模糊查询 名称模糊查询 规 产品类型下拉框查询格模糊查询
     * @param pdAssociation
     * @return
     */
    public List<PdAssociation> queryPdAssociList(PdAssociation pdAssociation);

    /**
     * 关联产品
     * long id 本产品id
     * String pdId 内部产品id
     * @param id
     * @param pdId
     * @return
     */
    public AjaxResult toRelation(PdAssociation pdAssociation);

    /**
     * 查询内部产品
     * 根据产品编号（模糊）、类型（下拉）查询
     * @param pdProduceManage
     * @return
     */
    public List<PdProduceManage> selectPdProduct(PdProduceManage pdProduceManage);

    /**
     * 查看图纸
     * @param annexName
     * @param annexUrl
     * @return
     */
    public AjaxResult queryDrawing(String annexName,String annexUrl);

    /**
     * 编辑产品关联
     * @param pdAssociation
     * @return
     */
    public AjaxResult editPdAssoci(PdAssociation pdAssociation);

    /**
     * 删除产品关联
     * @param id
     * @return
     */
    public AjaxResult deletePdAssoci(Long id);

    /**
     * 查看内部产品信息
     * @param pdId
     * @return
     */
    public Object queryInnerPdInfo(String pdId);
}
