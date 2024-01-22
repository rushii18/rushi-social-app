package com.rushi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.rushi.models.Story;
import com.rushi.models.User;
import com.rushi.service.StoryService;
import com.rushi.service.UserService;

@RestController
public class StoryController {

	@Autowired
	private UserService userService;

	@Autowired
	private StoryService storyService;

	@PostMapping("api/story")
	public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserfromJwt(jwt);

		Story storycreate = storyService.createStory(story, user.getId());

		return storycreate;
	}

	@GetMapping("api/Allstory")
	public List<Story> getAllstory() {

		return storyService.getAllstory();
	}

	@GetMapping("api/story/user/{userid}")
	public List<Story> getstoryByUserid(@PathVariable("userid") Integer userid) throws Exception {

		List<Story> story = storyService.findStoryByUserId(userid);

		return story;
	}

}
