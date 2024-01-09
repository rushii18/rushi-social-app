package com.rushi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rushi.models.User;
import com.rushi.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User registeruser(User user) {

		User usercreate = new User();

		usercreate.setFirstName(user.getFirstName());
		usercreate.setLastName(user.getLastName());
		usercreate.setPassword(user.getPassword());
		usercreate.setContactNo(user.getContactNo());
		usercreate.setEmail(user.getEmail());
		usercreate.setId(user.getId());
		usercreate.setGender(user.getGender());
		User newuser = userRepository.save(usercreate);
		return newuser;
	}

	@Override
	public User findUserByid(Integer id) throws Exception {

		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			return user.get();

		}

		throw new Exception("user not exist with userid" + id);
	}

	@Override
	public User findUserByEmail(String Email) {

		User useremail = userRepository.findByEmail(Email);
		return useremail;
	}

	@Override
	public User FollowUser(Integer userid1, Integer userid2) throws Exception {
		User user1 = findUserByid(userid1);
		User user2 = findUserByid(userid2);

		user2.getFollowers().add(user1.getId());
		user1.getFollowing().add(user2.getId());

		userRepository.save(user1);
		userRepository.save(user2);

		return user1;
	}

	@Override
	public User updateUser(User user, Integer userid) throws Exception {

		Optional<User> presentuser = userRepository.findById(userid);

		if (presentuser.isEmpty()) {
			throw new Exception("id not exist" + userid);
		}

		User user5 = presentuser.get();

		if (user.getFirstName() != null) {
			user5.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			user5.setLastName(user.getLastName());
		}
		if (user.getEmail() != null) {
			user5.setEmail(user.getEmail());
		}
		if (user.getPassword() != null) {
			user5.setPassword(user.getPassword());
		}
		if (user.getContactNo() != null) {
			user5.setContactNo(user.getContactNo());
		}
		User updateuser = userRepository.save(user5);

		return updateuser;
	}

	@Override
	public List<User> searchUser(String qury) {

		return userRepository.searchByUser(qury);
	}

}
