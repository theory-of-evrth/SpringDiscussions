package com.keschubay.discussions.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
public class Comment implements java.io.Serializable {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    //@ManyToOne
    //private User createdBy;

    @ManyToOne
    @MapsId
    private Discussion discussion;

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
