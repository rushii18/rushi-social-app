package com.rushi.serviceimplementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.dialect.function.PostgreSQLTruncRoundFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rushi.config.JwtProvider;
import com.rushi.models.Post;
import com.rushi.models.User;
import com.rushi.repository.PostRepository;
import com.rushi.repository.UserRepository;
import com.rushi.respones.Authrespons;
import com.rushi.service.PostService;
import com.rushi.service.UserService;

@Service
public class PostServiceImplementation implements PostService {

	@Autowired
	private PostRepository postRepository;
//	@Autowired
//	//private PostService postService;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

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
