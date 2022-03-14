package com.szychan.exchange.infrastructure.database.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.szychan.exchange.domain.Account;
import com.szychan.exchange.support.AccountEntityMother;
import com.szychan.exchange.support.AccountMother;
import org.junit.jupiter.api.Test;

class AccountEntityTest {

  @Test
  void givenAccountEntity_whenToAccount_thenConvert() {
    // given
    final AccountEntity entity = AccountEntityMother.getEntity(100);
    final Account expectedResult = Account.builder()
        .id(entity.getId())
        .balance(entity.getBalance())
        .currency(entity.getCurrency())
        .build();

    // when
    final Account result = entity.toAccount();

    // then
    assertThat(result).isEqualToComparingFieldByField(expectedResult);
  }

}