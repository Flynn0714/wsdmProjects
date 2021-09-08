package com.wsdm.materiel.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.wsdm.common.CodeUtils;
import com.wsdm.common.constant.Constants;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.enums.*;
import com.wsdm.common.exception.BaseException;
import com.wsdm.common.utils.DateUtils;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.StringUtils;
import com.wsdm.materiel.domain.*;
import com.wsdm.materiel.mapper.PurchaseOneTouchMapper;
import com.wsdm.materiel.mapper.PurchaseOrderDetailMapper;
import com.wsdm.materiel.mapper.PurchaseOrderInfoMapper;
import com.wsdm.materiel.service.IPurchaseOrderManageService;
import com.wsdm.materiel.service.PurchaseReturnInfoManageService;
import com.wsdm.materiel.service.QualityInspectionManageService;
import com.wsdm.pd.domain.PdRawMaterial;
import com.wsdm.pd.service.IPdRawMaterialService;
import com.wsdm.stock.domain.SkStockRequest;
import com.wsdm.stock.service.ISkStockService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 物料采购订单信息Service业务层处理
 *
 * @author wsdm
 * @date 2020-11-26
 */
@Service
public class PurchaseOrderManageServiceImpl implements IPurchaseOrderManageService {

    private static final String STRING_0 = "0";
    private static final String STRING_1 = "1";
    private static final String STRING_2 = "2";
    private static final String STRING_3 = "3";
    private static final String STRING_4 = "4";
    private static final String STRING_5 = "5";

    @Autowired
    private PurchaseOrderInfoMapper purchaseOrderInfoMapper;
    @Autowired
    private PurchaseOrderDetailMapper purchaseOrderDetailMapper;
    @Autowired
    private ISkStockService skStockService;
    @Autowired
    private ReceiveOrderManageServiceImpl receiveOrderManageService;
    @Autowired
    private QualityInspectionManageService qualityInspectionManageService;
    @Autowired
    private SupplierContractManageServiceImpl supplierContractManageService;
    @Autowired
    private SupplierContractChangeManageServiceImpl supplierContractChangeManageService;
    @Autowired
    private PurchaseReturnInfoManageService purchaseReturnInfoService;
    @Autowired
    private IPdRawMaterialService pdRawMaterialService;
    @Autowired
    private PurchaseOneTouchMapper oneMapper;

