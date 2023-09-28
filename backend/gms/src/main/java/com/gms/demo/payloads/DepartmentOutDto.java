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
   *
   * @return departmentId  department Id
   */
  public final Integer getDepartmentId() {
    return departmentId;
  }

  /**
   * sets the department ID.
   *
   *@param  departmentIdx department Id
   */
  public final void setDepartmentId(final Integer departmentIdx) {
    this.departmentId = departmentIdx;
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
   * @param departmentNamex The name to set for the department.
   */
  public final void setDepartmentName(final String departmentNamex) {
    this.departmentName = departmentNamex;
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
   * @param membersx The list of associated members as MemberOutDto objects.
   */
  public final void setMembers(final List<MemberOutDto> membersx) {
    this.members = membersx;
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
   * @param ticketsx The list of associated tickets as TicketOutDto objects.
   */
  public final void setTickets(final List<TicketOutDto> ticketsx) {
    this.tickets = ticketsx;
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
   * @param departmentNamex The name of the department.
   * @param membersx     The list of associated members as MemberOutDto objects.
   * @param ticketsx     The list of associated tickets as TicketOutDto objects.
   */
  public DepartmentOutDto(
      final String departmentNamex,
      final List<MemberOutDto> membersx,
      final List<TicketOutDto> ticketsx
  ) {
    super();
    this.departmentName = departmentNamex;
    this.members = membersx;
    this.tickets = ticketsx;
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
   *@param departmentIdx department Id
   *@param departmentNamex department Name
   *@param membersx list of members
   *@param ticketsx list of tickets
   */
  public DepartmentOutDto(
      final Integer departmentIdx,
      final String departmentNamex,
      final List<MemberOutDto> membersx,
      final List<TicketOutDto> ticketsx
  ) {
    super();
    this.departmentId = departmentIdx;
    this.departmentName = departmentNamex;
    this.members = membersx;
    this.tickets = ticketsx;
  }
}
