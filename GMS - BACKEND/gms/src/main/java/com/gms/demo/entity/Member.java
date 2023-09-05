package com.gms.demo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Represents an Member Entity.
 *
 * @author Sachin Singh Rajpoot
 *
 * @version 1.0
 * @since 28-08-2023
 */

/**
 * 
 */
@Entity
public class Member {
  /**
   * The unique identifier for the member.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer memberId;

  /**
   * The name of the member.
   */
  @NotEmpty
  private String name;

  /**
   * The email address of the member.
   */
  @NotEmpty
  @Email
  @Column(unique = true)
  private String email;

  /**
   * The password of the member.
   */
  @NotEmpty
  private String password;
  

  /**
   * The Check for the first login of the member.
   */
//  @NotEmpty
  private Boolean isFirstLogin;

  /**
   * The role of the member.
   */
  @Enumerated(EnumType.STRING)
  private Role role;

  /**
   * The department to which this member belongs.
   */
  @ManyToOne
  private Department department;

  /**
   * The list of tickets associated with this member.
   */
  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Ticket> tickets = new ArrayList<>();

  /**
   * Get the member ID.
   *
   * @return The member ID.
   */
  public Integer getMemberId() {
    return memberId;
  }

  /**
   * Set the member ID.
   *
   * @param memberId The member ID to set.
   */
  public void setMemberId(final Integer memberId) {
    this.memberId = memberId;
  }

  /**
   * Get the name of the member.
   *
   * @return The member name.
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the member.
   *
   * @param name The member name to set.
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Get the email of the member.
   *
   * @return The member email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Set the email of the member.
   *
   * @param email The member email to set.
   */
  public void setEmail(final String email) {
    this.email = email;
  }

  /**
   * Get the password of the member.
   *
   * @return The member password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Set the password of the member.
   *
   * @param password The member password to set.
   */
  public void setPassword(final String password) {
    this.password = password;
  }

  /**
   * Get the role of the member.
   *
   * @return The member role.
   */
  public Role getRole() {
    return role;
  }

  /**
   * Set the role of the member.
   *
   * @param role The member role to set.
   */
  public void setRole(final Role role) {
    this.role = role;
  }

  /**
   * Get the department of the member.
   *
   * @return The member department.
   */
  public Department getDepartment() {
    return department;
  }

  /**
   * Set the department of the member.
   *
   * @param department The member department to set.
   */
  public void setDepartment(final Department department) {
    this.department = department;
  }

  /**
   * Get the list of tickets associated with the member.
   *
   * @return The list of tickets.
   */
  public List<Ticket> getTickets() {
    return tickets;
  }

  /**
   * Set the list of tickets associated with the member.
   *
   * @param tickets The list of tickets to set.
   */
  public void setTickets(final List<Ticket> tickets) {
    this.tickets = tickets;
  }

  @Override
public String toString() {
	return "Member [memberId=" + memberId + ", name=" + name + ", email=" + email + ", password=" + password
			+ ", isFirstLogin=" + isFirstLogin + ", role=" + role + ", department=" + department + ", tickets="
			+ tickets + "]";
}

  /**
   * Constructor for creating a Member object.
   *
   * @param memberId   The member ID.
   * @param name       The name of the member.
   * @param email      The email of the member.
   * @param password   The password of the member.
   * @param role       The role of the member.
   * @param department The department of the member.
   * @param tickets    The list of tickets associated with the member.
   */
  public Member(
      final Integer memberId,
      @NotEmpty final String name,
      @NotEmpty @Email final String email,
      @NotEmpty final String password,
      @NotEmpty final Role role,
      final Department department,
      final List<Ticket> tickets) {
    super();
    this.memberId = memberId;
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
    this.department = department;
    this.tickets = tickets;
  }

  /**
   * Constructor for creating a Member object.
   *
   * @param memberId   The member ID.
   * @param name       The name of the member.
   * @param email      The email of the member.
   * @param password   The password of the member.
   */
  public Member(
      final Integer memberId,
      @NotEmpty final String name,
      @NotEmpty @Email final String email,
      @NotEmpty final String password) {
    super();
    this.memberId = memberId;
    this.name = name;
    this.email = email;
    this.password = password;
  }

  /**
   * Default constructor for creating a Member object.
   */
  public Member() {
    super();
    // TODO Auto-generated constructor stub
  }

public Boolean getIsFirstLogin() {
	return isFirstLogin;
}

public void setIsFirstLogin(Boolean isFirstLogin) {
	this.isFirstLogin = isFirstLogin;
}

@Override
public int hashCode() {
	return Objects.hash(department, email, isFirstLogin, memberId, name, password, role, tickets);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Member other = (Member) obj;
	return Objects.equals(department, other.department) && Objects.equals(email, other.email)
			&& Objects.equals(isFirstLogin, other.isFirstLogin) && Objects.equals(memberId, other.memberId)
			&& Objects.equals(name, other.name) && Objects.equals(password, other.password) && role == other.role
			&& Objects.equals(tickets, other.tickets);
}

public Member(Integer memberId, @NotEmpty String name, @NotEmpty @Email String email, @NotEmpty String password,
		Boolean isFirstLogin, Role role, Department department, List<Ticket> tickets) {
	super();
	this.memberId = memberId;
	this.name = name;
	this.email = email;
	this.password = password;
	this.isFirstLogin = isFirstLogin;
	this.role = role;
	this.department = department;
	this.tickets = tickets;
}

}
