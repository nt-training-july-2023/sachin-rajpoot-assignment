//package com.gms.demo.payload;
//
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.Test;
//
//import com.gms.demo.payloads.MemberLoginDto;
//import com.gms.demo.payloads.TicketDto;
//
//public class TicketDtoTest {
//	/** The TicketDto entity instance for testing. */
//	private TicketDto ticketDto = new TicketDto();
//	
//	/**
//	 * Test method for setters and getters of the TicketDto entity.
//	 */
//	@Test
//	public void settersAndGetterTest() {
//		ticketDto.setTicketId(1);
//		assertEquals(1, ticketDto.getTicketId());
//		
//		ticketDto.setCreatedOn(null);
//		assertEquals(null, ticketDto.getCreatedOn());
//		
//		ticketDto.setDepartment(null);
//		assertEquals(null, ticketDto.getDepartment());
//		
//		ticketDto.setDescription("desc");
//		assertEquals("desc", ticketDto.getDescription());
//		
//		ticketDto.setLastUpdatedOn(null);
//		assertEquals(null, ticketDto.getLastUpdatedOn());
//		
//		ticketDto.setMember(null);
//		assertEquals(null, ticketDto.getMember());
//		
//		
//		ticketDto.setTicketType(null);
//		assertEquals(null, ticketDto.getTicketType());
//		
//		ticketDto.setTitle("title");
//		assertEquals("title", ticketDto.getTitle());
//		
//		TicketDto ticketDto2 = new TicketDto(2, "aaa", "desc2", null, null, null, null, null, null);
//		assertTrue(ticketDto2 != null);
//		
//		
//	}
//	
//	
//	/**
//	 * Test method for generating the String representation of the ticketDto
//	 * entity.
//	 */
//	@Test
//	public void toStringTest() {
//		String expected = "TicketDto [ticketId=" + ticketDto.getTicketId() + ", title=" + ticketDto.getTitle() + ", description=" + ticketDto.getDescription() + ", createdOn="
//				+ ticketDto.getCreatedOn() + ", lastUpdatedOn=" + ticketDto.getLastUpdatedOn() + ", status=" + ticketDto.getStatus() + ", ticketType=" + ticketDto.getTicketType()
//				+ ", departmentDto=" + ticketDto.getDepartment() + ", memberDto=" + ticketDto.getMember() + "]";
//
//		assertEquals(expected, ticketDto.toString());
//	}
//}
