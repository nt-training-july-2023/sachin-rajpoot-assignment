package com.gms.demo.serviceimpl;

import com.gms.demo.exception.ResourceNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gms.demo.entity.Comment;
import com.gms.demo.entity.Ticket;
import com.gms.demo.payloads.CommentDto;
import com.gms.demo.payloads.CommentOutDto;
import com.gms.demo.payloads.TicketDto;
import com.gms.demo.repo.CommentRepo;
import com.gms.demo.repo.TicketRepo;
import com.gms.demo.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	TicketRepo ticketRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	CommentRepo commentRepo;
	@Override
	public CommentOutDto createComment(Integer ticketId, CommentDto commentDto) {
		Ticket ticket = this.ticketRepo.findById(ticketId) 
				.orElseThrow(() ->
		          new ResourceNotFoundException(
		            "Ticket",
		            "Ticket ID",
		            ticketId
		          )
		        );
		commentDto.setTicket(this.modelMapper.map(ticket, TicketDto.class));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		return this.modelMapper.map(this.commentRepo.save(comment), CommentOutDto.class);
	}

	

	
}
