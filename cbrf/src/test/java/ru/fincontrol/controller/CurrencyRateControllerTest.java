package ru.fincontrol.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.fincontrol.config.CbrConfig;
import ru.fincontrol.requester.CbrRequester;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.fincontrol.services.CurrencyRateService.DATE_TIME_FORMATTER;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
class CurrencyRateControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    CbrConfig cbrConfig;

    @MockBean
    CbrRequester cbrRequester;

    @Test
    @DirtiesContext
    void getCurrencyRateTest() throws Exception {
        var currency = "EUR";
        var date = "01-01-2022";

        prepareCbrRequestMock(date);

        var result = webTestClient
                .get().uri(String.format("/api/v1/currencyRate/%s/%s",currency,date))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(String.class)
                .getResponseBody()
                .blockLast();

        assertThat(result).isEqualTo("{\"numCode\":\"978\",\"charCode\":\"EUR\",\"nominal\":\"1\",\"name\":\"Евро\",\"value\":\"65,8166\"}");
    }

    @Test
    @DirtiesContext
    void cacheRatesTest() throws Exception {
        prepareCbrRequestMock(null);

        var currency = "EUR";
        var date = "01-01-2022";

        webTestClient.get().uri(String.format("/api/v1/currencyRate/%s/%s",currency,date)).exchange().expectStatus().isOk();
        webTestClient.get().uri(String.format("/api/v1/currencyRate/%s/%s",currency,date)).exchange().expectStatus().isOk();

        currency = "USD";
        webTestClient.get().uri(String.format("/api/v1/currencyRate/%s/%s",currency,date)).exchange().expectStatus().isOk();

        date = "03-03-2021";
        webTestClient.get().uri(String.format("/api/v1/currencyRate/%s/%s", currency, date)).exchange().expectStatus().isOk();

        verify(cbrRequester, times(2)).getRatesAsXml(any());
    }

    private void prepareCbrRequestMock(String date) throws URISyntaxException, IOException {
        var uri = ClassLoader.getSystemResource("cbrf_rates.xml").toURI();
        var ratesXML = Files.readString(Paths.get(uri));

        if (date == null){
            when(cbrRequester.getRatesAsXml(any())).thenReturn(ratesXML);
        }else {
            var dateParam = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            var cbrUrl = String.format("%s?date_req=%s", cbrConfig.getUrl(), DATE_TIME_FORMATTER.format(dateParam));
            when(cbrRequester.getRatesAsXml(cbrUrl)).thenReturn(ratesXML);
        }

    }
}