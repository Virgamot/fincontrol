package ru.fincontrol.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CbrfClientConfig.class)
public class ApplicationConfig {
}
