package franco.ortiz.blogspring.controller;

import franco.ortiz.blogspring.DTO.UserDTOInput;
import franco.ortiz.blogspring.entity.UserEntity;
import franco.ortiz.blogspring.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserEntity> findAll(){
        return userService.findAll();
    }

    @PostMapping("")
    public UserEntity save(@Valid @RequestBody UserDTOInput userDTO) {
        UserEntity user = UserEntity.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .phoneNumber(userDTO.getPhoneNumber())
                .createdAt(java.time.LocalDateTime.now())
                .build();
        return userService.save(user);
    }
}
