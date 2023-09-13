package com.gms.demo.service;

import java.util.List;

import com.gms.demo.payloads.TicketDto;

public interface TicketService {
	
	TicketDto createTicket(final TicketDto ticketDto, final Integer memberId);
	
	List<TicketDto> getAllTicket();
	
//	List<TicketDto> getAllTicketByDepartment(final Integer departmentId);
}
