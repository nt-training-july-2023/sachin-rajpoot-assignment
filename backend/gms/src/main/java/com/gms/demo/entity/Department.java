package com.gms.demo.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
  @Column(unique = true)
  private String departmentName;

  /**
   * The list of members associated with this department.
   */
  @OneToMany(
      mappedBy = "department",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY
  )
  private List<Member> members = new ArrayList<>();

  /**
   * The list of tickets associated with this department.
   */
  @OneToMany(
      mappedBy = "department",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY
  )
  private List<Ticket> tickets = new ArrayList<>();

  /**
   * Sets the unique identifier for the department.
   *
   * @param departmentId The department's unique identifier.
   */
  public final void setDepartmentId(final Integer departmentId) {
    this.departmentId = departmentId;
  }

  /**
   * Gets the departmentId of the department.
   *
   * @return The name of the department.
   */
  public final Integer getDepartmentId() {
    return departmentId;
  }

  /**
   * Gets the name of the department.
   *
   * @return The name of the department.
   */
  public final String getDepartmentName() {
    return departmentName;
  }

  /**
   * Sets the name of the department.
   *
   * @param name The name of the department.
   */
  public final void setDepartmentName(final String name) {
    this.departmentName = name;
  }

  /**
   * Gets the list of members associated with the department.
   *
   * @return The list of members associated with the department.
   */
  public final List<Member> getMembers() {
    return members;
  }

  /**
   * Sets the list of members associated with the department.
   *
   * @param members The list of members associated with the department.
   */
  public final void setMembers(final List<Member> members) {
    this.members = members;
  }

  /**
   * Gets the list of tickets associated with the department.
   *
   * @return The list of tickets associated with the department.
   */
  public final List<Ticket> getTickets() {
    return tickets;
  }

  /**
   * Sets the list of tickets associated with the department.
   *
   * @param tickets The list of tickets associated with the department.
   */
  public final void setTickets(final List<Ticket> tickets) {
    this.tickets = tickets;
  }

  /**
   * Returns a string representation of the Department object.
   *
   * @return A string containing department details.
   */
  @Override
  public final String toString() {
    return (
      "Department [departmentId="
      +
      departmentId
      +
      ", departmentName="
      +
      departmentName
      +
      "]"
      );
  }

  /**
   * Constructs a Department object with the specified parameters.
   *
   * @param departmentId The unique identifier for the department.
   * @param members      The list of members associated with the department.
   * @param tickets      The list of tickets associated with the department.
   * @param departmentName  Department name
   */
  public Department(
      final Integer departmentId,
      @NotEmpty final String departmentName,
      final List<Member> members,
      final List<Ticket> tickets
  ) {
    super();
    this.departmentId = departmentId;
    this.departmentName = departmentName;
    this.members = members;
    this.tickets = tickets;
  }

  /**
   * Default constructor for Department class.
   */
  public Department() {
    super();
  }
}
