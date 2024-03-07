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

import static org.springframework.util.Assert.notNull;

import com.lobofa.prices.domain.Price;
import com.lobofa.prices.exception.NotFoundException;
import com.lobofa.prices.infra.database.repository.PriceRepository;
import jakarta.annotation.PostConstruct;
import java.time.OffsetDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class PriceServiceImpl implements PriceService {

  private final PriceRepository priceRepository;

  @PostConstruct
  public void init() {
    notNull(priceRepository, "The property [priceRepository] has not been set.");

    log.info("PriceServiceImpl has been successfully initialized.");
  }

  @Override
  public Price getPrice(Integer product, Integer brand, OffsetDateTime date)
      throws NotFoundException {
    Optional<Price> result = priceRepository.lookForPrice(product, brand, date);
    if (!result.isPresent()) {
      throw new NotFoundException(product, brand, date);
    }
    return result.get();
  }
}
