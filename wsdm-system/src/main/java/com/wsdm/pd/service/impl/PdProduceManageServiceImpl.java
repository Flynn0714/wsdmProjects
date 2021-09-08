package com.wsdm.pd.service.impl;

import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.bean.BeanUtils;
import com.wsdm.pd.domain.*;
import com.wsdm.pd.mapper.*;
import com.wsdm.pd.service.IPdProduceManageService;
import com.wsdm.pd.service.IPdSemiProduceManageService;
import com.wsdm.produce.domain.FittingsMange;
import com.wsdm.produce.mapper.FittingsMangeMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 产品管理Service业务层处理
 *
 * @author wsdm
 * @date 2021-06-21
 */
@Service
public class PdProduceManageServiceImpl implements IPdProduceManageService {
    public static final String DELETED = "1";
    public static final String NORMAL  = "0";

    @Autowired
    private PdProduceManageMapper     pdProduceManageMapper;
    @Autowired
    private PdProduceAnnexMapper      pdProduceAnnexMapper;
    @Autowired
    private PdProduceDetailsMapper    pdProduceDetailsMapper;
    //    @Autowired
//    private PdRawMaterialMapper       pdRawMaterialMapper;
    @Autowired
    private PdSemiProduceManageMapper pdSemiProduceManageMapper;
    @Autowired
    private PdProduceHardwareMapper   pdProduceHardwareMapper;
    @Autowired
    private FittingsMangeMapper       fittingsMangeMapper;
    @Autowired
    private IPdSemiProduceManageService iPdSemiProduceManageService;

    @PostConstruct
    public void test() {
        selectPdProduceManageById(49L);
    }

    /**
     * 查询产品管理
     *
     * @param id 产品管理ID
     * @return 产品管理
     */
    @Override
    public PdProduceManageVO selectPdProduceManageById(Long id) {
        PdProduceManageVO pdProduceManageVO = new PdProduceManageVO();
        PdProduceManage pdProduceManage = pdProduceManageMapper.selectPdProduceManageById(id);
        pdProduceManageVO.setPdProduceManage(pdProduceManage);
        if (pdProduceManage == null) {
            return pdProduceManageVO;
        }

        PdProduceAnnex param0 = new PdProduceAnnex();
        param0.setProduceId(id.toString());
        param0.setStatus(NORMAL);
        pdProduceManageVO.setPdProduceAnnexList(pdProduceAnnexMapper.selectPdProduceAnnexList(param0));

        PdProduceDetails param1 = new PdProduceDetails();
        param1.setProduce(pdProduceManage.getProduceNumber());
        List<PdProduceDetails> detailsList = pdProduceDetailsMapper.selectPdProduceDetailsList(param1);
        if (CollectionUtils.isNotEmpty(detailsList)) {
            List<PdSemiProduceManageVO> pdSemiProduceManageVOList = new ArrayList<>();
            List<PdProduceHardwareVO> pdProduceHardwareVOList = new ArrayList<>();
            for (PdProduceDetails details : detailsList) {
                if ("1".equals(details.getType())) {
                    PdSemiProduceManage pdSemiProduceManage = pdSemiProduceManageMapper.selectPdSemiProduceManageById(Long.valueOf(details.getRef()));
                    pdSemiProduceManageVOList.add(new PdSemiProduceManageVO(pdSemiProduceManage, details));
                    continue;
                }
                if ("2".equals(details.getType())) {
                    PdProduceHardware pdProduceHardware = pdProduceHardwareMapper.selectPdProduceHardwareById(Long.valueOf(details.getRef()));
                    pdProduceHardwareVOList.add(new PdProduceHardwareVO(pdProduceHardware, details));
                }
            }

            pdProduceManageVO.setPdSemiProduceManageList(pdSemiProduceManageVOList);
            pdProduceManageVO.setPdProduceHardwareList(pdProduceHardwareVOList);
        }

        return pdProduceManageVO;
    }

    /**
     * 查询产品管理列表
     *
     * @param pdProduceManage 产品管理
     * @return 产品管理
     */
    @Override
    public List<PdProduceManage> selectPdProduceManageList(PdProduceManage pdProduceManage) {
        return pdProduceManageMapper.selectPdProduceManageList(pdProduceManage);
    }

