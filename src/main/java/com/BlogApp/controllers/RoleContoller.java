package com.BlogApp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApp.model.Role;
import com.BlogApp.model.User;
import com.BlogApp.payloads.RoleDto;
import com.BlogApp.payloads.UserDto;
import com.BlogApp.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleContoller {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/create")
	public ResponseEntity<Role> createRole(@Valid @RequestBody RoleDto roleDto)
	{
		Role role=roleService.addRole(roleDto);
		return new ResponseEntity<>(role,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addRoleToUser/{roleId}/{userId}")
	public ResponseEntity<User> addRoleToUser(@Valid @PathVariable Integer roleId, @PathVariable Integer userId)
	{
		User user=roleService.addRoleToUser(userId, roleId);
		return new ResponseEntity<User>(user,HttpStatus.CREATED);
	}
	

	

}
