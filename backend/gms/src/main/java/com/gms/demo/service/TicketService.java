package com.gms.demo.service;

import com.gms.demo.entity.Status;
import com.gms.demo.payloads.TicketDto;
import com.gms.demo.payloads.TicketGetAllOutDto;
import com.gms.demo.payloads.TicketOutDto;
import com.gms.demo.payloads.TicketUpdateStatusInDto;
import java.util.List;

/**
 * Service Implementation for ticket.
 */
public interface TicketService {
  /**
   * Creates a new ticket based on the provided ticket DTO, member ID, and
   * department ID.
   *
   * @param ticketDto    The DTO containing ticket information.
   * @param memberId     The unique identifier of the member creating
   *     the ticket.
   * @param departmentId The unique identifier of the department to which the
   *                     ticket belongs.
   * @return The created ticket as a TicketOutDto if successful, otherwise null.
   */
  TicketOutDto createTicket(
      TicketDto ticketDto,
      Integer memberId,
      Integer departmentId
  );

  /**
   * get All Ticket.
   *
   * @param memberId member Id
   * @param myTickets my Tickets
   * @param filter filter
   * @param pageNumber page Number
   * @param adminDept adminDept
   * @return TicketGetAllOutDto
   *     Ticket Get All Out Dto
   */
  List<TicketGetAllOutDto> getAllTicketAuth(
      Integer memberId,
      boolean myTickets,
      Status filter,
      Integer pageNumber,
      boolean adminDept
  );

  /**
   * Updates a tickets.
   *
   *@param ticketDto ticket Dto
   *@param ticketId ticket Id
   *
   * @return Updated TicketOutDto.
   */
  TicketOutDto updateTicket(
      TicketUpdateStatusInDto ticketDto,
      Integer ticketId
  );

  /**
   * get Ticket Bt Id.
   *
   * @param ticketId ticket Id
   * @return TicketOutDto Ticket Out Dto
   */
  TicketOutDto getTicketBtId(Integer ticketId);
}
