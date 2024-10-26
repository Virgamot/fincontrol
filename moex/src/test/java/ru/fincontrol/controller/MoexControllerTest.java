package ru.fincontrol.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.fincontrol.model.SecurityRate;
import ru.fincontrol.services.SecuritiesService;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
class MoexControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private SecuritiesService securitiesService;

    @Test
    void getCurrencyRate() {
    }

    @Test
    void getExchangeRate() {
    }

    @Test
    void getSecurityRate() {
        var ticket = "SBER";

        when(securitiesService.getSecurity(ticket)).thenReturn(SecurityRate.builder().ticket(ticket).build());

        var result = webTestClient
                .get().uri(String.format("/api/v1//securities/%s", ticket))
                        .accept(MediaType.APPLICATION_JSON)
                        .exchange()
                        .expectStatus().isOk()
                        .returnResult(String.class)
                        .getResponseBody()
                        .blockLast();

        //assertThat(result).isEqualTo("");
    }
}