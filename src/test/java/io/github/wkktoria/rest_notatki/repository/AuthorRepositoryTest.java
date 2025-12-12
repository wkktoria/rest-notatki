package io.github.wkktoria.rest_notatki.repository;

import io.github.wkktoria.rest_notatki.entity.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void AuthorRepository_Save_ReturnsSavedAuthor() {
        // Arrange
        Author author = Author.builder()
                .name("Jan Kowalski")
                .build();

        // Act
        Author savedAuthor = authorRepository.save(author);

        // Assert
        Assertions.assertNotNull(savedAuthor);
        Assertions.assertTrue(savedAuthor.getId() > 0);
    }

    @Test
    void AuthorRepository_FindAll_ReturnsMoreThanOneAuthor() {
        // Arrange
        Author author1 = Author.builder()
                .name("Jan Kowalski")
                .build();

        Author author2 = Author.builder()
                .name("Adam Nowak")
                .build();

        authorRepository.save(author1);
        authorRepository.save(author2);

        // Act
        List<Author> authorList = authorRepository.findAll();

        // Assert
        Assertions.assertNotNull(authorList);
        Assertions.assertEquals(2, authorList.size());
    }

    @Test
    void AuthorRepository_FindById_ReturnsAuthorNotNull() {
        // Arrange
        Author author = Author.builder()
                .name("Jan Kowalski")
                .build();

        authorRepository.save(author);

        // Act
        Author returnAuthor = authorRepository.findById(author.getId())
                .orElse(null);

        // Assert
        Assertions.assertNotNull(returnAuthor);
    }

}