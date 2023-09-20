package com.gms.demo.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gms.demo.entity.Role;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Represents an Member Login DTO.
 *
 * @author Sachin Singh Rajpoot
 *
 * @version 1.0
 * @since Begining of time
 */

public class MemberDto {

  /**
   * The unique identifier for the member.
   */
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
  @Email(message = "Email is not valid.")
  @Column(unique = true)
  private String email;

  /**
   * The minimum sixe for password.
   */
  private static final int MIN_SIZE_PASSWORD = 5;

  /**
   * The password associated with the member's account. Password must be between 5
   * and 12 characters in length.
   */
  @NotEmpty
  @Size(
    min = MIN_SIZE_PASSWORD,
    message = "PASSWORD MUST BE MINIMUM 5 CHARACTER."
  )
  private String password;

  /**
   * The Check for the first login of the member.
   */
  //  @NotEmpty
  private Boolean isFirstLogin;

  /**
   * The role of the member in the system.
   */
  // @NotEmpty
  @Enumerated(EnumType.STRING)
  private Role role;

  /**
   * The department associated with the member.
   */
//  @JsonIgnore
  private DepartmentDto department;

  /**
   * Gets the unique identifier for the member.
   *
   * @return The member's unique identifier.
   */
  public Integer getMemberId() {
    return memberId;
  }

  /**
   * Sets the unique identifier for the member.
   *
   * @param memberId The member's unique identifier.
   */
  public void setMemberId(final Integer memberId) {
    this.memberId = memberId;
  }

  /**
   * Gets the name of the member.
   *
   * @return The name of the member.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the member.
   *
   * @param name The name of the member.
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Gets the email of the member.
   *
   * @return The email of the member.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email of the member.
   *
   * @param email The email of the member.
   */
  public void setEmail(final String email) {
    this.email = email;
  }

  /**
   * Gets the password of the member.
   *
   * @return The password of the member.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password of the member.
   *
   * @param password The password of the member.
   */
  public void setPassword(final String password) {
    this.password = password;
  }

  /**
   * Gets whether the member is logging in for the first time.
   *
   * @return `true` if it's the member's first login; otherwise, `false`.
   */
  public Boolean getIsFirstLogin() {
    return isFirstLogin;
  }

  /**
   * Sets whether the member is logging in for the first time.
   *
   * @param isFirstLogin `true` if it's the member's first login; otherwise, `false`.
   */
  public void setIsFirstLogin(final Boolean isFirstLogin) {
    this.isFirstLogin = isFirstLogin;
  }

  /**
   * Gets the role of the member.
   *
   * @return The role of the member.
   */
  public Role getRole() {
    return role;
  }

  /**
   * Sets the role of the member.
   *
   * @param role The role of the member.
   */
  public void setRole(final Role role) {
    this.role = role;
  }

  /**
   * Gets the department DTO associated with the member.
   *
   * @return The department DTO associated with the member.
   */
  public DepartmentDto getDepartment() {
    return department;
  }

  /**
   * Sets the department DTO associated with the member.
   *
   * @param departmentDto The department DTO associated with the member.
   */
  public void setDepartment(final DepartmentDto department) {
    this.department = department;
  }

  /**
   * Returns a string representation of the MemberDto object.
   *
   * @return A string containing member details.
   */
  @Override
  public String toString() {
    return (
      "MemberDto [memberId=" +
      memberId +
      ", name=" +
      name +
      ", email=" +
      email +
      ", password=" +
      password +
      ", isFirstLogin=" +
      isFirstLogin +
      ", role=" +
      role +
      "]"
    );
  }

  /**
   * Constructs a MemberDto object with the specified member details.
   *
   * @param memberId      The unique identifier for the member.
   * @param name          The name of the member.
   * @param email         The email of the member.
   * @param password      The password of the member.
   * @param isFirstLogin  Indicates if the member is logging in for the first time.
   * @param role          The role of the member.
   * @param departmentDto The department DTO associated with the member.
   */
  public MemberDto(
    final Integer memberId,
    @NotEmpty final String name,
    @NotEmpty @Email(message = "Email is not valid.") final String email,
    @NotEmpty @Size(
      min = MIN_SIZE_PASSWORD,
      message = "PASSWORD MUST BE MINIMUM " + MIN_SIZE_PASSWORD + " CHARACTERS."
    ) final String password,
    final Boolean isFirstLogin,
    final Role role,
    final DepartmentDto department
  ) {
    super();
    this.memberId = memberId;
    this.name = name;
    this.email = email;
    this.password = password;
    this.isFirstLogin = isFirstLogin;
    this.role = role;
    this.department = department;
  }

  /**
   * Default constructor for the MemberDto class.
   */
  public MemberDto() {
    // Default constructor
  }
}
