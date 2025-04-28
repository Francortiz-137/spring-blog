package franco.ortiz.blogspring.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTOInput {
    @NotBlank(message = "El título no puede estar vacío.")
    private String title;

    @NotBlank(message = "El contenido no puede estar vacío.")
    private String content;

    @NotNull(message = "El autor es obligatorio.")
    private Long authorId; // ID del usuario que crea el post


}
