package app.eventify.web.controller;

import app.eventify.model.Post;
import app.eventify.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
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
    // @PreAuthorize("hasAuthority('create:post')")
    public Post createNewPost(@Valid @RequestParam String name,  @RequestParam String content, @RequestParam Long userId) {
        return postService.createPost(name, content, userId);
    }

    @PatchMapping("/editPost")
    // @PreAuthorize("hasAuthority('edit:post')")
    public Post editPost(@Valid @RequestParam Long postId, @RequestParam String name, @RequestParam String content) {
        return postService.editPost(postId, name, content);
    }

    @DeleteMapping("/deleteById/{id}")
    // @PreAuthorize("hasAuthority('delete:post')")
    public void deletePost(@PathVariable ("id") Long id) {
        postService.deleteById(id);
    }

    @PostMapping("/likePost")
    public Post likePost(@RequestParam Long postId, @RequestParam Long userId) {
        return postService.likePost(postId, userId);
    }

    @GetMapping("{postId}/likes")
    public int calculateLikes(@PathVariable Long postId) {
        return postService.calculateLikes(postId);
    }
}
