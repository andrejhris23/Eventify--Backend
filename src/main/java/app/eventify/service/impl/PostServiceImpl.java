package app.eventify.service.impl;

import app.eventify.model.Post;
import app.eventify.model.User;
import app.eventify.model.exceptions.InvalidPostIdException;
import app.eventify.model.exceptions.InvalidUserIdException;
import app.eventify.repository.PostRepository;
import app.eventify.repository.UserRepository;
import app.eventify.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
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
    public Post createPost(String name, String content, Long userId) {
        User postCreator = this.userRepository.findById(userId).orElseThrow(() -> new InvalidUserIdException(userId));
        /* User postCreator = new User(); */ // get the logged user from spring security
        Post newPost = new Post(name, content, postCreator);

        return postRepository.save(newPost);
    }

    @Override
    public Post editPost(Post editedPost) {
        Post post = postRepository.findById(editedPost.getId())
                .orElseThrow(() -> new InvalidPostIdException(editedPost.getId()));

        post.setName(editedPost.getName());
        post.setContent(editedPost.getContent());
        post.setDate(editedPost.getDate());
        //post.setComments(editedPost.getComments());

        return postRepository.save(post);
    }

    @Override
    public void likePost(Long postId) {
        User currentUser = new User(); // get this from spring security
        Post likedPost = postRepository.findById(postId).orElseThrow(() -> new InvalidPostIdException(postId));

        likedPost.getLikesFromUsers().add(currentUser);
        currentUser.getLikedPosts().add(likedPost);
    }

    @Override
    public int calculateLikes(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new InvalidPostIdException(postId));

        return post.getLikesFromUsers().size();
    }


}
