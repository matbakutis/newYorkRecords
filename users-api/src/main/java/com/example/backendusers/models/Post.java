package com.example.backendusers.models;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "userid")
    private Long userid;

    @Column(name = "username")
    private String username;

    public Post(String title, String content, Long userid, String username) {
        this.title = title;
        this.content = content;
        this.userid = userid;
        this.username = username;
    }
}
