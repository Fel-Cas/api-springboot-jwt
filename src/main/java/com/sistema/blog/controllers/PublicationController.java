package com.sistema.blog.controllers;

import com.sistema.blog.dto.PublicationDTO;
import com.sistema.blog.entities.Publication;
import com.sistema.blog.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @GetMapping()
    public  ResponseEntity<List<PublicationDTO>> getAll(){
        return new ResponseEntity<>( this.publicationService.getAll(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDTO> getById(@PathVariable(name = "id") Long id){
        return  ResponseEntity.ok(this.publicationService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<PublicationDTO> save(@RequestBody PublicationDTO publicationDTO){
        return  new ResponseEntity<>(this.publicationService.create(publicationDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicationDTO> update(@RequestBody PublicationDTO publicationDTO, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok(this.publicationService.update(publicationDTO,id));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<PublicationDTO> delete(@PathVariable(name = "id") Long id){
        this.publicationService.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
