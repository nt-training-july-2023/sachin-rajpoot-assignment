package com.gms.demo.controller;

import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberLoginDto;
import com.gms.demo.payloads.MemberOutDto;
import com.gms.demo.service.MemberService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
 * operations.
 * This controller provides functionality for member login and member creation.
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
//  @Autowired
  private MemberService memberService;
  
  @Autowired
	public MemberService setMemberService(MemberService memberService) {
		this.memberService=memberService;
		return memberService;
	}

  /**
   * Handles member login.
   *
   * @param memberLoginDto The data transfer object containing login credentials.
   * @return ResponseEntity indicating the status of the login attempt.
   * @throws Exception If an error occurs during the login process.
   */
  @CrossOrigin
  @PostMapping("login")
  public ResponseEntity<?> login(
    @RequestBody @Valid final MemberLoginDto memberLoginDto
  ) throws Exception {
    System.out.println(memberLoginDto);

    if (
      this.memberService.verifyEmailAndPassword(
          memberLoginDto.getEmail(),
          memberLoginDto.getPassword()
        )
      && this.memberService.login(memberLoginDto)
    ) {
      System.out.println("Login Successfully");
      return new ResponseEntity<>("Login Successfully", HttpStatus.ACCEPTED);
    }
    return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
  }

  /**
   * Creates a new member and associates them with a department.
   *
   * @param memberDto    The data transfer object containing member information.
   * @param departmentId The ID of the department to which the member is
   *                     associated.
   * @return ResponseEntity containing the newly created member's details.
   */
  @CrossOrigin
  @PostMapping("member/departmentId/{departmentId}")
  public final ResponseEntity<MemberOutDto> createMember(
    @RequestBody @Valid final MemberDto memberDto,
    @PathVariable final Integer departmentId
  ) {
    if (
      this.memberService.verifyEmailAndPassword(
          memberDto.getEmail(),
          memberDto.getPassword()
        )
    ) {
      return new ResponseEntity<>(
        this.memberService.createMember(memberDto, departmentId),
        HttpStatus.CREATED
      );
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  /**
   * Creates a new member with email and password authentication and associates
   * them with a department.
   *
   * @param memberDto    The data transfer object containing member information.
   * @param departmentId The ID of the department to which the member is
   *                     associated.
   * @param email        The email associated with the member for authentication.
   * @param password     The password associated with the member for
   *                     authentication.
   * @return ResponseEntity containing the newly created member's details if
   *         authentication is successful;
   *         otherwise, returns HTTP status 401 (Unauthorized).
   */
  @CrossOrigin
  @PostMapping("departmentId/{departmentId}")
  public final ResponseEntity<MemberOutDto> createMember2(
    @RequestBody @Valid final MemberDto memberDto,
    @PathVariable final Integer departmentId,
    @RequestHeader final String email,
    @RequestHeader final String password
  ) {
    System.out.println("Role " + memberDto.getRole());
    System.out.println("Member DTO " + memberDto);
    System.out.println("Email " + email);
    System.out.println("Password " + password);

    MemberOutDto memberDto2 =
      this.memberService.createMember2(
          memberDto,
          departmentId,
          email,
          password
        );
    if (memberDto2 != null) {
      return new ResponseEntity<>(memberDto2, HttpStatus.CREATED);
    }
    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
  }

  /**
   * Retrieves a list of all members.
   *
   * @return ResponseEntity containing a list of MemberOutDto objects with HTTP
   *         status 200 (OK).
   */
  @CrossOrigin
  @GetMapping("member/getAll")
  public final ResponseEntity<List<MemberOutDto>> getAllMember() {
    return new ResponseEntity<>(
      this.memberService.getAllmember(),
      HttpStatus.OK
    );
  }
  
  
  @CrossOrigin
  @GetMapping("member/getAll/auth")
  public final ResponseEntity<List<MemberOutDto>> getAllMemberAuth(
		    @RequestHeader final String email,
		    @RequestHeader final String password
		  ) {
    return new ResponseEntity<>(
      this.memberService.getAllMemberAuth(email, password),
      HttpStatus.OK
    );
  }
  
  @CrossOrigin
  @PutMapping("changepassword/memberId/{memberId}/oldpassword/{oldPassword}/newpassword/{newPassword}")
  public final ResponseEntity<MemberOutDto> changePassword(
		  @PathVariable final Integer memberId,
		    @PathVariable final String oldPassword,
		    @PathVariable final String newPassword
		    ) {

		      return new ResponseEntity<>( this.memberService.changePassword(
		    		  memberId,
		    		  oldPassword,
		    		  newPassword
		        ), HttpStatus.OK);
		    }
//	  return null;
  }

