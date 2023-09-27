package com.gms.demo.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TicketTest {

  private Ticket ticket = new Ticket();
  Ticket ticket2 = new Ticket(1, "test", "desc", 
		  new Date(), new Date(), Status.CLOSED, 
		  TicketType.FEEDBACK, new Department(), 
		  new Member(), new ArrayList<Comment>());

  @Test
  public void testGetAndSetTicketId() {
    ticket.setTicketId(1);
    assertEquals((Integer) 1, ticket.getTicketId());
  }

  @Test
  public void testGetAndSetTitle() {
    ticket.setTitle("Sample Ticket");
    assertEquals("Sample Ticket", ticket.getTitle());
  }

  @Test
  public void testGetAndSetDescription() {
    ticket.setDescription("This is a sample ticket description.");
    assertEquals(
      "This is a sample ticket description.",
      ticket.getDescription()
    );
  }

  @Test
  public void testGetAndSetCreatedOn() {
    Date createdOn = new Date();
    ticket.setCreatedOn(createdOn);
    assertEquals(createdOn, ticket.getCreatedOn());
  }

  @Test
  public void testGetAndSetLastUpdatedOn() {
    Date lastUpdatedOn = new Date();
    ticket.setLastUpdatedOn(lastUpdatedOn);
    assertEquals(lastUpdatedOn, ticket.getLastUpdatedOn());
  }

  @Test
  public void testGetAndSetStatus() {
    ticket.setStatus(Status.OPEN);
    assertEquals(Status.OPEN, ticket.getStatus());
  }

  @Test
  public void testGetAndSetTicketType() {
    ticket.setTicketType(TicketType.FEEDBACK);
    assertEquals(TicketType.FEEDBACK, ticket.getTicketType());
  }

  @Test
  public void testGetAndSetDepartment() {
    Department department = new Department();
    ticket.setDepartment(department);
    assertEquals(department, ticket.getDepartment());
  }

  @Test
  public void testGetAndSetMember() {
    Member member = new Member();
    ticket.setMember(member);
    assertEquals(member, ticket.getMember());
  }

  @Test
  public void testGetAndSetComments() {
    Comment comment1 = new Comment();
    Comment comment2 = new Comment();
    List<Comment> comments = new ArrayList<>();
    comments.add(comment1); comments.add(comment2);
    ticket.setComments(comments);
    assertEquals(comments, ticket.getComments());
  }
  
  @Test
  public void testAddComments() {
    Comment comment2 = new Comment();
    List<Comment> comments = new ArrayList<>();
    ticket.setComments(comments);
    this.ticket.addComment(comment2);  
    assertEquals(comment2, ticket.getComments().get(0));
  }

  @Test
  public void testToString() {
    ticket.setTicketId(1);
    ticket.setTitle("Sample Ticket");
    ticket.setDescription("This is a sample ticket description.");
    ticket.setStatus(Status.OPEN);
    ticket.setTicketType(TicketType.FEEDBACK);

    String expected =
      "Ticket [ticketId=1, title=Sample Ticket, description=This is a sample ticket description., " +
      "createdOn=null, lastUpdatedOn=null, status=OPEN, ticketType=FEEDBACK, department=null, member=null, comments=[]]";
    assertEquals(expected, ticket.toString());
  }

  @Test
  public void testDefaultConstructor() {
    assertNotNull(ticket);
  }
}
