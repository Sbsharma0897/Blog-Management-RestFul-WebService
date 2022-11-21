package com.BlogApp.service;

import java.util.List;
import java.util.Set;

import com.BlogApp.Exceptions.ResourceNotFoundException;
import com.BlogApp.model.Role;
import com.BlogApp.payloads.UserDto;

public interface UserService {
	
	
    UserDto registerNewUser(UserDto userDto);
    UserDto registerNewAdmin(UserDto userDto);
	
	UserDto updateUser(UserDto user) throws ResourceNotFoundException ;
	UserDto getUserById(Integer userId) throws ResourceNotFoundException ;
	List<UserDto> getAllUsers();
	UserDto deleteUser(Integer userId) throws ResourceNotFoundException ;
	Set<Role> getRolesOfUserById(Integer userId);


}
