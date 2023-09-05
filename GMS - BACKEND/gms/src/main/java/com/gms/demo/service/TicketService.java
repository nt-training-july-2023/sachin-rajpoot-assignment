package com.gms.demo.service;

import java.util.List;

import com.gms.demo.payloads.TicketDto;
import com.gms.demo.payloads.TicketOutDto;

public interface TicketService {
	
	TicketOutDto createTicket(final TicketDto ticketDto, final Integer memberId, final Integer departmentId);
	
//	TicketDto createTicket2(final TicketDto ticketDto, final Integer memberId, final Integer departmentId, String email, String password);
	
	List<TicketOutDto> getAllTicket();
	
//	List<TicketDto> getAllTicketByDepartment(final Integer departmentId);
}
