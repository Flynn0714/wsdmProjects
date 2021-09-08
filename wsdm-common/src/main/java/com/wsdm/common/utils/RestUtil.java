package com.wsdm.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 王瑞
 * @description rest接口操作类（spring RestTemplate）
 * @create 2019-8-16 15:59:03
 **/
public class RestUtil {

    private static RestTemplate restTemplate;

    static {
        restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        HttpMessageConverter<?> converterTarget = null;
        for (HttpMessageConverter<?> item : converterList) {
            if (StringHttpMessageConverter.class == item.getClass()) {
                converterTarget = item;
                break;
            }
        }
        if (null != converterTarget) {
            converterList.remove(converterTarget);
        }
        converterList.add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    /**
     * 调用查询接口
     *
     * @param uri
     * @param getParameters
     * @param responseType
     * @param <T>
     * @return
     */
    public static <T> T getForEntity(String uri, MultiValueMap<String, String> getParameters, Class<T> responseType) {
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(uri, responseType, getParameters);
        if (HttpStatus.OK == responseEntity.getStatusCode()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException(uri + " get failed , response code is " + responseEntity.getStatusCode());
        }

    }


    /**
     * 调用新增接口
     *
     * @param uri
     * @param postParameters
     * @param headers
     * @param responseType
     * @param <T>
     * @return
     */
    public static <T> T postForEntity(String uri, MultiValueMap<String, String> postParameters, HttpHeaders headers, Class<T> responseType) {
        if (headers == null) {
            headers = new HttpHeaders();
            headers.add("X-Auth-Token", UUID.randomUUID().toString());
        }
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
                postParameters, headers);
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(uri, requestEntity, responseType);
        if (HttpStatus.OK == responseEntity.getStatusCode()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException(uri + " post failed , response code is " + responseEntity.getStatusCode());
        }

    }

    /**
     * 调用新增接口
     *
     * @param uri
     * @param postParameters
     * @param headers
     * @param responseType
     * @param <T>
     * @return
     */
    public static <T> T postForObjectEntity(String uri, Map<String, Object> postParameters, HttpHeaders headers, Class<T> responseType) {
        if (headers == null) {
            headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            headers.set("Connection", "Close");
            headers.add("X-Auth-Token", UUID.randomUUID().toString());
        }
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(
                postParameters, headers);

        ResponseEntity<T> responseEntity = restTemplate.postForEntity(uri, requestEntity, responseType);
        if (HttpStatus.OK == responseEntity.getStatusCode()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException(uri + " post failed , response code is " + responseEntity.getStatusCode());
        }

    }


    /**
     * 调用修改接口
     *
     * @param uri
     * @param putParameters
     * @param headers
     * @return
     */
    public static void putForEntity(String uri, MultiValueMap<String, String> putParameters, HttpHeaders headers) {
        if (headers == null) {
            headers = new HttpHeaders();
            headers.add("X-Auth-Token", UUID.randomUUID().toString());
        }
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
                putParameters, headers);
        restTemplate.put(uri, requestEntity, putParameters);
    }


    /**
     * http post 接口
     *
     * @param uri          uri
     * @param headers      请求头
     * @param responseType 返回结果类型
     * @param <T>          返回结果类型
     * @return <T>
     */
    public static <T> T postForEntity(String uri, JSONObject postParameters, HttpHeaders headers, Class<T> responseType) {
        RestTemplate template = new RestTemplate();
        if (headers == null) {
            headers = new HttpHeaders();
            headers.add("X-Auth-Token", UUID.randomUUID().toString());
        }
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(
                postParameters, headers);
        ResponseEntity<T> responseEntity = template.postForEntity(uri, requestEntity, responseType);
        if (HttpStatus.OK == responseEntity.getStatusCode()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException(uri + " post failed , response code is " + responseEntity.getStatusCode());
        }

    }

    /**
     * 调用删除接口
     *
     * @param uri
     * @param parameters
     * @param headers
     * @return
     */
    public static void deleteForEntity(String uri, MultiValueMap<String, String> parameters, HttpHeaders headers) {
        if (headers == null) {
            headers = new HttpHeaders();
            headers.add("X-Auth-Token", UUID.randomUUID().toString());
        }
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
                parameters, headers);
        restTemplate.delete(uri, requestEntity, parameters);
    }

    public static <T> T getForEntity(String uri, String parameters, HttpHeaders headers, Class<T> responseType) {
        if (headers == null) {
            headers = new HttpHeaders();
        }
        headers.setContentType(MediaType.valueOf("application/json;UTF-8"));
        HttpEntity<String> strEntity = new HttpEntity<String>(parameters, headers);
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(uri, responseType, strEntity);
        if (HttpStatus.OK == responseEntity.getStatusCode()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException(uri + " post failed , response code is " + responseEntity.getStatusCode());
        }
    }

    public static <T> T postForEntity(String uri, String parameters, HttpHeaders headers, Class<T> responseType) {

        if (headers == null) {
            headers = new HttpHeaders();
        }
        headers.setContentType(MediaType.valueOf("application/json;UTF-8"));
        HttpEntity<String> strEntity = new HttpEntity<String>(parameters, headers);
        return restTemplate.postForObject(uri, strEntity, responseType);
    }

}
