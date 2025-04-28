package franco.ortiz.blogspring.controller;

import franco.ortiz.blogspring.dto.impl.input.UserDTOInput;
import franco.ortiz.blogspring.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDTOInput userDTOInput){
        userService.registerUser(userDTOInput);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }
}
