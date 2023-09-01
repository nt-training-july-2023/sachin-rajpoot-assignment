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

@RestControllerAdvice
public class GlobalException {

  /**
   * @param ex
   * @return ResponseEntity<ApiResponse>
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  ResponseEntity<ApiResponse> resourceNotFoundHandler(final ResourceNotFoundException ex) {
    String message = ex.getMessage();
    ApiResponse api = new ApiResponse(message, false);
    return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
  }

  /**
   * @param ex
   * @return ResponseEntity<Map<String, String>>
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(
      final MethodArgumentNotValidException ex) {
    Map<String, String> res = new HashMap<String, String>();
    ex
        .getBindingResult()
        .getAllErrors()
        .forEach(
            err -> {
              String fieldName = ((FieldError) err).getField();
              String message = err.getDefaultMessage();
              res.put(fieldName, message);
            });

    return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  final ResponseEntity<ApiResponse> httpRequestMethodNotSupportedExceptionHandler(
      final HttpRequestMethodNotSupportedException ex
  ) {
    String message = "Enter a valid category Id";

    ApiResponse api = new ApiResponse(message, false);
    return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
  }
}
