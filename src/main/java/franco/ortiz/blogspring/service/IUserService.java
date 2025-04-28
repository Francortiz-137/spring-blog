package franco.ortiz.blogspring.service;

import franco.ortiz.blogspring.dto.impl.input.UserDTOInput;
import franco.ortiz.blogspring.dto.impl.output.UserDTOOutput;

import java.util.List;

public interface IUserService {
    List<UserDTOOutput> findAll();
    UserDTOOutput save(UserDTOInput user);
}
