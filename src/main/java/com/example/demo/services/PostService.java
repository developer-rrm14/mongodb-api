package com.example.demo.services;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	public List<PostDTO> findByTitle(String text){
		List<Post> list = postRepository.findByTitleContainingIgnoreCase(text);
		//List<Post> list = postRepository.searchTitle(text);
		return list.stream().map(x-> new PostDTO(x)).collect(Collectors.toList());
	}
	
	public List<PostDTO> fullSearch(String text, String start, String end){
		
		Instant startMoment = convertMoment(start, Instant.ofEpochMilli(0L));
		Instant endMoment = convertMoment(end, Instant.now());
		
		List<Post> list = postRepository.fullSearch(text, startMoment, endMoment);
		return list.stream().map(x-> new PostDTO(x)).collect(Collectors.toList());
	}
	
	
	private Post getEntityById(String id) {
		Optional<Post> result = postRepository.findById(id);
		return result.orElseThrow(() -> new ResourceNotFoundException("Objeto Não Encontrado"));
	}
	
	private Instant convertMoment(String originalString, Instant alternative) {
		try {
			return Instant.parse(originalString);
		}catch(DateTimeParseException e) {
			return alternative;
		}
	}



}
