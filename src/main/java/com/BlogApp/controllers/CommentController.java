package com.BlogApp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApp.model.Comment;
import com.BlogApp.payloads.CommentDto;
import com.BlogApp.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	
	@PostMapping("/create/{userId}/{postId}")
	public ResponseEntity<Comment> createComment(@Valid @PathVariable Integer postId,@RequestBody CommentDto commentDto)
	{
		Comment comment=commentService.addComment(commentDto, postId);
		return new ResponseEntity<>(comment,HttpStatus.CREATED);
	}

	
	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<Comment> deleteCommentById(@PathVariable Integer commentId)
	{
		Comment comment=commentService.deleteComment(commentId);
		return new ResponseEntity<>(comment,HttpStatus.OK);
	}

	@PostMapping("/update/{commentId}")
	public ResponseEntity<Comment> updateComment(@Valid @RequestBody CommentDto commentDto,@PathVariable Integer commentId)
	{
		Comment comment=commentService.updateComment(commentDto, commentId);
		return new ResponseEntity<>(comment,HttpStatus.CREATED);
	}
	
	@GetMapping("/getByPostId/{postId}")
	public ResponseEntity<List<Comment>> getByPostId(@PathVariable Integer postId)
	{
		List<Comment> list=commentService.findByPostId(postId);
		return new ResponseEntity<List<Comment>>(list,HttpStatus.OK);
	}


}
