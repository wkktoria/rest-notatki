package io.github.wkktoria.rest_notatki.service.impl;

import io.github.wkktoria.rest_notatki.dto.author.AuthorDto;
import io.github.wkktoria.rest_notatki.dto.author.CreateAuthorDto;
import io.github.wkktoria.rest_notatki.entity.Author;
import io.github.wkktoria.rest_notatki.exception.NotFoundException;
import io.github.wkktoria.rest_notatki.mapper.AuthorMapper;
import io.github.wkktoria.rest_notatki.repository.AuthorRepository;
import io.github.wkktoria.rest_notatki.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHOR_NOT_FOUND_MESSAGE = "Nie znaleziono autora o podanym ID";

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public AuthorDto createAuthor(final CreateAuthorDto createAuthorDto) {
        Author author = authorMapper.toEntity(createAuthorDto);
        Author saved = authorRepository.save(author);

        return authorMapper.toDto(saved);
    }

    @Override
    public AuthorDto getAuthorById(final Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND_MESSAGE));

        return authorMapper.toDto(author);
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toDto)
                .toList();
    }

}
