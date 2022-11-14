package com.BlogApp.payloads;


import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.BlogApp.model.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
	
	
	@Column(length=100)
	@NotNull
	private String CategoryName;
	@NotNull
	private String CategoryDescription;


}
