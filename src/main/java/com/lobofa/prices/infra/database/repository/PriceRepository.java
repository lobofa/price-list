package com.lobofa.prices.infra.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lobofa.prices.domain.Price;

/**
 * Interface extending the {@link JpaRepository} interface for accessing the {@link
 * Price} entity.
 *
 * @author fabian.lobo
 * @since 1.0
 * @see {@link JpaRepository}
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {
    
}
