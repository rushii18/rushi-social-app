package com.rushi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rushi.config.JwtProvider;
import com.rushi.models.User;
import com.rushi.repository.UserRepository;
import com.rushi.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@PostMapping("/users")
	public User CreateUser(@RequestBody User user) {

		User newUser = userService.registeruser(user);

		return newUser;
	}

	@GetMapping("/api/users")
	public List<User> getallUser() {

		List<User> users = userRepository.findAll();

		return users;
	}

	@GetMapping("/api/users/{userid}")
	public User getUserByid(@PathVariable("userid") Integer id) throws Exception {

		User user = userService.findUserByid(id);
		return user;

	}

	@PutMapping("/api/users/")
	public User updateuser(@RequestBody User user, @RequestHeader("Authorization") String jwt) throws Exception {
		
		User jwtuser = userService.findUserfromJwt(jwt);

		User updateduser = userService.updateUser(user, jwtuser.getId());
		return updateduser;
	}

	@DeleteMapping("/api/users/{userid}")
	public String deletuser(@PathVariable Integer userid) throws Exception {
		
		

		Optional<User> user = userRepository.findById(userid);

		if (user.isEmpty()) {
			throw new Exception("id not exist" + userid);
		}

		userRepository.deleteById(userid);

		return "delet user successfully" + userid;
	}

	@PutMapping("/api/users/{userid2}")
	public User followUserHandler( @RequestHeader("Authorization") String jwt, @PathVariable Integer userid2) throws Exception {

		User requestuser = userService.findUserfromJwt(jwt);
		
		User user = userService.FollowUser(requestuser.getId(), userid2);

		return user;
	}

	@GetMapping("/api//users/searchuser")
	public List<User> searchuser(@RequestParam String query) {
		List<User> users = userService.searchUser(query);

		return users;
	}

	@GetMapping("/api/users/email")
	public User searchByEmail(@PathVariable String email) {

		User user = userService.findUserByEmail(email);
		return user;
	}
	
	@GetMapping("/api/users/profile")
	public User getUserbyToken(@RequestHeader("Authorization") String jwt) {
		
		User user = userService.findUserfromJwt(jwt);
		
		return user;
	}
	
	

}
