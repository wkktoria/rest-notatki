package io.github.wkktoria.rest_notatki.dto.note;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateNoteDto {

    @NotNull
    private String title;

    private String content;

    @NotNull
    private Long authorId;

}
