package ru.fincontrol.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.fincontrol.model.CurrencyRate;
import ru.fincontrol.model.SecurityRate;

import java.time.LocalDate;

@RestController
@Slf4j
@RequestMapping("${app.rest.api.prefix}/v1}")
public class MoexController {

    @GetMapping("/currencyRate/{currency}/{date}")
    public Mono<CurrencyRate> getCurrencyRate(@PathVariable("currency") String currency,
                                              @DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable("date") LocalDate date) {
        log.info("getCurrencyRate, currency:{}, date:{}", currency, date);
        return Mono.empty();
    }

    @GetMapping("/securities/{ticket}")
    public Mono<SecurityRate> getSecurityRate(@PathVariable("ticket") String ticket){
        return Mono.empty();
    }
}
