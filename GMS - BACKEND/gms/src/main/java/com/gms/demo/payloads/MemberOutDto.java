package com.gms.demo.payloads;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.gms.demo.entity.Department;
import com.gms.demo.entity.Role;
import com.gms.demo.entity.Ticket;

public class MemberOutDto {

	
	  private String name;

	  private String email;
	  
	  @Enumerated(EnumType.STRING)
	  private Role role;

	  private String departmentName;

	public MemberOutDto(String name, String email, Role role, String departmentName) {
		super();
		this.name = name;
		this.email = email;
		this.role = role;
		this.departmentName = departmentName;
	}

	public MemberOutDto() {
		super();
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
		return Objects.hash(departmentName, email, name, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberOutDto other = (MemberOutDto) obj;
		return Objects.equals(departmentName, other.departmentName) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && role == other.role;
	}

	@Override
	public String toString() {
		return "MemberOutDto [name=" + name + ", email=" + email + ", role=" + role + ", departmentName="
				+ departmentName + "]";
	}
	
	

}
