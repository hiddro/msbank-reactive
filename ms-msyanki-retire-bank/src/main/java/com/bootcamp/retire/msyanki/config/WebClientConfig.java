package com.bootcamp.retire.msyanki.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientConfig {

    @Bean
    public WebClient.Builder registrarWebClient() {
        return WebClient.builder();
    }
}
