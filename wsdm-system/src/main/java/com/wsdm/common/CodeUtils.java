package com.wsdm.common;

import com.google.common.base.Strings;
import com.wsdm.common.exception.CustomException;
import com.wsdm.common.utils.StringUtils;
import com.wsdm.materiel.domain.Supplier;

import java.util.List;

public class CodeUtils {

    public static void getCodeList(String code, String maxCode, List list, int index) {
        int maxNumber = 0;

        for (int i = 0; i < list.size(); i++) {

            if (maxCode != null) {
                String number = maxCode.replace(code, "");
                maxNumber = Integer.parseInt(number);
            }

            maxNumber++;

            code = code + StringUtils.spliceValue(maxNumber, index);

            if (list.get(i) instanceof Supplier) {
                ((Supplier) list.get(i)).setSupplierCode(code);
            } else {
                throw new CustomException("客户编码生成失败！");
            }
        }
    }

    public static String getOneCode(String code, String maxCode, int index) {
        int maxNumber = 0;

        if (maxCode != null) {
            String number = maxCode.replace(code, "");
            maxNumber = Integer.parseInt(number);
        }
        maxNumber++;
        code = code + StringUtils.spliceValue(maxNumber, index);
        if (Strings.isNullOrEmpty(code)) {
            throw new CustomException("编码生成失败");
        }
        return code;
    }
}


