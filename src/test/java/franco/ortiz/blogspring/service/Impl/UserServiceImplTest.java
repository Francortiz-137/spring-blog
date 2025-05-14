package franco.ortiz.blogspring.service.Impl;

import franco.ortiz.blogspring.dto.common.MappingDTO;
import franco.ortiz.blogspring.dto.impl.input.UserDTOInput;
import franco.ortiz.blogspring.dto.impl.output.UserDTOOutput;
import franco.ortiz.blogspring.entity.UserEntity;
import franco.ortiz.blogspring.respository.IUserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private IUserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    private final List<UserEntity> users = new ArrayList<>();

    @BeforeEach
    void setUp() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("john_doe");
        user.setEmail("john.doe@gmail.com");
        user.setPhoneNumber("1234567890");
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword("password");
        users.add(user);

        UserEntity user2 = UserEntity.builder()
                .id(2L)
                .username("john dos")
                .password("1234")
                .email("john.doe2@gmail.com")
                .phoneNumber("2234567890")
                .createdAt(LocalDateTime.now())
                .build();
        UserEntity user3 = UserEntity.builder()
                .id(3L)
                .username("john dos")
                .password("1234")
                .email("john.doe3@gmail.com")
                .phoneNumber("3234567890")
                .build();
        users.add(user2);
        users.add(user3);
    }

    @DisplayName("Deberia devolver todos los usuarios")
    @Test
    void findAllShouldReturnAllUsers() {
        when(userRepo.findAll()).thenReturn(users);

        List<UserDTOOutput> userDTOOutputs = users.stream().map(user -> (UserDTOOutput) MappingDTO.convertToDto(user, new UserDTOOutput()))
                .toList();

        assertEquals(userDTOOutputs, userService.findAll());
    }

    @DisplayName("Deberia retornar el usuario con el id dado")
    @Test
    void findByIdShouldReturnUser() {
        Long id = 1L;
        when(userRepo.findById(id)).thenReturn(Optional.of(users.getFirst()));

        UserDTOOutput user = userService.findById(id);

        assertNotNull(user);
        assertEquals(user, MappingDTO.convertToDto(user, new UserDTOOutput()));
    }

    @DisplayName("Debe actualizar los datos del usuario dado")
    @Test
    void updateShouldReturnUser() {
        Long id = 1L;

        UserDTOInput userInput = new UserDTOInput();
        userInput.setUsername("new username");
        userInput.setPassword("new password");
        userInput.setPhoneNumber("new phone number");
        userInput.setEmail("john.doe@gmail.com");

        UserEntity user = users.getFirst();

        when(userRepo.findById(id)).thenReturn(Optional.of(user));
        when(userRepo.save(user)).thenReturn(user);

        UserDTOOutput userDTOOutput = userService.update(id, userInput);

        assertNotNull(userDTOOutput);
        assertEquals(userDTOOutput, MappingDTO.convertToDto(user, new UserDTOOutput()));
    }

    @DisplayName("Deberia eliminar el usuario con el id dado")
    @Test
    void deleteShouldDeleteUser() {
        Long id = 1L;
        UserEntity user = users.getFirst();
        when(userRepo.findById(id)).thenReturn(Optional.of(users.getFirst()));

        userService.deleteById(id);
        verify(userRepo).delete(user);
    }
}