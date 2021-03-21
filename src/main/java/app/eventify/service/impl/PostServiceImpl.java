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
        return this.postRepository.findById(id).orElseThrow(InvalidPostIdException::new);
    }
    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }


}
