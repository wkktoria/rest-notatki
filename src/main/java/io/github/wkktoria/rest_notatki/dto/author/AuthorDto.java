package io.github.wkktoria.rest_notatki.dto.author;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorDto {

    private Long id;
    private String name;

}
