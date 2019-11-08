package com.sample.jjug.demo.integration;

import com.sample.jjug.demo.controller.SampleResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class HogeAPITest {

    @LocalServerPort
    private int port;

    private static RestTemplate createTemplate() {
        return new TestRestTemplate().getRestTemplate();
    }

    @Test
    public void 正しいクエリを指定したとき_hoge3にクエリで指定した値の10倍で返ってくる() {
        int query = 10;
        String url = "http://localhost:" + port + "/hoge/fuga";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("a", query);
        ResponseEntity<SampleResponse> result = createTemplate().exchange(uriBuilder.toUriString(), HttpMethod.GET, null, SampleResponse.class);
        SampleResponse expected = new SampleResponse() {{
            setHoge1("ほげ1");
            setHoge2("ほげ2");
            setHoge3(100);
        }};
        assertThat(result.getBody()).usingRecursiveComparison().isEqualTo(expected);
    }

}
