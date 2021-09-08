package com.wsdm.controller.common;

import com.google.common.base.Strings;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.domain.model.LoginBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author wangr
 * @version 1.0
 * @date 2021/5/12 22:23
 */
@RestController
public class WxController {
    protected final Logger logger = LoggerFactory.getLogger(WxController.class);

    @Autowired
    private WxService wxService;

    @PostMapping(value = "/getUserOpenIdByCode/v1.01" , produces = {"application/json;charset=UTF-8"})
    public AjaxResult getUserOpenIdByGrantCode(@RequestBody WxAppInfoVO wxAppInfoVO) {
        logger.debug("Enter the method WxUserController.getUserOpenIdByGrantCode RequestBody=" + String.valueOf(wxAppInfoVO));
        if (null == wxAppInfoVO || Strings.isNullOrEmpty(wxAppInfoVO.getCode())) {
            return AjaxResult.error("参数为空");
        }
        return wxService.getUserOpenIdByGrantCode(wxAppInfoVO.getCode());
    }

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/wxBinding")
    public AjaxResult wxBinding(@RequestBody LoginBody loginBody) {
        return wxService.wxBinding(loginBody);
    }

    @PostMapping("/wxLogin")
    public AjaxResult wxLogin(@RequestBody LoginBody loginBody) {
        return wxService.wxLogin(loginBody);
    }
}
