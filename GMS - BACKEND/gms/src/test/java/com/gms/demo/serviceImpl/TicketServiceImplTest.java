package com.gms.demo.serviceImpl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.gms.demo.entity.Department;
import com.gms.demo.entity.Member;
import com.gms.demo.entity.Role;
import com.gms.demo.entity.Status;
import com.gms.demo.entity.Ticket;
import com.gms.demo.entity.TicketType;
import com.gms.demo.payloads.TicketDto;
import com.gms.demo.payloads.TicketOutDto;
import com.gms.demo.repo.DepartmentRepo;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.repo.TicketRepo;
import com.gms.demo.service.DepartmentService;
import com.gms.demo.serviceimpl.MemberServiceImpl;
import com.gms.demo.serviceimpl.TicketServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class TicketServiceImplTest {
	@Mock
	private MemberRepo memberRepo;
	
	@Mock
	private TicketRepo ticketRepo;


	@Mock
	private DepartmentService departmentService;

	@Mock
	private DepartmentRepo departmentRepo;

	@InjectMocks
	private TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();

	@Mock
	private ModelMapper modelMapper;
	
	
	@Test
	public void createTicketTest() {
		
		TicketOutDto ticketOutDto = new TicketOutDto();
		ticketOutDto.setTitle("test");
		ticketOutDto.setCreatedOn(null);
		ticketOutDto.setDescription("test");
		ticketOutDto.setLastUpdatedOn(null);
		ticketOutDto.setStatus(Status.OPEN);
		ticketOutDto.setTicketType(TicketType.FEEDBACK);
		
		
		TicketDto ticketDto = new TicketDto();
		ticketDto.setTitle("test");
		ticketDto.setCreatedOn(null);
		ticketDto.setDepartment(null);
		ticketDto.setDescription("test");
		ticketDto.setLastUpdatedOn(null);
		ticketDto.setMember(null);
		ticketDto.setStatus(Status.OPEN);
		ticketDto.setTicketId(1);
		ticketDto.setTicketType(TicketType.FEEDBACK);
		
		Ticket ticket = new Ticket();
		ticket.setTitle("test");
		ticket.setCreatedOn(null);
		ticket.setDepartment(null);
		ticket.setDescription("test");
		ticket.setLastUpdatedOn(null);
		ticket.setMember(null);
		ticket.setStatus(Status.OPEN);
		ticket.setTicketId(1);
		ticket.setTicketType(TicketType.FEEDBACK);
		
		Integer memberId = 2;
		Integer departmentId = 3;
		
		Department department = new Department();
		department.setDepartmentId(departmentId);
		department.setMembers(null);
		department.setName("test");
		department.setTickets(null);
		
		Member member = new Member() ;
		member.setMemberId(memberId);
		member.setDepartment(null); 
		member.setEmail("test@nucleusteq.com");
		member.setPassword("12345678");
		member.setIsFirstLogin(true);
		member.setName("test");
		member.setRole(Role.USER);
		member.setTickets(null);
		
		Optional<Member> m = java.util.Optional.of(member) ;
		Optional<Department> d = java.util.Optional.of(department) ;
		
		
		
		when(memberRepo.findById(memberId)).thenReturn(m);
		
		when(departmentRepo.findById(departmentId)).thenReturn(d);
		when(this.modelMapper.map(ticketDto, Ticket.class)).thenReturn(ticket);
		
		ticket.setDepartment(department);
		ticket.setMember(member);
		
		when(ticketRepo.save(ticket)).thenReturn(ticket);
		
		when(this.modelMapper.map(ticket, TicketOutDto.class)).thenReturn(ticketOutDto);
		
		assertNotNull(this.ticketServiceImpl.createTicket(ticketDto, memberId, departmentId));
	}
	
	@Test
	public void getAllTest() {
		List<Ticket> tickets = new ArrayList<>();
		Ticket ticket = new Ticket();
		ticket.setTitle("test");
		ticket.setCreatedOn(null);
		ticket.setDepartment(null);
		ticket.setDescription("test");
		ticket.setLastUpdatedOn(null);
		ticket.setMember(null);
		ticket.setStatus(Status.OPEN);
		ticket.setTicketId(1);
		ticket.setTicketType(TicketType.FEEDBACK);
		
		tickets.add(ticket);
		
	    List<TicketOutDto> ticketOutDtos = new ArrayList<>();
	    TicketOutDto ticketOutDto = new TicketOutDto();
	    ticketOutDto.setTitle("test");
	    ticketOutDto.setCreatedOn(null);
	    ticketOutDto.setDescription("test");
	    ticketOutDto.setLastUpdatedOn(null);
	    ticketOutDto.setStatus(Status.OPEN);
	    ticketOutDto.setTicketType(TicketType.FEEDBACK);
	    ticketOutDtos.add(ticketOutDto);
	    
	    when(ticketRepo.findAll()).thenReturn(tickets);
	    when(modelMapper.map(ticket, TicketOutDto.class)).thenReturn(ticketOutDto);
	    
	    assertNotNull(ticketServiceImpl.getAllTicket());
	    
	    
	    
	}
	
	@Test
	public void updateTicket_Test() {
		
		Integer ticketId = 1;
		 Integer memberId = 2;
	     Integer departmentId = 3;
	     
			Department department = new Department();
			department.setDepartmentId(departmentId);
			department.setMembers(null);
			department.setName("test");
			department.setTickets(null);
			
			Member member = new Member() ;
			member.setMemberId(memberId);
			member.setDepartment(null); 
			member.setEmail("test@nucleusteq.com");
			member.setPassword("12345678");
			member.setIsFirstLogin(true);
			member.setName("test");
			member.setRole(Role.USER);
			member.setTickets(null);
			
			TicketOutDto ticketOutDto = new TicketOutDto();
			ticketOutDto.setTitle("test");
			ticketOutDto.setCreatedOn(null);
			ticketOutDto.setDescription("test");
			ticketOutDto.setLastUpdatedOn(null);
			ticketOutDto.setStatus(Status.OPEN);
			ticketOutDto.setTicketType(TicketType.FEEDBACK);
			
			TicketDto ticketDto = new TicketDto();
			ticketDto.setTitle("test");
			ticketDto.setCreatedOn(null);
			ticketDto.setDepartment(null);
			ticketDto.setDescription("test");
			ticketDto.setLastUpdatedOn(null);
			ticketDto.setMember(null);
			ticketDto.setStatus(Status.OPEN);
			ticketDto.setTicketId(ticketId);
			ticketDto.setTicketType(TicketType.FEEDBACK);
			
			Ticket ticket = new Ticket();
			ticket.setTitle("test");
			ticket.setCreatedOn(null);
			ticket.setDepartment(null);
			ticket.setDescription("test");
			ticket.setLastUpdatedOn(null);
			ticket.setMember(null);
			ticket.setStatus(Status.OPEN);
			ticket.setTicketId(ticketId);
			ticket.setTicketType(TicketType.FEEDBACK);
			
			Optional<Member> m = java.util.Optional.of(member) ;
			Optional<Department> d = java.util.Optional.of(department) ;
			
			
			
			when(memberRepo.findById(memberId)).thenReturn(m);
			
			when(departmentRepo.findById(departmentId)).thenReturn(d);
			
			when(ticketRepo.findById(ticketId)).thenReturn(Optional.of(ticket));
			
			ticket.setDepartment(department);
			ticket.setMember(member);
			
			when(ticketRepo.save(ticket)).thenReturn(ticket);
			
			when(this.modelMapper.map(ticket, TicketOutDto.class)).thenReturn(ticketOutDto);
			
			assertNotNull(this.ticketServiceImpl.updateTicket(ticketDto, ticketId, memberId, departmentId));
			
	    
	}

}
