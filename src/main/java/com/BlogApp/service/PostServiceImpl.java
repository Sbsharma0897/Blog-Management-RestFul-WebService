package com.BlogApp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.BlogApp.Exceptions.ResourceNotFoundException;
import com.BlogApp.model.Category;
import com.BlogApp.model.Post;
import com.BlogApp.model.User;
import com.BlogApp.payloads.PostDto;
import com.BlogApp.payloads.PostResponse;
import com.BlogApp.repo.CategoryRepo;
import com.BlogApp.repo.PostRepo;
import com.BlogApp.repo.UserRepo;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;


	@Override
	public Post createPost(PostDto postDto, Integer categoryId, Integer userId) {

		Optional<User> optUser=userRepo.findById(userId);

		Optional<Category> optCategory=categoryRepo.findById(categoryId);

		if(!optUser.isPresent())
		{
			throw new ResourceNotFoundException("User","Id", userId);
		}
		if(!optCategory.isPresent())
		{
			throw new ResourceNotFoundException("Category","Id", categoryId);
		}

		Post post =new Post();
		post.setCategory(optCategory.get());
		post.setUser(optUser.get());
		post.setContent(postDto.getContent());
		post.setDate_added(LocalDate.now());
		post.setTitle(postDto.getTitle());

		return postRepo.save(post);



	}


	@Override
	public Post updatePost(PostDto postDto, Integer postId) {

		Optional<Post> optional=postRepo.findById(postId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("Post", "Id", postId);
		}

		Post post=optional.get();

		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());

		return postRepo.save(post);
	}
	@Override
	public Post updateCategoryOfPost(Integer postId, Integer categoryId) {

		Optional<Post> optional1=postRepo.findById(postId);
		if(!optional1.isPresent())
		{
			throw new ResourceNotFoundException("Post", "Id", postId);
		}
		Optional<Category> optional2=categoryRepo.findById(categoryId);
		if(!optional2.isPresent())
		{
			throw new ResourceNotFoundException("Category", "Id", categoryId);
		}

		Post post=optional1.get();
		Category category=optional2.get();

		post.setCategory(category);

		return postRepo.save(post);
	}

	@Override
	public Post deletePost(Integer postId) {

		Optional<Post> optional=postRepo.findById(postId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("Post", "Id", postId);
		}

		Post post=optional.get();
		postRepo.delete(post);

		return post;
	}



	@Override
	public List<Post> getAllPost() {

		return postRepo.findAll();

	}
    @Override
	public PostResponse getAllPostPagination(Integer pageSize, Integer pageNumer,String sortBy) {

    	org.springframework.data.domain.Pageable p=PageRequest.of(pageNumer, pageSize,org.springframework.data.domain.Sort.by(sortBy));

    	Page<Post> posts=postRepo.findAll(p);
    	List<Post> list=posts.getContent();

    	PostResponse response=new PostResponse();
    	response.setContent(list);
    	response.setLastPage(posts.isLast());
    	response.setPageNumber(posts.getNumber());
    	response.setPageSize(posts.getSize());
    	response.setTotalElements(posts.getTotalElements());
    	response.setTotalPages(posts.getTotalPages());

    	return response;



	}


	@Override
	public Post getPostById(Integer postId) {

		Optional<Post> optional=postRepo.findById(postId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("Post", "Id", postId);
		}

		return optional.get();

	}

	@Override
	public List<Post> getPostByCategory(Integer categoryId) {

		Optional<Category> optional=categoryRepo.findById(categoryId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("Category","Id", categoryId);
		}

		List<Post> list= postRepo.findByCategory(optional.get());
		System.out.println(list);
		return list;

	}

	@Override
	public List<Post> getPostByUserId(Integer userId) {
		Optional<User> optional=userRepo.findById(userId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("User","Id", userId);
		}

		List<Post> list= postRepo.findByUser(optional.get());

		return list;
	}


}
