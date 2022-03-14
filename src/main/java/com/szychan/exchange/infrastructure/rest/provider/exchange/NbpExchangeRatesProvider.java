package com.szychan.exchange.infrastructure.rest.provider.exchange;

import static java.util.Objects.nonNull;

import com.szychan.exchange.domain.exception.CurrencyNotSupportedException;
import com.szychan.exchange.infrastructure.config.provider.ExchangeRateRestConnectionInfo;
import com.szychan.exchange.infrastructure.rest.provider.exchange.exception.ExchangeRatesProviderException;
import com.szychan.exchange.infrastructure.rest.provider.exchange.response.CurrencyResponse;
import com.szychan.exchange.service.ExchangeRatesProvider;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.joda.money.CurrencyUnit;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class NbpExchangeRatesProvider implements ExchangeRatesProvider {

  private static final Set<CurrencyUnit> SUPPORTED_CURRENCIES = Set.of(
      CurrencyUnit.EUR
  );

  private final RestTemplate restTemplate;
  private final ExchangeRateRestConnectionInfo connectionInfo;

  /**
   * Gets the average exchange rates for currency unit, currently Nbp api does not provide all
   * exchange rates, therefor it is validated to use only supported ones ( for now EUR ) and throws
   * {@link CurrencyNotSupportedException}
   *
   * @param unit to get rates for
   * @return rates
   */
  @Override
  public BigDecimal getCurrentAverageExchangeRate(final CurrencyUnit unit) {
    validateSupportedCurrencies(unit);

    ResponseEntity<CurrencyResponse> result;
    try {
      result = restTemplate.exchange(
          connectionInfo.buildGetAverageExchangeRates(unit.getCode()),
          HttpMethod.GET,
          buildHttpEntityWithHeaders(),
          CurrencyResponse.class
      );
    } catch (RestClientException exception) {
      throw new ExchangeRatesProviderException(
          String.format("Failed to retrieve data from NBP : %s", exception.getMessage())
      );
    }

    return retrieveAverageExchange(result);
  }

  private BigDecimal retrieveAverageExchange(ResponseEntity<CurrencyResponse> result) {
    try {
      if(nonNull(result.getBody())){
        return result.getBody().getRates().stream().findFirst().orElseThrow().getMid();
      }
    } catch (NoSuchElementException noSuchElementException) {
      throw new ExchangeRatesProviderException("Response does not have expected data", result.getBody().toString());
    }
    throw new ExchangeRatesProviderException("Null response body");
  }

  private void validateSupportedCurrencies(final CurrencyUnit unit) {
    if (!SUPPORTED_CURRENCIES.contains(unit)) {
      throw new CurrencyNotSupportedException(unit.getCode());
    }
  }

  private HttpEntity<Void> buildHttpEntityWithHeaders() {
    var headers = new HttpHeaders();
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    return new HttpEntity<>(headers);
  }
}
