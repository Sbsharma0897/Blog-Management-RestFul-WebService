package com.BlogApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApp.Exceptions.ResourceNotFoundException;

import com.BlogApp.model.Role;
import com.BlogApp.model.User;
import com.BlogApp.payloads.RoleDto;
import com.BlogApp.repo.RoleRepo;
import com.BlogApp.repo.UserRepo;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private UserRepo userRepo;
	

	@Override
	public Role addRole(RoleDto roleDto) {
		
		Role role=new Role();
		role.setName(roleDto.getName());
		
		return roleRepo.save(role);
		
		
	}

	@Override
	public Role addRoleToUser(Integer userId, Integer roleId) {
		
		System.out.println("sandeep");
		
		Optional<User> optUser=userRepo.findById(userId);

		Optional<Role> optRole=roleRepo.findById(roleId);

		if(!optUser.isPresent())
		{
			throw new ResourceNotFoundException("User","Id", userId);
		}
		if(!optRole.isPresent())
		{
			throw new ResourceNotFoundException("Role","Id", roleId);
		}
		System.out.println("befff");
		Role role=optRole.get();
		User user=optUser.get();
		
		role.getUsers().add(user);
//		user.getRoles().add(role);
//		
//		System.out.println("sdsd");
//		System.out.println(user);
//		userRepo.save(user);
//		
		return roleRepo.save(role);
	}

	@Override
	public List<User> viewUsersInRole(Integer roleId) {
		
		Optional<Role> optRole=roleRepo.findById(roleId);

		if(!optRole.isPresent())
		{
			throw new ResourceNotFoundException("Role","Id", roleId);
		}
		
		return optRole.get().getUsers();
		
	}

}
