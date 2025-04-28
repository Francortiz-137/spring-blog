package franco.ortiz.blogspring.dto.impl.input;

import franco.ortiz.blogspring.dto.IDTOEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTOInput implements IDTOEntity {

    @NotBlank(message = "El título no puede estar vacío.")
    private String title;

    @NotBlank(message = "El contenido no puede estar vacío.")
    private String content;

    @NotNull(message = "El autor es obligatorio.")
    private Long authorId;


}
