package com.gms.demo.controller;

import com.gms.demo.payloads.TicketDto;
import com.gms.demo.payloads.TicketOutDto;
import com.gms.demo.service.TicketService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The TicketController class handles API endpoints related to ticket operations.
 * This controller provides functionality for creating and retrieving tickets.
 */
@RestController
@RequestMapping("/api/ticket/")
public class TicketController {

  /**
   * The Ticket Service instance for handling ticket-related operations.
   */
  @Autowired
  private TicketService ticketService;

  /**
   * Creates a new ticket associated with a member and a department.
   *
   * @param ticketDto    The data transfer object containing ticket information.
   * @param memberId     The ID of the member associated with the ticket.
   * @param departmentId The ID of the department associated with the ticket.
   * @return ResponseEntity containing the newly created TicketOutDto with HTTP status 201 (Created).
   */
  @CrossOrigin
  @PostMapping("create/memberId/{memberId}/departmentId/{departmentId}")
  public ResponseEntity<TicketOutDto> createTicket(
    @Valid @RequestBody final TicketDto ticketDto,
    @PathVariable final Integer memberId,
    @PathVariable final Integer departmentId
  ) {
    return new ResponseEntity<>(
      this.ticketService.createTicket(ticketDto, memberId, departmentId),
      HttpStatus.CREATED
    );
  }

  /**
   * Retrieves a list of all tickets.
   *
   * @return ResponseEntity containing a list of TicketOutDto objects with HTTP status 200 (OK).
   */
  @CrossOrigin
  @GetMapping("getAll")
  public ResponseEntity<List<TicketOutDto>> getAllTicket() {
    return new ResponseEntity<>(
      this.ticketService.getAllTicket(),
      HttpStatus.OK
    );
  }
  
  @CrossOrigin
  @PutMapping("update/memberId/{memberId}/departmentId/{departmentId}/ticketId/{ticketId}")
  public ResponseEntity<TicketOutDto> updateTicket(@Valid @RequestBody final TicketDto ticketDto,
		  @PathVariable final Integer ticketId,
		    @PathVariable final Integer memberId,
		    @PathVariable final Integer departmentId
		  ){
	  return new ResponseEntity<TicketOutDto>(this.ticketService.updateTicket(ticketDto, ticketId, memberId,departmentId),HttpStatus.OK);
			  
  }
}