package app.eventify.web.controller;

import app.eventify.model.Post;
import app.eventify.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/new")
    public Post createNewPost(@RequestParam String name, @RequestParam String content) {
        return postService.createPost(name, content);
    }

    @PostMapping("/like")
    public void likePost(@RequestParam Long postId) {
        postService.likePost(postId);
    }

    @GetMapping("{postId}/likes")
    public int calculateLikes(@PathVariable Long postId) {
        return postService.calculateLikes(postId);
    }
}
