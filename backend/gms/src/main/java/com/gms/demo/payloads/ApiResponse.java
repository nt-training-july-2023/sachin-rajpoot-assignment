package com.gms.demo.payloads;

import java.util.Objects;

/**
 * The `ApiResponse` class represents a response from an API.
 *
 * @version 1.0
 * @since 28-08-2023
 */
public class ApiResponse {

  /**
   *The message contained in the API response.
   */
  private String message;

  /**
   *The success indicator, which is true
   *    for successful responses and false for errors.
   */
  private boolean success;

  /**
   *Gets the message contained in the API response.
   *
   *@return The message.
   */
  public final String getMessage() {
    return message;
  }

  /**
   *Sets the message to be included in the API response.
   *
   * @param message The message to be set.
   */
  public final void setMessage(final String message) {
    this.message = message;
  }

  /**
   * Checks whether the API response indicates success.
   *
   * @return True if the response is successful; false otherwise.
   */
  public final boolean isSuccess() {
    return success;
  }

  /**
   * Sets the success indicator for the API response.
   *
   * @param success True for successful responses, false for errors.
   */
  public final void setSuccess(final boolean success) {
    this.success = success;
  }

  /**
   * Calculates the hash code for this API
   * response based on its message and success
   * indicator.
   *
   * @return The hash code.
   */
  @Override
  public final int hashCode() {
    return Objects.hash(message, success);
  }

  /**
   * Checks if this API response is equal to another object.
   * Two API responses are considered equal
   * if they have the same message and success indicator.
   *
   * @param obj The object to compare with.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public final boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    ApiResponse other = (ApiResponse) obj;
    return Objects.equals(message, other.message)
        &&
        success == other.success;
  }

  /**
   * Constructs an ApiResponse object with the specified message
   *    and success indicator.
   *
   * @param message The message to include in the response.
   * @param success True for successful responses, false for errors.
   */
  public ApiResponse(final String message, final boolean success) {
    super();
    this.message = message;
    this.success = success;
  }
}
