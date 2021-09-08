package com.wsdm.pd.mapper;

import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.pd.domain.PdAssociation;
import com.wsdm.pd.domain.PdProduceManage;
import com.wsdm.pd.domain.PdSemiProduceManage;
import com.wsdm.sale.domain.SalesOrderDeliveryDetails;


import javax.swing.text.SimpleAttributeSet;
import java.util.List;

/**
 * 产品关联 mapper
 * PdAssociationMapper
 * @author ffl
 * @date 2021年8月27日09:44:05
 */
public interface PdAssociationMapper {



    /**
     * 产品关联查询
     * 产品编号模糊查询 名称模糊查询 规格模糊查询 产品类型下拉框查询
     * @param pdAssociation
     * @return
     */
    public List<PdAssociation> queryPdAssociList(PdAssociation pdAssociation);

    /**
     * 新增产品关联
     * @param pdAssociation
     * @return
     */
    public int insertPdAssoci(PdAssociation pdAssociation);

    /**
     * 关联产品
     * long id 本产品id
     * String pdId 内部产品id
     * @param pdAssociation
     * @return
     */
    public int toRelation(PdAssociation pdAssociation);

    /**
     * 编辑产品关联
     * @param pdAssociation
     * @return
     */
    public int editPdAssoci(PdAssociation pdAssociation);

    /**
     * 删除产品关联
     * @param id
     * @return
     */
    public int deletePdAssoci(Long id);

    /**
     * 通过产品规格查询是否已经存在该产品
     */
    public PdAssociation selectPdAssociBySpecs(String specs);

    /*
     * 最大编号
     *
     * */
    public String getMaxCode(String code);

    /**
     * 查询是否可以编辑，状态为0可编辑
     * @param id
     * @return
     */
    public PdAssociation selectPdAssociByStatus(Long id);

    /**
     * 查询内部产品
     */
    public List<PdProduceManage> selectPdProduct(PdProduceManage pdProduceManage);

    /**
     * 根据id查询产品，不存在则不可删除
     * @param id
     * @return
     */
    PdAssociation selectPdAssicoById(Long id);

    /**
     * 根据pdid查找内部产品类型
     * @param pdId
     * @return
     */
    public String selectTypeByPdid(String pdId);

    /**
     * 根据pdid查找成品编码
     * @param pdId
     * @return
     */
    PdProduceManage selectNoByPdid(String pdId);

    /**
     * 根据pdid查找半成品信息
     * @param pdId
     * @return
     */
    PdSemiProduceManage selectSemiByPdid(String pdId);

    /**
     *
     * @param materialNumber
     */
    String selectInnerPdIdByNo(String materialNumber);

}
