package com.sample.jjug.demo.integration.helper;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class リクエスト {
    private final TestRestTemplate restTemplate;
    private HttpMethod method = HttpMethod.GET;
    private Map<String, String> variables = new HashMap<>();
    private MultiValueMap<String, String> headers = new HttpHeaders();
    private Object body = null;

    リクエスト(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static リクエスト リクエストは(TestRestTemplate restTemplate) {
        return new リクエスト(restTemplate);
    }

    public リクエスト リクエストボディはJsonとして送って() {
        headers.set("Content-Type", "application/json");
        return this;
    }

    public リクエスト 次のリクエストクエリで(String key, String value) {
        variables.put(key, value);
        return this;
    }

    public リクエスト 次のリクエストメソッドで(HttpMethod method) {
        this.method = method;
        return this;
    }

    public レスポンス 以下URLに送信する(String url) {
        HttpEntity<Object> request = new HttpEntity<>(this.body, headers);
        return new レスポンス(restTemplate.exchange(
                buildUrl(url),
                method,
                request,
                String.class
        ));
    }

    private String buildUrl(String url) {
        if (variables.isEmpty()) {
            return url;
        }
        return url + "?" + variables.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining("&"));
    }

}
