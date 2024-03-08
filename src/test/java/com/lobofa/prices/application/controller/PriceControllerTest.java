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

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lobofa.prices.config.ApplicationConfiguration;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(
    classes = ApplicationConfiguration.class,
    webEnvironment = WebEnvironment.RANDOM_PORT)
@SuppressWarnings("null")
public class PriceControllerTest {

  @Autowired protected WebApplicationContext context;

  private MockMvc mvc;

  private final String _ENDPOINT = "/services/rest/price";

  private final String _TITLE_NOT_FOUND = "Record not found";
  private final String _TITLE_BAD_REQUEST = "Bad request";
  private final String _TITLE_UNEXPECTED = "Unexpected error";

  @BeforeEach
  public void setup() {
    this.mvc =
        MockMvcBuilders.webAppContextSetup(this.context)
            .defaultRequest(get("/").characterEncoding(StandardCharsets.UTF_8))
            .build();
  }

  @Test
  public void testGetPrice_forTestCaseOne_shouldReturnResult() throws Exception {
    final String date = "2020-06-14T10:00:00";
    final Integer product = 35455;
    final Integer brand = 1;

    this.mvc
        .perform(
            get(_ENDPOINT)
                .param("product", product.toString())
                .param("brand", brand.toString())
                .param("date", date))
        .andDo(log())
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.product", is(product)))
        .andExpect(jsonPath("$.brand", is(brand)))
        .andExpect(jsonPath("$.price", is(35.50)))
        .andExpect(jsonPath("$.currency", is("EUR")))
        .andExpect(jsonPath("$.startDate", is("2020-06-14T00:00:00")))
        .andExpect(jsonPath("$.endDate", is("2020-12-31T23:59:59")));
  }

  @Test
  public void testGetPrice_forTestCaseTwo_shouldReturnResult() throws Exception {
    final String date = "2020-06-14T16:00:00";
    final Integer product = 35455;
    final Integer brand = 1;

    this.mvc
        .perform(
            get(_ENDPOINT)
                .param("product", product.toString())
                .param("brand", brand.toString())
                .param("date", date))
        .andDo(log())
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.id", is(2)))
        .andExpect(jsonPath("$.product", is(product)))
        .andExpect(jsonPath("$.brand", is(brand)))
        .andExpect(jsonPath("$.price", is(25.45)))
        .andExpect(jsonPath("$.currency", is("EUR")))
        .andExpect(jsonPath("$.startDate", is("2020-06-14T15:00:00")))
        .andExpect(jsonPath("$.endDate", is("2020-06-14T18:30:00")));
  }

  @Test
  public void testGetPrice_forTestCaseThree_shouldReturnResult() throws Exception {
    final String date = "2020-06-14T21:00:00";
    final Integer product = 35455;
    final Integer brand = 1;

    this.mvc
        .perform(
            get(_ENDPOINT)
                .param("product", product.toString())
                .param("brand", brand.toString())
                .param("date", date))
        .andDo(log())
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.product", is(product)))
        .andExpect(jsonPath("$.brand", is(brand)))
        .andExpect(jsonPath("$.price", is(35.50)))
        .andExpect(jsonPath("$.currency", is("EUR")))
        .andExpect(jsonPath("$.startDate", is("2020-06-14T00:00:00")))
        .andExpect(jsonPath("$.endDate", is("2020-12-31T23:59:59")));
  }

  @Test
  public void testGetPrice_forTestCaseFour_shouldReturnResult() throws Exception {
    final String date = "2020-06-15T10:00:00";
    final Integer product = 35455;
    final Integer brand = 1;

    this.mvc
        .perform(
            get(_ENDPOINT)
                .param("product", product.toString())
                .param("brand", brand.toString())
                .param("date", date))
        .andDo(log())
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.id", is(3)))
        .andExpect(jsonPath("$.product", is(product)))
        .andExpect(jsonPath("$.brand", is(brand)))
        .andExpect(jsonPath("$.price", is(30.50)))
        .andExpect(jsonPath("$.currency", is("EUR")))
        .andExpect(jsonPath("$.startDate", is("2020-06-15T00:00:00")))
        .andExpect(jsonPath("$.endDate", is("2020-06-15T11:00:00")));
  }

  @Test
  public void testGetPrice_forTestCaseFive_shouldReturnResult() throws Exception {
    final String date = "2020-06-16T21:00:00";
    final Integer product = 35455;
    final Integer brand = 1;

    this.mvc
        .perform(
            get(_ENDPOINT)
                .param("product", product.toString())
                .param("brand", brand.toString())
                .param("date", date))
        .andDo(log())
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.id", is(4)))
        .andExpect(jsonPath("$.product", is(product)))
        .andExpect(jsonPath("$.brand", is(brand)))
        .andExpect(jsonPath("$.price", is(38.95)))
        .andExpect(jsonPath("$.currency", is("EUR")))
        .andExpect(jsonPath("$.startDate", is("2020-06-15T16:00:00")))
        .andExpect(jsonPath("$.endDate", is("2020-12-31T23:59:59")));
  }

