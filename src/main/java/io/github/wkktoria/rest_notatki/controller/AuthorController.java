package io.github.wkktoria.rest_notatki.controller;

import io.github.wkktoria.rest_notatki.dto.author.AuthorDto;
import io.github.wkktoria.rest_notatki.dto.author.CreateAuthorDto;
import io.github.wkktoria.rest_notatki.service.AuthorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AuthorController {

    private AuthorService authorService;

    @GetMapping(value = "/authors", version = "1.0")
    public ResponseEntity<List<AuthorDto>> getAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping(value = "/authors/{id}", version = "1.0")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable final Long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @PostMapping(value = "/authors", version = "1.0")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody @Valid final CreateAuthorDto createAuthorDto) {
        return new ResponseEntity<>(authorService.createAuthor(createAuthorDto), HttpStatus.CREATED);
    }

}
