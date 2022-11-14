package com.BlogApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BlogApp.model.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer>{
	
 List<Comment> findByPostId(Integer postId);

}
