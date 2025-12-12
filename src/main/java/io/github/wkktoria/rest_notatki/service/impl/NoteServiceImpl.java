package io.github.wkktoria.rest_notatki.service.impl;

import io.github.wkktoria.rest_notatki.dto.author.AuthorDto;
import io.github.wkktoria.rest_notatki.dto.note.CreateNoteDto;
import io.github.wkktoria.rest_notatki.dto.note.NoteDto;
import io.github.wkktoria.rest_notatki.entity.Author;
import io.github.wkktoria.rest_notatki.entity.Note;
import io.github.wkktoria.rest_notatki.exception.NotFoundException;
import io.github.wkktoria.rest_notatki.repository.AuthorRepository;
import io.github.wkktoria.rest_notatki.repository.NoteRepository;
import io.github.wkktoria.rest_notatki.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

    private static final String AUTHOR_NOT_FOUND_MESSAGE = "Nie znaleziono autora o podanym ID";
    private static final String NOTE_NOT_FOUND_MESSAGE = "Nie znaleziono notatki o podanym ID";

    private final NoteRepository noteRepository;
    private final AuthorRepository authorRepository;


    @Override
    public NoteDto createNote(final CreateNoteDto createNoteDto) {
        Author author = authorRepository.findById(createNoteDto.getAuthorId())
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND_MESSAGE));

        Note note = Note.builder()
                .title(createNoteDto.getTitle())
                .content(createNoteDto.getContent())
                .author(author)
                .build();

        Note saved = noteRepository.save(note);

        return NoteDto.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .content(saved.getContent())
                .createdAt(note.getCreatedAt())
                .author(AuthorDto.builder()
                        .id(saved.getAuthor().getId())
                        .name(saved.getAuthor().getName())
                        .build())
                .build();
    }

    @Override
    public NoteDto getNoteById(final long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOTE_NOT_FOUND_MESSAGE));

        return NoteDto.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .createdAt(note.getCreatedAt())
                .author(AuthorDto.builder()
                        .id(note.getAuthor().getId())
                        .name(note.getAuthor().getName())
                        .build())
                .build();
    }

    @Override
    public List<NoteDto> getAllNotes() {
        return noteRepository.findAll().stream()
                .map(note -> NoteDto.builder()
                        .id(note.getId())
                        .title(note.getTitle())
                        .content(note.getContent())
                        .createdAt(note.getCreatedAt())
                        .author(AuthorDto.builder()
                                .id(note.getAuthor().getId())
                                .name(note.getAuthor().getName())
                                .build())
                        .build())
                .toList();
    }

    @Override
    public void deleteNoteById(final long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOTE_NOT_FOUND_MESSAGE));
        noteRepository.delete(note);
    }

}
