package com.sistema.blog.dto;


import com.sistema.blog.entities.Comment;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

public class PublicationDTO {

    private Long id;
    @NotEmpty
    @Size(min=3, message = "El titulo de la publicación debería tener al menos 3 caracteres")
    private String title;
    @NotEmpty
    @Size(min=10, message = "La descripción de la publicación debería tener al menos 10 caracteres")
    private String description;
    @NotEmpty
    private  String content;
    private Set<Comment> comments;

    public PublicationDTO() {
    }

    public PublicationDTO(Long id, String title, String description, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
