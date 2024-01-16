package com.rushi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.rushi.models.Comment;
import com.rushi.models.Post;
import com.rushi.models.User;
import com.rushi.service.CommentService;
import com.rushi.service.UserService;

import jakarta.websocket.server.PathParam;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

	@PostMapping("/api/comment/post/{postid}")
	public Comment CreateComment(@RequestBody Comment comment, @RequestHeader("Authorization") String jwt,
			@PathVariable("postid") Integer postid) throws Exception {
		User user = userService.findUserfromJwt(jwt);

		Comment createdcomment = commentService.CreateComment(comment, postid, user.getId());

		return createdcomment;

	}

	@PutMapping("/api/comment/like/{commentid}")
	public Comment likecomment(@RequestHeader("Authorization") String jwt, @PathVariable("commentid") Integer commentid)
			throws Exception {
		User user = userService.findUserfromJwt(jwt);

		Comment likecomment = commentService.likecomment(commentid, user.getId());

		return likecomment;

	}

}
