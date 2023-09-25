package com.gms.demo.payload;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import com.gms.demo.entity.Status;
import com.gms.demo.entity.TicketType;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.TicketDto;
import com.gms.demo.payloads.TicketGetAllOutDto;

public class TicketGetAllOutDtoTest {
	  private TicketGetAllOutDto getAllOutDto = new TicketGetAllOutDto();
	  private TicketGetAllOutDto getAllOutDto2 = new TicketGetAllOutDto();
	  

	  @Test
	  public void testGetAndSetTicketId() {
		  getAllOutDto.setTicketId(1);
	    assertEquals(1, getAllOutDto.getTicketId());
	  }

	  @Test
	  public void testGetAndSetTitle() {
		  getAllOutDto.setTitle("Sample Ticket");
	    assertEquals("Sample Ticket", getAllOutDto.getTitle());
	  }

	  @Test
	  public void testGetAndSetDescription() {
		  getAllOutDto.setDescription("This is a sample ticket description.");
	    assertEquals(
	      "This is a sample ticket description.",
	      getAllOutDto.getDescription()
	    );
	  }

	  @Test
	  public void testGetAndSetCreatedOn() {
	    Date createdOn = new Date();
	    getAllOutDto.setCreatedOn(createdOn);
	    assertEquals(createdOn, getAllOutDto.getCreatedOn());
	  }

	  @Test
	  public void testGetAndSetLastUpdatedOn() {
	    Date lastUpdatedOn = new Date();
	    getAllOutDto.setLastUpdatedOn(lastUpdatedOn);
	    assertEquals(lastUpdatedOn, getAllOutDto.getLastUpdatedOn());
	  }

	  @Test
	  public void testGetAndSetStatus() {
		  getAllOutDto.setStatus(Status.OPEN);
	    assertEquals(Status.OPEN, getAllOutDto.getStatus());
	  }

	  @Test
	  public void testGetAndSetTicketType() {
		  getAllOutDto.setTicketType(TicketType.FEEDBACK);
	    assertEquals(TicketType.FEEDBACK, getAllOutDto.getTicketType());
	  }

	  @Test
	  public void testGetAndSetDepartment() {
	    getAllOutDto.setDepartmentName("IT Department");
	    assertEquals("IT Department", getAllOutDto.getDepartmentName());
	  }

	  @Test
	  public void testGetAndSetMember() {
	    getAllOutDto.setMemberName("kingkong");
	    assertEquals("kingkong", getAllOutDto.getMemberName());
	  }

	  @Test
	  public void testToString() {
		  getAllOutDto.setTicketId(1);
		  getAllOutDto.setTitle("Sample Ticket");
		  getAllOutDto.setDescription("This is a sample ticket description.");
		  getAllOutDto.setStatus(Status.OPEN);
		  getAllOutDto.setTicketType(TicketType.FEEDBACK);
		  getAllOutDto.setDepartmentName("IT Department");
		  getAllOutDto.setMemberName("kingkong");
	    String expected =
	    		"TicketGetAllOutDto [ticketId=" +
	    				getAllOutDto.getTicketId() +
	    	      ", title=" +
	    	      getAllOutDto.getTitle() +
	    	      ", description=" +
	    	      getAllOutDto.getDescription() +
	    	      ", createdOn=" +
	    	      getAllOutDto.getCreatedOn() +
	    	      ", lastUpdatedOn=" +
	    	      getAllOutDto.getLastUpdatedOn() +
	    	      ", status=" +
	    	      getAllOutDto.getStatus() +
	    	      ", ticketType=" +
	    	      getAllOutDto.getTicketType() +
	    	      ", departmentName=" +
	    	      getAllOutDto.getDepartmentName() +
	    	      ", memberName=" +
	    	      getAllOutDto.getMemberName() +
	    	      "]";
	    assertEquals(expected, getAllOutDto.toString());
	  }


	  @Test
	  public void testDefaultConstructor() {
	    assertNotNull(new TicketGetAllOutDto());
	  }
	  
	   @Test
	    public void testEqual() {
		   getAllOutDto.setTicketId(1);
			  getAllOutDto.setTitle("Sample Ticket");
			  getAllOutDto.setDescription("This is a sample ticket description.");
			  getAllOutDto.setStatus(Status.OPEN);
			  getAllOutDto.setTicketType(TicketType.FEEDBACK);
			  getAllOutDto.setDepartmentName("IT Department");
			  getAllOutDto.setMemberName("kingkong");
			  
			  getAllOutDto2.setTicketId(1);
			  getAllOutDto2.setTitle("Sample Ticket");
			  getAllOutDto2.setDescription("This is a sample ticket description.");
			  getAllOutDto2.setStatus(Status.OPEN);
			  getAllOutDto2.setTicketType(TicketType.FEEDBACK);
			  getAllOutDto2.setDepartmentName("IT Department");
			  getAllOutDto2.setMemberName("kingkong");
	       

	        assertEquals(getAllOutDto, getAllOutDto2);
	    }
	   

	    @Test
	    public void testHashCode() {
	        TicketGetAllOutDto dto1 = new TicketGetAllOutDto();
	        TicketGetAllOutDto dto2 = new TicketGetAllOutDto();
	        assertEquals(dto1.hashCode(), dto2.hashCode());
	    }
}
