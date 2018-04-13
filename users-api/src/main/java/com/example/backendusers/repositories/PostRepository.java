package com.example.backendusers.repositories;


import com.example.backendusers.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

//    @Query("SELECT * FROM BLa")
//    public void doThings() {}

}
