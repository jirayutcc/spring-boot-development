package com.spring.development.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RestTemplateService {
    public static final String REST_STATUS = "status";
    public static final String REST_STATUS_OK = "ok";
    public static final String REST_STATUS_FAIL = "fail";
    public static final String REST_CONTENT = "content";
    public static final String REST_MESSAGE = "message";

    /** GET **/
    public static Map<String, Object> callGetForEntity(String url) {
        HttpHeaders headers = setHeader();
        return getForEntity(url, headers, Map.class);
    }

    public static Map<String, Object> callGetForEntity(String url, Class clazz) {
        HttpHeaders headers = setHeader();
        return getForEntity(url, headers, clazz);
    }

    public static Map<String, Object> callGetForEntity(String url, String appId, String token) {
        HttpHeaders headers = setHeaderByToken(appId, token);
        return getForEntity(url, headers, Map.class);
    }

    public static Map<String, Object> callGetForEntity(String url, String appId, String token, Class clazz) {
        HttpHeaders headers = setHeaderByToken(appId, token);
        return getForEntity(url, headers, clazz);
    }

    public static Map<String, Object> callGetForEntityBasic(String url, String password) {
        HttpHeaders headers = setHeaderBasic(password);
        return getForEntity(url, headers, Map.class);
    }

    public static Map<String, Object> callGetForEntityBasic(String url, String password, Class clazz) {
        HttpHeaders headers = setHeaderBasic(password);
        return getForEntity(url, headers, clazz);
    }

    public static Map<String, Object> callGetExchange(String url, String appId, String token) {
        HttpHeaders headers = setHeaderByToken(appId, token);
        return exchange(url, headers, HttpMethod.GET, Map.class);
    }

    public static Map<String, Object> callGetExchange(String url, String appId, String token, Class clazz) {
        HttpHeaders headers = setHeaderByToken(appId, token);
        return exchange(url, headers, HttpMethod.GET, clazz);
    }

    public static Map<String, Object> callGetExchangeAuthorization(String url, String token, Class clazz) {
        HttpHeaders headers = setHeaderAuthorization(token);
        return exchange(url, headers, HttpMethod.GET, clazz);
    }

    /** POST **/
    public static Map<String, Object> callPostForEntity(String url, Object payload) {
        HttpHeaders headers = setHeader();
        return postForEntity(url, payload, headers, Map.class);
    }

    public static Map<String, Object> callPostForEntity(String url, Object payload, Class clazz) {
        HttpHeaders headers = setHeader();
        return postForEntity(url, payload, headers, clazz);
    }

    public static Map<String, Object> callPostForEntity(String url, Object payload, String appId, String token) {
        HttpHeaders headers = setHeaderByToken(appId, token);
        return postForEntity(url, payload, headers, Map.class);
    }

    public static Map<String, Object> callPostForEntity(String url, Object payload, String appId, String token, Class clazz) {
        HttpHeaders headers = setHeaderByToken(appId, token);
        return postForEntity(url, payload, headers, clazz);
    }

    public static Map<String, Object> callPostForEntityBasic(String url, Object payload, String password) {
        HttpHeaders headers = setHeaderBasic(password);
        return postForEntity(url, payload, headers, Map.class);
    }

    public static Map<String, Object> callPostForEntityBasic(String url, Object payload, String password, Class clazz) {
        HttpHeaders headers = setHeaderBasic(password);
        return postForEntity(url, payload, headers, clazz);
    }

    public static Map<String, Object> callPostExchangeAuthorizationToken(String url, Object payload, String token) {
        HttpHeaders headers = setHeaderAuthorization(token);
        return postForEntity(url, payload, headers, Map.class);
    }

    public static Map<String, Object> callPostExchangeAuthorizationToken(String url, Object payload, String token, Class clazz) {
        HttpHeaders headers = setHeaderAuthorization(token);
        return postForEntity(url, payload, headers, clazz);
    }

    public static Map<String, Object> callPostExchange(String url, String appId, String token) {
        HttpHeaders headers = setHeaderByToken(appId, token);
        return exchange(url, headers, HttpMethod.POST, Map.class);
    }

    public static Map<String, Object> callPostExchange(String url, String appId, String token, Class clazz) {
        HttpHeaders headers = setHeaderByToken(appId, token);
        return exchange(url, headers, HttpMethod.POST, clazz);
    }

    public static Map<String, Object> callPostExchangeAuthorization(String url, String token, Class clazz) {
        HttpHeaders headers = setHeaderAuthorization(token);
        return exchange(url, headers, HttpMethod.POST, clazz);
    }

    public static Map<String, Object> getForEntity(String url, HttpHeaders headers, Class clazz) {
        Map<String, Object> response = new HashMap<>();
        try {
            ResponseEntity<?> responseEntity = new RestTemplate().exchange(url, HttpMethod.GET, new HttpEntity<>(headers), clazz);

            response.put(REST_STATUS, REST_STATUS_OK);
            response.put(REST_CONTENT, responseEntity.getBody());
        } catch (Exception e) {
            response.put(REST_STATUS, REST_STATUS_FAIL);
            response.put(REST_MESSAGE, e.getMessage());
        }

        return response;
    }

    public static Map<String, Object> postForEntity(String url, Object payload, HttpHeaders headers, Class clazz) {
        Map<String, Object> response = new HashMap<>();
        try {
            HttpEntity<?> entity = new HttpEntity<>(payload, headers);
            ResponseEntity<?> responseEntity = new RestTemplate().postForEntity(url, entity, clazz);

            response.put(REST_STATUS, REST_STATUS_OK);
            response.put(REST_CONTENT, responseEntity.getBody());
        } catch (Exception e) {
            response.put(REST_STATUS, REST_STATUS_FAIL);
            response.put(REST_MESSAGE, e.getMessage());
        }

        return response;
    }

    public static Map<String, Object> exchange(String url, HttpHeaders headers, HttpMethod method, Class clazz) {
        Map<String, Object> response = new HashMap<>();
        try {
            HttpEntity<?> entity = new HttpEntity<>(headers);
            ResponseEntity<?> responseEntity = new RestTemplate().exchange(url, method, entity, clazz);

            response.put(REST_STATUS, REST_STATUS_OK);
            response.put(REST_CONTENT, responseEntity.getBody());
        } catch (Exception e) {
            response.put(REST_STATUS, REST_STATUS_FAIL);
            response.put(REST_MESSAGE, e.getMessage());
        }

        return response;
    }

    public static HttpHeaders setHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Content-Type", "application/json");
        return headers;
    }

    public static HttpHeaders setHeaderByToken(String appId, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-AppId", appId);
        headers.set("X-Token", token);
        headers.set("Accept", "application/json");
        headers.set("Content-Type", "application/json");
        return headers;
    }

    public static HttpHeaders setHeaderBasic(String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + password);
        return headers;
    }

    public static HttpHeaders setHeaderAuthorization(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + token);
        return headers;
    }
}
