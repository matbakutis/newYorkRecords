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

    @GetMapping("/")
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable Long userId) throws NotFoundException {
        User foundUser = userRepository.findOne(userId);

        if (foundUser == null) {
            throw new NotFoundException("User with ID of " + userId + " was not found!");
        }

        return foundUser;
    }

    @DeleteMapping("/{userId}")
    public HttpStatus deleteUserById(@PathVariable Long userId) throws EmptyResultDataAccessException {
        userRepository.delete(userId);
        return HttpStatus.OK;
    }

    @PostMapping("/")
    public User createNewUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @PatchMapping("/{userId}")
    public User updateUserById(@PathVariable Long userId, @RequestBody User userRequest) throws NotFoundException {
        User userFromDb = userRepository.findOne(userId);

        if (userFromDb == null) {
            throw new NotFoundException("User with ID of " + userId + " was not found!");
        }

        userFromDb.setUserName(userRequest.getUserName());
        userFromDb.setFirstName(userRequest.getFirstName());
        userFromDb.setLastName(userRequest.getLastName());

        return userRepository.save(userFromDb);
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
