package com.szychan.exchange.infrastructure.rest.provider.exchange.exception;

public class ExchangeRatesProviderException extends RuntimeException {

  public ExchangeRatesProviderException(final String messageFormat, String... args) {
    super(String.format(messageFormat, (Object[]) args));
  }
}
