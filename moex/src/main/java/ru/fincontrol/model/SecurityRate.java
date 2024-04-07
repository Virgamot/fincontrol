package ru.fincontrol.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SecurityRate {
    String ticket;
    String value;
}
