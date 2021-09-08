package com.wsdm.materiel.service;

import java.util.List;
import java.util.Map;

/**
 *  物料查询
 *
 * @author wangr
 * @version 1.0
 * @date 2021/6/19 15:04
 */
public interface MaterialsService {

    List<Map> salesList(String type, String number, String name);
    List<Map> purchaseList(String type, String number, String name);
    List<Map> salesListByRelation(String type, String number, String name);

}
