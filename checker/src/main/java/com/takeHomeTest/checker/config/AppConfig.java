package com.takeHomeTest.checker.config;

import org.apache.commons.text.similarity.JaccardSimilarity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {

    @Bean
    public JaccardSimilarity jaccardSimilarity() {
        return new JaccardSimilarity();
    }

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
