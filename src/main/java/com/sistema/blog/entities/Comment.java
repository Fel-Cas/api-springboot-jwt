package com.sistema.blog.entities;

import javax.persistence.*;

@Entity
@Table(name = "comentarios")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private  String email;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id", nullable = false)
    private  Publication publication;

    public Comment() {
    }

    public Comment(Long id, String name, String email, String content, Publication publication) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.content = content;
        this.publication = publication;
    }

    public Long getId() {
        return id;
    }

    public Comment setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Comment setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Comment setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Comment setContent(String content) {
        this.content = content;
        return this;
    }

    public Publication getPublication() {
        return publication;
    }

    public Comment setPublication(Publication publication) {
        this.publication = publication;
        return this;
    }
}
