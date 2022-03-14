package com.szychan.exchange.service;

import java.math.BigDecimal;
import org.joda.money.CurrencyUnit;

public interface ExchangeRatesProvider {

  BigDecimal getCurrentAverageExchangeRate(CurrencyUnit unit);
}
