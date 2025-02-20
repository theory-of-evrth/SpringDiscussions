package com.keschubay.discussions.model;

import jakarta.persistence.*;
import org.aspectj.lang.annotation.RequiredTypes;

@Entity
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String createdBy;

    @ManyToOne
    private Category category;

    //private Long category_id;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() { return this.content; }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String username)
    {
        this.createdBy = username;
    }

    public void setCreatedBy(AppUser createdBy) {
        this.createdBy = createdBy.getUsername();
    }
}
