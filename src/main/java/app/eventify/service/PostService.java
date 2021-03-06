package app.eventify.service;

import app.eventify.model.Post;
import app.eventify.service.impl.PostServiceImpl;

import java.util.List;

public interface PostService {

    List<Post> findAll();

    Post findById(Long id);

    void deleteById(Long id);

    Post createPost(String name, String content, Long userId);

    Post editPost(Long postId, String name, String content);

    Post likePost(Long postId, Long userId);

    int calculateLikes(Long postId);
}