    /**
     * 自动生成
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int selectMobilePurchaseOneTouch() {
        int errorValue = 3;//误差值
        //一键生成采购单
        //查询已审核未结束的销售单，已发货的去除已发数量
        //返回未发货的产品数量：销售单，产品编码，类型，数量
        List<Map<String,Object>> orderList =  oneMapper.selectProductQuantity();
        if(orderList==null||orderList.isEmpty()){
            return 1;
        }
        //查询库存
        List<Map<String,Object>> temporaryList = new ArrayList<>();
        //五金需要数量
        List<Map<String,Object>> hardwareList = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            if(orderList.get(i).get("code").toString().equals("C")){
                temporaryList.addAll(oneMapper.selectSemiProduceNumber(orderList.get(i).get("material_number").toString().trim(),orderList.get(i).get("sales_number").toString().trim()));
                hardwareList.addAll(oneMapper.selectHardwareList(orderList.get(i).get("material_number").toString().trim(),orderList.get(i).get("sales_number").toString().trim()));
            }
        }
        //去除重复的半成品
        for (int i = 0; i < orderList.size(); i++) {
            String produceNumber=  orderList.get(i).get("material_number").toString();
            for (int j = 0; j < temporaryList.size(); j++) {
                if(temporaryList.get(j)==null) continue;
                if(produceNumber.equals(temporaryList.get(j).get("material_number").toString().trim())){
                    orderList.get(i).put("num",Integer.parseInt(orderList.get(i).get("num").toString())+
                            Integer.parseInt(temporaryList.get(j).get("num").toString()));
                    temporaryList.set(j,null);
                }
            }
        }
        temporaryList.removeIf(Objects::isNull);
        orderList.addAll(temporaryList);//剩下不重复的半成品

        //获取不等于0的产品库存数量
        //code，产品编码，数量
        List<Map<String,Object>> stockList =  oneMapper.selectStockNumber();
        //获取五金库存
        List<Map<String,Object>> hardwareStockList =  oneMapper.selectHardwareStockNumber();
        Set<String> salesNumberSet = new HashSet<String>();

        //循环结束后得出最终需要采购的物品及数量。
        for (int i = 0; i < orderList.size(); i++) {
            if(orderList.get(i)==null)continue;
            Map<String,Object> order = orderList.get(i);
            if(order.get("code").toString().equals("C")){
                orderList.set(i,null);
                continue;
            }
            String[] material = order.get("material_number").toString().trim().split("_",0);
            String quality = "";//材质
            if(material.length<3){//表示没规格
                String[] a = material[1].split(".",0);
                quality = a[a.length-1];
            }
            String[] parameterOrder = material[1].split("-",0);
            for (int j = 0; j < stockList.size(); j++) {
                if(stockList.get(j)==null)continue;
                Map<String,Object> stock =stockList.get(j);
                String[] stockMaterial = stock.get("produce").toString().trim().split("_",0);
                if(order.get("material_number").toString().trim().
                   equals(stock.get("produce").toString().trim())){
                    //如果物料相等
                    //需求数量减去库存数量
                    int num  = Integer.parseInt(order.get("num").toString()) -
                            Integer.parseInt(stock.get("stock_available_num").toString());
                    //大于零表示需求超出,放入新的需求数量
                    if(num>0){
                        order.put("num",num);
                    }else{
                        stockList.set(j,null);
                        orderList.set(i,null);
                        break;//满足要求并且库存足够进行下一个订单物品循环
                    }
                }
                //如果未匹配进行第二次查找
                //以下进行匹配的是原料,不是原料不使用
                if(!stock.get("code").toString().trim().equals("A"))continue;
                //拆分长度不匹配下一个
                if(material.length!=stockMaterial.length)continue;
                //拆分类型不配下一个
                if(!material[0].equals(stockMaterial[0]))continue;
                //标准拆分状态下（3个）
                if(material.length>2){
                    //规格材质不配下一个
                    if(!material[2].equals(stockMaterial[2]))continue;
                }else if(!"".equals(quality) && quality!=null){//特殊情况
                    if(stockMaterial[1].indexOf("."+quality)==-1)continue;//匹配特殊情况
                }
                //200*200*150
                //160*940
                //9m83z-140*770.75*180-L
                //6m18z-121  .45#
                //物料编码规则类型_参数_规格.材质
                //获取参数：9m16z-165.25*464 中的165.25*464
                String[] parameterStock = stockMaterial[1].split("-",0);
                //开始参数拆分匹配
                int opten = 0;
                if(parameterOrder.length>1 && parameterStock.length>1){
                    if(!parameterOrder[0].equals(parameterStock[0]))continue;//横杠前内容不配下一个
                    opten = 1;
                }
                String[] orderNumber=parameterOrder[opten].split("\\*",0);//拆分出来的是数字
                String[] stockNumber=parameterStock[opten].split("\\*",0);//库存拆分

                if(orderNumber.length!=stockNumber.length)continue;//两个数据数组长度相等才进行比对
                try{
                    int count = 0;
                    for (int k = 0; k < orderNumber.length; k++) {
                        if(Double.parseDouble(stockNumber[k])>Double.parseDouble(orderNumber[k])-errorValue&&
                                Double.parseDouble(stockNumber[k])<Double.parseDouble(orderNumber[k])+errorValue){
                            count++;
                        }
                    }
                    if(count>orderNumber.length){
                        int num  = Integer.parseInt(order.get("num").toString()) -
                                Integer.parseInt(stock.get("stock_available_num").toString());
                        if(num>0){
                            order.put("num",num);
                        }else{
                            stockList.set(j,null);
                            orderList.set(i,null);
                            break;
                        }
                    }
                }catch (Exception e){//报错就是转换int出错，不比较了
                    continue;
                }
                stockList.set(j,stock);
            }
            orderList.set(i,order);
            if(orderList.get(i)==null)continue;
            salesNumberSet.add(orderList.get(i).get("sales_number").toString().trim());
        }
        orderList.removeIf(Objects::isNull);

        for (int i = 0; i < hardwareList.size(); i++) {
            String code = hardwareList.get(i).get("code").toString();
            for (int j = 0; j < hardwareStockList.size(); j++) {
                if (code.equals(hardwareStockList.get(i).get("produce").toString())){
                    int num = Integer.parseInt(hardwareList.get(i).get("num").toString())-
                              Integer.parseInt(hardwareStockList.get(j).get("stock_available_num").toString());
                    //库存不够
                    if (num>0){
                        hardwareList.get(i).put("num", num);
                    }else{
                        hardwareList.set(i,null);
                    }
                }
            }
        }
        hardwareList.removeIf(Objects::isNull);

        //采购物品进行记录并标记已采购完成的订单
        if(salesNumberSet==null||salesNumberSet.isEmpty()){
            return 1;
        }
        oneMapper.updateSalesOrderTags(salesNumberSet);
        Date createTime = DateUtils.getNowDate();
        String createUser = SecurityUtils.getUsername();
        List<SalesOrderAutomaticallyGenerate> aList = new ArrayList<>();
        List<String> materialNumber=new ArrayList<>(orderList.size());
        Map<String,String> materialHash = new HashMap<>();
        int count = 0;
        for (int i = 0; i < orderList.size(); i++) {
            int num = Integer.parseInt(orderList.get(i).get("num").toString());
            String material = orderList.get(i).get("material_number").toString();
            materialHash.put(material,String.valueOf(num));
            SalesOrderAutomaticallyGenerate ag = new SalesOrderAutomaticallyGenerate();
            ag.setSalesNumber(orderList.get(i).get("sales_number").toString());
            ag.setMaterialNumber(material);
            materialNumber.add(material);
            ag.setCode(orderList.get(i).get("code").toString());
            ag.setNum(String.valueOf(num));
            count+=num;
            ag.setCreateTime(createTime);
            ag.setUseUser(createUser);
            aList.add(ag);
        }
        //五金采购
        int hardwareCount=0;
        Map<String,String> hardwareHash = new HashMap<>();
        for (int i = 0; i < hardwareList.size(); i++) {
            int num = Integer.parseInt(hardwareList.get(i).get("num").toString());
            hardwareHash.put(hardwareList.get(i).get("code").toString(),String.valueOf(num));
            SalesOrderAutomaticallyGenerate ag = new SalesOrderAutomaticallyGenerate();
            ag.setSalesNumber(hardwareList.get(i).get("sales_number").toString());
            ag.setMaterialNumber(hardwareList.get(i).get("code").toString());
            ag.setCode("D");
            ag.setNum(String.valueOf(num));
            hardwareCount += num;
            ag.setCreateTime(createTime);
            ag.setUseUser(createUser);
            aList.add(ag);
        }
        if(aList==null||aList.isEmpty()){
            return 1;
        }
        oneMapper.insertSalesOrderAutomaticallyGenerate(aList);


        //最终需要采购的物品  orderList
        //生成采购单
        PurchaseOrderInfoVo purchaseInfo = new PurchaseOrderInfoVo();
        List<PurchaseOrderDetail> purchaseDetails = new ArrayList<PurchaseOrderDetail>();

        String createBy = "系统自动生成";
        String prefix = Constants.PURCHASE_NUMBER_PREFIX + DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date());
        String maxCode = purchaseOrderInfoMapper.getMaxPurchaseCode(prefix);
        purchaseInfo.setCreateBy(createBy);
        purchaseInfo.setCreateTime(createTime);
        // 操作渠道：1-仅保存
        purchaseInfo.setStatus(PurchaseOrderStatus.NOT_SUBMIT.getCode());
        // 编码
        purchaseInfo.setPurchaseNumber(CodeUtils.getOneCode(prefix, maxCode, 3));
        purchaseInfo.setPurchaseDate(createTime);
        purchaseInfo.setTotalPurchaseNum(BigDecimal.valueOf(count));
        int result = purchaseOrderInfoMapper.insertPurchaseOrderInfo(purchaseInfo);
        if (result <= 0) {
            throw new BaseException("采购订单新增失败！");
        }
        //查询物料信息
        List<SemiProduceEntity> Semi = oneMapper.selectSemiProduceEntity(materialNumber);
        for (SemiProduceEntity e :Semi){
            PurchaseOrderDetail detail = new PurchaseOrderDetail();
            detail.setPurchaseNumber(purchaseInfo.getPurchaseNumber());//单号
            detail.setMaterielNumber(e.getSemiProduceNumber());
            detail.setMaterielName(e.getSemiProduceName());
            detail.setModelParam(e.getModulusToothNumber());
            detail.setSpecifications(e.getSpecifications());
            detail.setWlType(e.getSemiProduceModel());
            detail.setMaterialQuality(e.getMaterialQuality());
            detail.setPurchaseNum(new BigDecimal(materialHash.get(e.getSemiProduceNumber())));
            detail.setCreateBy(createBy);//创建人
            detail.setCreateTime(createTime);//创建时间
            purchaseDetails.add(detail);
        }


        result = purchaseOrderDetailMapper.insertPurchaseOrderDetailBatch(purchaseDetails);
        if (result <= 0) {
            throw new BaseException("采购订单详情新增失败！");
        }
        /////////////////////////////////////////////////////////////////////////////////////////
        //最终需要采购的物品  hardwareList
        //生成采购单
        PurchaseOrderInfoVo hardwarePurchaseInfo = new PurchaseOrderInfoVo();
        List<PurchaseOrderDetail> hardwarePurchaseDetails = new ArrayList<PurchaseOrderDetail>();

        createBy = "系统自动生成";
        prefix = Constants.PURCHASE_NUMBER_PREFIX + DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date());
        maxCode = purchaseOrderInfoMapper.getMaxPurchaseCode(prefix);
        hardwarePurchaseInfo.setCreateBy(createBy);
        hardwarePurchaseInfo.setCreateTime(createTime);
        // 操作渠道：1-仅保存
        hardwarePurchaseInfo.setStatus(PurchaseOrderStatus.NOT_SUBMIT.getCode());
        // 编码
        hardwarePurchaseInfo.setPurchaseNumber(CodeUtils.getOneCode(prefix, maxCode, 3));
        hardwarePurchaseInfo.setPurchaseDate(createTime);
        hardwarePurchaseInfo.setTotalPurchaseNum(BigDecimal.valueOf(hardwareCount));
        result = purchaseOrderInfoMapper.insertPurchaseOrderInfo(hardwarePurchaseInfo);
        if (result <= 0) {
            throw new BaseException("采购订单新增失败！");
        }
        //查询物料信息
        //List<SemiProduceEntity> Semi = oneMapper.selectSemiProduceEntity(materialNumber);
        for (Map<String,Object> c :hardwareList){
            PurchaseOrderDetail detail = new PurchaseOrderDetail();
            detail.setPurchaseNumber(hardwarePurchaseInfo.getPurchaseNumber());//单号
            detail.setMaterielNumber(c.get("code").toString());
            detail.setMaterielName(c.get("type_name").toString());
            detail.setModelParam(c.get("model").toString());
            detail.setPurchaseNum(new BigDecimal(hardwareHash.get(c.get("code").toString())));
            detail.setCreateBy(createBy);//创建人
            detail.setCreateTime(createTime);//创建时间
            hardwarePurchaseDetails.add(detail);
        }


        result = purchaseOrderDetailMapper.insertPurchaseOrderDetailBatch(hardwarePurchaseDetails);
        if (result <= 0) {
            throw new BaseException("采购订单详情新增失败！");
        }
        return 0;
    }

    /**
     * 查询物料采购订单信息
     *
     * @param id 物料采购订单信息ID
     * @return 物料采购订单信息
     */
    @Override
    public PurchaseOrderInfoVo selectPurchaseOrderInfoById(Long id) {
        PurchaseOrderInfoVo purchaseOrderInfoVo = purchaseOrderInfoMapper.selectPurchaseOrderInfoById(id);
        if (purchaseOrderInfoVo == null || StringUtils.isEmpty(purchaseOrderInfoVo.getPurchaseNumber())) {
            return purchaseOrderInfoVo;
        }
        // 根据采购单号查询采购订单详情
        List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailMapper.selectPurchaseOrderDetailByPurchaseNumber(purchaseOrderInfoVo.getPurchaseNumber());
        purchaseOrderInfoVo.setPurchaseOrderDetails(purchaseOrderDetails);
        return purchaseOrderInfoVo;
    }

