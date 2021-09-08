package com.wsdm.pd.service;

import com.wsdm.pd.domain.PdMaterialReceiving;
import com.wsdm.pd.domain.PdMaterialReceivingVO;
import com.wsdm.stock.domain.SkStock;

import java.util.List;

/**
 * 物料领取单Service接口
 *
 * @author wsdm
 * @date 2021-05-11
 */
public interface IPdMaterialReceivingService
{
    /**
     * 查询物料领取单
     *
     * @param id 物料领取单ID
     * @return 物料领取单
     */
    public PdMaterialReceivingVO selectPdMaterialReceivingById(Long id);

    /**
     * 查询物料领取单列表
     *
     * @param pdMaterialReceiving 物料领取单
     * @return 物料领取单集合
     */
    public List<PdMaterialReceiving> selectPdMaterialReceivingList(PdMaterialReceiving pdMaterialReceiving);

    /**
     * 新增物料领取单
     *
     * @param vo 物料领取单
     * @return 结果
     */
    public int insertPdMaterialReceiving(PdMaterialReceivingVO vo);

    /**
     * 修改物料领取单
     *
     * @param vo 物料领取单
     * @return 结果
     */
    public int updatePdMaterialReceiving(PdMaterialReceivingVO pdMaterialReceiving);

    /**
     * 批量删除物料领取单
     *
     * @param ids 需要删除的物料领取单ID
     * @return 结果
     */
    public int deletePdMaterialReceivingByIds(Long[] ids);

    /**
     * 删除物料领取单信息
     *
     * @param id 物料领取单ID
     * @return 结果
     */
    public int deletePdMaterialReceivingById(Long id);

    int deleteSafe(Long id, String userId);

    int recover(Long id, String userId);

    int submit(Long id, String username);

    int pass(Long id, String username);

    int reject(Long id, String username);

    List<SkStock> selectAvailableMaterial(SkStock stock);
}
