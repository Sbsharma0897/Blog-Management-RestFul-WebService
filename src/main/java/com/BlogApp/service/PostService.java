package com.BlogApp.service;

import java.util.List;

import com.BlogApp.model.Post;
import com.BlogApp.payloads.PostDto;
import com.BlogApp.payloads.PostResponse;

public interface PostService {

	Post createPost(PostDto postDto,Integer categoryId);

	Post updatePost(PostDto postDto, Integer postId);

	Post deletePost(Integer postId);

	Post updateCategoryOfPost(Integer postId, Integer categoryId);

	List<Post> getAllPost();

	PostResponse getAllPostPagination(Integer pageSize, Integer pageNumer,String sortBy);

	Post getPostById(Integer postId);

	List<Post> getPostByCategory(Integer categoryId);

	List<Post> getPostByUserId(Integer userId);



}
