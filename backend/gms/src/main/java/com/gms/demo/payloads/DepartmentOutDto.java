package com.gms.demo.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The `DepartmentOutDto` class represents a Data Transfer Object
 * (DTO) for outbound department information.
 * It contains details about a department, including its name,
 * associated members, and associated tickets.
 *
 * @version 1.0
 * @since 28-08-2023
 */
public class DepartmentOutDto {

  /**
   * The unique identifier for the department.
   */
  private Integer departmentId;

  /**
   * get the department ID.
   */
  public final Integer getDepartmentId() {
    return departmentId;
  }

  /**
   * sets the department ID.
   */
  public final void setDepartmentId(final Integer departmentId) {
    this.departmentId = departmentId;
  }

  /**
   * The name of the department.
   */
  private String departmentName;

  /**
   * The list of members associated with the department.
   */
  @JsonIgnore
  private List<MemberOutDto> members = new ArrayList<>();

  /**
   * The list of tickets associated with the department.
   */
  @JsonIgnore
  private List<TicketOutDto> tickets = new ArrayList<>();

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
   * @param departmentName The name to set for the department.
   */
  public final void setDepartmentName(final String departmentName) {
    this.departmentName = departmentName;
  }

  /**
   * Gets the list of members associated with the department.
   *
   * @return The list of associated members as MemberOutDto objects.
   */
  public final List<MemberOutDto> getMembers() {
    return members;
  }

  /**
   * Sets the list of members associated with the department.
   *
   * @param members The list of associated members as MemberOutDto objects.
   */
  public final void setMembers(final List<MemberOutDto> members) {
    this.members = members;
  }

  /**
   * Gets the list of tickets associated with the department.
   *
   * @return The list of associated tickets as TicketOutDto objects.
   */
  public final List<TicketOutDto> getTickets() {
    return tickets;
  }

  /**
   * Sets the list of tickets associated with the department.
   *
   * @param tickets The list of associated tickets as TicketOutDto objects.
   */
  public final void setTickets(final List<TicketOutDto> tickets) {
    this.tickets = tickets;
  }

  /**
   * Returns a string representation of the DepartmentOutDto object.
   *
   * @return A string containing department details.
   */
  @Override
  public final String toString() {
    return "DepartmentOutDto [departmentName="
       + departmentName + "]";
  }

  /**
   * Constructs a DepartmentOutDto object with the
   * specified department name,
   * associated members, and associated tickets.
   *
   * @param departmentName The name of the department.
   * @param members     The list of associated members as MemberOutDto objects.
   * @param tickets     The list of associated tickets as TicketOutDto objects.
   */
  public DepartmentOutDto(
      final String departmentName,
      final List<MemberOutDto> members,
      final List<TicketOutDto> tickets
  ) {
    super();
    this.departmentName = departmentName;
    this.members = members;
    this.tickets = tickets;
  }

  /**
   * Default constructor for DepartmentOutDto class.
   */
  public DepartmentOutDto() {
    // Default constructor
  }

  @Override
  public final int hashCode() {
    return Objects.hash(departmentId, departmentName,
    members, tickets);
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
    DepartmentOutDto other = (DepartmentOutDto) obj;
    return (
      Objects.equals(departmentId, other.departmentId)
      &&
      Objects.equals(departmentName, other.departmentName)
      &&
      Objects.equals(members, other.members)
      &&
      Objects.equals(tickets, other.tickets)
      );
  }
  
  /**
   * Constructs a DepartmentOutDto object. 
   *
   *@param departmentId department Id
   *@param departmentName department Name
   *@param members list of members
   *@param tickets list of tickets
   */
  public DepartmentOutDto(
      final Integer departmentId,
      final String departmentName,
      final List<MemberOutDto> members,
      final List<TicketOutDto> tickets
  ) {
    super();
    this.departmentId = departmentId;
    this.departmentName = departmentName;
    this.members = members;
    this.tickets = tickets;
  }
}