  @Test
  public void testGetPrice_withValidValues_withNoPriceFound_shouldReturnNotFound()
      throws Exception {

    // 1. Non-existing product
    String date = "2020-06-16T21:00:00";
    String brand = "1";
    String product = "2";

    String message =
        String.format(
            "No price was found for the given values[Product %s, Brand %s, Date %s].",
            product, brand, date);
    this.mvc
        .perform(get(_ENDPOINT).param("product", product).param("brand", brand).param("date", date))
        .andDo(log())
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(APPLICATION_PROBLEM_JSON_VALUE))
        .andExpect(jsonPath("$.title", is(_TITLE_NOT_FOUND)))
        .andExpect(jsonPath("$.detail", is(message)));

    // 2. Non-existing brand
    date = "2020-06-16T21:00:00";
    brand = "2";
    product = "35455";

    message =
        String.format(
            "No price was found for the given values[Product %s, Brand %s, Date %s].",
            product, brand, date);
    this.mvc
        .perform(get(_ENDPOINT).param("product", product).param("brand", brand).param("date", date))
        .andDo(log())
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(APPLICATION_PROBLEM_JSON_VALUE))
        .andExpect(jsonPath("$.title", is(_TITLE_NOT_FOUND)))
        .andExpect(jsonPath("$.detail", is(message)));

    // 3. Non-registered date
    date = "2023-06-16T21:00:00";
    brand = "1";
    product = "35455";

    message =
        String.format(
            "No price was found for the given values[Product %s, Brand %s, Date %s].",
            product, brand, date);
    this.mvc
        .perform(get(_ENDPOINT).param("product", product).param("brand", brand).param("date", date))
        .andDo(log())
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(APPLICATION_PROBLEM_JSON_VALUE))
        .andExpect(jsonPath("$.title", is(_TITLE_NOT_FOUND)))
        .andExpect(jsonPath("$.detail", is(message)));
  }

  @Test
  public void testGetPrice_withInvalidValues_withInvalidProduct_shouldReturnBadRequest()
      throws Exception {
    final String date = "2020-06-16T21:00:00";
    final String brand = "1";

    // 1. Parameter not present
    String message =
        "Required request parameter 'product' for method parameter type Integer is not present";
    this.mvc
        .perform(get(_ENDPOINT).param("brand", brand).param("date", date))
        .andDo(log())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(APPLICATION_PROBLEM_JSON_VALUE))
        .andExpect(jsonPath("$.title", is(_TITLE_BAD_REQUEST)))
        .andExpect(jsonPath("$.detail", is(message)));

    // 2. Parameter with zero value
    String product = "0";
    message = "Product ID must be greater than 1 [Current value: 0]";
    this.mvc
        .perform(get(_ENDPOINT).param("product", product).param("brand", brand).param("date", date))
        .andDo(log())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(APPLICATION_PROBLEM_JSON_VALUE))
        .andExpect(jsonPath("$.title", is(_TITLE_BAD_REQUEST)))
        .andExpect(jsonPath("$.detail", is(message)));

    // 3. Parameter with negative value
    product = "-1";
    message = "Product ID must be greater than 1 [Current value: -1]";
    this.mvc
        .perform(get(_ENDPOINT).param("product", product).param("brand", brand).param("date", date))
        .andDo(log())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(APPLICATION_PROBLEM_JSON_VALUE))
        .andExpect(jsonPath("$.title", is(_TITLE_BAD_REQUEST)))
        .andExpect(jsonPath("$.detail", is(message)));

    // 4. Parameter with invalid value
    product = "invalid";
    message = "Invalid value for the parameter product [Value: invalid]";
    this.mvc
        .perform(get(_ENDPOINT).param("product", product).param("brand", brand).param("date", date))
        .andDo(log())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(APPLICATION_PROBLEM_JSON_VALUE))
        .andExpect(jsonPath("$.title", is(_TITLE_BAD_REQUEST)))
        .andExpect(jsonPath("$.detail", is(message)));

    // 5. Parameter with too long value
    product = Long.toString(Long.MAX_VALUE);
    message = "Invalid value for the parameter product [Value: 9223372036854775807]";
    this.mvc
        .perform(get(_ENDPOINT).param("product", product).param("brand", brand).param("date", date))
        .andDo(log())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(APPLICATION_PROBLEM_JSON_VALUE))
        .andExpect(jsonPath("$.title", is(_TITLE_BAD_REQUEST)))
        .andExpect(jsonPath("$.detail", is(message)));
  }

  @Test
  public void testGetPrice_withInvalidValues_withInvalidBrand_shouldReturnBadRequest()
      throws Exception {
    final String date = "2020-06-16T21:00:00";
    final String product = "35455";

    // 1. Parameter not present
    String message =
        "Required request parameter 'brand' for method parameter type Integer is not present";
    this.mvc
        .perform(get(_ENDPOINT).param("product", product).param("date", date))
        .andDo(log())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(APPLICATION_PROBLEM_JSON_VALUE))
        .andExpect(jsonPath("$.title", is(_TITLE_BAD_REQUEST)))
        .andExpect(jsonPath("$.detail", is(message)));

    // 2. Parameter with zero value
    String brand = "0";
    message = "Brand ID must be greater than 1 [Current value: 0]";
    this.mvc
        .perform(get(_ENDPOINT).param("product", product).param("brand", brand).param("date", date))
        .andDo(log())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(APPLICATION_PROBLEM_JSON_VALUE))
        .andExpect(jsonPath("$.title", is(_TITLE_BAD_REQUEST)))
        .andExpect(jsonPath("$.detail", is(message)));

    // 3. Parameter with negative value
    brand = "-1";
    message = "Brand ID must be greater than 1 [Current value: -1]";
    this.mvc
        .perform(get(_ENDPOINT).param("product", product).param("brand", brand).param("date", date))
        .andDo(log())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(APPLICATION_PROBLEM_JSON_VALUE))
        .andExpect(jsonPath("$.title", is(_TITLE_BAD_REQUEST)))
        .andExpect(jsonPath("$.detail", is(message)));

    // 4. Parameter with invalid value
    brand = "invalid";
    message = "Invalid value for the parameter brand [Value: invalid]";
    this.mvc
        .perform(get(_ENDPOINT).param("product", product).param("brand", brand).param("date", date))
        .andDo(log())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(APPLICATION_PROBLEM_JSON_VALUE))
        .andExpect(jsonPath("$.title", is(_TITLE_BAD_REQUEST)))
        .andExpect(jsonPath("$.detail", is(message)));

    // 5. Parameter with too long value
    brand = Long.toString(Long.MAX_VALUE);
    message = "Invalid value for the parameter brand [Value: 9223372036854775807]";
    this.mvc
        .perform(get(_ENDPOINT).param("product", product).param("brand", brand).param("date", date))
        .andDo(log())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(APPLICATION_PROBLEM_JSON_VALUE))
        .andExpect(jsonPath("$.title", is(_TITLE_BAD_REQUEST)))
        .andExpect(jsonPath("$.detail", is(message)));
  }

  @Test
  public void testGetPrice_withInvalidValues_withInvalidDate_shouldReturnBadRequest()
      throws Exception {
    final String brand = "1";
    final String product = "35455";

    // 1. Parameter not present
    String message =
        "Required request parameter 'date' for method parameter type LocalDateTime is not present";
    this.mvc
        .perform(get(_ENDPOINT).param("product", product).param("brand", brand))
        .andDo(log())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(APPLICATION_PROBLEM_JSON_VALUE))
        .andExpect(jsonPath("$.title", is(_TITLE_BAD_REQUEST)))
        .andExpect(jsonPath("$.detail", is(message)));

    // 2. Parameter with invalid value
    final String date = "invalid";
    message = "Invalid value for the parameter date [Value: invalid]";
    this.mvc
        .perform(get(_ENDPOINT).param("product", product).param("brand", brand).param("date", date))
        .andDo(log())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(APPLICATION_PROBLEM_JSON_VALUE))
        .andExpect(jsonPath("$.title", is(_TITLE_BAD_REQUEST)))
        .andExpect(jsonPath("$.detail", is(message)));
  }

  @Test
  public void testGetPrice_withInvalidValues_withInvalidUrl_shouldReturnNotFound()
      throws Exception {
    final String date = "2020-06-16T21:00:00";
    final String product = "35455";
    final String brand = "1";

    this.mvc
        .perform(
            get("/services/rest/prices")
                .param("product", product)
                .param("brand", brand)
                .param("date", date))
        .andDo(log())
        .andExpect(status().isNotFound());
  }

  @Test
  public void testGetPrice_withInvalidValues_withInvalidHeader_shouldReturnNotFound()
      throws Exception {
    final String date = "2020-06-16T21:00:00";
    final String product = "35455";
    final String brand = "1";

    this.mvc
        .perform(
            get(_ENDPOINT)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_ATOM_XML)
                .param("product", product)
                .param("brand", brand)
                .param("date", date))
        .andDo(log())
        .andExpect(status().isInternalServerError())
        .andExpect(content().contentType(APPLICATION_PROBLEM_JSON_VALUE))
        .andExpect(jsonPath("$.title", is(_TITLE_UNEXPECTED)))
        .andExpect(jsonPath("$.detail", is("No acceptable representation")));
  }
}
