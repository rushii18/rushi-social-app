package com.rushi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.rushi.models.Reels;
import com.rushi.models.User;
import com.rushi.service.ReelsService;
import com.rushi.service.UserService;

@RestController
public class ReelsController {

	@Autowired
	private UserService userService;

	@Autowired
	private ReelsService reelsService;

	@PostMapping("/api/reels")
	public Reels Createreesl(@RequestBody Reels reels, @RequestHeader("Authorization") String jwt) {
		User user = userService.findUserfromJwt(jwt);

		Reels reelsCreate = reelsService.Createreesl(user, reels);

		return reelsCreate;
	}
	
	
	@GetMapping("/api/reels/allreels")
	public List<Reels> getAllReels() throws Exception {

		List<Reels> reels = reelsService.finsAllReels();

		return reels;
	}
	
	
	

	@GetMapping("/api/reels/users/{userid}")
	public List<Reels> getAllReels(@PathVariable("userid") Integer userid) throws Exception {

		List<Reels> reels = reelsService.findUserReels(userid);

		return reels;
	}

}
