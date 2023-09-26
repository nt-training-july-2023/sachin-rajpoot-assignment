package com.gms.demo.payload;

import static org.junit.jupiter.api.Assertions.*;

import com.gms.demo.entity.Status;
import com.gms.demo.entity.TicketType;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.TicketDto;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class TicketDtoTest {

  private TicketDto ticketDto = new TicketDto();

  @Test
  public void testGetAndSetTicketId() {
    ticketDto.setTicketId(1);
    assertEquals(1, ticketDto.getTicketId());
  }

  @Test
  public void testGetAndSetTitle() {
    ticketDto.setTitle("Sample Ticket");
    assertEquals("Sample Ticket", ticketDto.getTitle());
  }

  @Test
  public void testGetAndSetDescription() {
    ticketDto.setDescription("This is a sample ticket description.");
    assertEquals(
      "This is a sample ticket description.",
      ticketDto.getDescription()
    );
  }

  @Test
  public void testGetAndSetCreatedOn() {
    Date createdOn = new Date();
    ticketDto.setCreatedOn(createdOn);
    assertEquals(createdOn, ticketDto.getCreatedOn());
  }

  @Test
  public void testGetAndSetLastUpdatedOn() {
    Date lastUpdatedOn = new Date();
    ticketDto.setLastUpdatedOn(lastUpdatedOn);
    assertEquals(lastUpdatedOn, ticketDto.getLastUpdatedOn());
  }

  @Test
  public void testGetAndSetStatus() {
    ticketDto.setStatus(Status.OPEN);
    assertEquals(Status.OPEN, ticketDto.getStatus());
  }

  @Test
  public void testGetAndSetTicketType() {
    ticketDto.setTicketType(TicketType.FEEDBACK);
    assertEquals(TicketType.FEEDBACK, ticketDto.getTicketType());
  }

  @Test
  public void testGetAndSetDepartment() {
    DepartmentDto departmentDto = new DepartmentDto();
    departmentDto.setDepartmentName("IT Department");
    ticketDto.setDepartment(departmentDto);
    assertEquals(departmentDto, ticketDto.getDepartment());
  }

  @Test
  public void testGetAndSetMember() {
    MemberDto memberDto = new MemberDto();
    memberDto.setName("kingkong");
    ticketDto.setMember(memberDto);
    assertEquals(memberDto, ticketDto.getMember());
  }

  @Test
  public void testToString() {
    ticketDto.setTicketId(1);
    ticketDto.setTitle("Sample Ticket");
    ticketDto.setDescription("This is a sample ticket description.");
    ticketDto.setStatus(Status.OPEN);
    ticketDto.setTicketType(TicketType.FEEDBACK);

    String expected =
      "TicketDto [ticketId=1, title=Sample Ticket, description=This is a sample ticket description., createdOn=null, lastUpdatedOn=null, status=OPEN, ticketType=FEEDBACK]";
    assertEquals(expected, ticketDto.toString());
  }

  @Test
  public void testParameterizedConstructor() {
    Date createdOn = new Date();
    Date lastUpdatedOn = new Date();
    DepartmentDto departmentDto = new DepartmentDto();
    departmentDto.setDepartmentName("IT Department");
    MemberDto memberDto = new MemberDto();
    memberDto.setName("kingkong");

    TicketDto ticket = new TicketDto(
      1,
      "Sample Ticket",
      "Description",
      createdOn,
      lastUpdatedOn,
      Status.OPEN,
      TicketType.FEEDBACK,
      departmentDto,
      memberDto
    );

    assertEquals(1, ticket.getTicketId());
    assertEquals("Sample Ticket", ticket.getTitle());
    assertEquals("Description", ticket.getDescription());
    assertEquals(createdOn, ticket.getCreatedOn());
    assertEquals(lastUpdatedOn, ticket.getLastUpdatedOn());
    assertEquals(Status.OPEN, ticket.getStatus());
    assertEquals(TicketType.FEEDBACK, ticket.getTicketType());
    assertEquals(departmentDto, ticket.getDepartment());
    assertEquals(memberDto, ticket.getMember());
  }

  @Test
  public void testDefaultConstructor() {
    assertNotNull(new TicketDto());
  }
}
