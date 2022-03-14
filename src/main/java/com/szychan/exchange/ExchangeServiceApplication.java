package com.szychan.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class ExchangeServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExchangeServiceApplication.class, args);
  }

}
