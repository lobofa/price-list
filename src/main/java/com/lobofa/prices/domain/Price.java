package com.lobofa.prices.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PRICES")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "PRODUCT_ID", nullable = false)
    private Integer product;

    @Column(name = "BRAND_ID", nullable = false)
    private Integer brand;

    @Column(name = "PRICE", nullable = false, scale = 2, precision = 10)
    private BigDecimal price;

    @Column(name = "CURR", nullable = false)
    private String currency;

    @Column(name = "PRIORITY", nullable = true, columnDefinition = "DEFAULT 0")
    private Integer priority;

    @Column(name = "START_DATE", nullable = false)
    private OffsetDateTime starDateTime;

    @Column(name = "END_DATE", nullable = false)
    private OffsetDateTime endDateTime;
    
}
