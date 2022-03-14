package com.szychan.exchange.infrastructure.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

  /**
   * Creates a {@link RestTemplate} .
   *
   * @return RestTemplate instance.
   */
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplateBuilder()
        .defaultMessageConverters()
        .build();
  }
}
