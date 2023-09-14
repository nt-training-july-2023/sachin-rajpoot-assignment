package com.gms.demo.payload;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gms.demo.payloads.MemberLoginDto;
import org.junit.Test;

/**
 * Represents a test class for the Member Login Dto entity, covering Member
 * Login-related
 * operations.
 * Contains methods for testing getters, setters, and the toString() method.
 *
 * @author Sachin Singh Rajpoot
 * @version 1.0
 * @since Beginning of time
 */

public class MemberLoginDtoTest {

  private MemberLoginDto memberLoginDto = new MemberLoginDto();

  @Test
  public void testGetAndSetEmail() {
    memberLoginDto.setEmail("kingkong@example.com");
    assertEquals("kingkong@example.com", memberLoginDto.getEmail());
  }

  @Test
  public void testGetAndSetPassword() {
    memberLoginDto.setPassword("password123");
    assertEquals("password123", memberLoginDto.getPassword());
  }

  @Test
  public void testGetMinSizePassword() {
    assertEquals(5, MemberLoginDto.getMinSizePassword());
  }

  @Test
  public void testToString() {
    memberLoginDto.setEmail("kingkong@example.com");
    memberLoginDto.setPassword("password123");

    String expected =
      "MemberLoginDto [email=kingkong@example.com, password=password123]";
    assertEquals(expected, memberLoginDto.toString());
  }

  @Test
  public void testParameterizedConstructor() {
    MemberLoginDto loginDto = new MemberLoginDto(
      "kingkong@example.com",
      "password123"
    );

    assertEquals("kingkong@example.com", loginDto.getEmail());
    assertEquals("password123", loginDto.getPassword());
  }

  @Test
  public void testDefaultConstructor() {
    assertNotNull(new MemberLoginDto());
  }
}