    /**
     * 新增产品管理
     *
     * @param pdProduceManageVO 产品管理
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PdProduceManageVO insertPdProduceManage(PdProduceManageVO pdProduceManageVO) {
        PdProduceManage pdProduceManage = pdProduceManageVO.getPdProduceManage();

        PdProduceManage param = new PdProduceManage();
        param.setProduceNumber(pdProduceManage.getProduceNumber());
        param.setStatus(NORMAL);
        if (CollectionUtils.isNotEmpty(pdProduceManageMapper.selectPdProduceManageList(param))) {
            throw new BaseException("该产品已存在，不允许添加！");
        }

        pdProduceManage.setStatus(NORMAL);
        pdProduceManage.setCreateTime(new Date());
        pdProduceManageMapper.insertPdProduceManage(pdProduceManage);

        saveAnnex(pdProduceManage, pdProduceManageVO.getPdProduceAnnexList(), pdProduceManage.getCreateBy());
        saveSemiProduce(pdProduceManage, pdProduceManageVO.getPdSemiProduceManageList(), pdProduceManage.getCreateBy());
        saveProduceHardware(pdProduceManage, pdProduceManageVO.getPdProduceHardwareList(),
                pdProduceManage.getCreateBy());

        return pdProduceManageVO;
    }

    /**
     * 修改产品管理
     *
     * @param pdProduceManageVO 产品管理
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updatePdProduceManage(PdProduceManageVO pdProduceManageVO) {
        PdProduceManage pdProduceManage = pdProduceManageVO.getPdProduceManage();
        PdProduceManage old = pdProduceManageMapper.selectPdProduceManageById(pdProduceManage.getId());
        if (old == null) {
            return 0;
        }
        pdProduceManage.setUpdateTime(new Date());
        pdProduceManageMapper.updatePdProduceManage(pdProduceManage);
        saveAnnex(pdProduceManage, pdProduceManageVO.getPdProduceAnnexList(), pdProduceManage.getUpdateBy());
        saveSemiProduce(pdProduceManage, pdProduceManageVO.getPdSemiProduceManageList(), pdProduceManage.getUpdateBy());
        saveProduceHardware(pdProduceManage, pdProduceManageVO.getPdProduceHardwareList(),
                pdProduceManage.getUpdateBy());

        return 1;
    }

    /**
     * 批量删除产品管理
     *
     * @param ids 需要删除的产品管理ID
     * @return 结果
     */
    @Override
    public int deletePdProduceManageByIds(Long[] ids) {
        return pdProduceManageMapper.deletePdProduceManageByIds(ids);
    }

    /**
     * 删除产品管理信息
     *
     * @param id 产品管理ID
     * @return 结果
     */
    @Override
    public int deletePdProduceManageById(Long id) {
        return pdProduceManageMapper.deletePdProduceManageById(id);
    }

    @Override
    public int deleteSafe(Long id, String userId) {
        PdProduceManage param = new PdProduceManage();
        param.setId(id);
        param.setStatus(DELETED);
        param.setUpdateBy(userId);
        param.setUpdateTime(new Date());
        return pdProduceManageMapper.updatePdProduceManage(param);
    }

    @Override
    public int recover(Long id, String userId) {
        PdProduceManage param = new PdProduceManage();
        param.setId(id);
        param.setStatus(NORMAL);
        param.setUpdateBy(userId);
        param.setUpdateTime(new Date());
        return pdProduceManageMapper.updatePdProduceManage(param);
    }

    private void saveProduceHardware(PdProduceManage pdProduceManage, List<PdProduceHardwareVO> pdProduceHardwareList,
            String operator) {
        PdProduceDetails param0 = new PdProduceDetails();
        param0.setProduce(pdProduceManage.getProduceNumber());
        param0.setType("2");
        List<PdProduceDetails> oldList = pdProduceDetailsMapper.selectPdProduceDetailsList(param0);
        List<Long> deleting = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(oldList)) {
            deleting.addAll(oldList.stream().map(PdProduceDetails::getId).collect(Collectors.toList()));
        }

        for (PdProduceHardwareVO vo : pdProduceHardwareList) {

            PdProduceDetails pdProduceDetails = vo.getPdProduceDetails();
            PdProduceHardware pdProduceHardware = vo.getPdProduceHardware();
            saveProduceHardware(pdProduceHardware, operator);
            saveFittingsManage(pdProduceHardware, vo.getPdProduceDetails().getNum(), operator);

            PdProduceDetails old = CollectionUtils.find(oldList, old0 -> old0.getId().equals(pdProduceDetails.getId()));
            if (old == null) {
                pdProduceDetails.setProduce(pdProduceManage.getProduceNumber());
                pdProduceDetails.setType("2");
                pdProduceDetails.setRef(pdProduceHardware.getId().toString());
                pdProduceDetails.setCreateBy(operator);
                pdProduceDetails.setCreateTime(new Date());
                pdProduceDetailsMapper.insertPdProduceDetails(pdProduceDetails);
                continue;
            }

            PdProduceDetails param = new PdProduceDetails();
            param.setId(old.getId());
            param.setNum(pdProduceDetails.getNum());
            param.setUpdateBy(operator);
            param.setUpdateTime(new Date());
            pdProduceDetailsMapper.updatePdProduceDetails(param);
            deleting.remove(old.getId());
        }

