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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApp.model.Post;
import com.BlogApp.payloads.PostDto;
import com.BlogApp.payloads.PostResponse;
import com.BlogApp.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/create/{userId}/{categoryId}")
	public ResponseEntity<Post> createPost(@Valid @RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId)
	{
		Post post =postService.createPost(postDto, categoryId, userId);
		return new ResponseEntity<>(post,HttpStatus.CREATED);
	}
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<Post>> getPostByCategoryId(@Valid @PathVariable Integer categoryId)
	{
		List<Post> list=postService.getPostByCategory(categoryId);
		return new ResponseEntity<>(list,HttpStatus.CREATED);
	}
	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<Post> deletePostById(@Valid @PathVariable Integer postId)
	{
		Post post=postService.deletePost(postId);
		return new ResponseEntity<>(post,HttpStatus.OK);
	}
	@PutMapping("/update/{postId}")
	public ResponseEntity<Post> updatePost(@Valid @PathVariable Integer postId,@RequestBody PostDto postDto)
	{
		Post post=postService.updatePost(postDto, postId);
		return new ResponseEntity<>(post,HttpStatus.OK);
	}
	@PutMapping("/updateCategory/{postId}/{categoryId}")
	public ResponseEntity<Post> updateCategoryOfPost(@Valid @PathVariable Integer postId,@PathVariable Integer categoryId)
	{
		Post post=postService.updateCategoryOfPost(postId, categoryId);
		return new ResponseEntity<>(post,HttpStatus.OK);
	}
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Post>> getPostByUserId(@Valid @PathVariable Integer userId)
	{
		List<Post> list=postService.getPostByUserId(userId);
		return new ResponseEntity<>(list,HttpStatus.CREATED);
	}
	@GetMapping("/")
	public ResponseEntity<List<Post>> getAllPost()
	{
		List<Post> list=postService.getAllPost();
		return new ResponseEntity<>(list,HttpStatus.CREATED);
	}
	@GetMapping("/pagination")
	public ResponseEntity<PostResponse> getAllPostPagination(@RequestParam("pageSize") Integer pageSize,@RequestParam("pageNumer") Integer pageNumber,
			@RequestParam("sortBy") String sortBy)
	{
		PostResponse list=postService.getAllPostPagination(pageSize, pageNumber,sortBy);
		return new ResponseEntity<>(list,HttpStatus.CREATED);
	}
	@GetMapping("/postId/{postId}")
	public ResponseEntity<Post> getPostByPostId(@Valid @PathVariable Integer postId)
	{
		Post post=postService.getPostById(postId);
		return new ResponseEntity<>(post,HttpStatus.OK);
	}



}
