package ru.fincontrol.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "moex")
public class MoexConfig {
    String url;
}
