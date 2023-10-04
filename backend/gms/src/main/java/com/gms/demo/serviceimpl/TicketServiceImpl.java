package com.gms.demo.serviceimpl;

import com.gms.demo.controller.DepartmentController;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * This is Ticket Service Implementation.
 */
@Service
public class TicketServiceImpl implements TicketService {

  /**
   * The TicketRepo interface represents a repository for Ticket entities in the
   * database. It provides methods for querying and manipulating ticket data.
   */
  @Autowired
  private TicketRepo ticketRepo;

  /**
   * The ModelMapper class is used for object mapping between different data
   * models. It facilitates the conversion of one object type to another.
   */
  @Autowired
  private ModelMapper modelMapper;

  /**
   * The MemberRepo interface represents a repository for Member entities in the
   * database. It provides methods for querying and manipulating member data.
   */
  @Autowired
  private MemberRepo memberRepo;

  /**
   * The DepartmentRepo interface represents a repository
   *     for Department entities
   * in the database. It provides methods for querying
   *     and manipulating department
   * data.
   */
  @Autowired
  private DepartmentRepo departmentRepo;

  /**
   * Logger for logging.
   */
  private static final Logger DISPLAY = LoggerFactory
      .getLogger(DepartmentController.class);

  /**
   * Number of items sent.
   */
  private final Integer numberOfItemToSend = 8;

  /**
   * Creates a new ticket based on the provided TicketDto, associating it with a
   * specific member and department.
   *
   * @param ticketDto    The TicketDto containing ticket information.
   * @param memberId     The ID of the member associated with the ticket.
   * @param departmentId The ID of the department associated with the ticket.
   * @return A TicketOutDto representing the newly created ticket.
   * @throws ResourceNotFoundException If the specified member or department is
   *                                   not found in the database.
   */
  @Override
  public final TicketOutDto createTicket(final TicketDto ticketDto,
      final Integer memberId, final Integer departmentId) {
    DISPLAY.info("Inside service");
    Member member = this.memberRepo.findById(memberId)
        .orElseThrow(() -> new
                ResourceNotFoundException("Member", "member ID", memberId));
    DISPLAY.info("Member found");
    Department department = this.departmentRepo.findById(departmentId)
        .orElseThrow(() ->
        new ResourceNotFoundException("Department", "department ID",
            departmentId));
    DISPLAY.info("Department found");
    Ticket ticket = this.modelMapper.map(ticketDto, Ticket.class);
    ticket.setMember(member);
    ticket.setDepartment(department);
    Ticket savedTicket = this.ticketRepo.save(ticket);
    return this.modelMapper.map(savedTicket, TicketOutDto.class);
  }

  /**
   * Updates a tickets.
   *
   * @return Updated TicketOutDto.
   */
  @Override
  public final TicketOutDto
      updateTicket(final TicketUpdateStatusInDto ticketDto,
          final Integer ticketId) {
    DISPLAY.info("Inside service");
    Ticket ticket = this.ticketRepo.findById(ticketId)
        .orElseThrow(() -> new ResourceNotFoundException("ticket",
            "ticket ID", ticketId));
    ticket.setStatus(ticketDto.getStatus());
    CommentDto commentDto = ticketDto.getComment();
    commentDto.setTicket(this.modelMapper.map(ticket, TicketDto.class));
    List<Comment> comments = ticket.getComments();
    comments.add(this.modelMapper.map(commentDto, Comment.class));
    Ticket savedTicket = this.ticketRepo.save(ticket);
    return this.modelMapper.map(savedTicket, TicketOutDto.class);
  }

