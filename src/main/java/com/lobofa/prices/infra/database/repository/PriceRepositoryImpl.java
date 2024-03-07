package com.lobofa.prices.infra.database.repository;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.lobofa.prices.domain.Price;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;


public class PriceRepositoryImpl extends SimpleJpaRepository<Price, Integer> implements PriceRepository {

    private final EntityManager entityManager;

    public PriceRepositoryImpl(JpaEntityInformation<Price, Integer> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Price> lookForPrice(Integer product, Integer brand, OffsetDateTime date) {
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Price> query = builder.createQuery(Price.class);
        final Root<Price> root = query.from(Price.class);
        final CriteriaQuery<Price> conditions = query.where(
            builder.and(
                builder.equal(root.get("product"), product), 
                builder.equal(root.get("brand"), brand), 
                builder.greaterThanOrEqualTo(root.get("starDateTime"), date), 
                builder.lessThanOrEqualTo(root.get("endDateTime"), date)
            )
        ).orderBy(builder.desc(root.get("priority")));
        
        final Price result = this.entityManager.createQuery(conditions).setMaxResults(1).getSingleResult();
        return Optional.ofNullable(result);
    }
    
}
