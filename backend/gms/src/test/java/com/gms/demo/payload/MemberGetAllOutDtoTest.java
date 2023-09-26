package com.gms.demo.payload;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import com.gms.demo.entity.Role;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberGetAllOutDto;

public class MemberGetAllOutDtoTest {

	private MemberGetAllOutDto memberGetAllOutDto = new MemberGetAllOutDto();
	private MemberGetAllOutDto memberGetAllOutDto2 = new MemberGetAllOutDto();
	 @Test
	  public void testGetAndSetMemberId() {
		 memberGetAllOutDto.setMemberId(1);
	    assertEquals(1, memberGetAllOutDto.getMemberId());
	  }

	  @Test
	  public void testGetAndSetName() {
		  memberGetAllOutDto.setName("kingkong");
	    assertEquals("kingkong", memberGetAllOutDto.getName());
	  }

	  @Test
	  public void testGetAndSetEmail() {
		  memberGetAllOutDto.setEmail("kingkong@example.com");
	    assertEquals("kingkong@example.com", memberGetAllOutDto.getEmail());
	  }
	  @Test
	  
	  public void testGetAndSetRole() {
		  memberGetAllOutDto.setRole(Role.ADMIN);
	    assertEquals(Role.ADMIN, memberGetAllOutDto.getRole());
	  }
	  
	  @Test
	  public void testGetAndSetDepartmentName() {
		  memberGetAllOutDto.setDepartmentName("HR");
	    assertEquals("HR", memberGetAllOutDto.getDepartmentName());
	  }
	  
	  @Test
	  public void testToString() {
		  memberGetAllOutDto.setMemberId(1);
		  memberGetAllOutDto.setName("kingkong");
		  memberGetAllOutDto.setEmail("kingkong@example.com");
	      memberGetAllOutDto.setRole(Role.ADMIN);
	      memberGetAllOutDto.setDepartmentName("HR");

	    String expected =
	    		 "MemberGetAllOutDto [memberId=" +
	    			       +memberGetAllOutDto.getMemberId() +
	    			      ", name=" +
	    			      memberGetAllOutDto.getName() +
	    			      ", email=" +
	    			      memberGetAllOutDto.getEmail() +
	    			      ", role=" +
	    			      memberGetAllOutDto.getRole() +
	    			      ", departmentName=" +
	    			      memberGetAllOutDto.getDepartmentName() +
	    			      "]";
	    assertEquals(expected, memberGetAllOutDto.toString());
	  }
	  
	  
	  @Test
	  public void testParameterizedConstructor() {
	    DepartmentDto department = new DepartmentDto();
	    department.setDepartmentName("HR Department");

	    MemberGetAllOutDto member = new MemberGetAllOutDto(
	      1,
	      "kingkong",
	      "kingkong@example.com",
	      Role.ADMIN,
	      "HR"
	    );
	    assertEquals(1, member.getMemberId());
	    assertEquals("kingkong", member.getName());
	    assertEquals("kingkong@example.com", member.getEmail());
	    assertEquals(Role.ADMIN, member.getRole());
	    assertEquals("HR", member.getDepartmentName());
	  }
	  
	  @Test
	  public void testDefaultConstructor() {
	    assertNotNull(new MemberGetAllOutDto());
	  }
	  
	  @Test
	  public void testEquals() {
		  memberGetAllOutDto.setMemberId(1);
		  memberGetAllOutDto.setName("kingkong");
		  memberGetAllOutDto.setEmail("kingkong@example.com");
	      memberGetAllOutDto.setRole(Role.ADMIN);
	      memberGetAllOutDto.setDepartmentName("HR");
	      
	      memberGetAllOutDto2.setMemberId(1);
		  memberGetAllOutDto2.setName("kingkong");
		  memberGetAllOutDto2.setEmail("kingkong@example.com");
	      memberGetAllOutDto2.setRole(Role.ADMIN);
	      memberGetAllOutDto2.setDepartmentName("HR");
	      
	      assertTrue(memberGetAllOutDto.equals(memberGetAllOutDto2));;
	  }
	  
	  @Test
	  public void testHashCode() {
		  memberGetAllOutDto.setMemberId(1);
		  memberGetAllOutDto.setName("kingkong");
		  memberGetAllOutDto.setEmail("kingkong@example.com");
	      memberGetAllOutDto.setRole(Role.ADMIN);
	      memberGetAllOutDto.setDepartmentName("HR");
		  assertEquals(memberGetAllOutDto.hashCode(), Objects.hash("HR", "kingkong@example.com", 1, "kingkong", Role.ADMIN));
	  }
}
