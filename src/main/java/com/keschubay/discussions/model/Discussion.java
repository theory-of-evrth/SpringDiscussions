package com.keschubay.discussions.model;

import jakarta.persistence.*;

@Entity
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    private User createdBy;
}
