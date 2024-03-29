package com.gms.demo.payload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


import com.gms.demo.entity.Status;
import com.gms.demo.payloads.CommentDto;
import com.gms.demo.payloads.TicketUpdateStatusInDto;

public class TicketUpdateStatusInDtoTest {
	CommentDto commentDto = new CommentDto();
	private TicketUpdateStatusInDto ticketUpdateStatusInDto1 = new TicketUpdateStatusInDto(Status.OPEN, commentDto);
	private TicketUpdateStatusInDto ticketUpdateStatusInDto2 = new TicketUpdateStatusInDto(Status.RESOLVED, commentDto);

	@Test
	public void testGettersAndSetters() {

		assertEquals(Status.OPEN, ticketUpdateStatusInDto1.getStatus());
		ticketUpdateStatusInDto1.setStatus(Status.RESOLVED);
		assertEquals(Status.RESOLVED, ticketUpdateStatusInDto1.getStatus());

		assertEquals(commentDto, ticketUpdateStatusInDto1.getComment());
		ticketUpdateStatusInDto1.setComment(commentDto);
		assertEquals(commentDto, ticketUpdateStatusInDto1.getComment());
		Integer id = 1;
		ticketUpdateStatusInDto1.setTicketId(id);
		assertEquals(id, ticketUpdateStatusInDto1.getTicketId());
	}

	@Test
	public void testHashCode() {
		assertEquals(ticketUpdateStatusInDto1.hashCode(), ticketUpdateStatusInDto1.hashCode());
		assertNotEquals(ticketUpdateStatusInDto1.hashCode(), ticketUpdateStatusInDto2.hashCode());
	}

	@Test
	public void testEquals() {
		assertTrue(ticketUpdateStatusInDto1.equals(ticketUpdateStatusInDto1));
		assertFalse(ticketUpdateStatusInDto1.equals(null));
		assertFalse(ticketUpdateStatusInDto1.equals("Not a TicketUpdateStatusInDto"));

		assertFalse(ticketUpdateStatusInDto1.equals(ticketUpdateStatusInDto2));
		assertFalse(ticketUpdateStatusInDto2.equals(ticketUpdateStatusInDto1));

		TicketUpdateStatusInDto ticketUpdateStatusInDto3 = new TicketUpdateStatusInDto(Status.OPEN, commentDto);
		assertTrue(ticketUpdateStatusInDto1.equals(ticketUpdateStatusInDto3));
		assertTrue(ticketUpdateStatusInDto3.equals(ticketUpdateStatusInDto1));
	}

	@Test
	public void testToString() {
		assertEquals("TicketUpdateStatusInDto [status=" + ticketUpdateStatusInDto1.getStatus() + ", comment="
				+ ticketUpdateStatusInDto1.getComment() + "]", ticketUpdateStatusInDto1.toString());
	}

	@Test
	public void testParameterizedConstructor() {
		assertEquals(Status.OPEN, ticketUpdateStatusInDto1.getStatus());
		assertEquals(commentDto, ticketUpdateStatusInDto1.getComment());
	}

}
