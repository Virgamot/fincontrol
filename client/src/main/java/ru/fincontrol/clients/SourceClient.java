package ru.fincontrol.clients;

import reactor.core.publisher.Mono;
import ru.fincontrol.model.CurrencyRate;

import java.time.LocalDate;

public interface SourceClient {
    Mono<CurrencyRate> getCurrencyRate(String currency, LocalDate date);
}
