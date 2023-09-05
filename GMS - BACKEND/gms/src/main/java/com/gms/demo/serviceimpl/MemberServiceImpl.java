package com.gms.demo.serviceimpl;

import com.gms.demo.entity.Member;
import com.gms.demo.entity.Role;
import com.gms.demo.payloads.ApiResponse;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberLoginDto;
import com.gms.demo.payloads.MemberOutDto;
import com.gms.demo.repo.DepartmentRepo;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.service.DepartmentService;
import com.gms.demo.service.MemberService;

import java.util.ArrayList;
import java.util.List;

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
	public Boolean login(final MemberLoginDto memberLoginDto) {

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
	public MemberDto createMember(final MemberDto memberDto, final Integer departmentId) {
		DepartmentDto departmentDto = this.departmentService.getDepartmentById(departmentId);
		memberDto.setDepartment(departmentDto);
//		memberDto.setDepartment();
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

	@Override
	public List<MemberOutDto> getAllmember() {
		
		// GET LIST IF ROLE IS USER -> MEMBER OF SAME DEPT
		// GET LIST OF ALL MEMBER IF ROLE IS ADMIN
		
		List<MemberOutDto> memberOutDtos = new ArrayList<>();
		List<Member> members = this.memberRepo.findAll();
		members.forEach(member -> memberOutDtos.add(new MemberOutDto(member.getName(), member.getEmail(),
				member.getRole(), member.getDepartment().getName())));

		return memberOutDtos;
	}

	@Override
	public MemberOutDto createMember2(MemberDto memberDto, Integer departmentId, String email, String password) {

		System.out.println("inside service");
		Member member = this.memberRepo.findByEmail(email);
		if (member != null) {
			System.out.println("inside 1");
			System.out.println("Role " + member.getRole());
			System.out.println(member.getRole().equals(Role.ADMIN));
			if (member.getPassword().equals(password) && member.getRole() != null
					&& member.getRole().equals(Role.ADMIN)) {
				System.out.println("Hail Admin!!!!!!!!!");
				System.out.println("This is what you've created" + memberDto);

				DepartmentDto departmentDto = this.departmentService.getDepartmentById(departmentId);
				memberDto.setDepartment(departmentDto);

//				Member member3 = this.mapper.map(memberDto, Member.class);

				Member member2 = this.mapper.map(memberDto, Member.class);
			Member savedMember =	this.memberRepo.save(member2);
//				return this.mapper.map(, MemberDto.class);
				return new MemberOutDto(savedMember.getName(), savedMember.getEmail(),
						savedMember.getRole(), savedMember.getDepartment().getName());

			}
		}
		return null;

	}
}
