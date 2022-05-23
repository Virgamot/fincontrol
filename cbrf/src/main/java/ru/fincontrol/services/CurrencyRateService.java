package ru.fincontrol.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.springframework.stereotype.Service;
import ru.fincontrol.config.CbrConfig;
import ru.fincontrol.model.CachedCurrencyRates;
import ru.fincontrol.model.CurrencyRate;
import ru.fincontrol.parser.CurrencyRateParcer;
import ru.fincontrol.requester.CbrRequester;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyRateService {
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private final CbrRequester cbrRequester;
    private final CurrencyRateParcer currencyRateParcer;
    private final CbrConfig cbrConfig;
    private final Cache<LocalDate, CachedCurrencyRates> currencyRatesCache;

    public CurrencyRate getCurrencyRate(String currency, LocalDate date) {
        log.info("getCurrencyRate. currency:{}, date:{}", currency, date);
        List<CurrencyRate> rates;

        var cachedCurrencyRates = currencyRatesCache.get(date);
        if (cachedCurrencyRates == null) {
            var urlWithParams = String.format("%s?date_req=%s", cbrConfig.getUrl(), DATE_TIME_FORMATTER.format(date));
            var ratesAsXml = cbrRequester.getRatesAsXml(urlWithParams);
            rates = currencyRateParcer.parse(ratesAsXml);
            currencyRatesCache.put(date, new CachedCurrencyRates(rates));
        } else {
            rates = cachedCurrencyRates.getCurrencyRates();
        }

        return rates.stream().filter(rate -> currency.equals(rate.getCharCode()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Currency rate not found. Currency: " + currency));
    }
}
