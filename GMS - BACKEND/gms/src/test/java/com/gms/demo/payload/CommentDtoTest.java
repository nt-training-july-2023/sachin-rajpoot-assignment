//package com.gms.demo.payload;
//
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.Test;
//
//import com.gms.demo.payloads.CommentDto;
//import com.gms.demo.payloads.MemberLoginDto;
//
//public class CommentDtoTest {
//	/** The CommentDto entity instance for testing. */
//	private CommentDto commentDto = new CommentDto();
//	
//	@Test
//	public void setterGetterTest() {
//		commentDto.setCommentId(1);
//		assertEquals(1, commentDto.getCommentId());
//		
//		commentDto.setContent("content");
//		assertEquals("content", commentDto.getContent());
//		
//		commentDto.setDate(null);
//		assertEquals(null, commentDto.getDate());
//		
//		commentDto.setUserName("User");
//		assertEquals("User", commentDto.getUserName());
//		
//		commentDto.setTicketDto(null);
//		assertEquals(null, commentDto.getCommentId());
//		
//		CommentDto commentDto2 = new CommentDto(2, "aaaa", "name", null, null);
//		assertTrue(commentDto2 != null);
//	}
//	
//	/**
//	 * Test method for generating the String representation of the commentDto
//	 * entity.
//	 */
//	@Test
//	public void toStringTest() {
//		String expected = "CommentDto [commentId=" + commentDto.getCommentId() + ", content=" + commentDto.getContent() + ", userName=" + commentDto.getUserName() + ", date="
//                + commentDto.getDate() + ", ticketDto=" + commentDto.getTicketDto() + "]";
//
//		assertEquals(expected, commentDto.toString());
//	}
//}
