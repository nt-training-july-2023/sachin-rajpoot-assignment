package com.gms.demo.service;

import com.gms.demo.payloads.CommentDto;
import com.gms.demo.payloads.CommentOutDto;

public interface CommentService {
	CommentOutDto createComment(Integer ticketId, CommentDto commentDto);
}
                                                                   