        deleting.forEach(pdProduceDetailsMapper::deletePdProduceDetailsById);
    }

    private void saveProduceHardware(PdProduceHardware pdProduceHardware, String operator) {
        PdProduceHardware param = new PdProduceHardware();
        param.setTypeCode(pdProduceHardware.getTypeCode());
        param.setModel(pdProduceHardware.getModel());
        param.setStatus(NORMAL);
        List<PdProduceHardware> oldList = pdProduceHardwareMapper.selectPdProduceHardwareList(param);
        if (CollectionUtils.isEmpty(oldList)) {
            pdProduceHardware.setCode(pdProduceHardware.getTypeCode() + "-" + pdProduceHardware.getModel());
            pdProduceHardware.setStatus(NORMAL);
            pdProduceHardware.setCreateBy(operator);
            pdProduceHardware.setCreateTime(new Date());
            pdProduceHardwareMapper.insertPdProduceHardware(pdProduceHardware);
        } else {
            BeanUtils.copyBeanProp(pdProduceHardware, oldList.get(0));
        }
    }

    private void saveFittingsManage(PdProduceHardware pdProduceHardware, Integer num, String operator) {
        FittingsMange params = new FittingsMange();
        params.setFittingNumber(pdProduceHardware.getCode());
        params.setStatus(NORMAL);
        List<FittingsMange> fittingsManges = fittingsMangeMapper.selectFittingsMangeList(params);
        if (CollectionUtils.isEmpty(fittingsManges)) {
            FittingsMange fittingsMange = new FittingsMange();
            fittingsMange.setFittingNumber(pdProduceHardware.getCode());
            fittingsMange.setFittingName(pdProduceHardware.getTypeName());
            fittingsMange.setFittingModel(pdProduceHardware.getModel());
            fittingsMange.setNum(num);
            fittingsMange.setCreateBy(SecurityUtils.getUsername());
            fittingsMange.setCreateTime(new Date());
            fittingsMange.setStatus(NORMAL);
            fittingsMangeMapper.insertFittingsMange(fittingsMange);
            return;
        }

        FittingsMange updating = new FittingsMange();
        updating.setId(fittingsManges.get(0).getId());
        updating.setFittingName(pdProduceHardware.getTypeName());
        updating.setFittingModel(pdProduceHardware.getModel());
        updating.setNum(num);
        updating.setUpdateTime(new Date());
        updating.setUpdateBy(operator);
        fittingsMangeMapper.updateFittingsMange(updating);
    }

    private void saveSemiProduce(PdProduceManage pdProduceManage, List<PdSemiProduceManageVO> pdSemiProduceManageList,
            String operator) {
        PdProduceDetails param0 = new PdProduceDetails();
        param0.setProduce(pdProduceManage.getProduceNumber());
        param0.setType("1");
        List<PdProduceDetails> oldList = pdProduceDetailsMapper.selectPdProduceDetailsList(param0);
        List<Long> deleting = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(oldList)) {
            deleting.addAll(oldList.stream().map(PdProduceDetails::getId).collect(Collectors.toList()));
        }

        for (PdSemiProduceManageVO vo : pdSemiProduceManageList) {
            PdProduceDetails pdProduceDetails = vo.getPdProduceDetails();
            PdSemiProduceManage pdSemiProduceManage = vo.getPdSemiProduceManage();
            saveSemiProduce(pdSemiProduceManage, operator);

            PdProduceDetails old = CollectionUtils.find(oldList, old0 -> old0.getId().equals(pdProduceDetails.getId()));
            if (old == null) {
                System.out.println("+++++++++++++++++++" + pdSemiProduceManage);
                pdProduceDetails.setProduce(pdProduceManage.getProduceNumber());
                pdProduceDetails.setType("1");
                try{
                pdProduceDetails.setRef(pdSemiProduceManage.getId().toString());
                } catch (NullPointerException e){
                    String semiProduceNumber = pdSemiProduceManage.getSemiProduceNumber();
                    PdSemiProduceManage pdSemiProduceManage1 = pdSemiProduceManageMapper.selectPdSemiByNumber(semiProduceNumber);
                    pdProduceDetails.setRef(pdSemiProduceManage1.getId().toString());
                }
                pdProduceDetails.setCreateBy(operator);
                pdProduceDetails.setCreateTime(new Date());
                pdProduceDetailsMapper.insertPdProduceDetails(pdProduceDetails);
                continue;
            }

            PdProduceDetails param = new PdProduceDetails();
            param.setId(old.getId());
            param.setNum(pdProduceDetails.getNum());
            param.setUpdateBy(operator);
            param.setUpdateTime(new Date());
            pdProduceDetailsMapper.updatePdProduceDetails(param);
            deleting.remove(old.getId());
        }

        deleting.forEach(pdProduceDetailsMapper::deletePdProduceDetailsById);
    }

    private void saveSemiProduce(PdSemiProduceManage pdSemiProduceManage, String operator) {
        List<PdSemiProduceManage> oldList = null;
        if (pdSemiProduceManage.getId() != null) {
            PdSemiProduceManage param = new PdSemiProduceManage();
            param.setId(pdSemiProduceManage.getId());
            param.setStatus(NORMAL);
            oldList = pdSemiProduceManageMapper.selectPdSemiProduceManageList(param);
        }
        if (CollectionUtils.isEmpty(oldList)) {
            pdSemiProduceManage.setStatus(NORMAL);
            pdSemiProduceManage.setCreateBy(operator);
            pdSemiProduceManage.setCreateTime(new Date());
            iPdSemiProduceManageService.insertPdSemiProduceManage(pdSemiProduceManage);

//            PdRawMaterial pdRawMaterial = JSON.parseObject(JSON.toJSONString(pdSemiProduceManage), PdRawMaterial.class);
//            pdRawMaterial.setSemiProduceNumber(pdRawMaterial.getSemiProduceNumber() + ".M");
//            pdRawMaterial.setSemiProduceName(pdRawMaterial.getSemiProduceName() + "[毛坯]");
//            pdRawMaterialMapper.insertPdRawMaterial(pdRawMaterial);
            return;
        }

        PdSemiProduceManage param = new PdSemiProduceManage();
        param.setId(oldList.get(0).getId());
        param.setSemiProduceNumber(pdSemiProduceManage.getSemiProduceNumber());
        param.setSemiProduceName(pdSemiProduceManage.getSemiProduceName());
        param.setMaterialQuality(pdSemiProduceManage.getMaterialQuality());
        param.setModulusToothNumber(pdSemiProduceManage.getModulusToothNumber());
        param.setUpdateBy(operator);
        param.setUpdateTime(new Date());
        pdSemiProduceManageMapper.updatePdSemiProduceManage(param);

//        String materialCode = oldList.get(0).getSemiProduceNumber() + ".M";
//        PdRawMaterial param1 = new PdRawMaterial();
//        param1.setSemiProduceNumber(materialCode);
//        param1.setStatus(NORMAL);
//        List<PdRawMaterial> pdRawMaterialList = pdRawMaterialMapper.selectPdRawMaterialList(param1);
//        if (CollectionUtils.isNotEmpty(pdRawMaterialList)) {
//            param1 = new PdRawMaterial();
//            param1.setSemiProduceNumber(materialCode);
//            param1.setSemiProduceName(pdSemiProduceManage.getSemiProduceName() + "[毛坯]");
//            param1.setUpdateBy(operator);
//            param1.setUpdateTime(new Date());
//            pdRawMaterialMapper.updatePdRawMaterial(param1);
//        }
    }

    private void saveAnnex(PdProduceManage pdProduceManage, List<PdProduceAnnex> pdProduceAnnexList, String operator) {
        PdProduceAnnex param0 = new PdProduceAnnex();
        param0.setProduceId(pdProduceManage.getId().toString());
        param0.setStatus(NORMAL);
        List<PdProduceAnnex> oldList = pdProduceAnnexMapper.selectPdProduceAnnexList(param0);
        List<Long> deleting = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(oldList)) {
            deleting.addAll(oldList.stream().map(PdProduceAnnex::getId).collect(Collectors.toList()));
        }

        List<PdProduceAnnex> adding = new ArrayList<>();
        for (PdProduceAnnex annex : pdProduceAnnexList) {
            PdProduceAnnex old = CollectionUtils.find(oldList, old0 -> old0.getId().equals(annex.getId()));
            if (old == null) {
                annex.setProduceId(pdProduceManage.getId().toString());
                annex.setStatus(NORMAL);
                annex.setCreateBy(operator);
                annex.setCreateTime(new Date());
                adding.add(annex);
                continue;
            }
            deleting.remove(old.getId());
        }
        adding.forEach(pdProduceAnnexMapper::insertPdProduceAnnex);

        for (Long id : deleting) {
            PdProduceAnnex param = new PdProduceAnnex();
            param.setId(id);
            param.setStatus(DELETED);
            param.setUpdateBy(operator);
            param.setUpdateTime(new Date());
            pdProduceAnnexMapper.updatePdProduceAnnex(param);
        }
    }
}