    /**
     * 查询物料采购订单信息列表
     *
     * @param purchaseOrderInfo 物料采购订单信息
     * @return 物料采购订单信息
     */
    @Override
    public List<PurchaseOrderInfo> selectPurchaseOrderInfoList(PurchaseOrderInfoVo purchaseOrderInfo) {
        return purchaseOrderInfoMapper.selectPurchaseOrderInfoList(purchaseOrderInfo);
    }

    /**
     * 查询物料采购订单信息列表
     *
     * @param purchaseOrderInfo 物料采购订单信息
     * @return 物料采购订单信息
     */
    @Override
    public List<PurchaseOrderInfoVo> selectPurchaseListForReceive(PurchaseOrderInfo purchaseOrderInfo) {
        String status = purchaseOrderInfo.getStatus();
        if (StringUtils.isEmpty(status)) {
            purchaseOrderInfo.setStatus(PurchaseOrderStatus.AUDITED.getCode());
        }
        List<PurchaseOrderInfoVo> infos = purchaseOrderInfoMapper.selectPurchaseListForReceive(purchaseOrderInfo);
        if (CollectionUtils.isNotEmpty(infos)) {
            for (PurchaseOrderInfoVo vo : infos) {
                String purchaseNumber = vo.getPurchaseNumber();
                List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailMapper.selectPurchaseOrderDetailByPurchaseNumber(purchaseNumber);
                vo.setPurchaseOrderDetails(purchaseOrderDetails);
            }
        }
        return infos;
    }

