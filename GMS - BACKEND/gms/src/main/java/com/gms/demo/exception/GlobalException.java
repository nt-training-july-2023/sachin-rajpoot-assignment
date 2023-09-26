package com.gms.demo.exception;

import com.gms.demo.payloads.ApiResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The GlobalException class handles global exception handling for the application.
 * It provides exception handlers for specific exception types.
 *
 * @version 1.0
 * @since 28-08-2023
 */
@RestControllerAdvice
public class GlobalException {

  /**
   * Exception handler for ResourceNotFoundException.
   *
   * @param ex The ResourceNotFoundException instance.
   * @return ResponseEntity containing an ApiResponse with HTTP status 404 (Not Found).
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  ResponseEntity<ApiResponse> resourceNotFoundHandler(
    final ResourceNotFoundException ex
  ) {
    String message = ex.getMessage();
    ApiResponse api = new ApiResponse(message, false);
    return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
  }

  /**
   * Exception handler for MethodArgumentNotValidException.
   *
   * @param ex The MethodArgumentNotValidException instance.
   * @return ResponseEntity containing a Map of field validation errors with HTTP status 400 (Bad Request).
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(
    final MethodArgumentNotValidException ex
  ) {
    Map<String, String> res = new HashMap<String, String>();
    ex
      .getBindingResult()
      .getAllErrors()
      .forEach(err -> {
        String fieldName = ((FieldError) err).getField();
        String message = err.getDefaultMessage();
        res.put(fieldName, message);
      });

    return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
  }

  /**
   * Exception handler for HttpRequestMethodNotSupportedException.
   *
   * @param ex The HttpRequestMethodNotSupportedException instance.
   * @return ResponseEntity containing an ApiResponse with HTTP status 405 (Method Not Allowed).
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  final ResponseEntity<ApiResponse> httpRequestMethodNotSupportedExceptionHandler(
    final HttpRequestMethodNotSupportedException ex
  ) {
    String message = "Enter a valid category Id";

    ApiResponse api = new ApiResponse(message, false);
    return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
  }
}