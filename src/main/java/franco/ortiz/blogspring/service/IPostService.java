package franco.ortiz.blogspring.service;

import franco.ortiz.blogspring.dto.impl.input.PostDTOInput;
import franco.ortiz.blogspring.dto.impl.output.PostDTOOutput;

import java.util.List;

public interface IPostService {
    List<PostDTOOutput> findAll();

    PostDTOOutput findById(Long postId);

    PostDTOOutput save(PostDTOInput input);

    PostDTOOutput update(Long postId, PostDTOInput input);

    void deleteById(Long id);
}
