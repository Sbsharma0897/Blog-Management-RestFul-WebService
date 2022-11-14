package com.BlogApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.BlogApp.model.Role;
import com.BlogApp.repo.RoleRepo;

import net.bytebuddy.asm.Advice.This;


@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner{
	
	@Autowired
    private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	
	}
	

	@Override
	public void run(String... args) throws Exception {
		
		Role role1=new Role();
		role1.setName("ADMIN");
		
		Role role2=new Role();
		role2.setName("USER");
		
		Role role3=new Role();
		role3.setName("MAINTAINANCE");
		
		roleRepo.save(role1);
		roleRepo.save(role2);
		roleRepo.save(role3);
		
	}

}
