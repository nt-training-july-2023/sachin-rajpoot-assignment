package com.gms.demo.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Represents a test class for the Member entity, covering Member-related
 * operations. Contains methods for testing getters, setters, and the toString()
 * method.
 *
 * @author Sachin Singh Rajpoot
 * @version 1.0
 * @since Beginning of time
 */
public class MemberTest {
	/** The Member entity instance for testing. */
	private Member member = new Member();

	/**
	 * Test method for setters and getters of the Member entity.
	 */
	@Test
	public void settersAndGetterTest() {
		member.setName("Goku");
		assertEquals("Goku", member.getName());

		member.setMemberId(1);
		assertTrue(1 == member.getMemberId());

		member.setEmail("supersaiyan@nucleusteq.com");
		assertEquals("supersaiyan@nucleusteq.com", member.getEmail());

		member.setPassword("123456788");
		assertEquals("123456788", member.getPassword());

		member.setDepartment(new Department(1, "HR", null, null));
		assertEquals(new Department(1, "HR", null, null), member.getDepartment());

		member.setRole(Role.ADMIN);
		assertEquals(Role.ADMIN, member.getRole());

		List<Ticket> tickets = new ArrayList<>();
		tickets.add(new Ticket(1, "title", "desc desc desc", null, null, Status.OPEN, TicketType.GREIVANCE, null, null,
				null));
		member.setTickets(tickets);
		assertEquals(tickets, member.getTickets());
	}

	/**
	 * Test method for generating the String representation of the Member entity.
	 */
	@Test
	public void toStringTest() {
		String expected = "Member [memberId=" + member.getMemberId() + ", name=" + member.getName() + ", email="
				+ member.getEmail() + ", password=" + member.getPassword() + ", role=" + member.getRole()
				+ ", department=" + member.getDepartment() + ", tickets=" + member.getTickets() + "]";

		assertEquals(expected, member.toString());
	}
}
