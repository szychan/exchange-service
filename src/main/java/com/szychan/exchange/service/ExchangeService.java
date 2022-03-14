package com.szychan.exchange.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ExchangeService {

  private final ExchangeRatesProvider exchangeRatesProvider;
  private final AccountRepository accountRepository;

  public Money getEuroForAccount(final UUID accountId) // consider making it parameterized for currency
  {
    final Money accountMoney = accountRepository.getAccountById(accountId).getMoney();
    final BigDecimal rates = exchangeRatesProvider.getCurrentAverageExchangeRate(CurrencyUnit.EUR);

    return convertPlnToEuro(accountMoney, rates);
  }

  private Money convertPlnToEuro(final Money toConvert, final BigDecimal rates) {
    return toConvert.convertedTo(
        CurrencyUnit.EUR,
        BigDecimal.ONE.setScale(100, RoundingMode.HALF_UP)
            .divide(rates, RoundingMode.HALF_UP),
        RoundingMode.HALF_UP
    );
  }
}
