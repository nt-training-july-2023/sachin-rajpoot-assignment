package com.gms.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gms.demo.entity.Member;
import com.gms.demo.entity.Role;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberLoginDto;
import com.gms.demo.payloads.MemberOutDto;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.service.MemberService;
import com.gms.demo.serviceimpl.MemberServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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

  @Test
  public void testLogin_Success() throws Exception {
    MemberLoginDto memberLoginDto = new MemberLoginDto();
    memberLoginDto.setEmail("test@nucleusteq.com");
    memberLoginDto.setPassword("testPassword");

    when(
      memberService.verifyEmailAndPassword(
        memberLoginDto.getEmail(),
        memberLoginDto.getPassword()
      )
    )
      .thenReturn(true);
    when(memberService.login(memberLoginDto)).thenReturn(true);

    ResponseEntity<?> responseEntity = memberController.login(memberLoginDto);
    System.out.println(responseEntity.getStatusCodeValue());
    System.out.println(responseEntity.getBody());
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post("/api/login")
          .contentType(MediaType.APPLICATION_JSON)
          .content(new ObjectMapper().writeValueAsString(memberLoginDto))
          .accept(MediaType.APPLICATION_JSON)
      );
//      .andExpect(MockMvcResultMatchers.status().isAccepted())
//      .andExpect(MockMvcResultMatchers.content().string("Login Successfully"));
    assertEquals(202, responseEntity.getStatusCodeValue());
    assertEquals("Login Successfully", responseEntity.getBody());
  }

  @Test
  public void testLogin_InvalidCredentials() throws Exception {
    MemberLoginDto memberLoginDto = new MemberLoginDto();
    memberLoginDto.setEmail("invalid@example.com");
    memberLoginDto.setPassword("invalidPassword");

    when(
      memberService.verifyEmailAndPassword(
        memberLoginDto.getEmail(),
        memberLoginDto.getPassword()
      )
    )
      .thenReturn(false);
    
    ResponseEntity<?> responseEntity = memberController.login(memberLoginDto);
    System.out.println(responseEntity.getStatusCodeValue());
    System.out.println(responseEntity.getBody());

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post("/api/login")
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      );
//      .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    assertEquals(401, responseEntity.getStatusCodeValue());
  }

