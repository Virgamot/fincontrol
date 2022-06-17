package ru.fincontrol;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class FincontrolClient {
    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(FincontrolClient.class).run(args);
    }
}
