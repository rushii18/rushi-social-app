package com.rushi.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rushi.config.JwtProvider;
import com.rushi.config.JwtValidator;
import com.rushi.models.User;
import com.rushi.repository.PostRepository;
import com.rushi.repository.UserRepository;
import com.rushi.respones.Authrespons;
import com.rushi.service.UserService;

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
	public User findUserByEmail(String email) {

		User useremail = userRepository.findByEmail(email);
		return useremail;
	}

	@Override
	public User FollowUser(Integer requestUserid, Integer userid2) throws Exception {
		User requestUser = findUserByid(requestUserid);
		User user2 = findUserByid(userid2);

		user2.getFollowers().add(requestUser.getId());
		requestUser.getFollowing().add(user2.getId());

		userRepository.save(requestUser);
		userRepository.save(user2);

		return requestUser;
	}

	@Override
	public User updateUser(User user, Integer userid) throws Exception {

		Optional<User> presentuser = userRepository.findById(userid);

		if (presentuser.isPresent()) {

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
		throw new Exception("id not exist" + userid);
	}

	@Override
	public List<User> searchUser(String qury) {

		return userRepository.searchByUser(qury);
	}

	@Override
	public User findUserfromJwt(String jwt) {

		String email = JwtProvider.getEmailfromJwtTokenst(jwt);

		User user = userRepository.findByEmail(email);

		return user;
	}

	@Override
	public String deletUser(Integer userid) {
	
		 userRepository.deleteById(userid);
		
		return "id deleted "+ userid;
	}

	
}
