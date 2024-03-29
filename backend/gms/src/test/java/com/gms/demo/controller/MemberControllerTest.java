package com.gms.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gms.demo.entity.Member;
import com.gms.demo.entity.Role;
import com.gms.demo.payloads.ApiResponse;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.MemberChangePasswordDto;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberGetAllOutDto;
import com.gms.demo.payloads.MemberLoginDto;
import com.gms.demo.payloads.MemberOutDto;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.service.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class MemberControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private MemberService memberService;

	@Mock
	private MemberRepo memberRepo;

	@InjectMocks
	MemberController memberController;

	@Before
	public void setup() {
		memberService = mock(MemberService.class);
		memberRepo = mock(MemberRepo.class);
		memberController = new MemberController();
		memberController.setMemberService(memberService);
		mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
	}

	MemberDto memberDto = new MemberDto();
	MemberOutDto memberOutDto = new MemberOutDto();
	MemberLoginDto memberLoginDto = new MemberLoginDto();
	MemberChangePasswordDto changePasswordDto = new MemberChangePasswordDto();
	List<MemberGetAllOutDto> allOutDto = new ArrayList<>();

	String email = "admin@nucleusteq.com";
	String password = "adminPassword";

	@Test
	public void testLogin_Success() throws Exception {
		memberLoginDto.setEmail("test@nucleusteq.com");
		memberLoginDto.setPassword("testPassword");
		when(memberService.login(memberLoginDto)).thenReturn(new MemberOutDto());
		ResponseEntity<?> responseEntity = memberController.login(memberLoginDto);
		System.out.println(responseEntity.getStatusCodeValue());
		System.out.println(responseEntity.getBody());
		mockMvc.perform(MockMvcRequestBuilders.post("/api/login").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(memberLoginDto)).accept(MediaType.APPLICATION_JSON));
		assertEquals(202, responseEntity.getStatusCodeValue());
		assertEquals(memberOutDto, responseEntity.getBody());
	}

	@Test
	public void testLogin_InvalidCredentials() throws Exception {
		MemberLoginDto memberLoginDto = new MemberLoginDto();
		memberLoginDto.setEmail("invalid@example.com");
		memberLoginDto.setPassword("invalidPassword");
		ResponseEntity<?> responseEntity = memberController.login(memberLoginDto);
		System.out.println(responseEntity.getStatusCodeValue());
		System.out.println(responseEntity.getBody());
		mockMvc.perform(MockMvcRequestBuilders.post("/api/login").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		assertEquals(400, responseEntity.getStatusCodeValue());
	}

	@Test
	public void testCreateMember2_Success() throws Exception {
		memberDto.setEmail("test@nucleusteq.com");
		memberDto.setPassword("testPassword");
		memberDto.setRole(Role.USER);

		DepartmentDto department = new DepartmentDto();
		Integer departmentId = 1;
		department.setDepartmentId(departmentId);
		department.setDepartmentName("HR");
		memberDto.setDepartment(department);

		memberOutDto.setEmail("test@nucleusteq.com");
		memberOutDto.setRole(Role.USER);
		memberOutDto.setDepartmentName("HR");
		memberOutDto.setIsFirstLogin(true);
		memberOutDto.setName("test name");

		when(memberService.createMember3(memberDto, email, password)).thenReturn(new ApiResponse("success", true));

		mockMvc.perform(MockMvcRequestBuilders.post("/api/create/nodept", memberDto, email, password)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		ResponseEntity<?> responseEntity = memberController.createMember3(memberDto, email, password);
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	public void changePassword_Test() throws Exception {
		Integer memberId = 1;
		String oldPassword = "12345678";
		String newPassword = "11111111";
		changePasswordDto.setNewPassword(newPassword);
		changePasswordDto.setOldpassword(oldPassword);

		Member member = new Member();
		member.setMemberId(1);
		member.setEmail("user@nucleusteq.com");
		member.setIsFirstLogin(false);
		member.setName("test");
		member.setPassword(oldPassword);
		member.setRole(Role.USER);

		memberOutDto.setMemberId(1);

		when(this.memberService.changePassword(memberId, changePasswordDto)).thenReturn(memberOutDto);

		mockMvc.perform(MockMvcRequestBuilders
				.put("/api/changepassword/changepassword/memberId/{memberId}", memberId, changePasswordDto)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		ResponseEntity<?> responseEntity = memberController.changePassword(memberId, changePasswordDto);
		assertEquals(200, responseEntity.getStatusCodeValue());
		assertEquals(this.memberService.changePassword(memberId, changePasswordDto), responseEntity.getBody());
	}

	@Test
	public void testDeleteMember() throws Exception {
		Integer memberId = 1;
		when(this.memberService.deleteMember(memberId)).thenReturn(new ApiResponse("SS", true));
		mockMvc.perform(MockMvcRequestBuilders.put("/api/member/delete/memberId/{memberId}", memberId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		ResponseEntity<?> responseEntity = memberController.deleteMember(memberId);
		assertEquals(200, responseEntity.getStatusCodeValue());
		assertEquals(this.memberService.deleteMember(memberId), responseEntity.getBody());
	}

	@Test
  public void testGetAllMember( ) throws Exception {
	  
	  when(this.memberService.getAllMemberAuth(email, password, 10))
	  .thenReturn(allOutDto);
	  mockMvc
      .perform(
        MockMvcRequestBuilders
          .put(
            "/apimember/getAll/auth/pageNumber/{pageNumber}",
            email, password, 10
          )
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      );
	  ResponseEntity<?> responseEntity = memberController.getAllMemberAuth(email, password, 10);
	    assertEquals(200, responseEntity.getStatusCodeValue());
	    assertEquals(
	      this.memberService.getAllMemberAuth(email, password, 10),
	      responseEntity.getBody()
	    );
  }
}
