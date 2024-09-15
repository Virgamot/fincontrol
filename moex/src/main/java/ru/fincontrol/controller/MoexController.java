package ru.fincontrol.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.fincontrol.model.CurrencyRate;
import ru.fincontrol.model.ExchangeRate;
import ru.fincontrol.model.SecurityRate;
import ru.fincontrol.services.SecuritiesService;

import java.time.LocalDate;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "${app.rest.api.prefix}/v1")
public class MoexController {

    private final SecuritiesService securitiesService;

    @GetMapping("/currencyRate/{currency}/{date}")
    public Mono<CurrencyRate> getCurrencyRate(@PathVariable("currency") String currency,
                                              @DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable("date") LocalDate date) {
        log.info("getCurrencyRate, currency:{}, date:{}", currency, date);
        //TODO: not implemented
        return Mono.empty();
    }

    @GetMapping("/exchangeRate/{rate}")
    public Mono<ExchangeRate> getExchangeRate(@PathVariable("rate") String rate) {
        log.info("getExchangeRate, rate:{}", rate);
        var exchangeRateInfo = securitiesService.getRate(rate);
        return Mono.just(exchangeRateInfo);
    }

    @GetMapping("/securities/{ticket}")
    public Mono<SecurityRate> getSecurityRate(@PathVariable("ticket") String ticket) {
        log.info("getSecurityRate for ticket: {}", ticket);
        var security = securitiesService.getSecurity(ticket);
        return Mono.just(security);
    }
}
