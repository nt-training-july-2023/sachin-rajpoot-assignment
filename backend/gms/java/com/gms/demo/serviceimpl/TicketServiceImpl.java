package com.gms.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gms.demo.entity.Department;
import com.gms.demo.entity.Member;
import com.gms.demo.entity.Ticket;
import com.gms.demo.exception.ResourceNotFoundException;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.TicketDto;
import com.gms.demo.repo.DepartmentRepo;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.repo.TicketRepo;
import com.gms.demo.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepo ticketRepo;

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	MemberRepo memberRepo;
	
	@Autowired
	DepartmentRepo departmentRepo;
	

	@Override
	public TicketDto createTicket(TicketDto ticketDto,final Integer memberId, final Integer departmentId) {
		Member member = this.memberRepo.findById(memberId).orElseThrow(()-> new ResourceNotFoundException("Member", "member ID", memberId));
		MemberDto memberDto = this.modelMapper.map(member, MemberDto.class);
		
		Department department = this.departmentRepo.findById(departmentId).orElseThrow(()-> new ResourceNotFoundException("Department", "department ID", departmentId));
		DepartmentDto departmentDto = this.modelMapper.map(department, DepartmentDto.class);
		
		ticketDto.setMember(memberDto); 
		ticketDto.setDepartment(departmentDto);
		
		Ticket ticket = this.modelMapper.map(ticketDto, Ticket.class);
		return this.modelMapper.map(this.ticketRepo.save(ticket), TicketDto.class);
	}

	@Override
	public List<TicketDto> getAllTicket() {
		List<Ticket> tickets = this.ticketRepo.findAll();
		List<TicketDto> ticketDtos = new ArrayList<>();
		tickets.forEach( ticket -> ticketDtos.add(this.modelMapper.map(ticket, TicketDto.class)));
		return ticketDtos;
	}

//	@Override
//	public List<TicketDto> getAllTicketByDepartment(final Integer departmentId) {
//		Department department = 
//	}

}
