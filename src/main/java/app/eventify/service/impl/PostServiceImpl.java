package app.eventify.service.impl;

import app.eventify.model.Post;
import app.eventify.model.exceptions.InvalidPostIdException;
import app.eventify.repository.PostRepository;
import app.eventify.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> listAll() {
        return this.postRepository.findAll();
    }

    @Override
    public Post findById(Long id) {
        return this.postRepository.findById(id).orElseThrow(() -> new InvalidPostIdException(id));
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post editPost(Post editedPost) {
        Post post = postRepository.findById(editedPost.getId())
                .orElseThrow(() -> new InvalidPostIdException(editedPost.getId()));

        post.setName(editedPost.getName());
        post.setDate(editedPost.getDate());
        //post.setComments(editedPost.getComments());

        return postRepository.save(post);
    }


}
