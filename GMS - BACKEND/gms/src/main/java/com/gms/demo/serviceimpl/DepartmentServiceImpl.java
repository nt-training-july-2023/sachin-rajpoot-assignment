package com.gms.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.gms.demo.entity.Department;
import com.gms.demo.entity.Member;
import com.gms.demo.entity.Role;
import com.gms.demo.entity.Ticket;
import com.gms.demo.exception.ResourceNotFoundException;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.TicketDto;
import com.gms.demo.repo.DepartmentRepo;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	DepartmentRepo departmentRepo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	MemberRepo memberRepo;

	@Override
	public DepartmentDto createDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
		Department department = this.modelMapper.map(departmentDto, Department.class);
		return this.modelMapper.map(this.departmentRepo.save(department), DepartmentDto.class);
	}

	@Override
	public List<DepartmentDto> getAllDepartment() {
		List<Department> departments = this.departmentRepo.findAll();
		List<DepartmentDto> departmentDtos = new ArrayList<>();
		departments.forEach(department -> departmentDtos.add(this.modelMapper.map(department, DepartmentDto.class)));
		return departmentDtos;
	}

	@Override
	public DepartmentDto getDepartmentById(Integer departmentId) {
		Department department = this.departmentRepo.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "departmentId", departmentId));
		return this.modelMapper.map(department, DepartmentDto.class);
	}

	@Override
	public DepartmentDto createDepartment2(DepartmentDto departmentDto, String email, String password) {
		System.out.println("inside service");
		Member member = this.memberRepo.findByEmail(email);
		if (member != null) {
			System.out.println("inside 1");
			System.out.println("Role " + member.getRole());
			System.out.println(member.getRole().equals(Role.ADMIN));
			if (member.getPassword().equals(password) && member.getRole() != null
					&& member.getRole().equals(Role.ADMIN)) {
				System.out.println("Hail Admin!!!!!!!!!");
				System.out.println("This is what you've created" + departmentDto);

				Department department = this.modelMapper.map(departmentDto, Department.class);
				return this.modelMapper.map(this.departmentRepo.save(department), DepartmentDto.class);

			}

		}
		return null;
	}

}
