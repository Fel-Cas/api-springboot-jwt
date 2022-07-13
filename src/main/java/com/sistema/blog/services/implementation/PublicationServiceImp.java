package com.sistema.blog.services.implementation;

import com.sistema.blog.dto.PublicationDTO;
import com.sistema.blog.entities.Publication;
import com.sistema.blog.exceptions.BadRequestException;
import com.sistema.blog.exceptions.ResourceNotFoundException;
import com.sistema.blog.repositories.PublicationRepository;
import com.sistema.blog.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImp implements PublicationService {

    @Autowired
    PublicationRepository publicationRepository;

    @Override
    public PublicationDTO create(PublicationDTO publicationDTO) {
        //Convertimos DTO a entidad
        if(this.publicationRepository.existsByTitle(publicationDTO.getTitle()))
            throw new BadRequestException("Ya hay una publicación con ese titulo");
        Publication publication= new Publication.Builder()
                .setId(publicationDTO.getId() )
                .setTitle(publicationDTO.getTitle())
                .setDescription(publicationDTO.getDescription())
                .setContent(publicationDTO.getContent()).build();

        return this.mapDTO(this.publicationRepository.save(publication));
    }

    @Override
    public List<PublicationDTO> getAll() {
        List<Publication> allPublications=this.publicationRepository.findAll();
        return allPublications.stream().map(publication -> mapDTO(publication)).collect(Collectors.toList());
    }

    @Override
    public PublicationDTO getById(Long id) {
        Publication publication=this.publicationRepository
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("Publicación","id",id));
        return  this.mapDTO(publication);
    }

    @Override
    public PublicationDTO update(PublicationDTO publicationDTO, Long id) {
        Publication publicationFound=this.publicationRepository
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("Publicación","id",id));
        publicationFound= this.mapEntity(publicationDTO);
        return this.mapDTO(this.publicationRepository.save(publicationFound));
    }

    @Override
    public void delete(Long id) {
        Publication publication=this.publicationRepository
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("Publicación","id",id));
        this.publicationRepository.delete(publication);
    }


    // Convierte entidad a DTO
    private PublicationDTO mapDTO(Publication publication){
        return  new PublicationDTO.Builder()
                .setId(publication.getId() )
                .setTitle(publication.getTitle())
                .setDescription(publication.getDescription())
                .setContent(publication.getContent()).build();
    }

    //Convierte DTO a Entity
    private Publication mapEntity(PublicationDTO publicationDTO){
        return new Publication.Builder()
                .setId(publicationDTO.getId() )
                .setTitle(publicationDTO.getTitle())
                .setDescription(publicationDTO.getDescription())
                .setContent(publicationDTO.getContent()).build();
    }


}
