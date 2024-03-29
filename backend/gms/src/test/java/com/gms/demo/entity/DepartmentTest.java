package com.gms.demo.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DepartmentTest {

  private Department department = new Department();
  Department department2 = new Department(1, "test", 
		  new ArrayList<Member>(), new ArrayList<Ticket>());

  @Test
  public void testGetAndSetDepartmentId() {
    department.setDepartmentId(1);
    Integer id = 1;
    assertEquals(id, department.getDepartmentId());
  }

  @Test
  public void testGetAndSetName() {
    department.setDepartmentName("HR Department");
    assertEquals("HR Department", department.getDepartmentName());
  }

  @Test
  public void testGetAndSetMembers() {
    Member member1 = new Member();
    Member member2 = new Member();
    List<Member> members = new ArrayList<>();
    members.add(member2); members.add(member1);
    department.setMembers(members);
    assertEquals(members, department.getMembers());
  }

  @Test
  public void testGetAndSetTickets() {
    Ticket ticket1 = new Ticket();
    Ticket ticket2 = new Ticket();
    List<Ticket> tickets = new ArrayList<>();
    tickets.add(ticket1); tickets.add(ticket2);
    department.setTickets(tickets);
    assertEquals(tickets, department.getTickets());
  }

//  @Test
//  public void testToString() {
//    department.setDepartmentId(1);
//    department.setDepartmentName("HR Department");
//
//    String expected = "Department [departmentId=1, name=HR Department]";
//    assertEquals(expected, department.toString());
//  }

  @Test
  public void testDefaultConstructor() {
    assertNotNull(department);
  }
}
