package com.gms.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
import com.google.common.net.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gms.demo.payloads.TicketDto;
import com.gms.demo.service.TicketService;

@RestController
@RequestMapping("/api/ticket/")
public class TicketController {

	@Autowired
	TicketService ticketService;
	
	@CrossOrigin
	@PostMapping(value = "create/memberId/{memberId}/departmentId/{departmentId}",  consumes={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<TicketDto> createTicket( @Valid @RequestBody final TicketDto ticketDto, @PathVariable Integer memberId
			,@PathVariable Integer departmentId){
		return new ResponseEntity<>(this.ticketService.createTicket(ticketDto, memberId, departmentId),HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@GetMapping("getAll")
	public ResponseEntity<List<TicketDto>> getAllTicket(){
		return new ResponseEntity<>(this.ticketService.getAllTicket(),HttpStatus.OK);
	}
	
}
