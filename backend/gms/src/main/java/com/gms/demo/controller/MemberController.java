package com.gms.demo.controller;

import com.gms.demo.payloads.ApiResponse;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberGetAllOutDto;
import com.gms.demo.payloads.MemberLoginDto;
import com.gms.demo.payloads.MemberOutDto;
import com.gms.demo.service.MemberService;
import java.util.List;
import javax.validation.Valid;
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
	private MemberService memberService;

	@Autowired
	public final MemberService setMemberService(final MemberService memberService) {
		this.memberService = memberService;
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
	public ResponseEntity<?> login(@RequestBody @Valid final MemberLoginDto memberLoginDto) throws Exception {
		System.out.println("Verify email and password"+this.memberService.verifyEmailAndPassword(memberLoginDto.getEmail(), memberLoginDto.getPassword()));
		
		if (this.memberService.verifyEmailAndPassword(memberLoginDto.getEmail(), memberLoginDto.getPassword())) {
			MemberOutDto memberOutDto = this.memberService.login(memberLoginDto);
			System.out.println("Member Out Dto received back "+memberOutDto);
			if (memberOutDto != null) {
				return new ResponseEntity<>(memberOutDto, HttpStatus.ACCEPTED);
			}
		}
		return new ResponseEntity<>("Invalid Credentials, Please try again.", HttpStatus.BAD_REQUEST);
	}


	@CrossOrigin
	@PostMapping("create/nodept")
	public final ResponseEntity<?> createMember3(@RequestBody @Valid final MemberDto memberDto,
			@RequestHeader final String email, @RequestHeader final String password) {
		if (this.memberService.verifyEmailAndPassword(memberDto.getEmail(), memberDto.getPassword())) {
			MemberOutDto memberDto2 = this.memberService.createMember3(memberDto, email, password);
			if (memberDto2 != null) {
				return new ResponseEntity<>(memberDto2, HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<>("Not Authorized",HttpStatus.UNAUTHORIZED);
			}
		}
		return new ResponseEntity<>("invalid credentails, Check email and password",HttpStatus.BAD_REQUEST);
	}



	@CrossOrigin
	@GetMapping("member/getAll/auth/pageNumber/{pageNumber}")
	public final ResponseEntity<List<MemberGetAllOutDto>> getAllMemberAuth(@RequestHeader final String email,
			@RequestHeader final String password
			, @PathVariable Integer pageNumber) {
		return new ResponseEntity<>(this.memberService.getAllMemberAuth(email, password, pageNumber), HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping("changepassword/memberId/{memberId}")
	public final ResponseEntity<?> changePassword(@PathVariable final Integer memberId,
			@RequestHeader final String oldPassword, @RequestHeader final String newPassword) {
		MemberOutDto memberOutDto = this.memberService.changePassword(memberId, oldPassword, newPassword);

		if (memberOutDto != null) {
			return new ResponseEntity<>(memberOutDto, HttpStatus.OK);
		}

		return new ResponseEntity<>(new ApiResponse("Incorrect Password, Please try again.", false),
				HttpStatus.BAD_REQUEST);
	}
	
	@CrossOrigin
	@DeleteMapping("member/delete/memberId/{memberId}")
	public ResponseEntity<ApiResponse> deleteMember(@PathVariable @Valid Integer memberId){
		ApiResponse apiResponse = this.memberService.deleteMember(memberId);
		if(apiResponse != null) {
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		}
		return new ResponseEntity<ApiResponse>( HttpStatus.BAD_REQUEST);
	}
}
