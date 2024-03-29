package com.gms.demo.controller;

import com.gms.demo.entity.Department;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberLoginDto;
import com.gms.demo.service.DepartmentService;
import com.gms.demo.service.MemberService;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The MemberController class handles API endpoints related to member
 * operations. This controller provides functionality for member login.
 *
 * @author Sachin Singh Rajpoot
 * @version 1.0
 * @since Begining of time
 */

@RestController
@RequestMapping("/api/")
public class MemberController {
	/** The Member Service instance for testing. */
	@Autowired
	private MemberService memberService;
	


	/**
	 * Handles member login.
	 *
	 * @param memberLoginDto The data transfer object containing login credentials.
	 * @return ResponseEntity indicating the status of the login attempt.
	 * @throws Exception
	 */
	@CrossOrigin
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody @Valid final MemberLoginDto memberLoginDto ) throws Exception {
		System.out.println(memberLoginDto);
		
		if (this.memberService.verifyEmailAndPassword(memberLoginDto.getEmail(), memberLoginDto.getPassword())
				&& this.memberService.login(memberLoginDto)) {
			return new ResponseEntity<>("Login Successfully", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Creates a new member.
	 *
	 * @param memberDto The data transfer object containing member information.
	 * @return ResponseEntity containing the newly created member's details.
	 */
	@CrossOrigin
	@PostMapping("member/departmentId/{departmentId}")
	
	public final ResponseEntity<MemberDto> createMember(@RequestBody @Valid final MemberDto memberDto, @PathVariable Integer departmentId) {

		if (this.memberService.verifyEmailAndPassword(memberDto.getEmail(), memberDto.getPassword())) {
			
			return new ResponseEntity<MemberDto>(this.memberService.createMember(memberDto), HttpStatus.CREATED);
		}
		return new ResponseEntity<MemberDto>(HttpStatus.BAD_REQUEST);
	}
}
