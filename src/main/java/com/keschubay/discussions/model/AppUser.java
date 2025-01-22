package com.keschubay.discussions.model;

import jakarta.persistence.*;

@Entity
public class AppUser {

    // TODO: see if this model is really needed

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    //private String role; // "USER" or "ADMIN"


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

