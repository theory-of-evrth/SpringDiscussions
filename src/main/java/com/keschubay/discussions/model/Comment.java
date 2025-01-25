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

    private String createdBy;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(AppUser appUser) {
        this.createdBy = appUser.getUsername();
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
