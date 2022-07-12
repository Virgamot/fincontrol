package ru.fincontrol.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import ru.fincontrol.clients.HttpClient;
import ru.fincontrol.config.CbrfClientConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
class CurrencyRateControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    CbrfClientConfig config;

    @MockBean
    HttpClient httpClient;

    @Test
    void getCurrencyRateTest() {
        var type = "CBRF";
        var currency = "EUR";
        var date = "01-01-2022";

        var url = String.format("%s/%s/%s", config.getUrl(), currency, date);

        when(httpClient.performRequest(url))
                .thenReturn(Mono.just("{\"numCode\":\"978\",\"charCode\":\"EUR\",\"nominal\":\"1\",\"name\":\"Евро\",\"value\":\"65,8166\"}"));

        var result = webTestClient
                .get().uri(String.format("/api/v1/currencyRate/%s/%s/%s", type, currency, date))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(String.class)
                .getResponseBody()
                .blockLast();

        assertThat(result).isEqualTo("{\"charCode\":\"EUR\",\"nominal\":\"1\",\"value\":\"65,8166\"}");
    }
}