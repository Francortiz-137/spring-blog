package franco.ortiz.blogspring.service.Impl;

import franco.ortiz.blogspring.dto.impl.output.PostDTOOutput;
import franco.ortiz.blogspring.entity.PostEntity;
import franco.ortiz.blogspring.entity.UserEntity;
import franco.ortiz.blogspring.respository.IPostRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private IPostRepo postRepo;

    @InjectMocks
    private PostServiceImpl postService;


    @Test
    void getAllPostsShouldReturnAllPosts() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("john_doe");

        List<PostEntity> mockPosts = List.of(
                new PostEntity(1L, "First Post", "Content 1", LocalDateTime.now(), user),
                new PostEntity(2L, "Second Post", "Content 2", LocalDateTime.now(), user)
        );

        when(postRepo.findAll()).thenReturn(mockPosts);

        List<PostDTOOutput> result = postService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("First Post", result.get(0).getTitle());
        assertEquals("Content 1", result.get(0).getContent());
        assertEquals("Second Post", result.get(1).getTitle());
        assertEquals("Content 2", result.get(1).getContent());
    }
}