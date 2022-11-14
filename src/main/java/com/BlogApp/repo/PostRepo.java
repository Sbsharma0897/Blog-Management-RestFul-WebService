package com.BlogApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BlogApp.model.Category;
import com.BlogApp.model.Post;
import com.BlogApp.model.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer>{

//	@Query("select a from Post a where a.category:?")
	List<Post> findByCategory(Category category);

//	@Query("select a from Post a where a.user:?")
	List<Post> findByUser(User user);

}
