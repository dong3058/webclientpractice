package com.example.webclientpractice;


import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.ProxySelector;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class WebConfig {

    @Bean
    public WebClient webClient(){

        HttpClient httpClient=HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,0)
                .responseTimeout(Duration.ofMillis(500))
                .doOnConnected(connection -> {
                    connection.addHandlerFirst(new ReadTimeoutHandler(500, TimeUnit.MILLISECONDS))
                            .addHandlerLast(new WriteTimeoutHandler(500,TimeUnit.MILLISECONDS));
                });

        WebClient webClient=WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("https://open-api.bser.io")
                .filter(ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
                            log.info("requestheader data");
                            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
                            clientRequest.headers().forEach(
                                    (name, values) -> values.forEach(value -> log.info("{} : {}", name, value))
                            );
                        return Mono.just(clientRequest);
                        }

                ))
                .filter(ExchangeFilterFunction.ofResponseProcessor(resp->{
                    log.info("Resp data");
                    resp.headers().asHttpHeaders().forEach(
                            (name, values) -> values.forEach(value -> log.info("{} : {}", name, value))
                    );
                    return Mono.just(resp);
                }))
                .defaultHeader("x-api-key","NVe4oEcXC86ROegAdN8taIWyTvWbQak5hJtVo1Ad")
                .defaultHeader("Accept","application/json")
                .build();



        return webClient;
    }



}
