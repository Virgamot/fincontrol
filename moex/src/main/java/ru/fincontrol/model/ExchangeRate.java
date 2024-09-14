package ru.fincontrol.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ExchangeRate {
    String name;
    String value;
}
