package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.models.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserDTO> findAll(){
		List<User> list = userRepository.findAll();
		return list.stream().map(item -> new UserDTO(item)).collect(Collectors.toList());
	}
	
	public UserDTO findById(String id) {
		Optional<User> result = userRepository.findById(id);
		User entity = result.orElseThrow(() -> new ResourceNotFoundException("Objeto Não Encontrado"));
		return new UserDTO(entity);
	}


}
