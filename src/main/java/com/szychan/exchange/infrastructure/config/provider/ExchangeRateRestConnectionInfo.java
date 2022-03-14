package com.szychan.exchange.infrastructure.config.provider;

import java.net.URI;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

@Data
@Configuration
@ConfigurationProperties(prefix = "exchange.provider.rest")
public class ExchangeRateRestConnectionInfo {

  private String host;
  private String averageExchangeRatesEndpoint;

  /**
   * Builds an URI for getting average exchange rate for specified currency
   *
   * @param currency to get average rates for
   * @return {@code URI} for average exchange rate endpoint
   */
  public URI buildGetAverageExchangeRates(final String currency) {
    return UriComponentsBuilder.fromHttpUrl(host)
        .path(averageExchangeRatesEndpoint)
        .buildAndExpand(currency.toLowerCase())
        .toUri();
  }
}
