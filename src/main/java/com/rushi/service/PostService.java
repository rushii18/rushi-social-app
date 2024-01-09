package com.rushi.service;

import java.util.List;

import com.rushi.models.Post;

public interface PostService {

  public 	Post createNewPost(Post post , Integer userid)throws Exception;
	
	 public String deletPost(Integer postid,Integer userid) throws Exception;
	
	List<Post> findPostByUserId(Integer userid);
	
	 public Post finidPostByid(Integer userid) throws Exception;
	
	 public List<Post> findAllPost();
	 
	 public Post savePost(Integer postid , Integer userid) throws Exception;
	 
	 public Post likePost(Integer postid,Integer userid) throws Exception;
	
}
