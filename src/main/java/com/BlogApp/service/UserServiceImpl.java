package com.BlogApp.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.BlogApp.Exceptions.ResourceNotFoundException;
import com.BlogApp.model.Role;
import com.BlogApp.model.User;
import com.BlogApp.payloads.UserDto;
import com.BlogApp.repo.RoleRepo;
import com.BlogApp.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User getCurrentUser()
	{
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
	
		String username = userDetails.getUsername();
		Optional<User> optUser=userRepo.findByEmail(username);
		
		return optUser.get();
	}



	@Override
	public UserDto updateUser(UserDto user) throws ResourceNotFoundException{

		User userGot=getCurrentUser();

		 userGot.setAbout(user.getAbout());
		 userGot.setEmail(user.getEmail());
		 userGot.setName(user.getName());
		 userGot.setPassword(user.getPassword());

		 User savedUser=userRepo.save(userGot);
		 return this.userToUserDto(savedUser);


	}

	@Override
	public UserDto getUserById(Integer userId) {

		Optional<User> optional=userRepo.findById(userId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("User","Id", userId);
		}

		return this.userToUserDto(optional.get());

	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users=userRepo.findAll();

		List<UserDto>  list= users.stream().map(user-> this.userToUserDto(user)).collect(Collectors.toList());
		return list;
	}

	@Override
	public UserDto deleteUser(Integer userId) {
		

		Optional<User> optional=userRepo.findById(userId);
		if(!optional.isPresent())
		{
			throw new ResourceNotFoundException("User","Id", userId);
		}

		UserDto userDto=this.userToUserDto(optional.get());
		userRepo.delete(optional.get());
		return userDto;

	}
	public User userDtotoUser(UserDto userDto)
	{
		User user=new User();
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		return user;

	}
	public UserDto userToUserDto(User user)
	{
		UserDto userDto=new UserDto();
		userDto.setAbout(user.getAbout());
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		userDto.setPassword(user.getPassword());
		userDto.setId(user.getId());
		return userDto;

	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		
	    User user=userDtotoUser(userDto);
	    
	    Optional<User> optional=userRepo.findByEmail(user.getEmail());
	    if(optional.isPresent())
	    {
	    	throw new ResourceNotFoundException("User", "Id", optional.get().getId());
	    }
	    
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    
	   
	    Role role=roleRepo.findById(2).get();
	    
	    user.getRoles().add(role);
	    
	    User newUser=userRepo.save(user);
	   
	     return userToUserDto(user);
	    
	}
	@Override
	public UserDto registerNewAdmin(UserDto userDto) {
		
	    User user=userDtotoUser(userDto);
	    
	    Optional<User> optional=userRepo.findByEmail(user.getEmail());
	    if(optional.isPresent())
	    {
	    	throw new ResourceNotFoundException("User", "Id", optional.get().getId());
	    }
	    
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    
	   
	    Role role=roleRepo.findById(1).get();
	    
	    user.getRoles().add(role);
	    
	    User newUser=userRepo.save(user);
	    
	   
	     return userToUserDto(user);
	    
	}

	@Override
	public Set<Role> getRolesOfUserById(Integer userId) {
		
		    Optional<User> optional=userRepo.findById(userId);
		    if(!optional.isPresent())
		    {
		    	throw new ResourceNotFoundException("User", "Id", userId);
		    }
		    
		   return optional.get().getRoles();
	}


}
