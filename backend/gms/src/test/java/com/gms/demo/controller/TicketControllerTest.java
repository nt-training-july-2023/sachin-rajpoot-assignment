package com.gms.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gms.demo.entity.Status;
import com.gms.demo.payloads.TicketDto;
import com.gms.demo.payloads.TicketGetAllOutDto;
import com.gms.demo.payloads.TicketOutDto;
import com.gms.demo.payloads.TicketUpdateStatusInDto;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.service.MemberService;
import com.gms.demo.service.TicketService;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class TicketControllerTest {

	@Mock
	private TicketService ticketService;

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private TicketController ticketController;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
	}

	TicketDto ticketDto = new TicketDto();
	TicketOutDto ticketOutDto = new TicketOutDto();
	TicketUpdateStatusInDto ticketUpdateStatusInDto = new TicketUpdateStatusInDto();
	List<TicketGetAllOutDto> allOutDtos = new ArrayList<>();

	Integer memberId = 1;
	Integer departmentId = 2;

	@Test
	public void testCreateTicket_ValidInput() throws JsonProcessingException, Exception {
		ticketDto.setStatus(Status.OPEN);
		when(ticketService.createTicket(ticketDto, memberId, departmentId)).thenReturn(ticketOutDto);
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/ticket/create/memberId/{memberId}/departmentId/{departmentId}", memberId, departmentId)
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(ticketDto))
				.accept(MediaType.APPLICATION_JSON));
		ResponseEntity<?> response = ticketController.createTicket(ticketDto, memberId, departmentId);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ticketOutDto, response.getBody());
	}

	@Test
	public void testGetAllTicket() throws Exception {

		List<TicketGetAllOutDto> ticketOutDtoList = Arrays.asList(new TicketGetAllOutDto(), new TicketGetAllOutDto());
		when(ticketService.getAllTicketAuth(memberId, false, Status.OPEN, 0, false)).thenReturn(ticketOutDtoList);
		mockMvc.perform(
				MockMvcRequestBuilders
						.post("/api/ticket\"getAll/auth/memberId/{memberId}/filter/{filter}/pageNumber/{pageNumber}",
								memberId, Status.OPEN, 0)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		ResponseEntity<List<TicketGetAllOutDto>> response = ticketController.getAllTicketAuth(memberId, Status.OPEN, 0,
				false, false);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ticketOutDtoList, response.getBody());
	}

	@Test
	public void updateTicket_Test() throws Exception {
		Integer ticketId = 1;
		TicketDto ticketDto = new TicketDto();
		ticketDto.setTicketId(ticketId);
		TicketOutDto ticketOutDto = new TicketOutDto();
		ticketOutDto.setTicketId(ticketId);
		when(this.ticketService.updateTicket(ticketUpdateStatusInDto, ticketId)).thenReturn(ticketOutDto);
		mockMvc.perform(MockMvcRequestBuilders.put("/api/update/ticketId/{ticketId}", ticketId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		ResponseEntity<TicketOutDto> updatedTicket = this.ticketController.updateTicket(ticketUpdateStatusInDto,
				ticketId);
		assertNotNull(updatedTicket);
	}

	@Test
   public void testgetticketById() throws Exception {
	   when(this.ticketService.getTicketBtId(1)).thenReturn(ticketOutDto);
	   mockMvc
	      .perform(
	        MockMvcRequestBuilders
	          .put("/api/getbyId/ticketId/{ticketId}",1)
	          .contentType(MediaType.APPLICATION_JSON)
	          .accept(MediaType.APPLICATION_JSON)
	      );	    
	    ResponseEntity<TicketOutDto> updatedTicket = this.ticketController.getTicketById(1);
	    assertNotNull(updatedTicket);    
   }

}
