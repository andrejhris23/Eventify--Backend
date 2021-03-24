package app.eventify.service;

import app.eventify.model.Post;
import app.eventify.service.impl.PostServiceImpl;

import java.util.List;

public interface PostService {

    List<Post> listAll();

    Post findById(Long id);

    void deleteById(Long id);

    Post createPost(String name, String content);

    Post editPost(Post editedPost);

    void likePost(Long postId);

    int calculateLikes(Long postId);
}
