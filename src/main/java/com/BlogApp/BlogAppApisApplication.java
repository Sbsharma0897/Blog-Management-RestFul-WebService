package com.BlogApp;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.BlogApp.Exceptions.ResourceNotFoundException;
import com.BlogApp.model.Role;
import com.BlogApp.model.User;
import com.BlogApp.payloads.UserDto;
import com.BlogApp.repo.RoleRepo;
import com.BlogApp.repo.UserRepo;
import com.BlogApp.service.UserService;

import net.bytebuddy.asm.Advice.This;


@SpringBootApplication
public class BlogAppApisApplication  implements CommandLineRunner{
	
	@Autowired
    private  RoleRepo roleRepo;
	
	@Autowired
    private  UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		
			SpringApplication.run(BlogAppApisApplication.class, args);
	
	}

	
	

	@Override
	public void run(String... args) throws Exception {
		
		Role role1=new Role();
		role1.setId(1);
		role1.setName("ROLE_ADMIN");
		
		Role role2=new Role();
		role2.setId(2);
		role2.setName("ROLE_USER");
		
		Role role3=new Role();
		role3.setId(3);
		role3.setName("ROLE_MAINTAINANCE");
		
		roleRepo.save(role1);
		roleRepo.save(role2);
		roleRepo.save(role3);
		
		System.out.println(passwordEncoder.encode("sandeep"));
		
		User user=new User();
		user.setId(1);
		user.setAbout("first user");
		user.setEmail("sbsharma0897@gmail.com");
		user.setName("sandeep");
		user.setPassword(passwordEncoder.encode("sandeep"));
		
		Set<Role> roles=new HashSet<>();
		roles.add(role1);
		
		user.setRoles(roles);
		userRepo.save(user);
		
		
		
   }

}
