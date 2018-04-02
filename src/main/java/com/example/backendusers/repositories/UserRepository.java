package com.example.backendusers.repositories;

import com.example.backendusers.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
