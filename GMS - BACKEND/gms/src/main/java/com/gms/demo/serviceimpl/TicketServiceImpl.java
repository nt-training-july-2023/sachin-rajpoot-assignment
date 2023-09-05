package com.gms.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gms.demo.entity.Department;
import com.gms.demo.entity.Member;
import com.gms.demo.entity.Role;
import com.gms.demo.entity.Ticket;
import com.gms.demo.exception.ResourceNotFoundException;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.TicketDto;
import com.gms.demo.payloads.TicketOutDto;
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
	public TicketOutDto createTicket(final TicketDto ticketDto, final Integer memberId, final Integer departmentId) {
		System.out.println("entered in create method");

		Member member = this.memberRepo.findById(memberId)
				.orElseThrow(() -> new ResourceNotFoundException("Member", "member ID", memberId));
//		System.out.println(member);
		
		MemberDto memberDto = this.modelMapper.map(member, MemberDto.class);
//		System.out.println(memberDto);
		
		Department department = this.departmentRepo.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "department ID", departmentId));
//		System.out.println("ddddddddddd");
		
		DepartmentDto departmentDto = this.modelMapper.map(department, DepartmentDto.class);

//		System.out.println(departmentDto);
		
		ticketDto.setMember(memberDto);
		ticketDto.setDepartment(departmentDto);
		
//		System.out.println(ticketDto);
		
		Ticket ticket = this.modelMapper.map(ticketDto, Ticket.class);
		Ticket savedTicket = this.ticketRepo.save(ticket);
//		System.out.println(savedTicket);
//		return this.modelMapper.map(, TicketDto.class);
		return new TicketOutDto(savedTicket.getTitle(), savedTicket.getDescription(), savedTicket.getCreatedOn(),
				savedTicket.getLastUpdatedOn(), savedTicket.getStatus(), savedTicket.getTicketType(),
				savedTicket.getDepartment().getName(), savedTicket.getMember().getName());
	}

	@Override
	public List<TicketOutDto> getAllTicket() {
		List<Ticket> tickets = this.ticketRepo.findAll();
		List<TicketOutDto> ticketOutDtos = new ArrayList<>();
		tickets.forEach(ticket -> ticketOutDtos.add(new TicketOutDto(ticket.getTitle(), ticket.getDescription(),
				ticket.getCreatedOn(), ticket.getLastUpdatedOn(), ticket.getStatus(), ticket.getTicketType(),
				ticket.getDepartment().getName(), ticket.getMember().getName())));
//		tickets.forEach(ticket -> ticketDtos.add(new TicketDto(ticket.getDepartment().getName())));
		return ticketOutDtos;
	}
//
//	@Override
//	public TicketDto createTicket2(TicketDto ticketDto, Integer memberId, Integer departmentId, String email,
//			String password) {
//		System.out.println("inside service");
//		Member member = this.memberRepo.findByEmail(email);
//		if (member != null) {
//			System.out.println("inside 1");
//			System.out.println("Role " + member.getRole());
//			System.out.println(member.getRole().equals(Role.ADMIN));
//			if (member.getPassword().equals(password) && member.getRole() != null
//					&& member.getRole().equals(Role.ADMIN)) {
//				System.out.println("Hail Admin!!!!!!!!!");
//				System.out.println("This is what you've created" + ticketDto);
//				
//				Member member2 = this.memberRepo.findById(memberId)
//						.orElseThrow(() -> new ResourceNotFoundException("Member", "member ID", memberId));
//				MemberDto memberDto = this.modelMapper.map(member2, MemberDto.class);
//
//				Department department = this.departmentRepo.findById(departmentId)
//						.orElseThrow(() -> new ResourceNotFoundException("Department", "department ID", departmentId));
//				DepartmentDto departmentDto = this.modelMapper.map(department, DepartmentDto.class);
//
//				ticketDto.setMember(memberDto);
//				ticketDto.setDepartment(departmentDto.getName());
//
//				Ticket ticket = this.modelMapper.map(ticketDto, Ticket.class);
//				return this.modelMapper.map(this.ticketRepo.save(ticket), TicketDto.class);
//				
//			}
//		}
//		return null;
//	}

//	@Override
//	public List<TicketDto> getAllTicketByDepartment(final Integer departmentId) {
//		Department department = 
//	}

}
