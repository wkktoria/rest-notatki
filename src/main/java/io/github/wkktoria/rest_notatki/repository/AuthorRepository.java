package io.github.wkktoria.rest_notatki.repository;

import io.github.wkktoria.rest_notatki.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    boolean existsByNameIgnoreCase(final String name);

}
