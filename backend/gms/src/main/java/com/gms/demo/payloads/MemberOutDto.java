package com.gms.demo.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gms.demo.entity.Role;
import java.util.List;
import java.util.Objects;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * MemberOutDto -> member in DTO for the member.
 */

public class MemberOutDto {

  /**
   * The unique identifier for the member.
   */
  private Integer memberId;

  /**
   * The name of the member.
   */
  private String name;

  /**
   * The email of the member.
   */
  private String email;

  /**
   * The role of the member, represented as an enumerated type.
   */
  @Enumerated(EnumType.STRING)
  private Role role;

  /**
   * The name of the department to which the member belongs.
   */
  private String departmentName;

  /**
   * Indicates if the member is logging in for the first time.
   */
  private Boolean isFirstLogin;

  /**
   * A list of TicketOutDto objects associated with the member.
   */
  @JsonIgnore
  private List<TicketOutDto> tickets;

  /**
   * Gets the name of the member.
   *
   * @return The name of the member.
   */
  public final String getName() {
    return name;
  }

  /**
   * Sets the name of the member.
   *
   * @param namex The name to set for the member.
   */
  public final void setName(final String namex) {
    this.name = namex;
  }

  /**
   * Gets the email of the member.
   *
   * @return The email of the member.
   */
  public final String getEmail() {
    return email;
  }

  /**
   * Sets the email of the member.
   *
   * @param emailx The email to set for the member.
   */
  public final void setEmail(final String emailx) {
    this.email = emailx;
  }

  /**
   * Gets the getMemberId of the member.
   *
   * @return The getMemberId of the member.
   */
  public final Integer getMemberId() {
    return memberId;
  }

  /**
   * Sets the memberId of the member.
   *
   * @param memberIdx The memberId to set for the member.
   */
  public final void setMemberId(final Integer memberIdx) {
    this.memberId = memberIdx;
  }

  /**
   * Gets the role of the member.
   *
   * @return The role of the member, represented as an enumerated type.
   */
  public final Role getRole() {
    return role;
  }

  /**
   * Sets the role of the member.
   *
   * @param rolex The role to set for the member.
   */
  public final void setRole(final Role rolex) {
    this.role = rolex;
  }

  /**
   * Gets the name of the department to which the member belongs.
   *
   * @return The name of the department.
   */
  public final String getDepartmentName() {
    return departmentName;
  }

  /**
   * Sets the name of the department to which the member belongs.
   *
   * @param departmentNamex The department name to set for the member.
   */
  public final void setDepartmentName(final String departmentNamex) {
    this.departmentName = departmentNamex;
  }

  /**
   * Checks if the member is logging in for the first time.
   *
   * @return `true` if it's the member's first login; otherwise, `false`.
   */
  public final Boolean getIsFirstLogin() {
    return isFirstLogin;
  }

  /**
   * Sets whether it's the member's first login.
   *
   * @param isFirstLoginx `true` if it's the member's first login; otherwise,
   *                     `false`.
   */
  public final void setIsFirstLogin(final Boolean isFirstLoginx) {
    this.isFirstLogin = isFirstLoginx;
  }

  /**
   * Gets a list of TicketOutDto objects associated with the member.
   *
   * @return A list of TicketOutDto objects.
   */
  public final List<TicketOutDto> getTickets() {
    return tickets;
  }

  /**
   * Sets a list of TicketOutDto objects associated with the member.
   *
   * @param ticketOutDtosx The list of TicketOutDto objects to set.
   */
  public final void setTickets(final List<TicketOutDto> ticketOutDtosx) {
    this.tickets = ticketOutDtosx;
  }

  /**
   * Returns a string representation of the MemberOutDto object.
   *
   * @return A string containing member details.
   */
  @Override
  public final String toString() {
    return (
      "MemberOutDto [name="
      +
      name
      +
      ", email="
      +
      email
      +
      ", role="
      +
      role
      +
      ", departmentName="
      +
      departmentName
      +
      ", isFirstLogin="
      +
      isFirstLogin
      +
      "]"
      );
  }

  /**
   * Constructs a MemberOutDto object with the
   * specified member details.
   *
   * @param namex           The name of the member.
   * @param emailx          The email of the member.
   * @param rolex           The role of the member.
   * @param departmentNamex The name of the department to
   *     which the member belongs.
   * @param isFirstLoginx   Indicates if it's the member's
   *     first login.
   * @param ticketOutDtosx  A list of TicketOutDto objects
   *     associated with the member.
   */
  public MemberOutDto(
      final String namex,
      final String emailx,
      final Role rolex,
      final String departmentNamex,
      final Boolean isFirstLoginx,
      final List<TicketOutDto> ticketOutDtosx
  ) {
    super();
    this.name = namex;
    this.email = emailx;
    this.role = rolex;
    this.departmentName = departmentNamex;
    this.isFirstLogin = isFirstLoginx;
    this.tickets = ticketOutDtosx;
  }

  /**
   * Default constructor for the MemberOutDto class.
   */
  public MemberOutDto() {
    super();
  }

  /**
   * Returns hashcode.
   *
   */
  @Override
  public final int hashCode() {
    return Objects.hash(
      departmentName,
      email,
      isFirstLogin,
      memberId,
      name,
      role,
      tickets
    );
  }

  /**
   * compares the object.
   *
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
    MemberOutDto other = (MemberOutDto) obj;
    return (
      Objects.equals(departmentName, other.departmentName)
      &&
      Objects.equals(email, other.email)
      &&
      Objects.equals(isFirstLogin, other.isFirstLogin)
      &&
      Objects.equals(memberId, other.memberId)
      &&
      Objects.equals(name, other.name)
      &&
      role == other.role
      &&
      Objects.equals(tickets, other.tickets)
      );
  }

  /**
   * constructor.
   *
   *@param memberIdx memberId
   *@param namex name
   *@param emailx email
   *@param rolex role
   *@param departmentNamex departmentName
   *@param isFirstLoginx is First Login
   *@param ticketsx tickets
   */
  public MemberOutDto(
      final Integer memberIdx,
      final String namex,
      final String emailx,
      final Role rolex,
      final String departmentNamex,
      final Boolean isFirstLoginx,
      final List<TicketOutDto> ticketsx
  ) {
    super();
    this.memberId = memberIdx;
    this.name = namex;
    this.email = emailx;
    this.role = rolex;
    this.departmentName = departmentNamex;
    this.isFirstLogin = isFirstLoginx;
    this.tickets = ticketsx;
  }
}
