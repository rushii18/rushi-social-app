package com.rushi.service;

import com.rushi.models.Comment;
import com.rushi.models.User;

public interface CommentService {

	public Comment CreateComment(Comment comment, Integer postid, Integer userid) throws Exception;

	public Comment likecomment(Integer id, Integer userid) throws Exception;
	
	public Comment findCommentbyID(Integer commentId) throws Exception;

}
