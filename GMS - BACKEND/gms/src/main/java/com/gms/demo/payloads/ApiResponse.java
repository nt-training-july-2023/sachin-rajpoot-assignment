package com.gms.demo.payloads;

import java.util.Objects;

/**
 * The `ApiResponse` class represents a response from an API, containing a message and a success
 * indicator.
 */
public class ApiResponse {

  private String message;
  private boolean success;

  public final String getMessage() {
    return message;
  }

  public final void setMessage(final String message) {
    this.message = message;
  }

  public final boolean isSuccess() {
    return success;
  }

  public final void setSuccess(final boolean success) {
    this.success = success;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(message, success);
  }

  @Override
  public final boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ApiResponse other = (ApiResponse) obj;
    return Objects.equals(message, other.message) && success == other.success;
  }

  public ApiResponse(final String message, final boolean success) {
    super();
    this.message = message;
    this.success = success;
  }
}
