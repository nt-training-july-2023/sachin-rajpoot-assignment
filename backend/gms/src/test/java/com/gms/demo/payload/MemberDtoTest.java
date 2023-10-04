package com.gms.demo.payload;

import static org.junit.jupiter.api.Assertions.*;

import com.gms.demo.entity.Role;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.MemberDto;
import org.junit.jupiter.api.Test;

public class MemberDtoTest {

  private MemberDto memberDto = new MemberDto();

  @Test
  public void testGetAndSetMemberId() {
    memberDto.setMemberId(1);
    assertEquals(1, memberDto.getMemberId());
  }

  @Test
  public void testGetAndSetName() {
    memberDto.setName("kingkong");
    assertEquals("kingkong", memberDto.getName());
  }

  @Test
  public void testGetAndSetEmail() {
    memberDto.setEmail("kingkong@example.com");
    assertEquals("kingkong@example.com", memberDto.getEmail());
  }

  @Test
  public void testGetAndSetPassword() {
    memberDto.setPassword("password123");
    assertEquals("password123", memberDto.getPassword());
  }

  @Test
  public void testGetAndSetIsFirstLogin() {
    memberDto.setIsFirstLogin(true);
    assertTrue(memberDto.getIsFirstLogin());
  }

  @Test
  public void testGetAndSetRole() {
    memberDto.setRole(Role.ADMIN);
    assertEquals(Role.ADMIN, memberDto.getRole());
  }

  @Test
  public void testGetAndSetDepartment() {
    DepartmentDto department = new DepartmentDto();
    department.setDepartmentName("HR Department");
    memberDto.setDepartment(department);

    assertEquals(
      "HR Department",
      memberDto.getDepartment().getDepartmentName()
    );
  }

  @Test
  public void testToString() {
    memberDto.setMemberId(1);
    memberDto.setName("kingkong");
    memberDto.setEmail("kingkong@example.com");
    memberDto.setPassword("password123");
    memberDto.setIsFirstLogin(true);
    memberDto.setRole(Role.ADMIN);

    String expected =
      "MemberDto [memberId=1, name=kingkong, email=kingkong@example.com, password=password123, isFirstLogin=true, role=ADMIN]";
    assertEquals(expected, memberDto.toString());
  }

  @Test
  public void testParameterizedConstructor() {
    DepartmentDto department = new DepartmentDto();
    department.setDepartmentName("HR Department");

    MemberDto member = new MemberDto(
      1,
      "kingkong",
      "kingkong@example.com",
      "password123",
      true,
      Role.ADMIN,
      department
    );

    assertEquals(1, member.getMemberId());
    assertEquals("kingkong", member.getName());
    assertEquals("kingkong@example.com", member.getEmail());
    assertEquals("password123", member.getPassword());
    assertTrue(member.getIsFirstLogin());
    assertEquals(Role.ADMIN, member.getRole());
    assertEquals("HR Department", member.getDepartment().getDepartmentName());
  }

  @Test
  public void testDefaultConstructor() {
    assertNotNull(new MemberDto());
  }
}
