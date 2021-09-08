package com.wsdm.controller.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.wsdm.common.constant.Constants;
import com.wsdm.common.core.domain.AjaxResult;
import com.wsdm.common.core.domain.entity.SysUser;
import com.wsdm.common.core.domain.model.LoginBody;
import com.wsdm.common.core.redis.RedisCache;
import com.wsdm.common.utils.RestUtil;
import com.wsdm.common.utils.SecurityUtils;
import com.wsdm.common.utils.http.HttpUtils;
import com.wsdm.framework.web.service.SysLoginService;
import com.wsdm.system.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author wangr
 * @version 1.0
 * @date 2021/5/12 22:28
 */
@Service
public class WxService {

    private static final Logger logger = LoggerFactory.getLogger(WxService.class);

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private RedisCache redisCache;

    @Value("${wx.secret}")
    private String secret;

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.tokenCacheTime:200}")
    private long cacheTime;

    public AjaxResult getUserOpenIdByGrantCode(String code) {
        if (Strings.isNullOrEmpty(secret)) {
            return AjaxResult.error("配置的微信秘钥为空");
        }
        String uri = Constants.WX_API_GET_OPENID_BYCODE;
        //获取用户openId
        String resultData = HttpUtils.sendGet(uri, "appid=" + appId + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code");
        JSONObject baseResult = JSON.parseObject(resultData);
        String openid = baseResult.getString("openid");
        redisCache.setCacheObject(Constants.REDIS_KEY_PREFIX_OPENID + openid, openid, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        return AjaxResult.success(baseResult);
    }

    @Transactional(rollbackFor = Exception.class)
    public AjaxResult wxBinding(LoginBody loginBody) {
        SysUser user = userMapper.selectUserByUserName(loginBody.getUsername());
        if (user == null) {
            return AjaxResult.error("该用户不存在！");
        } else if (!SecurityUtils.matchesPassword(loginBody.getPassword(), user.getPassword())) {
            return AjaxResult.error("用户名和密码不匹配！");
        } else if (!Strings.isNullOrEmpty(user.getOpenid())) {
            return AjaxResult.error("该用户已绑定，不可重复绑定");
        }

        // 绑定
        userMapper.updateUserOpenid(loginBody.getUsername(), loginBody.getOpenid());

        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword());
        return AjaxResult.success(Constants.TOKEN, token);
    }

    public AjaxResult wxLogin(LoginBody loginBody) {
        String realAuthCode = redisCache.getCacheObject(Constants.REDIS_KEY_PREFIX_OPENID + loginBody.getOpenid());
        if (Strings.isNullOrEmpty(realAuthCode)) {
            return AjaxResult.error("请重新获取openid");
        } else if (!loginBody.getOpenid().equals(realAuthCode)) {
            return AjaxResult.error("openid错误");
        } else {
            SysUser entity = userMapper.checkOpenidUnique(loginBody.getOpenid());
            String token = loginService.login(loginBody.getOpenid(), "CUSTOM_LOGIN_WX");
            AjaxResult result = AjaxResult.success("登录成功！", entity);
            result.put(Constants.TOKEN, token);
            //登陆成功后清除redis缓存
            redisCache.deleteObject(Constants.REDIS_KEY_PREFIX_OPENID + loginBody.getOpenid());
            return result;
        }
    }


    /**
     * 获取access_token
     */
    public AjaxResult getAccessToken() {
        logger.info("Enter the method WxService.getAccessToken appId=" + appId);
        //未超时，则直接返回；
        if (Long.parseLong(accessTokenMap.get(Constants.ACCESS_TOKEN_TIMEOUT_KEY)) > System.currentTimeMillis()) {
            logger.info("Quite the method WxService.getAccessToken");
            return AjaxResult.success("", accessTokenMap.get(Constants.ACCESS_TOKEN_KEY));
        }
        //超时则重新获取
        String uri = Constants.WX_API_ACCESS_TOKEN.replace("APPID", appId).replace("APPSECRET", secret);
        MultiValueMap<String, String> parameterMap = new LinkedMultiValueMap<>();
        JSONObject resultData = RestUtil.getForEntity(uri, parameterMap, JSONObject.class);
        if (resultData.containsKey("errcode")) {
            logger.info("Quite the method WxService.getAccessToken resultData=" + resultData.toString());
            return new AjaxResult(resultData.getString("errcode"), resultData.getString("errmsg"));
        }
        String access_token = resultData.getString("access_token");
        logger.info("WxService.getAccessToken parameter uri=" + uri + " access_token=" + access_token);

        accessTokenMap.put(Constants.ACCESS_TOKEN_KEY, access_token);
        //最新的超时时间=当前程序执行获取的access_token时间+微信access_token超时时间
        Long latestOutTime = System.currentTimeMillis() + cacheTime * 1000L;
        accessTokenMap.put(Constants.ACCESS_TOKEN_TIMEOUT_KEY, String.valueOf(latestOutTime));
        logger.info("Quite the method WxService.getAccessToken resultData=" + resultData.toJSONString());
        return AjaxResult.success("", access_token);
    }

    /**
     * 有时效的静态缓存
     */
    private static Map<String, String> accessTokenMap = new HashMap<String, String>() {
        {
            put(Constants.ACCESS_TOKEN_KEY, "");
            put(Constants.ACCESS_TOKEN_TIMEOUT_KEY, String.valueOf(System.currentTimeMillis()));
        }
    };

}
