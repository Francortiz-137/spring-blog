package franco.ortiz.blogspring.service.Impl;

import franco.ortiz.blogspring.entity.PostEntity;
import franco.ortiz.blogspring.respository.IPostRepo;
import franco.ortiz.blogspring.service.IPostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements IPostService {

    private IPostRepo postRepo;

    public PostServiceImpl(IPostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public List<PostEntity> findAll() {
        return postRepo.findAll();
    }
}
