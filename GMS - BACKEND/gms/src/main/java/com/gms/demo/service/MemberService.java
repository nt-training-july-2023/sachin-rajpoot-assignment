package com.gms.demo.service;


import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberLoginDto;

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
  MemberDto createMember(MemberDto memberDto);

  /**
     * Perform member login.
     *
     * @param memberLoginDto The MemberLoginDto containing login credentials.
     * @return True if login is successful, false otherwise.
 * @throws Exception
     */
  Boolean login(MemberLoginDto memberLoginDto) throws Exception;


}

