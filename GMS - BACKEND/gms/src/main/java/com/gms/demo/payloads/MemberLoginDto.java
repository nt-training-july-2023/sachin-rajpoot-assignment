package com.gms.demo.payloads;

import java.util.Objects;
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
   * Password must be between 5 and 12 characters in length.
   */
  @NotEmpty
  @Size(min = MIN_SIZE_PASSWORD, message = "Password must be between 5 and 12 characters.")
  private String password;
  
  /**
   * The Check for the first login of the member.
   */
//  @NotEmpty
  private Boolean isFirstLogin;

  /**
   * Get the email of the member for login.
   *
   * @return The member's login email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Set the email of the member for login.
   *
   * @param email The member's login email to set.
   */
  public void setEmail(final String email) {
    this.email = email;
  }

  /**
   * Get the password of the member for login.
   *
   * @return The member's login password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Set the password of the member for login.
   *
   * @param password The member's login password to set.
   */
  public void setPassword(final String password) {
    this.password = password;
  }

  /**
   * Calculates the hash code for the MemberLoginDto object.
   *
   * @return The hash code value.
   */
  @Override
  public int hashCode() {
    return Objects.hash(email, password);
  }

  /**
   * Checks if this MemberLoginDto object is equal to another object.
   *
   * @param obj The object to compare.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    MemberLoginDto other = (MemberLoginDto) obj;
    return (Objects.equals(email, other.email) && Objects.equals(password, other.password));
  }

  /**
   * Generates a string representation of the MemberLoginDto object.
   *
   * @return The string representation.
   */
  @Override
  public String toString() {
    return ("email=" + email + ", password=" + password + "]");
  }

  /**
   * Constructor for creating a MemberLoginDto object.
   *
   * @param memberId The member ID.
   * @param email    The email of the member for login.
   * @param password The password of the member for login.
   */
  public MemberLoginDto(
      final Integer memberId,
      @NotEmpty @Email final String email,
      @NotEmpty @Size(min = '5', message = "Password Must be minimum 5 and maximum 12 characters.") final String password) {
    super();
    this.email = email;
    this.password = password;
  }

  /**
   * Constructs a new MemberLoginDto with the provided email and password.
   * @param email    The email address of the member.
   * @param password The password associated with the member's account.
   */
  public MemberLoginDto(
      @NotEmpty @Email final String email,
      @NotEmpty @Size(min = '5', message = "Password must be between 5 and 12 characters.") final String password) {
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
