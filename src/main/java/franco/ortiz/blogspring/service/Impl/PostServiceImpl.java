package franco.ortiz.blogspring.service.Impl;

import franco.ortiz.blogspring.dto.common.MappingDTO;
import franco.ortiz.blogspring.dto.impl.input.PostDTOInput;
import franco.ortiz.blogspring.dto.impl.output.PostDTOOutput;
import franco.ortiz.blogspring.entity.PostEntity;
import franco.ortiz.blogspring.respository.IPostRepo;
import franco.ortiz.blogspring.service.IPostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {

    private IPostRepo postRepo;

    public PostServiceImpl(IPostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public List<PostDTOOutput> findAll() {

        return postRepo.findAll().stream()
                .map(post -> {
                    return (PostDTOOutput) MappingDTO.convertToDto(post, new PostDTOOutput());

                })
                .collect(Collectors.toList());
    }

    @Override
    public PostDTOOutput findById(Long postId) {
        return null;
    }

    @Override
    public PostDTOOutput save(PostDTOInput input) {
        return null;
    }

    @Override
    public PostDTOOutput update(Long postId, PostDTOInput input) {
        return null;
    }

    @Override
    public void deleteById(long l) {

    }
}
