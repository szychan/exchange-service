package com.szychan.exchange.infrastructure.rest.provider.exchange.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyResponse {

  String table;
  String currency;
  String code;
  Set<RateResponse> rates;

  @JsonCreator
  public static CurrencyResponse createFromJson(
      @JsonProperty("table") String table,
      @JsonProperty("currency") String currency,
      @JsonProperty("code") String code,
      @JsonProperty("rates") Set<RateResponse> rates) {
    return CurrencyResponse.builder()
        .table(table)
        .currency(currency)
        .code(code)
        .rates(rates)
        .build();
  }
}