package com.gms.demo.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gms.demo.entity.Role;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberLoginDto;
import com.gms.demo.payloads.MemberOutDto;
import com.gms.demo.service.MemberService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Mock
  private MemberService memberService;

  @InjectMocks
  MemberController memberController;

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

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post("/api/login")
          .content(objectMapper.writeValueAsString(memberLoginDto))
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(MockMvcResultMatchers.status().isAccepted())
      .andExpect(MockMvcResultMatchers.content().string("Login Successfully"));
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

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post("/api/login")
          .content(objectMapper.writeValueAsString(memberLoginDto))
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(MockMvcResultMatchers.status().isUnauthorized());
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

    Integer departmentId = 1;

    when(
      memberService.verifyEmailAndPassword(
        memberDto.getEmail(),
        memberDto.getPassword()
      )
    )
      .thenReturn(false);

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post("/api/member/departmentId/{departmentId}", departmentId)
          .content(objectMapper.writeValueAsString(memberDto))
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  public void testCreateMember2_Success() throws Exception {
    MemberDto memberDto = new MemberDto();
    memberDto.setEmail("test@example.com");
    memberDto.setPassword("testPassword");
    memberDto.setRole(Role.USER);

    Integer departmentId = 1;
    String email = "admin@example.com";
    String password = "adminPassword";

    MemberOutDto memberOutDto = new MemberOutDto();
    memberOutDto.setEmail("test@example.com");
    memberOutDto.setRole(Role.USER);

    when(
      memberService.verifyEmailAndPassword(
        memberDto.getEmail(),
        memberDto.getPassword()
      )
    )
      .thenReturn(true);
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
          .content(objectMapper.writeValueAsString(memberDto))
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andExpect(
        MockMvcResultMatchers.jsonPath("$.email").value("test@example.com")
      )
      .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("ROLE_USER"));
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

    when(
      memberService.verifyEmailAndPassword(
        memberDto.getEmail(),
        memberDto.getPassword()
      )
    )
      .thenReturn(true);
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
          .content(objectMapper.writeValueAsString(memberDto))
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(MockMvcResultMatchers.status().isUnauthorized());
  }
}
