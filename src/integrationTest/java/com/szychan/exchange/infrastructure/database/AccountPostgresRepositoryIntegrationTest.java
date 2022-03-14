package com.szychan.exchange.infrastructure.database;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.szychan.exchange.domain.Account;
import com.szychan.exchange.service.AccountRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import java.math.BigDecimal;
import java.util.UUID;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest()
@Transactional
@AutoConfigureEmbeddedDatabase
@ActiveProfiles("integration-test")
public class AccountPostgresRepositoryIntegrationTest {

  @Autowired
  private AccountRepository repository;

  @Test
  void givenNonExistentAccount_whenGetAccountById_thenThrowException() {
    // given
    final UUID nonExistentAccountId = UUID.randomUUID();

    // when/then
    assertThrows(
        JpaObjectRetrievalFailureException.class,
        () -> repository.getAccountById(nonExistentAccountId));
  }

  @Test
  void givenAccount_whenGetAccountById_thenThrowException() {
    // given
    final UUID existentAccountId = UUID.fromString("6724da0a-3109-41ae-968c-da434615f709");
    final Account expectedResult = Account.builder()
        .id(existentAccountId)
        .balance(BigDecimal.valueOf(500))
        .currency("PLN")
        .build();

    // when
    final Account result = repository.getAccountById(existentAccountId);

    // then
    assertThat(result).isEqualToIgnoringGivenFields(expectedResult, "balance");
    assertThat(result.getMoney().getAmount()).isEqualByComparingTo(
        expectedResult.getMoney().getAmount());
  }
}
