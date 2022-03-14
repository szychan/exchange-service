package com.szychan.exchange.infrastructure.database;

import com.szychan.exchange.domain.Account;
import com.szychan.exchange.infrastructure.database.jpa.AccountJpaRepository;
import com.szychan.exchange.service.AccountRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AccountPostgresRepository implements AccountRepository {

  private final AccountJpaRepository accountJpaRepository;

  @Override
  public Account getAccountById(UUID accountId) {
    return accountJpaRepository.getOne(accountId).toAccount();
  }
}
