package franco.ortiz.blogspring.service;

import franco.ortiz.blogspring.dto.impl.output.PostDTOOutput;
import franco.ortiz.blogspring.entity.PostEntity;

import java.util.List;

public interface IPostService {
    List<PostDTOOutput> findAll();
}
