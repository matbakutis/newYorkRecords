package com.example.postsapi.controllers;

import com.example.postsapi.models.Post;
import com.example.postsapi.repositories.PostRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class PostController {

    private Post newPost;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public Iterable<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{postId}")
    public Post findPostById(@PathVariable Long postId) throws NotFoundException {
        Post foundPost = postRepository.findOne(postId);

        if (foundPost == null) {
            throw new NotFoundException("Post with ID of " + postId + " was not found!");
        }

        return foundPost;
    }

    @DeleteMapping("/{postId}")
    public HttpStatus deletePostById(@PathVariable Long postId) throws EmptyResultDataAccessException {
        postRepository.delete(postId);
        return HttpStatus.OK;
    }

    @PostMapping("/")
    public Post createNewPost(@RequestBody Post newPost) {
        return postRepository.save(newPost);
    }

    @PatchMapping("/{postId}")
    public Post updatePostById(@PathVariable Long postId, @RequestBody Post postRequest) throws NotFoundException {
        Post postFromDb = postRepository.findOne(postId);

        if (postFromDb == null) {
            throw new NotFoundException("Post with ID of " + postId + " was not found!");
        }

        postFromDb.setTitle(postRequest.getTitle());
        postFromDb.setContent(postRequest.getContent());

        return postRepository.save(postFromDb);
    }

    @ExceptionHandler
    void handlePostNotFound(
            NotFoundException exception,
            HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler
    void handleDeleteNotFoundException(
            EmptyResultDataAccessException exception,
            HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

}
