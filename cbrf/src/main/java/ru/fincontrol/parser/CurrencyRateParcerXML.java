package ru.fincontrol.parser;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import ru.fincontrol.model.CurrencyRate;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CurrencyRateParcerXML implements CurrencyRateParcer {

    @Override
    public List<CurrencyRate> parse(String ratesAsString) {

        var rates = new ArrayList<CurrencyRate>();

        var documentBuilderFactory = DocumentBuilderFactory.newInstance();

        documentBuilderFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        documentBuilderFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        try {
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            var documentBuilder = documentBuilderFactory.newDocumentBuilder();

            try (var reader = new StringReader(ratesAsString)) {
                Document doc = documentBuilder.parse(new InputSource(reader));
                doc.getDocumentElement().normalize();

                NodeList list = doc.getElementsByTagName("Valute");

                for (var valuteIdx = 0; valuteIdx < list.getLength(); valuteIdx++) {
                    var node = list.item(valuteIdx);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        var element = (Element) node;

                        var rate = CurrencyRate.builder()
                                .numCode(element.getElementsByTagName("NumCode").item(0).getTextContent())
                                .charCode(element.getElementsByTagName("CharCode").item(0).getTextContent())
                                .nominal(element.getElementsByTagName("Nominal").item(0).getTextContent())
                                .name(element.getElementsByTagName("Name").item(0).getTextContent())
                                .value(element.getElementsByTagName("Value").item(0).getTextContent())
                                .build();

                        rates.add(rate);
                    }
                }
            }

        } catch (Exception e) {
            log.error("Exception when XML parcing: ", e);
        }

        return rates;
    }
}
