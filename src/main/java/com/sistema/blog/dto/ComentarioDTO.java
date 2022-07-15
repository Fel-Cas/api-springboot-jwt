package com.sistema.blog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ComentarioDTO {
    private long id;
    @NotEmpty(message = "El nombre no debe ser nulo")
    private String name;
    @NotEmpty(message = "El nombre no debe ser nulo")
    @Email
    private String email;
    @NotEmpty
    @Size(min=10, message = "El contenido debe tener al mnos 10 caracteres")
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
