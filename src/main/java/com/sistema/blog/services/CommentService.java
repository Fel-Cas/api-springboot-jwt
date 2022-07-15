package com.sistema.blog.services;


import com.sistema.blog.dto.ComentarioDTO;

import java.util.List;

public interface CommentService {

    public ComentarioDTO create(long id, ComentarioDTO comentarioDTO);
    public List<ComentarioDTO> getCommentsByPublication(long id);
    public ComentarioDTO getById(Long commetnId, Long publicationId);
    public ComentarioDTO update(Long publicationId, Long commentId,ComentarioDTO comentarioDTO );
    public void delete(Long publicationId, Long commentId);
}
