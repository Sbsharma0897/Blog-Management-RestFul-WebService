package com.BlogApp.service;

import java.util.List;

import com.BlogApp.model.Role;
import com.BlogApp.model.User;
import com.BlogApp.payloads.RoleDto;

public interface RoleService {

	Role addRole(RoleDto roleDto);
	
	Role addRoleToUser(Integer userId, Integer roleId);
	
	List<User> viewUsersInRole(Integer roleId);
}
