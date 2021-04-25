package app.eventify.web.controller;

import app.eventify.model.Post;
import app.eventify.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/getAllPosts")
    public List<Post> getAllPosts() {
        return postService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Post findById(@PathVariable("id") Long id) {
        return postService.findById(id);
    }

    @PostMapping("/createPost")
    public Post createNewPost(@Valid @RequestBody String name,  String content, Long userId) {
        return postService.createPost(name, content, userId);
    }

    @PatchMapping("/editPost")
    public Post editPost(@Valid @RequestBody Post editedPost) {
        return postService.editPost(editedPost);
    }

    @PostMapping("/likePost")
    public Post likePost(@RequestBody Long postId, @RequestBody Long userId) {
        return postService.likePost(postId, userId);
    }

    @GetMapping("{postId}/likes")
    public int calculateLikes(@PathVariable Long postId) {
        return postService.calculateLikes(postId);
    }
}
