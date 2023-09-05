package com.gms.demo.payloads;

import java.util.Objects;

public class DepartmentOutDto {
	 private String departmentName;
	 
//	 private Integer numberOfEmployees;
	 
//	 private String memberName;
	 
//	 private String memberEmail;
	 
	 private MemberOutDto memberOutDto;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public MemberOutDto getMemberOutDto() {
		return memberOutDto;
	}

	public void setMemberOutDto(MemberOutDto memberOutDto) {
		this.memberOutDto = memberOutDto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(departmentName, memberOutDto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepartmentOutDto other = (DepartmentOutDto) obj;
		return Objects.equals(departmentName, other.departmentName) && Objects.equals(memberOutDto, other.memberOutDto);
	}

	@Override
	public String toString() {
		return "DepartmentOutDto [departmentName=" + departmentName + ", memberOutDto=" + memberOutDto + "]";
	}

	public DepartmentOutDto(String departmentName, MemberOutDto memberOutDto) {
		super();
		this.departmentName = departmentName;
		this.memberOutDto = memberOutDto;
	}

	public DepartmentOutDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
	 
}
