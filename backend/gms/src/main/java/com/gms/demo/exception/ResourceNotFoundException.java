package com.gms.demo.exception;

/**
 * Custom exception class for resource not found scenarios.
 * This exception is thrown when a requested resource is not found.
 * It provides information  that led to the exception.
 *
 * @version 1.0
 * @since 28-08-2023
 */
public class ResourceNotFoundException extends RuntimeException {

  /**
   * The name of the resource that was not found.
   */
  private String resourceName;

  /**
   * The name of the field that was used in the query.
   */
  private String fieldName;

  /**
   * The field value that was used in the query.
   */
  private long fieldValue;

  /**
   * Constructs a ResourceNotFoundException with the specified parameters.
   *
   * @param resourceName The name of the resource that was not found.
   * @param fieldName    The name of the field that was used in the query.
   * @param fieldValue   The field value that was used in the query.
   */
  public ResourceNotFoundException(
      final String resourceName,
      final String fieldName,
      final long fieldValue
  ) {
    super(
        String.format(
        "%s not found with %s : %s",
        resourceName,
        fieldName,
        fieldValue
      )
    );
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;
  }

  /**
   * Gets the name of the resource that was not found.
   *
   * @return The name of the resource.
   */
  public String getResourceName() {
    return resourceName;
  }

  /**
   * Gets the name of the field that was used in the query.
   *
   * @return The name of the field.
   */
  public String getFieldName() {
    return fieldName;
  }

  /**
   * Gets the field value that was used in the query.
   *
   * @return The field value.
   */
  public long getFieldValue() {
    return fieldValue;
  }
}
