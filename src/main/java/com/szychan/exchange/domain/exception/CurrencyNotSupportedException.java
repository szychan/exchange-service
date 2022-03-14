package com.szychan.exchange.domain.exception;

public class CurrencyNotSupportedException extends RuntimeException {

  public CurrencyNotSupportedException(final String currency) {
    super(String.format("Currency {%s} is not supported", currency));
  }
}
