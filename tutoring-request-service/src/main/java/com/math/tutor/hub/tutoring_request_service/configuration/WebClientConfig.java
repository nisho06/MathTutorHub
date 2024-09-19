package com.math.tutor.hub.tutoring_request_service.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @Qualifier("yearTopicWebClient")
    public WebClient yearTopicClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl("http://localhost:8001/api/v1/year-topic")
                .build();
    }

    @Bean
    @Qualifier("topicWebClient")
    public WebClient topicClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl("http://localhost:8001/api/v1/topics")
                .build();
    }

    @Bean
    @Qualifier("yearsWebClient")
    public WebClient yearsClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl("http://localhost:8001/api/v1/years")
                .build();
    }
}

