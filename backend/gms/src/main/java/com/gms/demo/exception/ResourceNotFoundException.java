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
 * The serialVersionUID.
 */
  private static final long serialVersionUID = 1L;

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
   * @param resourceNamex The name of the resource that was not found.
   * @param fieldNamex    The name of the field that was used in the query.
   * @param fieldValuex   The field value that was used in the query.
   */
  public ResourceNotFoundException(
      final String resourceNamex,
      final String fieldNamex,
      final long fieldValuex
  ) {
    super(
        String.format(
        "%s not found with %s : %s",
        resourceNamex,
        fieldNamex,
        fieldValuex
      )
    );
    this.resourceName = resourceNamex;
    this.fieldName = fieldNamex;
    this.fieldValue = fieldValuex;
  }

  /**
   * constructor.
   *
   * @param string message
   */
  public ResourceNotFoundException(final String string) {
    super(string);
}

/**
   * Gets the name of the resource that was not found.
   *
   * @return The name of the resource.
   */
  public final String getResourceName() {
    return resourceName;
  }

  /**
   * Gets the name of the field that was used in the query.
   *
   * @return The name of the field.
   */
  public final String getFieldName() {
    return fieldName;
  }

  /**
   * Gets the field value that was used in the query.
   *
   * @return The field value.
   */
  public final long getFieldValue() {
    return fieldValue;
  }
}
