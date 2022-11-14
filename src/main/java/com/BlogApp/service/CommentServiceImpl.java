package com.BlogApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApp.Exceptions.ResourceNotFoundException;
import com.BlogApp.model.Comment;
import com.BlogApp.model.Post;
import com.BlogApp.model.User;
import com.BlogApp.payloads.CommentDto;
import com.BlogApp.repo.CategoryRepo;
import com.BlogApp.repo.CommentRepo;
import com.BlogApp.repo.PostRepo;
import com.BlogApp.repo.UserRepo;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Override
	public Comment addComment(CommentDto commentDto, Integer userId, Integer postId) {

        Optional<User> optUser=userRepo.findById(userId);

		Optional<Post> optPost=postRepo.findById(postId);

		if(!optUser.isPresent())
		{
			throw new ResourceNotFoundException("User","Id", userId);
		}
		if(!optPost.isPresent())
		{
			throw new ResourceNotFoundException("Category","Id", postId);
		}

		Comment comment=new Comment();
		comment.setContent(commentDto.getContent());
		comment.setPost(optPost.get());
		comment.setUser(optUser.get());

		return commentRepo.save(comment);
	}
	@Override
	public Comment deleteComment(Integer commentId) {

		    Optional<Comment> opt=commentRepo.findById(commentId);

			if(!opt.isPresent())
			{
				throw new ResourceNotFoundException("Comment","Id", commentId);
			}

			Comment comment=opt.get();
			
			Post post=comment.getPost();
			List<Comment> list=post.getComments();
			
			list.remove(comment);
			commentRepo.delete(comment);		
			postRepo.save(post);
			return comment;
			
	}
	@Override
	public Comment updateComment(CommentDto commentDto, Integer commentId) {

		Optional<Comment> opt=commentRepo.findById(commentId);

		if(!opt.isPresent())
		{
			throw new ResourceNotFoundException("Comment","Id", commentId);
		}

		Comment comment=opt.get();
		comment.setContent(commentDto.getContent());

		return commentRepo.save(comment);

	}
	@Override
	public List<Comment> findByPostId(Integer postId) {
		
//		Optional<Post> opt=commentRepo.findById(commentId);
//
//		if(!opt.isPresent())
//		{
//			throw new ResourceNotFoundException("Comment","Id", commentId);
//		}
//		return null;
		return commentRepo.findByPostId(postId);
	}



}
