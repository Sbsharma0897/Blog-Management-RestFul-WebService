package com.BlogApp.service;

import java.util.List;

import com.BlogApp.Exceptions.ResourceNotFoundException;
import com.BlogApp.model.Category;
import com.BlogApp.payloads.CategoryDto;

public interface CategoryService {

	Category createCategory(CategoryDto categoryDto);

	Category updateCategory(CategoryDto categoryDto,Integer categoryId) throws ResourceNotFoundException;

	Category deleteCategory(Integer categoryId ) throws ResourceNotFoundException;

	Category getCategoryById(Integer categoryId ) throws ResourceNotFoundException;

	List<Category>  getAllCategory();


}
