package io.github.wkktoria.rest_notatki.mapper;

import io.github.wkktoria.rest_notatki.dto.note.CreateNoteDto;
import io.github.wkktoria.rest_notatki.dto.note.NoteDto;
import io.github.wkktoria.rest_notatki.entity.Author;
import io.github.wkktoria.rest_notatki.entity.Note;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NoteMapper {

    private final AuthorMapper authorMapper;

    public NoteDto toDto(final Note note) {
        return NoteDto.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .createdAt(note.getCreatedAt())
                .author(authorMapper.toDto(note.getAuthor()))
                .build();
    }

    public Note toEntity(final CreateNoteDto createNoteDto, final Author author) {
        return Note.builder()
                .title(createNoteDto.getTitle())
                .content(createNoteDto.getContent())
                .author(author)
                .build();
    }

}
