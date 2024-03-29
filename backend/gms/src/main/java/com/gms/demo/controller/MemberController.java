package com.gms.demo.controller;

import com.gms.demo.payloads.ApiResponse;
import com.gms.demo.payloads.MemberChangePasswordDto;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberGetAllOutDto;
import com.gms.demo.payloads.MemberLoginDto;
import com.gms.demo.payloads.MemberOutDto;
import com.gms.demo.service.MemberService;
import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The MemberController class handles API endpoints related to member
 * operations. This controller provides functionality for member login and
 * member creation.
 *
 * @author Sachin Singh Rajpoot
 * @version 1.0
 * @since Begining of time
 */
@RestController
@RequestMapping("/api/")
public class MemberController {

  /**
   * The Member Service instance for handling member-related operations.
   */
  @Autowired
  private MemberService memberService;

  /**
   * Setter method for injecting  MemberService.
   *
   * @param memberServicex The MemberService to be injected.
   * @return The injected  MemberService.
   */
  @Autowired
  public final MemberService setMemberService(
      final MemberService memberServicex
  ) {
    this.memberService = memberServicex;
    return memberService;
  }

  /**
   * Logger for logging.
   */
  private static final Logger DISPLAY = LoggerFactory
      .getLogger(DepartmentController.class);

  /**
   * Handles member login.
   *
   * @param memberLoginDto The data transfer object containing
   *     login credentials.
   * @return ResponseEntity indicating the status of the login attempt.
   * @throws Exception If an error occurs during the login process.
   */
  @CrossOrigin
  @PostMapping("login")
  public final ResponseEntity<?> login(
      @RequestBody @Valid final MemberLoginDto memberLoginDto
  ) throws Exception {
    DISPLAY.info("Inside Controller");
    MemberOutDto memberOutDto = this.memberService.login(memberLoginDto);
    System.out.println("Member Out Dto received back " + memberOutDto);
    if (memberOutDto != null) {
      return new ResponseEntity<>(memberOutDto, HttpStatus.ACCEPTED);
    }
    return new ResponseEntity<>(
      new ApiResponse("Invalid Credentials, Please try again.", false),
      HttpStatus.BAD_REQUEST
    );
  }

  /**
   * Creates a new member without specifying a department.
   *
   * @param memberDto The MemberDto containing member data.
   * @param email     The email header used for verification.
   * @param password  The password header used for verification.
   * @return A ResponseEntity containing the created MemberOutDto
   *     and an HTTP status code.
   */
  @CrossOrigin
  @PostMapping("create/")
  public final ResponseEntity<?> createMember3(
      @RequestBody @Valid final MemberDto memberDto,
      @RequestHeader final String email,
      @RequestHeader final String password
  ) {
    DISPLAY.info("Inside Controller");
    return new ResponseEntity<>(this.memberService
        .createMember3(memberDto, email, password),
        HttpStatus.OK);
  }
  /**
   * Retrieves a list of authenticated members with pagination.
   *
   * @param email      The email header used for authentication.
   * @param password   The password header used for authentication.
   * @param pageNumber The page number for pagination.
   * @return A ResponseEntity containing a list of
   *     authenticated MemberGetAllOutDto.
   */

  @CrossOrigin
  @GetMapping("member/getAll/auth/pageNumber/{pageNumber}")
  public final ResponseEntity<List<MemberGetAllOutDto>> getAllMemberAuth(
      @RequestHeader final String email,
      @RequestHeader final String password,
      @PathVariable final Integer pageNumber
  ) {
    DISPLAY.info("Inside Controller");
    return new ResponseEntity<>(
      this.memberService
      .getAllMemberAuth(email, password, pageNumber),
      HttpStatus.OK
    );
  }

  /**
   * Changes the password of a member.
   *
   * @param memberId The ID of the member whose password will
   *     be changed.
   * @param changePasswordDto The DTO containing the
   *     new password information.
   * @return A ResponseEntity containing the updated MemberOutDto
   */
  @CrossOrigin
  @PutMapping("changepassword/memberId/{memberId}")
  public final ResponseEntity<?> changePassword(
      @PathVariable final Integer memberId,
      @RequestBody final MemberChangePasswordDto changePasswordDto
  ) {
    DISPLAY.info("Inside Controller");
    MemberOutDto memberOutDto =
        this.memberService.changePassword(memberId, changePasswordDto);
    if (memberOutDto != null) {
      return new ResponseEntity<>(memberOutDto, HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ApiResponse("Incorrect Password, Please try again.", false),
      HttpStatus.BAD_REQUEST
    );
  }

  /**
   * Deletes a member by their ID.
   *
   * @param memberId The ID of the member to be deleted.
   * @return A ResponseEntity containing an ApiResponse and
   *     an HTTP status code.
   */
  @CrossOrigin
  @DeleteMapping("member/delete/memberId/{memberId}")
  public final ResponseEntity<ApiResponse> deleteMember(
      @PathVariable @Valid final Integer memberId
  ) {
    DISPLAY.info("Inside Controller");
    ApiResponse apiResponse = this.memberService.deleteMember(memberId);
    if (apiResponse != null) {
      return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
    return new ResponseEntity<ApiResponse>(HttpStatus.BAD_REQUEST);
  }
}
