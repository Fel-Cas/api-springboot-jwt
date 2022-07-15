package com.sistema.blog.controllers;


import com.sistema.blog.dto.ComentarioDTO;
import com.sistema.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ComentarioController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/publications/{publicationId}/comments")
    public List<ComentarioDTO> getCommentsById(@PathVariable(value = "publicationId") long id){
        return  this.commentService.getCommentsByPublication(id);
    }

    @GetMapping("/publications/{publicationId}/comments/{commentsId}")
    public ResponseEntity<ComentarioDTO> getById(@PathVariable(name = "publicationId") Long publicationId, @PathVariable(name = "commentsId") Long commentId){
        return ResponseEntity.ok(this.commentService.getById(commentId,publicationId));
    }
    @PostMapping("/publications/{publicationId}/comments")
    public ResponseEntity<ComentarioDTO> create(@PathVariable(value = "publicationId") long id, @Valid @RequestBody ComentarioDTO comentarioDTO){
        return  new ResponseEntity(this.commentService.create(id, comentarioDTO), HttpStatus.CREATED);
    }

    @PutMapping("/publications/{publicationId}/comments/{commentsId}")
    public ResponseEntity<ComentarioDTO> update(@PathVariable(name = "publicationId") Long publicationId, @PathVariable(name = "commentsId") Long commentId, @Valid @RequestBody ComentarioDTO comentarioDTO){
        return ResponseEntity.ok(this.commentService.update(publicationId, commentId, comentarioDTO));
    }

    @DeleteMapping("/publications/{publicationId}/comments/{commentsId}")
    public ResponseEntity<String> delete(@PathVariable(name = "publicationId") Long publicationId, @PathVariable(name = "commentsId") Long commentId){
        this.commentService.delete(publicationId, commentId);
        return  ResponseEntity.noContent().build();
    }
}
