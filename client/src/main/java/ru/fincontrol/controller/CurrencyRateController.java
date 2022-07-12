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
import ru.fincontrol.model.SourceType;
import ru.fincontrol.services.CurrencyRateService;

import java.time.LocalDate;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "${app.rest.api.prefix}/v1")
public class CurrencyRateController {

    public final CurrencyRateService currencyRateService;

    @GetMapping("/currencyRate/{type}/{currency}/{date}")
    public Mono<CurrencyRate> getCurrencyRate(@PathVariable("type") SourceType type,
                                              @PathVariable("currency") String currency,
                                              @DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable("date") LocalDate date) {
        log.info("getCurrencyRate. sourceType:{}, currency:{}, date:{}", type, currency, date);
        return currencyRateService.getCurrencyRate(type, currency, date);
    }

}
