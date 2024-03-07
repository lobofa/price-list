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

import com.lobofa.prices.domain.Price;
import com.lobofa.prices.exception.NotFoundException;
import java.time.OffsetDateTime;

/**
 * Interface to define the business logic's entry points related with the {@link Price} entity.
 *
 * @author fabian.lobo
 * @since 1.0
 */
public interface PriceService {

  /**
   * Look for the {@link Price} entity that matches with the given criteria.
   *
   * @param product - The product ID
   * @param brand - The brand ID
   * @param date - The price date
   * @return a {@link Price} entity
   * @throws an {@link NotFoundException} if no records are found
   */
  Price getPrice(Integer product, Integer brand, OffsetDateTime date) throws NotFoundException;
}