    /**
     * 获取自动采购信息
     *
     * @param salesNumberList 销售单
     * @return 获取自动采购信息
     */
    @Override
    public List<AutomaticPurchaseInfo> automaticPurchaseInfo(List<String> salesNumberList) {
        return purchaseOrderInfoMapper.automaticPurchaseInfo(salesNumberList);
    }

    /**
     * 新增物料采购订单信息
     *
     * @param purchaseInfo 物料采购订单信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult insertPurchaseOrderInfo(PurchaseOrderInfoVo purchaseInfo) {
        List<PurchaseOrderDetail> purchaseDetails = purchaseInfo.getPurchaseOrderDetails();
        if (CollectionUtils.isEmpty(purchaseDetails)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        String createBy = SecurityUtils.getUsername();
        Date createTime = DateUtils.getNowDate();
        String prefix = Constants.PURCHASE_NUMBER_PREFIX + DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date());
        String maxCode = purchaseOrderInfoMapper.getMaxPurchaseCode(prefix);

        purchaseInfo.setCreateBy(createBy);
        purchaseInfo.setCreateTime(createTime);

        // 操作渠道：1-仅保存 2-保存提审
        String optChannel = purchaseInfo.getOptChannel();

        if ("2".equals(optChannel)) {
            purchaseInfo.setStatus(PurchaseOrderStatus.WAIT_AUDIT.getCode());
            purchaseInfo.setUpdateBy(createBy);
            purchaseInfo.setUpdateTime(createTime);
        } else {
            purchaseInfo.setStatus(PurchaseOrderStatus.NOT_SUBMIT.getCode());
        }
        // 编码
        purchaseInfo.setPurchaseNumber(CodeUtils.getOneCode(prefix, maxCode, 3));
        int result = purchaseOrderInfoMapper.insertPurchaseOrderInfo(purchaseInfo);
        if (result <= 0) {
            throw new BaseException("采购订单新增失败！");
        }
        List<String> wlList = Lists.newArrayList();
        for (PurchaseOrderDetail detail : purchaseDetails) {
            if (wlList.contains(detail.getMaterielNumber())) {
                throw new BaseException("采购物料重复！");
            } else {
                wlList.add(detail.getMaterielNumber());
            }
            detail.setPurchaseNumber(purchaseInfo.getPurchaseNumber());
            detail.setCreateBy(createBy);
            detail.setCreateTime(createTime);
        }
        result = purchaseOrderDetailMapper.insertPurchaseOrderDetailBatch(purchaseDetails);
        if (result <= 0) {
            throw new BaseException("采购订单详情新增失败！");
        }
        return AjaxResult.success();
    }

    @Override
    public AjaxResult insertAutomaticPurchaseInfo(List<AutomaticPurchaseInfo> automaticPurchaseInfos) {

        automaticPurchaseInfos.stream().forEach(a -> a.setKey(a.getType() + "-" + a.getSupplierCode()));

        Function<AutomaticPurchaseInfo, String> fun = new Function<AutomaticPurchaseInfo, String>() {
            @Override
            public String apply(AutomaticPurchaseInfo input) {
                if (input.getKey() == null) {
                    return "0";
                }
                return input.getKey();
            }
        };
        Multimap<String, AutomaticPurchaseInfo> index = Multimaps.index(automaticPurchaseInfos, fun);
        Map<String, Collection<AutomaticPurchaseInfo>> map = index.asMap();

        Date date = DateUtils.getNowDate();
        String userName = SecurityUtils.getUsername();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        Date expectArrivalDate = calendar.getTime();

        String prefix = DateUtils.parseDateToStr(DateUtils.YYYYMMDD, new Date());
        String maxCode = purchaseOrderInfoMapper.getMaxAutomaticPurchaseCode(prefix);
        String batchNumber = CodeUtils.getOneCode(prefix, maxCode, 3);

        List<PurchaseOrderInfoVo> infoVoList = new ArrayList<>();
        for (Map.Entry<String, Collection<AutomaticPurchaseInfo>> result : map.entrySet()) {
            PurchaseOrderInfoVo vo = new PurchaseOrderInfoVo();
            List<PurchaseOrderDetail> purchaseDetails = new ArrayList<>();

            String purchaseType = "";
            String purchaseTypeName = "";
            BigDecimal totalPurchaseNum = new BigDecimal(0);
            BigDecimal totalPurchaseAmount = new BigDecimal(0);
            vo.setBatchNumber(batchNumber);

            List<AutomaticPurchaseInfo> list = (List<AutomaticPurchaseInfo>) result.getValue();

            String contacts = "";
            String contactNumber = "";
            String supplierCode = "";
            for (AutomaticPurchaseInfo info : list) {
                PurchaseOrderDetail detail = new PurchaseOrderDetail();
                detail.setMaterielNumber(info.getNumber());
                detail.setMaterielName(info.getName());
                detail.setModelParam(info.getModelParam());
                detail.setUnitPrice(info.getPrice());
                detail.setPurchaseNum(info.getPurchaseNum());
                detail.setContractNumber(info.getContractNumber());
                detail.setSubtotalAmount(info.getSubtotalAmount());
                detail.setCreateBy(userName);
                detail.setCreateTime(date);
                purchaseDetails.add(detail);

                contacts = info.getContacts();
                contactNumber = info.getContactNumber();
                supplierCode = info.getSupplierCode();
                purchaseTypeName = info.getTypeName();

                totalPurchaseNum = totalPurchaseNum.add(detail.getPurchaseNum());
                totalPurchaseAmount = totalPurchaseAmount.add(detail.getSubtotalAmount());
            }
            vo.setPurchaseOrderDetails(purchaseDetails);
            vo.setRemark("批量创建（批号：" + batchNumber + ")");
            vo.setSupplierCode(supplierCode);
            vo.setOptChannel("2");
            vo.setExpectArrivalDate(expectArrivalDate);
            vo.setPurchaseDate(date);
            vo.setContacts(contacts);
            vo.setContactNumber(contactNumber);
            vo.setPurchaseType(purchaseType);
            vo.setPurchaseTypeName(purchaseTypeName);
            vo.setTotalPurchaseNum(totalPurchaseNum);
            vo.setTotalPurchaseAmount(totalPurchaseAmount);
            vo.setCreateBy(userName);
            vo.setCreateTime(date);
            infoVoList.add(vo);
        }

        for (PurchaseOrderInfoVo infoVo : infoVoList) {
            insertPurchaseOrderInfo(infoVo);
        }

        return AjaxResult.success(batchNumber);
    }

    private void purchaseIntoStock(PurchaseOrderInfoVo purchaseInfoVo) {
        List<PurchaseOrderDetail> purchaseDetails = purchaseInfoVo.getPurchaseOrderDetails();
        if (CollectionUtils.isEmpty(purchaseDetails)) {
            return;
        }
        // 采购类型
        String purchaseType = purchaseInfoVo.getPurchaseType();
        List<SkStockRequest> skStocks = Lists.newArrayList();
        for (PurchaseOrderDetail detail : purchaseDetails) {
            SkStockRequest skStock = new SkStockRequest();
            skStock.setCode(purchaseType);
            skStock.setName(StockEnum.codeToName(purchaseType));
            // 物料编号
            skStock.setProduce(detail.getMaterielNumber());
            // 物料名称
            skStock.setProduceName(detail.getMaterielName());
            // 采购数量
            BigDecimal purchaseNum = detail.getPurchaseNum();
            purchaseNum = null == purchaseNum ? BigDecimal.ZERO : purchaseNum;
            skStock.setStockTotal(BigDecimal.ZERO);
            skStock.setStockTransitNum(purchaseNum);
            skStocks.add(skStock);
        }
        if (CollectionUtils.isNotEmpty(skStocks)) {
            skStockService.insertStockTransit(skStocks);
        }
    }

    /**
     * 修改物料采购订单信息
     *
     * @param purchaseInfo 物料采购订单信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updatePurchaseOrderInfo(PurchaseOrderInfoVo purchaseInfo) {
        List<PurchaseOrderDetail> purchaseDetails = purchaseInfo.getPurchaseOrderDetails();
        if (CollectionUtils.isEmpty(purchaseDetails) || StringUtils.isEmpty(purchaseInfo.getPurchaseNumber()) || null == purchaseInfo.getId()) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        PurchaseOrderInfoVo data = selectPurchaseOrderInfoById(purchaseInfo.getId());
        String purchaseStatus = data.getStatus();
        if (!PurchaseOrderStatus.NOT_SUBMIT.getCode().equals(purchaseStatus)) {
            return AjaxResult.error("订单状态非未提交，不能修改");
        }
        String updateBy = SecurityUtils.getUsername();
        Date updateTime = DateUtils.getNowDate();

        // 操作渠道：1-仅保存 2-保存提审
        String optChannel = purchaseInfo.getOptChannel();
        if ("2".equals(optChannel)) {
            purchaseInfo.setStatus(PurchaseOrderStatus.WAIT_AUDIT.getCode());
            purchaseInfo.setUpdateBy(updateBy);
            purchaseInfo.setUpdateTime(updateTime);
        } else {
            purchaseInfo.setStatus(PurchaseOrderStatus.NOT_SUBMIT.getCode());
        }
        int result = purchaseOrderInfoMapper.updatePurchaseOrderInfo(purchaseInfo);
        if (result <= 0) {
            throw new BaseException("采购订单更新失败！");
        }
        // 删除采购订单详情
        result = purchaseOrderDetailMapper.deletePurchaseOrderDetailByPurchaseNumber(purchaseInfo.getPurchaseNumber());
        if (result <= 0) {
            throw new BaseException("采购订单详情更新失败！");
        }
        List<String> wlList = Lists.newArrayList();
        for (PurchaseOrderDetail detail : purchaseDetails) {
            if (wlList.contains(detail.getMaterielNumber())) {
                throw new BaseException("采购物料重复！");
            } else {
                wlList.add(detail.getMaterielNumber());
            }
            detail.setPurchaseNumber(purchaseInfo.getPurchaseNumber());
            detail.setCreateBy(updateBy);
            detail.setCreateTime(updateTime);
        }
        result = purchaseOrderDetailMapper.insertPurchaseOrderDetailBatch(purchaseDetails);
        if (result <= 0) {
            throw new BaseException("采购订单详情更新失败！");
        }
        return AjaxResult.success();
    }

    /**
     * 批量删除物料采购订单信息
     *
     * @param ids 需要删除的物料采购订单信息ID
     * @return 结果
     */
    @Override
    public AjaxResult deletePurchaseOrderInfoByIds(Long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        for (Long id : ids) {
            if (id == null) {
                return AjaxResult.error(ResultEnums.NULL_PARAM);
            }
        }
        // 查询采购订单状态
        List<PurchaseOrderInfo> data = purchaseOrderInfoMapper.selectPurchaseOrderInfoByIds(ids);
        for (PurchaseOrderInfo purchaseOrderInfo : data) {
            if (!StringUtils.equals(PurchaseOrderStatus.NOT_SUBMIT.getCode(), purchaseOrderInfo.getStatus())) {
                return AjaxResult.error("订单状态非未提交，不能作废");
            }
        }
        int result = purchaseOrderInfoMapper.deletePurchaseOrderInfoByIds(ids);
        if (result <= 0) {
            throw new BaseException("销售订单作废错误！");
        }
        return AjaxResult.success();
    }

