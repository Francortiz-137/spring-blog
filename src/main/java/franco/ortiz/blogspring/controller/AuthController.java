package franco.ortiz.blogspring.controller;

import franco.ortiz.blogspring.dto.impl.JwtResponseDTO;
import franco.ortiz.blogspring.dto.impl.UserLoginDTO;
import franco.ortiz.blogspring.dto.impl.UserRegisterDTO;
import franco.ortiz.blogspring.dto.impl.input.UserDTOInput;
import franco.ortiz.blogspring.service.AuthService;
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
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterDTO dto) {
        authService.register(dto);
        return ResponseEntity.ok("Usuario registrado con éxito");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody UserLoginDTO dto) {
        String token = authService.login(dto);
        return ResponseEntity.ok(new JwtResponseDTO(token));
    }
}
