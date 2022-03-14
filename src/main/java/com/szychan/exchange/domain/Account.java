package com.szychan.exchange.domain;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

@Builder
public class Account {

  UUID id;
  BigDecimal balance;
  String currency;

  /**
   * Returns representation of the account balance in {@link Money}
   *
   * @return {@link Money} instance
   */
  public Money getMoney() {
    return Money.of(CurrencyUnit.of(currency), balance);
  }
}
