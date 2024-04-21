package ru.fincontrol.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients
@EnableConfigurationProperties(MoexConfig.class)
public class ApplicationConfig {
}
