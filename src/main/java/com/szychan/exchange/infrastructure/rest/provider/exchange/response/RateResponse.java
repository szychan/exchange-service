package com.szychan.exchange.infrastructure.rest.provider.exchange.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RateResponse {

  String no;
  String effectiveDate;
  BigDecimal mid;

  @JsonCreator
  public static RateResponse createFromJson(
      @JsonProperty("no") String no,
      @JsonProperty("effectiveDate") String effectiveDate,
      @JsonProperty("mid") BigDecimal mid) {
    return RateResponse.builder()
        .no(no)
        .effectiveDate(effectiveDate)
        .mid(mid)
        .build();
  }
}
