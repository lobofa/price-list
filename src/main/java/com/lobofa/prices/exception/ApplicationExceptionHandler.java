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

import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON;

import com.lobofa.prices.application.model.ErrorMessageModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
@SuppressWarnings("null")
public class ApplicationExceptionHandler {

  /**
   * Method to verify that all dependencies and requirements have been set.
   *
   * @author fabian.lobo
   * @since 1.0.0
   */
  public void init() {
    log.info("ApplicationExceptionHandler has been successfully initialized.");
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorMessageModel> handleNotFoundException(NotFoundException exception) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .contentType(APPLICATION_PROBLEM_JSON)
        .body(new ErrorMessageModel("Record not found", exception.getMessage()));
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<ErrorMessageModel> handleAnyThrowable(Throwable throwable) {

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .contentType(APPLICATION_PROBLEM_JSON)
        .body(new ErrorMessageModel("Unexpected error", throwable.getMessage()));
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorMessageModel> handleConstraintViolationException(
      ConstraintViolationException exception) {

    ConstraintViolation<?> violation =
        exception.getConstraintViolations().stream().findFirst().get();

    return ResponseEntity.badRequest()
        .contentType(APPLICATION_PROBLEM_JSON)
        .body(new ErrorMessageModel("Bad request", violation.getMessage()));
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ErrorMessageModel> handleMissingServletRequestParameterException(
      MissingServletRequestParameterException exception) {

    return ResponseEntity.badRequest()
        .contentType(APPLICATION_PROBLEM_JSON)
        .body(new ErrorMessageModel("Bad request", exception.getLocalizedMessage()));
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorMessageModel> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException exception) {

    final String name = exception.getName();
    final Object value = exception.getValue();
    final String message =
        String.format(
            "Invalid value for the parameter %s [Value: %s]", name, Objects.toString(value));

    return ResponseEntity.badRequest()
        .contentType(APPLICATION_PROBLEM_JSON)
        .body(new ErrorMessageModel("Bad request", message));
  }
}
