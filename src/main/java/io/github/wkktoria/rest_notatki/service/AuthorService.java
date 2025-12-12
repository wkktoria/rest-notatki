package io.github.wkktoria.rest_notatki.service;

import io.github.wkktoria.rest_notatki.dto.author.AuthorDto;
import io.github.wkktoria.rest_notatki.dto.author.CreateAuthorDto;

import java.util.List;

public interface AuthorService {

    AuthorDto createAuthor(final CreateAuthorDto createAuthorDto);

    AuthorDto getAuthorById(final Long id);

    List<AuthorDto> getAllAuthors();

}
