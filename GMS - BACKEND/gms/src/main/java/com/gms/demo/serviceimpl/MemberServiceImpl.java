package com.gms.demo.serviceimpl;

import com.gms.demo.entity.Department;
import com.gms.demo.entity.Member;
import com.gms.demo.entity.Role;
import com.gms.demo.exception.ResourceNotFoundException;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.DepartmentOutDto;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberLoginDto;
import com.gms.demo.payloads.MemberOutDto;
import com.gms.demo.repo.DepartmentRepo;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.service.DepartmentService;
import com.gms.demo.service.MemberService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

  /** The DepartmentService instance. */
  @Autowired
  private DepartmentService departmentService;
  
  /** The DepartmentService instance. */
  @Autowired
  private DepartmentRepo departmentRepo;

  /**
   * The minimum sixe for password.
   */
  private static final int MIN_SIZE_PASSWORD = 5;

  /**
   * Perform member login.
   *
   * @param memberLoginDto The MemberLoginDto containing login credentials.
   * @return True if login is successful, false otherwise.
   * @throws Exception
   */
  @Override
  public Boolean login(final MemberLoginDto memberLoginDto) {
    Member member = this.memberRepo.findByEmail(memberLoginDto.getEmail());
    if (member != null) {
      if (member.getPassword().equals(memberLoginDto.getPassword())) {
        System.out.println(memberLoginDto);
        return true;
      }
    }

    return false;
  }

  /**
   * Create a new member.
   *
   * @param memberDto The MemberDto containing member details.
   * @param departmentId
   * @return The created MemberDto.
   */
  @Override
  public MemberOutDto createMember(
    final MemberDto memberDto,
    final Integer departmentId
  ) {
//    DepartmentDto departmentDto =
//      this.departmentService.getDepartmentById(departmentId);
//    memberDto.setDepartmentDto(departmentDto); 
    		Department department = this.departmentRepo.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("Department", "departmentId", departmentId));

    Member member = this.mapper.map(memberDto, Member.class);
    member.setDepartment(department);
    return this.mapper.map(this.memberRepo.save(member), MemberOutDto.class);
  }

  /**
   * Verify valid email and password.
   *
   * @param email
   * @param password
   * @return True if valid email and password.
   * @throws Exception
   */
  @Override
  public Boolean verifyEmailAndPassword(
    final String email,
    final String password
  ) {
    String emailPattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

    boolean isValidEmail = email.matches(emailPattern);
    boolean isValidEmail2 = email.endsWith("@nucleusteq.com");
    boolean isValidPassword = password.length() >= MIN_SIZE_PASSWORD
      ? true
      : false;

    if (isValidEmail && isValidPassword && isValidEmail2) {
      return true;
    }
    return false;
  }

  @Override
  public final List<MemberOutDto> getAllmember() {
    List<MemberOutDto> memberOutDtos = new ArrayList<>();
    List<Member> members = this.memberRepo.findAll();

    members.forEach(m ->
      memberOutDtos.add(this.mapper.map(m, MemberOutDto.class))
    );

    return memberOutDtos;
  }

  /**
   * Creates a new member if the provided credentials match those of an admin member.
   * This method requires administrator privileges.
   *
   * @param memberDto The data transfer object containing member information.
   * @param departmentId The unique identifier of the department to which the member will be added.
   * @param email The email address of the administrator member.
   * @param password The password of the administrator member.
   * @return The created member represented as a MemberOutDto if the credentials match;
   *         otherwise, returns null.
   */
  @Override
  public final MemberOutDto createMember2(
    final MemberDto memberDto,
    final Integer departmentId,
    final String email,
    final String password
  ) {
    Member member = this.memberRepo.findByEmail(email);
    if (member != null) {
      if (
        member.getPassword().equals(password) &&
        member.getRole() != null &&
        member.getRole().equals(Role.ADMIN)
      ) {
    	  Department department =
          this.departmentRepo.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("Department", "departmentId", departmentId));
//        memberDto.setDepartment(departmentDto);

        Member member2 = this.mapper.map(memberDto, Member.class);
        member2.setDepartment(department);
        Member savedMember = this.memberRepo.save(member2);

        MemberOutDto memberOutDto2 =
          this.mapper.map(savedMember, MemberOutDto.class);
        return memberOutDto2;
      }
    }
    return null;
  }


}
