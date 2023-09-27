package com.gms.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import org.junit.jupiter.api.Test;

public class CommentTest {

  private Comment comment = new Comment();
  Comment comment2 = new Comment(1, "test", "content", 
		  new Date(), new Ticket());

  @Test
  public void testGetAndSetCommentId() {
    comment.setCommentId(1);
    assertEquals(1, comment.getCommentId());
  }

  @Test
  public void testGetAndSetContent() {
    comment.setContent("This is a sample comment.");
    assertEquals("This is a sample comment.", comment.getContent());
  }

  @Test
  public void testGetAndSetUserName() {
    comment.setUserName("kingkong");
    assertEquals("kingkong", comment.getUserName());
  }

  @Test
  public void testGetAndSetDate() {
    Date date = new Date();
    comment.setDate(date);
    assertEquals(date, comment.getDate());
  }

  @Test
  public void testGetAndSetTicket() {
    Ticket ticket = new Ticket();
    comment.setTicket(ticket);
    assertEquals(ticket, comment.getTicket());
  }

  @Test
  public void testToString() {
    comment.setCommentId(1);
    comment.setContent("This is a sample comment.");
    comment.setUserName("kingkong");

    String expected =
      "Comment [commentId=1, content=This is a sample comment., userName=kingkong, date=null]";
    assertEquals(expected, comment.toString());
  }

  @Test
  public void testDefaultConstructor() {
    assertNotNull(comment);
  }
}
