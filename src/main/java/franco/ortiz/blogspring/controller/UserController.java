package franco.ortiz.blogspring.controller;

import franco.ortiz.blogspring.dto.impl.input.UserDTOInput;
import franco.ortiz.blogspring.dto.impl.output.UserDTOOutput;
import franco.ortiz.blogspring.entity.UserEntity;
import franco.ortiz.blogspring.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTOOutput>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<UserDTOOutput> save(@Valid @RequestBody UserDTOInput userDTO) {

        return ResponseEntity.ok(userService.save(userDTO));
    }
}
