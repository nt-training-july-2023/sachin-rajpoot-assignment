package com.gms.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DepartmentTest {

  private Department department = new Department();

  @Test
  public void testGetAndSetDepartmentId() {
    department.setDepartmentId(1);
    assertEquals(1, department.getDepartmentId());
  }

  @Test
  public void testGetAndSetName() {
    department.setName("HR Department");
    assertEquals("HR Department", department.getName());
  }

  @Test
  public void testGetAndSetMembers() {
    Member member1 = new Member();
    Member member2 = new Member();
    department.setMembers(new ArrayList<>(List.of(member1, member2)));
    assertEquals(List.of(member1, member2), department.getMembers());
  }

  @Test
  public void testGetAndSetTickets() {
    Ticket ticket1 = new Ticket();
    Ticket ticket2 = new Ticket();
    department.setTickets(new ArrayList<>(List.of(ticket1, ticket2)));
    assertEquals(List.of(ticket1, ticket2), department.getTickets());
  }

  @Test
  public void testToString() {
    department.setDepartmentId(1);
    department.setName("HR Department");

    String expected = "Department [departmentId=1, name=HR Department]";
    assertEquals(expected, department.toString());
  }

  @Test
  public void testDefaultConstructor() {
    assertNotNull(department);
  }
}
