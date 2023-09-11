package com.gms.demo.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Represents a test class for the Member entity, covering Member-related
 * operations.
 * Contains methods for testing getters, setters, and the toString() method.
 *
 * @author Sachin Singh Rajpoot
 * @version 1.0
 * @since Beginning of time
 */

public class MemberTest {

  private Member member = new Member();

  //    @BeforeEach
  //    public void setUp() {
  //        member = new Member();
  //    }

  @Test
  public void testGetAndSetMemberId() {
    member.setMemberId(1);
    assertEquals((Integer) 1, member.getMemberId());
  }

  @Test
  public void testGetAndSetName() {
    member.setName("Naotami Ura");
    assertEquals("Naotami Ura", member.getName());
  }

  @Test
  public void testGetAndSetEmail() {
    member.setEmail("naotami.ura@example.com");
    assertEquals("naotami.ura@example.com", member.getEmail());
  }

  @Test
  public void testGetAndSetPassword() {
    member.setPassword("password123");
    assertEquals("password123", member.getPassword());
  }

  @Test
  public void testGetAndSetIsFirstLogin() {
    member.setIsFirstLogin(true);
    assertTrue(member.getIsFirstLogin());
  }

  @Test
  public void testGetAndSetRole() {
    member.setRole(Role.ADMIN);
    assertEquals(Role.ADMIN, member.getRole());
  }

  @Test
  public void testGetAndSetDepartment() {
    Department department = new Department();
    member.setDepartment(department);
    assertEquals(department, member.getDepartment());
  }

  @Test
  public void testGetAndSetTickets() {
    Ticket ticket1 = new Ticket();
    Ticket ticket2 = new Ticket();
    member.setTickets(new ArrayList<>(List.of(ticket1, ticket2)));
    assertEquals(List.of(ticket1, ticket2), member.getTickets());
  }

  @Test
  public void testToString() {
    member.setMemberId(1);
    member.setName("Naotami Ura");
    member.setEmail("naotami.ura@example.com");
    member.setPassword("password123");
    member.setIsFirstLogin(true);
    member.setRole(Role.ADMIN);

    String expected =
      "Member [memberId=1, name=Naotami Ura, email=naotami.ura@example.com, password=password123, isFirstLogin=true, role=ADMIN]";
    assertEquals(expected, member.toString());
  }

  @Test
  public void testDefaultConstructor() {
    assertNotNull(member);
  }
}
