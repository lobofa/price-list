/*
 * Copyright (C) © 2024, All rights reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 * Proprietary and confidential.
 *
 * Written by Fabián Lobo (lobofa@gmail.com), 2024
 */
package com.lobofa.prices.config;

import com.lobofa.prices.application.service.PriceService;
import com.lobofa.prices.application.service.PriceServiceImpl;
import com.lobofa.prices.infra.database.repository.PriceRepository;
import com.lobofa.prices.infra.database.repository.PriceRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Class in charge of configure the Spring beans used in the platform application.
 *
 * @author fabian.lobo
 * @since 1.0.0
 */
@ComponentScan(basePackages = "com.lobofa.prices")
@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = {"com.lobofa.prices.domain"})
public class ApplicationConfiguration {

  @Autowired private EntityManager entityManager;

  @Bean
  public PriceService priceService() {
    return new PriceServiceImpl(priceRepository());
  }

  @Bean
  public PriceRepository priceRepository() {
    return new PriceRepositoryImpl(entityManager);
  }
}
