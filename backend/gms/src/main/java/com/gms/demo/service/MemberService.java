package com.gms.demo.service;

import com.gms.demo.payloads.ApiResponse;
import com.gms.demo.payloads.MemberChangePasswordDto;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberGetAllOutDto;
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
   * creates the member.
   *
   * @param memberDto member Dto
   * @param email email
   * @param password password
   * @return MemberOutDto Member Out Dto
   */
  ApiResponse createMember3(
      MemberDto memberDto,
      String email,
      String password
  );

  /**
   * get All Members.
   *
   * @param email email
   * @param password password
   * @param pageNumber pageNumber
   * @return MemberGetAllOutDto MemberGet All Out Dto
   */
  List<MemberGetAllOutDto> getAllMemberAuth(
      String email,
      String password,
      Integer pageNumber
  );

  /**
   * Perform member login.
   *
   * @param memberLoginDto The MemberLoginDto containing login credentials.
   * @return True if login is successful, false otherwise.
   * @throws Exception Exception
   */
  MemberOutDto login(MemberLoginDto memberLoginDto) throws Exception;

  /**
   * Verifies the email and password combination for authentication.
   *
   * @param email    The email associated with the member.
   * @param password The password provided for authentication.
   * @return true if the email and password combination is valid,
   *     false otherwise.
   */
  Boolean verifyEmailAndPassword(String email, String password);

  /**
   * change Password.
   *
   * @param memberId member Id
   * @param changePasswordDto change Password Dto
   * @return MemberOutDto Member Out Dto
   */
  MemberOutDto changePassword(
      Integer memberId,
      MemberChangePasswordDto changePasswordDto
  );

  /**
   *delete Member .
   *
   * @param memberId member Id
   * @return ApiResponse Api Response
   */
  ApiResponse deleteMember(Integer memberId);
}
