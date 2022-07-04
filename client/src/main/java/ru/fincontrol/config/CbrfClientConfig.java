package ru.fincontrol.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "fincontrol-client")
public class CbrfClientConfig {
    String url;
}
