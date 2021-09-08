package com.wsdm.produce.service.impl;

import com.google.common.collect.Lists;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.ProduceStatus;
import com.wsdm.common.enums.ResultEnums;
import com.wsdm.common.enums.WisdomBusinessStatus;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.produce.domain.*;
import com.wsdm.produce.mapper.*;
import com.wsdm.produce.service.IProduceManageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.wsdm.common.constant.Constants.MP;

/**
 * 产品管理Service业务层处理
 *
 * @author wsdm
 * @date 2020-11-06
 */
@Service
public class ProduceManageServiceImpl implements IProduceManageService {
    @Autowired
    private ProduceManageMapper produceManageMapper;

    @Autowired
    private FittingsMangeMapper fittingsMangeMapper;

    @Autowired
    private SemiProduceManageMapper semiProduceManageMapper;

    @Autowired
    private RawMaterialMapper rawMaterialMapper;

    @Autowired
    private ProduceModelMapper produceModelMapper;


    /**
     * 查询产品管理
     *
     * @param id 产品管理ID
     * @return 产品管理
     */
    @Override
    public ProduceManage selectProduceManageById(Long id) {
        return produceManageMapper.selectProduceManageById(id);
    }

    @Override
    public ProduceManageVO selectProduceManageVoById(Long id) {
        ProduceManageVO produceManageVO = produceManageMapper.selectProduceManageVoById(id);

        if (produceManageVO == null || produceManageVO.getId() == null) {
            return produceManageVO;
        }

        List<FittingsMange> fittingsManges = fittingsMangeMapper.getFittingsByProduceNumber(produceManageVO.getProduceNumber());
        List<SemiProduceManage> semiProduceManages = semiProduceManageMapper.getSemiProduceByProduceNumber(produceManageVO.getProduceNumber());

        produceManageVO.setFittingsManges(fittingsManges);
        produceManageVO.setSemiProduceManages(semiProduceManages);

        return produceManageVO;
    }

    /**
     * 查询产品管理列表
     *
     * @param produceManage 产品管理
     * @return 产品管理
     */
    @Override
    public List<ProduceManage> selectProduceManageList(ProduceManage produceManage) {
        return produceManageMapper.selectProduceManageList(produceManage);
    }

    /**
     *
     * 引入标准产品
     * @author wangr
     * @date 2020/11/19 19:25
     * @return com.wsdm.common.core.domain.AjaxResult
     */
    @Override
    public AjaxResult introduceProduce(ProduceManage produceManage) {
        produceManage.setStatus(WisdomBusinessStatus.STATUS_NORMAL.getCode());
        List<ProduceManageVO> produceManageVOList = produceManageMapper.selectProduceManageVOList(produceManage);
        produceManageVOList.stream().forEach(produceManageVO -> {
            List<SemiProduceManage> semiProduceManageLists = semiProduceManageMapper.getSemiProduceByProduceNumber(produceManageVO.getProduceNumber());
            List<FittingsMange> fittingsManges = fittingsMangeMapper.getFittingsByProduceNumber(produceManageVO.getProduceNumber());

            produceManageVO.setSemiProduceManages(semiProduceManageLists);
            produceManageVO.setFittingsManges(fittingsManges);
        });

        return AjaxResult.success(produceManageVOList);
    }

