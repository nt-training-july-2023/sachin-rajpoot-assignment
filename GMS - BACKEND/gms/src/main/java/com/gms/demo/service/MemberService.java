package com.gms.demo.service;


import java.util.List;

import com.gms.demo.entity.Role;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberLoginDto;
import com.gms.demo.payloads.MemberOutDto;

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
     * @return The created MemberDto.
     */
  MemberDto createMember(MemberDto memberDto, final Integer departmentId);
  
  MemberOutDto createMember2(MemberDto memberDto, final Integer departmentId, String email, String password);
  
  List<MemberOutDto> getAllmember();

  /**
     * Perform member login.
     *
     * @param memberLoginDto The MemberLoginDto containing login credentials.
     * @return True if login is successful, false otherwise.
 * @throws Exception
     */
  Boolean login(MemberLoginDto memberLoginDto) throws Exception;
  
  Boolean verifyEmailAndPassword(final String email, final String password);


}

