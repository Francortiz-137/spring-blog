package franco.ortiz.blogspring.dto.impl.output;

import franco.ortiz.blogspring.dto.IDTOEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTOOutput implements IDTOEntity {

    private String username;
    private String email;
    private String phoneNumber;
}
