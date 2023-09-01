package com.gms.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberLoginDto;
import com.gms.demo.service.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Unit tests for the MemberController class. Covers various scenarios related
 * to member login. Utilizes Mockito and MockMvc for mocking and testing.
 * 
 * @author SACHIN SINHH.
 * @version 1.0
 * @since Begining of time
 */
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {
	/** The MemberService instance for testing. */
	@Mock
	private MemberService memberService;

	/** The MemberController instance for testing. */
	@InjectMocks
	private MemberController memberController;

	/** The MemberLoginDto instance for testing. */
	private MemberLoginDto memberLoginDto = new MemberLoginDto("supersaiyan@nucleusteq.com", "132642677");

	/**
	 * Set up method to initialize mocks and the mockMvc instance.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test for successful member login.
	 * 
	 * @throws Exception
	 */
	@Test
	public void loginSuccess() throws Exception {
		ResponseEntity<?> responseEntity = new ResponseEntity<>("Login Successfully", HttpStatus.ACCEPTED);

		Mockito.when(this.memberService.login(memberLoginDto)).thenReturn(true);

		ResponseEntity<?> responseEntityWeGet = memberController.login(memberLoginDto);
		assertEquals(responseEntity, responseEntityWeGet);
	}

	/**
	 * Test for failed member login due to invalid credentials.
	 * 
	 * @throws Exception
	 */
	@Test
	public void loginfail() throws Exception {
		ResponseEntity<?> responseEntity = new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);

		MemberLoginDto memberLoginDto2 = new MemberLoginDto("supersaiyan@cleusteq.com", "132642677");
		Mockito.when(this.memberService.login(memberLoginDto2)).thenReturn(false);
		ResponseEntity<?> responseEntityWeGet = memberController.login(memberLoginDto2);
		assertEquals(responseEntity, responseEntityWeGet);
	}

	/**
	 * Test for successful member creation.
	 */
	@Test
	public void createUserTest() {
		MemberDto memberDto2 = new MemberDto(1, "Naruto", "supersaiyan@nucleusteq.com", "132642677");
		ResponseEntity<MemberDto> responseEntity = new ResponseEntity<>(memberDto2, HttpStatus.CREATED);
		Mockito.when(this.memberService.createMember(memberDto2)).thenReturn(memberDto2);
		ResponseEntity<MemberDto> actualEntity = this.memberController.createMember(memberDto2);
		assertEquals(responseEntity, actualEntity);
	}

}
