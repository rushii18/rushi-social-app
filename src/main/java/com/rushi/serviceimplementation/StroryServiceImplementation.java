package com.rushi.serviceimplementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rushi.models.Story;
import com.rushi.models.User;
import com.rushi.repository.StoryRepository;
import com.rushi.service.StoryService;
import com.rushi.service.UserService;

@Service
public class StroryServiceImplementation implements StoryService {

	@Autowired
	private StoryRepository storyRepository;

	@Autowired
	private UserService userService;

	@Override
	public Story createStory(Story story, Integer userid) throws Exception {
		Story storyCreate = new Story();
		User user = userService.findUserByid(userid);

		storyCreate.setStory(story.getStory());
		storyCreate.setCaption(story.getCaption());
		storyCreate.setTime(LocalDateTime.now());
		storyCreate.setUser(user);

	//	Story story2 = storyRepository.save(storyCreate);

		return storyRepository.save(storyCreate);
	}

	@Override
	public List<Story> getAllstory() {

		List<Story> Allstory = storyRepository.findAll();

		return Allstory;
	}

	@Override
	public List<Story> findStoryByUserId(Integer userid) throws Exception {

		 User user = userService.findUserByid(userid);

		List<Story> story = storyRepository.findByUserId(userid);

		return story;
	}

}
