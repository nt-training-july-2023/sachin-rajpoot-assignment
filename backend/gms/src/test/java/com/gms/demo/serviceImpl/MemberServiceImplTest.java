package com.gms.demo.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.gms.demo.entity.Department;
import com.gms.demo.entity.Member;
import com.gms.demo.entity.Role;
import com.gms.demo.payloads.ApiResponse;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.MemberChangePasswordDto;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberGetAllOutDto;
import com.gms.demo.payloads.MemberLoginDto;
import com.gms.demo.payloads.MemberOutDto;
import com.gms.demo.repo.DepartmentRepo;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.service.DepartmentService;
import com.gms.demo.serviceimpl.MemberServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class MemberServiceImplTest {

	@Mock
	private MemberRepo memberRepo;

	@Mock
	private DepartmentService departmentService;

	@Mock
	private DepartmentRepo departmentRepo;

	@InjectMocks
	private MemberServiceImpl memberService = new MemberServiceImpl();

	@Mock
	private ModelMapper modelMapper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	Member member = new Member();
	List<Member> members = new ArrayList<>();
	MemberDto memberDto = new MemberDto();
	MemberOutDto memberOutDto = new MemberOutDto();
	MemberLoginDto memberLoginDto = new MemberLoginDto();
	MemberChangePasswordDto changePasswordDto = new MemberChangePasswordDto();
	List<MemberGetAllOutDto> allOutDto = new ArrayList<>();
	MemberGetAllOutDto getAllOutDto = new MemberGetAllOutDto();
	Integer pageNumber = 0;
	Pageable pageable = PageRequest.of(pageNumber, 6, Sort.by("name"));
	Page<Member> memberPages = new PageImpl<>(members, pageable, members.size());
	Department department = new Department();

	String email = "admin@nucleusteq.com";
	String password = "adminPassword";

	@Test
	public void testLoginValidCredentials() {
		memberLoginDto.setPassword(password);
		memberLoginDto.setEmail(email);
		member.setEmail(email);
		member.setPassword(password);
		when(memberRepo.findByEmail(email)).thenReturn(member);
		when(this.modelMapper.map(member, MemberOutDto.class)).thenReturn(memberOutDto);
		assertNotNull(memberService.login(memberLoginDto));
	}

	@Test
	public void testLoginInvalidCredentials() {
		MemberLoginDto loginDto = new MemberLoginDto("invalidEmail", "invalidPassword");
		when(memberRepo.findByEmail("invalidEmail")).thenReturn(null);

		assertNull(memberService.login(loginDto));
	}

	@Test
	public void testVerifyEmailAndPasswordValid() {
		assertTrue(memberService.verifyEmailAndPassword("user@nucleusteq.com", "validPassword"));
	}

	@Test
	public void testVerifyEmailAndPasswordInvalidEmail() {
		assertFalse(memberService.verifyEmailAndPassword("invalid_email", "validPassword"));
	}

	@Test
	public void testVerifyEmailAndPasswordInvalidPassword() {
		assertFalse(memberService.verifyEmailAndPassword("user@example.com", "pass"));
	}

	@Test
	public void testCreateMember3ValidAdminCredentials() {
		Integer DeptId = 1;
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentId(DeptId); 
		departmentDto.setDepartmentName("HR");
		
		Department department = new Department();
		department.setDepartmentId(DeptId);
		department.setDepartmentName("HR");
		
		MemberDto memberDto = new MemberDto();
		memberDto.setName("name");
		memberDto.setPassword(password);
		memberDto.setEmail(email);
		memberDto.setRole(Role.USER);
		memberDto.setDepartment(departmentDto);
		
		Member existingMember = new Member();
		existingMember.setEmail(email);
		existingMember.setPassword(password);
		existingMember.setRole(Role.ADMIN);
		department.setDepartmentId(DeptId);
		existingMember.setDepartment(department);
		
		Mockito.when(memberRepo.findByEmail(email)).thenReturn(existingMember);
		Mockito.when(this.departmentRepo.findById(memberDto.getDepartment().getDepartmentId()))
				.thenReturn(Optional.of(department));
		Member mappedMember = new Member();
		Mockito.when(this.modelMapper.map(memberDto, Member.class)).thenReturn(mappedMember);
		Member savedMember = new Member();
		Mockito.when(memberRepo.save(mappedMember)).thenReturn(savedMember);
		MemberOutDto mappedMemberOutDto = new MemberOutDto();
		ApiResponse result = memberService.createMember3(memberDto, email, password);
		assertTrue(result.isSuccess());
	}

	@Test
	public void testCreateMemberInvalidData1() {
		MemberDto memberDto = new MemberDto();
		memberDto.setName("");
		ApiResponse result = memberService.createMember3(memberDto, email, password);
		assertFalse(result.isSuccess());
	}
	
	@Test
	public void testCreateMemberInvalidData2() {
		MemberDto memberDto = new MemberDto();
		memberDto.setName("hh");
		memberDto.setPassword("         ");
		ApiResponse result = memberService.createMember3(memberDto, email, password);
		assertFalse(result.isSuccess());
	}
	
	@Test
	public void testCreateMemberInvalidData3() {
		MemberDto memberDto = new MemberDto();
		memberDto.setName("hh");
		memberDto.setPassword("         ");
		memberDto.setDepartment(null);
		ApiResponse result = memberService.createMember3(memberDto, email, password);
		assertFalse(result.isSuccess());
	}
	
	@Test
	public void testCreateMemberInvalidData4() {
		MemberDto memberDto = new MemberDto();
		memberDto.setName("hh");
		memberDto.setPassword("         ");
		DepartmentDto departmentDto = new DepartmentDto();
		memberDto.setDepartment(departmentDto);
		ApiResponse result = memberService.createMember3(memberDto, email, password);
		assertFalse(result.isSuccess());
	}

	@Test
	public void testGetAllMemberAuth() {
		String email = "test@nucleusteq.com";
		String password = "password";
		Integer pageNumber = 0;
		Pageable pageable = PageRequest.of(pageNumber, 6, Sort.by("name"));
		List<Member> memberEntities = new ArrayList<>();
		Page<Member> memberPage = new PageImpl<>(memberEntities, pageable, memberEntities.size());
		Mockito.when(this.memberRepo.findAll(PageRequest.of(pageNumber, 10, Sort.by("name")))).thenReturn(memberPage);
		List<MemberGetAllOutDto> memberOutDtos = new ArrayList<>();
//      Mockito.when(this.modelMapper.map(member, MemberGetAllOutDto.class))
//              .thenReturn(getAllOutDto); 
		List<MemberGetAllOutDto> result = memberService.getAllMemberAuth(email, password, pageNumber);
		assertEquals(memberOutDtos.size(), result.size());
	}

	@Test
	public void changePassword_Test() {
		Integer memberId = 1;
		MemberChangePasswordDto changePasswordDto = new MemberChangePasswordDto();
		changePasswordDto.setOldpassword("oldPassword");
		changePasswordDto.setNewPassword("newPassword");
		Member existingMember = new Member();
		existingMember.setMemberId(memberId);
		existingMember.setPassword("oldPassword");
		Mockito.when(memberRepo.findById(memberId)).thenReturn(Optional.of(existingMember));
		Member updatedMember = new Member();
		updatedMember.setMemberId(memberId);
		updatedMember.setPassword("newPassword");
		Mockito.when(memberRepo.save(existingMember)).thenReturn(updatedMember);
		MemberOutDto mappedMemberOutDto = new MemberOutDto();
		Mockito.when(this.modelMapper.map(updatedMember, MemberOutDto.class)).thenReturn(mappedMemberOutDto);
		MemberOutDto result = memberService.changePassword(memberId, changePasswordDto);
		assertEquals(mappedMemberOutDto, result);
	}

	@Test
	public void changePasswordFail_Test() {

		Integer memberId = 1;

		Member member = new Member();
		member.setEmail("user@nucleusteq.com");
		member.setPassword("222222222222");
		member.setIsFirstLogin(true);
		member.setName("test name");
		member.setMemberId(1);
		member.setRole(Role.USER);

		MemberOutDto memberOutDto = new MemberOutDto();
		memberOutDto.setEmail(member.getEmail());
		memberOutDto.setName(member.getName());
		memberOutDto.setRole(member.getRole());
		memberOutDto.setDepartmentName(null);
		memberOutDto.setDepartmentName(null);
		memberOutDto.setIsFirstLogin(member.getIsFirstLogin());

		when(this.memberRepo.findById(memberId)).thenReturn(Optional.of(member));

		MemberOutDto memberOutDto2 = this.memberService.changePassword(memberId, changePasswordDto);
		assertNotEquals(memberOutDto2, memberOutDto);

	}

	@Test
	public void testDeleteExistingMember() {
		Integer memberId = 1;
		member.setMemberId(memberId);
		when(memberRepo.findById(memberId)).thenReturn(Optional.of(member));
		ApiResponse response = memberService.deleteMember(memberId);
		verify(memberRepo, times(1)).deleteById(memberId);
		assertNotNull(response);
		assertTrue(response.isSuccess());
		assertEquals("Member with member ID : " + memberId + " is deleted successfully", response.getMessage());
	}

}
