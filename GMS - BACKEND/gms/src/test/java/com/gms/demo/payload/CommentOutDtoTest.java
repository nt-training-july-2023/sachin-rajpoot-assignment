package com.gms.demo.payload;

import static org.junit.jupiter.api.Assertions.*;

import com.gms.demo.payloads.CommentOutDto;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class CommentOutDtoTest {

  private CommentOutDto commentOutDto = new CommentOutDto(
    "This is a comment",
    "kingkong",
    new Date()
  );

  @Test
  public void testGetAndSetContent() {
    commentOutDto.setContent("New content");
    assertEquals("New content", commentOutDto.getContent());
  }

  @Test
  public void testGetAndSetUserName() {
    commentOutDto.setUserName("kingkong");
    assertEquals("kingkong", commentOutDto.getUserName());
  }

  @Test
  public void testGetAndSetDate() {
    Date date = new Date();
    commentOutDto.setDate(date);
    assertEquals(date, commentOutDto.getDate());
  }

  @Test
  public void testToString() {
    String expected =
      "CommentOutDto [content=This is a comment, userName=kingkong, date=" +
      commentOutDto.getDate() +
      "]";
    assertEquals(expected, commentOutDto.toString());
  }

  @Test
  public void testDefaultConstructor() {
    assertNotNull(new CommentOutDto());
  }
}
