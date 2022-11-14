package com.BlogApp.service;

import java.util.List;

import com.BlogApp.model.Comment;
import com.BlogApp.payloads.CommentDto;

public interface CommentService {

	Comment addComment(CommentDto commentDto,Integer userId,Integer postId);

	Comment deleteComment(Integer commentId);

	Comment updateComment(CommentDto commentDto,Integer commentId);
	
	List<Comment> findByPostId(Integer postId);


}
