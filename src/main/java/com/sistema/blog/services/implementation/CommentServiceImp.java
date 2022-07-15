package com.sistema.blog.services.implementation;

import com.sistema.blog.dto.ComentarioDTO;
import com.sistema.blog.entities.Comment;
import com.sistema.blog.entities.Publication;
import com.sistema.blog.exceptions.BlogAppException;
import com.sistema.blog.exceptions.ResourceNotFoundException;
import com.sistema.blog.repositories.ComentarioRepository;
import com.sistema.blog.repositories.PublicationRepository;
import com.sistema.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private ModelMapper modelMapper;

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

    @Override
    public ComentarioDTO getById(Long commentId, Long publicationId) {
        Publication publication=this.publicationRepository
                .findById(publicationId).orElseThrow(()-> new ResourceNotFoundException("Publicación","id",publicationId));
        Comment comment=this.comentarioRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comentario","id",commentId));
        if(comment.getPublication().getId()!=publication.getId()) throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
        return  this.mapDTO(comment);
    }

    @Override
    public ComentarioDTO update(Long publicationId, Long commentId,ComentarioDTO comentarioDTO) {
        Publication publication=this.publicationRepository
                .findById(publicationId).orElseThrow(()-> new ResourceNotFoundException("Publicación","id",publicationId));
        Comment comment=this.comentarioRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comentario","id",commentId));
        if(comment.getPublication().getId()!=publication.getId()) throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
        comment.setName(comentarioDTO.getName());
        comment.setEmail(comentarioDTO.getEmail());
        comment.setContent(comentarioDTO.getContent());

        return  this.mapDTO(this.comentarioRepository.save(comment));
    }

    @Override
    public void delete(Long publicationId, Long commentId) {
        Publication publication=this.publicationRepository
                .findById(publicationId).orElseThrow(()-> new ResourceNotFoundException("Publicación","id",publicationId));
        Comment comment=this.comentarioRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comentario","id",commentId));
        if(comment.getPublication().getId()!=publication.getId()) throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
        this.comentarioRepository.delete(comment);
    }

    private ComentarioDTO mapDTO(Comment comment){
        return modelMapper.map(comment, ComentarioDTO.class);
    }

    private Comment mapEntity(ComentarioDTO comentarioDTO){
        return  modelMapper.map(comentarioDTO, Comment.class);
    }
}
