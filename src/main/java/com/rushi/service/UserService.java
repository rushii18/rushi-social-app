package com.rushi.service;

import java.util.List;

import com.rushi.models.User;

public interface UserService {

	public User registeruser(User user);

	public User findUserByid(Integer id) throws Exception;

	public User findUserByEmail(String email);

	public User FollowUser(Integer userid1, Integer userid2) throws Exception;

	public User updateUser(User user, Integer userid) throws Exception;

	public List<User> searchUser(String qury);
	
	public User findUserfromJwt(String jwt);
	
	public String deletUser(Integer userid);

}