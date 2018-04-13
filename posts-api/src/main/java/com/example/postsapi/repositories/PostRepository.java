package com.example.postsapi.repositories;


import com.example.postsapi.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
