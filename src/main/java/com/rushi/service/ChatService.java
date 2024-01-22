package com.rushi.service;

import java.util.List;

import com.rushi.models.Chat;
import com.rushi.models.User;

public interface ChatService {
	
	public Chat createchat(User user , User reqUser);
	
	public Chat findChatByid(Integer chatid) throws Exception;
		
	public List<Chat> findUserChat(Integer userid);

}
