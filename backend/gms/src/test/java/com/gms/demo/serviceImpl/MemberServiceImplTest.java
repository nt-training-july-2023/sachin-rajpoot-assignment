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
import org.springframework.beans.factory.annotation.Autowired;
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

  @Mock
  private  ModelMapper modelMapper ;

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

    assertNotNull(memberService.login(loginDto));
  }

  @Test
  public void testLoginInvalidCredentials() {
    MemberLoginDto loginDto = new MemberLoginDto(
      "invalidEmail",
      "invalidPassword"
    );
    when(memberRepo.findByEmail("invalidEmail")).thenReturn(null);

    assertNull(memberService.login(loginDto));
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
	String email = "admin@nucleusteq.com";
	String password = "adminPassword";
	String name = "kingkong";
	
	MemberDto memberDto = new MemberDto();
    memberDto.setEmail(email);
    memberDto.setPassword(password); 
    memberDto.setIsFirstLogin(true); 
    memberDto.setName(name);
    memberDto.setMemberId(1);
    memberDto.setRole(Role.USER);
    
    

    Member member = new Member();
    member.setEmail(email);
    member.setPassword(password); 
    member.setIsFirstLogin(true); 
    member.setName(name);
    member.setMemberId(1);
    member.setRole(Role.USER);
    
    


    Member adminMember = new Member();
    adminMember.setRole(Role.ADMIN);
    adminMember.setEmail(email); 
    adminMember.setPassword(password);


    Department department = new Department();
    Integer departmentId = 1;
    String departmentName = "HR";
    department.setDepartmentId(departmentId);
    department.setDepartmentName(departmentName);
    
    
    Member member2 = new Member();
    member2.setEmail(email);
    member2.setPassword(password); 
    member2.setIsFirstLogin(true); 
    member2.setName(name);
    member2.setMemberId(1);
    member2.setRole(Role.USER);
    member2.setDepartment(department);

    when(memberRepo.findByEmail(email)).thenReturn(adminMember);
    when(departmentRepo.findById(departmentId))
      .thenReturn(Optional.of(department));
    
    when(modelMapper.map(memberDto, Member.class)).thenReturn(member2);
    when(memberRepo.save(member2)).thenReturn(member2);
    
    MemberOutDto memberOutDto = new MemberOutDto(name, email, Role.USER, departmentName, true, null);
    when(this.modelMapper.map(member2, MemberOutDto.class)).thenReturn(memberOutDto);

    MemberOutDto createdMember = memberService.createMember2(
      memberDto,
      departmentId,
      email,
      password
    );

    System.out.println(createdMember);
    assertNotNull(createdMember);
   
  }

  @Test
  public void testCreateMember2InvalidAdminCredentials() {
    MemberDto memberDto = new MemberDto();
    Integer departmentId = 1;
    String email = "user@example.com";
    String password = "userPassword";

    when(memberRepo.findByEmail(email)).thenReturn(null);

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
    Member member1 = new Member();
    member1.setMemberId(1);
    member1.setName("Member 1");
    member1.setEmail("user1@nucleusteq.com");
    member1.setPassword("userpassword");
    member1.setIsFirstLogin(true);
    member1.setRole(Role.USER);
    member1.setDepartment(null);
    member1.setTickets(null);
    
    MemberOutDto memberOutDto = new MemberOutDto();
    memberOutDto.setEmail(member1.getEmail());
    memberOutDto.setName(member1.getName());
    memberOutDto.setRole(member1.getRole());
    memberOutDto.setDepartmentName(null);
    memberOutDto.setDepartmentName(null);
    memberOutDto.setIsFirstLogin(member1.getIsFirstLogin());


    Member member2 = new Member();
    member2.setMemberId(2);
    member2.setName("Member 2");
    member2.setEmail("user2@nucleusteq.com");
    member2.setPassword("userpassword");
    member2.setIsFirstLogin(true);
    member2.setRole(Role.USER);
    member2.setDepartment(null);
    member2.setTickets(null);

    List<Member> members = new ArrayList<>();
    members.add(member1);
//    members.add(member2);

    when(memberRepo.findAll()).thenReturn(members);
    when(this.modelMapper.map(member1, MemberOutDto.class)).thenReturn(memberOutDto);

    List<MemberOutDto> memberOutDtos = memberService.getAllmember();

    assertEquals(1, memberOutDtos.size());
    assertEquals("Member 1", memberOutDtos.get(0).getName());
//    assertEquals("Member 2", memberOutDtos.get(1).getName());
  }
  
  @Test
  public void changePassword_Test() {
	  
	  Integer memberId = 1;
	  String oldPassword ="12345678";
	  String newPassword ="11111111";
	  
	    Member member = new Member();
	    member.setEmail("user@nucleusteq.com");
	    member.setPassword(oldPassword); 
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
	    when(this.memberRepo.save(member)).thenReturn(member);
	   when(this.modelMapper.map(member, MemberOutDto.class)).thenReturn(memberOutDto);
	   
	    MemberOutDto memberOutDto2 = this.memberService.changePassword(memberId, oldPassword, newPassword);
	    assertEquals(memberOutDto2, memberOutDto);
	    
	    
  }
  
  @Test
  public void changePasswordFail_Test() {
	  
	  Integer memberId = 1;
	  String oldPassword ="12345678";
	  String newPassword ="11111111";
	  
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
//	    when(this.memberRepo.save(member)).thenReturn(member);
//	   when(this.modelMapper.map(member, MemberOutDto.class)).thenReturn(memberOutDto);
	   
	    MemberOutDto memberOutDto2 = this.memberService.changePassword(memberId, oldPassword, newPassword);
	    assertNotEquals(memberOutDto2, memberOutDto);
	    
	    
  }
}
