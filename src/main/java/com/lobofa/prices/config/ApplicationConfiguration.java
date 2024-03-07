package com.lobofa.prices.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.lobofa.prices.infra.database.repository.PriceRepository;
import com.lobofa.prices.infra.database.repository.PriceRepositoryImpl;

/**
 * Class in charge of configure the Spring beans used in the platform application.
 *
 * @author fabian.lobo
 * @since 1.0.0
 */
@ComponentScan(basePackages = "com.lobofa.prices")
@Configuration
@EnableJpaRepositories(basePackageClasses = PriceRepository.class, repositoryBaseClass = PriceRepositoryImpl.class)
@EnableTransactionManagement
@EntityScan(basePackages = { "com.lobofa.prices.domain"})
public class ApplicationConfiguration {
    
}
