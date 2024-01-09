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
import org.springframework.web.bind.annotation.RestController;

import com.rushi.models.Post;
import com.rushi.respones.ApiRespons;
import com.rushi.service.PostService;

@RestController
public class PostController {
	@Autowired
	PostService postService;

	@PostMapping("/posts/user/{userid}")
	public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userid) throws Exception {

		Post postcreate = postService.createNewPost(post, userid);

		return new ResponseEntity<Post>(postcreate, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/posts/{postid}/user/{userid}")
	public ResponseEntity<ApiRespons> deletePost(@PathVariable Integer postid, @PathVariable Integer userid)
			throws Exception {

		String massge = postService.deletPost(postid, userid);

		ApiRespons apiRespons = new ApiRespons(massge, true);

		return new ResponseEntity<ApiRespons>(apiRespons, HttpStatus.OK);
	}

	@GetMapping("/posts/{postid}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postid) throws Exception {

		Post post = postService.finidPostByid(postid);

		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}

	@GetMapping("/posts/user/{userid}")
	public ResponseEntity<List<Post>> findUserPost(@PathVariable Integer userid) {

		List<Post> posts = postService.findPostByUserId(userid);

		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<List<Post>> findAllPost() {

		List<Post> Allposts = postService.findAllPost();

		return new ResponseEntity<List<Post>>(Allposts, HttpStatus.OK);
	}

	@PutMapping("/posts/savepost/{postid}/user/{userid}")
	public ResponseEntity<Post> savePostHandler(@PathVariable Integer postid, @PathVariable Integer userid)
			throws Exception {

		Post savepost = postService.savePost(postid, userid);

		return new ResponseEntity<Post>(savepost, HttpStatus.ACCEPTED);
	}

	@PutMapping("/posts/like/{postid}/user/{userid}")
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postid, @PathVariable Integer userid)
			throws Exception {

		Post likepost = postService.likePost(postid, userid);

		return new ResponseEntity<Post>(likepost, HttpStatus.ACCEPTED);
	}

}
