package com.gms.demo.payload;

import static org.junit.jupiter.api.Assertions.*;

import com.gms.demo.payloads.DepartmentOutDto;
import com.gms.demo.payloads.MemberOutDto;
import com.gms.demo.payloads.TicketOutDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DepartmentOutDtoTest {

  private DepartmentOutDto departmentOutDto = new DepartmentOutDto();

  @Test
  public void testGetAndSetDepartmentName() {
    departmentOutDto.setDepartmentName("HR Department");
    assertEquals("HR Department", departmentOutDto.getDepartmentName());
  }

  @Test
  public void testGetAndSetMembers() {
    List<MemberOutDto> members = new ArrayList<>();
    members.add(new MemberOutDto());
    departmentOutDto.setMembers(members);

    assertEquals(1, departmentOutDto.getMembers().size());
  }

  @Test
  public void testGetAndSetTickets() {
    List<TicketOutDto> tickets = new ArrayList<>();
    tickets.add(new TicketOutDto());
    departmentOutDto.setTickets(tickets);

    assertEquals(1, departmentOutDto.getTickets().size());
  }

  @Test
  public void testToString() {
    departmentOutDto.setDepartmentName("HR Department");

    String expected = "DepartmentOutDto [departmentName=HR Department]";
    assertEquals(expected, departmentOutDto.toString());
  }

  @Test
  public void testParameterizedConstructor() {
    List<MemberOutDto> members = new ArrayList<>();
    members.add(new MemberOutDto());

    List<TicketOutDto> tickets = new ArrayList<>();
    tickets.add(new TicketOutDto());

    DepartmentOutDto department = new DepartmentOutDto(
      "HR Department",
      members,
      tickets
    );

    assertEquals("HR Department", department.getDepartmentName());
    assertEquals(1, department.getMembers().size());

    assertEquals(1, department.getTickets().size());
  }

  @Test
  public void testDefaultConstructor() {
    assertNotNull(new DepartmentOutDto());
  }
}
