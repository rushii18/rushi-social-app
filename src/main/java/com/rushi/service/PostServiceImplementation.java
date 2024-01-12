package com.rushi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.dialect.function.PostgreSQLTruncRoundFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rushi.models.Post;
import com.rushi.models.User;
import com.rushi.repository.PostRepository;
import com.rushi.repository.UserRepository;

@Service
public class PostServiceImplementation implements PostService {
	@Autowired
	PostRepository postRepository;
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;

	@Override
	public Post createNewPost(Post post, Integer userid) throws Exception {

		Post newPost = new Post();
		User user = userService.findUserByid(userid);

		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setViedo(post.getViedo());
		newPost.setCreatedAT(LocalDateTime.now());
		newPost.setUser(user);

		return postRepository.save(newPost);
		return null;
	}

	@Override
	public String deletPost(Integer postid, Integer userid) throws Exception {
		Post post = finidPostByid(userid);
		User user = userService.findUserByid(userid);

		if (post.getUser().getId() != user.getId()) {
			throw new Exception("you can't delet other user post");

		}
		postRepository.delete(post);
		return "your post delet successfull";
	}

	@Override
	public List<Post> findPostByUserId(Integer userid) {

		return postRepository.findPostByUserId(userid);
	}

	@Override
	public Post finidPostByid(Integer userid) throws Exception {

		Optional<Post> opt = postRepository.findById(userid);
		if (opt.isEmpty()) {
			throw new Exception("post not found with id " + userid);
		}

		return opt.get();
	}

	@Override
	public List<Post> findAllPost() {

		return postRepository.findAll();
	}

	@Override
	public Post savePost(Integer postid, Integer userid) throws Exception {

		Post post = finidPostByid(postid);
		User user = userService.findUserByid(userid);

		if (user.getSavepost().contains(post)) {
			user.getSavepost().remove(post);
		} else {
			user.getSavepost().add(post);
		}

		userRepository.save(user);
		return post;
	}

	@Override
	public Post likePost(Integer postid, Integer userid) throws Exception {
		Post post = finidPostByid(postid);
		User user = userService.findUserByid(userid);

		if (post.getLikeuser().contains(user)) {
			post.getLikeuser().remove(user);
		} else {
			post.getLikeuser().add(user);

		}

		return postRepository.save(post);
	}

}
