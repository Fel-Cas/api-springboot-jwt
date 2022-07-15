package com.sistema.blog.services.implementation;

import com.sistema.blog.dto.ComentarioDTO;
import com.sistema.blog.entities.Comment;
import com.sistema.blog.entities.Publication;
import com.sistema.blog.exceptions.ResourceNotFoundException;
import com.sistema.blog.repositories.ComentarioRepository;
import com.sistema.blog.repositories.PublicationRepository;
import com.sistema.blog.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioServiceImp implements ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public ComentarioDTO create(long id, ComentarioDTO comentarioDTO) {
        Comment comment =mapEntity(comentarioDTO);
        Publication publication=this.publicationRepository
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("Publicación","id",id));
        comment.setPublication(publication);
        return this.mapDTO(this.comentarioRepository.save(comment));
    }

    @Override
    public List<ComentarioDTO> getCommentsByPublication(long id) {
        List<Comment> comments = comentarioRepository.findByPublicationId(id);
        return  comments.stream().map(comment -> mapDTO(comment)).collect(Collectors.toList());
    }

    private ComentarioDTO mapDTO(Comment comment){
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setId(comment.getId());
        comentarioDTO.setName(comment.getName());
        comentarioDTO.setEmail(comment.getEmail());
        comentarioDTO.setContent(comment.getContent());
        return comentarioDTO;
    }

    private Comment mapEntity(ComentarioDTO comentarioDTO){
        Comment comment = new Comment();
        comment.setId(comentarioDTO.getId());
        comment.setName(comentarioDTO.getName());
        comment.setEmail(comentarioDTO.getEmail());
        comment.setContent(comentarioDTO.getContent());
        return comment;
    }
}
