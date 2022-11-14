package com.BlogApp.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.BlogApp.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {
	


	private String name;

}
