package ru.fincontrol.clients;

import reactor.core.publisher.Mono;

public interface HttpClient {
    Mono<String> performRequest(String url);
}
