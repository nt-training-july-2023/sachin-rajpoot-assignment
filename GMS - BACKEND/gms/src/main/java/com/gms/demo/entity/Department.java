package com.gms.demo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

/**
 * Represents an Department Entity.
 *
 * @author Sachin Singh Rajpoot
 *
 * @version 1.0
 * @since 28-08-2023
 */

@Entity
public class Department {
  /**
   * The unique identifier for the department.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer departmentId;

  /**
   * The name of the department.
   */
  @NotEmpty
  private String name;

  /**
   * The list of members associated with this department.
   */
  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Member> members = new ArrayList<>();

  /**
   * The list of tickets associated with this department.
   */
  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Ticket> tickets = new ArrayList<>();

  /**
   * Get the department ID.
   *
   * @return The department ID.
   */
  public Integer getDepartmentId() {
    return departmentId;
  }

  /**
   * Set the department ID.
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
   * Get the list of members associated with the department.
   *
   * @return The list of members.
   */
  public List<Member> getMembers() {
    return members;
  }

  /**
   * Set the list of members associated with the department.
   *
   * @param members The list of members to set.
   */
  public void setMembers(final List<Member> members) {
    this.members = members;
  }

  /**
   * Get the list of tickets associated with the department.
   *
   * @return The list of tickets.
   */
  public List<Ticket> getTickets() {
    return tickets;
  }

  /**
   * Set the list of tickets associated with the department.
   *
   * @param tickets The list of tickets to set.
   */
  public void setTickets(final List<Ticket> tickets) {
    this.tickets = tickets;
  }

  /**
   * Calculates the hash code for the Department object.
   *
   * @return The hash code value.
   */
  @Override
  public int hashCode() {
    return Objects.hash(departmentId, members, name, tickets);
  }

  /**
   * Checks if this Department object is equal to another object.
   *
   * @param obj The object to compare.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Department other = (Department) obj;
    return (Objects.equals(departmentId, other.departmentId)
        &&
        Objects.equals(members, other.members)
        &&
        Objects.equals(name, other.name)
        &&
        Objects.equals(tickets, other.tickets));
  }

  /**
   * Generates a string representation of the Department object.
   *
   * @return The string representation.
   */
  @Override
  public String toString() {
    return ("Department [departmentId="
        +
        departmentId
        +
        ", name="
        +
        name
        +
        ", members="
        +
        members
        +
        ", tickets="
        +
        tickets
        +
        "]");
  }

  /**
   * Constructor for creating a Department object.
   *
   * @param departmentId The department ID.
   * @param name         The name of the department.
   * @param members      The list of members associated with the department.
   * @param tickets      The list of tickets associated with the department.
   */
  public Department(
      final Integer departmentId,
      @NotEmpty final String name,
      final List<Member> members,
      final List<Ticket> tickets) {
    super();
    this.departmentId = departmentId;
    this.name = name;
    this.members = members;
    this.tickets = tickets;
  }

  /**
   * Default constructor for creating a Department object.
   */
  public Department() {
    super();
  }
}
