/*
 * Copyright (C) © 2024, All rights reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 * Proprietary and confidential.
 *
 * Written by Fabián Lobo (lobofa@gmail.com), 2024
 */
package com.lobofa.prices.infra.database.repository;

import com.lobofa.prices.domain.Price;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Interface extending the {@link JpaRepository} interface for accessing the {@link Price} entity.
 *
 * @author fabian.lobo
 * @since 1.0
 * @see {@link JpaRepository}
 */
@NoRepositoryBean
public interface PriceRepository extends JpaRepository<Price, Integer> {

  /**
   * Look for the {@link Price} entity that matches with the given criteria.
   *
   * @param product - The product ID
   * @param brand - The brand ID
   * @param date - The price date
   * @return a {@link Price} entity if exists, an empty Optional otherwise
   */
  Optional<Price> lookForPrice(Integer product, Integer brand, LocalDateTime date);
}
