package franco.ortiz.blogspring.dto.impl;

import franco.ortiz.blogspring.dto.IDTOEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO implements IDTOEntity {
    private String username;
    private String password;
}
