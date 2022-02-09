package com.example.demo.config;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.models.entities.User;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@PostConstruct
	public void init() {
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User user1 = new User(null, "Maria Brown" , "maria@gmail.com");
		User user2 = new User(null, "Alex Green" , "alex@gmail.com");
		User user3 = new User(null, "Bob Grey" , "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3));
	}
}