  @Override
  public final List<TicketGetAllOutDto>
      getAllTicketAuth(final Integer memberId,
      final boolean myTickets, final Status filter,
      final Integer pageNumber, final boolean adminDept) {
    DISPLAY.info("Inside service");
    DISPLAY.info("Param received -> adminDept : " + adminDept
            + ", myTickets : " + myTickets);
    DISPLAY.info("Filter received : " + filter);
    DISPLAY.info("Page Number : " + pageNumber);
    DISPLAY.info("Member Id : " + memberId);
    Member member = this.memberRepo.findById(memberId)
        .orElseThrow(() -> new
                ResourceNotFoundException("Member",
            "member ID", memberId));
    String departmentName =
            member.getDepartment().getDepartmentName();
    Integer numberOfPages = numberOfItemToSend;
    Pageable pageable = PageRequest.of(pageNumber, numberOfPages);
    Role role = member.getRole();
    if (myTickets) {
      if (filter.toString().equals("ALL")) {
        Page<Ticket> pageTickets =
            ticketRepo
                .findAllByMemberIdOrderByStatusAsc(member.getMemberId(),
            PageRequest.of(pageNumber, numberOfPages, Sort.by("status")));
        List<Ticket> tickets = pageTickets.getContent();
        List<TicketGetAllOutDto> ticketOutDtos = new ArrayList<>();
        tickets.forEach(t -> ticketOutDtos
                .add(this.modelMapper.map(t, TicketGetAllOutDto.class)));
        return ticketOutDtos;
      }
      Page<Ticket> pageTickets =
          ticketRepo.findAllByMemberIdAndStatus(member.getMemberId(),
                  filter, pageable);
      List<Ticket> tickets = pageTickets.getContent();
      List<TicketGetAllOutDto> ticketOutDtos = new ArrayList<>();
      tickets
          .forEach(t -> ticketOutDtos.add(this.modelMapper
                  .map(t, TicketGetAllOutDto.class)));
      return ticketOutDtos;
    } else if (adminDept && role.equals(Role.ADMIN)) {
      if (filter.equals(Status.ALL)) {
        Page<Ticket> pagetickets =
            this.ticketRepo
                .findAllByDepartmentId(member.getDepartment()
                        .getDepartmentId(),
            PageRequest.of(pageNumber, numberOfPages, Sort.by("status")));
        List<Ticket> tickets = pagetickets.getContent();
        List<TicketGetAllOutDto> ticketOutDtos = new ArrayList<>();
        tickets
            .forEach(t -> ticketOutDtos
                    .add(this.modelMapper.map(t,
                            TicketGetAllOutDto.class)));
        return ticketOutDtos;
      }
      Page<Ticket> pagetickets = this.ticketRepo
              .findAllByDepartmentIdAndStatus(
          member.getDepartment().getDepartmentId(),
          filter, PageRequest.of(pageNumber, numberOfPages,
                  Sort.by("status")));
      List<Ticket> tickets = pagetickets.getContent();
      List<TicketGetAllOutDto> ticketOutDtos = new ArrayList<>();
      tickets
          .forEach(t -> ticketOutDtos.add(this.modelMapper.map(t,
                  TicketGetAllOutDto.class)));
      return ticketOutDtos;
    } else if (role.equals(Role.ADMIN)) {
      if (filter.toString().equals("ALL")) {
        Page<Ticket> tickets =
            this.ticketRepo
                .findAll(PageRequest.of(pageNumber, numberOfPages,
                        Sort.by("status")));
        List<TicketGetAllOutDto> ticketOutDtos = new ArrayList<>();
        tickets.forEach(t -> ticketOutDtos
                .add(this.modelMapper.map(t, TicketGetAllOutDto.class)));
        return ticketOutDtos;
      }
      Page<Ticket> tickets =
          this.ticketRepo.findByStatus(filter,
                  PageRequest.of(pageNumber,
                          numberOfPages, Sort.by("status")));
      List<TicketGetAllOutDto> ticketOutDtos = new ArrayList<>();
      tickets
          .forEach(t -> ticketOutDtos.add(this.modelMapper
                  .map(t, TicketGetAllOutDto.class)));
      return ticketOutDtos;
    } else {
      if (filter.toString().equals("ALL")) {
        Page<Ticket> pagetickets =
            this.ticketRepo.findAllByDepartmentId(member
                    .getDepartment().getDepartmentId(),
            PageRequest.of(pageNumber, numberOfPages,
                    Sort.by("status")));
        List<Ticket> tickets = pagetickets.getContent();
        List<TicketGetAllOutDto> ticketOutDtos = new ArrayList<>();
        tickets.forEach(t -> {
          if (departmentName
                  .equals(t.getDepartment().getDepartmentName())) {
            ticketOutDtos
                .add(this.modelMapper.map(t, TicketGetAllOutDto.class));
          }
        });
        return ticketOutDtos;
      }
      Page<Ticket> tickets =
          this.ticketRepo
              .findAllByDepartmentIdAndStatus(member.getDepartment()
                      .getDepartmentId(),
          filter, PageRequest.of(pageNumber,
                  numberOfPages, Sort.by("status")));
      List<TicketGetAllOutDto> ticketOutDtos = new ArrayList<>();
      tickets.forEach(t -> ticketOutDtos
              .add(this.modelMapper.map(t, TicketGetAllOutDto.class)));
      return ticketOutDtos;
    }
  }

  @Override
  public final TicketOutDto getTicketBtId(final Integer ticketId) {
    DISPLAY.info("Inside service");
    Ticket ticket = this.ticketRepo.findById(ticketId)
        .orElseThrow(() -> new
                ResourceNotFoundException("Ticket", "Ticket ID", ticketId));
    return this.modelMapper.map(ticket, TicketOutDto.class);
  }
}
