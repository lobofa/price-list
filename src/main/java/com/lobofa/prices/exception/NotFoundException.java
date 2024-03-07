/*
 * Copyright (C) © 2024, All rights reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 * Proprietary and confidential.
 *
 * Written by Fabián Lobo (lobofa@gmail.com), 2024
 */
package com.lobofa.prices.exception;

import java.time.OffsetDateTime;

/**
 * Class extending the {@link Exception} class to report any error occurred within the platform.
 *
 * @author fabian.lobo
 * @since 1.0.0
 */
public class NotFoundException extends Exception {

  public NotFoundException(Integer product, Integer brand, OffsetDateTime date) {
    super(
        String.format(
            "No price was found for the given values[Product %s, Brand %s, Date %s].",
            product, brand, date));
  }
}
