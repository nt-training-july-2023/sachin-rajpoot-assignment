package com.gms.demo.service;

import java.util.List;

import com.gms.demo.payloads.DepartmentDto;

public interface DepartmentService {

	DepartmentDto createDepartment(final DepartmentDto departmentDto);
	
	DepartmentDto createDepartment2(final DepartmentDto departmentDto, String email, String password);
	
	DepartmentDto getDepartmentById(final Integer departmentId);
	
	List<DepartmentDto> getAllDepartment();

//	DepartmentDto findById();
}
