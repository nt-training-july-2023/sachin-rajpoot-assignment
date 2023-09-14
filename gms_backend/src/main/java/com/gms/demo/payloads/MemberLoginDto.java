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
   * The password associated with the member's account. Password must be between 5
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
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email of the member for login.
   *
   * @param email The email to set for member login.
   */
  public void setEmail(final String email) {
    this.email = email;
  }

  /**
   * Gets the password of the member for login.
   *
   * @return The password used for member login.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password of the member for login.
   *
   * @param password The password to set for member login.
   */
  public void setPassword(final String password) {
    this.password = password;
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
  public String toString() {
    return "MemberLoginDto [email=" + email + ", password=" + password + "]";
  }

  /**
   * Constructs a MemberLoginDto object with the specified email and password.
   *
   * @param email    The email for member login.
   * @param password The password for member login.
   */
  public MemberLoginDto(
    @NotEmpty @Email final String email,
    @NotEmpty @Size(
      min = MIN_SIZE_PASSWORD,
      message = "Password must be between " +
      MIN_SIZE_PASSWORD +
      " and 12 characters."
    ) final String password
  ) {
    super();
    this.email = email;
    this.password = password;
  }

  /**
   * Default constructor for creating a MemberLoginDto object.
   */
  public MemberLoginDto() {
    super();
  }
}
