package ru.fincontrol.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.fincontrol.clients.SourceClient;
import ru.fincontrol.model.CurrencyRate;
import ru.fincontrol.model.SourceType;

import java.time.LocalDate;
import java.util.Map;

@Service
@Slf4j
public class CurrencyRateService {
    private final Map<String, SourceClient> clients;

    public CurrencyRateService(Map<String, SourceClient> clients) {
        this.clients = clients;
    }

    public Mono<CurrencyRate> getCurrencyRate(SourceType sourceType, String currency, LocalDate date) {
        log.info("getCurrencyRate. sourceType:{}, currency:{}, date:{}", sourceType, currency, date);
        var client = clients.get(sourceType.getServiceName());
        return client.getCurrencyRate(currency, date);
    }
}
