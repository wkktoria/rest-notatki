package io.github.wkktoria.rest_notatki.controller;

import io.github.wkktoria.rest_notatki.dto.author.AuthorDto;
import io.github.wkktoria.rest_notatki.dto.author.CreateAuthorDto;
import io.github.wkktoria.rest_notatki.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Gets list of authors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AuthorDto.class)))
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain")
            )
    })
    public ResponseEntity<List<AuthorDto>> getAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping(value = "/authors/{id}", version = "1.0")
    @Operation(summary = "Gets author by ID", description = "Author must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthorDto.class))
            ),
            @ApiResponse(responseCode = "404", description = "Author not found",
                    content = @Content(mediaType = "text/plain")
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain")
            )
    })
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable final Long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @PostMapping(value = "/authors", version = "1.0")
    @Operation(summary = "Creates new author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthorDto.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid input supplied",
                    content = @Content(mediaType = "text/plain")
            ),
            @ApiResponse(responseCode = "409", description = "Author with the same name already exists",
                    content = @Content(mediaType = "text/plain")
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain")
            )
    })
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody @Valid final CreateAuthorDto createAuthorDto) {
        return new ResponseEntity<>(authorService.createAuthor(createAuthorDto), HttpStatus.CREATED);
    }

}
