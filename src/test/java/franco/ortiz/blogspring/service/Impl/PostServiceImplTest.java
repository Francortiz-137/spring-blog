package franco.ortiz.blogspring.service.Impl;

import franco.ortiz.blogspring.dto.impl.input.PostDTOInput;
import franco.ortiz.blogspring.dto.impl.output.PostDTOOutput;
import franco.ortiz.blogspring.entity.PostEntity;
import franco.ortiz.blogspring.entity.UserEntity;
import franco.ortiz.blogspring.respository.IPostRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private IPostRepo postRepo;

    @InjectMocks
    private PostServiceImpl postService;

    private UserEntity user;
    private List<PostEntity> posts;

    @BeforeEach
    void setUp() {
        user = new UserEntity();
        user.setId(1L);
        user.setUsername("john_doe");
        posts = List.of(
                new PostEntity(1L, "First Post", "Content 1", LocalDateTime.now(), user),
                new PostEntity(2L, "Second Post", "Content 2", LocalDateTime.now(), user)
        );
    }

    @Test
    void getAllPostsShouldReturnAllPosts() {


        when(postRepo.findAll()).thenReturn(posts);

        List<PostDTOOutput> result = postService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("First Post", result.get(0).getTitle());
        assertEquals("Content 1", result.get(0).getContent());
        assertEquals("Second Post", result.get(1).getTitle());
        assertEquals("Content 2", result.get(1).getContent());
    }

    @DisplayName("Debería retornar un post por su ID y mapearlo a DTOOutput")
    @Test
    void getPostByIdShouldReturnPost() {
        Long postId = 1L;

        PostEntity post = PostEntity.builder()
                .id(postId)
                .title("Título de prueba")
                .content("Contenido de prueba")
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();

        when(postRepo.findById(postId)).thenReturn(Optional.of(post));

        PostDTOOutput result = postService.findById(postId);

        assertNotNull(result);
        assertEquals("Título de prueba", result.getTitle());
        assertEquals("Contenido de prueba", result.getContent());

        verify(postRepo).findById(postId);
    }


    @DisplayName("Debería guardar un nuevo post a partir de un DTOInput")
    @Test
    void savePostShouldSavePost() {
        // DTO de entrada
        PostDTOInput input = new PostDTOInput();
        input.setTitle("Nuevo Post");
        input.setContent("Contenido del post");
        input.setAuthorId(1L);


        // Post simulado que se espera guardar
        PostEntity savedPost = PostEntity.builder()
                .id(1L)
                .title(input.getTitle())
                .content(input.getContent())
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();

        // Mock del comportamiento del repositorio
        when(postRepo.save(any(PostEntity.class))).thenReturn(savedPost);

        // Llamada al servicio
        PostDTOOutput result = postService.save(input);

        // Verificaciones
        assertNotNull(result);
        assertEquals(input.getTitle(), result.getTitle());
        assertEquals(input.getContent(), result.getContent());

        verify(postRepo).save(any(PostEntity.class));
    }


    @DisplayName("Debería actualizar un post existente correctamente desde un DTO")
    @Test
    void updatePostShouldUpdatePost() {
        // ID del post a actualizar
        Long postId = 1L;

        // Post existente simulado
        PostEntity existingPost = PostEntity.builder()
                .id(postId)
                .title("Old Title")
                .content("Old Content")
                .user(user)
                .createdAt(LocalDateTime.now().minusDays(1))
                .build();

        // Datos de entrada (DTOInput)
        PostDTOInput input = new PostDTOInput();
        input.setTitle("New Title");
        input.setContent("New Content");
        input.setAuthorId(1L);

        // Mock del repositorio
        when(postRepo.findById(postId)).thenReturn(Optional.of(existingPost));
        when(postRepo.save(any(PostEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Llamada al métod del servicio
        PostDTOOutput result = postService.update(postId, input);

        // Verificaciones
        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
        assertEquals("New Content", result.getContent());

        verify(postRepo).save(any(PostEntity.class));
    }



    @Test
    void deletePostShouldDeletePost() {
        postService.deleteById(1L);
        verify(postRepo).deleteById(1L);
    }
}