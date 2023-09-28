package com.gms.demo.payloads;

import com.gms.demo.entity.Role;
import java.util.Objects;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
/**
 * The MemberGetAllOutDto for tranfer of all member datas.
 */

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

  /**
   * gets the member ID.
   *
   *@return memberId member Id
   */
  public final Integer getMemberId() {
    return memberId;
  }

  /**
   * sets ID of the member.
   *
   *@param memberIdx member Id
   */
  public final void setMemberId(final Integer memberIdx) {
    this.memberId = memberIdx;
  }

  /**
   * gets name of the member.
   *
   * @return name name
   */
  public final String getName() {
    return name;
  }

  /**
   * sets name of the member.
   *
   * @param namex name
   */
  public final void setName(final String namex) {
    this.name = namex;
  }


  /**
   * gets email of the member.
   *
   * @return email email
   */
  public final String getEmail() {
    return email;
  }

  /**
   * sets email of the member.
   *
   * @param emailx email
   */
  public final void setEmail(final String emailx) {
    this.email = emailx;
  }

  /**
   * gets Role of the member.
   *
   * @return role role
   */
  public final Role getRole() {
    return role;
  }

  /**
   * sets Role of the member.
   *
   *@param rolex role
   */
  public final void setRole(final Role rolex) {
    this.role = rolex;
  }

  /**
   * gets department name of the member.
   *
   *@return departmentName department Name
   */
  public final String getDepartmentName() {
    return departmentName;
  }

  /**
   * sets department name of the member.
   *
   *@param departmentNamex department Name
   */
  public final void setDepartmentName(final String departmentNamex) {
    this.departmentName = departmentNamex;
  }

  /**
   *hashcode of the member.
   */
  @Override
  public final int hashCode() {
    return Objects.hash(departmentName, email,
      memberId, name, role);
  }

  /**
   * compares the object.
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
    MemberGetAllOutDto other = (MemberGetAllOutDto) obj;
    return (
      Objects.equals(departmentName, other.departmentName)
      &&
      Objects.equals(email, other.email)
      &&
      Objects.equals(memberId, other.memberId)
      &&
      Objects.equals(name, other.name)
      &&
      role == other.role
      );
  }

  /**
   * To string of the member.
   *
   */

  @Override
  public final String toString() {
    return (
      "MemberGetAllOutDto [memberId="
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
      ", role="
      +
      role
      +
      ", departmentName="
      +
      departmentName
      +
      "]"
      );
  }

  /**
   * constructor  of the member.
   *
   * @param memberIdx memberId
   * @param namex name
   * @param emailx email
   * @param rolex role
   * @param departmentNamex department Name
   */
  public MemberGetAllOutDto(
      final Integer memberIdx,
      final String namex,
      final String emailx,
      final Role rolex,
      final String departmentNamex
  ) {
    super();
    this.memberId = memberIdx;
    this.name = namex;
    this.email = emailx;
    this.role = rolex;
    this.departmentName = departmentNamex;
  }

  /**
   * default constructor  of the member.
   */
  public MemberGetAllOutDto() {
    super();
  }
}
