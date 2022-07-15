package com.sistema.blog.controllers;

import com.sistema.blog.dto.PublicationDTO;
import com.sistema.blog.dto.PublicationResponse;
import com.sistema.blog.entities.Publication;
import com.sistema.blog.services.PublicationService;
import com.sistema.blog.utils.Constants;
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
    public  ResponseEntity<PublicationResponse> getAll(
            @RequestParam(value = "numberPage", defaultValue = Constants.DEFAULT_NUMBER_PAGE, required = false) int numberPage,
            @RequestParam(value = "pageSize",defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false)int pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_ORDER_BY, required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIR, required = false) String sortDir
            ){
        return new ResponseEntity<>( this.publicationService.getAll(numberPage,pageSize, orderBy, sortDir),HttpStatus.ACCEPTED);
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
