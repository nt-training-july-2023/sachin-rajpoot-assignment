package com.gms.demo.exception;

import com.gms.demo.payloads.ApiResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The GlobalException class handles global exception
 *     handling for the application.
 * It provides exception handlers for specific exception types.
 *
 * @version 1.0
 * @since 28-08-2023
 */
@RestControllerAdvice
public class GlobalExcHandler {

  /**
   * Exception handler for ResourceNotFoundException.
   *
   * @param ex The ResourceNotFoundException instance.
   * @return ResponseEntity containing an ApiResponse
   *     with HTTP status 404 (Not Found).
   */
  @ExceptionHandler(ResourceNotFoundException.class)
public final
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
   * @return ResponseEntity containing a Map of field
   *     validation errors .
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
public final
      ResponseEntity<Map<String, String>>
      methodArgumentNotValidExceptionHandler(
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
   * Exception handler for
   *     HttpRequestMethodNotSupportedException.
   *
   * @param ex The HttpRequestMethodNotSupportedException instance.
   * @return ResponseEntity containing an ApiResponse with HTTP
   *     status 405 (Method Not Allowed).
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
public
      final ResponseEntity<ApiResponse>
      httpRequestMethodNotSupportedExceptionHandler(
      final HttpRequestMethodNotSupportedException ex
  ) {
    String message = "Enter a valid category Id";

    ApiResponse api = new ApiResponse(message, false);
    return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
  }

  /**
   * Exception handler for DataIntegrityViolationException.
   *
   *@param ex    exception
   *@return ApiResponse for voilation
   */
  @ExceptionHandler(DataIntegrityViolationException.class)
public
        final ResponseEntity<ApiResponse>
      dataIntegrityViolationExceptionHandler(
        final DataIntegrityViolationException ex
  ) {
    String message = "THIS FIELD ALREADY EXISTS, CHECK FOR UNIQUE FIELDS.";

    ApiResponse api = new ApiResponse(message, false);
    return new ResponseEntity<>(api, HttpStatus.BAD_REQUEST);
  }

  /**
   * Exception handler for HttpMessageNotReadableException.
   *
   * @param ex HttpMessageNotReadableException exception.
   * @return ResponseEntity with Api Response.
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiResponse> handleHttpMessageNotReadableException(
          final HttpMessageNotReadableException ex) {
    ApiResponse errorResponse = new ApiResponse(
              "Invalid Input, Enum Values Must Be Exact.", false);
    return new ResponseEntity<ApiResponse>(
              errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * Exception handler for IllegalArgumentException.
   *
   * @param ex IllegalArgumentException EXCEPTION.
   * @return ResponseEntity aPI RESPONSE.
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiResponse> handleIllegalArgumentException(
          final IllegalArgumentException ex) {
    ApiResponse errorResponse = new ApiResponse(
              ex.getMessage(), false);
    return new ResponseEntity<ApiResponse>(
              errorResponse, HttpStatus.BAD_REQUEST);
  }
}

