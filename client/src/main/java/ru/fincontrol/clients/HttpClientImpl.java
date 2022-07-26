package ru.fincontrol.clients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.fincontrol.exceptions.HttpClientException;

@Service
@Slf4j
public class HttpClientImpl implements HttpClient {
    private final WebClient.Builder webBuilder;

    public HttpClientImpl(WebClient.Builder webBuilder) {
        this.webBuilder = webBuilder;
    }

    @Override
    public Mono<String> performRequest(String url) {
        log.info("http request, url:{}", url);
        var client = webBuilder.baseUrl(url).build();

        try {
            return client.get()
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class)
                    .doOnError(error -> log.error("Http request error, url:{}", url, error))
                    .doOnNext(val -> log.info("val:{}", val));

        } catch (Exception e) {
            throw new HttpClientException(e.getMessage());
        }
    }
}
