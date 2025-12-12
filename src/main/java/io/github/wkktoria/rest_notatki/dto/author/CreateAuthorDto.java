package io.github.wkktoria.rest_notatki.dto.author;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAuthorDto {

    @NotBlank
    private String name;

}
