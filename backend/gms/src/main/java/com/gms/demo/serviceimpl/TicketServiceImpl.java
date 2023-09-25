package com.gms.demo.serviceimpl;

import com.gms.demo.entity.Comment;
import com.gms.demo.entity.Department;
import com.gms.demo.entity.Member;
import com.gms.demo.entity.Role;
import com.gms.demo.entity.Status;
import com.gms.demo.entity.Ticket;
import com.gms.demo.exception.ResourceNotFoundException;
import com.gms.demo.payloads.CommentDto;
import com.gms.demo.payloads.TicketDto;
import com.gms.demo.payloads.TicketGetAllOutDto;
import com.gms.demo.payloads.TicketOutDto;
import com.gms.demo.payloads.TicketUpdateStatusInDto;
import com.gms.demo.repo.DepartmentRepo;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.repo.TicketRepo;
import com.gms.demo.service.TicketService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    Department department =
      this.departmentRepo.findById(departmentId)
        .orElseThrow(() ->
          new ResourceNotFoundException(
            "Department",
            "department ID",
            departmentId
          )
        );

    Ticket ticket = this.modelMapper.map(ticketDto, Ticket.class);
    ticket.setMember(member);
    ticket.setDepartment(department);
    Ticket savedTicket = this.ticketRepo.save(ticket);
    //    System.out.println(member);
    //    System.out.println(department);
    System.out.println(savedTicket);
    return this.modelMapper.map(savedTicket, TicketOutDto.class);
  }

  /**
   * Updates a tickets.
   *
   * @return Updated TicketOutDto.
   */
  @Override
  public TicketOutDto updateTicket(
    TicketUpdateStatusInDto ticketDto,
    Integer ticketId
  ) {
    Ticket ticket =
      this.ticketRepo.findById(ticketId)
        .orElseThrow(() ->
          new ResourceNotFoundException("ticket", "ticket ID", ticketId)
        );
    ticket.setStatus(ticketDto.getStatus());
    CommentDto commentDto = ticketDto.getComment();
    commentDto.setTicket(this.modelMapper.map(ticket, TicketDto.class));
    List<Comment> comments = ticket.getComments();
    comments.add(this.modelMapper.map(commentDto, Comment.class));
    Ticket savedTicket = this.ticketRepo.save(ticket);
    return this.modelMapper.map(savedTicket, TicketOutDto.class);
  }

  @Override
  public List<TicketGetAllOutDto> getAllTicketAuth(
    Integer memberId,
    boolean myTickets,
    Status filter,
    Integer pageNumber,
    boolean adminDept
  ) {
	  System.out.println("inside");
    Member member =
      this.memberRepo.findById(memberId)
        .orElseThrow(() ->
          new ResourceNotFoundException("Member", "member ID", memberId)
        );
    Role role = member.getRole();
    String departmentName = member.getDepartment().getDepartmentName();
    Integer status = 0;
    Pageable pageable = PageRequest.of(pageNumber, 8);
    if (filter.toString().equals("OPEN")) {
      status = 0;
    }
    if (filter.toString().equals("PROGRESS")) {
      status = 1;
    }
    if (filter.toString().equals("CLOSED")) {
      status = 2;
    }
    if (filter.toString().equals("ALL")) {
      status = 3;
    }
    System.out.println("Status is set to : " + status);
    if (myTickets) {
      if (filter.toString().equals("ALL")) {
        Page<Ticket> pageTickets = ticketRepo.findAllByMemberIdOrderByStatusAsc(
          member.getMemberId(),
          PageRequest.of(pageNumber, 8, Sort.by("status"))
        );
        List<Ticket> tickets = pageTickets.getContent();
        List<TicketGetAllOutDto> ticketOutDtos = new ArrayList<>();
        tickets.forEach(t ->
          ticketOutDtos.add(this.modelMapper.map(t, TicketGetAllOutDto.class))
        );
        return ticketOutDtos;
      }
      Page<Ticket> pageTickets = ticketRepo.findAllByMemberIdAndStatus(
        member.getMemberId(),
        filter,
        pageable
      );
      List<Ticket> tickets = pageTickets.getContent();
      List<TicketGetAllOutDto> ticketOutDtos = new ArrayList<>();
      tickets.forEach(t ->
        ticketOutDtos.add(this.modelMapper.map(t, TicketGetAllOutDto.class))
      );
      return ticketOutDtos;
    } else if (adminDept && role.equals(Role.ADMIN)) {
      if (filter.equals(Status.ALL)) {
        Page<Ticket> pagetickets =
          this.ticketRepo.findAllByDepartmentId(
              member.getDepartment().getDepartmentId(),
              PageRequest.of(pageNumber, 8, Sort.by("status"))
            );
        List<Ticket> tickets = pagetickets.getContent();
        List<TicketGetAllOutDto> ticketOutDtos = new ArrayList<>();
        tickets.forEach(t ->
          ticketOutDtos.add(this.modelMapper.map(t, TicketGetAllOutDto.class))
        );
        return ticketOutDtos;
      }
      Page<Ticket> tickets =
        this.ticketRepo.findAllByDepartmentIdAndStatus(
            member.getDepartment().getDepartmentId(),
            filter,
            PageRequest.of(pageNumber, 8, Sort.by("status"))
          );
      List<TicketGetAllOutDto> ticketOutDtos = new ArrayList<>();
      tickets.forEach(t ->
        ticketOutDtos.add(this.modelMapper.map(t, TicketGetAllOutDto.class))
      );
      return ticketOutDtos;
    } else if (role.equals(Role.ADMIN)) {
      if (filter.toString().equals("ALL")) {
        Page<Ticket> tickets =
          this.ticketRepo.findAll(
              PageRequest.of(pageNumber, 8, Sort.by("status"))
            );
        List<TicketGetAllOutDto> ticketOutDtos = new ArrayList<>();
        tickets.forEach(t ->
          ticketOutDtos.add(this.modelMapper.map(t, TicketGetAllOutDto.class))
        );
        return ticketOutDtos;
      }
      System.out.println("Before Repo");
      Page<Ticket> tickets = this.ticketRepo.findByStatus(filter, PageRequest.of(pageNumber, 8, Sort.by("status")));
      System.out.println("After Repo");
      List<TicketGetAllOutDto> ticketOutDtos = new ArrayList<>();
      tickets.forEach(t ->
        ticketOutDtos.add(this.modelMapper.map(t, TicketGetAllOutDto.class))
      );
      return ticketOutDtos;
    } else {
      if (filter.toString().equals("ALL")) {
        Page<Ticket> pagetickets =
          this.ticketRepo.findAllByDepartmentId(
              member.getDepartment().getDepartmentId(),
              PageRequest.of(pageNumber, 8, Sort.by("status"))
            );
        List<Ticket> tickets = pagetickets.getContent();
        List<TicketGetAllOutDto> ticketOutDtos = new ArrayList<>();
        tickets.forEach(t -> {
          if (departmentName.equals(t.getDepartment().getDepartmentName())) {
            ticketOutDtos.add(
              this.modelMapper.map(t, TicketGetAllOutDto.class)
            );
          }
        });
        return ticketOutDtos;
      }
      System.out.println("inside user -> not all");
      Page<Ticket> tickets =
        this.ticketRepo.findAllByDepartmentIdAndStatus(
            member.getDepartment().getDepartmentId(),
            filter,
            PageRequest.of(pageNumber, 8, Sort.by("status"))
          );
      List<TicketGetAllOutDto> ticketOutDtos = new ArrayList<>();
      tickets.forEach(t ->
        ticketOutDtos.add(this.modelMapper.map(t, TicketGetAllOutDto.class))
      );
      return ticketOutDtos;
    }
  }

  @Override
  public TicketOutDto getTicketBtId(Integer ticketId) {
    Ticket ticket =
      this.ticketRepo.findById(ticketId)
        .orElseThrow(() ->
          new ResourceNotFoundException("Ticket", "Ticket ID", ticketId)
        );

    return this.modelMapper.map(ticket, TicketOutDto.class);
  }
}