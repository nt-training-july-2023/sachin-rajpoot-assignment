package com.gms.demo.serviceimpl;

import com.gms.demo.entity.Member;
import com.gms.demo.entity.Role;
import com.gms.demo.exception.ResourceNotFoundException;
import com.gms.demo.payloads.ApiResponse;
import com.gms.demo.payloads.MemberChangePasswordDto;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberGetAllOutDto;
import com.gms.demo.payloads.MemberLoginDto;
import com.gms.demo.payloads.MemberOutDto;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.service.MemberService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
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

  /**
   * The minimum sixe for password.
   */
  private static final int MIN_SIZE_PASSWORD = 5;

  /**
   * Perform member login.
   *
   * @param memberLoginDto The MemberLoginDto containing login credentials.
   * @return True if login is successful, false otherwise.
   * @throws Exception Exception
   */
  @Override
  public MemberOutDto login(final MemberLoginDto memberLoginDto) {
    Member member = this.memberRepo.findByEmail(memberLoginDto.getEmail());
    System.out.println("member found by email " + member);

    if (member != null) {
      System.out.println(member.getPassword() + "  " + memberLoginDto.getPassword());
      if (member.getPassword().equals(memberLoginDto.getPassword())) {
        System.out.println(memberLoginDto);
        return this.mapper.map(member, MemberOutDto.class);
      }
    }
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
  public Boolean verifyEmailAndPassword(final String email, final String password) {
    String emailPattern = "^[a-zA-Z0-9]+@[a-zA-Z0-9.-]+$";
    // String passwordPattern = "^[a-zA-Z0-9]*$";

    boolean isValidEmail = email.matches(emailPattern);
    // boolean isValidPassword = password.matches(passwordPattern);
    boolean isValidEmail2 = email.endsWith("@nucleusteq.com");
    boolean isValidPassword2 = password.length() >= MIN_SIZE_PASSWORD ? true : false;

    if (isValidEmail && isValidEmail2 && isValidPassword2) {
      return true;
    }
    return false;
  }

  @Override
  public final MemberOutDto changePassword(final Integer memberId,
      final MemberChangePasswordDto changePasswordDto) {
    System.out.println("member id received -> " + memberId);
    Member member = this.memberRepo.findById(memberId)
        .orElseThrow(() -> new ResourceNotFoundException("member", "member ID", memberId));
    System.out.println("MemberChangePasswordDto -> " + changePasswordDto);
    System.out.println(
        "Real Password : " + member.getPassword() + " Password Received -> "
        + changePasswordDto.getOldpassword());
    if (member.getPassword().equals(changePasswordDto.getOldpassword())) {
      member.setPassword(changePasswordDto.getNewPassword());
      member.setIsFirstLogin(false);
      Member savedMember = this.memberRepo.save(member);
      return this.mapper.map(savedMember, MemberOutDto.class);
    }
    return null;
  }

  @Override
  public final List<MemberGetAllOutDto> getAllMemberAuth(final String email, final String password,
      final Integer pageNumber) {
    Page<Member> members = this.memberRepo.findAll(PageRequest.of(pageNumber, 6, Sort.by("name")));
    List<MemberGetAllOutDto> memberOutDtos = new ArrayList<>();
    members.forEach(m -> memberOutDtos.add(this.mapper.map(m, MemberGetAllOutDto.class)));
    return memberOutDtos;
  }

  @Override
  public final MemberOutDto createMember3(final MemberDto memberDto,
      final String email, final String password) {
    Member member = this.memberRepo.findByEmail(email);
    if (member != null) {
      if (member.getPassword().equals(password)
          && member.getRole() != null
          && member.getRole().equals(Role.ADMIN)) {
        Member member2 = this.mapper.map(memberDto, Member.class);
        Member savedMember = this.memberRepo.save(member2);
        MemberOutDto memberOutDto2 = this.mapper.map(savedMember, MemberOutDto.class);
        return memberOutDto2;
      }
    }
    return null;
  }

  @Override
  public final ApiResponse deleteMember(final Integer memberId) {
    this.memberRepo.findById(memberId)
        .orElseThrow(() -> new ResourceNotFoundException("member", "member ID", memberId));
    this.memberRepo.deleteById(memberId);

    return new ApiResponse("Member with member ID : "
    + memberId + " is deleted successfully", true);
  }
}
