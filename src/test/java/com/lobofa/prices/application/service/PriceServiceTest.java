/*
 * Copyright (C) © 2024, All rights reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 * Proprietary and confidential.
 *
 * Written by Fabián Lobo (lobofa@gmail.com), 2024
 */
package com.lobofa.prices.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lobofa.prices.domain.Price;
import com.lobofa.prices.exception.NotFoundException;
import com.lobofa.prices.infra.database.repository.PriceRepository;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class for testing the exposed methods in the {@link PriceService} interface
 *
 * @author fabian.lobo
 * @since 1.0
 * @see PriceService
 */
public class PriceServiceTest {

  private PriceRepository priceRepository = mock();

  @Test
  public void testGetPrice_whenPriceIsFound_shouldReturnResult() {
    try {

      final Integer product = 231;
      final Integer brand = 23;
      final OffsetDateTime date = OffsetDateTime.parse("2024-01-02T23:23:34-03:00");

      final Price price = new Price();
      price.setId(1);
      price.setBrand(brand);
      price.setProduct(product);
      price.setCurrency("USD");
      price.setPriority(1);
      price.setStarDateTime(OffsetDateTime.parse("2024-01-02T00:00:00-03:00"));
      price.setEndDateTime(OffsetDateTime.parse("2024-01-28T23:59:59-03:00"));
      price.setPrice(new BigDecimal("35.42"));

      when(priceRepository.lookForPrice(anyInt(), anyInt(), any(OffsetDateTime.class)))
          .thenReturn(Optional.of(price));

      final PriceService service = new PriceServiceImpl(priceRepository);

      final Price result = service.getPrice(product, brand, date);

      assertNotNull(result);
      assertNotNull(result.getId());
      assertNotNull(result.getProduct());
      assertNotNull(result.getBrand());
      assertNotNull(result.getPriority());
      assertNotNull(result.getPrice());
      assertNotNull(result.getCurrency());
      assertNotNull(result.getStarDateTime());
      assertNotNull(result.getEndDateTime());

      assertEquals(result.getId(), price.getId());
      assertEquals(result.getProduct(), price.getProduct());
      assertEquals(result.getBrand(), price.getBrand());
      assertEquals(result.getPriority(), price.getPriority());
      assertEquals(result.getPrice(), price.getPrice());
      assertEquals(result.getCurrency(), price.getCurrency());
      assertEquals(result.getStarDateTime(), price.getStarDateTime());
      assertEquals(result.getEndDateTime(), price.getEndDateTime());

      verify(priceRepository, times(1)).lookForPrice(anyInt(), anyInt(), any(OffsetDateTime.class));

    } catch (Exception e) {
      fail(e);
    }
  }

  @Test
  public void testGetPrice_whenPriceIsNotFound_shouldThrowException() {

    when(priceRepository.lookForPrice(anyInt(), anyInt(), any(OffsetDateTime.class)))
        .thenReturn(Optional.empty());

    final Integer product = 231;
    final Integer brand = 23;
    final OffsetDateTime date = OffsetDateTime.parse("2024-01-02T23:23:34-03:00");

    final PriceService service = new PriceServiceImpl(priceRepository);

    final String message =
        String.format(
            "No price was found for the given values[Product %s, Brand %s, Date %s].",
            product, brand, date);

    NotFoundException exception =
        Assertions.assertThrows(
            NotFoundException.class, () -> service.getPrice(product, brand, date));

    assertNotNull(exception);
    assertNotNull(exception.getMessage());

    assertEquals(exception.getMessage(), message);
  }
}
