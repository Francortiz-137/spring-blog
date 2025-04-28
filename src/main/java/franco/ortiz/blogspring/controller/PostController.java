package franco.ortiz.blogspring.controller;


import franco.ortiz.blogspring.DTO.PostDTOInput;
import franco.ortiz.blogspring.entity.PostEntity;
import franco.ortiz.blogspring.service.IPostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private IPostService postService;
    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostEntity> listAll(){
        return postService.findAll();
    }
}
