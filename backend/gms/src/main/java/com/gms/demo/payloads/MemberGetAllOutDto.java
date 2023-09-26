package com.gms.demo.payloads;

import com.gms.demo.entity.Role;
import java.util.Objects;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class MemberGetAllOutDto {

  /**
   * The unique identifier for the member.
   */
  private Integer memberId;

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

  public final Integer getMemberId() {
    return memberId;
  }

  public final void setMemberId(final Integer memberId) {
    this.memberId = memberId;
  }

  public final String getName() {
    return name;
  }

  public final void setName(final String name) {
    this.name = name;
  }

  public final String getEmail() {
    return email;
  }

  public final void setEmail(final String email) {
    this.email = email;
  }

  public final Role getRole() {
    return role;
  }

  public final void setRole(final Role role) {
    this.role = role;
  }

  public final String getDepartmentName() {
    return departmentName;
  }

  public final void setDepartmentName(final String departmentName) {
    this.departmentName = departmentName;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(departmentName, email, memberId, name, role);
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
    MemberGetAllOutDto other = (MemberGetAllOutDto) obj;
    return (
      Objects.equals(departmentName, other.departmentName) &&
      Objects.equals(email, other.email) &&
      Objects.equals(memberId, other.memberId) &&
      Objects.equals(name, other.name) &&
      role == other.role
    );
  }

  @Override
  public final String toString() {
    return (
      "MemberGetAllOutDto [memberId=" +
      memberId +
      ", name=" +
      name +
      ", email=" +
      email +
      ", role=" +
      role +
      ", departmentName=" +
      departmentName +
      "]"
    );
  }

  public MemberGetAllOutDto(
    final Integer memberId,
    final String name,
    final String email,
    final Role role,
    final String departmentName
  ) {
    super();
    this.memberId = memberId;
    this.name = name;
    this.email = email;
    this.role = role;
    this.departmentName = departmentName;
  }

  public MemberGetAllOutDto() {
    super();
    // TODO Auto-generated constructor stub
  }
}
