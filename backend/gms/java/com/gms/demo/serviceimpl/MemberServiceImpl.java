package com.gms.demo.serviceimpl;

import com.gms.demo.entity.Department;
import com.gms.demo.entity.Member;
import com.gms.demo.payloads.ApiResponse;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberLoginDto;
import com.gms.demo.repo.DepartmentRepo;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.service.DepartmentService;
import com.gms.demo.service.MemberService;

import com.gms.demo.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Represents a service implementation for Member-related operations. Implements
 * methods for creating members and performing login. Uses ModelMapper for
 * mapping entities to DTOs. This class is marked as a Spring service.
 * Responsible for interacting with the Member repository.
 *
 * @author Sachin Singh Rajpoot
 * @version 1.0
 * @since 28-08-2023
 */
@Service
public class MemberServiceImpl implements MemberService {
	/** The ModelMapper instance. */
	@Autowired
	private ModelMapper mapper;

	/** The MemberRepo instance. */
	@Autowired
	private MemberRepo memberRepo;
	
	@Autowired
	DepartmentRepo departmentRepo;
	
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	ModelMapper modelMapper;

	/**
	 * The minimum sixe for password.
	 */
	private static final int MIN_SIZE_PASSWORD = 5;

	/**
	 * Perform member login.
	 *
	 * @param memberLoginDto The MemberLoginDto containing login credentials.
	 * @return True if login is successful, false otherwise.
	 * @throws Exception
	 */
	@Override
	public Boolean login(final MemberLoginDto memberLoginDto)  {

		Member member = this.memberRepo.findByEmail(memberLoginDto.getEmail());
		if (member != null) {
			if (member.getPassword().equals(memberLoginDto.getPassword())) {
				System.out.println(memberLoginDto);
				return true;
			}
		}

		return false;
	}

	/**
	 * Create a new member.
	 *
	 * @param memberDto The MemberDto containing member details.
	 * @return The created MemberDto.
	 */
	@Override
	public MemberDto createMember(final MemberDto memberDto,final Integer departmentId) {
		
		DepartmentDto departmentDto = this.departmentService.getDepartmentById(departmentId);
//				.orElseThrow(()->new ResourceNotFoundException("Department","Department ID" , departmentId));
		Department department = this.modelMapper.map(departmentDto, Department.class);
		
		memberDto.setDepartment(this.modelMapper.map(department, DepartmentDto.class));
		Member member = this.mapper.map(memberDto, Member.class);
		return this.mapper.map(this.memberRepo.save(member), MemberDto.class);
	}

	/**
	 * Verify valid email and password.
	 *
	 * @param memberLoginDto The MemberLoginDto containing email and password.
	 * @return True if valid email and password.
	 * @throws Exception
	 */
	@Override
	public Boolean verifyEmailAndPassword(final String email, final String password) {
		String emailPattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

		boolean isValidEmail = email.matches(emailPattern);
		boolean isValidEmail2 = email.endsWith("@nucleusteq.com");
		boolean isValidPassword = password.length() >= MIN_SIZE_PASSWORD ? true : false;

		if (isValidEmail && isValidPassword && isValidEmail2) {
			return true;

		}
		return false;
	}
}
