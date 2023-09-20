package com.gms.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.service.DepartmentService;

@RestController
@RequestMapping("/api/department/")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	@CrossOrigin
	@PostMapping("create")
	ResponseEntity<DepartmentDto> createDepartment(@RequestBody @Valid DepartmentDto departmentDto){
		return new ResponseEntity<>(this.departmentService.createDepartment(departmentDto),HttpStatus.CREATED);
	}
}
