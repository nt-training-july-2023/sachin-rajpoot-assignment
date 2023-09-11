package com.gms.demo.controller;

import com.gms.demo.controller.TicketController;
import com.gms.demo.exception.ResourceNotFoundException;
import com.gms.demo.payloads.TicketDto;
import com.gms.demo.payloads.TicketOutDto;
import com.gms.demo.service.TicketService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class TicketControllerTest {

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private TicketController ticketController;

    @Test
    public void testCreateTicket_ValidInput() {

        TicketDto ticketDto = new TicketDto();
        Integer memberId = 1;
        Integer departmentId = 2;
        TicketOutDto ticketOutDto = new TicketOutDto();
        when(ticketService.createTicket(ticketDto, memberId, departmentId)).thenReturn(ticketOutDto);


        ResponseEntity<TicketOutDto> response = ticketController.createTicket(ticketDto, memberId, departmentId);


        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ticketOutDto, response.getBody());
    }

//    @Test
//    public void testCreateTicket_InvalidInput() throws ValidationException {
//        // Arrange
//        TicketDto ticketDto = new TicketDto();
//        Integer memberId = 1;
//        Integer departmentId = 2;
//        doThrow(ValidationException.class).when(ticketService).createTicket(ticketDto, memberId, departmentId);
//        when(ticketService.createTicket(ticketDto, memberId, departmentId)).thenReturn(null);
//
//        ResponseEntity<TicketOutDto> response = ticketController.createTicket(ticketDto, memberId, departmentId);
//
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//    }

    @Test
    public void testGetAllTicket() {

        List<TicketOutDto> ticketOutDtoList = Arrays.asList(new TicketOutDto(), new TicketOutDto());
        when(ticketService.getAllTicket()).thenReturn(ticketOutDtoList);

     
        ResponseEntity<List<TicketOutDto>> response = ticketController.getAllTicket();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ticketOutDtoList, response.getBody());
    }
}

