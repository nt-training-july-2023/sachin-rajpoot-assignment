package com.gms.demo.payload;

import static org.junit.jupiter.api.Assertions.*;

import com.gms.demo.entity.Status;
import com.gms.demo.entity.TicketType;
import com.gms.demo.payloads.CommentOutDto;
import com.gms.demo.payloads.TicketOutDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class TicketOutDtoTest {

  private TicketOutDto ticketOutDto = new TicketOutDto();

  Integer ticketId =1;
  String title="Test title"; String description="test decs"; 
  Date createdOn= new Date();
  Date lastUpdatedOn = new Date();
  Status status = Status.OPEN;
  TicketType ticketType= TicketType.GRIEVANCE;
  String departmentName="HR";
  String memberName="kingkong";
  List<CommentOutDto> comments = new ArrayList<>();
 private TicketOutDto ticketOutDto2 = new TicketOutDto(
		  ticketId,  title,  description,  createdOn,  lastUpdatedOn,
			 status,  ticketType,  departmentName,  memberName,  comments
		 );
 
 private TicketOutDto ticketOutDto3 = new TicketOutDto(
		  ticketId,  title,  description,  createdOn,  lastUpdatedOn,
			 status,  ticketType,  departmentName,  memberName,  comments
		 );
  
  @Test
  public void testGetAndSetTitle() {
    ticketOutDto.setTitle("Sample Ticket");
    assertEquals("Sample Ticket", ticketOutDto.getTitle());
  }

  @Test
  public void testGetAndSetDescription() {
    ticketOutDto.setDescription("This is a sample ticket description.");
    assertEquals(
      "This is a sample ticket description.",
      ticketOutDto.getDescription()
    );
  }

  @Test
  public void testGetAndSetCreatedOn() {
    Date createdOn = new Date();
    ticketOutDto.setCreatedOn(createdOn);
    assertEquals(createdOn, ticketOutDto.getCreatedOn());
  }

  @Test
  public void testGetAndSetLastUpdatedOn() {
    Date lastUpdatedOn = new Date();
    ticketOutDto.setLastUpdatedOn(lastUpdatedOn);
    assertEquals(lastUpdatedOn, ticketOutDto.getLastUpdatedOn());
  }

  @Test
  public void testGetAndSetStatus() {
    ticketOutDto.setStatus(Status.OPEN);
    assertEquals(Status.OPEN, ticketOutDto.getStatus());
  }

  @Test
  public void testGetAndSetTicketType() {
    ticketOutDto.setTicketType(TicketType.FEEDBACK);
    assertEquals(TicketType.FEEDBACK, ticketOutDto.getTicketType());
  }

  @Test
  public void testGetAndSetDepartmentName() {
    ticketOutDto.setDepartmentName("IT Department");
    assertEquals("IT Department", ticketOutDto.getDepartmentName());
  }

  @Test
  public void testGetAndSetMemberName() {
    ticketOutDto.setMemberName("kingkong");
    assertEquals("kingkong", ticketOutDto.getMemberName());
  }

  @Test
  public void testGetAndSetComments() {
    List<CommentOutDto> comments = new ArrayList<>();
    CommentOutDto comment1 = new CommentOutDto();
    CommentOutDto comment2 = new CommentOutDto();
    comments.add(comment1);
    comments.add(comment2);

    ticketOutDto.setComments(comments);

    assertEquals(comments, ticketOutDto.getComments());
  }

  @Test
  public void testToString() {
    ticketOutDto.setTitle("Sample Ticket");
    ticketOutDto.setDescription("This is a sample ticket description.");
    ticketOutDto.setStatus(Status.OPEN);
    ticketOutDto.setTicketType(TicketType.FEEDBACK);
    ticketOutDto.setDepartmentName("IT Department");
    ticketOutDto.setMemberName("kingkong");

    String expected =
      "TicketOutDto [title=Sample Ticket, description=This is a sample ticket description., createdOn=null, lastUpdatedOn=null, status=OPEN, ticketType=FEEDBACK, departmentName=IT Department, memberName=kingkong]";
    assertEquals(expected, ticketOutDto.toString());
  }

  @Test
  public void testParameterizedConstructor() {
    Date createdOn = new Date();
    Date lastUpdatedOn = new Date();
    List<CommentOutDto> comments = new ArrayList<>();
    CommentOutDto comment1 = new CommentOutDto();
    CommentOutDto comment2 = new CommentOutDto();
    comments.add(comment1);
    comments.add(comment2);

    TicketOutDto ticket = new TicketOutDto(
      "Sample Ticket",
      "Description",
      createdOn,
      lastUpdatedOn,
      Status.OPEN,
      TicketType.FEEDBACK,
      "IT Department",
      "kingkong",
      comments
    );

    assertEquals("Sample Ticket", ticket.getTitle());
    assertEquals("Description", ticket.getDescription());
    assertEquals(createdOn, ticket.getCreatedOn());
    assertEquals(lastUpdatedOn, ticket.getLastUpdatedOn());
    assertEquals(Status.OPEN, ticket.getStatus());
    assertEquals(TicketType.FEEDBACK, ticket.getTicketType());
    assertEquals("IT Department", ticket.getDepartmentName());
    assertEquals("kingkong", ticket.getMemberName());
    assertEquals(comments, ticket.getComments());
  }

  @Test
  public void testDefaultConstructor() {
    assertNotNull(new TicketOutDto());
  }
  
  @Test
  public void testHashCodeMethod() {
	 int receivedValue = Objects.hash(comments, createdOn, departmentName, description, lastUpdatedOn, memberName, status, ticketId,
				ticketType, title);
	 int expected = this.ticketOutDto2.hashCode();
	 assertEquals(expected, receivedValue);
  }
  
  @Test
  public void testEqualsMethod() {
	  assertTrue(this.ticketOutDto2.equals(ticketOutDto3));
  }
}
