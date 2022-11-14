package com.BlogApp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApp.Exceptions.ResourceNotFoundException;
import com.BlogApp.model.Role;
import com.BlogApp.payloads.UserDto;
import com.BlogApp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto userDto2=userService.createUser(userDto);
		return new ResponseEntity<>(userDto2,HttpStatus.CREATED);
	}
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto> updateUser( @Valid @RequestBody UserDto userDto,@PathVariable Integer userId) throws ResourceNotFoundException
	{
		UserDto userDto2=userService.updateUser(userDto, userId);
		return new ResponseEntity<>(userDto2,HttpStatus.OK);
	}
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@Valid @PathVariable Integer userId) throws ResourceNotFoundException
	{
		UserDto userDto2=userService.getUserById(userId);
		return new ResponseEntity<>(userDto2,HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		List<UserDto> list=userService.getAllUsers();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('normal')")
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<UserDto> deleteUser(@Valid @PathVariable Integer userId) throws ResourceNotFoundException
	{
		UserDto userDto2=userService.deleteUser(userId);
		return new ResponseEntity<>(userDto2,HttpStatus.OK);
	}
	
	@GetMapping("/getRolesOfUser/{userId}")
	public ResponseEntity<List<Role>> getRolesOfUser(@Valid @PathVariable Integer userId)
	{
		List<Role> list=userService.getRolesOfUserById(userId);
		return new ResponseEntity<List<Role>>(list,HttpStatus.OK);
	}

}
