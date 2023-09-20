package com.gms.demo.service;

import com.gms.demo.entity.Status;
import com.gms.demo.payloads.TicketDto;
import com.gms.demo.payloads.TicketOutDto;
import java.util.List;

public interface TicketService {
  /**
   * Creates a new ticket based on the provided ticket DTO, member ID, and department ID.
   *
   * @param ticketDto    The DTO containing ticket information.
   * @param memberId     The unique identifier of the member creating the ticket.
   * @param departmentId The unique identifier of the department to which the ticket belongs.
   * @return The created ticket as a TicketOutDto if successful, otherwise null.
   */
  TicketOutDto createTicket(
    TicketDto ticketDto,
    Integer memberId,
    Integer departmentId
  );


  
  List<TicketOutDto> getAllTicketAuth(Integer memberId, boolean myTickets, Status filter, Integer pageNumber, boolean adminDept);
  
  /**
   * Updates a tickets.
   *
   * @return Updated TicketOutDto.
   */
  TicketOutDto updateTicket(TicketDto ticketDto, Integer ticketId,     final Integer memberId,
		    final Integer departmentId);
  
  TicketOutDto getTicketBtId(Integer ticketId);
 
}
