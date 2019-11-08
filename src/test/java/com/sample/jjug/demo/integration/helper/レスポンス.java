package com.sample.jjug.demo.integration.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link リクエスト}から返却されるクラス.<br>
 */
public class レスポンス {

    private Gson gson = new GsonBuilder().create();

    private final String body;
    private final HttpHeaders header;
    private final HttpStatus statusCode;

    レスポンス(ResponseEntity<String> exchange) {
        body = exchange.getBody();
        statusCode = exchange.getStatusCode();
        header = exchange.getHeaders();
    }

    public <T> レスポンスボディ<T> ボディは次の型で(Class<T> bodyClass) {
        return new レスポンスボディ<T>(gson.fromJson(body, bodyClass));
    }

    public void ステータスコードは次の値であることを検証する(int status) {
        assertThat(statusCode.value()).isEqualTo(status);
    }

    public static class レスポンスボディ<T> {
        private final T body;

        private レスポンスボディ(T body) {
            this.body = body;
        }

        /**
         * bodyの値とそのまま一致させたい場合に利用する
         *
         * @param expected
         */
        public void 次の値と一致することを検証する(T expected) {
            assertThat(body).usingRecursiveComparison().isEqualTo(expected);
        }

    }
}
