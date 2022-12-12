package com.BlogApp.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApp.model.Category;
import com.BlogApp.payloads.CategoryDto;
import com.BlogApp.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryDto categoryDto) throws URISyntaxException, IOException, InterruptedException
	{
		
		Category category2=categoryService.createCategory(categoryDto);
		return new ResponseEntity<Category>(category2,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update")
	public ResponseEntity<Category> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@RequestParam Integer categoryId)
	{
		Category category2=categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<Category>(category2,HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete")
	public ResponseEntity<Category> deleteCategory(@Valid @RequestParam Integer categoryId)
	{
		Category category2=categoryService.deleteCategory(categoryId);
		return new ResponseEntity<Category>(category2,HttpStatus.OK);
	}
	
	@GetMapping("/getCategory")
	public ResponseEntity<Category> getCategoryById(@Valid @RequestParam Integer categoryId)
	{
		Category category2=categoryService.getCategoryById(categoryId);
		return new ResponseEntity<Category>(category2,HttpStatus.OK);
	}
	
	@GetMapping("/getAllCategory")
	public ResponseEntity<List<Category>> getAllCategory()
	{
		List<Category> category2=categoryService.getAllCategory();
		return new ResponseEntity<List<Category>>(category2,HttpStatus.OK);
	}


}
