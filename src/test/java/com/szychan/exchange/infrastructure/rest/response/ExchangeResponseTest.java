package com.szychan.exchange.infrastructure.rest.response;

import static com.szychan.exchange.support.MoneyMother.pln;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.joda.money.Money;
import org.junit.jupiter.api.Test;

class ExchangeResponseTest {

  @Test
  void givenMoney_whenFrom_thenBuildExpectedResponse() {
    // given
    final Money money = pln(1150.99);

    // when
    final ExchangeResponse result = ExchangeResponse.from(money);

    // then
    assertThat(result)
        .hasFieldOrPropertyWithValue("convertedValue", "1150.99 PLN");
  }
}