package io.github.wkktoria.rest_notatki.controller;

import io.github.wkktoria.rest_notatki.dto.author.AuthorDto;
import io.github.wkktoria.rest_notatki.dto.note.CreateNoteDto;
import io.github.wkktoria.rest_notatki.dto.note.NoteDto;
import io.github.wkktoria.rest_notatki.service.NoteService;
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
public class NoteController {

    private final NoteService noteService;

    @GetMapping(value = "/notes", version = "1.0")
    @Operation(summary = "Gets list of notes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = NoteDto.class)))
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain")
            )
    })
    public ResponseEntity<List<NoteDto>> getNotes(@RequestParam(value = "authorId", required = false) final Long authorId) {
        if (authorId != null) {
            return ResponseEntity.ok(noteService.getNotesByAuthor(authorId));
        }
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @GetMapping(value = "/notes/{id}", version = "1.0")
    @Operation(summary = "Gets note by ID", description = "Note must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NoteDto.class))
            ),
            @ApiResponse(responseCode = "404", description = "Note not found",
                    content = @Content(mediaType = "text/plain")
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain")
            )
    })
    public ResponseEntity<NoteDto> getNote(@PathVariable final Long id) {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    @PostMapping(value = "/notes", version = "1.0")
    @Operation(summary = "Creates new note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthorDto.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid input supplied",
                    content = @Content(mediaType = "text/plain")
            ),
            @ApiResponse(responseCode = "404", description = "Author with supplied ID not found",
                    content = @Content(mediaType = "text/plain")
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain")
            )
    })
    public ResponseEntity<NoteDto> createNote(@RequestBody @Valid final CreateNoteDto createNoteDto) {
        return new ResponseEntity<>(noteService.createNote(createNoteDto), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/notes/{id}", version = "1.0")
    @Operation(summary = "Deletes note by ID", description = "Note must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Note not found",
                    content = @Content(mediaType = "text/plain")
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain")
            )
    })
    public ResponseEntity<Void> deleteNote(@PathVariable final Long id) {
        noteService.deleteNoteById(id);
        return ResponseEntity.noContent().build();
    }

}
