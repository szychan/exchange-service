package com.szychan.exchange.infrastructure.rest.handler;

import com.szychan.exchange.infrastructure.rest.provider.exchange.exception.ExchangeRatesProviderException;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GenericErrorHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handleCheckValidationException(EntityNotFoundException ex) {
    log.warn(ex.getMessage());
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(ExchangeRatesProviderException.class)
  public ResponseEntity<String> handleExchangeRatesProviderException(
      ExchangeRatesProviderException ex) {
    log.warn(ex.getMessage());
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ex.getMessage());
  }
}