//  @Test
//  public void testCreateMember_Success() throws Exception {
//    MemberDto memberDto = new MemberDto();
//    memberDto.setEmail("test@example.com");
//    memberDto.setPassword("testPassword");
//    memberDto.setRole(Role.USER);
//
//    Integer departmentId = 1;
//
//    when(
//      memberService.verifyEmailAndPassword(
//        memberDto.getEmail(),
//        memberDto.getPassword()
//      )
//    )
//      .thenReturn(true);
//    when(memberService.createMember(memberDto, departmentId))
//      .thenReturn(memberDto);
//
//    mockMvc
//      .perform(
//        MockMvcRequestBuilders
//          .post("/api/member/departmentId/{departmentId}", departmentId)
//          .content(objectMapper.writeValueAsString(memberDto))
//          .contentType(MediaType.APPLICATION_JSON)
//          .accept(MediaType.APPLICATION_JSON)
//      )
//      .andExpect(MockMvcResultMatchers.status().isCreated())
//      .andExpect(
//        MockMvcResultMatchers.jsonPath("$.email").value("test@example.com")
//      )
//      .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("ROLE_USER"));
//  }

  @Test
  public void testCreateMember_InvalidCredentials() throws Exception {
    MemberDto memberDto = new MemberDto();
    memberDto.setEmail("invalid@example.com");
    memberDto.setPassword("invalidPassword");

//    Integer departmentId = 1;
    Integer departmentId = 1;
    String email = "admin@nucleusteq.com";
    String password = "adminPassword";

//    when(
//      memberService.verifyEmailAndPassword(
//        memberDto.getEmail(),
//        memberDto.getPassword()
//      )
//    )
//      .thenReturn(false);

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post("/api/member/departmentId/{departmentId}", departmentId)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      );
//      .andExpect(MockMvcResultMatchers.status().isBadRequest());
    ResponseEntity<MemberOutDto> responseEntity = memberController.createMember2(memberDto,departmentId,email,password);
    System.out.println(responseEntity.getStatusCodeValue());
    System.out.println(responseEntity.getBody());
    
    assertEquals(401, responseEntity.getStatusCodeValue());
  }

  @Test
  public void testCreateMember2_Success() throws Exception {
    MemberDto memberDto = new MemberDto();
    memberDto.setEmail("test@nucleusteq.com");
    memberDto.setPassword("testPassword");
    memberDto.setRole(Role.USER);

    Integer departmentId = 1;
    String email = "admin@nucleusteq.com";
    String password = "adminPassword";

    MemberOutDto memberOutDto = new MemberOutDto();
    memberOutDto.setEmail("test@nucleusteq.com");
    memberOutDto.setRole(Role.USER);
    memberOutDto.setDepartmentName(null);
    memberOutDto.setIsFirstLogin(true);
    memberOutDto.setName("test name");
    memberOutDto.setTickets(null);
    
//    when(
//      memberService.verifyEmailAndPassword(
//        memberDto.getEmail(),
//        memberDto.getPassword()
//      )
//    )
//      .thenReturn(true);
    when(memberService.createMember2(memberDto, departmentId, email, password))
      .thenReturn(memberOutDto);

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post(
            "/api/{departmentId}/email/{email}/password/{password}",
            departmentId,
            email,
            password
          )
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      );
//      .andExpect(MockMvcResultMatchers.status().isCreated())
//      .andExpect(
//        MockMvcResultMatchers.jsonPath("$.email").value("test@nucleusteq.com")
//      );
    ResponseEntity<MemberOutDto> responseEntity = memberController.createMember2(memberDto,departmentId,email,password);
    System.out.println(responseEntity.getStatusCodeValue());
    System.out.println(responseEntity.getBody());
    
    assertEquals(201, responseEntity.getStatusCodeValue());
  }

  @Test
  public void testCreateMember2_Unauthorized() throws Exception {
    MemberDto memberDto = new MemberDto();
    memberDto.setEmail("test@example.com");
    memberDto.setPassword("testPassword");
    memberDto.setRole(Role.USER);

    Integer departmentId = 1;
    String email = "user@example.com";
    String password = "userPassword";

//    when(
//      memberService.verifyEmailAndPassword(
//        memberDto.getEmail(),
//        memberDto.getPassword()
//      )
//    )
//      .thenReturn(true);
    when(memberService.createMember2(memberDto, departmentId, email, password))
      .thenReturn(null);

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post(
            "/api/{departmentId}/email/{email}/password/{password}",
            departmentId,
            email,
            password
          )
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      );
//      .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    ResponseEntity<MemberOutDto> responseEntity = memberController.createMember2(memberDto,departmentId,email,password);
    System.out.println(responseEntity.getStatusCodeValue());
    System.out.println(responseEntity.getBody());
    
    assertEquals(401, responseEntity.getStatusCodeValue());
    
  }
  
  @Test
  public void changePassword_Test() throws Exception {
	  
	  Integer memberId = 1;
	  String oldPassword ="12345678";
	  String newPassword ="11111111";
	  
	  Member member = new Member();
	  member.setMemberId(1);
	  member.setEmail("user@nucleusteq.com");
	  member.setDepartment(null);
	  member.setIsFirstLogin(false);
	  member.setName("test");
	  member.setPassword(oldPassword);
	  member.setTickets(null);
	  member.setRole(Role.USER);
	  
	  

	  
	  MemberOutDto memberOutDto = new MemberOutDto();
	  memberOutDto.setMemberId(1);
	  when(this.memberService.changePassword(memberId, oldPassword, newPassword)).thenReturn(memberOutDto);
	  
	    mockMvc
	      .perform(
	        MockMvcRequestBuilders
	          .put(
	            "/api/changepassword/memberId/{memberId}/oldpassword/{oldPassword}/newpassword/{newPassword}",
	            memberId,
	            oldPassword,
	            newPassword
	          )
	          .contentType(MediaType.APPLICATION_JSON)
	          .accept(MediaType.APPLICATION_JSON)
	      )
	      .andExpect(MockMvcResultMatchers.status().isOk());
	    ResponseEntity<MemberOutDto> responseEntity = memberController.changePassword(memberId,oldPassword,newPassword);
	  assertEquals(200, responseEntity.getStatusCodeValue());
	  assertEquals(this.memberService.changePassword(memberId, oldPassword, newPassword), responseEntity.getBody());
  }
  

}