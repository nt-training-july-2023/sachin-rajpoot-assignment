package com.gms.demo.entity;

import java.util.ArrayList;
import java.util.List;
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
import javax.validation.constraints.NotNull;

/**
 * Represents an Member Entity.
 *
 * @author Sachin Singh Rajpoot
 *
 * @version 1.0
 * @since 28-08-2023
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
  @NotNull
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
  @OneToMany(
      mappedBy = "member",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY
  )
  private List<Ticket> tickets = new ArrayList<>();

  /**
   * Gets the unique identifier for the member.
   *
   * @return The member's unique identifier.
   */
  public final Integer getMemberId() {
    return memberId;
  }

  /**
   * Sets the unique identifier for the member.
   *
   * @param memberIdx The member's unique identifier.
   */
  public final void setMemberId(final Integer memberIdx) {
    this.memberId = memberIdx;
  }

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
   * @param namex The name of the member.
   */
  public final void setName(final String namex) {
    this.name = namex;
  }

  /**
   * Gets the email address of the member.
   *
   * @return The email address of the member.
   */
  public final String getEmail() {
    return email;
  }

  /**
   * Sets the email address of the member.
   *
   * @param emailx The email address of the member.
   */
  public final void setEmail(final String emailx) {
    this.email = emailx;
  }

  /**
   * Gets the password of the member.
   *
   * @return The password of the member.
   */
  public final String getPassword() {
    return password;
  }

  /**
   * Sets the password of the member.
   *
   * @param passwordx The password of the member.
   */
  public final void setPassword(final String passwordx) {
    this.password = passwordx;
  }

  /**
   * Gets whether the member is logging in for the first time.
   *
   * @return True if the member is logging in for the first time
   *     false otherwise.
   */
  public final Boolean getIsFirstLogin() {
    return isFirstLogin;
  }

  /**
   * Sets whether the member is logging in for the first time.
   *
   * @param isFirstLoginx True if the member is logging in
   *     for the first time; false otherwise.
   */
  public final void setIsFirstLogin(final Boolean isFirstLoginx) {
    this.isFirstLogin = isFirstLoginx;
  }

  /**
   * Gets the role of the member.
   *
   * @return The role of the member.
   */
  public final Role getRole() {
    return role;
  }

  /**
   * Sets the role of the member.
   *
   * @param rolex The role of the member.
   */
  public final void setRole(final Role rolex) {
    this.role = rolex;
  }

  /**
   * Gets the department to which the member belongs.
   *
   * @return The department to which the member belongs.
   */
  public final Department getDepartment() {
    return department;
  }

  /**
   * Sets the department to which the member belongs.
   *
   * @param departmentx The department to which the member belongs.
   */
  public final void setDepartment(final Department departmentx) {
    this.department = departmentx;
  }

  /**
   * Gets the list of tickets associated with the member.
   *
   * @return The list of tickets associated with the member.
   */
  public final List<Ticket> getTickets() {
    return tickets;
  }

  /**
   * Sets the list of tickets associated with the member.
   *
   * @param ticketsx The list of tickets associated with the member.
   */
  public final void setTickets(final List<Ticket> ticketsx) {
    this.tickets = ticketsx;
  }

  /**
   * Returns a string representation of the Member object.
   *
   * @return A string containing member details.
   */
  @Override
  public final String toString() {
    return
      "Member [memberId="
      +
      memberId
      +
      ", name="
      +
      name
      +
      ", email="
      +
      email
      +
      ", password="
      +
      password
      +
      ", isFirstLogin="
      +
      isFirstLogin
      +
      ", role="
      +
      role
      +
      "]";
  }

  /**
   * Default constructor for Member class.
   */
  public Member() {
    super();
  }
}
