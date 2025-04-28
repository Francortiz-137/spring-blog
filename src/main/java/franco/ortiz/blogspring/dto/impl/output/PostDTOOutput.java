package franco.ortiz.blogspring.dto.impl.output;

import franco.ortiz.blogspring.dto.IDTOEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTOOutput implements IDTOEntity {
    @NotBlank(message = "El título no puede estar vacío.")
    private String title;

    @NotBlank(message = "El contenido no puede estar vacío.")
    private String content;
}
