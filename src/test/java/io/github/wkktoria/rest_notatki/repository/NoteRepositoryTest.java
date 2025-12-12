package io.github.wkktoria.rest_notatki.repository;

import io.github.wkktoria.rest_notatki.entity.Author;
import io.github.wkktoria.rest_notatki.entity.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void NoteRepository_Save_ReturnsSavedNote() {
        // Arrange
        Author author = Author.builder()
                .name("Jan Kowalski")
                .build();
        Note note = Note.builder()
                .title("Notatka")
                .content("To jest testowa notatka")
                .author(author)
                .build();

        // Act
        Note savedNote = noteRepository.save(note);

        // Assert
        Assertions.assertNotNull(savedNote);
        Assertions.assertTrue(savedNote.getId() > 0);
        Assertions.assertNotNull(savedNote.getAuthor());
        Assertions.assertNotNull(savedNote.getCreatedAt());
    }

    @Test
    void NoteRepository_FindAll_ReturnsMoreThanOneNote() {
        // Arrange
        Note note1 = Note.builder()
                .title("Notatka")
                .content("To jest testowa notatka")
                .build();

        Note note2 = Note.builder()
                .title("Inna notatka")
                .content("To jest inna testowa notatka")
                .build();

        noteRepository.save(note1);
        noteRepository.save(note2);

        // Act
        List<Note> noteList = noteRepository.findAll();

        // Assert
        Assertions.assertNotNull(noteList);
        Assertions.assertEquals(2, noteList.size());
    }

    @Test
    void NoteRepository_FindById_ReturnsNoteNotNull() {
        // Arrange
        Author author = Author.builder()
                .name("Jan Kowalski")
                .build();
        Note note = Note.builder()
                .title("Notatka")
                .content("To jest testowa notatka")
                .author(author)
                .build();

        noteRepository.save(note);

        // Act
        Note returnNote = noteRepository.findById(note.getId())
                .orElse(null);

        // Assert
        Assertions.assertNotNull(returnNote);
    }

    @Test
    void NoteRepository_DeleteById_ReturnNoteIsEmpty() {
        // Arrange
        Note note = Note.builder()
                .title("Notatka")
                .content("To jest testowa notatka")
                .build();

        noteRepository.save(note);

        // Act
        noteRepository.deleteById(note.getId());
        Optional<Note> returnNote = noteRepository.findById(note.getId());

        // Assert
        Assertions.assertTrue(returnNote.isEmpty());
    }

    @Test
    void NoteRepository_FindByAuthorId_ReturnsMoreThanOneNote() {
        // Arrange
        Author author = Author.builder()
                .name("Jan Kowalski")
                .build();
        Note note1 = Note.builder()
                .title("Notatka")
                .content("To jest testowa notatka")
                .author(author)
                .build();

        Note note2 = Note.builder()
                .title("Inna notatka")
                .content("To jest inna testowa notatka")
                .author(author)
                .build();

        authorRepository.save(author);
        noteRepository.save(note1);
        noteRepository.save(note2);

        // Act
        List<Note> noteList = noteRepository.findByAuthorId(author.getId());

        // Assert
        Assertions.assertNotNull(noteList);
        Assertions.assertEquals(2, noteList.size());
    }

}