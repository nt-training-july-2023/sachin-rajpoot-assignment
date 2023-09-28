package com.gms.demo.payloads;

import javax.validation.constraints.NotEmpty;

/**
 * Data transfer object (DTO) representing a department.
 */
public class DepartmentDto {

  /**
   * The unique identifier for the department.
   */
  private Integer departmentId;

  /**
   * The name of the department.
   * This field should not be empty.
   */
  @NotEmpty
  private String departmentName;

  /**
   * Get the ID of the department.
   *
   * @return The department ID.
   */
  public final Integer getDepartmentId() {
    return departmentId;
  }

  /**
   * Set the ID of the department.
   *
   * @param departmentIdx The department ID to set.
   */
  public final void setDepartmentId(final Integer departmentIdx) {
    this.departmentId = departmentIdx;
  }

  /**
   * Get the name of the department.
   *
   * @return The department name.
   */
  public final String getDepartmentName() {
    return departmentName;
  }

  /**
   * Set the name of the department.
   *
   * @param departmentNamex The department name to set.
   */
  public final void setDepartmentName(final String departmentNamex) {
    this.departmentName = departmentNamex;
  }

  /**
   * Generate a hash code for the DepartmentDto.
   *
   * @return The hash code.
   */
  @Override
  public final int hashCode() {
    final int prime = 31;
    int result = 1;
    if (departmentId == null) {
      result =
          prime * result + 0;
    } else {
      result =
          prime * result + departmentId.hashCode();
    }
    if (departmentName == null) {
      result =
          prime * result + 0;
    } else {
      result =
          prime * result + departmentName.hashCode();
    }
    return result;
  }

  /**
   * Check if this DepartmentDto is equal to another object.
   *
   * @param obj The object to compare.
   * @return True if the objects are equal, false otherwise.
   */
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

    final DepartmentDto other = (DepartmentDto) obj;
    if (departmentId == null) {
      if (other.departmentId != null) {
        return false;
      }
    } else if (!departmentId.equals(other.departmentId)) {
      return false;
    }

    if (departmentName == null) {
      if (other.departmentName != null) {
        return false;
      }
    } else if (!departmentName.equals(other.departmentName)) {
      return false;
    }

    return true;
  }



  /**
   * Default constructor for the DepartmentDto class.
   * This constructor initializes an instance of
   * DepartmentDto with default values.
   *
   */
  public DepartmentDto() {
    super();
  }
}
