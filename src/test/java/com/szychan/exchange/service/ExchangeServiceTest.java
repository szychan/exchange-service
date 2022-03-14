package com.szychan.exchange.service;

import static com.szychan.exchange.support.AccountMother.plnAccount;
import static com.szychan.exchange.support.MoneyMother.eur;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.szychan.exchange.domain.Account;
import com.szychan.exchange.support.AccountMother;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Stream;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExchangeServiceTest {

  @Mock
  private ExchangeRatesProvider exchangeRatesProvider;

  @Mock
  private AccountRepository accountRepository;

  @InjectMocks
  private ExchangeService exchangeService;

  @ParameterizedTest
  @MethodSource("getEuroForAccountFeedMethod")
  public void givenAccount_whenGetEuroForAccount_thenConvertToEuro(
      final Account account,
      final BigDecimal averageRates,
      final Money expectedResult
  ) {
    // given
    final UUID accountId = AccountMother.accountID;
    when(accountRepository.getAccountById(accountId))
        .thenReturn(account);
    when(exchangeRatesProvider.getCurrentAverageExchangeRate(CurrencyUnit.EUR))
        .thenReturn(averageRates);

    // when
    final Money result = exchangeService.getEuroForAccount(accountId);

    // then
    assertThat(result).isEqualTo(expectedResult);
  }

  private static Stream<Arguments> getEuroForAccountFeedMethod() {
    return Stream.of(
        Arguments.of(plnAccount(500), BigDecimal.valueOf(5), eur(100)),
        Arguments.of(plnAccount(65.65), BigDecimal.valueOf(98), eur(0.67)),
        Arguments.of(plnAccount(0), BigDecimal.valueOf(5), eur(0)),
        Arguments.of(plnAccount(55), BigDecimal.valueOf(9), eur(6.11)),
        Arguments.of(plnAccount(555), BigDecimal.valueOf(7), eur(79.29))
    );
  }
}