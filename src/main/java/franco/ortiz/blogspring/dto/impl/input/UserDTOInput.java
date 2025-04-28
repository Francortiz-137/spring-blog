package franco.ortiz.blogspring.dto.impl.input;

import franco.ortiz.blogspring.dto.IDTOEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTOInput implements IDTOEntity {

    @NotBlank(message = "El nombre de usuario es obligatorio.")
    private String username;

    @NotBlank(message = "El email es obligatorio.")
    @Email(message = "El email debe ser válido.")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria.")
    private String password;

    @NotBlank(message = "El número de teléfono es obligatorio.")
    @Size(max = 45, message = "El teléfono no debe superar los 45 caracteres.")
    private String phoneNumber;
}
