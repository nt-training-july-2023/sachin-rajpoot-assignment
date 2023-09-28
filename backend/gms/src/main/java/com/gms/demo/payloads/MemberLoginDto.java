package com.gms.demo.payloads;

import javax.persistence.Column;
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

public class MemberLoginDto {

  /**
   * The email address of the member.
   */
  @NotEmpty
  @Email
  @Column(unique = true)
  private String email;

  /**
   * The minimum size of password for the member.
   */
  private static final int MIN_SIZE_PASSWORD = 5;

  /**
   * The password associated with the member's account.
   * Password must be between 5
   * and 12 characters in length.
   */
  @NotEmpty
  @Size(
      min = MIN_SIZE_PASSWORD,
      message = "Password must be between 5 and 12 characters."
  )
  private String password;

  /**
   * Gets the email of the member for login.
   *
   * @return The email used for member login.
   */
  public final String getEmail() {
    return email;
  }

  /**
   * Sets the email of the member for login.
   *
   * @param emailx The email to set for member login.
   */
  public final void setEmail(final String emailx) {
    this.email = emailx;
  }

  /**
   * Gets the password of the member for login.
   *
   * @return The password used for member login.
   */
  public final String getPassword() {
    return password;
  }

  /**
   * Sets the password of the member for login.
   *
   * @param passwordx The password to set for member login.
   */
  public final void setPassword(final String passwordx) {
    this.password = passwordx;
  }

  /**
   * Gets the minimum size required for a password.
   *
   * @return The minimum size required for a password.
   */
  public static int getMinSizePassword() {
    return MIN_SIZE_PASSWORD;
  }

  /**
   * Returns a string representation of the MemberLoginDto object.
   *
   * @return A string containing member login details.
   */
  @Override
  public final String toString() {
    return "MemberLoginDto [email=" + email + ", password=" + password + "]";
  }

  /**
   * Constructs a MemberLoginDto object with the specified email and password.
   *
   * @param emailx    The email for member login.
   * @param passwordx The password for member login.
   */
  public MemberLoginDto(
      @NotEmpty @Email final String emailx,
      @NotEmpty @Size(
      min = MIN_SIZE_PASSWORD,
      message = "Password must be between "
      +
      MIN_SIZE_PASSWORD
      +
      " and 12 characters."
    ) final String passwordx
  ) {
    super();
    this.email = emailx;
    this.password = passwordx;
  }

  /**
   * Default constructor for creating a MemberLoginDto object.
   */
  public MemberLoginDto() {
    super();
  }
}
