package com.szychan.exchange.infrastructure.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeResponse {

  private static final String RESPONSE_FORMAT = "%s %s";

  String convertedValue;

  /**
   * Converts Money to ExchangeResponse with parsed converted value.
   * Example "150.99 EUR"
   *
   * @param convertedValue to build response from
   * @return formatted string
   */
  public static ExchangeResponse from(final Money convertedValue) {
    return ExchangeResponse.builder()
        .convertedValue(moneyToResponseString(convertedValue))
        .build();
  }

  private static String moneyToResponseString(final Money value) {
    return String.format(
        RESPONSE_FORMAT,
        value.getAmount(),
        value.getCurrencyUnit().getCode().toUpperCase());
  }
}
