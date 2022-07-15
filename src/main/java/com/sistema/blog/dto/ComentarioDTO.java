package com.sistema.blog.dto;

public class ComentarioDTO {
    private long id;
    private String name;
    private String email;
    private String content;

    public ComentarioDTO(long id, String name, String email, String content) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.content = content;
    }

    public ComentarioDTO() {
    }

    public long getId() {
        return id;
    }

    public ComentarioDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ComentarioDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ComentarioDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ComentarioDTO setContent(String content) {
        this.content = content;
        return this;
    }
}
