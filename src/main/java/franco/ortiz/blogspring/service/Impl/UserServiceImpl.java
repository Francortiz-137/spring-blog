package franco.ortiz.blogspring.service.Impl;

import franco.ortiz.blogspring.dto.common.MappingDTO;
import franco.ortiz.blogspring.dto.impl.input.UserDTOInput;
import franco.ortiz.blogspring.dto.impl.output.UserDTOOutput;
import franco.ortiz.blogspring.entity.UserEntity;
import franco.ortiz.blogspring.respository.IUserRepo;
import franco.ortiz.blogspring.service.IUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepo userRepo;

    public UserServiceImpl(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserDTOOutput> findAll() {
        return userRepo.findAll().stream()
                .map(user -> {
                    return (UserDTOOutput) MappingDTO.convertToDto(user, new UserDTOOutput());
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserDTOOutput save(UserDTOInput userDTOInput) {
        UserEntity user = (UserEntity) MappingDTO.convertToEntity(userDTOInput, UserEntity.class);
        UserDTOOutput userDTOOutput = (UserDTOOutput) MappingDTO.convertToDto(userRepo.save(user), new UserDTOOutput());
        return userDTOOutput;
    }
}
