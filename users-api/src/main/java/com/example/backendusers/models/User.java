package com.example.backendusers.models;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "admin")
    private Boolean admin;

    public User(String userName, String firstName, String lastName, Boolean admin) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.admin = admin;
    }
}
