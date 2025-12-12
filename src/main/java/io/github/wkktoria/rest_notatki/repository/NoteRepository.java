package io.github.wkktoria.rest_notatki.repository;

import io.github.wkktoria.rest_notatki.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByAuthorId(final Long id);

}
