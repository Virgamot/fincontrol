package ru.fincontrol;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MoexMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(MoexMain.class).run(args);
    }
}
