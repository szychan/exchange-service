package com.szychan.exchange.infrastructure.database;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.szychan.exchange.infrastructure.rest.response.ExchangeResponse;
import com.szychan.exchange.service.ExchangeRatesProvider;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import java.math.BigDecimal;
import org.joda.money.CurrencyUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureEmbeddedDatabase
@SpringBootTest
@ActiveProfiles("integration-test")
public class ExchangeControllerIntegrationTest {

  @Autowired
  private WebApplicationContext webContext;

  @MockBean
  private ExchangeRatesProvider ratesProvider;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
    mockMvc = MockMvcBuilders
        .webAppContextSetup(webContext)
        .build();
  }

  @Test
  void givenRequest_whenAccountExists_thenReturnConvertedValue() throws Exception {
    // given
    when(ratesProvider.getCurrentAverageExchangeRate(CurrencyUnit.EUR))
        .thenReturn(BigDecimal.valueOf(5));
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
        .get("/exchange/" + "6724da0a-3109-41ae-968c-da434615f709")
        .contentType("application/json");

    // when/then
    final MvcResult result = mockMvc.perform(request)
        .andExpect(status().isOk())
        .andReturn();

    final String json = result.getResponse().getContentAsString();
    final ExchangeResponse response = objectMapper.readValue(json, ExchangeResponse.class);
    assertThat(response.getConvertedValue()).isEqualTo("100.00 EUR");
  }

  @Test
  void givenRequest_whenAccountNotExists_thenReturnNotFound() throws Exception {
    // given
    when(ratesProvider.getCurrentAverageExchangeRate(CurrencyUnit.EUR))
        .thenReturn(BigDecimal.valueOf(5));

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
        .get("/exchange/" + "6724da0a-3109-41ae-968c-da434615f705")
        .contentType("application/json");

    // when/then
    mockMvc.perform(request)
        .andExpect(status().isNotFound());
  }
}
