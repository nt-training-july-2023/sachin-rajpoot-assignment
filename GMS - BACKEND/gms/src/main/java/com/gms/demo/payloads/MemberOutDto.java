package com.gms.demo.payloads;

import com.gms.demo.entity.Role;
import java.util.List;
import java.util.Objects;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class MemberOutDto {
	
	  /**
	   * The unique identifier for the member.
	   */
	  private Integer memberId;

  public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

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
   * @param name The name to set for the member.
   */
  public final void setName(final String name) {
    this.name = name;
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
   * @param email The email to set for the member.
   */
  public final void setEmail(final String email) {
    this.email = email;
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
   * @param role The role to set for the member.
   */
  public final void setRole(final Role role) {
    this.role = role;
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
   * @param departmentName The department name to set for the member.
   */
  public final void setDepartmentName(final String departmentName) {
    this.departmentName = departmentName;
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
   * @param isFirstLogin `true` if it's the member's first login; otherwise, `false`.
   */
  public final void setIsFirstLogin(final Boolean isFirstLogin) {
    this.isFirstLogin = isFirstLogin;
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
   * @param ticketOutDtos The list of TicketOutDto objects to set.
   */
  public final void setTickets(final List<TicketOutDto> ticketOutDtos) {
    this.tickets = ticketOutDtos;
  }

  /**
   * Returns a string representation of the MemberOutDto object.
   *
   * @return A string containing member details.
   */
  @Override
  public final String toString() {
    return (
      "MemberOutDto [name=" +
      name +
      ", email=" +
      email +
      ", role=" +
      role +
      ", departmentName=" +
      departmentName +
      ", isFirstLogin=" +
      isFirstLogin +
      "]"
    );
  }

  /**
   * Constructs a MemberOutDto object with the specified member details.
   *
   * @param name            The name of the member.
   * @param email           The email of the member.
   * @param role            The role of the member.
   * @param departmentName  The name of the department to which the member belongs.
   * @param isFirstLogin    Indicates if it's the member's first login.
   * @param ticketOutDtos   A list of TicketOutDto objects associated with the member.
   */
  public MemberOutDto(
    final String name,
    final String email,
    final Role role,
    final String departmentName,
    final Boolean isFirstLogin,
    final List<TicketOutDto> ticketOutDtos
  ) {
    super();
    this.name = name;
    this.email = email;
    this.role = role;
    this.departmentName = departmentName;
    this.isFirstLogin = isFirstLogin;
    this.tickets = ticketOutDtos;
  }

  /**
   * Default constructor for the MemberOutDto class.
   */
  public MemberOutDto() {
    super();
  }

@Override
public int hashCode() {
	return Objects.hash(departmentName, email, isFirstLogin, memberId, name, role, tickets);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	MemberOutDto other = (MemberOutDto) obj;
	return Objects.equals(departmentName, other.departmentName) && Objects.equals(email, other.email)
			&& Objects.equals(isFirstLogin, other.isFirstLogin) && Objects.equals(memberId, other.memberId)
			&& Objects.equals(name, other.name) && role == other.role && Objects.equals(tickets, other.tickets);
}

public MemberOutDto(Integer memberId, String name, String email, Role role, String departmentName, Boolean isFirstLogin,
		List<TicketOutDto> tickets) {
	super();
	this.memberId = memberId;
	this.name = name;
	this.email = email;
	this.role = role;
	this.departmentName = departmentName;
	this.isFirstLogin = isFirstLogin;
	this.tickets = tickets;
}
  
  
}
