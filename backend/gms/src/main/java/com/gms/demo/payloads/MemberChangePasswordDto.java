package com.gms.demo.payloads;

import java.util.Objects;
import javax.validation.constraints.NotEmpty;

public class MemberChangePasswordDto {

  @NotEmpty
  String oldpassword;

  @NotEmpty
  String newPassword;

  public final String getOldpassword() {
    return oldpassword;
  }

  public final void setOldpassword(final String oldpassword) {
    this.oldpassword = oldpassword;
  }

  public final String getNewPassword() {
    return newPassword;
  }

  public final void setNewPassword(final String newPassword) {
    this.newPassword = newPassword;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(newPassword, oldpassword);
  }

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
      Objects.equals(newPassword, other.newPassword) &&
      Objects.equals(oldpassword, other.oldpassword)
    );
  }



  public MemberChangePasswordDto(
    final String oldpassword,
    final String newPassword
  ) {
    super();
    this.oldpassword = oldpassword;
    this.newPassword = newPassword;
  }

  public MemberChangePasswordDto() {
    super();
    // TODO Auto-generated constructor stub
  }
}
