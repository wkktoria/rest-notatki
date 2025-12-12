package io.github.wkktoria.rest_notatki.controller;

import io.github.wkktoria.rest_notatki.dto.note.CreateNoteDto;
import io.github.wkktoria.rest_notatki.dto.note.NoteDto;
import io.github.wkktoria.rest_notatki.service.NoteService;
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
    public ResponseEntity<List<NoteDto>> getNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @GetMapping(value = "/notes/{id}", version = "1.0")
    public ResponseEntity<NoteDto> getNote(@PathVariable final Long id) {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    @PostMapping(value = "/notes", version = "1.0")
    public ResponseEntity<NoteDto> createNote(@RequestBody @Valid final CreateNoteDto createNoteDto) {
        return new ResponseEntity<>(noteService.createNote(createNoteDto), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/notes/{id}", version = "1.0")
    public ResponseEntity<Void> deleteNote(@PathVariable final Long id) {
        noteService.deleteNoteById(id);
        return ResponseEntity.noContent().build();
    }

}
