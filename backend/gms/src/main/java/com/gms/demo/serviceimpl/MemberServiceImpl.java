package com.gms.demo.serviceimpl;

import com.gms.demo.controller.DepartmentController;
import com.gms.demo.entity.Member;
import com.gms.demo.entity.Role;
import com.gms.demo.exception.ResourceNotFoundException;
import com.gms.demo.payloads.ApiResponse;
import com.gms.demo.payloads.MemberChangePasswordDto;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberGetAllOutDto;
import com.gms.demo.payloads.MemberLoginDto;
import com.gms.demo.payloads.MemberOutDto;
import com.gms.demo.repo.DepartmentRepo;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.service.MemberService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Represents a service implementation for Member-related operations. Implements
 * methods for creating members and performing login. Uses ModelMapper for
 * mapping entities to DTOs. This class is marked as a Spring service.
 * Responsible for interacting with the Member repository.
 *
 * @author Sachin Singh Rajpoot
 * @version 1.0
 * @since 28-08-2023
 */
@Service
public class MemberServiceImpl implements MemberService {

  /** The ModelMapper instance. */
  @Autowired
  private ModelMapper mapper;

  /** The MemberRepo instance. */
  @Autowired
  private MemberRepo memberRepo;

  /** The MemberRepo instance. */
  @Autowired
  private DepartmentRepo departmentRepo;

  /**
   * Number of items sent.
   */
  private final Integer numberOfItemToSend = 10;

  /**
   * The minimum sixe for password.
   */
  private static final int MIN_SIZE_PASSWORD = 8;

  /**
   * Logger for logging.
   */
  private static final Logger DISPLAY = LoggerFactory
      .getLogger(DepartmentController.class);

  /**
   * Perform member login.
   *
   * @param memberLoginDto The MemberLoginDto containing login credentials.
   * @return True if login is successful, false otherwise.
   * @throws Exception Exception
   */
  @Override
  public final MemberOutDto
      login(final MemberLoginDto memberLoginDto) {
    DISPLAY.info("Inside Service");
    Member member = this.memberRepo
            .findByEmail(memberLoginDto.getEmail());
    DISPLAY.info("Member Found");
    if (member != null
        && member.getPassword()
        .equals(memberLoginDto.getPassword())) {
        DISPLAY.info("Password Matched");
        return this.mapper.map(member, MemberOutDto.class);
    }
    DISPLAY.info("Password does not Matched");
    return null;
  }

  /**
   * Verify valid email and password.
   *
   * @param email    email
   * @param password password
   * @return True if valid email and password.
   * @throws Exception Exception
   */
  @Override
  public final Boolean verifyEmailAndPassword(final String email,
          final String password) {
    DISPLAY.info("Inside Service");
    String emailPattern =
            "^[a-zA-Z0-9]+@[a-zA-Z0-9.-]+$";
    boolean isValidEmail = email.matches(emailPattern);
    boolean isValidEmail2 = email.endsWith("@nucleusteq.com");
    boolean isValidPassword2 = false;
    if (password.length() >= MIN_SIZE_PASSWORD) {
      isValidPassword2 = true;
    }
    if (isValidEmail
        && isValidEmail2
        && isValidPassword2) {
      DISPLAY.info("Verify success");
      return true;
    }
    DISPLAY.info("Verify failed");
    return false;
  }

  @Override
  public final MemberOutDto changePassword(final Integer memberId,
      final MemberChangePasswordDto changePasswordDto) {
    DISPLAY.info("Inside Service");
    Member member = this.memberRepo.findById(memberId)
        .orElseThrow(() -> new
            ResourceNotFoundException("member", "member ID", memberId));
    DISPLAY.info("Member found");
    if (member.getPassword().equals(changePasswordDto
            .getOldpassword())) {
      DISPLAY.info("Password Matched");
      member.setPassword(changePasswordDto.getNewPassword());
      member.setIsFirstLogin(false);
      Member savedMember = this.memberRepo.save(member);
      return this.mapper.map(savedMember, MemberOutDto.class);
    }
    DISPLAY.info("Password Matched Failed");
    return null;
  }

  @Override
  public final List<MemberGetAllOutDto>
      getAllMemberAuth(final String email,
              final String password,
      final Integer pageNumber) {
    DISPLAY.info("Inside Service");
    Integer numberOfPages = numberOfItemToSend;
    Page<Member> members =
        this.memberRepo
            .findAll(PageRequest.of(pageNumber,
                    numberOfPages, Sort.by("name")));
    List<MemberGetAllOutDto> memberOutDtos = new ArrayList<>();
    members.forEach(m -> memberOutDtos
            .add(this.mapper.map(m, MemberGetAllOutDto.class)));
    return memberOutDtos;
  }

  @Override
  public final ApiResponse createMember3(final MemberDto memberDto,
      final String email, final String password) {
    DISPLAY.info("Inside Service");
    if (memberDto.getName().trim().equals("")) {
        DISPLAY.info("name failed");
        return new ApiResponse("Name cannot be empty", false);
    }
    if (memberDto.getPassword().trim().equals("")) {
        DISPLAY.info("password failed");
        return new ApiResponse("password cannot be empty", false);
    }
    if (Objects.isNull(memberDto.getDepartment())) {
        DISPLAY.info("Department failed");
        return new ApiResponse("Department cannot be null", false);
    }
    if (Objects.isNull(memberDto.getDepartment().getDepartmentId())) {
        DISPLAY.info("Department ID failed");
        return new ApiResponse("Department ID cannot be null or empty", false);
    }
    if (memberDto.getEmail().trim().equals("")) {
        DISPLAY.info("email failed");
        return new ApiResponse("Email cannot be empty", false);
    }
    Member member = this.memberRepo.findByEmail(email);
    this.departmentRepo.findById(memberDto.getDepartment()
        .getDepartmentId()).orElseThrow(() -> new
                    ResourceNotFoundException("Department", "Department ID",
                    memberDto.getDepartment().getDepartmentId()));
    DISPLAY.info("member found");
    if (member != null
            && member.getPassword().equals(password)
            && member.getRole() != null
            && member.getRole().equals(Role.ADMIN)) {
        DISPLAY.info("Role is Admin");
        memberDto.setIsFirstLogin(true);
        memberDto.setName(memberDto.getName().trim());
        memberDto.setPassword(memberDto.getPassword().trim());
        Member member2 = this.mapper.map(memberDto, Member.class);
        this.memberRepo.save(member2);
        return new ApiResponse("Member Created Successfully", true);
    }
    DISPLAY.info("Null returned");
    return new ApiResponse("Something went wrong", false);
  }

  @Override
  public final ApiResponse deleteMember(final Integer memberId) {
    DISPLAY.info("Inside Service");
    this.memberRepo.findById(memberId)
        .orElseThrow(() -> new
                ResourceNotFoundException("member", "member ID", memberId));
    DISPLAY.info("member found");
    this.memberRepo.deleteById(memberId);
    DISPLAY.info("member deleted");
    return new ApiResponse("Member with member ID : "
    + memberId + " is deleted successfully", true);
  }
}
