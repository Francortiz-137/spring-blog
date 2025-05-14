package franco.ortiz.blogspring.service;

import franco.ortiz.blogspring.dto.impl.input.UserDTOInput;
import franco.ortiz.blogspring.dto.impl.output.UserDTOOutput;
import jakarta.validation.Valid;

import java.util.List;

public interface IUserService {
    List<UserDTOOutput> findAll();
    void registerUser(UserDTOInput userDTOInput);

    UserDTOOutput update(Long id, @Valid UserDTOInput input);
    ;

    UserDTOOutput findById(Long id);

    void deleteById(Long id);
}
