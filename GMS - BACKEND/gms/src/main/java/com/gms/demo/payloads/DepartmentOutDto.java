package com.gms.demo.payloads;

import java.util.ArrayList;
import java.util.List;

/**
 * The `DepartmentOutDto` class represents a Data Transfer Object (DTO) for outbound department information.
 * It contains details about a department, including its name, associated members, and associated tickets.
 *
 * @version 1.0
 * @since 28-08-2023
 */
public class DepartmentOutDto {

  /**
   * The name of the department.
   */
  private String departmentName;

  /**
   * The list of members associated with the department.
   */
  private List<MemberOutDto> members = new ArrayList<>();

  /**
   * The list of tickets associated with the department.
   */
  private List<TicketOutDto> tickets = new ArrayList<>();

  /**
   * Gets the name of the department.
   *
   * @return The name of the department.
   */
  public String getDepartmentName() {
    return departmentName;
  }

  /**
   * Sets the name of the department.
   *
   * @param departmentName The name to set for the department.
   */
  public void setDepartmentName(final String departmentName) {
    this.departmentName = departmentName;
  }

  /**
   * Gets the list of members associated with the department.
   *
   * @return The list of associated members as MemberOutDto objects.
   */
  public List<MemberOutDto> getMembers() {
    return members;
  }

  /**
   * Sets the list of members associated with the department.
   *
   * @param members The list of associated members as MemberOutDto objects.
   */
  public void setMembers(final List<MemberOutDto> members) {
    this.members = members;
  }

  /**
   * Gets the list of tickets associated with the department.
   *
   * @return The list of associated tickets as TicketOutDto objects.
   */
  public List<TicketOutDto> getTickets() {
    return tickets;
  }

  /**
   * Sets the list of tickets associated with the department.
   *
   * @param tickets The list of associated tickets as TicketOutDto objects.
   */
  public void setTickets(final List<TicketOutDto> tickets) {
    this.tickets = tickets;
  }

  /**
   * Returns a string representation of the DepartmentOutDto object.
   *
   * @return A string containing department details.
   */
  @Override
  public String toString() {
    return "DepartmentOutDto [departmentName=" + departmentName + "]";
  }

  /**
   * Constructs a DepartmentOutDto object with the specified department name, associated members, and associated tickets.
   *
   * @param departmentName The name of the department.
   * @param members        The list of associated members as MemberOutDto objects.
   * @param tickets        The list of associated tickets as TicketOutDto objects.
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
}
