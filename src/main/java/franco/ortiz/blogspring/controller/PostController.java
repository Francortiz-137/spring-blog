package franco.ortiz.blogspring.controller;


import franco.ortiz.blogspring.dto.impl.input.PostDTOInput;
import franco.ortiz.blogspring.dto.impl.output.PostDTOOutput;
import franco.ortiz.blogspring.service.IPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final IPostService postService;
    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostDTOOutput>> listAll(){
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTOOutput> findById(@PathVariable Long id){
        return ResponseEntity.ok(postService.findById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<PostDTOOutput> savePost(@RequestBody PostDTOInput post){
        return ResponseEntity.ok(postService.save(post));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<PostDTOOutput> updatePost(@PathVariable Long id, @RequestBody PostDTOInput post){
        return ResponseEntity.ok(postService.update(id, post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        postService.deleteById(id);
        return ResponseEntity.ok("Post eliminado");
    }
}
