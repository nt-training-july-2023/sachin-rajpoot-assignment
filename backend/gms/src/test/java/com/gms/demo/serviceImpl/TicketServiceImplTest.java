package com.gms.demo.serviceImpl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.gms.demo.entity.Department;
import com.gms.demo.entity.Member;
import com.gms.demo.entity.Role;
import com.gms.demo.entity.Status;
import com.gms.demo.entity.Ticket;
import com.gms.demo.entity.TicketType;
import com.gms.demo.exception.ResourceNotFoundException;
import com.gms.demo.payloads.CommentDto;
import com.gms.demo.payloads.TicketDto;
import com.gms.demo.payloads.TicketGetAllOutDto;
import com.gms.demo.payloads.TicketOutDto;
import com.gms.demo.payloads.TicketUpdateStatusInDto;
import com.gms.demo.repo.DepartmentRepo;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.repo.TicketRepo;
import com.gms.demo.service.DepartmentService;
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

	TicketUpdateStatusInDto statusInDto = new TicketUpdateStatusInDto();
	Department department = new Department();
	Member member = new Member();
	TicketOutDto ticketOutDto = new TicketOutDto();
	TicketDto ticketDto = new TicketDto();
	Ticket ticket = new Ticket();
	Integer ticketId = 1;
	Integer memberId = 2;
	Integer departmentId = 3;
	String departmentName = "test";
	TicketGetAllOutDto getAllOutDto = new TicketGetAllOutDto();

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

		Department department = new Department();
		department.setDepartmentId(departmentId);
		department.setMembers(null);
		department.setDepartmentName("test");
		department.setTickets(null);

		Member member = new Member();
		member.setMemberId(memberId);
		member.setDepartment(null);
		member.setEmail("test@nucleusteq.com");
		member.setPassword("12345678");
		member.setIsFirstLogin(true);
		member.setName("test");
		member.setRole(Role.USER);
		member.setTickets(null);

		Optional<Member> m = java.util.Optional.of(member);
		Optional<Department> d = java.util.Optional.of(department);

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
	public void updateTicket_Test() throws ResourceNotFoundException{
			when(ticketRepo.findById(ticketId)).thenReturn(Optional.of(ticket));
			statusInDto.setStatus(Status.OPEN);	
			CommentDto commentDto = new CommentDto();
			commentDto.setCommentId(departmentId);
			commentDto.setContent("hh");
			commentDto.setDate(new Date());
			commentDto.setTicket(ticketDto); 
			commentDto.setUserName("hh");
			statusInDto.setComment(commentDto);
			when(ticketRepo.save(ticket)).thenReturn(ticket);	
			when(this.modelMapper.map(ticket, TicketOutDto.class)).thenReturn(ticketOutDto);	
			assertNotNull(this.ticketServiceImpl.updateTicket(statusInDto, ticketId));
	}

	@Test
    public void getTicketByIdTest() {
    when(this.ticketRepo.findById(ticketId)).thenReturn(Optional.of(ticket));
    when(this.modelMapper.map(ticket, TicketOutDto.class)).thenReturn(ticketOutDto);
    assertNotNull(this.ticketServiceImpl.getTicketBtId(ticketId));
  }

	@Test
	public void getAllFilterAllMytickets() {
		boolean myTickets = true;
		boolean adminDept = false;
		Status filter = Status.ALL;
		Integer pageNumber = 0;
		department.setDepartmentId(departmentId);
		department.setDepartmentName(departmentName);
		member.setDepartment(department);
		member.setMemberId(memberId);
		member.setRole(Role.ADMIN);
		when(this.memberRepo.findById(memberId)).thenReturn(Optional.of(member));
		Pageable pageable = PageRequest.of(pageNumber, 8);
		List<Ticket> tickets = new ArrayList<>();
		Ticket ticket1 = new Ticket();
		Ticket ticket2 = new Ticket();
		tickets.add(ticket1);
		tickets.add(ticket2);
		Page<Ticket> ticketPages = new PageImpl<>(tickets, pageable, tickets.size());
		when(ticketRepo.findAllByMemberIdOrderByStatusAsc(member.getMemberId(),
				PageRequest.of(pageNumber, 8, Sort.by("status")))).thenReturn(ticketPages);
		;

		when(this.modelMapper.map(ticket1, TicketGetAllOutDto.class)).thenReturn(getAllOutDto);

		List<TicketGetAllOutDto> getAllOutDtos2 = this.ticketServiceImpl.getAllTicketAuth(memberId, myTickets, filter,
				pageNumber, adminDept);
		assertNotNull(getAllOutDtos2);
	}

	@Test
	public void getAllFilterNotAllMytickets() {
		boolean myTickets = true;
		boolean adminDept = false;
		Status filter = Status.OPEN;
		Integer pageNumber = 0;
		department.setDepartmentId(departmentId);
		department.setDepartmentName(departmentName);
		member.setDepartment(department);
		member.setMemberId(memberId);
		member.setRole(Role.ADMIN);
		when(this.memberRepo.findById(memberId)).thenReturn(Optional.of(member));
		Pageable pageable = PageRequest.of(pageNumber, 8);
		List<Ticket> tickets = new ArrayList<>();
		Ticket ticket1 = new Ticket();
		Ticket ticket2 = new Ticket();
		tickets.add(ticket1);
		tickets.add(ticket2);
		Page<Ticket> ticketPages = new PageImpl<>(tickets, pageable, tickets.size());
		when(ticketRepo.findAllByMemberIdAndStatus(member.getMemberId(), filter, pageable)).thenReturn(ticketPages);
		;

		when(this.modelMapper.map(ticket1, TicketGetAllOutDto.class)).thenReturn(getAllOutDto);

		List<TicketGetAllOutDto> getAllOutDtos2 = this.ticketServiceImpl.getAllTicketAuth(memberId, myTickets, filter,
				pageNumber, adminDept);
		assertNotNull(getAllOutDtos2);
	}

	@Test
	public void getAllFilterAllAdminDept() {
		boolean myTickets = false;
		boolean adminDept = true;
		Status filter = Status.ALL;
		Integer pageNumber = 0;
		department.setDepartmentId(departmentId);
		department.setDepartmentName(departmentName);
		member.setDepartment(department);
		member.setMemberId(memberId);
		member.setRole(Role.ADMIN);
		when(this.memberRepo.findById(memberId)).thenReturn(Optional.of(member));
		Pageable pageable = PageRequest.of(pageNumber, 8);
		List<Ticket> tickets = new ArrayList<>();
		Ticket ticket1 = new Ticket();
		ticket1.setDepartment(department);
		Ticket ticket2 = new Ticket();
		ticket2.setDepartment(department);
		tickets.add(ticket1);
		tickets.add(ticket2);
		Page<Ticket> ticketPages = new PageImpl<>(tickets, pageable, tickets.size());
		when(this.ticketRepo.findAllByDepartmentId(member.getDepartment().getDepartmentId(),
				PageRequest.of(pageNumber, 8, Sort.by("status")))).thenReturn(ticketPages);

		when(this.modelMapper.map(ticket1, TicketGetAllOutDto.class)).thenReturn(getAllOutDto);

		List<TicketGetAllOutDto> getAllOutDtos2 = this.ticketServiceImpl.getAllTicketAuth(memberId, myTickets, filter,
				pageNumber, adminDept);
		assertNotNull(getAllOutDtos2);
	}

	@Test
	public void getAllFilterAllRoleAdminMyDept() {

		boolean adminDept = true;
		Status filter = Status.ALL;
		Integer pageNumber = 0;
		department.setDepartmentId(departmentId);
		department.setDepartmentName(departmentName);
		member.setDepartment(department);
		member.setMemberId(memberId);
		member.setRole(Role.ADMIN);
		when(this.memberRepo.findById(memberId)).thenReturn(Optional.of(member));
		Pageable pageable = PageRequest.of(pageNumber, 8);
		List<Ticket> tickets = new ArrayList<>();
		Ticket ticket1 = new Ticket();
		Ticket ticket2 = new Ticket();
		tickets.add(ticket1);
		tickets.add(ticket2);
		Page<Ticket> ticketPages = new PageImpl<>(tickets, pageable, tickets.size());
		when(this.ticketRepo.findAllByDepartmentId(member.getDepartment().getDepartmentId(),
				PageRequest.of(pageNumber, 8, Sort.by("status")))).thenReturn(ticketPages);

		when(this.modelMapper.map(ticket1, TicketGetAllOutDto.class)).thenReturn(getAllOutDto);

		List<TicketGetAllOutDto> getAllOutDtos2 = this.ticketServiceImpl.getAllTicketAuth(memberId, false, filter,
				pageNumber, adminDept);
		assertNotNull(getAllOutDtos2);
	}

	@Test
	public void getAllFilterNotAllRoleAdminMyDept() {

		boolean adminDept = true;
		Status filter = Status.OPEN;
		Integer pageNumber = 0;
		department.setDepartmentId(departmentId);
		department.setDepartmentName(departmentName);
		member.setDepartment(department);
		member.setMemberId(memberId);
		member.setRole(Role.ADMIN);
		when(this.memberRepo.findById(memberId)).thenReturn(Optional.of(member));
		Pageable pageable = PageRequest.of(pageNumber, 8);
		List<Ticket> tickets = new ArrayList<>();
		Ticket ticket1 = new Ticket();
		Ticket ticket2 = new Ticket();
		tickets.add(ticket1);
		tickets.add(ticket2);
		Page<Ticket> ticketPages = new PageImpl<>(tickets, pageable, tickets.size());
		when(this.ticketRepo.findAllByDepartmentIdAndStatus(member.getDepartment().getDepartmentId(), filter,
				PageRequest.of(pageNumber, 8, Sort.by("status")))).thenReturn(ticketPages);

		when(this.modelMapper.map(ticket1, TicketGetAllOutDto.class)).thenReturn(getAllOutDto);

		List<TicketGetAllOutDto> getAllOutDtos2 = this.ticketServiceImpl.getAllTicketAuth(memberId, false, filter,
				pageNumber, adminDept);
		assertNotNull(getAllOutDtos2);
	}

	@Test
	public void getAllFilterAllRole() {

		boolean adminDept = false;
		Status filter = Status.ALL;
		Integer pageNumber = 0;
		department.setDepartmentId(departmentId);
		department.setDepartmentName(departmentName);
		member.setDepartment(department);
		member.setMemberId(memberId);
		member.setRole(Role.ADMIN);
		when(this.memberRepo.findById(memberId)).thenReturn(Optional.of(member));
		Pageable pageable = PageRequest.of(pageNumber, 8);
		List<Ticket> tickets = new ArrayList<>();
		Ticket ticket1 = new Ticket();
		Ticket ticket2 = new Ticket();
		tickets.add(ticket1);
		tickets.add(ticket2);
		Page<Ticket> ticketPages = new PageImpl<>(tickets, pageable, tickets.size());
		when(this.ticketRepo.findAll(PageRequest.of(pageNumber, 8, Sort.by("status")))).thenReturn(ticketPages);

		when(this.modelMapper.map(ticket1, TicketGetAllOutDto.class)).thenReturn(getAllOutDto);

		List<TicketGetAllOutDto> getAllOutDtos2 = this.ticketServiceImpl.getAllTicketAuth(memberId, false, filter,
				pageNumber, adminDept);
		assertNotNull(getAllOutDtos2);
	}

	@Test
	public void getAllFilterNotAllRole() {

		boolean adminDept = false;
		Status filter = Status.OPEN;
		Integer pageNumber = 0;
		department.setDepartmentId(departmentId);
		department.setDepartmentName(departmentName);
		member.setDepartment(department);
		member.setMemberId(memberId);
		member.setRole(Role.ADMIN);
		when(this.memberRepo.findById(memberId)).thenReturn(Optional.of(member));
		Pageable pageable = PageRequest.of(pageNumber, 8);
		List<Ticket> tickets = new ArrayList<>();
		Ticket ticket1 = new Ticket();
		Ticket ticket2 = new Ticket();
		tickets.add(ticket1);
		tickets.add(ticket2);
		Page<Ticket> ticketPages = new PageImpl<>(tickets, pageable, tickets.size());
		when(this.ticketRepo.findByStatus(filter, PageRequest.of(pageNumber, 8, Sort.by("status"))))
				.thenReturn(ticketPages);

		when(this.modelMapper.map(ticket1, TicketGetAllOutDto.class)).thenReturn(getAllOutDto);

		List<TicketGetAllOutDto> getAllOutDtos2 = this.ticketServiceImpl.getAllTicketAuth(memberId, false, filter,
				pageNumber, adminDept);
		assertNotNull(getAllOutDtos2);
	}

	@Test
	public void getAllFilterAllRoleUser() {

		boolean adminDept = false;
		Status filter = Status.ALL;
		Integer pageNumber = 0;
		department.setDepartmentId(departmentId);
		department.setDepartmentName(departmentName);
		member.setDepartment(department);
		member.setMemberId(memberId);
		member.setRole(Role.USER);
		when(this.memberRepo.findById(memberId)).thenReturn(Optional.of(member));
		Pageable pageable = PageRequest.of(pageNumber, 8);
		List<Ticket> tickets = new ArrayList<>();
		Ticket ticket1 = new Ticket();
		ticket1.setDepartment(department);
		Ticket ticket2 = new Ticket();
		ticket2.setDepartment(department);
		tickets.add(ticket1);
		tickets.add(ticket2);
		Page<Ticket> ticketPages = new PageImpl<>(tickets, pageable, tickets.size());
		when(this.ticketRepo.findAllByDepartmentId(member.getDepartment().getDepartmentId(),
				PageRequest.of(pageNumber, 8, Sort.by("status")))).thenReturn(ticketPages);

		when(this.modelMapper.map(ticket1, TicketGetAllOutDto.class)).thenReturn(getAllOutDto);

		List<TicketGetAllOutDto> getAllOutDtos2 = this.ticketServiceImpl.getAllTicketAuth(memberId, false, filter,
				pageNumber, adminDept);
		assertNotNull(getAllOutDtos2);
	}

	@Test
	public void getAllFilterNotAllRoleUser() {

		boolean adminDept = false;
		Status filter = Status.OPEN;
		Integer pageNumber = 0;
		department.setDepartmentId(departmentId);
		department.setDepartmentName(departmentName);
		member.setDepartment(department);
		member.setMemberId(memberId);
		member.setRole(Role.USER);
		when(this.memberRepo.findById(memberId)).thenReturn(Optional.of(member));
		Pageable pageable = PageRequest.of(pageNumber, 8);
		List<Ticket> tickets = new ArrayList<>();
		Ticket ticket1 = new Ticket();
		Ticket ticket2 = new Ticket();
		tickets.add(ticket1);
		tickets.add(ticket2);
		Page<Ticket> ticketPages = new PageImpl<>(tickets, pageable, tickets.size());
		when(this.ticketRepo.findAllByDepartmentIdAndStatus(member.getDepartment().getDepartmentId(), filter,
				PageRequest.of(pageNumber, 8, Sort.by("status")))).thenReturn(ticketPages);

		when(this.modelMapper.map(ticket1, TicketGetAllOutDto.class)).thenReturn(getAllOutDto);

		List<TicketGetAllOutDto> getAllOutDtos2 = this.ticketServiceImpl.getAllTicketAuth(memberId, false, filter,
				pageNumber, adminDept);
		assertNotNull(getAllOutDtos2);
	}

}
