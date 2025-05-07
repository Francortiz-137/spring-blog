package franco.ortiz.blogspring.service;

import franco.ortiz.blogspring.common.JwtUtil;
import franco.ortiz.blogspring.dto.common.MappingDTO;
import franco.ortiz.blogspring.dto.impl.UserLoginDTO;
import franco.ortiz.blogspring.dto.impl.UserRegisterDTO;
import franco.ortiz.blogspring.entity.Role;
import franco.ortiz.blogspring.entity.UserEntity;
import franco.ortiz.blogspring.exception.ResourceAlreadyExistsException;
import franco.ortiz.blogspring.respository.IUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

    private final IUserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(IUserRepo userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void register(UserRegisterDTO request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            log.error("Usuario ya existe");
            throw new ResourceAlreadyExistsException("Usuario ya existe");
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            log.error("Email ya existe");
            throw new RuntimeException("Email ya existe");
        }

        UserEntity user = (UserEntity) MappingDTO.convertToEntity( request, UserEntity.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf("ROLE_USER"));
        userRepository.save(user);
    }

    public String login(UserLoginDTO dto) {
        UserEntity user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}
