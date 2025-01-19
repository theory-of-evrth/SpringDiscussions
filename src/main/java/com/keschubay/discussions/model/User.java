package com.keschubay.discussions.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class User {

    // TODO: see if this model is really needed

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String role; // "USER" or "ADMIN"

}

