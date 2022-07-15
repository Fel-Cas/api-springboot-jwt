package com.sistema.blog.repositories;

import com.sistema.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByPublicationId(long publicationId);
}
