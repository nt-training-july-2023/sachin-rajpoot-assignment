package com.gms.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gms.demo.payloads.CommentDto;
import com.gms.demo.payloads.CommentOutDto;
import com.gms.demo.service.CommentService;

@RestController
@RequestMapping("/api/comment/")
public class CommentController {

	@Autowired
	CommentService commentService;

	@CrossOrigin
	@PostMapping("create/ticketId/{ticketId}")
	public ResponseEntity<CommentOutDto> createComment(@RequestBody CommentDto commentDto,
			@PathVariable Integer ticketId) {
		System.out.println(commentDto);
		return new ResponseEntity<CommentOutDto>(this.commentService.createComment(ticketId, commentDto),
				HttpStatus.OK);
	}
}
