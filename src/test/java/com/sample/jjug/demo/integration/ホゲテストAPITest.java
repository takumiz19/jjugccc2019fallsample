package com.sample.jjug.demo.integration;

import com.sample.jjug.demo.controller.SampleResponse;
import com.sample.jjug.demo.integration.helper.リクエスト;
import com.sample.jjug.demo.integration.helper.レスポンス;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class ホゲテストAPITest {

    @Resource
    private リクエスト リクエストは;

    @Test
    public void 正しいクエリを指定したとき_hoge3にクエリで指定した値の10倍で返ってくる() {
        int query = 10;
        レスポンス レスポンスの = リクエストは.次のリクエストメソッドで(HttpMethod.GET)
                .次のリクエストクエリで("a", String.valueOf(query))
                .以下URLに送信する("/hoge/fuga");

        SampleResponse expected = new SampleResponse() {{
            setHoge1("ほげ1");
            setHoge2("ほげ2");
            setHoge3(query * 10);
        }};

        レスポンスの.ステータスコードは次の値であることを検証する(200);

        レスポンスの.ボディは次の型で(SampleResponse.class)
                .次の値と一致することを検証する(expected);

    }

}
