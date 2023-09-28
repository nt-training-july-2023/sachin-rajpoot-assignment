package com.gms.demo.payloads;

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
   * The password associated with the member's account.
   * Password must be between 5.
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
   * @param emailx The email of the member.
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
   * @return `true` if it's the member's first login; otherwise, `false`.
   */
  public final Boolean getIsFirstLogin() {
    return isFirstLogin;
  }

  /**
   * Sets whether the member is logging in for the first time.
   *
   * @param isFirstLoginx `true` if it's the member's first login;
   *     otherwise, `false`.
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
   * Gets the department DTO associated with the member.
   *
   * @return The department DTO associated with the member.
   */
  public final DepartmentDto getDepartment() {
    return department;
  }

  /**
   * Sets the department DTO associated with the member.
   *
   *@param departmentx department
   */
  public final void setDepartment(final DepartmentDto departmentx) {
    this.department = departmentx;
  }

  /**
   * Returns a string representation of the MemberDto object.
   *
   * @return A string containing member details.
   */
  @Override
  public final String toString() {
    return (
      "MemberDto [memberId="
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
      "]"
      );
  }

  /**
   * Constructs a MemberDto object with the specified member details.
   *
   * @param memberIdx      The unique identifier for the member.
   * @param namex          The name of the member.
   * @param emailx         The email of the member.
   * @param passwordx      The password of the member.
   * @param isFirstLoginx  Indicates if the member is logging in
   *     for the first time.
   * @param rolex          The role of the member.
   * @param departmentx department
   */
  public MemberDto(
      final Integer memberIdx,
      @NotEmpty final String namex,
      @NotEmpty @Email(message = "Email is not valid.") final String emailx,
      @NotEmpty @Size(
      min = MIN_SIZE_PASSWORD,
      message = "PASSWORD MUST BE MINIMUM " + MIN_SIZE_PASSWORD + " CHARACTERS."
    ) final String passwordx,
      final Boolean isFirstLoginx,
      final Role rolex,
      final DepartmentDto departmentx
  ) {
    super();
    this.memberId = memberIdx;
    this.name = namex;
    this.email = emailx;
    this.password = passwordx;
    this.isFirstLogin = isFirstLoginx;
    this.role = rolex;
    this.department = departmentx;
  }

  /**
   * Default constructor for the MemberDto class.
   */
  public MemberDto() {
    super();
  }
}
