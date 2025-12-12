package io.github.wkktoria.rest_notatki.service.impl;

import io.github.wkktoria.rest_notatki.dto.author.AuthorDto;
import io.github.wkktoria.rest_notatki.dto.author.CreateAuthorDto;
import io.github.wkktoria.rest_notatki.entity.Author;
import io.github.wkktoria.rest_notatki.exception.NotFoundException;
import io.github.wkktoria.rest_notatki.repository.AuthorRepository;
import io.github.wkktoria.rest_notatki.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHOR_NOT_FOUND_MESSAGE = "Nie znaleziono autora o podanym ID";

    private AuthorRepository authorRepository;

    @Override
    public AuthorDto createAuthor(final CreateAuthorDto createAuthorDto) {
        Author author = Author.builder()
                .name(createAuthorDto.getName())
                .build();

        Author saved = authorRepository.save(author);

        return AuthorDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .build();
    }

    @Override
    public AuthorDto getAuthorById(final Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND_MESSAGE));

        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(author -> AuthorDto.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .build())
                .toList();
    }

}
