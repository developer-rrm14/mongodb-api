package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

}
