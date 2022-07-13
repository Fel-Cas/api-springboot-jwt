package com.sistema.blog.repositories;

import com.sistema.blog.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    boolean existsByTitle(String title);
}
