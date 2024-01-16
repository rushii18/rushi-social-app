package com.rushi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.rushi.models.Post;
import com.rushi.models.User;
import com.rushi.repository.UserRepository;
import com.rushi.respones.ApiRespons;
import com.rushi.service.PostService;
import com.rushi.service.UserService;

@RestController
public class PostController {
	@Autowired
	PostService postService;

	@Autowired
	UserService userService;

	@PostMapping("/api/posts/user")
	public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestHeader("Authorization") String jwt)
			throws Exception {

		User userpost = userService.findUserfromJwt(jwt);

		Post postcreate = postService.createNewPost(post, userpost.getId());

		return new ResponseEntity<Post>(postcreate, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/api/posts/{postid}/user")
	public ResponseEntity<ApiRespons> deletePost(@PathVariable Integer postid,
			@RequestHeader("Authorization") String jwt) throws Exception {

		User userpost = userService.findUserfromJwt(jwt);
		String massge = postService.deletPost(postid, userpost.getId());

		ApiRespons apiRespons = new ApiRespons(massge, true);

		return new ResponseEntity<ApiRespons>(apiRespons, HttpStatus.OK);
	}

	@GetMapping("/api/posts/{postid}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postid) throws Exception {

		Post post = postService.finidPostByid(postid);

		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}

	@GetMapping("/api/posts/user/{userid}")
	public ResponseEntity<List<Post>> findUserPost(@PathVariable Integer userid) {

		List<Post> posts = postService.findPostByUserId(userid);

		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}

	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> findAllPost() {

		List<Post> Allposts = postService.findAllPost();

		return new ResponseEntity<List<Post>>(Allposts, HttpStatus.OK);
	}

	@PutMapping("/api/posts/savepost/{postid}/user")
	public ResponseEntity<Post> savePostHandler(@PathVariable Integer postid,
			@RequestHeader("Authorization") String jwt) throws Exception {

		User userpost = userService.findUserfromJwt(jwt);

		Post savepost = postService.savePost(postid, userpost.getId());

		return new ResponseEntity<Post>(savepost, HttpStatus.ACCEPTED);
	}

	@PutMapping("/api/posts/like/{postid}")
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postid,
			@RequestHeader("Authorization") String jwt) throws Exception {

		User userpost = userService.findUserfromJwt(jwt);

		Post likepost = postService.likePost(postid, userpost.getId());

		return new ResponseEntity<Post>(likepost, HttpStatus.ACCEPTED);
	}

}
