package io.github.wkktoria.rest_notatki.dto.note;

import io.github.wkktoria.rest_notatki.dto.author.AuthorDto;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoteDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private AuthorDto author;
    
}
