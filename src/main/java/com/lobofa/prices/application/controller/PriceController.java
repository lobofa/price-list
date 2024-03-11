/*
 * Copyright (C) © 2024, All rights reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 * Proprietary and confidential.
 *
 * Written by Fabián Lobo (lobofa@gmail.com), 2024
 */
package com.lobofa.prices.application.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.util.Assert.notNull;

import com.lobofa.prices.application.model.PriceModel;
import com.lobofa.prices.application.service.PriceService;
import com.lobofa.prices.application.validation.Brand;
import com.lobofa.prices.application.validation.Date;
import com.lobofa.prices.application.validation.Product;
import com.lobofa.prices.domain.Price;
import jakarta.validation.GroupSequence;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class that implements a Spring controllers that expose endpoints for the {@link Price} to be
 * consumed by REST.
 *
 * @author fabian.lobo
 * @since 1.0.0
 */
@GroupSequence({
  Product.NotNull.class,
  Product.Min.class,
  Brand.NotNull.class,
  Brand.Min.class,
  Date.NotNull.class,
  PriceController.class
})
@RequiredArgsConstructor
@RestController
@Slf4j
@Validated
public class PriceController {

  private final MessageSource messageSource;

  private final PriceService priceService;

  /**
   * Method to verify that all dependencies and requirements have been set.
   *
   * @author fabian.lobo
   * @since 1.0.0
   */
  public void init() {
    notNull(messageSource, "The property [messageSource] has not been set.");
    notNull(priceService, "The property [priceService] has not been set.");

    log.info("PriceController has been successfully initialized.");
  }

  @GetMapping(path = "/services/rest/price", produces = APPLICATION_JSON_VALUE)
  ResponseEntity<PriceModel> getPrice(
      @NotNull(
              message = "Product ID must exists and cannot be null",
              groups = Product.NotNull.class)
          @Min(
              value = 1,
              message =
                  "Product ID must be greater than {value} [Current value: ${validatedValue}]",
              groups = Product.Min.class)
          @Valid
          @RequestParam(value = "product", required = true)
          Integer product,
      @NotNull(message = "Brand ID must exists and cannot be null", groups = Brand.NotNull.class)
          @Min(
              value = 1,
              message = "Brand ID must be greater than {value} [Current value: ${validatedValue}]",
              groups = Brand.Min.class)
          @Valid
          @RequestParam(value = "brand", required = true)
          Integer brand,
      @NotNull(message = "Date must exists and cannot be null", groups = Date.NotNull.class)
          @Valid
          @RequestParam(value = "date", required = true)
          @DateTimeFormat(
              iso = DateTimeFormat.ISO.DATE_TIME,
              fallbackPatterns = "yyyy-MM-dd'T'HH:mm:ss")
          LocalDateTime date)
      throws Exception {
    final Price price = priceService.getPrice(product, brand, date);
    final PriceModel response =
        new PriceModel(
            price.getId(),
            price.getProduct(),
            price.getBrand(),
            price.getPrice().doubleValue(),
            price.getCurrency(),
            price.getStartDateTime(),
            price.getEndDateTime());
    return ResponseEntity.ok(response);
  }
}
