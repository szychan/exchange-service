package com.szychan.exchange.support;

import com.szychan.exchange.infrastructure.database.entity.AccountEntity;
import java.math.BigDecimal;
import java.util.UUID;

public class AccountEntityMother {

  public static UUID accountEntityId = UUID.randomUUID();

  public static AccountEntity getEntity(final double amount) {

    return AccountEntity.builder()
        .id(accountEntityId)
        .balance(BigDecimal.valueOf(amount))
        .currency("PLN")
        .build();
  }

}
