package com.szychan.exchange.infrastructure.rest.handler;

import com.szychan.exchange.infrastructure.rest.provider.exchange.exception.ExchangeRatesProviderException;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

class GenericErrorHandlerTest {

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(new DummyController())
        .setControllerAdvice(new GenericErrorHandler())
        .build();
  }

  @Test
  void shouldReturn404_whenEntityNotFound() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get("/throwAlgorithmNotFoundException")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  void shouldReturn500_whenExchangeRatesProviderException() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get("/throwExchangeRatesProviderException")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().isInternalServerError());
  }

  /**
   * Dummy class used to throw the exceptions
   */
  @RestController
  private static class DummyController {

    @GetMapping("/throwEntityNotFoundException")
    void throwEntityNotFoundException() {
      throw new EntityNotFoundException("No Such Entity");
    }

    @GetMapping("/throwExchangeRatesProviderException")
    void throwExchangeRatesProviderException() {
      throw new ExchangeRatesProviderException("No Such Entity");
    }
  }
}
