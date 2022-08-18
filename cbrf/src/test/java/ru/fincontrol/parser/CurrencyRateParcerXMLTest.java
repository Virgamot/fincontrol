package ru.fincontrol.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class CurrencyRateParcerXMLTest {

    @Test
    void parseTest() throws URISyntaxException, IOException {
        var parser = new CurrencyRateParcerXML();
        var uri = ClassLoader.getSystemResource("cbrf_rates.xml").toURI();
        var ratesXml = Files.readString(Path.of(uri), StandardCharsets.UTF_8);

        var rates = parser.parse(ratesXml);
        assertThat(rates).hasSize(34);
    }
}