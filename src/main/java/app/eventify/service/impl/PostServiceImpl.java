package app.eventify.service.impl;

import app.eventify.model.Post;
import app.eventify.model.User;
import app.eventify.model.exceptions.InvalidLikedPostException;
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
    public List<Post> findAll() {
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
    public Post editPost(Long postId, String name, String content) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new InvalidPostIdException(postId));

        post.setName(name);
        post.setContent(content);
        //post.setDate(editedPost.getDate());
        //post.setComments(editedPost.getComments());

        return postRepository.save(post);
    }

    @Override
    public Post likePost(Long postId, Long userId) {
        User currentUser = userRepository.findById(userId).orElseThrow(() -> new InvalidUserIdException(userId));
        Post likedPost = postRepository.findById(postId).orElseThrow(() -> new InvalidPostIdException(postId));


    // !!!! podole ima frka so if uslovot. Treba da gleda dali e lajknat postot, pa posle da go lajkne !!!

         if ( likedPost.getLikesFromUsers().contains(currentUser) ){
            throw new InvalidLikedPostException(postId);
        }

        else {
               likedPost.getLikesFromUsers().add(currentUser);
               System.out.println(likedPost.getLikesFromUsers());
        return postRepository.save(likedPost);



        }

//        likedPost.getLikesFromUsers().add(currentUser);
//        return postRepository.save(likedPost);
    }

    @Override
    public int calculateLikes(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new InvalidPostIdException(postId));

        return post.getLikesFromUsers().size();
    }


}
