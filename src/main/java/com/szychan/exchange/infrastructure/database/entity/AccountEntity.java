package com.szychan.exchange.infrastructure.database.entity;

import com.szychan.exchange.domain.Account;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "account", schema = "accounts")
public class AccountEntity {

  @Id
  UUID id;
  BigDecimal balance;
  String currency;

  public Account toAccount() {
    return Account.builder()
        .id(id)
        .balance(balance)
        .currency(currency)
        .build();
  }
}
