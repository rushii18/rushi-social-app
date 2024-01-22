package com.rushi.service;

import java.util.List;

import com.rushi.models.Story;
import com.rushi.models.User;

public interface StoryService  {

	public Story createStory(Story story, Integer userid) throws Exception;
	
	public List<Story> getAllstory();
	
	public List<Story> findStoryByUserId(Integer userid) throws Exception;
	
	
}
