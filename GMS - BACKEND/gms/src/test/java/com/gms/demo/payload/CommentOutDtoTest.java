package com.gms.demo.payload;

import static org.junit.jupiter.api.Assertions.*;

import com.gms.demo.payloads.CommentOutDto;
import java.util.Date;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class CommentOutDtoTest {
	
	 Integer commentId=1;
	 String content = "This is a comment";
	 Date date = new Date();
	 String userName = "kingkong";

  private CommentOutDto commentOutDto = new CommentOutDto(
   content,
    userName,
    date
  );
  
  private CommentOutDto commentOutDto2 = new CommentOutDto(
		  	commentId,
		  	content,
		    userName,
		    date
		  );

  private CommentOutDto commentOutDto3 = new CommentOutDto(
		  	commentId,
		  	content,
		    userName,
		    date
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
  
  @Test
  public void testHashCodeMethod() {
	 int receivedValue = Objects.hash(commentId, content, date, userName);
	 int expected = this.commentOutDto2.hashCode();
	 assertEquals(expected, receivedValue);
  }
  
  @Test
  public void testEqualsMethod() {
	  assertTrue(this.commentOutDto2.equals(commentOutDto3));
  }
}
