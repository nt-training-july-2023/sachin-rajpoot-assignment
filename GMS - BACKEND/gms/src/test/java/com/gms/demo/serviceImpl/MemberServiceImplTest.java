package com.gms.demo.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gms.demo.entity.Department;
import com.gms.demo.entity.Member;
import com.gms.demo.entity.Role;
import com.gms.demo.payloads.MemberDto;
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
import org.junit.jupiter.api.Test;
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

  private final ModelMapper modelMapper = new ModelMapper();

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testLoginValidCredentials() {
    MemberLoginDto loginDto = new MemberLoginDto("validEmail", "validPassword");
    Member member = new Member();
    member.setEmail("validEmail");
    member.setPassword("validPassword");
    when(memberRepo.findByEmail("validEmail")).thenReturn(member);

    assertTrue(memberService.login(loginDto));
  }

  @Test
  public void testLoginInvalidCredentials() {
    MemberLoginDto loginDto = new MemberLoginDto(
      "invalidEmail",
      "invalidPassword"
    );
    when(memberRepo.findByEmail("invalidEmail")).thenReturn(null);

    assertFalse(memberService.login(loginDto));
  }

  @Test
  public void testVerifyEmailAndPasswordValid() {
    assertTrue(
      memberService.verifyEmailAndPassword(
        "user@nucleusteq.com",
        "validPassword"
      )
    );
  }

  @Test
  public void testVerifyEmailAndPasswordInvalidEmail() {
    assertFalse(
      memberService.verifyEmailAndPassword("invalid_email", "validPassword")
    );
  }

  @Test
  public void testVerifyEmailAndPasswordInvalidPassword() {
    assertFalse(
      memberService.verifyEmailAndPassword("user@example.com", "pass")
    );
  }

  @Test
  public void testCreateMember2ValidAdminCredentials() {
    MemberDto memberDto = new MemberDto();
    Integer departmentId = 1;
    String email = "admin@example.com";
    String password = "adminPassword";

    Member member = new Member();
    Integer departmentId2 = 1;
    String email2 = "admin@example.com";
    String password2 = "adminPassword";

    Member adminMember = new Member();
    adminMember.setRole(Role.ADMIN);

    Department department = new Department();
    department.setDepartmentId(departmentId);

    when(memberRepo.findByEmail(email)).thenReturn(adminMember);
    when(departmentRepo.findById(departmentId))
      .thenReturn(Optional.of(department));
    when(memberRepo.save(any(Member.class))).thenReturn(new Member());

    MemberOutDto createdMember = memberService.createMember2(
      memberDto,
      departmentId,
      email,
      password
    );

    assertNotNull(createdMember);
  }

  @Test
  public void testCreateMember2InvalidAdminCredentials() {
    MemberDto memberDto = new MemberDto();
    Integer departmentId = 1;
    String email = "user@example.com";
    String password = "userPassword";

    Member userMember = new Member(); // Regular user, not admin

    when(memberRepo.findByEmail(email)).thenReturn(userMember);

    MemberOutDto createdMember = memberService.createMember2(
      memberDto,
      departmentId,
      email,
      password
    );

    assertNull(createdMember);
  }

  @Test
  public void testGetAllMembers() {
    List<Member> members = new ArrayList<>();
    Member member1 = new Member();
    member1.setMemberId(1);
    member1.setName("Member 1");

    Member member2 = new Member();
    member2.setMemberId(2);
    member2.setName("Member 2");

    members.add(member1);
    members.add(member2);

    when(memberRepo.findAll()).thenReturn(members);

    List<MemberOutDto> memberOutDtos = memberService.getAllmember();

    assertEquals(2, memberOutDtos.size());
    assertEquals("Member 1", memberOutDtos.get(0).getName());
    assertEquals("Member 2", memberOutDtos.get(1).getName());
  }
}
