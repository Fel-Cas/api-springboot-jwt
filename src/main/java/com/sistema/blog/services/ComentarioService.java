package com.sistema.blog.services;


import com.sistema.blog.dto.ComentarioDTO;

import java.util.List;

public interface ComentarioService {

    public ComentarioDTO create(long id, ComentarioDTO comentarioDTO);
    public List<ComentarioDTO> getCommentsByPublication(long id);
}
