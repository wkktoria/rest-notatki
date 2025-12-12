package io.github.wkktoria.rest_notatki.service;

import io.github.wkktoria.rest_notatki.dto.note.CreateNoteDto;
import io.github.wkktoria.rest_notatki.dto.note.NoteDto;

import java.util.List;

public interface NoteService {

    NoteDto createNote(final CreateNoteDto createNoteDto);

    NoteDto getNoteById(final long id);

    List<NoteDto> getAllNotes();

    void deleteNoteById(final long id);

}
