package com.rushi.serviceimplementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rushi.models.Comment;
import com.rushi.models.Post;
import com.rushi.models.User;
import com.rushi.repository.CommentRepository;
import com.rushi.repository.PostRepository;
import com.rushi.service.CommentService;
import com.rushi.service.PostService;
import com.rushi.service.UserService;

@Service
public class CommentServiceImpementation implements CommentService {

	@Autowired
	private UserService userService;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PostService postService;

	@Autowired
	private PostRepository postRepository;

	@Override
	public Comment CreateComment(Comment comment, Integer postid, Integer userid) throws Exception {
		Comment createcomment = new Comment();

		User user = userService.findUserByid(userid);
		Post post = postService.finidPostByid(postid);

		createcomment.setUser(user);
		// createcomment.setId(comment.getId());
		createcomment.setContent(comment.getContent());
		createcomment.setCreatedat(LocalDateTime.now());

		Comment commnetSave = commentRepository.save(createcomment);

		post.getCommentUser().add(commnetSave);

		postRepository.save(post);

		return commnetSave;
	}

	@Override
	public Comment likecomment(Integer id, Integer userid) throws Exception {

		Comment comment = findCommentbyID(id);

		User user = userService.findUserByid(userid);

		if (comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		} else {
			comment.getLiked().remove(user);
		}

		return commentRepository.save(comment);
	}

	@Override
	public Comment findCommentbyID(Integer commentId) throws Exception {

		Optional<Comment> commentid = commentRepository.findById(commentId);

		if (commentid.isPresent()) {
			return commentid.get();
		}

		throw new Exception("comment id not " + commentId);
	}

}
