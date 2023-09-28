package com.gms.demo.payloads;

import java.util.Objects;
import javax.validation.constraints.NotEmpty;

/**
 * A DTO (Data Transfer Object) class representing
 * a member's change password request.
 * It contains the old password and the new password fields.
 */
public class MemberChangePasswordDto {

  /**
 * The old password for authentication purposes.
 */
  @NotEmpty
  private String oldpassword;

  /**
   * The new password that the member wants to set.
   */
  @NotEmpty
  private String newPassword;

  /**
   * Get the old password.
   *
   * @return The old password.
   */
  public final String getOldpassword() {
    return oldpassword;
  }


  /**
   * Set the old password.
   *
   * @param oldpasswordx The old password to set.
   */
  public final void setOldpassword(final String oldpasswordx) {
    this.oldpassword = oldpasswordx;
  }

  /**
   * Get the NewPassword.
   *
   * @return The NewPassword.
   */
  public final String getNewPassword() {
    return newPassword;
  }

  /**
   * Set the NewPassword.
   *
   *@param newPasswordx new Password
   */
  public final void setNewPassword(final String newPasswordx) {
    this.newPassword = newPasswordx;
  }

  /**
   * Generate a hash code for this object based on its properties.
   *
   * @return The hash code for this object.
   */
  @Override
  public final int hashCode() {
    return Objects.hash(newPassword, oldpassword);
  }

  /**
   * Compare this object to another object for equality.
   *
   * @param obj The object to compare with.
   * @return True if the objects are equal, false otherwise.
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
    MemberChangePasswordDto other = (MemberChangePasswordDto) obj;
    return (
      Objects.equals(newPassword, other.newPassword)
      &&
      Objects.equals(oldpassword, other.oldpassword)
      );
  }


  /**
   * Create a new instance of MemberChangePasswordDto with
   * the specified old and new passwords.
   *
   * @param oldpasswordx The old password.
   * @param newPasswordx The new password.
   */
  public MemberChangePasswordDto(
      final String oldpasswordx,
      final String newPasswordx
  ) {
    super();
    this.oldpassword = oldpasswordx;
    this.newPassword = newPasswordx;
  }

  /**
   * Create a new instance of MemberChangePasswordDto with default values.
   */
  public MemberChangePasswordDto() {
    super();
  }
}
