package com.sistema.blog.services;

import com.sistema.blog.dto.PublicationDTO;
import com.sistema.blog.entities.Publication;

import java.util.List;

public interface PublicationService {
    public PublicationDTO create(PublicationDTO publication);
    public List<PublicationDTO> getAll();
    public PublicationDTO getById(Long id);
    public PublicationDTO update(PublicationDTO publicationDTO, Long id);
    public void delete(Long id);
}
