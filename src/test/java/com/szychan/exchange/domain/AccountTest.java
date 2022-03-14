package com.szychan.exchange.domain;

import static com.szychan.exchange.support.AccountMother.eurAccount;
import static com.szychan.exchange.support.AccountMother.plnAccount;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

class AccountTest {

  @Test
  void givenPlnAccount_whenGetMoney_thenReturnMoney() {
    // given
    final Account account = plnAccount(500);

    // when
    final Money result = account.getMoney();

    // then
    assertThat(result).isEqualTo(Money.of(CurrencyUnit.of("PLN"), 500));
  }

  @Test
  void givenEurAccount_whenGetMoney_thenReturnMoney() {
    // given
    final Account account = eurAccount(0.88);

    // when
    final Money result = account.getMoney();

    // then
    assertThat(result).isEqualTo(Money.of(CurrencyUnit.EUR, 0.88));
  }

}