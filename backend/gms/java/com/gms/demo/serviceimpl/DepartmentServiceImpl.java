package com.gms.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.gms.demo.entity.Department;
import com.gms.demo.entity.Ticket;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.TicketDto;
import com.gms.demo.repo.DepartmentRepo;
import com.gms.demo.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	DepartmentRepo departmentRepo;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public DepartmentDto createDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
		Department department = this.modelMapper.map(departmentDto, Department.class);
		return this.modelMapper.map(this.departmentRepo.save(department), DepartmentDto.class);
	}

	@Override
	public List<DepartmentDto> getAllDepartment() {
		List<Department> departments = this.departmentRepo.findAll();
		List<DepartmentDto> departmentDtos = new ArrayList<>();
		departments.forEach( department -> departmentDtos.add(this.modelMapper.map(department, DepartmentDto.class)));
		return departmentDtos;
	}

}
