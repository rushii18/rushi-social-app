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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@PutMapping("/api/users/{userid}")
	public User updateuser(@RequestBody User user, @PathVariable Integer userid) throws Exception {

		User updateduser = userService.updateUser(user, userid);
		return updateduser;
	}

     @DeleteMapping("/api/users/{userid}")
     public String deletuser(@PathVariable Integer userid) throws Exception {
    	 
    	  Optional<User> user = userRepository.findById(userid);
     	 
    	  if(user.isEmpty()) {
    		  throw new Exception("id not exist" + userid);
    	  }
    	 
    	 userRepository.deleteById(userid);
    	 
    	 return "delet user successfully" +userid;
     }

	@PutMapping("/api/users/{userid1}/{userid2}")
	public User followUserHandler(@PathVariable Integer userid1, @PathVariable Integer userid2) throws Exception {

		User user = userService.FollowUser(userid1, userid2);

		return user;
	}

	@GetMapping("/api//users/searchuser")
	public List<User> searchuser(@RequestParam String query) {
		List<User> users = userService.searchUser(query);

		return users;
	}

	@GetMapping("/api/users/Email")
	public User searchByEmail(@PathVariable String Email) {

		User user = userService.findUserByEmail(Email);
		return user;
	}

}
