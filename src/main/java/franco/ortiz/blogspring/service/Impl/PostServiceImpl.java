package franco.ortiz.blogspring.service.Impl;

import franco.ortiz.blogspring.dto.common.MappingDTO;
import franco.ortiz.blogspring.dto.impl.input.PostDTOInput;
import franco.ortiz.blogspring.dto.impl.output.PostDTOOutput;
import franco.ortiz.blogspring.entity.PostEntity;
import franco.ortiz.blogspring.exception.ResourceNotFoundException;
import franco.ortiz.blogspring.respository.IPostRepo;
import franco.ortiz.blogspring.service.IPostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {

    private final IPostRepo postRepo;

    public PostServiceImpl(IPostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public List<PostDTOOutput> findAll() {

        return postRepo.findAll().stream()
                .map(post -> (PostDTOOutput) MappingDTO.convertToDto(post, new PostDTOOutput()))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTOOutput findById(Long postId) {
        Optional<PostEntity> postEntity = postRepo.findById(postId);

        if (postEntity.isEmpty()) {
            throw new ResourceNotFoundException("Post not found");
        }

        return (PostDTOOutput) MappingDTO.convertToDto(postEntity.get(), new PostDTOOutput());
    }

    @Override
    public PostDTOOutput save(PostDTOInput input) {
        PostEntity postEntity = postRepo.save((PostEntity) MappingDTO.convertToEntity(input, PostEntity.class));
        return (PostDTOOutput) MappingDTO.convertToDto(postEntity, new PostDTOOutput());
    }

    @Override
    public PostDTOOutput update(Long postId, PostDTOInput input) {
        Optional<PostEntity> post = postRepo.findById(postId);

        if (post.isEmpty()) {
            throw new ResourceNotFoundException("Post not found");
        }
        PostEntity postEntity = (PostEntity) MappingDTO.convertToEntity(input, PostEntity.class);
        postEntity.setId(postId);
        postRepo.save(postEntity);

        return (PostDTOOutput) MappingDTO.convertToDto(postEntity, new PostDTOOutput());
    }

    @Override
    public void deleteById(Long id) {
        PostEntity postEntity = postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Post not found"));

        postRepo.delete(postEntity);
    }
}
