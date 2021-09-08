package com.wsdm.materiel.service.impl;

import com.wsdm.materiel.mapper.MaterialsMapper;
import com.wsdm.materiel.service.MaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 物料查询
 *
 * @author wangr
 * @version 1.0
 * @date 2021/6/19 15:05
 */
@Service
public class MaterialsServiceImpl implements MaterialsService {
    @Autowired
    private MaterialsMapper materialsMapper;

    @Override
    public List<Map> salesList(String type, String number, String name) {
        return materialsMapper.salesList(type,number, name);
    }

    @Override
    public List<Map> purchaseList(String type, String number, String name) {
        return materialsMapper.purchaseList(type,number, name);
    }

    @Override
    public List<Map> salesListByRelation(String type, String number, String name) {
        return materialsMapper.salesListByRelation(type,number,name);
    }
}
