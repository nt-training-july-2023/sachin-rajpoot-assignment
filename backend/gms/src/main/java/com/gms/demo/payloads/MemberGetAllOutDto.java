package com.gms.demo.payloads;

import java.util.List;
import java.util.Objects;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.gms.demo.entity.Role;

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

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(departmentName, email, memberId, name, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberGetAllOutDto other = (MemberGetAllOutDto) obj;
		return Objects.equals(departmentName, other.departmentName) && Objects.equals(email, other.email)
				&& Objects.equals(memberId, other.memberId) && Objects.equals(name, other.name) && role == other.role;
	}

	@Override
	public String toString() {
		return "MemberGetAllOutDto [memberId=" + memberId + ", name=" + name + ", email=" + email + ", role=" + role
				+ ", departmentName=" + departmentName + "]";
	}

	public MemberGetAllOutDto(Integer memberId, String name, String email, Role role, String departmentName) {
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
