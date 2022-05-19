package ru.fincontrol.requester;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CbrRequesterImplTest {
    @Test
    void getRatesAsXmlTest() {
        CbrRequester cbrRequester = new CbrRequesterImpl();
        String ratesAsString = cbrRequester.getRatesAsXml("https://cbr.ru/scripts/XML_daily.asp");
        assertThat(ratesAsString).contains("<CharCode>USD</CharCode>").
                                  contains("<CharCode>EUR</CharCode>");
    }
}