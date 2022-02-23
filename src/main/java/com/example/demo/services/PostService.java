package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.dto.PostDTO;
import com.example.demo.models.entities.Post;
import com.example.demo.repositories.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	
	
	public PostDTO findById(String id) {
		Post entity = getEntityById(id);
		return new PostDTO(entity);
	}
	
	
	private Post getEntityById(String id) {
		Optional<Post> result = postRepository.findById(id);
		return result.orElseThrow(() -> new ResourceNotFoundException("Objeto Não Encontrado"));
	}


}
