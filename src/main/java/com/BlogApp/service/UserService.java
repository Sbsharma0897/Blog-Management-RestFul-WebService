package com.BlogApp.service;

import java.util.List;

import com.BlogApp.Exceptions.ResourceNotFoundException;
import com.BlogApp.model.Role;
import com.BlogApp.payloads.UserDto;

public interface UserService {
	
	
    UserDto registerUser(UserDto userDto);
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId) throws ResourceNotFoundException ;
	UserDto getUserById(Integer userId) throws ResourceNotFoundException ;
	List<UserDto> getAllUsers();
	UserDto deleteUser(Integer userId) throws ResourceNotFoundException ;
	List<Role> getRolesOfUserById(Integer userId);


}
