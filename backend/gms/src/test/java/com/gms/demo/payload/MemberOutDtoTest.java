package com.gms.demo.payload;

import static org.junit.jupiter.api.Assertions.*;

import com.gms.demo.entity.Role;
import com.gms.demo.payloads.MemberOutDto;
import com.gms.demo.payloads.TicketOutDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class MemberOutDtoTest {

  private MemberOutDto memberOutDto = new MemberOutDto();
  
  Integer memberId =1;
  String name="kingkong";
  String email="kingkong@nucleusteq.com";
  Role role = Role.USER;
  String departmentName="Test Dept";
  Boolean isFirstLogin = true;
  List<TicketOutDto> tickets = new ArrayList<>();
  private MemberOutDto memberOutDto2 = new MemberOutDto(
		   memberId,  name,  email,  role,  departmentName,  isFirstLogin,
			 tickets);
  private MemberOutDto memberOutDto3 = new MemberOutDto(
		   memberId,  name,  email,  role,  departmentName,  isFirstLogin,
			 tickets);
 

  @Test
  public void testGetAndSetName() {
    memberOutDto.setName("kingkong");
    assertEquals("kingkong", memberOutDto.getName());
  }

  @Test
  public void testGetAndSetEmail() {
    memberOutDto.setEmail("kingkong@example.com");
    assertEquals("kingkong@example.com", memberOutDto.getEmail());
  }

  @Test
  public void testGetAndSetRole() {
    memberOutDto.setRole(Role.USER);
    assertEquals(Role.USER, memberOutDto.getRole());
  }

  @Test
  public void testGetAndSetDepartmentName() {
    memberOutDto.setDepartmentName("IT Department");
    assertEquals("IT Department", memberOutDto.getDepartmentName());
  }

  @Test
  public void testGetAndSetIsFirstLogin() {
    memberOutDto.setIsFirstLogin(true);
    assertTrue(memberOutDto.getIsFirstLogin());
  }

  @Test
  public void testGetAndSetTickets() {
    List<TicketOutDto> tickets = new ArrayList<>();
    tickets.add(new TicketOutDto());
    tickets.add(new TicketOutDto());

    memberOutDto.setTickets(tickets);

    assertEquals(tickets, memberOutDto.getTickets());
  }

  @Test
  public void testToString() {
    memberOutDto.setName("kingkong");
    memberOutDto.setEmail("kingkong@example.com");
    memberOutDto.setRole(Role.USER);
    memberOutDto.setDepartmentName("IT Department");
    memberOutDto.setIsFirstLogin(true);

    String expected =
      "MemberOutDto [name=kingkong, email=kingkong@example.com, role=USER, departmentName=IT Department, isFirstLogin=true]";
    assertEquals(expected, memberOutDto.toString());
  }

  @Test
  public void testParameterizedConstructor() {
    List<TicketOutDto> tickets = new ArrayList<>();
    tickets.add(new TicketOutDto());
    tickets.add(new TicketOutDto());

    MemberOutDto member = new MemberOutDto(
      "kingkong",
      "kingkong@example.com",
      Role.USER,
      "IT Department",
      true,
      tickets
    );

    assertEquals("kingkong", member.getName());
    assertEquals("kingkong@example.com", member.getEmail());
    assertEquals(Role.USER, member.getRole());
    assertEquals("IT Department", member.getDepartmentName());
    assertTrue(member.getIsFirstLogin());
    assertEquals(tickets, member.getTickets());
  }

  @Test
  public void testDefaultConstructor() {
    assertNotNull(new MemberOutDto());
  }
  
  @Test
  public void testHashCodeMethod() {
	 int receivedValue = Objects.hash(departmentName, email, isFirstLogin, memberId, name, role, tickets);
	 int expected = this.memberOutDto2.hashCode();
	 assertEquals(expected, receivedValue);
  }
  
  @Test
  public void testEqualsMethod() {
	  assertTrue(this.memberOutDto2.equals(memberOutDto3));
  }
}
