/*
 * Copyright (C) © 2024, All rights reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 * Proprietary and confidential.
 *
 * Written by Fabián Lobo (lobofa@gmail.com), 2024
 */
package com.lobofa.prices.application.model;

import java.time.LocalDateTime;

public record PriceModel(
    Integer id,
    Integer product,
    Integer brand,
    Double price,
    String currency,
    LocalDateTime startDate,
    LocalDateTime endDate) {}
