package ru.fincontrol.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.fincontrol.config.CbrfClientConfig;
import ru.fincontrol.model.CurrencyRate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service("cbrf")
@RequiredArgsConstructor
@Slf4j
public class CbrfClient implements  SourceClient{

    public static final String DATE_FORMAT = "dd-MM-yyyy";
    private static  final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private final CbrfClientConfig config;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Override
    public Mono<CurrencyRate> getCurrencyRate(String currency, LocalDate date) {
        log.info("getCurrencyRate:{}, date:{}",currency,date);
        //TODO
        return null;
    }
}
