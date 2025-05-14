package franco.ortiz.blogspring.service.Impl;

import franco.ortiz.blogspring.dto.common.MappingDTO;
import franco.ortiz.blogspring.dto.impl.input.UserDTOInput;
import franco.ortiz.blogspring.dto.impl.output.UserDTOOutput;
import franco.ortiz.blogspring.entity.UserEntity;
import franco.ortiz.blogspring.exception.BadRequestException;
import franco.ortiz.blogspring.respository.IUserRepo;
import franco.ortiz.blogspring.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTOOutput> findAll() {
        return userRepo.findAll().stream()
                .map(user -> (UserDTOOutput) MappingDTO.convertToDto(user, new UserDTOOutput()))
                .collect(Collectors.toList());
    }


    @Override
    public void registerUser(UserDTOInput userDTO) {
        if (userRepo.existsByEmail(userDTO.getEmail())) {
            throw new BadRequestException("Email ya registrado");
        }

        UserEntity user = (UserEntity) MappingDTO.convertToEntity(userDTO, UserEntity.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);
    }

    @Override
    public UserDTOOutput findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public UserDTOOutput update(Long id, UserDTOInput input) {
        return null;
    }
}
