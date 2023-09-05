package com.gms.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.service.DepartmentService;

@RestController
@RequestMapping("/api/department/")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@CrossOrigin
	@PostMapping("create")
	ResponseEntity<DepartmentDto> createDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
		return new ResponseEntity<>(this.departmentService.createDepartment(departmentDto), HttpStatus.CREATED);
	}

	@CrossOrigin
	@PostMapping("create/email/{email}/password/{password}")
	ResponseEntity<DepartmentDto> createDepartment2(@RequestBody @Valid DepartmentDto departmentDto,
			@PathVariable String email, @PathVariable String password) {
		DepartmentDto departmentDto2 = this.departmentService.createDepartment2(departmentDto, email, password);
		if(departmentDto2 != null) {
			return new ResponseEntity<>(departmentDto2, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
	}

	@CrossOrigin
	@GetMapping("getAll")
	ResponseEntity<List<DepartmentDto>> getAllDepartment() {
		return new ResponseEntity<>(this.departmentService.getAllDepartment(), HttpStatus.OK);
	}

}
