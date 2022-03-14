package com.szychan.exchange.support;

import com.szychan.exchange.domain.Account;
import java.math.BigDecimal;
import java.util.UUID;

public class AccountMother {
  public static UUID accountID = UUID.randomUUID();

  public static Account plnAccount(final double amount) {
    return Account.builder()
        .id(accountID)
        .balance(BigDecimal.valueOf(amount))
        .currency("PLN")
        .build();
  }

  public static Account eurAccount(final double amount) {
    return Account.builder()
        .id(accountID)
        .balance(BigDecimal.valueOf(amount))
        .currency("EUR")
        .build();
  }

}
