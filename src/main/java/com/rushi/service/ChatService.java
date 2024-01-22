package com.rushi.service;

import java.util.List;

import com.rushi.models.Chat;
import com.rushi.models.User;

public interface ChatService {
	
	public Chat createchat(Chat chat , Integer userid);
	
	public Chat findChatByid(Integer userid);
		
	public List< Chat> findUserChat(Integer userid);

}
