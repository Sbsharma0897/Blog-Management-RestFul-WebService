package com.BlogApp;

import java.util.Optional;

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
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		
			SpringApplication.run(BlogAppApisApplication.class, args);
	
	}

	
	

	@Override
	public void run(String... args) throws Exception {
		
		Role role1=new Role();
		role1.setName("ROLE_ADMIN");
		
		Role role2=new Role();
		role2.setName("ROLE_USER");
		
		Role role3=new Role();
		role3.setName("ROLE_MAINTAINANCE");
		
		roleRepo.save(role1);
		roleRepo.save(role2);
		roleRepo.save(role3);
		
		
   }

}