    /**
     * 删除物料采购订单信息信息
     *
     * @param id 物料采购订单信息ID
     * @return 结果
     */
    @Override
    public int deletePurchaseOrderInfoById(Long id) {
        return purchaseOrderInfoMapper.deletePurchaseOrderInfoById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateApproveStatus(Long id, String action) {
        if (null == id || StringUtils.isBlank(action)) {
            return AjaxResult.error(ResultEnums.NULL_PARAM);
        }
        // 查询采购订单状态
        PurchaseOrderInfoVo data = selectPurchaseOrderInfoById(id);
        if (!PurchaseOrderStatus.WAIT_AUDIT.getCode().equals(data.getStatus())) {
            return AjaxResult.error("采购订单状态非待审核，不能审核");
        }
        PurchaseOrderInfo purchaseInfo = new PurchaseOrderInfo();
        purchaseInfo.setId(id);
        if ("adopt".equals(action)) {
            purchaseInfo.setAuditTime(DateUtils.getNowDate());
            purchaseInfo.setAuditBy(SecurityUtils.getUsername());
            purchaseInfo.setStatus(PurchaseOrderStatus.AUDITED.getCode());
        } else if ("cancel".equals(action)) {
            purchaseInfo.setStatus(PurchaseOrderStatus.NOT_SUBMIT.getCode());
        } else {
            return AjaxResult.error("参数格式错误");
        }
        int result = purchaseOrderInfoMapper.updateAuditStatus(purchaseInfo);
        if (result <= 0) {
            return AjaxResult.error("采购订单审核失败！");
        }
        if ("adopt".equals(action)) {
            // 原材料采购时判断资料是否已经存在不存在则新增
            if (data.getPurchaseType().equals(PurchaseTypeEnum.RAW_MATERIAL.getCode())) {
                addRawMaterialInfo(data);
            }
            // 审核完成，更新库存--在途
            purchaseIntoStock(data);
        }
        return AjaxResult.success();
    }

    private void addRawMaterialInfo(PurchaseOrderInfoVo data) {
        List<PurchaseOrderDetail> details = data.getPurchaseOrderDetails();
        if (CollectionUtils.isNotEmpty(details)) {
            for (PurchaseOrderDetail detail : details) {
                String materielNumber = detail.getMaterielNumber();
                PdRawMaterial material = new PdRawMaterial();
                material.setSemiProduceNumber(materielNumber);
                List<PdRawMaterial> pdRawMaterials = pdRawMaterialService.selectPdRawMaterialList(material);
                if (CollectionUtils.isEmpty(pdRawMaterials)) {
                    // 需要新增
                    PdRawMaterial raw = new PdRawMaterial();
                    raw.setSemiProduceNumber(materielNumber);
                    raw.setCreateBy(SecurityUtils.getUsername());
                    raw.setCreateTime(DateUtils.getNowDate());
                    raw.setStatus(WisdomBusinessStatus.STATUS_NORMAL.getCode());
                    raw.setMaterialQuality(detail.getMaterialQuality());
                    raw.setSemiProduceName(detail.getMaterielName());
                    raw.setSemiProduceModel(detail.getWlType());
                    raw.setModulusToothNumber(detail.getModelParam());
                    raw.setSpecifications(detail.getSpecifications());
                    int result = pdRawMaterialService.insertPdRawMaterial(raw);
                    if (result <= 0) {
                        throw new BaseException("原料资料创建失败！");
                    }
                }
            }
        }
    }

    @Override
    public List<?> selectMobilePurchaseApproveList(String orderType, String approveStatus) {
        // 0 采购 1 采购合同 2 采购合同变更 3 收货  4  质检  5 采购退货
        if (STRING_0.equals(orderType)) {
            PurchaseOrderInfoVo info = new PurchaseOrderInfoVo();
            // 0 待审核 1 已审核
            info.setStatus("0".equals(approveStatus) ? PurchaseOrderStatus.WAIT_AUDIT.getCode() : PurchaseOrderStatus.AUDITED.getCode());
            return purchaseOrderInfoMapper.selectPurchaseOrderInfoList(info);
        } else if (STRING_1.equals(orderType)) {
            SupplierContractVo contractVo = new SupplierContractVo();
            contractVo.setAuditStatus("0".equals(approveStatus) ? ContractAuditStatus.NOT_APPROVE.getCode() : ContractAuditStatus.APPROVED.getCode());
            return supplierContractManageService.selectSupplierContractList(contractVo);
        } else if (STRING_2.equals(orderType)) {
            SupplierContractChangeVo contractVo = new SupplierContractChangeVo();
            contractVo.setChangeStatus("0".equals(approveStatus) ? ContractAuditStatus.NOT_APPROVE.getCode() : ContractAuditStatus.APPROVED.getCode());
            return supplierContractChangeManageService.selectSupplierContractChangeList(contractVo);
        } else if (STRING_3.equals(orderType)) {
            ReceiveOrderInfoVo orderInfoVo = new ReceiveOrderInfoVo();
            orderInfoVo.setStatus("0".equals(approveStatus) ? ReceiveOrderStatus.WAIT_AUDIT.getCode() : ReceiveOrderStatus.AUDITED.getCode());
            return receiveOrderManageService.selectReceiveOrderInfoList(orderInfoVo);
        } else if (STRING_4.equals(orderType)) {
            QualityInspectionInfoVo infoVo = new QualityInspectionInfoVo();
            infoVo.setStatus("0".equals(approveStatus) ? ReceiveOrderStatus.WAIT_AUDIT.getCode() : ReceiveOrderStatus.AUDITED.getCode());
            return qualityInspectionManageService.selectQualityInspectionInfoList(infoVo);
        } else if (STRING_5.equals(orderType)) {
            PurchaseReturnInfoVo infoVo = new PurchaseReturnInfoVo();
            infoVo.setStatus("0".equals(approveStatus) ? ReturnOrderStatus.WAIT_APPROVE.getCode() : ReturnOrderStatus.APPROVED.getCode());
            return purchaseReturnInfoService.selectPurchaseReturnInfoList(infoVo);
        }
        return null;
    }

    @Override
    public AjaxResult updateMobilePurchaseApproveStatus(Long id, String orderType, String action, String approvedType) {
        // 0 采购 1 采购合同 2 采购合同变更 3 收货  4  质检  5 采购付款
        if (STRING_0.equals(orderType)) {
            return updateApproveStatus(id, action);
        } else if (STRING_1.equals(orderType)) {
            return supplierContractManageService.updateApproveStatus(id, action);
        } else if (STRING_2.equals(orderType)) {
            return supplierContractChangeManageService.updateApproveStatus(id, action);
        } else if (STRING_3.equals(orderType)) {
            return receiveOrderManageService.updateApproveStatus(id, action);
        } else if (STRING_4.equals(orderType)) {
            return qualityInspectionManageService.updateApproveStatus(id, action, approvedType);
        } else if (STRING_5.equals(orderType)) {
            return purchaseReturnInfoService.updateApproveStatus(id, action);
        }
        return AjaxResult.success();
    }

    @Override
    public Object selectMobilePurchaseApproveInfoById(Long id, String orderType) {
        // 0 采购 1 采购合同 2 采购合同变更 3 收货  4  质检  5 采购付款
        if (STRING_0.equals(orderType)) {
            return selectPurchaseOrderInfoById(id);
        } else if (STRING_1.equals(orderType)) {
            return supplierContractManageService.selectSupplierContractById(id);
        } else if (STRING_2.equals(orderType)) {
            return supplierContractChangeManageService.selectSupplierContractChangeById(id);
        } else if (STRING_3.equals(orderType)) {
            return receiveOrderManageService.selectReceiveOrderInfoById(id);
        } else if (STRING_4.equals(orderType)) {
            return qualityInspectionManageService.selectQualityInspectionInfoById(id);
        } else if (STRING_5.equals(orderType)) {
            return purchaseReturnInfoService.selectReturnOrderInfoById(id);
        }
        return null;
    }
}