    /**
     * 新增产品管理
     * 1.部分参数不为空
     * 2.该产品是否存在  型号 + 速比 + 装配形式确定唯一
     * 3.该产品中的配件是否存在 配件名称 + 型号确定唯一  如果不存在入配件资料库
     * 4.该产品中半成品是否存在 半成品名称 + 类型 + 材质 + 模数齿数 + 规格确定唯一  不存在入半成品资料库
     * 5.生成产品型号入产品型号库
     * 6.生成整个产品入库
     *
     * @param produceManage 产品管理
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult insertProduceManage(ProduceManageVO produceManage) {

        List<FittingsMange> fittingsMangeList = produceManage.getFittingsManges();
        List<SemiProduceManage> semiProduceManageList = produceManage.getSemiProduceManages();

        if (CollectionUtils.isEmpty(fittingsMangeList) || CollectionUtils.isEmpty(semiProduceManageList)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }

        String produceNumber = produceManage.getProduceModel() + "-" + produceManage.getSpeedRatio() + "-" + produceManage.getAssembleModality();

        ProduceManage produce = produceManageMapper.getProduceNumber(produceNumber);
        if (produce != null && produce.getId() != null) {
            return AjaxResult.error(ResultEnums.PRODUCE_EXIST);
        }

        ProduceModel produceModel = new ProduceModel();
        List<RawMaterial> rawMaterialList = Lists.newArrayList();
        List<ProduceRef> produceRefs = Lists.newArrayList();

        assemble(produceNumber, produceManage, semiProduceManageList, fittingsMangeList, produceModel, produceRefs, rawMaterialList);

        // 产品的型号 由产品主表生成
        ProduceModel model = produceModelMapper.selectProduceModelByModel(produceManage.getProduceModel());
        if (model == null || model.getId() == null)
            produceModelMapper.insertProduceModel(produceModel);

        // 原材料 由半成品生成
        if (!CollectionUtils.isEmpty(rawMaterialList))
            rawMaterialMapper.insertRawMaterialBatch(rawMaterialList);
        // 配件
        if (!CollectionUtils.isEmpty(fittingsMangeList))
            fittingsMangeMapper.insertFittingsMangeBatch(fittingsMangeList);
        // 半成品
        if (!CollectionUtils.isEmpty(semiProduceManageList))
            semiProduceManageMapper.insertSemiProduceManageBatch(semiProduceManageList);

        produceManage.setProduceNumber(produceNumber);
        produceManage.setCreateTime(DateUtils.getNowDate());
        produceManage.setCreateBy(SecurityUtils.getUsername());
        produceManage.setStatus(WisdomBusinessStatus.STATUS_NORMAL.getCode());
        // 产品详情关联
        produceManageMapper.insertProduceDetailsBatch(produceRefs);
        int result = produceManageMapper.insertProduceManage(produceManage);

        if (result <= 0) {
            throw new BaseException("产品资料新增错误！");
        } else {
            return AjaxResult.success(produceManage.getId());
        }
    }

    /**
     * 修改产品管理
     *
     * @param produceManage 产品管理
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateProduceManage(ProduceManageVO produceManage) {

        if (produceManage == null || produceManage.getId() == null) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }

        List<FittingsMange> fittingsMangeList = produceManage.getFittingsManges();
        List<SemiProduceManage> semiProduceManageList = produceManage.getSemiProduceManages();

        if (CollectionUtils.isEmpty(fittingsMangeList) || CollectionUtils.isEmpty(semiProduceManageList)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }

        String produceNumber = produceManage.getProduceModel() + "-" + produceManage.getSpeedRatio() + "-" + produceManage.getAssembleModality();

        ProduceManage produce = produceManageMapper.getProduceNumber(produceNumber);
        if (produce != null && !produce.getProduceNumber().equals(produceManage.getProduceNumber())) {
            return AjaxResult.error(ResultEnums.PRODUCE_EXIST);
        }

        ProduceModel produceModel = new ProduceModel();
        List<RawMaterial> rawMaterialList = Lists.newArrayList();
        List<ProduceRef> produceRefs = Lists.newArrayList();

        assemble(produceNumber, produceManage, semiProduceManageList, fittingsMangeList, produceModel, produceRefs, rawMaterialList);

        // 产品的型号 有产品主表生成
        ProduceModel model = produceModelMapper.selectProduceModelByModel(produceManage.getProduceModel());
        if (model == null || model.getId() == null)
            produceModelMapper.insertProduceModel(produceModel);
        // 产品资料详情关联表先删除在新增
        produceManageMapper.deleteProduceDetailsByProduceNumber(produceNumber);

        if (!CollectionUtils.isEmpty(rawMaterialList))
            rawMaterialMapper.insertRawMaterialBatch(rawMaterialList);
        if (!CollectionUtils.isEmpty(fittingsMangeList))
            fittingsMangeMapper.insertFittingsMangeBatch(fittingsMangeList);
        if (!CollectionUtils.isEmpty(semiProduceManageList))
            semiProduceManageMapper.insertSemiProduceManageBatch(semiProduceManageList);

        produceManage.setProduceNumber(produceNumber);
        produceManage.setUpdateTime(DateUtils.getNowDate());
        produceManage.setUpdateBy(SecurityUtils.getUsername());
        produceManageMapper.insertProduceDetailsBatch(produceRefs);
        int result = produceManageMapper.updateProduceManage(produceManage);

        if (result <= 0) {
            throw new BaseException("产品资料修改错误！");
        } else {
            return AjaxResult.success();
        }

    }

    public void assemble(String produceNumber, ProduceManage produceManage,
                         List<SemiProduceManage> semiProduceManageList, List<FittingsMange> fittingsMangeList,
                         ProduceModel produceModel, List<ProduceRef> produceRefs, List<RawMaterial> rawMaterialList) {

        fittingsMangeList.stream().forEach(fittingsMange -> {
            fittingsMange.setFittingNumber(fittingsMange.getFittingName()
                    + "-" + fittingsMange.getFittingModel());
            fittingsMange.setCreateTime(DateUtils.getNowDate());
            fittingsMange.setCreateBy(SecurityUtils.getUsername());
            fittingsMange.setStatus(WisdomBusinessStatus.STATUS_NORMAL.getCode());

            ProduceRef produceRef = new ProduceRef(
                    ProduceStatus.PRODUCE_FITTINGS.getCode(),
                    produceNumber,
                    fittingsMange.getFittingNumber(),
                    fittingsMange.getNum()
            );
            produceRefs.add(produceRef);
        });

        semiProduceManageList.stream().forEach(semi -> {
            semi.setSemiProduceNumber(semi.getSemiProduceName()
                    + "-" + semi.getSemiProduceModel()
                    + "-" + semi.getMaterialQuality()
                    + "-" + semi.getModulusToothNumber()
                    + "-" + semi.getSpecifications());
            semi.setCreateBy(SecurityUtils.getUsername());
            semi.setCreateTime(DateUtils.getNowDate());
            semi.setStatus(WisdomBusinessStatus.STATUS_NORMAL.getCode());

            ProduceRef produceRef = new ProduceRef(
                    ProduceStatus.PRODUCE_SEMI.getCode(),
                    produceNumber,
                    semi.getSemiProduceNumber(),
                    semi.getNum()
            );
            produceRefs.add(produceRef);
        });

        List<String> fittingsNumberList = fittingsMangeList.stream()
                .map(fittingsMange -> fittingsMange.getFittingNumber())
                .collect(Collectors.toList());

        List<String> semiNumberList = semiProduceManageList.stream()
                .map(semi -> semi.getSemiProduceNumber())
                .collect(Collectors.toList());

        List<FittingsMange> fittings = fittingsMangeMapper.fittingInfoByListFittingNumber(fittingsNumberList);
        List<SemiProduceManage> semis = semiProduceManageMapper.semiInfoByListBySemiNumber(semiNumberList);

        Iterator<FittingsMange> fs = fittingsMangeList.iterator();
        while (fs.hasNext()) {
            FittingsMange next = fs.next();
            fittings.stream().forEach(f -> {
                if (next.getFittingNumber().equals(f.getFittingNumber())) {
                    fs.remove();
                }
            });
        }

        Iterator<SemiProduceManage> sms = semiProduceManageList.iterator();
        while (sms.hasNext()) {
            SemiProduceManage next = sms.next();
            semis.stream().forEach(sm -> {
                if (next.getSemiProduceNumber().equals(sm.getSemiProduceNumber())) {
                    sms.remove();
                }
            });
        }

        semiProduceManageList.stream().forEach(semi -> {
            RawMaterial rawMaterial = new RawMaterial();
            BeanUtils.copyProperties(semi, rawMaterial, RawMaterial.class);
            rawMaterial.setSemiProduceName(semi.getSemiProduceName() + MP);
            rawMaterialList.add(rawMaterial);
        });


        produceModel.setModelNumber(produceNumber);
        produceModel.setModel(produceManage.getProduceModel());
        produceModel.setStatus(WisdomBusinessStatus.STATUS_NORMAL.getCode());
        produceModel.setCreateBy(produceManage.getCreateBy());
        produceModel.setCreateTime(produceManage.getCreateTime());
    }

    /**
     * 批量删除产品管理
     *
     * @param ids 需要删除的产品管理ID
     * @return 结果
     */
    @Override
    public int deleteProduceManageByIds(Long[] ids) {
        return produceManageMapper.deleteProduceManageByIds(ids);
    }

    /**
     * 删除产品管理信息
     *
     * @param id 产品管理ID
     * @return 结果
     */
    @Override
    public int deleteProduceManageById(Long id) {
        return produceManageMapper.deleteProduceManageById(id);
    }
}
