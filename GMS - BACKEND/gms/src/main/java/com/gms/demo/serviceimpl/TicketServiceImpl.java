package com.gms.demo.serviceimpl;

import com.gms.demo.entity.Department;
import com.gms.demo.entity.Member;
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
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

  /**
   * The TicketRepo interface represents a repository for Ticket entities in the database.
   * It provides methods for querying and manipulating ticket data.
   */
  @Autowired
  private TicketRepo ticketRepo;

  /**
   * The ModelMapper class is used for object mapping between different data models.
   * It facilitates the conversion of one object type to another.
   */
  @Autowired
  private ModelMapper modelMapper;

  /**
   * The MemberRepo interface represents a repository for Member entities in the database.
   * It provides methods for querying and manipulating member data.
   */
  @Autowired
  private MemberRepo memberRepo;

  /**
   * The DepartmentRepo interface represents a repository for Department entities in the database.
   * It provides methods for querying and manipulating department data.
   */
  @Autowired
  private DepartmentRepo departmentRepo;

  /**
   * Creates a new ticket based on the provided TicketDto, associating it with a specific member and department.
   *
   * @param ticketDto    The TicketDto containing ticket information.
   * @param memberId     The ID of the member associated with the ticket.
   * @param departmentId The ID of the department associated with the ticket.
   * @return A TicketOutDto representing the newly created ticket.
   * @throws ResourceNotFoundException If the specified member or department is not found in the database.
   */
  @Override
  public TicketOutDto createTicket(
    final TicketDto ticketDto,
    final Integer memberId,
    final Integer departmentId
  ) {
    Member member =
      this.memberRepo.findById(memberId)
        .orElseThrow(() ->
          new ResourceNotFoundException("Member", "member ID", memberId)
        );

//    MemberDto memberDto = this.modelMapper.map(member, MemberDto.class);

    Department department =
      this.departmentRepo.findById(departmentId)
        .orElseThrow(() ->
          new ResourceNotFoundException(
            "Department",
            "department ID",
            departmentId
          )
        );

//    DepartmentDto departmentDto =
//      this.modelMapper.map(department, DepartmentDto.class);

//    ticketDto.setMember(memberDto);
//    ticketDto.setDepartment(departmentDto);

    Ticket ticket = this.modelMapper.map(ticketDto, Ticket.class);
    ticket.setMember(member);
    ticket.setDepartment(department);
    Ticket savedTicket = this.ticketRepo.save(ticket);
    return this.modelMapper.map(savedTicket, TicketOutDto.class);
  }

  /**
   * Retrieves a list of all tickets in the system.
   *
   * @return A list of TicketOutDto objects representing all tickets.
   */
  @Override
  public List<TicketOutDto> getAllTicket() {
    List<Ticket> tickets = this.ticketRepo.findAll();
    List<TicketOutDto> ticketOutDtos = new ArrayList<>();
    tickets.forEach(ticket ->
      ticketOutDtos.add(this.modelMapper.map(ticket, TicketOutDto.class))
    );
    return ticketOutDtos;
  }
}
