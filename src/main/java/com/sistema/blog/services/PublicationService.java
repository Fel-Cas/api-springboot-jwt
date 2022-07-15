package com.sistema.blog.services;

import com.sistema.blog.dto.PublicationDTO;
import com.sistema.blog.dto.PublicationResponse;

public interface PublicationService {
    public PublicationDTO create(PublicationDTO publication);
    public PublicationResponse getAll(int numberPage, int pageSize, String orderBy, String sortDir );
    public PublicationDTO getById(Long id);
    public PublicationDTO update(PublicationDTO publicationDTO, Long id);
    public void delete(Long id);
}
