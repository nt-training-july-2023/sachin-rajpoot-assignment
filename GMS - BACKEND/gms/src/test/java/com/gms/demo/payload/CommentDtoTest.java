package com.gms.demo.payload;

import static org.junit.jupiter.api.Assertions.*;

import com.gms.demo.payloads.CommentDto;
import com.gms.demo.payloads.TicketDto;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class CommentDtoTest {

  private CommentDto commentDto = new CommentDto(
    1,
    "This is a comment",
    "JohnDoe",
    new Date(),
    new TicketDto()
  );

  @Test
  public void testGetAndSetCommentId() {
    commentDto.setCommentId(2);
    assertEquals(2, commentDto.getCommentId());
  }

  @Test
  public void testGetAndSetContent() {
    commentDto.setContent("New content");
    assertEquals("New content", commentDto.getContent());
  }

  @Test
  public void testGetAndSetUserName() {
    commentDto.setUserName("JaneDoe");
    assertEquals("JaneDoe", commentDto.getUserName());
  }

  @Test
  public void testGetAndSetDate() {
    Date date = new Date();
    commentDto.setDate(date);
    assertEquals(date, commentDto.getDate());
  }

  @Test
  public void testGetAndSetTicket() {
    TicketDto ticket = new TicketDto();
    commentDto.setTicket(ticket);
    assertEquals(ticket, commentDto.getTicket());
  }

  @Test
  public void testToString() {
    String expected =
      "CommentDto [commentId=1, content=This is a comment, userName=JohnDoe, date=" +
      commentDto.getDate() +
      "]";
    assertEquals(expected, commentDto.toString());
  }

  @Test
  public void testDefaultConstructor() {
    assertNotNull(new CommentDto());
  }
}
