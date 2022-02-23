package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.dto.PostDTO;
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
		User entity = getEntityById(id);
		return new UserDTO(entity);
	}
	
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		copyDtoEntity(dto, entity);
		entity = userRepository.insert(entity);
		return new UserDTO(entity);
	}
	
	public UserDTO update(String id, UserDTO dto) {
		User entity = getEntityById(id);
		copyDtoEntity(dto, entity);
		entity = userRepository.save(entity);
		return new UserDTO(entity);
	}
	
	public void delete(String id) {
		getEntityById(id);
		userRepository.deleteById(id);
	}
	
	public List<PostDTO> getUserPosts(String id){
		User user = getEntityById(id);
		return user.getPosts().stream().map(x-> new PostDTO(x)).collect(Collectors.toList());
		
	}

	private void copyDtoEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
	}
	
	private User getEntityById(String id) {
		Optional<User> result = userRepository.findById(id);
		return result.orElseThrow(() -> new ResourceNotFoundException("Objeto Não Encontrado"));
	}


}
