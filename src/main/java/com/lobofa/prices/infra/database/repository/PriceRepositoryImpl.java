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

import static org.springframework.util.Assert.notNull;

import com.lobofa.prices.domain.Price;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.time.OffsetDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

@Slf4j
public class PriceRepositoryImpl extends SimpleJpaRepository<Price, Integer>
    implements PriceRepository {

  private final EntityManager entityManager;

  public PriceRepositoryImpl(EntityManager entityManager) {
    super(Price.class, entityManager);
    this.entityManager = entityManager;
  }

  @PostConstruct
  public void init() {
    notNull(entityManager, "The property [entityManager] has not been set.");

    log.info("PriceRepositoryImpl has been successfully initialized.");
  }

  @Override
  public Optional<Price> lookForPrice(Integer product, Integer brand, OffsetDateTime date) {
    final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
    final CriteriaQuery<Price> query = builder.createQuery(Price.class);
    final Root<Price> root = query.from(Price.class);
    final CriteriaQuery<Price> conditions =
        query
            .where(
                builder.and(
                    builder.equal(root.get("product"), product),
                    builder.equal(root.get("brand"), brand),
                    builder.greaterThanOrEqualTo(root.get("starDateTime"), date),
                    builder.lessThanOrEqualTo(root.get("endDateTime"), date)))
            .orderBy(builder.desc(root.get("priority")));

    final Price result =
        this.entityManager.createQuery(conditions).setMaxResults(1).getSingleResult();
    return Optional.ofNullable(result);
  }
}
