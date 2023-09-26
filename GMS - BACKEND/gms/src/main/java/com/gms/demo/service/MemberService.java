package com.gms.demo.service;

import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberLoginDto;
import com.gms.demo.payloads.MemberOutDto;
import java.util.List;

/**
 * Represents a service interface for Member-related operations.
 * Contains methods for creating members and performing login.
 *
 * @author Sachin Singh Rajpoot
 * @version 1.0
 * @since Begining of time
 */
public interface MemberService {
  /**
   * Create a new member.
   *
   * @param memberDto The MemberDto containing member details.
   * @param departmentId
   * @return The created MemberDto.
   */
  MemberOutDto createMember(MemberDto memberDto, Integer departmentId);

  /**
   * Creates a new member based on the provided member DTO, department ID, email, and password.
   *
   * @param memberDto     The DTO containing member information.
   * @param departmentId  The unique identifier of the department to which the member belongs.
   * @param email         The email associated with the member.
   * @param password      The password associated with the member.
   * @return The created member as a MemberOutDto if successful, otherwise null.
   */
  MemberOutDto createMember2(
    MemberDto memberDto,
    Integer departmentId,
    String email,
    String password
  );

  /**
   * Retrieves a list of all members as MemberOutDto objects.
   *
   * @return A list of MemberOutDto objects representing all members.
   */
  List<MemberOutDto> getAllmember();

  /**
   * Perform member login.
   *
   * @param memberLoginDto The MemberLoginDto containing login credentials.
   * @return True if login is successful, false otherwise.
   * @throws Exception
   */
  Boolean login(MemberLoginDto memberLoginDto) throws Exception;

  /**
   * Verifies the email and password combination for authentication.
   *
   * @param email    The email associated with the member.
   * @param password The password provided for authentication.
   * @return true if the email and password combination is valid, false otherwise.
   */
  Boolean verifyEmailAndPassword(String email, String password);
  
  
  
  
  MemberOutDto changePassword(Integer memberId, String oldPassword, String newPassword);
}