package com.gms.demo.payloads;

import javax.validation.constraints.NotEmpty;

/**
 * Data transfer object (DTO) representing a department.
 */
public class DepartmentDto {
    /**
     * The unique identifier for the member.
     */
    private Integer departmentId;

    /**
     * The name of the department.
     */
    @NotEmpty
    private String name;

    /**
     * Get the ID of the department.
     *
     * @return The department ID.
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Set the ID of the department.
     *
     * @param departmentId The department ID to set.
     */
    public void setDepartmentId(final Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * Get the name of the department.
     *
     * @return The department name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the department.
     *
     * @param name The department name to set.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Generate a hash code for the DepartmentDto.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((departmentId == null) ? 0 : departmentId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /**
     * Check if this DepartmentDto is equal to another object.
     *
     * @param obj The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
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

        if (name == null) {
            if (other.name != null) {
                return false;
            }

        } else if (!name.equals(other.name)) {
            return false;
        }

        return true;
    }

    /**
     * Generate a string representation of the DepartmentDto.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "DepartmentDto [departmentId=" + departmentId + ", name=" + name + "]";
    }
}
