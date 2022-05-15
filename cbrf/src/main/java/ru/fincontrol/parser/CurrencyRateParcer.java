package ru.fincontrol.parser;

import ru.fincontrol.model.CurrencyRate;

import java.util.List;

public interface CurrencyRateParcer {
    List<CurrencyRate> parse(String ratesAsString);
}
