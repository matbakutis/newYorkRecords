package com.example.backendusers.controllers;

import com.example.backendusers.models.User;
import com.example.backendusers.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
public class UserController {

    private User newUser;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public Optional<User> findUserById(@PathVariable Long userId) throws NotFoundException {
        Optional<User> foundUser = userRepository.findById(userId);

        if (!foundUser.isPresent()) {
            throw new NotFoundException("User with ID of " + userId + " was not found!");
        }

        return foundUser;
    }

    @DeleteMapping("/users/{userId}")
    public HttpStatus deleteUserById(@PathVariable Long userId) throws EmptyResultDataAccessException {
        userRepository.deleteById(userId);
        return HttpStatus.OK;
    }

    @PostMapping("/users")
    public User createNewUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @PatchMapping("/users/{userId}")
    public User updateUserById(@PathVariable Long userId, @RequestBody User userRequest) throws NotFoundException {
        Optional<User> userFromDb = userRepository.findById(userId);

        if (!userFromDb.isPresent()) {
            throw new NotFoundException("User with ID of " + userId + " was not found!");
        }

        User foundUser = userFromDb.get();
        foundUser.setUserName(userRequest.getUserName());
        foundUser.setFirstName(userRequest.getFirstName());
        foundUser.setLastName(userRequest.getLastName());

        return userRepository.save(foundUser);
    }

    @ExceptionHandler
    void handleUserNotFound(
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
