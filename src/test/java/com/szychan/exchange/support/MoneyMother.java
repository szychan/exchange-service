package com.szychan.exchange.support;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class MoneyMother {

  public static final CurrencyUnit pln = CurrencyUnit.of("PLN");
  public static final CurrencyUnit eur = CurrencyUnit.EUR;

  public static Money pln(final double amount) {
    return Money.of(pln, amount);
  }

  public static Money eur(final double amount) {
    return Money.of(eur, amount);
  }
}
