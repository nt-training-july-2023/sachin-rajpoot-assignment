package com.gms.demo.controller;

import com.gms.demo.entity.Status;
import com.gms.demo.payloads.TicketDto;
import com.gms.demo.payloads.TicketGetAllOutDto;
import com.gms.demo.payloads.TicketOutDto;
import com.gms.demo.payloads.TicketUpdateStatusInDto;
import com.gms.demo.service.TicketService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The TicketController class handles API endpoints related to ticket
 * operations. This controller provides functionality for creating and
 * retrieving tickets.
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
   * @return ResponseEntity containing the newly created TicketOutDto with HTTP
   *         status 201 (Created).
   */
  @CrossOrigin
  @PostMapping("create/memberId/{memberId}/departmentId/{departmentId}")
  public ResponseEntity<?> createTicket(
    @Valid @RequestBody final TicketDto ticketDto,
    @PathVariable final Integer memberId,
    @PathVariable final Integer departmentId
  ) {
    System.out.println("INSIDE CREATE MEMBER");
    System.out.println(ticketDto.getStatus().equals(Status.OPEN));
    return new ResponseEntity<>(
      this.ticketService.createTicket(ticketDto, memberId, departmentId),
      HttpStatus.CREATED
    );
  }

  /**
   * Retrieves a list of authenticated tickets with optional filtering and pagination.
   *
   * @param memberId    The ID of the member for whom tickets are retrieved.
   * @param filter      The status filter applied to the tickets.
   * @param pageNumber  The page number for pagination.
   * @param myTickets   Flag indicating whether to retrieve only the member's tickets.
   * @param adminDept   Flag indicating whether to include tickets from admin department.
   * @return A ResponseEntity containing a list of TicketGetAllOutDto objects and an HTTP status code.
   */
  @CrossOrigin
  @GetMapping(
    "getAll/auth/memberId/{memberId}/filter/{filter}/pageNumber/{pageNumber}"
  )
  public final ResponseEntity<List<TicketGetAllOutDto>> getAllTicketAuth(
    @PathVariable final Integer memberId,
    @PathVariable final Status filter,
    @PathVariable final Integer pageNumber,
    @RequestParam(required = false) final boolean myTickets,
    @RequestParam(required = false) final boolean adminDept
  ) {
    System.out.println(myTickets + "          " + adminDept);
    return new ResponseEntity<List<TicketGetAllOutDto>>(
      this.ticketService.getAllTicketAuth(
          memberId,
          myTickets,
          filter,
          pageNumber,
          adminDept
        ),
      HttpStatus.OK
    );
  }

  /**
   * Updates the status of a ticket with a specified ID.
   *
   * @param ticketDto The DTO containing the updated ticket status information.
   * @param ticketId  The ID of the ticket to be updated.
   * @return A ResponseEntity containing the updated TicketOutDto and an HTTP status code.
   */
  @CrossOrigin
  @PutMapping("update/ticketId/{ticketId}")
  public final ResponseEntity<TicketOutDto> updateTicket(
    @Valid @RequestBody final TicketUpdateStatusInDto ticketDto,
    @PathVariable final Integer ticketId
  ) {
    return new ResponseEntity<TicketOutDto>(
      this.ticketService.updateTicket(ticketDto, ticketId),
      HttpStatus.OK
    );
  }

  /**
   * Retrieves a ticket by its ID.
   *
   * @param ticketId The ID of the ticket to be retrieved.
   * @return A ResponseEntity containing the retrieved TicketOutDto and an HTTP status code.
   */
  @CrossOrigin
  @GetMapping("getbyId/ticketId/{ticketId}")
  public final ResponseEntity<TicketOutDto> getTicketById(
    @PathVariable @Valid final Integer ticketId
  ) {
    return new ResponseEntity<TicketOutDto>(
      this.ticketService.getTicketBtId(ticketId),
      HttpStatus.OK
    );
  }
}
