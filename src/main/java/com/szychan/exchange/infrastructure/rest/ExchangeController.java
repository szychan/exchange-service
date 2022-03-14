package com.szychan.exchange.infrastructure.rest;

import com.szychan.exchange.infrastructure.rest.response.ExchangeResponse;
import com.szychan.exchange.service.ExchangeService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.joda.money.Money;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exchange")
public class ExchangeController {

  private final ExchangeService exchangeService;

  @GetMapping("/{userId}")
  public ResponseEntity<ExchangeResponse> toEuro(
      @PathVariable("userId") final UUID userId ) {
    final Money value = exchangeService.getEuroForAccount(userId);
    return ResponseEntity.ok(ExchangeResponse.from(value));
  }
}
