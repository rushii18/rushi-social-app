package com.rushi.serviceimplementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rushi.models.Chat;
import com.rushi.models.User;
import com.rushi.repository.ChatRepository;
import com.rushi.service.ChatService;
import com.rushi.service.UserService;

@Service
public class ChatServiceImplementation implements ChatService {

	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Chat createchat(Chat chat, Integer userid) {
		
		//Chat chat2 = chatRepository.findByUserId(userid);
		
		
//		chat2.setChatname(chat.getChatname());
//		chat2.setImage(chat.getImage());
//		chat2.setTime(LocalDateTime.now());
//		
//		
		
		return null;
	}

	@Override
	public Chat findChatByid(Integer userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Chat> findUserChat(Integer userid) {
		// TODO Auto-generated method stub
		return null;
	}

}
