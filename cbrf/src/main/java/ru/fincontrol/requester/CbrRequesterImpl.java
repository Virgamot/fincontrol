package ru.fincontrol.requester;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class CbrRequesterImpl implements CbrRequester {

    @Override
    public String getRatesAsXml(String url) {

        log.info("request for url:{}", url);

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(new HttpGet(url))
        ) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return IOUtils.toString(entity.getContent(), "windows-1251");
            }
        } catch (IOException e) {
            log.error("Error when request CBRF rates, url:{}", url, e);
        }

        return "";
    }
}
