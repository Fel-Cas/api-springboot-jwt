package com.sistema.blog.controllers;


import com.sistema.blog.dto.ComentarioDTO;
import com.sistema.blog.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/publications/{publicationId}/comments")
    public List<ComentarioDTO> getCommentsById(@PathVariable(value = "publicationId") long id){
        return  this.comentarioService.getCommentsByPublication(id);
    }

    @PostMapping("/publications/{publicationId}/comments")
    public ResponseEntity<ComentarioDTO> create(@PathVariable(value = "publicationId") long id, @RequestBody ComentarioDTO comentarioDTO){
        return  new ResponseEntity(this.comentarioService.create(id, comentarioDTO), HttpStatus.CREATED);
    }
}
