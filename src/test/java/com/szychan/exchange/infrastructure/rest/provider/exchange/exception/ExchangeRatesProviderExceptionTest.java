package com.szychan.exchange.infrastructure.rest.provider.exchange.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.MissingFormatArgumentException;
import org.junit.jupiter.api.Test;

class ExchangeRatesProviderExceptionTest {

  @Test
  void givenMessageFormatAndParams_buildProperExceptionInstance() {
    // given
    final String messageFormat = "Test message %s, %s";
    final String arg1 = "argvalue1";
    final String arg2 = "argvalue2";

    // when
    final ExchangeRatesProviderException exception = new ExchangeRatesProviderException(
        messageFormat,
        arg1,
        arg2);

    // then
    assertThat(exception)
        .hasMessage("Test message argvalue1, argvalue2");
  }

  @Test
  void givenMessageFormatAndNotSufficientAmountOfParams_throw() {
    // given
    final String messageFormat = "Test message %s, %s";
    final String arg1 = "argvalue1";

    // when/then
     assertThrows(
         MissingFormatArgumentException.class,
         () ->new ExchangeRatesProviderException(messageFormat, arg1));
  }
}