package io.github.wkktoria.rest_notatki.service.impl;

import io.github.wkktoria.rest_notatki.dto.note.CreateNoteDto;
import io.github.wkktoria.rest_notatki.dto.note.NoteDto;
import io.github.wkktoria.rest_notatki.entity.Author;
import io.github.wkktoria.rest_notatki.entity.Note;
import io.github.wkktoria.rest_notatki.exception.NotFoundException;
import io.github.wkktoria.rest_notatki.mapper.NoteMapper;
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
    private final NoteMapper noteMapper;

    @Override
    public NoteDto createNote(final CreateNoteDto createNoteDto) {
        Author author = authorRepository.findById(createNoteDto.getAuthorId())
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND_MESSAGE));

        Note note = noteMapper.toEntity(createNoteDto, author);
        Note saved = noteRepository.save(note);

        return noteMapper.toDto(saved);
    }

    @Override
    public NoteDto getNoteById(final long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOTE_NOT_FOUND_MESSAGE));

        return noteMapper.toDto(note);
    }

    @Override
    public List<NoteDto> getAllNotes() {
        return noteRepository.findAll().stream()
                .map(noteMapper::toDto)
                .toList();
    }

    @Override
    public List<NoteDto> getNotesByAuthor(final long authorId) {
        return noteRepository.findByAuthorId(authorId).stream()
                .map(noteMapper::toDto)
                .toList();
    }

    @Override
    public void deleteNoteById(final long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOTE_NOT_FOUND_MESSAGE));
        noteRepository.delete(note);
    }

}
