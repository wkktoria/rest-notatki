package io.github.wkktoria.rest_notatki.mapper;

import io.github.wkktoria.rest_notatki.dto.author.AuthorDto;
import io.github.wkktoria.rest_notatki.dto.author.CreateAuthorDto;
import io.github.wkktoria.rest_notatki.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorDto toDto(final Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }

    public Author toEntity(final CreateAuthorDto createAuthorDto) {
        return Author.builder()
                .name(createAuthorDto.getName())
                .build();
    }

}
